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

public class ActivityEditOrg {

	//GUI Variables 
	private JFrame frmActivityViewOrg;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnProfile;
	private JButton btnBack;
	JButton btnSave;
	JButton btnDeletePost;
	private JLabel lblOrganizationName;
	private JSeparator separator;
	private ScrollPane scrollPane;
	private JLabel lblOrgsJoined;
	private JTextField[] textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityEditOrg window = new ActivityEditOrg();
					window.frmActivityViewOrg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frmActivityViewOrg = new JFrame();
		frmActivityViewOrg.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityViewOrg.setContentPane(new JLabel(new ImageIcon(ActivityViewOrg.class.getResource("/images/background.png"))));		
		frmActivityViewOrg.setTitle("PUP Organization Search");
		frmActivityViewOrg.setBounds(0, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityViewOrg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityViewOrg.getContentPane().setLayout(null);
		
		btnProfile = new JButton(new ImageIcon(ActivityEditOrg.class.getResource("/images/user.png")));
		btnProfile.setBounds(195, 10, 100, 100);
		frmActivityViewOrg.getContentPane().add(btnProfile);
		
		btnBack = new JButton("BACK");
		btnBack.setOpaque(false);
		btnBack.setForeground(new Color(255, 69, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(174, 695, 150, 23);
		frmActivityViewOrg.getContentPane().add(btnBack);
		
		lblOrganizationName = new JLabel("Organization Name");
		lblOrganizationName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblOrganizationName.setBounds(33, 146, 309, 31);
		frmActivityViewOrg.getContentPane().add(lblOrganizationName);
		
		separator = new JSeparator();
		separator.setBounds(10, 429, 448, 31);
		frmActivityViewOrg.getContentPane().add(separator);
		
		scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 459, 448, 175);
		/*
		 * contentPane.add(scrollPane);
			JList list = new JList(objPosts.toArray());
			scrollPane.setViewportView(list);	
		 */
		frmActivityViewOrg.getContentPane().add(scrollPane);
		frmActivityViewOrg.getContentPane().add(scrollPane);
		
		lblOrgsJoined = new JLabel("POSTS");
		lblOrgsJoined.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOrgsJoined.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrgsJoined.setBounds(10, 429, 319, 31);
		frmActivityViewOrg.getContentPane().add(lblOrgsJoined);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDescription.setBounds(33, 269, 309, 31);
		frmActivityViewOrg.getContentPane().add(lblDescription);
		
		JLabel lblOrganizationType = new JLabel("Organization Type");
		lblOrganizationType.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblOrganizationType.setBounds(33, 187, 159, 31);
		frmActivityViewOrg.getContentPane().add(lblOrganizationType);
		
		btnSave = new JButton("SAVE");
		btnSave.setOpaque(false);
		btnSave.setForeground(new Color(255, 69, 0));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setContentAreaFilled(false);
		btnSave.setBorderPainted(false);
		btnSave.setBounds(174, 374, 150, 23);
		frmActivityViewOrg.getContentPane().add(btnSave);
		
		JButton btnCreatePost = new JButton("Create Post");
		btnCreatePost.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreatePost.setBounds(40, 652, 189, 37);
		frmActivityViewOrg.getContentPane().add(btnCreatePost);
		
		JLabel lblOrganizationEmail = new JLabel("Organization Email");
		lblOrganizationEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblOrganizationEmail.setBounds(33, 228, 309, 31);
		frmActivityViewOrg.getContentPane().add(lblOrganizationEmail);
		
		btnDeletePost = new JButton("Delete Post");
		btnDeletePost.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeletePost.setBounds(269, 652, 189, 37);
		frmActivityViewOrg.getContentPane().add(btnDeletePost);
		
		textField = new JTextField[4];
		
		int y=115;
		for(int i=0;i<4;i++) {
		textField[i] = new JTextField();
		textField[i].setBounds(258, y+=40, 159, 23);
		frmActivityViewOrg.getContentPane().add(textField[i]);
		textField[i].setColumns(10);
		}
		textField[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textField[0].setText(null);
				if(textField[1].getText().equals("")) {
					textField[1].setText("  Organization Type...");
				}
				if(textField[2].getText().equals("")) {
					textField[2].setText("  Organization Email...");
				}
				if(textField[3].getText().equals("")) {
					textField[3].setText("  Description...");
				}
			}
		});
		textField[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textField[1].setText(null);
				if(textField[0].getText().equals("")) {
					textField[0].setText("  Organization Name...");
				}
				if(textField[2].getText().equals("")) {
					textField[2].setText("  Organization Email...");
				}
				if(textField[3].getText().equals("")) {
					textField[3].setText("  Description...");
				}
			}
		});
		textField[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textField[2].setText(null);
				if(textField[0].getText().equals("")) {
					textField[0].setText("  Organization Name...");
				}
				if(textField[1].getText().equals("")) {
					textField[1].setText("  Organization Type...");
				}
				if(textField[3].getText().equals("")) {
					textField[3].setText("  Description...");
				}
			}
		});
		textField[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				textField[3].setText(null);
				if(textField[0].getText().equals("")) {
					textField[0].setText("  Organization Name...");
				}
				if(textField[1].getText().equals("")) {
					textField[1].setText("  Organization Type...");
				}
				if(textField[2].getText().equals("")) {
					textField[2].setText("  Organization Email...");
				}
			}
		});
	//btn add action
		/*
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strOrganizationName1 = textField[0].getText().trim();
				String strOrganizationType1 = textField[1].getText().trim();
				String strOrganizationEmail1 = textField[2].getText().trim();
				String strDesc1 = textField[3].getText();
				}

					try {
						objResultSet = objSQLQuery.executeQuery("select * from tblorganizationdata");
						while(objResultSet.next()) {
							if(/*strOrganizationID*//*.contentEquals(objResultSet.getString("strOrganizationID"))) {
								/*
								objSQLQuery.executeUpdate("UPDATE tblorganizationdata"
										+ "SET"
										+ "strOrganizationName = 'strOrganizationName1'"
										+", strOrganizationType = 'strOrganizationType1'"
										+", strOrganizationEmail = 'strOrganizationEmail1'"
										+", strOrganizationDescription = 'strOrganizationDescription'"
										+"WHERE strOrganizationID ='strOrganizationID1';"
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
					//GO TO  VIEW ORG IN ACTIVITY
				}
			});
			btnDeletePost.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent objAE) {
					//deletion of post
				}
			});*/
		}
	}
