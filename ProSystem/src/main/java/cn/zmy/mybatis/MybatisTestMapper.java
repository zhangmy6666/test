package cn.zmy.mybatis;

import java.util.List;

public interface MybatisTestMapper {
	int batchInsert(List<AdminUser> userList);

	AdminUser queryByKey(String key);

	List<AdminUser> queryByName(String string);

	void insert(AdminUser user);
}
