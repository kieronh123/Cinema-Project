package Tills.TillsSystem.sample;

public class User {
    private int User_ID;
    private String Username;
    private String Password;

    public User(){}

    @Override
    public String toString(){
        return User_ID + ", " + Username + ", " + Password;

    }
}
