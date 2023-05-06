package com.example.demo.user;

import com.example.demo.dream.Dream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Login> getUsers(){
       return userRepository.findAll();
    }

    public void addNewUser(Login user) {
        Optional<Login> userOptional = userRepository.findLoginByUsername(user.getUsername());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public void deleteStudent(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + userId + " does not exist!");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, Dream dream) {
        Login user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exist "
                ));

        if(dream != null)
        {
            user.getDreams().add(dream);
        }
    }

   public List<Dream> getDreams(Long userId) {
        Login user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exist "
                ));

        return user.getDreams();
    }

    public List<Dream> generateChartData(String tag,Long userId ) throws IOException {
        List<Dream> dreams = getDreams(userId);
        List<Dream> dreamsTag = new ArrayList<>();
        for(Dream dream : dreams) {
            if(dream.getTag().equals(tag)){
                dreamsTag.add(dream);
            }
        }
        return dreamsTag;
    }

  /*  public void addNewDream(Dream dream,Login user) {
        Optional<Login> userOptional = userRepository.findLoginByUsername(user.getUsername());
        if(userOptional.isPresent()){
            user.getDreams().add(dream);
            userRepository.save(user);
        }
    }*/
}
