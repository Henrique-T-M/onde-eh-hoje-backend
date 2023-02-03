package com.maybenot.oeh.services;

import com.maybenot.oeh.entities.User;
import com.maybenot.oeh.exception.ResourceNotFoundException;
import com.maybenot.oeh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private static final String USER = "User";

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER, id));
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void registerUser(User user){
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(user);
    }

    public void updateUser(String id, String name, String email) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER, id));
        boolean isChanged = false;

        if(name != null && name.length()>0 && !Objects.equals(user.getName(), name)){
            user.setName(name);
            isChanged = true;
        }

        if(email != null && email.length()>0 && !Objects.equals(user.getEmail(), email)){
            Optional<User> userByEmail = userRepository.findUserByEmail(email);
            if(userByEmail.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
            isChanged = true;
        }

        if (isChanged) {
            userRepository.save(user);
        }
    }

    public void deleteUser(String id){
        User userById = userRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(USER, id));
        userRepository.delete(userById);
    }
}
