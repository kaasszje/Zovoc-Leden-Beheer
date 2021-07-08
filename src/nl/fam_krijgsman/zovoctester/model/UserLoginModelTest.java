package nl.fam_krijgsman.zovoctester.model;

import nl.fam_krijgsman.zovoc.model.UserLoginModel;

public class UserLoginModelTest {
    static UserLoginModel userLoginModel = new UserLoginModel();

    public static void test() {
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

    private static void checkLogin(String user, String password) {
        if (userLoginModel.isValidLogin(user, password)) {
            System.out.println(user + " : " + "goed");
        } else {
            System.out.println(user + " : " + "fout");
        }
    }
}
