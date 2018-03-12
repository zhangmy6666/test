package cn.zmy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LockService {
	
	@Autowired
	StringRedisTemplate template;
	
	public void set(String key,String value) {
		template.opsForValue().set(key, value);
	}
	
	public String get(String key) {
		return template.opsForValue().get(key);
	}
	
	public void delete(String key) {
		template.delete(key);
	}		
	
	public static void main(String[] args) {
		new LockService().set("HPU:TASK_STATUS", "1");
		System.out.println(new LockService().get("HPU:TASK_STATUS"));
	}

}
