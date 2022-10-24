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

public class ActivityHomePage implements Runnable {

	//GUI Variables 
	private JFrame frmActivityHomePage;
	private JLabel lblTitle;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnSearch;
	private JButton btnProfile;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */

	
	public void run() {
		try {
			ActivityHomePage window = new ActivityHomePage();
			window.frmActivityHomePage.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public ActivityHomePage() {
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
		

		frmActivityHomePage = new JFrame();
		frmActivityHomePage.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityHomePage.setContentPane(new JLabel(new ImageIcon(ActivityHomePage.class.getResource("/images/background.png"))));		
		frmActivityHomePage.setTitle("PUP Organization Search");
		frmActivityHomePage.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityHomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityHomePage.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("PUP ORGANIZATION SEARCH");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(10, 11, 299, 54);
		frmActivityHomePage.getContentPane().add(lblTitle);
		
		btnProfile = new JButton(new ImageIcon(ActivityHomePage.class.getResource("/images/user.png")));
		btnProfile.setBounds(319, 11, 50, 50);
		frmActivityHomePage.getContentPane().add(btnProfile);
		
		btnSearch = new JButton(new ImageIcon(ActivityHomePage.class.getResource("/images/magnifying.png")));
		//btnSearch.setSelectedIcon(new ImageIcon(ActivityHomePage.class.getResource("/images/magnifying.png")));
		btnSearch.setBounds(379, 11, 50, 50);
		frmActivityHomePage.getContentPane().add(btnSearch);
		
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
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 71, 419, 618);
		frmActivityHomePage.getContentPane().add(scrollPane);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogout.setForeground(new Color(255, 69, 0));
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBounds(145, 695, 150, 23);
		frmActivityHomePage.getContentPane().add(btnLogout);

		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityUserProfile();
				frmActivityHomePage.dispose();
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityDiscover();
				frmActivityHomePage.dispose();
			}
		});

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				//GO TO ACTIVITYVIEWPOST
				MainActivity.SubActivityViewPost();
				frmActivityHomePage.dispose();
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityLoggingIn();
				frmActivityHomePage.dispose();
			}
		});
		
		
		
	}
}
