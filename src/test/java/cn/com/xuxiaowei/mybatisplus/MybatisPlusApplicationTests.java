package cn.com.xuxiaowei.mybatisplus;

import cn.com.xuxiaowei.mybatisplus.entity.User;
import cn.com.xuxiaowei.mybatisplus.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

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
