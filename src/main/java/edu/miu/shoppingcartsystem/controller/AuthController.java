package edu.miu.shoppingcartsystem.controller;

import edu.miu.shoppingcartsystem.model.ERole;
import edu.miu.shoppingcartsystem.model.EmailDetails;
import edu.miu.shoppingcartsystem.model.Role;
import edu.miu.shoppingcartsystem.model.User;
import edu.miu.shoppingcartsystem.payload.request.LoginRequest;
import edu.miu.shoppingcartsystem.payload.request.SignupRequest;
import edu.miu.shoppingcartsystem.payload.response.JwtResponse;
import edu.miu.shoppingcartsystem.payload.response.MessageResponse;
import edu.miu.shoppingcartsystem.repository.RoleRepository;
import edu.miu.shoppingcartsystem.repository.UserRepository;
import edu.miu.shoppingcartsystem.service.EmailService;
import edu.miu.shoppingcartsystem.service.security.jwt.JwtUtils;
import edu.miu.shoppingcartsystem.service.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  boolean isVendor=false;
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private EmailService emailService;


  @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    isVendor=false;
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
    boolean isVendorAdmin;

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "vendor":
          Role modRole = roleRepository.findByName(ERole.ROLE_VENDOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);
          isVendor=true;

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    if(isVendor){
//      private String recipient;
//      private String msgBody;
//      private String subject;
//      private String attachment;
      StringBuilder sb= new StringBuilder();
      sb.append("Welcome "+user.getUsername() ).append(",\n").
              append("You have been added as a Vendor admin to Easy store.").
              append("To Login Credentials are as follows: \n").
              append("Username: "+user.getUsername()).append("\n").
              append("Password: "+signUpRequest.getPassword());

      EmailDetails ed= new EmailDetails(signUpRequest.getEmail(), sb.toString(), "Welcome to easy store", "");
      emailService.sendSimpleMail(ed);
    }

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
