import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import com.mysql.cj.xdevapi.SqlStatement;

import javax.swing.Icon;
import java.awt.ScrollPane;
import javax.swing.JSeparator;

public class ActivityViewOrg implements Runnable{

	//GUI Variables 
	private JFrame frmActivityViewOrg;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnProfile;
	private JButton btnBack;
	private JLabel lblOrganizationName;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JLabel lblOrgsJoined;
	private JButton btnJoin;
	private JButton btnMemberCount;
	private JButton btnViewMembers;

	public static String strOrganizationID,
		strOrganizationName,
		strOrganizationType,
		strOrganizationEmail,
		strOrganizationDescription;
	

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ActivityViewOrg window = new ActivityViewOrg();
			window.frmActivityViewOrg.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ActivityViewOrg() {
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

			objResultSet=objSQLQuery.executeQuery("select * from tblorganizationdata");
			/*while(objResultSet.next()){
				if(strOrganizationID)
			}*/
			System.out.println("-----"+strOrganizationID);
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
		frmActivityViewOrg = new JFrame();
		frmActivityViewOrg.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityViewOrg.setContentPane(new JLabel(new ImageIcon(ActivityViewOrg.class.getResource("/images/background.png"))));		
		frmActivityViewOrg.setTitle("PUP Organization Search");
		frmActivityViewOrg.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityViewOrg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityViewOrg.getContentPane().setLayout(null);
		
		btnProfile = new JButton(new ImageIcon(ActivityViewOrg.class.getResource("/images/user.png")));
		btnProfile.setBounds(10, 11, 100, 100);
		frmActivityViewOrg.getContentPane().add(btnProfile);
		
		btnBack = new JButton("BACK");
		btnBack.setOpaque(false);
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(145, 695, 150, 23);
		frmActivityViewOrg.getContentPane().add(btnBack);
		
		lblOrganizationName = new JLabel(strOrganizationName);
		lblOrganizationName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOrganizationName.setBounds(120, 11, 309, 31);
		frmActivityViewOrg.getContentPane().add(lblOrganizationName);
		
		separator = new JSeparator();
		separator.setBounds(10, 156, 419, 14);
		frmActivityViewOrg.getContentPane().add(separator);
				
		ArrayList <String> arrScrollPane = new ArrayList<>();
		try {
			int num=1;
			objResultSet=objSQLQuery.executeQuery("select * from tblpostsdata where strLocation = '" + strOrganizationID + "'" + "order by dtime desc");
			while(objResultSet.next()) {
				arrScrollPane.add("( "
						+ objResultSet.getString("dtime")
						+ " ) "
						+ objResultSet.getString("strStudentID")
						+ " - "
						+ objResultSet.getString("strTitle")
						);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JList list = new JList(arrScrollPane.toArray());
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 197, 419, 460);
		frmActivityViewOrg.getContentPane().add(scrollPane);
		
		lblOrgsJoined = new JLabel("POSTS");
		lblOrgsJoined.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrgsJoined.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrgsJoined.setBounds(20, 160, 106, 31);
		frmActivityViewOrg.getContentPane().add(lblOrgsJoined);
		
		btnJoin = new JButton("Join");
		btnJoin.setBounds(20, 122, 79, 23);
		frmActivityViewOrg.getContentPane().add(btnJoin);
		
		JLabel lblDescription = new JLabel(strOrganizationDescription);
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblDescription.setBounds(120, 35, 309, 31);
		frmActivityViewOrg.getContentPane().add(lblDescription);
		
		JLabel lblOrganizationType = new JLabel(strOrganizationType);
		lblOrganizationType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrganizationType.setBounds(120, 118, 159, 31);
		frmActivityViewOrg.getContentPane().add(lblOrganizationType);
		
		JButton btnEditDetails = new JButton("EDIT");
		btnEditDetails.setOpaque(false);
		btnEditDetails.setForeground(new Color(255, 69, 0));
		btnEditDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditDetails.setContentAreaFilled(false);
		btnEditDetails.setBorderPainted(false);
		btnEditDetails.setBounds(349, 122, 80, 23);
		frmActivityViewOrg.getContentPane().add(btnEditDetails);
		
		JButton btnCreatePost = new JButton("Create Post");
		btnCreatePost.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreatePost.setBounds(10, 658, 419, 31);
		frmActivityViewOrg.getContentPane().add(btnCreatePost);
		
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnMemberCount = new JButton("");
		btnMemberCount.setOpaque(false);
		btnMemberCount.setForeground(new Color(255, 69, 0));
		btnMemberCount.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMemberCount.setContentAreaFilled(false);
		btnMemberCount.setBorderPainted(false);
		btnMemberCount.setBounds(120, 163, 159, 23);
		
		frmActivityViewOrg.getContentPane().add(btnMemberCount);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				//GO TO ACTIVITYVIEWPOST
				MainActivity.SubActivityViewPost();
				frmActivityViewOrg.dispose();
			}
		});
		
		btnViewMembers = new JButton("View Members");
		btnViewMembers.setOpaque(false);
		btnViewMembers.setForeground(new Color(255, 69, 0));
		btnViewMembers.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnViewMembers.setContentAreaFilled(false);
		btnViewMembers.setBorderPainted(false);
		btnViewMembers.setBounds(270, 163, 159, 23);
		frmActivityViewOrg.getContentPane().add(btnViewMembers);

		try {
			objResultSet=objSQLQuery.executeQuery("select * from tblorganizationbelongdata");
			boolean booljoined=false;
			while(objResultSet.next()){
				if(objResultSet.getString("strStudentID").contentEquals(ActivityUserProfile.strStudentID)){
					booljoined=true;
				}
			}
			if(booljoined=true){
				btnJoin.setText("Joined!");
			}
			else{
				btnJoin.setText("Join");
			}
			}catch (SQLException e1) {
			e1.printStackTrace();
			}
		
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO MODIFY TBLORGANIZATIONBELONGDATA
				try {
					objResultSet=objSQLQuery.executeQuery("select * from tblorganizationbelongdata");
					boolean booljoined=false;
					while(objResultSet.next()){
						if(objResultSet.getString("strStudentID").contentEquals(ActivityUserProfile.strStudentID)){
							booljoined=true;
						}
					}
					if(!booljoined){
						objSQLQuery.executeUpdate("insert into tblorganizationbelongdata values"
										+ "("
										+ "'"
										+ ActivityUserProfile.strStudentID
										+ "'"
										+ ","
										+ "'"
										+ strOrganizationID
										+ "'"
										+ ","
										+ "'"
										+ "member"
										+ "'"
										+ ")"
										);
						} else{
							objSQLQuery.executeUpdate("delete from tblorganizationbelongdata where strStudentID = "
										+ "'"
										+ ActivityUserProfile.strStudentID
										+ "'"
										);
						}
					}catch (SQLException e1) {
					e1.printStackTrace();
					}
				}
		});
		
		btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO MODIFY EDITORG
				MainActivity.ActivityEditOrg();
				frmActivityViewOrg.dispose();
			}
		});
		
		btnCreatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//USE ORG NAME AND STORE TO...
				//GO TO SUBACTIVITYPOST
				MainActivity.SubActivityCreatePost();
				frmActivityViewOrg.dispose();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO ACTIVITYDISCOVER
				MainActivity.ActivityHomePage();
				frmActivityViewOrg.dispose();
			}
		});
		
		btnViewMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO SUBACTIVITYVIEWMEMBERS
				MainActivity.SubActivityViewMembers();
				frmActivityViewOrg.dispose();
			}
		});
			
	}
}
