package cn.zmy.mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.zmy.util.StringUtil;
import junit.framework.TestCase;

public class MapperTestService  extends TestCase{

	/*如果mapper方法返回单个pojo对象（非集合对象），代理对象内部通过selectOne来查询数据库 
	如果mapper方法返回一个非集合对象，代理对象内部通过selectList来查询数据库*/
	private SqlSessionFactory sqlSessionFactory;
    
	@Before
	protected void setUp() throws Exception {
       //mybatis配置文件
       String resource = "SqlMapConfig.xml";
       InputStream inputStream = Resources.getResourceAsStream(resource);
       //使用SqlSessionFactoryBuilder创建sessionFactory
       sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
	
	@Test
    public void testFindUserById() throws Exception {
       //获取session
       SqlSession session = sqlSessionFactory.openSession();
       //创建usermapper对象，mybatis自动生成mapper代理对象
       MybatisTestMapper userMapper =session.getMapper(MybatisTestMapper.class);
       //调用代理对象方法
       AdminUser user = userMapper.queryByKey("362a38eb7b2c48138aa6cdff876768ef");
       System.out.println(user);
       //关闭session
       session.close();
    }
    
//	@Test
//    public void testFindUserByUsername() throws Exception {
//       SqlSession sqlSession = sqlSessionFactory.openSession();
//       MybatisTestMapper userMapper =sqlSession.getMapper(MybatisTestMapper.class);
//       List<AdminUser> list =userMapper.queryByName("zz");
//       System.out.println(list.size());
//       sqlSession.close();
//    }
//    
//	@Test
//    public void testInsertUser() throws Exception {
//       //获取session
//       SqlSession session = sqlSessionFactory.openSession();
//       //获取mapper接口的代理对象
//       MybatisTestMapper userMapper =session.getMapper(MybatisTestMapper.class);
//       //要添加的数据
//       AdminUser user = new AdminUser(StringUtil.getUUIDString(), "2017111601",
//    		   "zzhsq", "12341234");
//       //通过mapper接口添加用户
//       userMapper.insert(user);
//       //提交
//       session.commit();
//       //关闭session
//       session.close();
//    }
}
