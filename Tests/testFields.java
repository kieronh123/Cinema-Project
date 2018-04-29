import org.junit.Test;
import Tills.*;

import java.util.ArrayList;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import Tills.Controllers.*;


/**
 * Test class used for JUnit testing of the Tills System.
 */
public class testFields {

    //Fields from controller pages
    public PaymentPageController name;
    public PaymentPageController time;
    public PaymentPageController seat;
    public PaymentPageController total;


    //  /**
//   * Testing fields
//   */
//  //Cannot have negative or over bounded fields
    @Test
    public void testFields() {
        PaymentPageController fields = new PaymentPageController("13:00", "Batman", "25", 10.00, "5", "7", 6);
        assertThat(fields.getTime(), is("13:00"));
        assertThat(fields.getName(), is("Batman"));
        assertThat(fields.getSeat(), is("25"));
        assertThat(fields.getTotal(), is(10.00));

    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPayment() {
        new PaymentPageController("13:00", "Batman", "25", -5.00, "5", "7", 6);
    }

    // Testing that fields are what it is meant to be
    @Test
    public void equality() {
        PaymentPageController fields = new PaymentPageController("13:00", "Batman", "25", 10.00, "2", "3", 7);
        assertTrue(fields.equals(fields));
        assertFalse(fields.equals(new PaymentPageController("13:43", "SuperMan", "32", 10.00, "2", "3", 7)));
        assertTrue(fields.equals(new PaymentPageController("13:00", "Batman", "25", 10.00, "2", "3", 7)));
    }

    private ArrayList testList;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        System.out.println("@BeforeClass: onceExecutedBeforeAll");
    }

    @Before
    public void executedBeforeEach() {
        testList = new ArrayList();
        System.out.println("@Before: executedBeforeEach");
    }

    @Test
    public void EmptyCollection() {
        assertTrue(testList.isEmpty());
        System.out.println("@Test: EmptyArrayList");

    }

    //  @Test
//  public void testA() throws InterruptedException {
//    Thread thread = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        new JFXPanel(); // Initializes the JavaFx Platform
//        Platform.runLater(new Runnable() {
//          @Override
//          public void run() {
//            new Main().start(new Stage()); // Create and
//
//          }
//        });
//      }
//    });
//  }

//  @Test
//  public void testMain() {
//    new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          // Do something
//          Thread.sleep(3000);
//        }
//        catch (InterruptedException e) {
//        }
//        System.exit(0);
//      }
//    }).start();
//
//    Main.main();
//  }
}
