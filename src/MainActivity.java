
public class MainActivity {
	
	public static void main(String[] args) {
		ActivityLoggingIn();
	}
	public static void ActivityLoggingIn() {
		Thread ActivityLoggingIn = new Thread(new ActivityLoggingIn());
		ActivityLoggingIn.start();
	} //public static void ActivityMain()
	public static void ActivityCreateUser() {
		Thread ActivityCreateUser = new Thread(new ActivityCreateUser());
		ActivityCreateUser.start();
	} //public static void ActivityMain()
	public static void ActivityHomePage() {
		Thread ActivityHomePage = new Thread(new ActivityHomePage());
		ActivityHomePage.start();
	} //public static void ActivityMain()


}
