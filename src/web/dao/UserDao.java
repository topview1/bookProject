package web.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import web.entity.User;
import web.utils.DataSourceUtils;

public class UserDao {

	//����û�
	public void addUser(User user) throws SQLException {
		//׼��sql
		String sql = "insert into user(username,password,gender,email,telephone,introduce,activeCode) values(?,?,?,?,?,?,?)";
		QueryRunner runner= new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),
				user.getTelephone(),user.getIntroduce().trim(),user.getActiveCode());
		
		if(row == 0) {
			throw new RuntimeException();
		}
	}

	//���ҵ����û�
	public User findUserByUsernamePsd(String userName, String password) throws SQLException {
		//1.׼��sql���
		String sql = "select * from user where username=? and password=?";
		//2 �������� 
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		User user = runner.query(sql, new BeanHandler<User>(User.class),userName,password);
		return user;
	}

	//�޸�������Ϣ
	public void modifyUser(User user, String password, String telephone) throws SQLException {
		//1.׼��sql���
		String sql = "update user  set password = ?, telephone=? where id = ?";
		//2.��������
		QueryRunner runner  = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, password,telephone,user.getId());	
	}

}
