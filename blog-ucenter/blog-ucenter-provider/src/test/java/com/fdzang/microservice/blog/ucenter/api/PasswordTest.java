package com.fdzang.microservice.blog.ucenter.api;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tanghu
 * @Date: 2019/5/31 0:28
 */
public class PasswordTest {

    @Test
    public void getPassword(){
        PasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("123456"));
    }

}
