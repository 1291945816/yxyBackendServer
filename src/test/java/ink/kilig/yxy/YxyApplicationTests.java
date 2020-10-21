package ink.kilig.yxy;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
public class YxyApplicationTests {

    @Test
    public void testMethod(){
        System.out.println(DigestUtils.sha256Hex("123"));
    }


}
