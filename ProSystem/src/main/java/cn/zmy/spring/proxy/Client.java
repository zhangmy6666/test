package cn.zmy.spring.proxy;

import cn.zmy.spring.other.UserMgr;
import cn.zmy.spring.other.UserMgrImpl;

public class Client {
	public static void main(String[] args) {
//		System.out.println("-----------CGLibProxy-------------");    
//		UserMgr userManagerCGLib = (UserMgr) new CGLibProxy()    
//                .createProxyObject(new UserMgrImpl());        
//        userManagerCGLib.addUser("root");
        
        System.out.println("-----------JDKProxy-------------");    
        JDKProxy jdkPrpxy = new JDKProxy();    
        UserMgr userManagerJDK = (UserMgr) jdkPrpxy    
                .newProxy(new UserMgrImpl());    
        userManagerJDK.addUser("tom"); 
//        userManagerJDK.delUser();
	}

}
