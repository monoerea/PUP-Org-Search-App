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

public class ActivityDiscover {

	//GUI Variables 
	private JFrame frmActivityDiscover;
	
	//Connection Variables
	private Connection objConn;
	private boolean boolConn2Db = false;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	
	//User Variables
	String Email,Password;//put to main
	private JButton btnSearch;
	private JButton btnLogout;
	private JTextField txtSearch;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityDiscover window = new ActivityDiscover();
					window.frmActivityDiscover.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ActivityDiscover() {
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
		frmActivityDiscover = new JFrame();
		frmActivityDiscover.getContentPane().setBackground(new Color(176, 224, 230));
		//frmActivityDiscover.setContentPane(new JLabel(new ImageIcon(ActivityDiscover.class.getResource("/images/background.png"))));		
		frmActivityDiscover.setTitle("PUP Organization Search");
		frmActivityDiscover.setBounds(0, 0, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3),(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		System.out.println(("Hello"+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3) + " " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));//to know screen size, mine 455.3333333333333 768.0
		frmActivityDiscover.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActivityDiscover.getContentPane().setLayout(null);
		
		btnSearch = new JButton(new ImageIcon(ActivityDiscover.class.getResource("/images/magnifying.png")));
		//btnSearch.setSelectedIcon(new ImageIcon(ActivityDiscover.class.getResource("/images/magnifying.png")));
		btnSearch.setBounds(10, 11, 50, 50);
		frmActivityDiscover.getContentPane().add(btnSearch);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 71, 419, 618);
		/*
		 * contentPane.add(scrollPane);
			JList list = new JList(objPosts.toArray());
			scrollPane.setViewportView(list);	
		 */
		frmActivityDiscover.getContentPane().add(scrollPane);
		
		btnLogout = new JButton("LOGOUT");
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogout.setForeground(new Color(255, 69, 0));
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBounds(145, 695, 150, 23);
		frmActivityDiscover.getContentPane().add(btnLogout);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLUE));
		txtSearch.setText("  Search...");
		txtSearch.setBounds(70, 11, 359, 50);
		frmActivityDiscover.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		txtSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent objME) {
				txtSearch.setText(null);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO VIEW ORG/USER ACTIVITY
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//GO TO LOGGINGIN ACTIVITY
			}
		});
		
	}
}
