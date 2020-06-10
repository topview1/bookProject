package web.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	//�õ�����Դ����
	private static DataSource dataSource = new ComboPooledDataSource();
	//�ṩ�߳��ڵľֲ����������ֱ����ڶ��̻߳����·���ʱ�ܹ���֤�����߳�������Ķ����ԡ�
	//��֤��ͬһ�����Ӷ���
		private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//�ṩ��̬�����õ�����Դ����
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * ��DBUtils��Ҫ�ֶ���������ʱ�����ø÷������һ��Ψһ������
	 * ��֤Ψһ����tl����ײ�ʹ�õ���map����<�̵߳ı�ţ�Connection>
	 */
	
	public static Connection  getConnection() throws SQLException{
		Connection con = tl.get();
		if(con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}
	
	/**
	 * ��������
	 * throws SQLException
	 */
	public static void startTransation() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			//setAutoCommit�ܵ���˵���Ǳ������ݵ������ԣ�һ��ϵͳ�ĸ��²�������Ҫ�漰���ű�����SQL�����в���
//ѭ���������Ľ��в��������������ڿ�ʼʱ�����ˣ�conn.setAutoCommit(false);
//���Ž���conn.commit(),�����㼴ʹ�����ʱ�򱨴��޸ĵ�����Ҳ�����ύ�����ݿ⣬
//�������û���ֶ��Ľ���setAutoCommit(false);
//����ʱ�ͻ���ɣ�ǰ�������룬����û��
//���γ�������~~
	con.setAutoCommit(false);
		}
	}
	
	/**
	 * ���������
	 * ��ThreadLocal���ͷŲ��ҹر�Connection������������
	 */
	
	public static void releaseAndCloseConnection() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			//�ύ����
			con.commit();
			tl.remove();
			//�ر�����
			con.close();
		}
	}
	
	/**
	 * ���������
	 * ����ع�
	 */
	public static void rollback() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			 //���ݻع��������ݿ��������ִ�У���һ������ʧ�ܣ����еĶ������룬���ݿ�ع�������֮ǰ������
			con.rollback();
		}
	}
	
}
