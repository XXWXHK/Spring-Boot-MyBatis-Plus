package cn.com.xuxiaowei.mybatisplus.service.impl;

import cn.com.xuxiaowei.mybatisplus.entity.Test;
import cn.com.xuxiaowei.mybatisplus.mapper.TestMapper;
import cn.com.xuxiaowei.mybatisplus.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2019-12-09
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
