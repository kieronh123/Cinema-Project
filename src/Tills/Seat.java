package Tills;

public class Seat {
    public int Screening_ID;
    public int Row_Num;
    public int Column_Num;

    public Seat(){}


    public String toString() {
        return "Seats{" +
                "Screening_ID=" + String.valueOf(Screening_ID) +
                ", column=" + Column_Num +
                ", row=" + Row_Num + '\'' +
                '}';
    }
}
