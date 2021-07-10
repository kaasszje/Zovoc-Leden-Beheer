package nl.fam_krijgsman.zovoc.controller;

import nl.fam_krijgsman.zovoc.MVC.MVCBeheer;
import nl.fam_krijgsman.zovoc.model.UserLoginModel;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import nl.fam_krijgsman.zovoc.view.UserLoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserLoginController {
    private final String USERLOGINTEXT = "Vul hier uw gebruikersnaam in.";
    private UserLoginView userLoginView;
    private UserLoginModel userLoginModel;

    public UserLoginController(UserLoginView userLoginView, UserLoginModel userLoginModel) {
        this.userLoginView = userLoginView;
        this.userLoginModel = userLoginModel;

        //fill test data
        UserLoginData.addTestUsers(userLoginModel);

        this.userLoginView.setUserField(USERLOGINTEXT);
        this.userLoginView.loginActionListener(new UserLoginActionListener());
        this.userLoginView.userTextFocusListener(new UserLoginFocusListener());
    }

    class UserLoginFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            userLoginView.setUserField("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (userLoginView.getUserField().equals("")) {
                userLoginView.setUserField(USERLOGINTEXT);
            }
        }
    }

    class UserLoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == userLoginView.getLoginButton()) {
                if (userLoginModel.isValidLogin(userLoginView.getUserField(), userLoginView.getPassField())) {
                    userLoginView.dispose();
                    MVCBeheer.startBeheer(userLoginView.getUserField());
                } else {
                    userLoginView.displayErrorMessage("User and / or password are in correct.");
                    userLoginView.setUserField("");
                    userLoginView.setPassField("");
                    userLoginView.focusUserField();
                }
            }
        }
    }
}
