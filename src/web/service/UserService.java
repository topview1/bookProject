package web.service;

import java.sql.SQLException;

import web.dao.UserDao;
import web.entity.User;
import web.exception.RegisterException;

public class UserService {
	/**
	 * ע�᷽��
	 * @throws RegisterException 
	 */
	public void register(User user) throws RegisterException  {
		//����dao�������ݿ�
		UserDao dao = new UserDao();
		try {
			dao.addUser(user);
			
		} catch (SQLException e) {
			//ע��ʧ��
			e.printStackTrace();
			throw new RegisterException("ע��ʧ��");
		}
	}

	/**
	 * ��¼����
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
	 * �޸��û�����Ϣ
	 * @param user
	 * @param password
	 * @param checkedPassword
	 */
	public void modifyUserInfo(User user, String password, String telephone) {
		UserDao dao = new UserDao();
		try {
			dao.modifyUser(user,password,telephone);
		} catch (SQLException e) {
			//�޸�ʧ��
			e.printStackTrace();
			throw new RuntimeException("�޸�ʧ��");
		}
	}
}
