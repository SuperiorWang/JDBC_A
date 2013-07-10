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
		insert();  //������Ӽ�¼
		update();  //���¼�¼����
		delete();  //ɾ����¼
		query();   //��ѯ��¼����ʾ
	}
	
	/*�������ݼ�¼���������������ݼ�¼��*/
	public static void insert()
	{
		conn = getConnection(); //��ȡ���ӣ������ӵ����ݿ�
		
		try{
			String sql = "INSERT INTO staff(name,age,sex,address,depart,worklen,wage)"
					+" VALUES('Tom1',32,'M','china','Personnel','3','3000')";
			
			st = (Statement)conn.createStatement();//��������ִ�о�̬��sql����Statement����
			
			int count = st.executeUpdate(sql); //����sql��䣬�����ز������ĸ���
			
			System.out.println("��staff���в��� " + count +"������");
			
			conn.close();
		}catch(SQLException e){
			System.out.println("��������ʧ��"+e.getMessage());
		}
	}
	
    /*���·���Ҫ��ļ�¼�������ظ��µļ�¼��Ŀ*/
	public static void update()
	{
		conn = getConnection();
		
		try{
			String sql = "update staff set wage='2200' where name= 'lucy'";//�������ݵ�sql���
			
			st = (Statement)conn.createStatement();
			
			int count = st.executeUpdate(sql);
			
			System.out.println("��staff���в��� " + count + "������");
			
			conn.close();
		}catch(SQLException e){
			System.out.println("��������ʧ��");
		}
	}
	
	/*��ѯ���ݿ⣬�������Ҫ������ݿ�*/
	public static void query()
	{
		conn = getConnection();
		
		try{
			String sql = "SELECT * FROM staff";
			
			st = (Statement)conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			System.out.println("���Ĳ�ѯ���Ϊ�� ");
			
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
			System.out.println("��ѯ����ʧ��");
		}
	}
	
	public static void delete(){
		conn = getConnection();
		
		try{
			String sql = "DELETE FROM staff WHERE name = 'lili'" ;
			
			st = (Statement)conn.createStatement();
			
			int count = st.executeUpdate(sql);
			
			System.out.println("��staff����ɾ��" + count + "����¼") ;
		}catch(SQLException e){
			System.out.println("ɾ������ʧ��");
		}
	}

	/*��ȡ���ݿ����ӵĺ���*/
	public static Connection getConnection(){
		Connection con = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver"); //����Mysql��������
			
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myuser","root","root"); //������������
		}catch (Exception e){
			System.out.println("���ݿ�����ʧ��" + e.getMessage());
		}
		return con;
	}
}

