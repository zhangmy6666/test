package cn.zmy.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zmy.ProSystemApplication;
import cn.zmy.mybatis.AdminUser;
import cn.zmy.mybatis.MybatisTestMapper;
import cn.zmy.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProSystemApplication.class)
public class TestService {
	
	@Autowired
	MybatisTestMapper mybatisTestMapper;
	
	@Test
	@Transactional
	public void test() throws Exception {
		AdminUser user1 = new AdminUser(StringUtil.getUUIDString(),"zz","123123","zz");
		AdminUser user2 = new AdminUser(StringUtil.getUUIDString()+"123123","mm","232323","mm");
		mybatisTestMapper.insert(user1);
		mybatisTestMapper.insert(user2);
	}

}
