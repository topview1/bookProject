package web.service;

import java.sql.SQLException;

import web.dao.UserDao;
import web.entity.User;
import web.exception.RegisterException;

public class UserService {
	/**
	 * 注册方法
	 * @throws RegisterException 
	 */
	public void register(User user) throws RegisterException  {
		//依赖dao操作数据库
		UserDao dao = new UserDao();
		try {
			dao.addUser(user);
			
		} catch (SQLException e) {
			//注册失败
			e.printStackTrace();
			throw new RegisterException("注册失败");
		}
	}

	/**
	 * 登录方法
	 * @param userName
	 * @param password
	 * @return
	 */
	public User login(String userName, String password) {
		UserDao dao = new UserDao();
		User user;
		try {
			user = dao.findUserByUsernamePsd(userName,password);
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * 修改用户的信息
	 * @param user
	 * @param password
	 * @param checkedPassword
	 */
	public void modifyUserInfo(User user, String password, String telephone) {
		UserDao dao = new UserDao();
		try {
			dao.modifyUser(user,password,telephone);
		} catch (SQLException e) {
			//修改失败
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
	}
}
