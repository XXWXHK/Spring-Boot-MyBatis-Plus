package cn.com.xuxiaowei.springbootmybatisplus.controller;


import cn.com.xuxiaowei.springbootmybatisplus.entity.User;
import cn.com.xuxiaowei.springbootmybatisplus.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表（测试） 前端控制器
 * </p>
 *
 * @author 徐晓伟
 * @since 2019-05-17
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    /**
     * 用户表（测试） 服务类
     */
    private final IUserService userService;

    /**
     * 尽量使用这种方式注入（最好的方式）
     *
     * @param userService 用户表（测试） 服务类
     */
    @Autowired
    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 根据 ID 查询
     * <p>
     * http://127.0.0.1/user/getById?id=5
     *
     * @param id 用户主键
     */
    @RequestMapping("/getById")
    public Map<String, Object> getById(HttpServletRequest request, HttpServletResponse response, String id) {

        Map<String, Object> map = new HashMap<>(4);

        User user = userService.getById(id);

        map.put("user", user);

        return map;
    }

    /**
     * 根据 Wrapper，查询一条记录
     * <p>
     * 正常：http://127.0.0.1/user/getOne?age=47
     * 多条数据报错：http://127.0.0.1/user/getOne?age=21
     *
     * @param age 年龄
     */
    @RequestMapping("/getOne")
    public Map<String, Object> getOne(HttpServletRequest request, HttpServletResponse response, String age) {

        Map<String, Object> map = new HashMap<>(4);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq(User.AGE, age);

        User user = userService.getOne(userQueryWrapper);

        map.put("user", user);

        return map;
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     * <p>
     * http://127.0.0.1/user/count?age=21
     *
     * @param age 年龄
     */
    @RequestMapping("/count")
    public Map<String, Object> count(HttpServletRequest request, HttpServletResponse response, String age) {

        Map<String, Object> map = new HashMap<>(4);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq(User.AGE, age);

        int count = userService.count(userQueryWrapper);

        map.put("count", count);

        return map;
    }

}

