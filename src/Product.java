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
	//테이블 컬럼명 
	private String colName1[] = { "식재료번호", "식재료명", "단가", "업체번호" };            //state 1        
	private String colName2[] = { "식당ID", "식당명", "주소", "전화번호","담당자" };               //state 2 
	private String colName3[] = {  "식당ID", "식재료번호", "식재료명", "재고량" };     //pstate 1
	private String colName4[] = { "발주번호", "식당ID", "식재료번호", "발주수량","발주일자" };   //pstate2_select

	//테이블 컬럼명 테이블에 부착
	public DefaultTableModel model1 = new DefaultTableModel(colName1, 0);
	public DefaultTableModel model2 = new DefaultTableModel(colName2, 0);        
	public DefaultTableModel model3 = new DefaultTableModel(colName3, 0);
	public DefaultTableModel model4 = new DefaultTableModel(colName4, 0); 


	JTable table1 = new JTable(model1);           //table
	JTable table2 = new JTable(model2);           //table
	JTable table3 = new JTable(model3);           //table
	JTable table4 = new JTable(model4);           //table

	//테이블 스크롤 기능
	private JScrollPane jsp1 = new JScrollPane(table1,          //scrollPane 만들어져있으면 삭제     필요한 곳에 가져다 쓰는 세트 중 하나
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp2 = new JScrollPane(table2,          //scrollPane 만들어져있으면 삭제     필요한 곳에 가져다 쓰는 세트 중 하나
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp3 = new JScrollPane(table3,          //scrollPane 만들어져있으면 삭제     필요한 곳에 가져다 쓰는 세트 중 하나
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JScrollPane jsp4 = new JScrollPane(table4,          //scrollPane 만들어져있으면 삭제     필요한 곳에 가져다 쓰는 세트 중 하나
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



	public ContentPane() {
		//전체 메인 화면
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("식자재 발주 관리 프로그램");
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 윈도우를 닫으면 프로그램 종료
		MainFrame.setLayout(null);

		//MainFrame.setLocationRelativeTo(null);
		//메뉴바
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("메뉴");
		JMenuItem m_item = new JMenuItem("경비계산");
		mb.add(menu);
		menu.add(m_item);

		MainFrame.setJMenuBar(mb);//메인 화면에 메뉴바 부착
		// 검색 창
		JTextField search = new JTextField();
		search.setBounds(580,120,250,25);

		//텍스트 레이블
		JLabel P_name = new JLabel("재고관리 시스템");
		P_name.setFont(new Font("맑은고딕", Font.BOLD, 30));
		P_name.setForeground(Color.black);
		P_name.setBounds(10, 10, 300, 100);

		//패널
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

		//라디오 버튼
		/*ButtonGroup g = new ButtonGroup();
		JRadioButton r1 = new JRadioButton("상품명");
		JRadioButton r2 = new JRadioButton("업체명");
		JRadioButton r3 = new JRadioButton("식당명");
		g.add(r1);
		g.add(r2);
		g.add(r3);

		r1.setBounds(600, 100, 80, 20);
		r1.setFont(new Font("맑은고딕", Font.BOLD, 15));
		r1.setForeground(Color.black);
		r1.setBorderPainted(false);
		r1.setFocusPainted(false);
		r1.setContentAreaFilled(false);

		r2.setBounds(680, 100, 80, 20);
		r2.setFont(new Font("맑은고딕", Font.BOLD, 15));
		r2.setForeground(Color.black);
		r2.setBorderPainted(false);
		r2.setFocusPainted(false);
		r2.setContentAreaFilled(false);

		r3.setBounds(760, 100, 80, 20);
		r3.setFont(new Font("맑은고딕", Font.BOLD, 15));
		r3.setForeground(Color.black);
		r3.setBorderPainted(false);
		r3.setFocusPainted(false);
		r3.setContentAreaFilled(false);
		 */
		//버튼

		JButton food_stock_btn = new JButton("식자재 재고");
		food_stock_btn.setBounds(0,100,150, 50);
		food_stock_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		food_stock_btn.setBackground(Color.white);
		//food_btn.setBorderPainted(false);

		JButton food_btn = new JButton("식재료");
		food_btn.setBounds(150,100,150, 50);
		food_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		food_btn.setBackground(Color.white);
		//food_btn.setBorderPainted(false);

		JButton restaurant_btn = new JButton("학생식당");
		restaurant_btn.setBounds(300,100,150, 50);
		restaurant_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		restaurant_btn.setBackground(Color.white);
		//restaurant_btn.setBorderPainted(false);

		JButton Search_btn = new JButton("검색");

		Search_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		Search_btn.setBounds(850,120,100,25);

		JButton refreshify_btn = new JButton("새로고침");
		refreshify_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		refreshify_btn.setBounds(970,120,180,25);

		JButton order_btn = new JButton("발주조회");   //내보내기->발주버튼로변경
		order_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		order_btn.setBounds(1170,120,200,25);

		JButton insert_btn = new JButton("추가");
		insert_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		insert_btn.setBounds(0,0,150,100);

		JButton correction_btn = new JButton("수정");
		correction_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		correction_btn.setBounds(0,200,150,100);

		JButton delete_btn = new JButton("삭제");
		delete_btn .setFont(new Font("맑은고딕", Font.BOLD, 20));
		delete_btn .setBounds(0,400,150,100);

		//프레임+패널에 부착
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

		//MainFrame.setResizable(false);//크기고정
		MainFrame.setSize(1420,780);
		MainFrame.setVisible(true);


		//경비계산 버튼
		m_item.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Money_Frame m = new Money_Frame();
			}
		});
		//검색 버튼
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
		//식자재 재고 버튼
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
		//식재료 버튼
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
		//학생식당 버튼
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
		//새로고침 버튼
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
		//삭제버튼
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
						System.out.println("오류 발생");
					}
				}



			}
				});
		//수정버튼
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
						if(colum_name.equals("재고량"))
						{
							value = table3.getValueAt(row, col);
							value1 = table3.getValueAt(row, 0);
							value2 = table3.getValueAt(row, 1);
							System.out.println(value);
							dbc.state2_update(value,colum_name,value1,value2);	
						}
						else if(colum_name.equals(null)){
							JOptionPane.showMessageDialog(null, "재고량만 수정이 가능합니다!!");
						}
					}
					catch(ArrayIndexOutOfBoundsException e3)
					{
						System.out.println("오류 발생");
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
	private ResultSet rs=null;    //결과담아오는애

	public DB_Conn_Query( ) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "sehong";      String password = "1234";
		try {   Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("드라이버 적재 성공");
		con = DriverManager.getConnection(url, id, password);
		System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {         System.out.println("No Driver.");    }
		catch (SQLException e) {         System.out.println("Connection Fail");      }
	}
	public void sqlrun(JTable table5)//식재료
	{
		try {
			int i = 0;
			int j = 0;
			ArrayList<Integer> array = new ArrayList<Integer>();

			CallableStatement cs = con.prepareCall("begin Temp(?); end;");
			// 출력 파라메터
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			// 실행
			cs.execute();
			ResultSet rs1 = (ResultSet)cs.getObject(1);
			DefaultTableModel m = (DefaultTableModel)table5.getModel();

			while(rs1.next()) {	

				String query = "select 단가 from 식재료 where 식재료번호='"+rs1.getString("식재료번호")+"'";
				try { 
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);

					if(rs.next()) {
						array.add(rs1.getInt("총수량")*rs.getInt("단가"));
						m.insertRow(i++,(new Object[]{rs1.getString("식당ID"),rs1.getString("식재료번호"),rs1.getInt("총수량"),rs1.getInt("총수량")*rs.getInt("단가")}));
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
						m.insertRow(i++,(new Object[]{rs1.getString("식당ID"),rs1.getString("식당ID")+" 총 발주량",rs1.getInt("총수량"),""}));
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
	public void sqlrun1(DefaultTableModel model)//식재료
	{
		String query = "select * from 식재료 order by 식재료번호 ASC";
		try { 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			String row[] = new String[4];

			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = Integer.toString(rs.getInt(3));
				row[3] = rs.getString(4);

				model.addRow(row);         //model1에 부착
			}	 
			stmt.close();    rs.close();     con.close();             
		}catch (SQLException e) { e.printStackTrace(); }
	}


	public void sqlrun2(DefaultTableModel model) //학생식당
	{
		String query =  "select * from 학생식당";
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


	public void sqlrun3(DefaultTableModel model)//식자재 재고 
	{
		String query = "select * from 식자재재고 order by 식재료번호 ASC";
		try { 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			String row[] = new String[4];
			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				model.addRow(row);         //모델에 부착
			}	 
			stmt.close();    rs.close();     con.close();             
		}catch (SQLException e) { e.printStackTrace(); }
	}

	public void state2_update(Object value,Object column_name,Object value1) { 
		String query="";
		try { 
			if(column_name.equals("식당명")){
				query =  "update 학생식당 set 식당명= '"+value+"' where 식당ID = '"+value1+"'";;
			}
			else if(column_name.equals("전화번호")){
				query =  "update 학생식당 set 전화번호= '"+value+"' where 식당ID = '"+value1+"'";
			}
			else if(column_name.equals("주소")) {
				query =  "update 학생식당 set 주소= '"+value+"' where 식당ID = '"+value1+"'";
			}
			else if(column_name.equals("담당자")) {
				query =  "update 학생식당 set 담당자= '"+value+"' where 식당ID = '"+value1+"'";
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
			if(column_name.equals("재고량")) {
				query =  "update 식자재재고 set 재고량= '"+value+"' where 식재료번호 = '"+value2+"' and 식당ID = '" + value1+"'" ;
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
	//식자재 재고,학생식당 목록 삭제
	public void state2_delete(Object value) { //이건 그냥 냅뒀음
		String query="";
		try { 
			try {
				query =  "delete from 식자재재고 where 식당ID = '"+value+"'";  

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);    
			}
			catch (SQLException e) { e.printStackTrace(); }
			try {
				//입력한 값의 식재료번호와, 식당ID 가 같은 튜플삭제
				query =  "delete from 발주 where 식당ID = '"+value+"'"; 

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);      
			}
			catch (SQLException e) { e.printStackTrace(); }
			try {
				query =  "delete from 학생식당 where 식당ID = '"+value+"'";  

				System.out.println(query);
				stmt = con.createStatement();                       
				stmt.executeUpdate(query);            
			}
			catch(SQLException e) { e.printStackTrace(); }

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
	public void state2_delete(Object value, Object value1) { //이건 그냥 냅뒀음
		String query="";
		try { 
			//입력한 값의 식재료번호와, 식당ID 가 같은 튜플삭제

			query =  "delete from 식자재재고 where 식당ID = '"+value+"' and 식재료번호 = '" + value1 +"'" ;  

			System.out.println(query);
			stmt = con.createStatement();                       
			stmt.executeUpdate(query);            

			stmt.close();   con.close();
		}catch (SQLException e) { e.printStackTrace(); }
	}
	public void pstate2_insert(String id,String r_name,String address, String P_num, String name) {                       
		try { 
			String query= "select * from 학생식당 where 식당ID=?";  //입력한 정보들이 존재하는지 select
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);      //추가 정보 받아오는 매개변수

			rs = pstmt.executeQuery();     
			if(rs.next()){                                   //만약 존재하면
				JOptionPane.showMessageDialog(null,"**존재하는 정보**");
			}
			else {			                       //없으면
				query = "insert into 학생식당 values(?,?,?,?,?)";        //삽입
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);      
				pstmt.setString(2, r_name);         
				pstmt.setString(3, address);        
				pstmt.setString(4, P_num);
				pstmt.setString(5, name);     
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null,"**등록되었습니다**");
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
				query1 = "select *  from 식재료 where 단가 like '%"+Integer.parseInt(value)+"%' order by 업체번호 ASC";
				query3 = "select * from 식자재재고 where 재고량 like '%"+Integer.parseInt(value)+"%' order by 재고량 ASC";
				query4 = "select * from 발주 where 발주수량 like '%"+Integer.parseInt(value)+"%' order by 발주일자 ASC";
			}
			catch(java.lang.NumberFormatException e1)
			{
				query1 = "select *  from 식재료 where 식재료명 like '%"+value+"%' or 식재료번호 like'%"+value+"%' or 업체번호 like '%"+value+"%' order by 업체번호 ASC";
				query2 = "select *  from 학생식당 where 식당ID like '%"+value+"%' or 식당명 like'%"+value+"%' or 주소 like '%"+value+"%' or 전화번호 like '%"+value+"%' or 담당자 like '%"+value+"%' order by 식당ID ASC";
				query3 = "select *  from 식자재재고 where 식재료명 like '%"+value+"%' or 식당ID like '%"+value+"%' or 식재료번호 like '%"+value+"%' order by 재고량 ASC";
				query4 = "select *  from 발주 where 발주번호 like '%"+value+"%' or 식당ID like '%"+value+"%' or 식재료번호 like '%"+value+"%' order by 발주일자 ASC";
			}
			stmt = con.createStatement();
			rs = stmt.executeQuery(query1);               //셀렉트 한 결과를 RS에 저장

			String row[] = new String[4];
			if(query1 !="")
			{
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = Integer.toString(rs.getInt(3));
					row[3] = rs.getString(4);
					model1.addRow(row);             //RS의 정보를 MODEL1에 부착
				}
			}
			if(query2 !="")
			{
				rs = stmt.executeQuery(query2);               //셀렉트 한 결과를 RS에 저장

				row = new String[5];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = rs.getString(4);
					row[4] = rs.getString(5);
					model2.addRow(row);             //RS의 정보를 MODEL1에 부착
				}
			}
			if(query3 !="") {

				rs = stmt.executeQuery(query3);               //셀렉트 한 결과를 RS에 저장

				row = new String[4];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = Integer.toString(rs.getInt(4));
					model3.addRow(row);             //RS의 정보를 MODEL1에 부착s
				}
			}
			if(query4 !="")
			{
				rs = stmt.executeQuery(query4);               //셀렉트 한 결과를 RS에 저장

				row = new String[5];
				while (rs.next()) {
					row[0] = rs.getString(1);
					row[1] = rs.getString(2);
					row[2] = rs.getString(3);
					row[3] = Integer.toString(rs.getInt(4));
					row[4] = rs.getString(5);
					model4.addRow(row);             //RS의 정보를 MODEL1에 부착
				}
			}
			stmt.close();  
		}catch (SQLException e) { e.printStackTrace(); }
	}


	public void order(String ID, String cook_n, String quantity ) {
		long milli =System.currentTimeMillis();
		java.util.Date date1=new java.util.Date(milli);
		try {
			String query = "insert into 발주 values(SEQ.NEXTVAL,?,?,?,?)";
			pstmt = con.prepareStatement(query); 
			pstmt.setString(1, ID);          
			pstmt.setString(2, cook_n);         
			pstmt.setInt(3, Integer.parseInt(quantity));        
			pstmt.setDate(4, new java.sql.Date(milli)); //현재날짜
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"**등록되었습니다**");
			pstmt.close(); 
		} 

		catch (SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null,"**ID가 존재하지 않습니다**");		
		}             
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"**발주량을 확인해주세요!!!**");
			}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"**빈칸이 없는지 확인해주세요!!**");
		}
	}


	public void order_sql(DefaultTableModel model4) {
		try { 

			String query = "select * from 발주 order by 발주번호 DESC";
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
			}            //RS의 정보를 MODEL4에 부착
			stmt.close();  
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"**빈칸이 없는지 확인해주세요!!**");
			e.printStackTrace(); }	
	}
}
class Money_Frame extends JFrame{

	private String colName5[] = {"식당ID", "식재료번호", "총수량","총 발주금액"};
	public DefaultTableModel model5 = new DefaultTableModel(colName5, 0); 
	JTable table5 = new JTable(model5);           //table
	private JScrollPane jsp5 = new JScrollPane(table5,          //scrollPane 만들어져있으면 삭제     필요한 곳에 가져다 쓰는 세트 중 하나
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public Money_Frame()
	{
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("경비계산");
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
		MainFrame.setTitle("추가");
		MainFrame.setBackground(Color.darkGray);
		//MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLayout(new FlowLayout(FlowLayout.CENTER,25,30));
		MainFrame.setLocationRelativeTo(null);

		JButton insert_1= new JButton("식당추가");  //식당 추가버튼
		JButton insert_2= new JButton("발주");     //발주 버튼

		MainFrame.add(insert_1);  //식당추가버튼
		MainFrame.add(insert_2);   //발주버튼

		//MainFrame.setResizable(false);//크기고정
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

	public void insert_1() {    //식당추가버튼이벤트
		JFrame MainFrame = new JFrame();
		MainFrame.setTitle("식당추가");
		MainFrame.setBackground(Color.darkGray);
		MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLocationRelativeTo(null);

		//텍스트 레이블gksro
		JLabel ID = new JLabel("식당ID");
		ID.setForeground(Color.black);
		ID.setBounds(10, 10, 300, 100);

		// 검색 창
		JTextField ID_t = new JTextField();


		JLabel r_name = new JLabel("식당명");
		r_name.setForeground(Color.black);

		JTextField r_name_t = new JTextField();

		JLabel address = new JLabel("주소");
		address.setForeground(Color.black);

		JTextField address_t = new JTextField();

		JLabel P_num = new JLabel("전화번호");
		P_num.setForeground(Color.black);

		JTextField P_num_t = new JTextField();

		JLabel name = new JLabel("담당자");
		name.setForeground(Color.black);

		JTextField name_t = new JTextField();

		JButton submit = new JButton("등록");
		JButton exit = new JButton("취소");



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
		MainFrame.setTitle("발주");
		MainFrame.setBackground(Color.darkGray);
		MainFrame.setLayout(new GridLayout(6,2));
		MainFrame.setLocationRelativeTo(null);

		//JLabel order_n = new JLabel("발주번호");
		//order_n.setForeground(Color.black);
		//order_n.setBounds(10, 10, 300, 100);

		// 검색 창
		//JTextField order_n_t = new JTextField();


		JLabel ID = new JLabel("식당ID");
		ID.setForeground(Color.black);

		JTextField ID_t = new JTextField();

		JLabel cook_n = new JLabel("식재료번호");
		cook_n.setForeground(Color.black);

		JTextField cook_n_t = new JTextField();

		JLabel quantity = new JLabel("발주수량");
		quantity.setForeground(Color.black);

		JTextField quantity_t = new JTextField();


		JButton submit = new JButton("등록");
		JButton exit = new JButton("취소");

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

