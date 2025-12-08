package com.sourjelly.memo.user.service;

import com.sourjelly.memo.common.MD5HashingEncoder;
import com.sourjelly.memo.user.domain.User;
import com.sourjelly.memo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

//    @Autowired
    // 클래스내에 생성자가 객체 주입을 위한 생성자가 유일한 경우 @Autowired 생략 가능
    // 만약 생성자가 여러가지이고 객체를 주입해야하는 생성자일 경우 @Autowired 명시 (DI 객체주입)
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email){

        String encodedPassword = MD5HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);

        return count== 1;
    }

    public User getUser(String loginId, String password){

        String encodedPassword= MD5HashingEncoder.encode(password);

        User user = userRepository.selectUser(loginId, encodedPassword);

        return user;
    }

}
