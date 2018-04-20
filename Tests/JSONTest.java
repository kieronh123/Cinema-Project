import JSON.*;
import Tills.*;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class JSONTest {
    @Test
    public void moviesJsonTest(){
        String moviesString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}, {\"Movie_Runtime\": 8400, \"Movie_Name\": \"Superman: Man of Steel\", \"Movie_Image\": \"image_url2\", \"Movie_Rating\": \"15\", \"Movie_Info\": \"Is it a bird, is it a plane? No its Superman!\", \"Movie_ID\": 2}]";
        Movie[] movies = JSON.moviesFromJson(moviesString);
        String[] stringArray = {movies[0].toString(), movies[1].toString()};

        assertThat(stringArray[0], is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
        assertThat(stringArray[1], is((new Movie(2, "Superman: Man of Steel", "15", "8400", "Is it a bird, is it a plane? No its Superman!", "image_url2")).toString()));
    }

    @Test
    public void movieJsonTest(){
        String movieString = "[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}]";
        Movie movie = JSON.movieFromJson(movieString);
        String string = movie.toString();

        assertThat(string, is((new Movie(1, "Batman Begins", "12A", "7200", "Man scared of bats dresses up to beat up people", "image_url1")).toString()));
    }

    @Test
    public void usersJsonTest(){
        String usersString = "[{\"Username\": \"kieron.hushon@gmail.com\", \"Password\": \"5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8\", \"User_ID\": 1}, {\"Username\": \"alexander_hoare@homail.co.uk\", \"Password\": \"F52FBD32B2B3B86FF88EF6C490628285F482AF15DDCB29541F94BCF526A3F6C7\", \"User_ID\": 2}]";
        User[] users = JSON.usersFromJson(usersString);
        String[] stringArray = {users[0].toString(), users[1].toString()};

        assertThat(stringArray[0], is((new User(1, "kieron.hushon@gmail.com", "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8")).toString()));
        assertThat(stringArray[1], is((new User(2, "alexander_hoare@homail.co.uk", "F52FBD32B2B3B86FF88EF6C490628285F482AF15DDCB29541F94BCF526A3F6C7")).toString()));
    }
}
