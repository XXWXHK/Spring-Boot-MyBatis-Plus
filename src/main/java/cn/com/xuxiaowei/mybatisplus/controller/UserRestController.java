package cn.com.xuxiaowei.mybatisplus.controller;


import cn.com.xuxiaowei.mybatisplus.entity.User;
import cn.com.xuxiaowei.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户表（测试） 前端控制器
 * </p>
 * 使用了 @RestController 只能返回数据，不能返回页面
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
     * 最好的方式（官方推荐）
     * <p>
     * Controller层尽量使用这种方式注入，否则会后警告
     * <p>
     * 注入警告有时是错误，而你开始时无从得知
     * <p>
     * 注入没有警告，即肯定没有错误
     * <p>
     * Servlet注册为Bean后，直接注入即可
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

        String password = UUID.randomUUID().toString().replace("-", "");

        User user = new User().setNickname(nickname).setPassword(password);

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

    /**
     * 无条件翻页查询
     * <p>
     * http://127.0.0.1/user/page
     *
     * @param current 页数
     * @param size    每页条数
     */
    @RequestMapping("/page")
    public Map<String, Object> page(HttpServletRequest request, HttpServletResponse response, String current, String size) {

        Map<String, Object> map = new HashMap<>(4);

        if (StringUtils.isEmpty(current)) {
            map.put("current", "不可为空");
            return map;
        }

        if (StringUtils.isEmpty(size)) {
            map.put("size", "不可为空");
            return map;
        }

        long currentLong = Long.parseLong(current);

        long sizeLong = Long.parseLong(size);

        Page<User> page = new Page<>(currentLong, sizeLong);

        // 排序
        page.setAsc(User.ID);

        IPage pages = userService.page(page);

        List records = pages.getRecords();

        map.put("records", records);

        return map;
    }


}

