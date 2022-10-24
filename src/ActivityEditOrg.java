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

public class ActivityEditOrg implements Runnable {

	//GUI Variables 
	private JFrame frmActivityEditOrg;
	private JLabel lblPUPLogo;
	private JLabel lblTitle;
	private JTextField txtOrganizationName;
	private JTextField txtOrganizationType;
	private JTextField txtOrganizationEmail;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String OrganizationEmail,Password;//put to main
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
			ActivityEditOrg window = new ActivityEditOrg();
			window.frmActivityEditOrg.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ActivityEditOrg() {
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
		
		frmActivityEditOrg = new JFrame();
		//frmActivityEditOrg.getContentPane().setBackground(new Color(176, 224, 230));
		frmActivityEditOrg.setTitle("PUP Organization Search");
		frmActivityEditOrg.setBounds(400, 0, 455,768);
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityEditOrg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityEditOrg.getContentPane().setLayout(null);
		frmActivityEditOrg.setContentPane(new JLabel(new ImageIcon(ActivityHomePage.class.getResource("/images/background1.png"))));		
		
		lblPUPLogo = new JLabel("Insert PUP Logo Here");
		lblPUPLogo.setBounds(145, 21, 150, 150);
		lblPUPLogo.setIcon(new ImageIcon(ActivityEditOrg.class.getResource("/images/puplogo1.png")));
		frmActivityEditOrg.getContentPane().add(lblPUPLogo);
		
		lblTitle = new JLabel("PUP Organization Search");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(31, 191, 377, 54);
		frmActivityEditOrg.getContentPane().add(lblTitle);
		
		txtOrganizationName = new JTextField();
		txtOrganizationName.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationName.setOpaque(false);
		txtOrganizationName.setText("  Organization Name...");
		txtOrganizationName.setBounds(65, 267, 303, 30);
		frmActivityEditOrg.getContentPane().add(txtOrganizationName);
		txtOrganizationName.setColumns(10);
		
		txtOrganizationType = new JTextField();
		txtOrganizationType.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationType.setOpaque(false);
		txtOrganizationType.setText("  Organization Type...");
		txtOrganizationType.setBounds(65, 321, 303, 30);
		frmActivityEditOrg.getContentPane().add(txtOrganizationType);
		txtOrganizationType.setColumns(10);
		
		txtOrganizationEmail = new JTextField();
		txtOrganizationEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationEmail.setOpaque(false);
		txtOrganizationEmail.setText("  Organization Email...");
		txtOrganizationEmail.setBounds(65, 375, 303, 30);
		frmActivityEditOrg.getContentPane().add(txtOrganizationEmail);
		txtOrganizationEmail.setColumns(10);
				
		txtDesc.setLineWrap(true);
		txtDesc.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtDesc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtDesc.setOpaque(false);
		txtDesc.setRows(2);
		txtDesc.setText("  Add Organization Description...");
		txtDesc.setBounds(65, 427, 303, 60);
		frmActivityEditOrg.getContentPane().add(txtDesc);
		txtDesc.setColumns(10);
		
		JButton btnFINISH = new JButton("FINISH");
		btnFINISH.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFINISH.setFont(new Font("Calibri", Font.BOLD, 30));
		btnFINISH.setBounds(99, 621, 230, 54);
		btnFINISH.setBackground(new Color(255, 69, 0));
		frmActivityEditOrg.getContentPane().add(btnFINISH);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setOpaque(false);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.setBounds(135, 686, 150, 23);
		frmActivityEditOrg.getContentPane().add(btnGoBack);
		
		//makes content of textfield to disappear when mouse
		txtOrganizationName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtOrganizationName.getText().equals("  Organization Name..."))
					txtOrganizationName.setText(null);
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  Organization Type...");
				}
				if(txtOrganizationEmail.getText().equals("")) {
					txtOrganizationEmail.setText("  Organization Email...");
				}
				if(txtDesc.getText().equals("")) {
					txtDesc.setText("  Add Organization Description...");
				}
			}
		});
		//makes content of textfield to disappear when mouse
		txtOrganizationType.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtOrganizationType.getText().equals("  Organization Type..."))
					txtOrganizationType.setText(null);
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationEmail.getText().equals("")) {
					txtOrganizationEmail.setText("  Organization Email...");
				}
				if(txtDesc.getText().equals("")) {
					txtDesc.setText("  Add Organization Description...");
				}
			}
		});
		//makes content of textfield to disappear when mouse
		txtOrganizationEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				if(txtOrganizationEmail.getText().equals("  Organization Email..."))
					txtOrganizationEmail.setText(null);
				txtOrganizationEmail.setForeground(Color.BLACK);
				txtOrganizationEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  Organization Type...");
				}
				if(txtDesc.getText().equals("")) {
					txtDesc.setText("  Add Organization Description...");
				}
			}
		});	
		//makes content of textfield to disappear when mouse
		txtDesc.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent objME) {
				if(txtDesc.getText().equals("  Add Organization Description..."))
					txtDesc.setText(null);
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  Organization Type...");
				}
				if(txtOrganizationEmail.getText().equals("")) {
					txtOrganizationEmail.setText("  Organization Email...");
				}
			}
		});
		
		btnFINISH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//compare if existing
				String strOrganizationName1 = txtOrganizationName.getText().trim();
				String strOrganizationType1 = txtOrganizationType.getText().trim();
				String strOrganizationEmail1 = txtOrganizationEmail.getText().trim();
				String strDesc1 = txtDesc.getText();
				try {
					
					objSQLQuery.executeUpdate("update tblorganizationdata set "
							+ "strOrganizationName = "
							+ strOrganizationName1
							+ ", "
							+ "strOrganizationType = "
							+ strOrganizationType1
							+ ", "
							+ "strOrganizationEmail = "
							+ strOrganizationEmail1
							+ ", "
							+ "strOrganizationDescription = "
							+ strDesc1
							//+ " where strOrganizationID = "
							);
					JOptionPane.showMessageDialog(null,"Account successfully created");
						//GO TO LOGGING IN ACTIVITY
					MainActivity.ActivityHomePage();
					frmActivityEditOrg.dispose();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//GO TO LOGGING IN ACTIVITY
				MainActivity.ActivityHomePage();
				frmActivityEditOrg.dispose();
			}
		});	
	}
}
