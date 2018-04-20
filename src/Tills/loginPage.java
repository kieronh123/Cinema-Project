package Tills;

import JSON.JSON;

public class loginPage {
    public String UsernameLogIn;
    public String PasswordLogIn;

    public static boolean loginButton(String username, String password) {
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            response = harness.sendGet("users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        User[] users = JSON.usersFromJson(response.toString());
        for (User user : users) {
            if (user.Username.equals(username) && (user.Password.equals(password)) ){
                System.out.println(user.toString());
                return true;
            }
        }
        return false;
    }
}
