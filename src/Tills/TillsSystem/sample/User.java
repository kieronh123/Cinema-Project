package Tills.TillsSystem.sample;

import java.security.MessageDigest;

public class User {
    private int User_ID;
    public String Username;
    public String Password;

    public User() {}

    public String hashPassword() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.Password = "Hello";
        String hash = user.hashPassword();
        System.out.println(hash);
    }

    @Override
    public String toString() {
        return User_ID + ", " + Username + ", " + Password;

    }
}
