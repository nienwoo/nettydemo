import com.crazymakercircle.chat.util;
import org.junit.Test;

public class testUtil
{
    @Test
    public void testsplit(){

     String[]ss=   util.split("1 -> s");
        for (int i = 0; i < ss.length; i++)
        {
            System.out.println("ss = " + ss[i]);

        }
    }
}
