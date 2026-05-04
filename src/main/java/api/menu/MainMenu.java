package api.menu;

import api.input.UserResponse;

public class MainMenu {
    public static void show(){
        boolean isComplete = false;
        while(!isComplete) {
            System.out.println("Welcome to the Store Main Menu");
            System.out.println("----OPTIONS----");
            System.out.println("1. User Menu | 2. Product Menu | 3. Cart Menu");
            String response = UserResponse.ask("Where would you like to go? Enter a number");
            try {
                int res = Integer.parseInt(response);
                switch (res){
                    case 1:
                        UserMenu.show();
                        isComplete = true;
                        break;
                    case 2:
                        ProductMenu.show();
                        isComplete = true;
                        break;
                    case 3:
                        CartMenu.show();
                        isComplete = true;
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException _){}
        }
    }
}
