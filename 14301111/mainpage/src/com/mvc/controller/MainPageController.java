package com.mvc.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainPageController {
	@Autowired
	public MainPageController(){
		
	}
	
	private LoginService loginService;
	
	//General Search
	@RequestMapping("/{param}/")
    @ResponseBody
    public String checkDate(@PathVariable String param){
        if(StringUtils.isBlank(param)){
            return mainpageResult.build(500,"参数为空");
        }else{
            return mainpageResult.ok(param);
        }
    }
	
	//Hot topic
	 @RequestMapping("movie")
	    public String showHot(){
	        return "Hot";
	    }
	 
	 //LoginService
	    @RequestMapping("value=/login",method=RequestMeth.POST)
	    public mainpageResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
	        try {
	            mainpageResult result = loginService.login(username, password, request, response);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return mainpageResult.build(500, ExceptionUtil.getStackTrace(e));
	        }
	    }
	    
	    //register service
	    @RequestMapping(value="/register", method=RequestMethod.POST)
	    @ResponseBody
	    public mainpageResult register(User user){
	        try {
	            mainpageResult result = registerService.register(user);
	            return result;
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return mainpageResult.build(500, ExceptionUtil.getStackTrace(e));
	        }
	    }
	    
	    //weiboDetail service
	    @RequestMapping("detail")
	    public String showDetail(){
	        return "detail";
	    }
}