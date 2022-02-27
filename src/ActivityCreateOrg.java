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

public class ActivityCreateOrg {

	//GUI Variables 
	private JFrame frmActivityCreateOrg;
	private JLabel lblPUPLogo;
	private JLabel lblTitle;
	private JTextField txtOrganizationID;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityCreateOrg window = new ActivityCreateOrg();
					window.frmActivityCreateOrg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ActivityCreateOrg() {
		try {
			//connecting to server
			String strDriver = "com.mysql.cj.jdbc.Driver";
	        String strConn = "jdbc:mysql://localhost:3306/dbpuporgsearch";
	        String strUser = "root";
	        String strPass = "DerTeufelunterhandler12"; // TODO
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
		
		frmActivityCreateOrg = new JFrame();
		//frmActivityCreateOrg.getContentPane().setBackground(new Color(176, 224, 230));
		frmActivityCreateOrg.setTitle("PUP Organization Search");
		frmActivityCreateOrg.setBounds(0, 0, 455,768);
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityCreateOrg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityCreateOrg.getContentPane().setLayout(null);
		frmActivityCreateOrg.setContentPane(new JLabel(new ImageIcon(ActivityHomePage.class.getResource("/images/background1.png"))));		
		
		lblPUPLogo = new JLabel("Insert PUP Logo Here");
		lblPUPLogo.setBounds(145, 21, 150, 150);
		lblPUPLogo.setIcon(new ImageIcon(ActivityCreateOrg.class.getResource("/images/puplogo1.png")));
		frmActivityCreateOrg.getContentPane().add(lblPUPLogo);
		
		lblTitle = new JLabel("PUP Organization Search");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setForeground(new Color(197, 90, 17));
		lblTitle.setBounds(31, 191, 377, 54);
		frmActivityCreateOrg.getContentPane().add(lblTitle);
		
		txtOrganizationID = new JTextField();
		txtOrganizationID.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationID.setOpaque(false);
		txtOrganizationID.setText("  Organization ID...");
		txtOrganizationID.setBounds(65, 267, 303, 30);
		frmActivityCreateOrg.getContentPane().add(txtOrganizationID);
		txtOrganizationID.setColumns(10);
		
		txtOrganizationName = new JTextField();
		txtOrganizationName.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationName.setOpaque(false);
		txtOrganizationName.setText("  Organization Name...");
		txtOrganizationName.setBounds(65, 321, 303, 30);
		frmActivityCreateOrg.getContentPane().add(txtOrganizationName);
		txtOrganizationName.setColumns(10);
		
		txtOrganizationType = new JTextField();
		txtOrganizationType.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationType.setOpaque(false);
		txtOrganizationType.setText("  Organization Type...");
		txtOrganizationType.setBounds(65, 375, 303, 30);
		frmActivityCreateOrg.getContentPane().add(txtOrganizationType);
		txtOrganizationType.setColumns(10);
		
		txtOrganizationEmail = new JTextField();
		txtOrganizationEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtOrganizationEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtOrganizationEmail.setOpaque(false);
		txtOrganizationEmail.setText("  Organization Email...");
		txtOrganizationEmail.setBounds(65, 427, 303, 30);
		frmActivityCreateOrg.getContentPane().add(txtOrganizationEmail);
		txtOrganizationEmail.setColumns(10);
				
		txtDesc.setLineWrap(true);
		txtDesc.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtDesc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtDesc.setOpaque(false);
		txtDesc.setRows(2);
		txtDesc.setText("  Add Organization Description...");
		txtDesc.setBounds(65, 478, 303, 60);
		frmActivityCreateOrg.getContentPane().add(txtDesc);
		txtDesc.setColumns(10);
		
		JButton btnFINISH = new JButton("FINISH");
		btnFINISH.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFINISH.setFont(new Font("Calibri", Font.BOLD, 30));
		btnFINISH.setBounds(99, 621, 230, 54);
		btnFINISH.setBackground(new Color(255, 69, 0));
		frmActivityCreateOrg.getContentPane().add(btnFINISH);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setOpaque(false);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.setBounds(135, 686, 150, 23);
		frmActivityCreateOrg.getContentPane().add(btnGoBack);
		
		//makes content of textfield to disappear when mouse
		txtOrganizationID.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				txtOrganizationID.setText(null);
				txtOrganizationID.setForeground(Color.BLACK);
				txtOrganizationID.setFont(new Font("Calibri", Font.PLAIN, 20));
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  OrganizationType...");
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
		txtOrganizationName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				txtOrganizationName.setText(null);
				if(txtOrganizationID.getText().equals("")) {
					txtOrganizationID.setText("  Organization ID...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  OrganizationType...");
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
				txtOrganizationType.setText(null);
				if(txtOrganizationID.getText().equals("")) {
					txtOrganizationID.setText("  Organization ID...");
				}
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
				txtOrganizationEmail.setText(null);
				txtOrganizationEmail.setForeground(Color.BLACK);
				txtOrganizationEmail.setFont(new Font("Calibri", Font.PLAIN, 20));
				if(txtOrganizationID.getText().equals("")) {
					txtOrganizationID.setText("  Organization ID...");
				}
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  OrganizationType...");
				}
				if(txtDesc.getText().equals("")) {
					txtDesc.setText("  Add Organization Description...");
				}
			}
		});	
		//makes content of textfield to disappear when mouse
		txtDesc.addMouseListener(new MouseAdapter( ) {
			public void mouseClicked(MouseEvent objME) {
				txtDesc.setText(null);
				if(txtOrganizationID.getText().equals("")) {
					txtOrganizationID.setText("  Organization ID...");
				}
				if(txtOrganizationName.getText().equals("")) {
					txtOrganizationName.setText("  Organization Name...");
				}
				if(txtOrganizationType.getText().equals("")) {
					txtOrganizationType.setText("  OrganizationType...");
				}
				if(txtOrganizationEmail.getText().equals("")) {
					txtOrganizationEmail.setText("  Organization Email...");
				}
			}
		});
		
		btnFINISH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//compare if existing
				String strOrganizationID1 = txtOrganizationID.getText().trim();
				String strOrganizationName1 = txtOrganizationName.getText().trim();
				String strOrganizationType1 = txtOrganizationType.getText().trim();
				String strOrganizationEmail1 = txtOrganizationEmail.getText().trim();
				String strDesc1 = txtDesc.getText();
				boolean boolError = false;
				try {
					objResultSet = objSQLQuery.executeQuery("select * from tblorganizationdata");
					while(objResultSet.next()) {
						if(strOrganizationID1.contentEquals(objResultSet.getString("strOrganizationID"))) {
							JOptionPane.showMessageDialog(null,"Organization ID already in used");
							txtOrganizationID.setText("  !Organization ID!");
							txtOrganizationID.setForeground(Color.RED);
							txtOrganizationID.setFont(new Font("Calibri", Font.BOLD, 20));
							boolError=true;
						}
						if(strOrganizationEmail1.contentEquals(objResultSet.getString("strOrganizationEmail"))) {
							JOptionPane.showMessageDialog(null,"OrganizationEmail already in used");
							txtOrganizationEmail.setText("  !OrganizationEmail!");
							txtOrganizationEmail.setForeground(Color.RED);
							txtOrganizationEmail.setFont(new Font("Calibri", Font.BOLD, 20));
							boolError=true;
						}
					}
					if(boolError==false) {
						objSQLQuery.executeUpdate("insert into tblorganizationdata values"
								+ "("
								+ "'"
								+ strOrganizationID1
								+ "'"
								+ ","
								+ "'"
								+ strOrganizationName1
								+ "'"
								+ ","
								+ "'"
								+ strOrganizationType1
								+ "'"
								+ ","
								+ "'"
								+ strOrganizationEmail1
								+ "'"
								+ ","
								+ "'"
								+ strDesc1
								+ "'"
								+ ")"
								);
						JOptionPane.showMessageDialog(null,"Account successfully created");
						//GO TO LOGGING IN ACTIVITY
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				//GO TO LOGGING IN ACTIVITY
			}
		});	
	}
}
