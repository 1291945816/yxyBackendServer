package ink.kilig.yxy;

import ink.kilig.yxy.mapper.YxyPictureMapper;
import ink.kilig.yxy.mapper.YxyUserAblumMapper;
import ink.kilig.yxy.mapper.YxyUserMapper;

import org.junit.Test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class YxyApplicationTests {

    @Autowired
    YxyUserMapper yxyUserMapper;

    @Autowired
    YxyPictureMapper yxyPictureMapper;


    @Autowired
    YxyUserAblumMapper yxyUserAblumMapper;

    @Test
    public void testMethod(){
        System.out.println(yxyUserAblumMapper.getStaredDetails("29"));
    }


}
