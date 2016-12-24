package cn.edu.bjtu.weibo.controller;

import javax.servlet.http.*;
import cn.edu.bjtu.weibo.service.LoginService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
	@Autowired
	public MainPageController(){
		
	}
	private LoginService loginService;
	private String username;
	private String password;
	//�ж��û���û�е�¼,���û�е�¼����ת����¼ҳ�棬����Ѿ���¼����ת����ҳ
	@RequestMapping("index")
    @ResponseBody
    public ModelAndView index(){
		boolean isLogin = loginService.isLogin(username, password);
		
		if (isLogin){
			ModelAndView mav = new ModelAndView("MainPage");
			return mav;
		}else{
			ModelAndView mav = new ModelAndView("LoginPage");
			return mav;
		}
		
		
    }
	
	 
	//����û�ʱ����ת���û��Լ�����ҳ
	@RequestMapping("/resp")
	public void handleRequest(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		resp.sendRedirect("UserMainPage.jsp");
	}
	    
	
}