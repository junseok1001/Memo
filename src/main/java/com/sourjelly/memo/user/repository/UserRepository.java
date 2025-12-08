package com.sourjelly.memo.user.repository;

import com.sourjelly.memo.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserRepository {

    public int insertUser(
            @Param("loginId") String loginId
            ,@Param("password") String password
            ,@Param("name") String name
            ,@Param("email") String email);

    public User selectUser(
            @Param("loginId") String loginId
            , @Param("password") String password
    );

}
