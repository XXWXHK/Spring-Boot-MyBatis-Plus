package cn.com.xuxiaowei.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 徐晓伟
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TEST")
public class Test implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("USER_ID")
    private Double userId;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;


    public static final String USER_ID = "USER_ID";

    public static final String USERNAME = "USERNAME";

    public static final String PASSWORD = "PASSWORD";

}
