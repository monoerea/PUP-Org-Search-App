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
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class SubActivityViewPost implements Runnable{

	//GUI Variables 
	private JFrame frmSubActivityCreatePost;
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
	private JLabel lblDateAndTime;
	private JLabel lblPostID;
	

	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					SubActivityViewPost window = new SubActivityViewPost();
					window.frmSubActivityCreatePost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public SubActivityViewPost() {
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
		frmSubActivityCreatePost = new JFrame();
		frmSubActivityCreatePost.getContentPane().setBackground(new Color(176, 224, 230));
		//frmSubActivityCreatePost.setContentPane(new JLabel(new ImageIcon(SubActivityPost.class.getResource("/images/background.png"))));		
		frmSubActivityCreatePost.setTitle("PUP Organization Search");
		frmSubActivityCreatePost.setBounds(400, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmSubActivityCreatePost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubActivityCreatePost.getContentPane().setLayout(null);
		
		lblTitle = new JLabel("TEXT POST");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(10, 11, 127, 54);
		frmSubActivityCreatePost.getContentPane().add(lblTitle);
		
		btnBACK = new JButton("BACK");
		btnBACK.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBACK.setForeground(new Color(255, 69, 0));
		btnBACK.setOpaque(false);
		btnBACK.setContentAreaFilled(false);
		btnBACK.setBorderPainted(false);
		btnBACK.setBounds(145, 695, 150, 23);
		frmSubActivityCreatePost.getContentPane().add(btnBACK);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 419, 2);
		frmSubActivityCreatePost.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 123, 419, 2);
		frmSubActivityCreatePost.getContentPane().add(separator_1);

		JTextArea txtrAnInterestingTitle = new JTextArea();
		txtrAnInterestingTitle.setEditable(false);
		txtrAnInterestingTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtrAnInterestingTitle.setText("  An interesting title");
		txtrAnInterestingTitle.setBounds(10, 90, 419, 22);
		frmSubActivityCreatePost.getContentPane().add(txtrAnInterestingTitle);
		
		JTextArea txtrYourTextPost = new JTextArea();
		txtrYourTextPost.setEditable(false);
		txtrYourTextPost.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtrYourTextPost.setText("  Your text post (optional)");
		txtrYourTextPost.setBounds(10, 136, 419, 548);
		frmSubActivityCreatePost.getContentPane().add(txtrYourTextPost);
		
		lblOrganization = new JLabel("Organization");
		lblOrganization.setBounds(142, 11, 109, 23);
		frmSubActivityCreatePost.getContentPane().add(lblOrganization);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(142, 33, 109, 23);
		frmSubActivityCreatePost.getContentPane().add(lblAuthor);
		
		lblDateAndTime = new JLabel("Date and Time");
		lblDateAndTime.setBounds(142, 55, 109, 23);
		frmSubActivityCreatePost.getContentPane().add(lblDateAndTime);
		
		lblPostID = new JLabel("PostID");
		lblPostID.setBounds(320, 55, 109, 23);
		frmSubActivityCreatePost.getContentPane().add(lblPostID);
		
		btnBACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
				MainActivity.ActivityHomePage();
				frmSubActivityCreatePost.dispose();
			}
		});
		
	}
}
