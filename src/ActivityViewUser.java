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

public class ActivityViewUser implements Runnable{

	//GUI Variables 
	private JFrame frmActivityViewUser;
	
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
	private JSeparator separator;
	private JLabel lblDetails;
	private JLabel lblFirstName;
	private JLabel lblMiddleName;
	private JLabel lblLastName;
	private JLabel lblCollege;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JSeparator separator_1;
	private JScrollPane scrollPane;
	private JLabel lblOrgsJoined;


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
			ActivityViewUser window = new ActivityViewUser();
			window.frmActivityViewUser.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ActivityViewUser() {
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
		frmActivityViewUser = new JFrame();
		frmActivityViewUser.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityViewUser.setContentPane(new JLabel(new ImageIcon(ActivityViewUser.class.getResource("/images/background.png"))));		
		frmActivityViewUser.setTitle("PUP Organization Search");
		frmActivityViewUser.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityViewUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityViewUser.getContentPane().setLayout(null);
		
		btnProfile = new JButton(new ImageIcon(ActivityViewUser.class.getResource("/images/user.png")));
		btnProfile.setBounds(173, 11, 100, 100);
		frmActivityViewUser.getContentPane().add(btnProfile);

		JButton btnEditDetails = new JButton("EDIT");
		btnEditDetails.setOpaque(false);
		btnEditDetails.setForeground(new Color(255, 69, 0));
		btnEditDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditDetails.setContentAreaFilled(false);
		btnEditDetails.setBorderPainted(false);
		btnEditDetails.setBounds(349, 170, 80, 23);
		frmActivityViewUser.getContentPane().add(btnEditDetails);
		
		btnBack = new JButton("BACK");
		btnBack.setOpaque(false);
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(145, 695, 150, 23);
		frmActivityViewUser.getContentPane().add(btnBack);
		
		lblStudentID = new JLabel(strStudentID);
		lblStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblStudentID.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentID.setBounds(114, 122, 219, 23);
		frmActivityViewUser.getContentPane().add(lblStudentID);
		
		separator = new JSeparator();
		separator.setBounds(10, 156, 419, 23);
		frmActivityViewUser.getContentPane().add(separator);
		
		lblDetails = new JLabel("DETAILS");
		lblDetails.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDetails.setBounds(20, 170, 319, 31);
		frmActivityViewUser.getContentPane().add(lblDetails);
		
		lblFirstName = new JLabel(strFirstName);
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFirstName.setBounds(20, 226, 319, 23);
		frmActivityViewUser.getContentPane().add(lblFirstName);
		
		lblMiddleName = new JLabel(strMiddleName);
		lblMiddleName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblMiddleName.setBounds(20, 260, 319, 23);
		frmActivityViewUser.getContentPane().add(lblMiddleName);
		
		lblLastName = new JLabel(strLastName);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblLastName.setBounds(20, 294, 319, 23);
		frmActivityViewUser.getContentPane().add(lblLastName);
		
		lblCollege = new JLabel(strCollege);
		lblCollege.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCollege.setBounds(20, 328, 319, 23);
		frmActivityViewUser.getContentPane().add(lblCollege);
		
		lblEmail = new JLabel(strEmail);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEmail.setBounds(20, 362, 319, 23);
		frmActivityViewUser.getContentPane().add(lblEmail);
		
		/*
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPassword.setBounds(20, 396, 319, 23);
		frmActivityViewUser.getContentPane().add(lblPassword);*/
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 423, 419, 23);
		frmActivityViewUser.getContentPane().add(separator_1);
		
		lblOrgsJoined = new JLabel("ORGS JOINED");
		lblOrgsJoined.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrgsJoined.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrgsJoined.setBounds(20, 430, 319, 31);
		frmActivityViewUser.getContentPane().add(lblOrgsJoined);
		
		ArrayList <String> arrScrollPane = new ArrayList<>();
		try {
			int num=1;
			objResultSet=objSQLQuery.executeQuery("select * from tblpostsdata");
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
		scrollPane.setBounds(10, 467, 419, 222);
		frmActivityViewUser.getContentPane().add(scrollPane);
		
		/*btnEditDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityEditUser();
				frmActivityViewUser.dispose();
			}
		});*/

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				//GO TO ACTIVITYVIEWORG
				MainActivity.ActivityViewOrg();
				frmActivityViewUser.dispose();
			}
		});		

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityHomePage();
				frmActivityViewUser.dispose();
			}
		});


	}
}
