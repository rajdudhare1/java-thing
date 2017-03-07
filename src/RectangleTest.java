import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTest {
  Rectangle myRectangle = new Rectangle(2,3);

  @Test
  public void testGetArea() {
     assertEquals(myRectangle.getArea(), 6);
  }

  @Test
  public void testGetPerimeter() {
     assertEquals(myRectangle.getPerimeter(), 10);
  }

  @Test
  public void testLength() {
     assertEquals(myRectangle.length, 2);
  }

  @Test
  public void testWidth() {
     assertEquals(myRectangle.width, 3);
  }
}
