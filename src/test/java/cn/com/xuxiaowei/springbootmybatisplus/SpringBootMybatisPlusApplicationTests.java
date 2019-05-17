package cn.com.xuxiaowei.springbootmybatisplus;

import cn.com.xuxiaowei.springbootmybatisplus.entity.User;
import cn.com.xuxiaowei.springbootmybatisplus.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    public void contextLoads() {

        getById();
    }

    void getById() {
        User user = userService.getById(1);
        System.err.println(user);
    }

}
