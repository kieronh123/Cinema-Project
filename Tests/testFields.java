
import org.junit.Test;
import Tills.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import Tills.Controllers.*;

/**
 * Test class used for JUnit testing of the Tills System.
 */
public class testFields {
//
  //Fields from controller pages
  public PaymentPageController name;
  public PaymentPageController time;
  public PaymentPageController seat;
  public PaymentPageController total;


//
//
//  private ArrayList testList;
//
//  @BeforeClass
//  public static void onceExecutedBeforeAll() {
//    System.out.println("@BeforeClass: onceExecutedBeforeAll");
//  }
//
//  @Before
//  public void executedBeforeEach() {
//    testList = new ArrayList();
//    System.out.println("@Before: executedBeforeEach");
//  }

  //  /**
//   * Testing fields
//   */
//  //Cannot have negative or over bounded fields
  @Test
  public void testFields() {
    PaymentPageController fields = new PaymentPageController("13:00", "Batman", "25", 10.00);
    assertThat(fields.getTime(), is("13:00"));
    assertThat(fields.getName(), is("Batman"));
    assertThat(fields.getSeat(), is("25"));
    assertThat(fields.getTotal(), is(10.00));

  }
//  @Test
//  public void testTicketFields() {
//    TicketPageController pages = new TicketPageController("12:00", "Superman", 14, "18");
//    assertThat(pages.getTime(), is("12:00"));
//    assertThat(pages.getFilmName(), is("Superman"));
//    assertThat(pages.getScreeningID(), is(14));
//    assertThat(pages.getAge(), is("18"));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void invalidPayment() {
//    new PaymentPageController("13:00", "Batman", "25", -5.00);
//  }
//
//  // Testing that fields are what it is meant to be
//  @Test
//  public void equality() {
//    PaymentPageController fields = new PaymentPageController("13:00", "Batman", "25", 10.00);
//    assertTrue(fields.equals(fields));
//    assertFalse(fields.equals(new PaymentPageController("13:43", "SuperMan", "32", 1.00)));
//    assertTrue(fields.equals(new PaymentPageController("13:00", "Batman", "25", 10.00)));
//  }


}
