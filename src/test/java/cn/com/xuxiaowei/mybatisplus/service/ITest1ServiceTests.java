package cn.com.xuxiaowei.mybatisplus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 Oracle 自动生成
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITest1ServiceTests {

    @Autowired
    private ITestService testService;

    @Test
    public void getById() {
        cn.com.xuxiaowei.mybatisplus.entity.Test byId = testService.getById(1);
        System.out.println(byId);
    }

}
