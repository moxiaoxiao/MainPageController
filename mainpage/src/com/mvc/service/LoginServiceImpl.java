package com.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;



@Service
public class LoginServiceImpl implements LoginService{

	/**
	 * 需要用到dao层对象，因此需要添加注解@resouce/@AutoWired
	 */
	@Autowired
	private UserMapper userMapper;
	
/*	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;*/
	@Override
	public mainpageResult login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);

		//取用户信息判断
		if(list == null || list.isEmpty()){
			//用户信息不存在
			return mainpageResult.build(400, "用户名或密码错误");
		}
		//System.out.println(list.get(0));
		//取出用户信息
		User user = list.get(0);

		
		//校验密码md5加密
		//System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
			return mainpageResult.build(400, "密码错误");
		}
		
		//登录成功,写入cookie中 取request，写response
		//生成一个token以后备用
		String token = UUID.randomUUID().toString();
		//CookieUtils.setCookie(request, response, "LOGIN_KEY", user, SESSION_EXPIRE);
		Cookie usernamecookie = new Cookie("username",username);
		Cookie emailcookie = new Cookie("email",user.getEmail());
		//System.out.println(emailcookie);
		//System.out.println(user.getEmail());
		//Cookie passwordcookie = new Cookie("password",DigestUtils.md5DigestAsHex(password.getBytes()));
		//System.out.println(username);
		//System.out.println(usernamecookie.getName());//username
		/*if(usernamecookie.getName().equals("username")){
			System.out.println("ok");
		}*/
		usernamecookie.setMaxAge(12000);
		emailcookie.setMaxAge(12000);
		usernamecookie.setPath("/");
		emailcookie.setPath("/");
		
		
		response.addCookie(usernamecookie);
		response.addCookie(emailcookie);
		return mainpageResult.ok(token);
	}

}
