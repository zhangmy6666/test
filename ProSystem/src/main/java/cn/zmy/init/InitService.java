package cn.zmy.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zmy.couchbase.CouchbaseService;

@Service
public class InitService {
	private static Logger logger = LoggerFactory.getLogger(InitService.class);
	
	@Autowired
	CouchbaseService couchbase;

	@PostConstruct
	public void init() {
		logger.info("init...");
//		couchbase.bucketAdd("test", "123456");
		System.out.println(couchbase.bucketGetString("test"));

	}

}
