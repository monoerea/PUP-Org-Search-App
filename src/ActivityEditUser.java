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
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class ActivityEditUser implements Runnable {

	//GUI Variables 
	private JFrame frmActivityEditUser;
	private JLabel lblPUPLogo;
	private JLabel lblTitle;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JTextField txtCollege;
	private JTextField txtEmail;
	private JTextField txtPassword;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email, Password;//put to main
	private JButton btnGoBack;
	/**
	 * @wbp.nonvisual location=132,579
	 */
	private final JTextArea txtDesc = new JTextArea();
	

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ActivityEditUser window = new ActivityEditUser();
			window.frmActivityEditUser.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	        String strPass = "Whippycape2012"; // TODO
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
		//frmActivityEditUser.getContentPane().setBackground(new Color(176, 224, 230));
		frmActivityEditUser.setTitle("PUP Name Search");
		frmActivityEditUser.setBounds(400, 0, 455,768);
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityEditUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityEditUser.getContentPane().setLayout(null);
		frmActivityEditUser.setContentPane(new JLabel(new ImageIcon(ActivityHomePage.class.getResource("/images/background1.png"))));		
		
		lblPUPLogo = new JLabel("Insert PUP Logo Here");
		lblPUPLogo.setBounds(145, 21, 150, 150);
		lblPUPLogo.setIcon(new ImageIcon(ActivityEditUser.class.getResource("/images/puplogo1.png")));
		frmActivityEditUser.getContentPane().add(lblPUPLogo);
		
		lblTitle = new JLabel("PUP Name Search");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(31, 191, 377, 54);
		frmActivityEditUser.getContentPane().add(lblTitle);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtFirstName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtFirstName.setOpaque(false);
		txtFirstName.setText("  First Name...");
		txtFirstName.setBounds(65, 267, 303, 30);
		frmActivityEditUser.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtMiddleName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtMiddleName.setOpaque(false);
		txtMiddleName.setText("  Middle Name...");
		txtMiddleName.setBounds(65, 321, 303, 30);
		frmActivityEditUser.getContentPane().add(txtMiddleName);
		txtMiddleName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtLastName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtLastName.setOpaque(false);
		txtLastName.setText("  Last Name...");
		txtLastName.setBounds(65, 375, 303, 30);
		frmActivityEditUser.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		txtCollege = new JTextField();
		txtCollege.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtCollege.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtCollege.setOpaque(false);
		txtCollege.setText("  College...");
		txtCollege.setBounds(65, 427, 303, 30);
		frmActivityEditUser.getContentPane().add(txtCollege);
		txtCollege.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtEmail.setOpaque(false);
		txtEmail.setText("  Email...");
		txtEmail.setBounds(65, 478, 303, 30);
		frmActivityEditUser.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtPassword.setOpaque(false);
		txtPassword.setText("  Password...");
		txtPassword.setBounds(65, 530, 303, 30);
		frmActivityEditUser.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnFINISH = new JButton("FINISH");
		btnFINISH.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFINISH.setFont(new Font("Calibri", Font.BOLD, 30));
		btnFINISH.setBounds(99, 621, 230, 54);
		btnFINISH.setBackground(new Color(255, 69, 0));
		frmActivityEditUser.getContentPane().add(btnFINISH);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setOpaque(false);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.setBounds(135, 686, 150, 23);
		frmActivityEditUser.getContentPane().add(btnGoBack);
		
		
		//makes content of textfield to disappear when mouse
		txtFirstName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtFirstName.getText().equals("  First Name..."))
					txtFirstName.setText(null);
				if(txtMiddleName.getText().equals("")) {
					txtMiddleName.setText("  Middle Name...");
				}
				if(txtLastName.getText().equals("")) {
					txtLastName.setText("  Last Name...");
				}
				if(txtCollege.getText().equals("")) {
					txtCollege.setText("  College...");
				}
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});
		//makes content of textfield to disappear when mouse
		txtMiddleName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtMiddleName.getText().equals("  Middle Name..."))
					txtMiddleName.setText(null);
				if(txtFirstName.getText().equals("")) {
					txtFirstName.setText("  First Name...");
				}
				if(txtLastName.getText().equals("")) {
					txtLastName.setText("  Last Name...");
				}
				if(txtCollege.getText().equals("")) {
					txtCollege.setText("  College...");
				}
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});
		//makes content of textfield to disappear when mouse
		txtLastName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtLastName.getText().equals("  Last Name..."))
					txtLastName.setText(null);
				if(txtFirstName.getText().equals("")) {
					txtFirstName.setText("  First Name...");
				}
				if(txtMiddleName.getText().equals("")) {
					txtMiddleName.setText("  Middle Name...");
				}
				if(txtCollege.getText().equals("")) {
					txtCollege.setText("  College...");
				}
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});	
		//makes content of textfield to disappear when mouse
		txtCollege.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtCollege.getText().equals("  College..."))
					txtCollege.setText(null);
				if(txtFirstName.getText().equals("")) {
					txtFirstName.setText("  First Name...");
				}
				if(txtMiddleName.getText().equals("")) {
					txtMiddleName.setText("  Middle Name...");
				}
				if(txtLastName.getText().equals("")) {
					txtLastName.setText("  Last Name...");
				}
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});
		//makes content of textfield to disappear when mouse
		txtEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtEmail.getText().equals("  Email..."))
					txtEmail.setText(null);
				txtEmail.setForeground(Color.BLACK);
				txtEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
				if(txtFirstName.getText().equals("")) {
					txtFirstName.setText("  First Name...");
				}
				if(txtMiddleName.getText().equals("")) {
					txtMiddleName.setText("  Middle Name...");
				}
				if(txtLastName.getText().equals("")) {
					txtLastName.setText("  Last Name...");
				}
				if(txtCollege.getText().equals("")) {
					txtCollege.setText("  College...");
				}
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("  Password...");
				}
			}
		});	
		//makes content of textfield to disappear when mouse
		txtPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtPassword.getText().equals("  Password..."))
					txtPassword.setText(null);
				if(txtFirstName.getText().equals("")) {
					txtFirstName.setText("  First Name...");
				}
				if(txtMiddleName.getText().equals("")) {
					txtMiddleName.setText("  Middle Name...");
				}
				if(txtLastName.getText().equals("")) {
					txtLastName.setText("  Last Name...");
				}
				if(txtCollege.getText().equals("")) {
					txtCollege.setText("  College...");
				}
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("  Email...");
				}
			}
		});
		
		btnFINISH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//compare if existing
				String strFirstName1 = txtFirstName.getText().trim();
				String strMiddleName1 = txtMiddleName.getText().trim();
				String strLastName1 = txtLastName.getText().trim();
				String strCollege1 = txtCollege.getText().trim();
				String strEmail1 = txtEmail.getText().trim();
				String strPassword1 = txtPassword.getText().trim();
				String strDesc1 = txtDesc.getText();
				try {
					
					objSQLQuery.executeUpdate("update tblNamedata set "
							+ "strFirstName = "
							+ strFirstName1
							+ ", "
							+ "strMiddletName = "
							+ strMiddleName1
							+ ", "
							+ "strLasttName = "
							+ strLastName1
							+ ", "
							+ "strCollege = "
							+ strCollege1
							+ ", "
							+ "strEmail = "
							+ strEmail1
							+ ", "
							+ "strPassword = "
							+ strPassword1
							+ ", "
							+ "strNameDescription = "
							+ strDesc1
							//+ " where strNameID = "
							);
					JOptionPane.showMessageDialog(null,"Account successfully created");
						//GO TO LOGGING IN ACTIVITY
					MainActivity.ActivityUserProfile();
					frmActivityEditUser.dispose();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//GO TO LOGGING IN ACTIVITY
				MainActivity.ActivityUserProfile();
				frmActivityEditUser.dispose();
			}
		});	
	}
}
