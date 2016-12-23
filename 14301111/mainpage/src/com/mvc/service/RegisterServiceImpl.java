package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * 实现RegisterService里面的方法：注册服务---->controller：发布服务
 * service层利用注解开发，需要注入@Service
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	
	/**
	 * 检查数据，需要注入Mapper(dao层)
	 */
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public mainpageResult checkData(String param, int type) {
		/**	
		 * 根据数据类型来检查数据
		 */
		UserExample example = new UserExample();
		/* criteria */
		Criteria criteria = example.createCriteria();
		//1,2分别代表username,email(说明：user表中暂时没加入phone字段)
		if(1 == type){
			criteria.andUsernameEqualTo(param);
		}else if( 2 == type){
			criteria.andEmailEqualTo(param);
		}
		//根据查询条件执行查询
		//用selectByExample方法查询(按照条件查询)，需要的参数example，需要用UserExample创建一个example
		List<User> list = userMapper.selectByExample(example);
		
		//判断查询结构是否为空(或者没有结果)
		if(list == null || list.isEmpty()){
			return mainpageResult.ok(true);
		}
		return mainpageResult.ok(false);
	}

	/**
	 * 用户注册
	 */
	@Override
	public mainpageResult register(User user) {
		//校验数据 参数：param：username不能为空重复  (password)  email
		/**
		 * 思路：用户名密码不能为空，判断User是否有值 ， 重复,利用checkData()
		 */
		if(StringUtils.isBlank(user.getUsername())
				||StringUtils.isBlank(user.getPassword())){
			return mainpageResult.build(400, "用户不存在");
		}
		
		//检测用户名 ,checkData:默认返回的是false
		mainpageResult result = checkData(user.getUsername(), 1);
		if(!(boolean)result.getData()){
			return mainpageResult.build(400, "用户名重复");
		}
		
		/**
		 * email
		 * 逻辑：先判断email是不是空，如果不是空在进行校验重复
		 */
		
		
		if(user.getEmail() != null ){
			result = checkData(user.getEmail(), 2);
			if(!(boolean)result.getData()){
					return mainpageResult.build(400, "邮箱已被注册");
			}
		}

		
		//执行插入数据操作
		String token = UUID.randomUUID().toString();
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setUuid(token);
		//利用dao层插入
		//对密码进行md5加密  Spring 自带DigestUtils
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		
		return mainpageResult.ok();
	}

}
