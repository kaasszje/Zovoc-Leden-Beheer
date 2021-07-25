package nl.fam_krijgsman.zovoc;

import nl.fam_krijgsman.zovoc.mvc.UserLogin;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(String[] args) {
        invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        UserLogin.startUserLogin();
    }
}
