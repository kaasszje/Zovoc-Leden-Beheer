package nl.fam_krijgsman.zovoc.view;

import javax.swing.*;
import java.awt.*;

public class BeheerScreenFrame extends JFrame {
    JLabel text, header;
    ImageIcon icon;
    String user;

    public BeheerScreenFrame(String user) {
        this.user = user.substring(0,1).toUpperCase() + user.substring(1).toLowerCase();
        text = new JLabel("Succesvol ingelogt");
        text.setBackground(Color.GREEN);

        header = new JLabel("Welkom " + this.user);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBackground(Color.GREEN);

        this.setLayout(new BorderLayout());
        add(text, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        try {
            icon = new ImageIcon(BeheerScreenFrame.class.getResource("/Images/Icon.PNG"));
        } catch (NullPointerException e) {
            icon = null;
        }
        this.setBackground(Color.GREEN);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setIconImage(icon.getImage());
        //Add elements to frame

        this.setSize(500,500);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
