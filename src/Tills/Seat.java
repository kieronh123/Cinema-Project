package Tills;

public class Seat {
    public int Screening_ID;
    public int Row_Num;
    public int Column_Num;

    public Seat(int screening_ID, int row_Num, int column_Num) {
        Screening_ID = screening_ID;
        Row_Num = row_Num;
        Column_Num = column_Num;
    }

    public String toString() {
        return "Seats{" +
                "Screening_ID=" + String.valueOf(Screening_ID) +
                ", column=" + Column_Num +
                ", row=" + Row_Num + '\'' +
                '}';
    }
}
