package nl.fam_krijgsman.zovoc.testdata;

public class UserLoginTest {
    static Users users = new Users();

    public static void main(String[] args) {
        // test 1 niet bestaande gebruiker
        String user = "test";
        String password = "test";
        System.out.println("Expect fout:");
        checkLogin(user, password);
        System.out.println();

        user = "martijn";
        password = "martijn";
        System.out.println("Expect fout:");
        checkLogin(user, password);
        System.out.println();

        user = "martijn";
        password = "test";
        System.out.println("Expect goed:");
        checkLogin(user, password);
        System.out.println();

        user = "zovocuser";
        password = "zovocpassword";
        System.out.println("Expect goed:");
        checkLogin(user, password);
        System.out.println();

    }

    public static void checkLogin(String user, String password) {
        if (users.checkLogin(user, password)) {
            System.out.println("goed");
        } else {
            System.out.println("fout");
        }
    }
}
