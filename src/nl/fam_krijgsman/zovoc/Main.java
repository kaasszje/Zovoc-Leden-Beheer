package nl.fam_krijgsman.zovoc;

import nl.fam_krijgsman.zovoc.gui.LoginScreenFrame;
import nl.fam_krijgsman.zovoc.testdata.Users;

public class Main {

    public static void main(String[] args) {
        Users users = new Users();

        new LoginScreenFrame(users);



    }

}
