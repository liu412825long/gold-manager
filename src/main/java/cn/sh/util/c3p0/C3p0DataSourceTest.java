package cn.sh.util.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0DataSourceTest {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		ComboPooledDataSource  dataSource=new ComboPooledDataSource ();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ticket?useUnicode=true&characterEncoding=utf-8");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setMaxPoolSize(1);
		Connection connection=dataSource.getConnection();
		String sql="select * from tickets";
		PreparedStatement statement=connection.prepareStatement(sql);
		ResultSet resultSet=statement.executeQuery();
		while(resultSet.next()) {
			System.out.println(resultSet.getString("id"));
			System.out.println(resultSet.getString("period"));
		}
		System.out.println(dataSource.getNumConnections());
		dataSource.close();
		System.out.println(dataSource);
		
		

	}

}
