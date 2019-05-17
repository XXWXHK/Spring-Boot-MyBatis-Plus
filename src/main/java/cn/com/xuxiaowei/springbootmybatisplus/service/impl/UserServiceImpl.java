package cn.com.xuxiaowei.springbootmybatisplus.service.impl;

import cn.com.xuxiaowei.springbootmybatisplus.entity.User;
import cn.com.xuxiaowei.springbootmybatisplus.mapper.UserMapper;
import cn.com.xuxiaowei.springbootmybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表（测试） 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2019-05-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
