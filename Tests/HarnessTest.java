import Tills.Harness;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HarnessTest {
    @Test
    public void testGetRequest() {
        try {
            String string = Harness.sendGet("movies").toString();
            assertThat(string, is("[{\"Movie_Runtime\": 7200, \"Movie_Name\": \"Batman Begins\", \"Movie_Image\": \"image_url1\", \"Movie_Rating\": \"12A\", \"Movie_Info\": \"Man scared of bats dresses up to beat up people\", \"Movie_ID\": 1}, {\"Movie_Runtime\": 8400, \"Movie_Name\": \"Superman: Man of Steel\", \"Movie_Image\": \"image_url2\", \"Movie_Rating\": \"15\", \"Movie_Info\": \"Is it a bird, is it a plane? No its Superman!\", \"Movie_ID\": 2}]"));
        }catch (Exception e){}
    }
}
