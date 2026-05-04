package api.input;

import java.util.Scanner;

public class UserResponse {

    public static String ask(String dialog) {
        Scanner input = new Scanner(System.in);
        System.out.println(dialog);
        System.out.print("[INPUT] > ");
        return input.next();
    }
}
