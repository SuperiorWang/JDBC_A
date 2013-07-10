package chp07;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class JDBC_Text {

	static Connection conn;
	static Statement st;
	
	public static void main(String[] args)
	{
		insert();  //插入添加记录
		update();  //更新记录数据
		delete();  //删除记录
		query();   //查询记录并显示
	}
	
	/*插入数据记录，并输出插入的数据记录数*/
	public static void insert()
	{
		conn = getConnection(); //获取链接，即连接到数据库
		
		try{
			String sql = "INSERT INTO staff(name,age,sex,address,depart,worklen,wage)"
					+" VALUES('Tom1',32,'M','china','Personnel','3','3000')";
			
			st = (Statement)conn.createStatement();//创建用于执行静态的sql语句的Statement对象
			
			int count = st.executeUpdate(sql); //插入sql语句，并返回插入数的个数
			
			System.out.println("向staff表中插入 " + count +"条数据");
			
			conn.close();
		}catch(SQLException e){
			System.out.println("插入数据失败"+e.getMessage());
		}
	}
	
    /*更新符合要求的记录，并返回更新的记录数目*/
	public static void update()
	{
		conn = getConnection();
		
		try{
			String sql = "update staff set wage='2200' where name= 'lucy'";//更新数据的sql语句
			
			st = (Statement)conn.createStatement();
			
			int count = st.executeUpdate(sql);
			
			System.out.println("向staff表中插入 " + count + "条数据");
			
			conn.close();
		}catch(SQLException e){
			System.out.println("插入数据失败");
		}
	}
	
	/*查询数据库，输出符合要求的数据库*/
	public static void query()
	{
		conn = getConnection();
		
		try{
			String sql = "SELECT * FROM staff";
			
			st = (Statement)conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			System.out.println("最后的查询结果为： ");
			
			while (res.next()){
				String name = res.getString("name");
				int age = res.getInt("age");
				String sex = res.getString("sex");
				String address = res.getString("address");
				String depart = res.getString("depart");
				String worklen = res.getString("worklen");
				String wage = res.getString("wage");
				
				System.out.println(name + " "+ age + " " + sex + " " +address
						+ " " + depart + " " + worklen + " " + wage);
			}
			conn.close();
		}catch(SQLException e){
			System.out.println("查询数据失败");
		}
	}
	
	public static void delete(){
		conn = getConnection();
		
		try{
			String sql = "DELETE FROM staff WHERE name = 'lili'" ;
			
			st = (Statement)conn.createStatement();
			
			int count = st.executeUpdate(sql);
			
			System.out.println("从staff表中删除" + count + "条记录") ;
		}catch(SQLException e){
			System.out.println("删除数据失败");
		}
	}

	/*获取数据库连接的函数*/
	public static Connection getConnection(){
		Connection con = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver"); //加载Mysql数据驱动
			
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myuser","root","root"); //创建数据连接
		}catch (Exception e){
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con;
	}
}

