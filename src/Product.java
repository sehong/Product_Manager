import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


class ContentPane extends JFrame{
	private String[] Tdata = {};
	private String[] Vdata = {};
	boolean enable1 = false;
	boolean enable2 = false;
	boolean enable3 = false;
	//���̺� �÷��� 
	private String colName1[] = { "������ȣ", "������", "�ܰ�", "��ü��ȣ" };            //state 1        
	private String colName2[] = { "�Ĵ�ID", "�Ĵ��", "�ּ�", "��ȭ��ȣ","�����" };               //state 2 
	private String colName3[] = {  "�Ĵ�ID", "������ȣ", "������", "���" };     //pstate 1
	private String colName4[] = { "���ֹ�ȣ", "�Ĵ�ID", "������ȣ", "���ּ���","��������" };   //pstate2_select

	//���̺� �÷��� ���̺� ����
	public DefaultTableModel model1 = new DefaultTableModel(colName1, 0);
	public DefaultTableModel model2 = new DefaultTableModel(colName2, 0);        
	public DefaultTableModel model3 = new DefaultTableModel(colName3, 0);
	public DefaultTableModel model4 = new DefaultTableModel(colName4, 0); 


	JTable table1 = new JTable(model1);           //table
	JTable table2 = new JTable(model2);           //table
	JTable table3 = new JTable(model3);           //table
	JTable table4 = new JTable(model4);           //table

	//���̺� ��ũ�� ���
	private JScrollPane jsp1 = new JScrollPane(table1,          //scrollPane ������������� ����     �ʿ��� ���� ������ ���� ��Ʈ �� �ϳ�
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp2 = new JScrollPane(table2,          //scrollPane ������������� ����     �ʿ��� ���� ������ ���� ��Ʈ �� �ϳ�
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp3 = new JScrollPane(table3,          //scrollPane ������������� ����     �ʿ��� ���� ������ ���� ��Ʈ �� �ϳ�
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp4 = new JScrollPane(table4,          //scrollPane ������������� ����     �ʿ��� ���� ������ ���� ��Ʈ �� �ϳ�
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



	public ContentPane() {
		//��ü ���� ȭ��
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("������ ���� ���� ���α׷�");
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �����츦 ������ ���α׷� ����
		MainFrame.setLayout(null);

		//MainFrame.setLocationRelativeTo(null);
		//�޴���
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("�޴�");
		JMenuItem m_item = new JMenuItem("�����");
		mb.add(menu);
		menu.add(m_item);

		MainFrame.setJMenuBar(mb);//���� ȭ�鿡 �޴��� ����
		// �˻� â
		JTextField search = new JTextField();
		search.setBounds(580,120,250,25);

		//�ؽ�Ʈ ���̺�
		JLabel P_name = new JLabel("������ �ý���");
		P_name.setFont(new Font("�������", Font.BOLD, 30));
		P_name.setForeground(Color.black);
		P_name.setBounds(10, 10, 300, 100);

		//�г�
		JPanel Top_p = new JPanel();
		Top_p.setLayout(null);
		Top_p.setBounds(0, 0, 1650, 150);

		JPanel Btn_p = new JPanel();
		Btn_p.setLayout(null);
		Btn_p.setBounds(840, 180, 150, 510);
		//Btn_p.setBackground(Color.LIGHT_GRAY);

		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(3,1));
		p3.setBounds(30, 180, 800, 510);
		//p3.setBackground(Color.BLUE);
		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout(1,1));
		p5.setBounds(1000, 180, 370, 510);
		JPanel p6 = new JPanel();
		p6.setLayout(null);
		p6.setBounds(950, 180, 430, 510);
		//p6.setBackground(Color.black);

		//���� ��ư
		/*ButtonGroup g = new ButtonGroup();
		JRadioButton r1 = new JRadioButton("��ǰ��");
		JRadioButton r2 = new JRadioButton("��ü��");
		JRadioButton r3 = new JRadioButton("�Ĵ��");
		g.add(r1);
		g.add(r2);
		g.add(r3);

		r1.setBounds(600, 100, 80, 20);
		r1.setFont(new Font("�������", Font.BOLD, 15));
		r1.setForeground(Color.black);
		r1.setBorderPainted(false);
		r1.setFocusPainted(false);
		r1.setContentAreaFilled(false);

		r2.setBounds(680, 100, 80, 20);
		r2.setFont(new Font("�������", Font.BOLD, 15));
		r2.setForeground(Color.black);
		r2.setBorderPainted(false);
		r2.setFocusPainted(false);
		r2.setContentAreaFilled(false);

		r3.setBounds(760, 100, 80, 20);
		r3.setFont(new Font("�������", Font.BOLD, 15));
		r3.setForeground(Color.black);
		r3.setBorderPainted(false);
		r3.setFocusPainted(false);
		r3.setContentAreaFilled(false);
		 */
		//��ư

		JButton food_stock_btn = new JButton("������ ���");
		food_stock_btn.setBounds(0,100,150, 50);
		food_stock_btn.setFont(new Font("�������", Font.BOLD, 20));
		food_stock_btn.setBackground(Color.white);
		//food_btn.setBorderPainted(false);

		JButton food_btn = new JButton("�����");
		food_btn.setBounds(150,100,150, 50);
		food_btn.setFont(new Font("�������", Font.BOLD, 20));
		food_btn.setBackground(Color.white);
		//food_btn.setBorderPainted(false);

		JButton restaurant_btn = new JButton("�л��Ĵ�");
		restaurant_btn.setBounds(300,100,150, 50);
		restaurant_btn.setFont(new Font("�������", Font.BOLD, 20));
		restaurant_btn.setBackground(Color.white);
		//restaurant_btn.setBorderPainted(false);

		JButton Search_btn = new JButton("�˻�");

		Search_btn.setFont(new Font("�������", Font.BOLD, 20));
		Search_btn.setBounds(850,120,100,25);

		JButton refreshify_btn = new JButton("���ΰ�ħ");
		refreshify_btn.setFont(new Font("�������", Font.BOLD, 20));
		refreshify_btn.setBounds(970,120,180,25);

		JButton order_btn = new JButton("������ȸ");   //��������->���ֹ�ư�κ���
		order_btn.setFont(new Font("�������", Font.BOLD, 20));
		order_btn.setBounds(1170,120,200,25);

		JButton insert_btn = new JButton("�߰�");
		insert_btn.setFont(new Font("�������", Font.BOLD, 20));
		insert_btn.setBounds(0,0,150,100);

		JButton correction_btn = new JButton("����");
		correction_btn.setFont(new Font("�������", Font.BOLD, 20));
		correction_btn.setBounds(0,200,150,100);

		JButton delete_btn = new JButton("����");
		delete_btn .setFont(new Font("�������", Font.BOLD, 20));
		delete_btn .setBounds(0,400,150,100);

		//������+�гο� ����
		Top_p.add(P_name);
		Top_p.add(food_stock_btn);
		Top_p.add(food_btn);
		Top_p.add(restaurant_btn);
		//Top_p.add(r1);
		//Top_p.add(r2);
		//Top_p.add(r3);
		Top_p.add(search);
		Top_p.add(Search_btn);
		Top_p.add(refreshify_btn);
		Top_p.add(order_btn);

		Btn_p.add(insert_btn);
		Btn_p.add(correction_btn);
		Btn_p.add(delete_btn);



		p3.add(jsp1);
		p3.add(jsp2);
		p3.add(jsp3);
		p5.add(jsp4);

		MainFrame.add(p5);
		MainFrame.add(Top_p);
		MainFrame.add(p3);
		MainFrame.add(Btn_p);



		/*table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 */

		//MainFrame.setResizable(false);//ũ�����
		MainFrame.setSize(1420,780);
		MainFrame.setVisible(true);


		//����� ��ư
		m_item.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Money_Frame m = new Money_Frame();
			}
		});
		//�˻� ��ư
		Search_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				model1.setNumRows(0);
				model2.setNumRows(0);
				model3.setNumRows(0);
				model4.setNumRows(0);

				DB_Conn_Query dbc = new DB_Conn_Query();
				dbc.state_sql(search.getText(),model1,model2,model3,model4);
			}
		});
		//������ ��� ��ư
		food_stock_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				enable1 = true;
				enable2 = false;
				enable3 = false;

				food_stock_btn.setEnabled(false);
				restaurant_btn.setEnabled(true);
				food_btn.setEnabled(true);
				insert_btn.setEnabled(true);
				correction_btn.setEnabled(true);
				delete_btn.setEnabled(true);

				model1.setNumRows(0);
				model2.setNumRows(0);
				model3.setNumRows(0);

				DB_Conn_Query dbc = new DB_Conn_Query();
				dbc.sqlrun3(model3);
			}
		});
		//����� ��ư
		food_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				enable1 = false;
				enable2 = true;
				enable3 = false;

				food_stock_btn.setEnabled(true);
				restaurant_btn.setEnabled(true);
				food_btn.setEnabled(false);
				insert_btn.setEnabled(true);
				correction_btn.setEnabled(false);
				delete_btn.setEnabled(false);

				model1.setNumRows(0);
				model2.setNumRows(0);
				model3.setNumRows(0);

				DB_Conn_Query dbc = new DB_Conn_Query();
				dbc.sqlrun1(model1);
			}
		});
		//�л��Ĵ� ��ư
		restaurant_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				enable1 = false;
				enable2 = false;
				enable3 = true;

				food_stock_btn.setEnabled(true);
				restaurant_btn.setEnabled(false);
				food_btn.setEnabled(true);
				insert_btn.setEnabled(true);
				correction_btn.setEnabled(true);
				delete_btn.setEnabled(true);

				model1.setNumRows(0);
				model2.setNumRows(0);
				model3.setNumRows(0);

				DB_Conn_Query dbc = new DB_Conn_Query();
				dbc.sqlrun2(model2);
			}
		});
		//���ΰ�ħ ��ư
		refreshify_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				model1.setNumRows(0);
				model2.setNumRows(0);
				model3.setNumRows(0);
				model4.setNumRows(0);

				if(enable2 == true)
				{
					DB_Conn_Query dbc = new DB_Conn_Query();
					dbc.sqlrun1(model1);
				}
				else if(enable3 == true)
				{
					DB_Conn_Query dbc1 = new DB_Conn_Query();
					dbc1.sqlrun2(model2);
				}
				else if(enable1 == true)
				{
					DB_Conn_Query dbc2 = new DB_Conn_Query();
					dbc2.sqlrun3(model3);
				}

			}
		});
		//������ư
		delete_btn.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Object value,value1;
				int row;
				DB_Conn_Query dbc = new DB_Conn_Query();
				try {
					row = table2.getSelectedRow();
					value = table2.getValueAt(row, 0);
					System.out.println(value);
					dbc.state2_delete(value);
				}
				catch(ArrayIndexOutOfBoundsException e2)
				{
					try {
						row = table3.getSelectedRow();
						value = table3.getValueAt(row, 0);
						value1 = table3.getValueAt(row, 1);
						System.out.println(value+","+value1);
						dbc.state2_delete(value,value1);
					}
					catch(ArrayIndexOutOfBoundsException e3)
					{
						System.out.println("���� �߻�");
					}
				}



			}
				});
		//������ư
		correction_btn.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Object colum_name; 
				Object value,value1,value2;
				int row,col;
				DB_Conn_Query dbc = new DB_Conn_Query();

				try {
					row = table2.getSelectedRow();
					col = table2.getSelectedColumn();
					colum_name = table2.getColumnName(col);
					value = table2.getValueAt(row, col);
					value1 = table2.getValueAt(row, 0);
					System.out.println(value);
					dbc.state2_update(value,colum_name,value1);
				}
				catch(ArrayIndexOutOfBoundsException e2)
				{
					try {
						row = table3.getSelectedRow();
						col = table3.getSelectedColumn();
						colum_name = table3.getColumnName(col);
						if(colum_name.equals("���"))
						{
							value = table3.getValueAt(row, col);
							value1 = table3.getValueAt(row, 0);
							value2 = table3.getValueAt(row, 1);
							System.out.println(value);
							dbc.state2_update(value,colum_name,value1,value2);	
						}
						else if(colum_name.equals(null)){
							JOptionPane.showMessageDialog(null, "����� ������ �����մϴ�!!");
						}
					}
					catch(ArrayIndexOutOfBoundsException e3)
					{
						System.out.println("���� �߻�");
					}
				}
			}
				});
		insert_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Insert_Frame i = new Insert_Frame();

			}
		});

		order_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DB_Conn_Query dbc = new DB_Conn_Query();
				model4.setRowCount(0);
				dbc.order_sql(model4);

			}
		});
	}
}

class DB_Conn_Query {
	Connection con = null;
	private Statement stmt=null;           
	private PreparedStatement pstmt=null;  
	private CallableStatement cstmt=null;   
	private ResultSet rs=null;    //�����ƿ��¾�

	public DB_Conn_Query( ) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "sehong";      String password = "1234";
		try {   Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("����̹� ���� ����");
		con = DriverManager.getConnection(url, id, password);
		System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {         System.out.println("No Driver.");    }
		catch (SQLException e) {         System.out.println("Connection Fail");      }
	}
	public void sqlrun(JTable table5)//�����
	{
		try {
			int i = 0;
			int j = 0;
			ArrayList<Integer> array = new ArrayList<Integer>();

			CallableStatement cs = con.prepareCall("begin Temp(?); end;");
			// ��� �Ķ����
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			// ����
			cs.execute();
			ResultSet rs1 = (ResultSet)cs.getObject(1);
			DefaultTableModel m = (DefaultTableModel)table5.getModel();

			while(rs1.next()) {	

				String query = "select �ܰ� from ����� where ������ȣ='"+rs1.getString("������ȣ")+"'";
				try { 
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);

					if(rs.next()) {
						array.add(rs1.getInt("�Ѽ���")*rs.getInt("�ܰ�"));
						m.insertRow(i++,(new Object[]{rs1.getString("�Ĵ�ID"),rs1.getString("������ȣ"),rs1.getInt("�Ѽ���"),rs1.getInt("�Ѽ���")*rs.getInt("�ܰ�")}));
					}

					else
					{

						/*int result = 0;
						String s;
						while(true)
						{
							System.out.println(i +""+ j);
							if(j == i)
							{
								j = i+1;
								System.out.println(i +""+ j);
								break;
							}
							else
							{
								result += array.get(j);
							}
							j++;
						}*/
						m.insertRow(i++,(new Object[]{rs1.getString("�Ĵ�ID"),rs1.getString("�Ĵ�ID")+" �� ���ַ�",rs1.getInt("�Ѽ���"),""}));
					}


					table5.updateUI();
				}
				catch(Exception e){
					e.printStackTrace();
				}



			}

			rs.close();
			rs1.close();
			cs.close();
			con.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sqlrun1(DefaultTableModel model)//�����
	{
		String query = "select * from ����� order by ������ȣ ASC";
		try { 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			String row[] = new String[4];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = Integer.toString(rs.getInt(3));
				row[3] = rs.getString(4);

				model.addRow(row);         //model1�� ����
			}	 
			stmt.close();    rs.close();     con.close();             
		}catch (SQLException e) { e.printStackTrace(); }
	}


	public void sqlrun2(DefaultTableModel model) //�л��Ĵ�
	{
		String query =  "select * from �л��Ĵ�";
		try { 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			String row[] = new String[5];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				model.addRow(row); 
			}	 
			stmt.close();    rs.close();     con.close();             
		}catch (SQLException e) { e.printStackTrace(); }

	}


	public void sqlrun3(DefaultTableModel model)//������ ��� 
	{
		String query = "select * from ��������� order by ������ȣ ASC";
		try { 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			String row[] = new String[4];
			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				model.addRow(row);         //�𵨿� ����
			}	 
			stmt.close();    rs.close();     con.close();             
		}catch (SQLException e) { e.printStackTrace(); }
	}

	public void state2_update(Object value,Object column_name,Object value1) { 
		String query="";
		try { 
			if(column_name.equals("�Ĵ��")){
				query =  "update �л��Ĵ� set �Ĵ��= '"+value+"' where �Ĵ�ID = '"+value1+"'";;
			}
			else if(column_name.equals("��ȭ��ȣ")){
				query =  "update �л��Ĵ� set ��ȭ��ȣ= '"+value+"' where �Ĵ�ID = '"+value1+"'";
			}
			else if(column_name.equals("�ּ�")) {
				query =  "update �л��Ĵ� set �ּ�= '"+value+"' where �Ĵ�ID = '"+value1+"'";
			}
			else if(column_name.equals("�����")) {
				query =  "update �л��Ĵ� set �����= '"+value+"' where �Ĵ�ID = '"+value1+"'";
			}

			System.out.println(query);
			stmt = con.createStatement();                       
			stmt.executeUpdate(query);   	      

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}

	public void state2_update(Object value,Object column_name,Object value1,Object value2) { 
		String query="";
		try { 
			if(column_name.equals("���")) {
				query =  "update ��������� set ���= '"+value+"' where ������ȣ = '"+value2+"' and �Ĵ�ID = '" + value1+"'" ;
			}

			System.out.println(query);
			stmt = con.createStatement();                       
			stmt.executeUpdate(query);   	      

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
		catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
	}
	//������ ���,�л��Ĵ� ��� ����
	public void state2_delete(Object value) { //�̰� �׳� ������
		String query="";
		try { 
			try {
				query =  "delete from ��������� where �Ĵ�ID = '"+value+"'";  

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);    
			}
			catch (SQLException e) { e.printStackTrace(); }
			try {
				//�Է��� ���� ������ȣ��, �Ĵ�ID �� ���� Ʃ�û���
				query =  "delete from ���� where �Ĵ�ID = '"+value+"'"; 

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);      
			}
			catch (SQLException e) { e.printStackTrace(); }
			try {
				query =  "delete from �л��Ĵ� where �Ĵ�ID = '"+value+"'";  

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);            
			}
			catch(SQLException e) { e.printStackTrace(); }

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
	public void state2_delete(Object value, Object value1) { //�̰� �׳� ������
		String query="";
		try { 
			//�Է��� ���� ������ȣ��, �Ĵ�ID �� ���� Ʃ�û���

			query =  "delete from ��������� where �Ĵ�ID = '"+value+"' and ������ȣ = '" + value1 +"'" ;  

			System.out.println(query);
			stmt = con.createStatement();                       
			stmt.executeUpdate(query);            

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
	public void pstate2_insert(String id,String r_name,String address, String P_num, String name) {                       
		try { 
			String query= "select * from �л��Ĵ� where �Ĵ�ID=?";  //�Է��� �������� �����ϴ��� select
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);      //�߰� ���� �޾ƿ��� �Ű�����

			rs = pstmt.executeQuery();     
			if(rs.next()){                                   //���� �����ϸ�
				JOptionPane.showMessageDialog(null,"**�����ϴ� ����**");
			}
			else {			                       //������
				query = "insert into �л��Ĵ� values(?,?,?,?,?)";        //����
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);      
				pstmt.setString(2, r_name);         
				pstmt.setString(3, address);        
				pstmt.setString(4, P_num);
				pstmt.setString(5, name);     
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null,"**��ϵǾ����ϴ�**");
			}
			pstmt.close();  
		}catch (SQLException e) { e.printStackTrace(); }
	}  

	public void state_sql(String value,DefaultTableModel model1,DefaultTableModel model2,DefaultTableModel model3,DefaultTableModel model4) {       

		try { 
			String query1 ="";
			String query2 ="";
			String query3 ="";
			String query4 ="";

			try {
				query1 = "select *  from ����� where �ܰ� like '%"+Integer.parseInt(value)+"%' order by ��ü��ȣ ASC";
				query3 = "select * from ��������� where ��� like '%"+Integer.parseInt(value)+"%' order by ��� ASC";
				query4 = "select * from ���� where ���ּ��� like '%"+Integer.parseInt(value)+"%' order by �������� ASC";
			}
			catch(java.lang.NumberFormatException e1)
			{
				query1 = "select *  from ����� where ������ like '%"+value+"%' or ������ȣ like'%"+value+"%' or ��ü��ȣ like '%"+value+"%' order by ��ü��ȣ ASC";
				query2 = "select *  from �л��Ĵ� where �Ĵ�ID like '%"+value+"%' or �Ĵ�� like'%"+value+"%' or �ּ� like '%"+value+"%' or ��ȭ��ȣ like '%"+value+"%' or ����� like '%"+value+"%' order by �Ĵ�ID ASC";
				query3 = "select *  from ��������� where ������ like '%"+value+"%' or �Ĵ�ID like '%"+value+"%' or ������ȣ like '%"+value+"%' order by ��� ASC";
				query4 = "select *  from ���� where ���ֹ�ȣ like '%"+value+"%' or �Ĵ�ID like '%"+value+"%' or ������ȣ like '%"+value+"%' order by �������� ASC";
			}
			stmt = con.createStatement();
			rs = stmt.executeQuery(query1);               //����Ʈ �� ����� RS�� ����

			String row[] = new String[4];
			if(query1 !="")
			{
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = Integer.toString(rs.getInt(3));
					row[3] = rs.getString(4);
					model1.addRow(row);             //RS�� ������ MODEL1�� ����
				}
			}
			if(query2 !="")
			{
				rs = stmt.executeQuery(query2);               //����Ʈ �� ����� RS�� ����

				row = new String[5];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = rs.getString(4);
					row[4] = rs.getString(5);
					model2.addRow(row);             //RS�� ������ MODEL1�� ����
				}
			}
			if(query3 !="") {

				rs = stmt.executeQuery(query3);               //����Ʈ �� ����� RS�� ����

				row = new String[4];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = Integer.toString(rs.getInt(4));
					model3.addRow(row);             //RS�� ������ MODEL1�� ����s
				}
			}
			if(query4 !="")
			{
				rs = stmt.executeQuery(query4);               //����Ʈ �� ����� RS�� ����

				row = new String[5];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = Integer.toString(rs.getInt(4));
					row[4] = rs.getString(5);
					model4.addRow(row);             //RS�� ������ MODEL1�� ����
				}
			}
			stmt.close();  
		}catch (SQLException e) { e.printStackTrace(); }
	}


	public void order(String ID, String cook_n, String quantity ) {
		long milli =System.currentTimeMillis();
		java.util.Date date1=new java.util.Date(milli);
		try {
			String query = "insert into ���� values(SEQ.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(query); 
			pstmt.setString(1, ID);          
			pstmt.setString(2, cook_n);         
			pstmt.setInt(3, Integer.parseInt(quantity));        
			pstmt.setDate(4, new java.sql.Date(milli)); //���糯¥
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"**��ϵǾ����ϴ�**");
			pstmt.close(); 
		} 

		catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null,"**ID�� �������� �ʽ��ϴ�**");		
		}             
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"**���ַ��� Ȯ�����ּ���!!!**");
			}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"**��ĭ�� ������ Ȯ�����ּ���!!**");
		}
	}


	public void order_sql(DefaultTableModel model4) {
		try { 

			String query = "select * from ���� order by ���ֹ�ȣ DESC";
			stmt = con.createStatement();    
			rs = stmt.executeQuery(query);  

			String row[] = new String[5];
			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5); 
				model4.addRow(row);     
			}            //RS�� ������ MODEL4�� ����
			stmt.close();  
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"**��ĭ�� ������ Ȯ�����ּ���!!**");
			e.printStackTrace(); }	
	}
}
class Money_Frame extends JFrame{

	private String colName5[] = {"�Ĵ�ID", "������ȣ", "�Ѽ���","�� ���ֱݾ�"};
	public DefaultTableModel model5 = new DefaultTableModel(colName5, 0); 
	JTable table5 = new JTable(model5);           //table
	private JScrollPane jsp5 = new JScrollPane(table5,          //scrollPane ������������� ����     �ʿ��� ���� ������ ���� ��Ʈ �� �ϳ�
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public Money_Frame()
	{
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("�����");
		MainFrame.setBackground(Color.darkGray);
		//MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLayout(new GridLayout(1,1));
		MainFrame.setLocationRelativeTo(null);
		MainFrame.add(jsp5);
		MainFrame.setSize(500,500);
		MainFrame.setVisible(true);
		DB_Conn_Query dbc = new DB_Conn_Query();
		dbc.sqlrun(table5);
	}
}
class Insert_Frame extends JFrame{
	public Insert_Frame()
	{
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("�߰�");
		MainFrame.setBackground(Color.darkGray);
		//MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLayout(new FlowLayout(FlowLayout.CENTER,25,30));
		MainFrame.setLocationRelativeTo(null);

		JButton insert_1= new JButton("�Ĵ��߰�");  //�Ĵ� �߰���ư
		JButton insert_2= new JButton("����");     //���� ��ư

		MainFrame.add(insert_1);  //�Ĵ��߰���ư
		MainFrame.add(insert_2);   //���ֹ�ư

		//MainFrame.setResizable(false);//ũ�����
		MainFrame.setSize(200,200);
		MainFrame.setVisible(true);

		insert_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insert_1();
				MainFrame.dispose();
			}
		});

		insert_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insert_2();
				MainFrame.dispose();
			}
		});
	}

	public void insert_1() {    //�Ĵ��߰���ư�̺�Ʈ
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("�Ĵ��߰�");
		MainFrame.setBackground(Color.darkGray);
		MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLocationRelativeTo(null);

		//�ؽ�Ʈ ���̺�gksro
		JLabel ID = new JLabel("�Ĵ�ID");
		ID.setForeground(Color.black);
		ID.setBounds(10, 10, 300, 100);

		// �˻� â
		JTextField ID_t = new JTextField();


		JLabel r_name = new JLabel("�Ĵ��");
		r_name.setForeground(Color.black);

		JTextField r_name_t = new JTextField();

		JLabel address = new JLabel("�ּ�");
		address.setForeground(Color.black);

		JTextField address_t = new JTextField();

		JLabel P_num = new JLabel("��ȭ��ȣ");
		P_num.setForeground(Color.black);

		JTextField P_num_t = new JTextField();

		JLabel name = new JLabel("�����");
		name.setForeground(Color.black);

		JTextField name_t = new JTextField();

		JButton submit = new JButton("���");
		JButton exit = new JButton("���");



		MainFrame.add(ID);
		MainFrame.add(ID_t);
		MainFrame.add(r_name);
		MainFrame.add(r_name_t);
		MainFrame.add(address);
		MainFrame.add(address_t);
		MainFrame.add(P_num);
		MainFrame.add(P_num_t);
		MainFrame.add(name);
		MainFrame.add(name_t);
		MainFrame.add(submit);
		MainFrame.add(exit);

		MainFrame.setSize(200,200);
		MainFrame.setVisible(true);

		submit.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				try {
					DB_Conn_Query dbc = new DB_Conn_Query();
					dbc.pstate2_insert(ID_t.getText(),r_name_t.getText(),address_t.getText(),P_num_t.getText(),name_t.getText());

				}
				catch(ArrayIndexOutOfBoundsException e2)
				{
				}
				MainFrame.dispose();
			}
				});
		exit.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.dispose();
			}
				});
	}

	public void insert_2() {
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("����");
		MainFrame.setBackground(Color.darkGray);
		MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLocationRelativeTo(null);

		//JLabel order_n = new JLabel("���ֹ�ȣ");
		//order_n.setForeground(Color.black);
		//order_n.setBounds(10, 10, 300, 100);

		// �˻� â
		//JTextField order_n_t = new JTextField();


		JLabel ID = new JLabel("�Ĵ�ID");
		ID.setForeground(Color.black);

		JTextField ID_t = new JTextField();

		JLabel cook_n = new JLabel("������ȣ");
		cook_n.setForeground(Color.black);

		JTextField cook_n_t = new JTextField();

		JLabel quantity = new JLabel("���ּ���");
		quantity.setForeground(Color.black);

		JTextField quantity_t = new JTextField();


		JButton submit = new JButton("���");
		JButton exit = new JButton("���");

		//MainFrame.add(order_n);
		//MainFrame.add(order_n_t);
		MainFrame.add(ID);
		MainFrame.add(ID_t);
		MainFrame.add(cook_n);
		MainFrame.add(cook_n_t);
		MainFrame.add(quantity);
		MainFrame.add(quantity_t);
		MainFrame.add(submit);
		MainFrame.add(exit);

		MainFrame.setSize(200,200);
		MainFrame.setVisible(true);

		submit.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				try {
					DB_Conn_Query dbc = new DB_Conn_Query();
					dbc.order(ID_t.getText(),cook_n_t.getText(),quantity_t.getText());


				}

				catch(ArrayIndexOutOfBoundsException e2)
				{
				}



			}
				});
		exit.addActionListener(new ActionListener() //
				{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				DB_Conn_Query dbc = new DB_Conn_Query();
				try {

					MainFrame.dispose();
				}
				catch(ArrayIndexOutOfBoundsException e2)
				{
				}



			}
				});
	}


}
public class Product {

	public static void main(String[] args) {
		DB_Conn_Query dbc = new DB_Conn_Query();
		if (dbc.con == null) return;


		ContentPane f = new ContentPane();
		//dbc.sqlrun(f.model1);
	}
}

