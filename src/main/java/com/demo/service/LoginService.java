package com.demo.service;

import com.demo.model.Login;

public interface LoginService {

	Login findByUserNameAndPassword(String userName, String password);

}
