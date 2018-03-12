//package cn.zmy.service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.alibaba.fastjson.JSONObject;
//
//import cn.redcdn.hpu.common.annotation.Path;
//import cn.redcdn.hpu.common.dao.CacheDao;
//import cn.redcdn.hpu.common.dto.ResponseDto;
//import cn.redcdn.hpu.common.service.BaseService;
//import cn.redcdn.hpu.common.util.StringUtil;
//
//@Path("/test")
//public class TestService extends BaseService<String> {
//	
//	@Autowired
//	CacheDao cache;
//	
//	@Override
//	public ResponseDto<String> process(JSONObject params, HttpServletRequest request, 
//			HttpServletResponse response) {
//		String res = "";
//		String key = params.getString("key");
//		String value = params.getString("value");
//		if(params.getString("oper").equals("get")){
//			res = cache.get(params.getString("key"));
//		}
//		if(params.getString("oper").equals("del")){
//			cache.delete(params.getString("key"));
//		}
//		if(params.getString("oper").equals("set")) {
//			while (true) {
//				if (StringUtil.isNotBlank(cache.get(key))) {
//					res = "没我的事了。。。";
//					break;
//				}
//                if (checkTaskStatus(key,value)) {
//                	res = "我开始干活了。。。";
//                	try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						break;
//					}
//                    break;
//                }
//            }
//			
//		}
//		ResponseDto<String> rspDto = new ResponseDto<String>();
//		rspDto.setData(res);
//		return rspDto;
//	}
//	
//	public boolean checkTaskStatus(String key, String value){
//		boolean flg = false;
//		if(cache.setnx(key, value)==1) {
//			cache.setExpiredTime(key, 60);
//			flg= true; 
//		}else{
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		return flg;
//	}
//
//}
