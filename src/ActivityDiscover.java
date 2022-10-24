import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Icon;
import java.awt.ScrollPane;

public class ActivityDiscover implements Runnable {

	//GUI Variables 
	private JFrame frmActivityDiscover;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnSearch;
	private JButton btnBack;
	private JTextField txtSearch;
	private JList list;

	public static String strOrganizationID,
		strOrganizationName,
		strOrganizationType,
		strOrganizationEmail,
		strOrganizationDescription;

	public static String strStudentID,
		strFirstName,
		strMiddleName,
		strLastName,
		strCollege,
		strEmail,
		strPassword;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ActivityDiscover window = new ActivityDiscover();
			window.frmActivityDiscover.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public ActivityDiscover() {
		try {
			//connecting to server
			String strDriver = "com.mysql.cj.jdbc.Driver";
	        String strConn = "jdbc:mysql://localhost:3306/dbpuporgsearch";
	        String strUser = "root";
	        String strPass = "Whippycape2012";
        	Class.forName(strDriver);
			objConn = DriverManager.getConnection(strConn, strUser, strPass);
			objSQLQuery = objConn.createStatement();
			boolConn2Db = true;
			if (boolConn2Db) {
				System.out.println("Connected Succesfully..");
				initialize();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Connected Unsuccesfully..");
			e.printStackTrace();
		}  
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmActivityDiscover = new JFrame();
		frmActivityDiscover.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityDiscover.setContentPane(new JLabel(new ImageIcon(ActivityDiscover.class.getResource("/images/background.png"))));		
		frmActivityDiscover.setTitle("PUP Organization Search");
		frmActivityDiscover.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityDiscover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityDiscover.getContentPane().setLayout(null);
		
		btnSearch = new JButton(new ImageIcon(ActivityDiscover.class.getResource("/images/magnifying.png")));
		//btnSearch.setSelectedIcon(new ImageIcon(ActivityDiscover.class.getResource("/images/magnifying.png")));
		btnSearch.setBounds(10, 11, 50, 50);
		frmActivityDiscover.getContentPane().add(btnSearch);
		
		ArrayList <String> arrScrollPane = new ArrayList<>();
		try {
			int num=1;
			objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
			while(objResultSet.next()) {
				arrScrollPane.add(objResultSet.getString("strOrganizationName"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setViewportView(list);
		scrollPane.setBounds(10, 72, 419, 500);
		frmActivityDiscover.getContentPane().add(scrollPane);

		JButton btnCreateOrg = new JButton("Create Organization");
		btnCreateOrg.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreateOrg.setBounds(10, 658, 419, 31);
		frmActivityDiscover.getContentPane().add(btnCreateOrg);
				
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(145, 695, 150, 23);
		frmActivityDiscover.getContentPane().add(btnBack);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtSearch.setText("  Search...");
		txtSearch.setBounds(70, 11, 359, 50);
		frmActivityDiscover.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//finalize search, modify jlist
				try {
					String strSearch1=txtSearch.getText().trim().toUpperCase();
					arrScrollPane.clear();
					//check by org names
					objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strOrganizationName").toUpperCase();
						if(strSearch2.contains(strSearch1)){
							arrScrollPane.add(strSearch2);
						}
					}
					//check by org type
					objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strOrganizationType").toUpperCase();
						if(strSearch2.contains(strSearch1)){
							arrScrollPane.add(strSearch2);
						}
					}
					//check by user
					objResultSet=objSQLQuery.executeQuery("select * from tbluserdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strFirstName").toUpperCase().concat(objResultSet.getString("strLastName").toUpperCase());
						if(strSearch2.contains(strSearch1)){
							arrScrollPane.add(strSearch2);
						}
					}

					list = new JList(arrScrollPane.toArray());
					scrollPane.setViewportView(list);
					//frmActivityDiscover.getContentPane().add(scrollPane);

					System.out.println("Successed searched!");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("ahello before selection model");
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				System.out.println("ahello after selection model");
				list.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int intIndex = list.getSelectedIndex();
						System.out.println(arrScrollPane.get(intIndex));
						String strIndex = arrScrollPane.get(intIndex).trim().toUpperCase();
						System.out.println(strIndex);
						/* */
						try {
							//check by org names
							objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
							while(objResultSet.next()){
								String strSearch2 = objResultSet.getString("strOrganizationName").toUpperCase();
								if(strSearch2.contains(strIndex)){
									ActivityViewOrg.strOrganizationID=objResultSet.getString("strOrganizationID");
									ActivityViewOrg.strOrganizationName=objResultSet.getString("strOrganizationName");
									ActivityViewOrg.strOrganizationType=objResultSet.getString("strOrganizationType");
									ActivityViewOrg.strOrganizationEmail=objResultSet.getString("strOrganizationEmail");
									ActivityViewOrg.strOrganizationDescription=objResultSet.getString("strOrganizationDescription");
									MainActivity.ActivityViewOrg();
									frmActivityDiscover.dispose();
								}
							}
							//check by org type
							objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
							while(objResultSet.next()){
								String strSearch2 = objResultSet.getString("strOrganizationType").toUpperCase();
								if(strSearch2.contains(strIndex)){
									ActivityViewOrg.strOrganizationID=objResultSet.getString("strOrganizationID");
									ActivityViewOrg.strOrganizationName=objResultSet.getString("strOrganizationName");
									ActivityViewOrg.strOrganizationType=objResultSet.getString("strOrganizationType");
									ActivityViewOrg.strOrganizationEmail=objResultSet.getString("strOrganizationEmail");
									ActivityViewOrg.strOrganizationDescription=objResultSet.getString("strOrganizationDescription");
									MainActivity.ActivityViewOrg();
									frmActivityDiscover.dispose();
								}
							}
							//check by user
							objResultSet=objSQLQuery.executeQuery("select * from tbluserdata");
							while(objResultSet.next()){
								String strSearch2 = objResultSet.getString("strFirstName").toUpperCase().concat(objResultSet.getString("strLastName").toUpperCase());
								if(strSearch2.contains(strIndex)){
									ActivityViewUser.strStudentID=objResultSet.getString("strStudentID");
									ActivityViewUser.strFirstName=objResultSet.getString("strFirstName");
									ActivityViewUser.strMiddleName=objResultSet.getString("strMiddleName");
									ActivityViewUser.strLastName=objResultSet.getString("strLastName");
									ActivityViewUser.strCollege=objResultSet.getString("strCollege");
									ActivityViewUser.strEmail=objResultSet.getString("strEmail");
									ActivityViewUser.strPassword=objResultSet.getString("strPassword");
									MainActivity.ActivityViewUser();
									frmActivityDiscover.dispose();
								}
							}

							System.out.println("Successed ");

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
		});
		
			}
		});
		txtSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				txtSearch.setText(null);
			}
		});
		System.out.println("hello before selection model");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		System.out.println("hello after selection model");
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				String strIndex = arrScrollPane.get(intIndex).trim().toUpperCase();
				System.out.println(strIndex);
				

				/* */
				try {
					//check by org names
					objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strOrganizationName").toUpperCase();
						if(strSearch2.contains(strIndex)){
							ActivityViewOrg.strOrganizationID=objResultSet.getString("strOrganizationID");
							ActivityViewOrg.strOrganizationName=objResultSet.getString("strOrganizationName");
							ActivityViewOrg.strOrganizationType=objResultSet.getString("strOrganizationType");
							ActivityViewOrg.strOrganizationEmail=objResultSet.getString("strOrganizationEmail");
							ActivityViewOrg.strOrganizationDescription=objResultSet.getString("strOrganizationDescription");
							MainActivity.ActivityViewOrg();
							frmActivityDiscover.dispose();
						}
					}
					//check by org type
					objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strOrganizationType").toUpperCase();
						if(strSearch2.contains(strIndex)){
							ActivityViewOrg.strOrganizationID=objResultSet.getString("strOrganizationID");
							ActivityViewOrg.strOrganizationName=objResultSet.getString("strOrganizationName");
							ActivityViewOrg.strOrganizationType=objResultSet.getString("strOrganizationType");
							ActivityViewOrg.strOrganizationEmail=objResultSet.getString("strOrganizationEmail");
							ActivityViewOrg.strOrganizationDescription=objResultSet.getString("strOrganizationDescription");
							MainActivity.ActivityViewOrg();
							frmActivityDiscover.dispose();
						}
					}
					//check by user
					objResultSet=objSQLQuery.executeQuery("select * from tbluserdata");
					while(objResultSet.next()){
						String strSearch2 = objResultSet.getString("strFirstName").toUpperCase().concat(objResultSet.getString("strLastName").toUpperCase());
						if(strSearch2.contains(strIndex)){
							ActivityViewUser.strStudentID=objResultSet.getString("strStudentID");
							ActivityViewUser.strFirstName=objResultSet.getString("strFirstName");
							ActivityViewUser.strMiddleName=objResultSet.getString("strMiddleName");
							ActivityViewUser.strLastName=objResultSet.getString("strLastName");
							ActivityViewUser.strCollege=objResultSet.getString("strCollege");
							ActivityViewUser.strEmail=objResultSet.getString("strEmail");
							ActivityViewUser.strPassword=objResultSet.getString("strPassword");
							MainActivity.ActivityViewUser();
							frmActivityDiscover.dispose();
						}
					}

					System.out.println("Successed ");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreateOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityCreateOrg();
				frmActivityDiscover.dispose();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityHomePage();
				frmActivityDiscover.dispose();
			}
		});
		
	}
}
