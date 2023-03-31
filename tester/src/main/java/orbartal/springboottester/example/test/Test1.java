package orbartal.springboottester.example.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Test1 {

   @Test
   public void test1()
      throws Exception {
      Assert.assertEquals(1, 1);
   }

   @Test
   public void test2()
      throws Exception {
      Assert.assertEquals(1, 2);
   }

   @Test
   public void test3()
      throws Exception {
      throw new RuntimeException();
   }

   @Ignore
   @Test
   public void test4()
      throws Exception {
   }
}
