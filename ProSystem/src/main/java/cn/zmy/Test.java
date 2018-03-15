package cn.zmy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Test {
	class Person {
		String name;

		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

	public static void main(String[] args) {
		System.out.println(Objects.equals( null,"haha"));
//		List<Person> list = new ArrayList<Person> ();
//		list.add(new Test().new Person("James"));
//		list.add(new Test().new Person("kobe"));
////		list.add(new Test().new Person("tracy"));
//		
//		List<String> listB = new ArrayList<String>();
//		listB.add("kobe");
//		listB.add("james");
//		listB.add("tracy");
//		for(int i =0;i<listB.size();i++) {
//			String s = listB.get(i); 
//			for(Person p:list) {
//				String name = p.getName();
//				if (s.equalsIgnoreCase(name)) {
//					listB.remove(s);
//				}
//	        }
//		}
//		System.out.println("James".equalsIgnoreCase("james"));
//		System.out.println(listB.toString());
//		String result = "{\"code\":0,\"data\":{\"homeOfficialAccountList\":["
//				+ "{\"id\":\"8d0d0784fdc14cb686ae9ede25369bf7\"}]},\"msg\":\"\"}";
//		JSONObject resultObj = parseObject(result);
//		int key = resultObj.getIntValue("code");
//		System.out.println(key);
//		String keyStr = resultObj.getString("code");
//		System.out.println(StringUtils.isNotBlank(keyStr));
		
//    	JSONObject homePage = resultObj.getJSONObject("data");
//    	JSONArray homeOfficialAccountList = homePage.getJSONArray("homeOfficialAccountList");
//    	System.out.println(homeOfficialAccountList.size());
//		String a = "bbc";
//		System.out.println(a.length());//字符串的length方法
//		List<String> list = new ArrayList<String>();
//		list.add(a);
//		System.out.println(list.size());//列表由size方法，没有length方法或属性
////		String[] arr = {"a","b"};
////		System.out.println(arr.length);
//		String aa = "a,b,b";
//		String[] bb = aa.split(",");
//		System.out.println(bb.length);//数组的length属性
//		
//		List<String> ll = Arrays.asList(bb);
//		System.out.println(ll.get(0));
//		String[] cc = ll.toArray(new String[ll.size()]);
//		System.out.println(cc[0]);
//		
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		map.put(1, "1");
//		System.out.println(map.get(1));
		
//		String a = "";
//        String[] ar = a.split(",");
//        if (ar != null)
//        {
//        	System.out.println("ar is not null");
//        }
//        else
//        {
//        	System.out.println("ar is null");
//        }
		System.out.println((int)'0');
		System.out.println((int)'9');
        
	}
	
	public static JSONObject parseObject(String json)
	{
		// 参数为空的时候 默认检查成功
		if (StringUtils.isNotBlank(json))
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
