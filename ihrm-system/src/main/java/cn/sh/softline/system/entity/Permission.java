package cn.sh.softline.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pe_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 主键
     */
    private String pid;

    private String code;

    /**
     * 企业可见性 0：不可见，1：可见
     */
    private Integer enVisible;


}
