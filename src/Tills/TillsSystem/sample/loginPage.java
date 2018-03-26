package Tills.TillsSystem.sample;

import JSON.JSON;

/**
 * Created by sc16km on 26/03/18.
 */
public class loginPage {

    public static void main(String[] args) throws Exception {
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            response = harness.sendGet("users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        User[] users = JSON.usersFromJson(response.toString());
        for(User user: users){
            System.out.println(user.toString());
        }
    }
}
