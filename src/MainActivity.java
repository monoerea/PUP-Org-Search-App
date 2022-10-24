
public class MainActivity {
	
	public static void main(String[] args) {
		ActivityLoggingIn();
	}
	
	public static void ActivityCreateUser() {
		Thread ActivityCreateUser = new Thread(new ActivityCreateUser());
		ActivityCreateUser.start();
	} //public static void ActivityMain()

	public static void ActivityCreateOrg() {
		Thread ActivityCreateOrg = new Thread(new ActivityCreateOrg());
		ActivityCreateOrg.start();
	} //public static void ActivityCreateOrg()
	public static void ActivityDiscover() {
		Thread ActivityDiscover = new Thread(new ActivityDiscover());
		ActivityDiscover.start();
	} //public static void ActivityHomePage()
	
	public static void ActivityEditUser() {
		Thread ActivityEditUser = new Thread(new ActivityEditUser());
		ActivityEditUser.start();
	} //public static void ActivityHomePage()
	
	public static void ActivityEditOrg() {
		Thread ActivityEditOrg = new Thread(new ActivityEditOrg());
		ActivityEditOrg.start();
	} //public static void ActivityHomePage()
	
	public static void ActivityHomePage() {
		Thread ActivityHomePage = new Thread(new ActivityHomePage());
		ActivityHomePage.start();
	} //public static void ActivityHomePage()
	
	public static void ActivityLoggingIn() {
		Thread ActivityLoggingIn = new Thread(new ActivityLoggingIn());
		ActivityLoggingIn.start();
	} //public static void ActivityMain()
	
	
	public static void ActivityUserProfile() {
		Thread ActivityUserProfile = new Thread(new ActivityUserProfile());
		ActivityUserProfile.start();
	} //public static void ActivityUserProfile()
	
	public static void ActivityViewUser() {
		Thread ActivityViewUser = new Thread(new ActivityViewUser());
		ActivityViewUser.start();
	} //public static void ActivityViewUser()
	
	public static void ActivityViewOrg() {
		Thread ActivityViewOrg = new Thread(new ActivityViewOrg());
		ActivityViewOrg.start();
	} //public static void ActivityViewOrg()
	
	public static void SubActivityViewMembers() {
		Thread SubActivityViewMembers = new Thread(new SubActivityViewMembers());
		SubActivityViewMembers.start();
	} //public static void SubActivityViewMembers()
	
	public static void SubActivityViewPost() {
		Thread SubActivityViewPost = new Thread(new SubActivityViewPost());
		SubActivityViewPost.start();
	} //public static void SubActivityViewPost()
		
	public static void SubActivityCreatePost() {
		Thread SubActivityCreatePost = new Thread(new SubActivityCreatePost());
		SubActivityCreatePost.start();	
	} //public static void SubActivityCreatePost()
	
	


}
