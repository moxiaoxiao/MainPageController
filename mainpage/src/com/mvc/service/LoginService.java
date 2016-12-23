package com.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.controller;

public interface LoginService {
	mainpageResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
