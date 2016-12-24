package cn.edu.bjtu.weibo.controller;

import javax.servlet.http.*;


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
	
	
	@RequestMapping("index")
    @ResponseBody
    public ModelAndView index(){
        //返回到首页页面
		ModelAndView mav = new ModelAndView("MainPage");
		return mav;
    }
	
	 
	//点击用户时，跳转到用户自己的主页
	@RequestMapping("/resp")
	public void handleRequest(HttpServletRequest req,HttpServletResponse resp) throws Exception{
	        
		resp.sendRedirect("UserMainPage.jsp");
	}
	    
	
}