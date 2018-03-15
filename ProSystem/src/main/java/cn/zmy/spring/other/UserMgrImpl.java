package cn.zmy.spring.other;

public class UserMgrImpl implements UserMgr {

	@Override
	public void addUser(String id) {
		System.out.println("添加用户.....");
	}

	@Override
	public void delUser() {
		System.out.println("删除用户.....");
	}

}
