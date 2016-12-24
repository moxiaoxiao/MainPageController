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
        //���ص���ҳҳ��
		ModelAndView mav = new ModelAndView("MainPage");
		return mav;
    }
	
	 
	//����û�ʱ����ת���û��Լ�����ҳ
	@RequestMapping("/resp")
	public void handleRequest(HttpServletRequest req,HttpServletResponse resp) throws Exception{
	        
		resp.sendRedirect("UserMainPage.jsp");
	}
	    
	
}