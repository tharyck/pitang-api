package com.pitang.services;

import com.pitang.models.UserModel;
import com.pitang.repositories.UserRepository;
import com.pitang.utis.ErrorsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    public UserModel create(UserModel userModel) {
        try {
            return this.userRepository.save(userModel);
        } catch (Exception e) {
            throw new ErrorsException(e.getMessage(), 1);
        }
    }

    public UserModel update(UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    public UserModel show(Long id) {
        return this.userRepository.findById(id).get();
    }

    public List<UserModel> index() {
        return this.userRepository.findAll().stream().toList();
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
