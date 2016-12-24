package cn.edu.bjtu.weibo.controller;

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
	
	//点击搜索微博跳转
	@RequestMapping("/{param}/")
    @ResponseBody
    public String checkDate(@PathVariable String param){
        if(StringUtils.isBlank(param)){
            return mainpageResult.build(500,"参数为空");
        }else{
            return mainpageResult.ok(param);
        }
    }
	
	//点击热门话题跳转
	 @RequestMapping("movie")
	    public String showHot(){
	        return "Hot";
	    }
	 
	 //点击登录跳转
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
	    
	    //点击注册跳转
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
	    
	    //点击微博跳转
	    @RequestMapping("detail")
	    public String showDetail(){
	        return "detail";
	    }
}