package edu.miu.shoppingcartsystem.service.security;

import edu.miu.shoppingcartsystem.model.User;
import edu.miu.shoppingcartsystem.repository.UserRepository;
import edu.miu.shoppingcartsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class LoggedInUserUtil {

    @Autowired
    private UserService userService;

   public Optional<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Optional<User> user = Optional.ofNullable(userService.getCurrentUser(currentUserName));
            return user;
        }
        return Optional.empty();
    }
}
