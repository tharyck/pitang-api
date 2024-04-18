package com.pitang.services;

import com.pitang.models.CarModel;
import com.pitang.models.UserModel;
import com.pitang.repositories.UserRepository;
import com.pitang.utis.CryptoUtil;
import com.pitang.utis.ErrorMessagesConstants;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CryptoUtil cryptoUtil;

    public UserModel create(UserModel userModel) {
        try {
            userModel.setPassword(cryptoUtil.hashPassword(userModel.getPassword()));
            return this.userRepository.save(userModel);
        } catch (DataIntegrityViolationException de) {
            if (de.getMessage().contains(ErrorMessagesConstants.UK_LOGIN.getError())) {
                throw new RuntimeException(ErrorMessagesConstants.UK_LOGIN.getMessage());
            } else if (de.getMessage().contains(ErrorMessagesConstants.UK_EMAIL.getError())) {
                throw new RuntimeException(ErrorMessagesConstants.UK_EMAIL.getMessage());
            } else if (de.getMessage().contains(ErrorMessagesConstants.NULL.getError())) {
                throw new RuntimeException(ErrorMessagesConstants.NULL.getMessage());
            }
        }
        return null;
    }

    public UserModel update(UserModel userModel) {
        if (this.cryptoUtil.isNotHashPassword(userModel.getPassword())) {
            userModel.setPassword(cryptoUtil.hashPassword(userModel.getPassword()));
        }

        return this.userRepository.save(userModel);
    }

    public UserModel show(Long id) {
        return this.userRepository.findById(id).get();
    }

    public List<UserModel> index() {
        Sort sort = (Sort.by(new Sort.Order(Sort.Direction.DESC, "login")));
        List<UserModel> users = this.userRepository.findAll(sort).stream().toList();
        return countUsed(users);
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public UserModel findByLogin(String login) {
        try {
            return this.userRepository.findByLogin(login);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesConstants.LOGIN_NOT_VALID_USER_PASSWORD.getMessage());
        }
    }

    private List<UserModel> countUsed(List<UserModel> users) {
        users.forEach(user -> {
            user.getCars().forEach(car -> user.setUsedCounter(user.getUsedCounter() + car.getUsed()));
            user.getCars().sort(Comparator.comparing(CarModel::getUsed).reversed());
        });
        return users.stream().sorted(Comparator.comparing(UserModel::getUsedCounter).reversed()).toList();
    }

}
