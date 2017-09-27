/**
 * 南京青牛通讯技术有限公司
 * 日期：$$Date: 2015-09-09 15:08:01 +0800 (周三, 09 9月 2015) $$
 * 作者：$$Author: zhangmy $$
 * 版本：$$Rev: 1158 $$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.common.plugin;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import cn.redcdn.jds.common.annotation.Now;
import cn.redcdn.jds.common.annotation.UUID;
import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.exception.ExternalException;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.util.MessageUtil;
import cn.redcdn.jds.common.util.StringUtil;


@Intercepts({@Signature(type = Executor.class, method ="update", args = {MappedStatement.class, Object.class})})  
public class DatePlugin implements Interceptor 
{

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] targets = invocation.getArgs();
		String packages[] = ((MappedStatement)invocation.getArgs()[0]).getId().split("\\.");
		String method = packages[packages.length - 1];
		if (targets != null && targets.length > 1)
		{
			Object obj = targets[1];
			if (obj instanceof HashMap)
			{
				for (Map.Entry<String, Object> entry : ((HashMap<String, Object>) obj).entrySet())
				{
					Object  params = entry.getValue();
					if (params instanceof List)
					{
						List<Object> objList = (List<Object>) params;
						for (Object target : objList)
						{
							setDate(target, method);
						}
					}
				}
			}
			else
			{
				setDate(obj, method);
			}
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target)
	{		
		
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0)
	{
		
	}
	
	@SuppressWarnings("rawtypes")
	private void setDate(Object target, String method)
	{
		if (target == null)
		{
			return;
		}
		Class targetCls = target.getClass();
		// 当有now注解的时候，自动设定最新日期
		for (Field f : targetCls.getDeclaredFields())
		{
			// UUID
			UUID uuid = f.getAnnotation(UUID.class);
			if (uuid != null && method.startsWith("insert")) 
			{
				try
				{
					f.setAccessible(true);
					f.set(target, StringUtil.getUUIDString());
				} catch (Exception e)
				{
					throw new ExternalException();
				}
			}
			// 日期
			Now now = f.getAnnotation(Now.class);
			if (now != null) 
			{
				String type = now.type();
				if (method.startsWith("update") && !"U".equals(type))
				{
					continue;
				}
				try
				{
					f.setAccessible(true);
					f.set(target, new Date());
				} catch (Exception e)
				{
					MessageInfoDto messageInfo = MessageUtil.getMessageInfoSuccess();
					throw new ExternalServiceException(messageInfo);
				}
			}
		}
	}
}
