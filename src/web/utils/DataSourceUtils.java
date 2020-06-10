package web.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	//得到数据源对象
	private static DataSource dataSource = new ComboPooledDataSource();
	//提供线程内的局部变量，这种变量在多线程环境下访问时能够保证各个线程里变量的独立性。
	//保证是同一个连接对象
		private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//提供静态方法得到数据源对象
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个唯一的连接
	 * 保证唯一性是tl对象底层使用的是map集合<线程的编号，Connection>
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
	 * 开启事务
	 * throws SQLException
	 */
	public static void startTransation() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			//setAutoCommit总的来说就是保持数据的完整性，一个系统的更新操作可能要涉及多张表，需多个SQL语句进行操作
//循环里连续的进行插入操作，如果你在开始时设置了：conn.setAutoCommit(false);
//最后才进行conn.commit(),这样你即使插入的时候报错，修改的内容也不会提交到数据库，
//而如果你没有手动的进行setAutoCommit(false);
//出错时就会造成，前几条插入，后几条没有
//会形成脏数据~~
	con.setAutoCommit(false);
		}
	}
	
	/**
	 * 正常情况下
	 * 从ThreadLocal中释放并且关闭Connection，并结束事务
	 */
	
	public static void releaseAndCloseConnection() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			//提交事务
			con.commit();
			tl.remove();
			//关闭连接
			con.close();
		}
	}
	
	/**
	 * 错误情况下
	 * 事务回滚
	 */
	public static void rollback() throws SQLException{
		Connection con = getConnection();
		if(con != null) {
			 //数据回滚，是数据库中事物的执行，有一条插入失败，所有的都不插入，数据库回滚到操作之前的样子
			con.rollback();
		}
	}
	
}
