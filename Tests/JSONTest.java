import JSON.*;
import Tills.*;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class JSONTest {
    @Test
    public void moviesFromJsonTest() {
        String moviesString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}, {\"Movie_Runtime\": 8400, \"Movie_Name\": \"Superman: Man of Steel\", \"Movie_Image\": \"image_url2\", \"Movie_Rating\": \"15\", \"Movie_Info\": \"Is it a bird, is it a plane? No its Superman!\", \"Movie_ID\": 2}]";
        Movie[] movies = JSON.moviesFromJson(moviesString);
        String[] stringArray = {movies[0].toString(), movies[1].toString()};

        assertThat(stringArray[0], is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
        assertThat(stringArray[1], is((new Movie(2, "Superman: Man of Steel", "15", "8400", "Is it a bird, is it a plane? No its Superman!", "image_url2")).toString()));
    }

    @Test
    public void movieFromJsonTest() {
        String movieString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}]";
        Movie movie = JSON.movieFromJson(movieString);
        String string = movie.toString();

        assertThat(string, is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
    }

    @Test
    public void MoviesToJsonTest() {
        Movie movie1 = new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "img_url1");
        Movie movie2 = new Movie(2, "Superman: Man of Steel", "15", "8400", "Is it a bird, is it a plane? No its Superman!", "img_url2");
        Movie[] movies = {movie1, movie2};

        assertThat(JSON.moviesToJson(movies), is("[{\"Movie_ID\":1,\"Movie_Name\":\"Batman Begins\",\"Movie_Rating\":\"12A\",\"Movie_Runtime\":\"7200\",\"Movie_Info\":\"Man scared of bats dresses up to beat up people\",\"Movie_Image\":\"img_url1\"},{\"Movie_ID\":2,\"Movie_Name\":\"Superman: Man of Steel\",\"Movie_Rating\":\"15\",\"Movie_Runtime\":\"8400\",\"Movie_Info\":\"Is it a bird, is it a plane? No its Superman!\",\"Movie_Image\":\"img_url2\"}]"));
    }

    @Test
    public void SeatsFromJsonTest() {
        String seatString = "[{\"Screening_ID\":\"5\",\"Row_Num\":\"6\",\"Column_Num\":\"6\"}]";
        Seat[] seats = JSON.seatsFromJson(seatString);
        String seat = seats[0].toString();

        assertThat(seat, is((new Seat(5, 6, 6)).toString()));
    }

    @Test
    public void SeatsToJsonTest() {
        Seat seat1 = new Seat(5, 6, 6);
        Seat seat2 = new Seat(6, 3, 4);
        Seat[] seats = {seat1, seat2};

        assertThat(JSON.seatsToJson(seats), is("[{\"Screening_ID\":5,\"Row_Num\":6,\"Column_Num\":6},{\"Screening_ID\":6,\"Row_Num\":3,\"Column_Num\":4}]"));
    }

    @Test
    public void WhatsOnFromJsonTest() {
        String whatsOnString = "[{\"Screening_ID\":\"5\",\"Movie_ID\":\"6\",\"Screen_ID\":\"6\",\"Start_Time\":\"12:00\"}]";
        WhatsOn[] whatsOn = JSON.whatsOnFromJson(whatsOnString);
        String whatson = whatsOn[0].toString();

        assertThat(whatson, is((new WhatsOn(5, 6, 6, "12:00")).toString()));
    }

    @Test
    public void WhatsOnToJsonTest() {
        WhatsOn whatsOn1 = new WhatsOn(5, 6, 6, "12:00");
        WhatsOn whatsOn2 = new WhatsOn(6, 3, 4, "13:00");
        WhatsOn[] whatsOn = {whatsOn1, whatsOn2};

        assertThat(JSON.whatsOnToJson(whatsOn), is("[{\"Screening_ID\":5,\"Movie_ID\":6,\"Screen_ID\":6,\"Start_Time\":\"12:00\"},{\"Screening_ID\":6,\"Movie_ID\":3,\"Screen_ID\":4,\"Start_Time\":\"13:00\"}]"));
    }

}
