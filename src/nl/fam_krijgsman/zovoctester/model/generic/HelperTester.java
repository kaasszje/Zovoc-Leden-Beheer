package nl.fam_krijgsman.zovoctester.model.generic;

import java.util.ArrayList;
import java.util.List;

public class HelperTester {
    public static void emailCheck() {
        System.out.println("Email checks:");
        List<String> emails = new ArrayList<>();
        //Valid emails
        emails.add("user@domain.com");
        emails.add("user@domain.co.in");
        emails.add("user.name@domain.com");
        emails.add("user_name@domain.com");
        emails.add("username@yahoo.corporate.in");

        System.out.println("All should be valid:");
        for (String email : emails) {
            System.out.println(email + " : " + nl.fam_krijgsman.zovoc.generic.Helper.checkEmail(email));
        }

        System.out.println();
        emails = new ArrayList<>();

        //Invalid emails
        emails.add(".username@yahoo.com");
        emails.add("username@yahoo.com.");
        emails.add("username@yahoo..com");
        emails.add("username@yahoo.c");
        emails.add("username@yahoo.corporate");

        System.out.println("All should be invalid:");
        for (String email : emails) {
            System.out.println(email + " : " + nl.fam_krijgsman.zovoc.generic.Helper.checkEmail(email));
        }

    }

    public static void phoneCheck() {
        List<String> phoneNumbers = new ArrayList<>();
        //Local notation:
        phoneNumbers.add("0101234567");
        phoneNumbers.add("010-1234567");
        phoneNumbers.add("010 - 123 45 67");
        phoneNumbers.add("010 1234 567");
        phoneNumbers.add("06-12345678");
        phoneNumbers.add("06 123 456 78");
        phoneNumbers.add("0111-123456");
        phoneNumbers.add("0111 123456");

        System.out.println("All local should be valid:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber + " : " + nl.fam_krijgsman.zovoc.generic.Helper.checkPhoneNumber(phoneNumber));
        }

        System.out.println();
        phoneNumbers = new ArrayList<>();

        //International notation:
        phoneNumbers.add("+31101234567");
        phoneNumbers.add("0031101234567");
        phoneNumbers.add("+31(0) 10123 4567");
        phoneNumbers.add("+3110-1234567");
        phoneNumbers.add("003110 1234567");
        phoneNumbers.add("+316 123 456 78");
        phoneNumbers.add("+31(0)6 123 45678");
        phoneNumbers.add("+31111-123456");
        phoneNumbers.add("0031111-123456");

        System.out.println("All international should be valid:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber + " : " + nl.fam_krijgsman.zovoc.generic.Helper.checkPhoneNumber(phoneNumber));
        }
        System.out.println();
        phoneNumbers = new ArrayList<>();

        //Invalid
        phoneNumbers.add("06-1234-5678"); //An extra dash is not allowed
        phoneNumbers.add("06 123456789"); //To long
        phoneNumbers.add("06 1234567"); //To short
        phoneNumbers.add("+31(06) 123 45678"); //Invalid optional declaration
        phoneNumbers.add("1234567"); //(without regional number)

        phoneNumbers.add("112"); //(emergency number)");
        phoneNumbers.add("0900-1234"); //(paid service numbers)
        phoneNumbers.add("0906-12345"); //(paid service numbers)");
        phoneNumbers.add("0800-5555");
        System.out.println("All should be invalid:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber + " : " + nl.fam_krijgsman.zovoc.generic.Helper.checkPhoneNumber(phoneNumber));
        }
    }
}
