package com.mvc.service;

import com.mvc.service;

/**
 * 数据校验部分：返回值根据接口说明包装为ImvResult
 * @author xiaohan
 *
 */
public interface RegisterService {
	mainpageResult checkData(String param, int type);
	mainpageResult register(User user);
}
