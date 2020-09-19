package group3.persistance;

import java.util.Random;

public class RandomID {
    public static String random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String random = "";


        int length = 5;

        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int j = 0; j < text.length; j++) {
            random += text[j];
        }
        System.out.println(random);
        return random;
    }

    public static String randomFL() {
        String characters = "0123456789";
        String random = "";


        int length = 4;

        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int j = 0; j < text.length; j++) {
            random += text[j];
        }
        System.out.println(random);
        return random;
    }

}
