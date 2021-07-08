package nl.fam_krijgsman.zovoc.generic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    private static final String verenigingNaam = "Zovoc";

    public static boolean checkEmail(String email) {
        //email regex:
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();

    }

    public static boolean checkPhoneNumber(String phone) {
        String regex = "^((\\+|00(\\s|\\s?-\\s?)?)31(\\s|\\s?-\\s?)?(\\(0\\)[\\-\\s]?)?|0)[1-9]((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static String getVerenigingNaam() {
        return Helper.verenigingNaam;
    }
}
