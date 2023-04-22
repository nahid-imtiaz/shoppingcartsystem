package edu.miu.shoppingcartsystem.service;

import edu.miu.shoppingcartsystem.model.User;
import edu.miu.shoppingcartsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(String userName){
        return userRepository.findByUsername(userName).orElse(null);
    }



}
