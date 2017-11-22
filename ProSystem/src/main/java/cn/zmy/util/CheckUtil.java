/**
 * 南京青牛通讯技术有限公司
 * 日期：$$Date: 2016-09-06 17:30:34 +0800 (星期二, 06 九月 2016) $$
 * 作者：$$Author: zhang.hao $$
 * 版本：$$Rev: 129750 $$
 * 版权：All rights reserved.
 */
package cn.zmy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CheckUtil
{

	/**
	 * 校验是否为JSON格式，请求参数为空返回NULL
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject parseObject(String json)
	{
		// 参数为空的时候 默认检查成功
		if (StringUtil.isNotBlank(json))
		{
			try
			{
				return JSON.parseObject(json);
			}
			catch (Exception e)
			{
				
			}
		}
		
		return new JSONObject();
	}
}
