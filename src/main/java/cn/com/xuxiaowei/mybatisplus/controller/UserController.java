package cn.com.xuxiaowei.mybatisplus.controller;

import cn.com.xuxiaowei.mybatisplus.entity.User;
import cn.com.xuxiaowei.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 用户表（测试） 页面
 * </p>
 *
 * @author 徐晓伟
 * @since 2019-05-17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 此注入存在警告
     * <p>
     * Controller层注入，参见 {@link UserRestController}
     */
    @Autowired
    private IUserService userService;

    /**
     * 访问地址：http://127.0.0.1/user
     *
     * @param model 本页面数据
     * @return 最前面不能有/，否则打包方式为 jar 时，出现错误
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        String password = UUID.randomUUID().toString().replace("-", "");

        Random random = new Random();

        int i = random.nextInt(100);

        String nickname = "测试数据用户名" + i;

        User user = new User().setNickname(nickname).setPassword(password);

        boolean save = userService.save(user);

        model.addAttribute("save", save);
        model.addAttribute("user", user);

        return "user";
    }

}

