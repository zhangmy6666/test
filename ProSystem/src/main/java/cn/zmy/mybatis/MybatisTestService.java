package cn.zmy.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.zmy.util.BaseService;
import cn.zmy.util.Path;
import cn.zmy.util.ResponseDto;
import cn.zmy.util.StringUtil;

@Path("/mybatistest")
public class MybatisTestService extends BaseService<String>{
	@Autowired
	MybatisTestMapper mapper;
	
//	 #{…} 解释为JDBC prepared statement 的一个参数标记。而将 ${…} 解释为字符串替换。
//	不能在表名(table name)的位置使用参数标记。
//	用$ {…} (字符串替换)时可能会有SQL注入攻击的风险。
//          字符串替换在处理复杂类型也可能常常发生问题,如日期类型。尽可能地使用 #{…} 这种方式。
	
	/*半自动orm(对象关系映射) 数据类型都没有映射  oxm*/
	
	//动态代理
	/*Mapper是一个接口 它并没有实现类，为什么接口可以直接使用呢? */
	/*1、  Mapper.xml文件中的namespace与mapper接口的类路径相同,接口名称和xml名称也相同。
	2、  Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
	3、  Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
	4、  Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同*/
	/*Mapper接口开发方法只需要程序员编写Mapper接口（相当于Dao接口），
	由Mybatis框架根据接口定义创建接口的动态代理对象*/
	/*如果mapper方法返回单个pojo对象（非集合对象），代理对象内部通过selectOne来查询数据库 
	如果mapper方法返回一个非集合对象，代理对象内部通过selectList来查询数据库*/
	
	@Override
	public ResponseDto<String> process(JSONObject params, 
			HttpServletRequest request, HttpServletResponse response) {
		ResponseDto<String> dto = new ResponseDto<String>();
		List<AdminUser> userList = new ArrayList<AdminUser>();
		AdminUser user1 = new AdminUser(StringUtil.getUUIDString(),"zz","123123","zz");
		AdminUser user2 = new AdminUser(StringUtil.getUUIDString(),"mm","232323","mm");
		userList.add(user1);
		userList.add(user2);
		// 返回操作成功数
		int id = mapper.batchInsert(userList);
		System.out.println(id);
		return dto;
	}

}
