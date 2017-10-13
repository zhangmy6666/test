package cn.zmy.couchbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.CouchbaseBucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

@Component
public class CouchbaseService {
	public static String PRE_PRO = "pro";
	
	private CouchbaseTemplate template;

	@Autowired
	public CouchbaseService(CouchbaseTemplate template) {
		this.template = template;
	}
	
	public void add(String key, String value) {
		JsonObject content = JsonObject.create().put(key, value);
		template.getCouchbaseBucket().insert(JsonDocument.create(PRE_PRO, content));
	}

	public String getString(String key) {
		return template.getCouchbaseBucket().get(PRE_PRO).content().getString(key);
	}
	
	private CouchbaseBucket bucket;
	
	@Autowired
	public CouchbaseService(CouchbaseBucket bucket) {
		this.bucket = bucket;
	}
	
	public void bucketAdd(String key, String value) {
		JsonObject content = JsonObject.create().put(key, value);
		bucket.insert(JsonDocument.create(PRE_PRO, content));
	}
	
	public String bucketGetString(String key) {
		return bucket.get(PRE_PRO).content().getString(key);
	}
	
}
