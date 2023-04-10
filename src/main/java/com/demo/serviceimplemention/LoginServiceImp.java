package com.demo.serviceimplemention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Login;
import com.demo.repository.LoginRepository;
import com.demo.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	LoginRepository loginRepository;

	@Override
	public Login findByUserNameAndPassword(String userName, String password) {
			return loginRepository.findByUserNameAndPassword(userName,  password);
	}

}