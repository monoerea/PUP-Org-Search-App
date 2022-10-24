import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Icon;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class SubActivityViewMembers implements Runnable{

	//GUI Variables 
	private JFrame frmSubActivityViewMembers;
	private JLabel lblTitle;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	
	String strPostID=null;//get from dtime + student ID
	String strLocation=null;//get from previous view org
	String strStudentID=null;
	String strTitle=null;
	String strBody=null;
	private JButton btnBACK;
	private JSeparator separator_1;
	private JLabel lblOrganization;
	private JLabel lblAuthor;
	private JButton btnRemoveMember;
	private JButton btnPromoteToModerator;
	private JButton btnDemoteToMember;
	

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			SubActivityViewMembers window = new SubActivityViewMembers();
			window.frmSubActivityViewMembers.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public SubActivityViewMembers() {
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
		frmSubActivityViewMembers = new JFrame();
		frmSubActivityViewMembers.getContentPane().setBackground(new Color(176, 224, 230));
		//frmSubActivityViewMembers.setContentPane(new JLabel(new ImageIcon(SubActivityPost.class.getResource("/images/background.png"))));		
		frmSubActivityViewMembers.setTitle("PUP Organization Search");
		frmSubActivityViewMembers.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmSubActivityViewMembers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubActivityViewMembers.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("TEXT POST");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(10, 11, 127, 54);
		frmSubActivityViewMembers.getContentPane().add(lblTitle);
		
		btnBACK = new JButton("BACK");
		btnBACK.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBACK.setForeground(new Color(255, 69, 0));
		btnBACK.setOpaque(false);
		btnBACK.setContentAreaFilled(false);
		btnBACK.setBorderPainted(false);
		btnBACK.setBounds(145, 695, 150, 23);
		frmSubActivityViewMembers.getContentPane().add(btnBACK);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 419, 2);
		frmSubActivityViewMembers.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 123, 419, 2);
		frmSubActivityViewMembers.getContentPane().add(separator_1);
		
		lblOrganization = new JLabel("Organization");
		lblOrganization.setBounds(142, 11, 109, 23);
		frmSubActivityViewMembers.getContentPane().add(lblOrganization);
		
		lblAuthor = new JLabel("Member Count");
		lblAuthor.setBounds(142, 33, 109, 23);
		frmSubActivityViewMembers.getContentPane().add(lblAuthor);
		
		btnRemoveMember = new JButton("Remove Member");
		btnRemoveMember.setBackground(Color.RED);
		btnRemoveMember.setBounds(10, 89, 133, 23);
		frmSubActivityViewMembers.getContentPane().add(btnRemoveMember);
		
		btnPromoteToModerator = new JButton("Promote to Moderator");
		btnPromoteToModerator.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnPromoteToModerator.setBackground(Color.GREEN);
		btnPromoteToModerator.setBounds(296, 89, 133, 23);
		frmSubActivityViewMembers.getContentPane().add(btnPromoteToModerator);
		
		btnDemoteToMember = new JButton("Demote to Member");
		btnDemoteToMember.setForeground(Color.WHITE);
		btnDemoteToMember.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDemoteToMember.setBackground(Color.GRAY);
		btnDemoteToMember.setBounds(153, 89, 133, 23);
		frmSubActivityViewMembers.getContentPane().add(btnDemoteToMember);
		
		ArrayList <String> arrScrollPane = new ArrayList<>();
		try {
			int num=1;
			objResultSet=objSQLQuery.executeQuery("select * from tblorganizationbelongdata where strOrganizationID = '"+ActivityViewOrg.strOrganizationID+"'");
			while(objResultSet.next()) {
				arrScrollPane.add("( "
						+ objResultSet.getString("strRole")
						+ " ) "
						+ objResultSet.getString("strStudentID")
						);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JList list = new JList(arrScrollPane.toArray());
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 136, 419, 553);
		frmSubActivityViewMembers.getContentPane().add(scrollPane);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int intIndex = list.getSelectedIndex();
				System.out.println(arrScrollPane.get(intIndex));
				//GO TO ACTIVITYVIEWORG
				MainActivity.ActivityViewOrg();
				frmSubActivityViewMembers.dispose();
			}
		});				
		
		btnBACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityViewOrg();
				frmSubActivityViewMembers.dispose();
			}
		});
		
		btnRemoveMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Remove per selection in jlist
				//ask for confirmation
			}
		});
		
		btnDemoteToMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Remove per selection in jlist
				//ask for confirmation
			}
		});
		
		btnPromoteToModerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Remove per selection in jlist
				//ask for confirmation
			}
		});
		
	}
}
