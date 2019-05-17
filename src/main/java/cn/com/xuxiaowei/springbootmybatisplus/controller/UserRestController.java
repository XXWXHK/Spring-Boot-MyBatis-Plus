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
import java.util.UUID;

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
     * 插入一条记录（选择字段，策略插入）
     * <p>
     * http://127.0.0.1/user/save?nickname=AAAAA
     *
     * @param nickname 昵称
     */
    @RequestMapping("/save")
    public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response, String nickname) {

        Map<String, Object> map = new HashMap<>(4);

        User user = new User().setNickname(nickname).setPassword(UUID.randomUUID().toString().replace("-", ""));

        boolean save = userService.save(user);

        map.put("save", save);
        map.put("user", user);

        return map;
    }

    /**
     * 根据 ID 删除
     * <p>
     * http://127.0.0.1/user/removeById?id=18
     * <p>
     * 存在数据，删除了，返回 true
     * 不存在数据，返回 false
     *
     * @param id 用户主键
     */
    @RequestMapping("/removeById")
    public Map<String, Object> removeById(HttpServletRequest request, HttpServletResponse response, String id) {

        Map<String, Object> map = new HashMap<>(4);

        boolean removeById = userService.removeById(id);

        map.put("removeById", removeById);

        return map;
    }

    /**
     * 根据 entity 条件，删除记录
     * <p>
     * http://127.0.0.1/user/remove?age=30
     * <p>
     * 存在数据，删除了，返回 true
     * 不存在数据，返回 false
     * <p>
     * 删除只是逻辑删除（状态改变），数据还在数据库中
     *
     * @param age 年龄
     */
    @RequestMapping("/remove")
    public Map<String, Object> remove(HttpServletRequest request, HttpServletResponse response, String age) {

        Map<String, Object> map = new HashMap<>(4);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        // eq：等于
        // ne：不等于
        // gt：大于
        // ge：大于等于
        // lt：小于
        // le：小于等于
        // between：在两个值之间
        // notBetween：不在两个值之间
        // like：像，LIKE '%值%'
        // notLike：不像，NOT LIKE '%值%'
        // likeLeft：LIKE '%值'
        // likeRight：LIKE '值%'
        userQueryWrapper.gt(User.AGE, age);

        boolean removeById = userService.remove(userQueryWrapper);

        map.put("removeById", removeById);

        return map;
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

