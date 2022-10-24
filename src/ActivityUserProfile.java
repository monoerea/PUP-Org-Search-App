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
import javax.swing.JSeparator;

public class ActivityUserProfile implements Runnable{

	//GUI Variables 
	private JFrame frmActivityUserProfile;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnProfile;
	private JButton btnBack;
	private JLabel lblStudentID;
	private JButton btnEditDetails;
	private JSeparator separator;
	private JLabel lblDetails;
	private JLabel lblFirstName;
	private JLabel lblMiddleName;
	private JLabel lblLastName;
	private JLabel lblCollege;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JSeparator separator_1;
	private ScrollPane scrollPane;
	private JLabel lblOrgsJoined;
	private JButton btnEditOrgs;

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
					ActivityUserProfile window = new ActivityUserProfile();
					window.frmActivityUserProfile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public ActivityUserProfile() {
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
		frmActivityUserProfile = new JFrame();
		frmActivityUserProfile.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityUserProfile.setContentPane(new JLabel(new ImageIcon(ActivityUserProfile.class.getResource("/images/background.png"))));		
		frmActivityUserProfile.setTitle("PUP Organization Search");
		frmActivityUserProfile.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityUserProfile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityUserProfile.getContentPane().setLayout(null);
		
		btnProfile = new JButton(new ImageIcon(ActivityUserProfile.class.getResource("/images/user.png")));
		btnProfile.setBounds(173, 11, 100, 100);
		frmActivityUserProfile.getContentPane().add(btnProfile);
		
		btnBack = new JButton("BACK");
		btnBack.setOpaque(false);
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(145, 695, 150, 23);
		frmActivityUserProfile.getContentPane().add(btnBack);
		
		System.out.println("++++++++++++++++++++++++"+strStudentID);
		lblStudentID = new JLabel(strStudentID);
		lblStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblStudentID.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentID.setBounds(114, 122, 219, 23);
		frmActivityUserProfile.getContentPane().add(lblStudentID);
		
		btnEditDetails = new JButton("EDIT");
		btnEditDetails.setForeground(new Color(255, 69, 0));
		btnEditDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditDetails.setBorderPainted(false);
		btnEditDetails.setOpaque(false);
		btnEditDetails.setContentAreaFilled(false);
		btnEditDetails.setBounds(349, 174, 80, 23);
		frmActivityUserProfile.getContentPane().add(btnEditDetails);
		
		separator = new JSeparator();
		separator.setBounds(10, 156, 419, 23);
		frmActivityUserProfile.getContentPane().add(separator);
		
		lblDetails = new JLabel("DETAILS");
		lblDetails.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDetails.setBounds(20, 170, 319, 31);
		frmActivityUserProfile.getContentPane().add(lblDetails);
		
		lblFirstName = new JLabel(strFirstName);
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFirstName.setBounds(20, 226, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblFirstName);
		
		lblMiddleName = new JLabel(strMiddleName);
		lblMiddleName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblMiddleName.setBounds(20, 260, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblMiddleName);
		
		lblLastName = new JLabel(strLastName);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblLastName.setBounds(20, 294, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblLastName);
		
		lblCollege = new JLabel(strCollege);
		lblCollege.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCollege.setBounds(20, 328, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblCollege);
		
		lblEmail = new JLabel(strEmail);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEmail.setBounds(20, 362, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblEmail);
		
		lblPassword = new JLabel(strPassword);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPassword.setBounds(20, 396, 319, 23);
		frmActivityUserProfile.getContentPane().add(lblPassword);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 423, 419, 23);
		frmActivityUserProfile.getContentPane().add(separator_1);
		
		ArrayList <String> arrScrollPane = new ArrayList<>();
		try {
			int num=1;
			objResultSet=objSQLQuery.executeQuery("select * from tblorganizationbelongdata where strStudentID = '" + ActivityUserProfile.strStudentID +"'");//
			System.out.println(objResultSet);
			while(objResultSet.next()) {
				arrScrollPane.add("( "
						+ objResultSet.getString("strRole")
						+ " ) "
						+ objResultSet.getString("strOrganizationID")
						);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JList list = new JList(arrScrollPane.toArray());
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 71, 419, 618);
		scrollPane.setBounds(10, 467, 419, 222);
		frmActivityUserProfile.getContentPane().add(scrollPane);
		frmActivityUserProfile.getContentPane().add(scrollPane);
		
		lblOrgsJoined = new JLabel("ORGS JOINED");
		lblOrgsJoined.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrgsJoined.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrgsJoined.setBounds(20, 430, 319, 31);
		frmActivityUserProfile.getContentPane().add(lblOrgsJoined);
		
		btnEditOrgs = new JButton("EDIT");
		btnEditOrgs.setOpaque(false);
		btnEditOrgs.setForeground(new Color(255, 69, 0));
		btnEditOrgs.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditOrgs.setContentAreaFilled(false);
		btnEditOrgs.setBorderPainted(false);
		btnEditOrgs.setBounds(349, 435, 80, 23);
		frmActivityUserProfile.getContentPane().add(btnEditOrgs);
		
		btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO EDIT USER ACTIVITY
				MainActivity.ActivityEditUser();
				frmActivityUserProfile.dispose();
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO EDIT ORG ACTIVITY
				MainActivity.ActivityHomePage();
				frmActivityUserProfile.dispose();
			}
		});

		btnEditOrgs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO EDIT ORG ACTIVITY
			}
		});
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				//GO TO ACTIVITYVIEWORG
				/*
				ActivityViewOrg.strOrganizationID=objResultSet.getString("strOrganizationID");
				ActivityViewOrg.strOrganizationName=objResultSet.getString("strOrganizationName");
				ActivityViewOrg.strOrganizationType=objResultSet.getString("strOrganizationType");
				ActivityViewOrg.strOrganizationEmail=objResultSet.getString("strOrganizationEmail");
				ActivityViewOrg.strOrganizationDescription=objResultSet.getString("strOrganizationDescription");
				*/

				MainActivity.ActivityViewOrg();
				frmActivityUserProfile.dispose();
			}
		});
		
	}
}
