package nl.fam_krijgsman.zovoctester;

import nl.fam_krijgsman.zovoctester.model.UserLoginModelTest;
import nl.fam_krijgsman.zovoctester.model.generic.HelperTester;

public class Tester {
    public static void main(String[] args) {
        System.out.println("UserLoginModelTest:");
        UserLoginModelTest.test();
        System.out.println();
        HelperTester.emailCheck();
        System.out.println();
        HelperTester.phoneCheck();
    }
}
