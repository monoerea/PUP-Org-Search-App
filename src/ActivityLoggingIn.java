import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ActivityLoggingIn implements Runnable {

	//GUI Variables 
	private JFrame frmActivityLoggingIn;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JLabel lblPUPLogo;
	private JLabel lblTitle;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	
	

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ActivityLoggingIn window = new ActivityLoggingIn();
			window.frmActivityLoggingIn.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public ActivityLoggingIn() {
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
		frmActivityLoggingIn = new JFrame();
		//frmActivityLoggingIn.getContentPane().setBackground(new Color(176, 224, 230));
		frmActivityLoggingIn.setTitle("PUP Organization Search");
		frmActivityLoggingIn.setBounds(400, 0, 455, 768);
		System.out.println((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityLoggingIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityLoggingIn.getContentPane().setLayout(null);
		frmActivityLoggingIn.setContentPane(new JLabel(new ImageIcon(ActivityHomePage.class.getResource("/images/background.png"))));		
		
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtEmail.setOpaque(false);
		txtEmail.setToolTipText("only accepts webmail");
		txtEmail.setText("  Email...");
		txtEmail.setBounds(100, 331, 230, 54);
		frmActivityLoggingIn.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtPassword.setOpaque(false);
		txtPassword.setText("  Password...");
		txtPassword.setBounds(100, 396, 230, 54);
		frmActivityLoggingIn.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLOGIN = new JButton("LOGIN");
		btnLOGIN.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLOGIN.setFont(new Font("Calibri", Font.BOLD, 30));
		btnLOGIN.setBounds(100, 461, 230, 54);
		btnLOGIN.setBackground(new Color(143, 170, 220));
		frmActivityLoggingIn.getContentPane().add(btnLOGIN);
		
		lblPUPLogo = new JLabel("Insert PUP Logo Here");
		lblPUPLogo.setBounds(145, 106, 150, 150);
		lblPUPLogo.setIcon(new ImageIcon(ActivityLoggingIn.class.getResource("/images/puplogo1.png")));
		frmActivityLoggingIn.getContentPane().add(lblPUPLogo);
		
		JButton btnCreateUserActivity = new JButton("Create Account");
		btnCreateUserActivity.setBounds(145, 526, 150, 23);
		btnCreateUserActivity.setBorderPainted(false);
		btnCreateUserActivity.setOpaque(false);
		btnCreateUserActivity.setContentAreaFilled(false);
		frmActivityLoggingIn.getContentPane().add(btnCreateUserActivity);
		
		lblTitle = new JLabel("PUP Organization Search");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(28, 266, 377, 54);
		frmActivityLoggingIn.getContentPane().add(lblTitle);
		
		//makes content of textfield to disappear when mouse
		txtEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtEmail.getText().equals("  Email..."))
					txtEmail.setText(null);
				if(txtPassword.getPassword().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});
		
		//makes content of textfield to disappear when mouse 
		txtPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtPassword.getText().equals("  Password..."))
					txtPassword.setText(null);
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
			} 
		}); 
		
		btnLOGIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
				if(txtPassword.getPassword().equals("")) {
					txtPassword.setText("  Password...");
				}
				
				//compare email and password
				String strEmail1=txtEmail.getText().trim();//from textbox
				String strPassword1;
				strPassword1 = txtPassword.getText().trim();//from textbox
				String strEmail2;//from db
				String strPassword2;//from db
				boolean boolcorrect = false;
				try {
					objResultSet=objSQLQuery.executeQuery("Select * from tbluserdata");//retrieve data and store to objResultSet
					while(objResultSet.next()){
						strEmail2=objResultSet.getString("strEmail").trim();
						strPassword2=objResultSet.getString("strPassword").trim();
						System.out.println("-----");
						System.out.println(strEmail1 + " " + strEmail2);
						System.out.println(strPassword1 + " " + strPassword2);
						if(strEmail2.contentEquals(strEmail1)) {
							if(strPassword2.contentEquals(strPassword1)) {
								JOptionPane.showMessageDialog(null,"Entered");
								boolcorrect=true;
								//GO TO HOME PAGE ACTIVITY
								System.out.println(objResultSet.getString("strStudentID"));
								ActivityUserProfile.strStudentID=objResultSet.getString("strStudentID");
								ActivityUserProfile.strFirstName=objResultSet.getString("strFirstName");
								ActivityUserProfile.strMiddleName=objResultSet.getString("strMiddleName");
								ActivityUserProfile.strLastName=objResultSet.getString("strLastName");
								ActivityUserProfile.strCollege=objResultSet.getString("strCollege");
								ActivityUserProfile.strEmail=objResultSet.getString("strEmail");
								ActivityUserProfile.strPassword=objResultSet.getString("strPassword");
								MainActivity.ActivityHomePage();
								frmActivityLoggingIn.dispose();
							}
						}
						
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
				if(boolcorrect != true){
					JOptionPane.showMessageDialog(null,"Wrong Email or Password");
				}
			} 
		}); 
		
		btnCreateUserActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO CREATE USER ACTIVITY
				MainActivity.ActivityCreateUser();
				frmActivityLoggingIn.dispose();
			}
		});
	}
}
