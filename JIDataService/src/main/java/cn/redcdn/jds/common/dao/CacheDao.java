/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package cn.redcdn.jds.common.dao;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import cn.redcdn.jds.common.dto.MessageInfoDto;
import cn.redcdn.jds.common.exception.ExternalServiceException;
import cn.redcdn.jds.common.util.MessageUtil;
import cn.redcdn.jds.common.util.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis操作工具类
 * 
 */
@Component
public class CacheDao {

	private static JedisPool pool = null;
	private static Logger logger = LoggerFactory.getLogger(CacheDao.class);

	// 缓存key的前缀标记；jds：极会议
	private static String PRO_PRE = "jds_";

	@Value("${redis.url}")
	private String url;

	@Value("${redis.port}")
	private int port;

	@Value("${redis.password}")
	private String password;

	@Value("${redis.maxTotal}")
	private int maxTotal;

	@Value("${redis.minIdle}")
	private int minIdle;

	@Value("${redis.maxIdle}")
	private int maxIdle;

	@Value("${redis.maxWaitMillis}")
	private int maxWaitMillis;

	@Value("${redis.readTimeout}")
	private int readTimeout;

	@Value("${redis.testWhileIdle}")
	private String testWhileIdle;

	@Value("${redis.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${redis.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@PostConstruct
	public void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setMinIdle(minIdle);
		// Idle时进行连接扫描
		config.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
		// 表示idle object evitor两次扫描之间要sleep的毫秒数
		config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

		if (StringUtil.isNotBlank(password)) {
			pool = new JedisPool(config, url, port, readTimeout, password);
		} else {
			pool = new JedisPool(config, url, port, readTimeout);
		}
	}

	/**
	 * 设置String数据类型
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value, int time) {
		if (value == null) {
			value = "";
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.setex(PRO_PRE + key, time, value);
		} catch (Exception e) {
			logger.error("CacheDao.settime", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置String数据类型
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value) {
		if (value == null) {
			value = "";
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(PRO_PRE + key, value);
		} catch (Exception e) {
			logger.error("CacheDao.set", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(PRO_PRE + key);
		} catch (Exception e) {
			logger.error("CacheDao.get", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public boolean isExist(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.exists(PRO_PRE + key);
		} catch (Exception e) {
			logger.error("CacheDao.isExist", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void delete(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(PRO_PRE + key);
		} catch (Exception e) {
			logger.error("CacheDao.delete", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public long ttl(String key) {
		Jedis jedis = null;
		long time = 0;
		try {
			jedis = pool.getResource();
			time = jedis.ttl(PRO_PRE + key);
			return time;
		} catch (Exception e) {
			logger.error("CacheDao.delete", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置Object数据类型
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, Object value, int time) {
		if (value == null) {
			value = "";
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.setex(PRO_PRE + key, time, JSON.toJSONString(value));
		} catch (Exception e) {
			logger.error("CacheDao.settime", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 设置Object数据类型
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, Object value) {
		if (value == null) {
			value = "";
		}
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(PRO_PRE + key, JSON.toJSONString(value));
		} catch (Exception e) {
			logger.error("CacheDao.set", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String value = jedis.get(PRO_PRE + key);
			return JSON.parseObject(value, clazz);
		} catch (JSONException e) {
			logger.warn("json转换错误");
			return null;
		} catch (Exception e) {
			logger.error("CacheDao.get", e);
			MessageInfoDto messageInfo = MessageUtil.getMessageInfoByKey("redis.connect.timeout");
			throw new ExternalServiceException(messageInfo);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

}