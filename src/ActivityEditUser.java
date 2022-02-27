import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import java.awt.ScrollPane;
import javax.swing.JSeparator;

public class ActivityEditUser {

	//GUI Variables 
	private JFrame frmActivityEditUser;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnProfile;
	private JButton btnBack;
	private JButton editBTN;
	private JButton saveBTN;
	
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
	private ScrollPane scrollPane;
	private JLabel lblOrgsJoined;
	private JTextField[] textfield;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityEditUser window = new ActivityEditUser();
					window.frmActivityEditUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ActivityEditUser() {
		try {
			//connecting to server
			String strDriver = "com.mysql.cj.jdbc.Driver";
	        String strConn = "jdbc:mysql://localhost:3306/dbpuporgsearch";
	        String strUser = "root";
	        String strPass = "1234";
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
		frmActivityEditUser = new JFrame();
		frmActivityEditUser.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityViewUser.setContentPane(new JLabel(new ImageIcon(ActivityViewUser.class.getResource("/images/background.png"))));		
		frmActivityEditUser.setTitle("PUP Organization Search");
		frmActivityEditUser.setBounds(0, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityEditUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityEditUser.getContentPane().setLayout(null);
		
		btnProfile = new JButton(new ImageIcon(ActivityViewUser.class.getResource("/images/user.png")));
		btnProfile.setBounds(173, 11, 100, 100);
		frmActivityEditUser.getContentPane().add(btnProfile);
		
		btnBack = new JButton("BACK");
		btnBack.setOpaque(false);
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(145, 695, 150, 23);
		frmActivityEditUser.getContentPane().add(btnBack);
		
		lblStudentID = new JLabel("Student ID");
		lblStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblStudentID.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentID.setBounds(114, 122, 219, 23);
		frmActivityEditUser.getContentPane().add(lblStudentID);
		
		separator = new JSeparator();
		separator.setBounds(10, 156, 419, 23);
		frmActivityEditUser.getContentPane().add(separator);
		
		lblDetails = new JLabel("DETAILS");
		lblDetails.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDetails.setBounds(20, 170, 319, 31);
		frmActivityEditUser.getContentPane().add(lblDetails);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFirstName.setBounds(20, 226, 319, 23);
		frmActivityEditUser.getContentPane().add(lblFirstName);
		
		lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblMiddleName.setBounds(20, 260, 319, 23);
		frmActivityEditUser.getContentPane().add(lblMiddleName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblLastName.setBounds(20, 294, 319, 23);
		frmActivityEditUser.getContentPane().add(lblLastName);
		
		lblCollege = new JLabel("College");
		lblCollege.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblCollege.setBounds(20, 328, 319, 23);
		frmActivityEditUser.getContentPane().add(lblCollege);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEmail.setBounds(20, 362, 319, 23);
		frmActivityEditUser.getContentPane().add(lblEmail);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPassword.setBounds(20, 396, 319, 23);
		frmActivityEditUser.getContentPane().add(lblPassword);
		
		//JTEXTFIELDS
		int baseNUM=195;
		
		JTextField textfield[] = new JTextField[6];
		for (int i=0;i<6;i++) {
			
			textfield[i] = new JTextField();
			textfield[i].setBounds(228, baseNUM+=33, 201, 19);
			frmActivityEditUser.getContentPane().add(textfield[i]);
			textfield[i].setColumns(10);
			
			if (i==0) {
				textfield[i].setText("  First Name...");
			}
			if (i==1) {
				textfield[i].setText("  Middle Name...");
			}
			if (i==2) {
				textfield[i].setText("  Last Name...");
			}
			if (i==3) {
				textfield[i].setText("  College...");
			}
			if (i==4) {
				textfield[i].setText("  Email...");
			}
			if (i==5) {
				textfield[i].setText("  Password...");
			}
		}
		
		
		///for resetting fields
		for( int i = 0; i<6; i++) {
			
			
			}
		
		textfield[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[0].setText(null);
				if(textfield[1].getText().equals("")) {
					textfield[1].setText("  Middle Name...");
				}
				if(textfield[2].getText().equals("")) {
					textfield[2].setText("  Last Name...");
				}
				if(textfield[3].getText().equals("")) {
					textfield[3].setText("  College...");
				}
				if(textfield[4].getText().equals("")) {
					textfield[4].setText("  Email...");
				}
				if(textfield[5].getText().equals("")) {
					textfield[5].setText("  Password...");
				}
			}
		});
		textfield[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[1].setText(null);
				if(textfield[0].getText().equals("")) {
					textfield[0].setText("  First Name...");
				}
				if(textfield[2].getText().equals("")) {
					textfield[2].setText("  Last Name...");
				}
				if(textfield[3].getText().equals("")) {
					textfield[3].setText("  College...");
				}
				if(textfield[4].getText().equals("")) {
					textfield[4].setText("  Email...");
				}
				if(textfield[5].getText().equals("")) {
					textfield[5].setText("  Password...");
				}
			}
		});
		textfield[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[2].setText(null);
				if(textfield[0].getText().equals("")) {
					textfield[0].setText("  First Name...");
				}
				if(textfield[1].getText().equals("")) {
					textfield[1].setText("  Middle Name...");
				}
				if(textfield[3].getText().equals("")) {
					textfield[3].setText("  College...");
				}
				if(textfield[4].getText().equals("")) {
					textfield[4].setText("  Email...");
				}
				if(textfield[5].getText().equals("")) {
					textfield[5].setText("  Password...");
				}
			}
		});
		textfield[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[3].setText(null);
				if(textfield[0].getText().equals("")) {
					textfield[0].setText("  First Name...");
				}
				if(textfield[1].getText().equals("")) {
					textfield[1].setText("  Middle Name...");
				}
				if(textfield[2].getText().equals("")) {
					textfield[2].setText("  Last Name...");
				}
				if(textfield[4].getText().equals("")) {
					textfield[4].setText("  Email...");
				}
				if(textfield[5].getText().equals("")) {
					textfield[5].setText("  Password...");
				}
			}
		});
		textfield[4].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[4].setText(null);
				if(textfield[0].getText().equals("")) {
					textfield[0].setText("  First Name...");
				}
				if(textfield[1].getText().equals("")) {
					textfield[1].setText("  Middle Name...");
				}
				if(textfield[2].getText().equals("")) {
					textfield[2].setText("  Last Name...");
				}
				if(textfield[3].getText().equals("")) {
					textfield[3].setText("  College...");
				}
				if(textfield[5].getText().equals("")) {
					textfield[5].setText("  Password...");
				}
			}
		});
		textfield[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textfield[5].setText(null);
				if(textfield[0].getText().equals("")) {
					textfield[0].setText("  First Name...");
				}
				if(textfield[1].getText().equals("")) {
					textfield[1].setText("  Middle Name...");
				}
				if(textfield[2].getText().equals("")) {
					textfield[2].setText("  Last Name...");
				}
				if(textfield[3].getText().equals("")) {
					textfield[3].setText("  College...");
				}
				if(textfield[4].getText().equals("")) {
					textfield[4].setText("  Email...");
				}
			}
		});
		
		saveBTN = new JButton("SAVE");
		saveBTN.setFont(new Font("Tahoma", Font.PLAIN, 25));
		saveBTN.setBounds(225, 425, 92, 31);
		frmActivityEditUser.getContentPane().add(saveBTN);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(14, 466, 319, 31);
		frmActivityEditUser.getContentPane().add(separator_1);
		
		scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 513, 419, 137);
		/*
		 * contentPane.add(scrollPane);
			JList list = new JList(objPosts.toArray());
			scrollPane.setViewportView(list);	
		 */
		frmActivityEditUser.getContentPane().add(scrollPane);
		frmActivityEditUser.getContentPane().add(scrollPane);
		
		lblOrgsJoined = new JLabel("ORGS JOINED");
		lblOrgsJoined.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrgsJoined.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrgsJoined.setBounds(14, 476, 319, 31);
		frmActivityEditUser.getContentPane().add(lblOrgsJoined);
	/*
		saveBTN.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent objAE) {
				
				String strFirstName1 = textfield[0].getText().trim();
				String strMiddleName1 = textfield[1].getText().trim();
				String strLastName1 = textfield[2].getText().trim();
				String strCollege1 = textfield[3].getText().trim();
				String strEmail1 = textfield[4].getText().trim();
				String strPassword1 = textfield[5].getText().trim();
				try {
					objResultSet = objSQLQuery.executeQuery("select * from tbluserdata");
					while(objResultSet.next()) {
						if(/*strStudentID*//*.contentEquals(objResultSet.getString("strStudentID"))) {
							/*
							objSQLQuery.executeUpdate("UPDATE tbluserdata"
									+ "SET strFirstName = 'strFirstName1'"
									+ ", strMiddleName = 'strMiddleName1'"
									+", strLastName = 'strLastName1'"
									+", strCollege = 'strCollege1'"
									+", strEmail = 'strEmail1'"
									+", strPassword = 'strPassword1'"
									+"WHERE strStudentID ='2019-06744-MN-0';"
									);
							*/
							/*}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//GO TO USER PROFILE IN ACTIVITY
			}
		});*/
	}//end of initialize method
}