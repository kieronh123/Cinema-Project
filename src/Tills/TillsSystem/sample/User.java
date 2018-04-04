package Tills.TillsSystem.sample;

public class User {
    private int User_ID;
    public String Username;
    public String Password;

    public User(){}

    @Override
    public String toString(){
        return User_ID + ", " + Username + ", " + Password;

    }
}
