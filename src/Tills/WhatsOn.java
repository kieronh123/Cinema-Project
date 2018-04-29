package Tills;

/** Class representing a WhatsOn
 */
public class WhatsOn {
    private int Screening_ID;
    private int Movie_ID;
    private int Screen_ID;
    private String Start_Time;

    public WhatsOn(int screening_ID, int movie_ID, int screen_ID, String start_Time) {
        Screening_ID = screening_ID;
        Movie_ID = movie_ID;
        Screen_ID = screen_ID;
        Start_Time = start_Time;
    }

    public int getScreening_ID(){
      return Screening_ID;
    }

    public int getMovie_ID() {
        return Movie_ID;
    }

    public int getScreen_ID() {
        return Screen_ID;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public String toString() {
        return "WhatsOn{" +
                "Screening_ID=" + Screening_ID +
                ", Movie_ID=" + Movie_ID +
                ", Screen_ID=" + Screen_ID +
                ", Start_Time='" + Start_Time + '\'' +
                '}';
    }
}
