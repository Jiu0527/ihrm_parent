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
@TableName("pe_permission_point")
public class PermissionPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 按钮类型
     */
    private String pointClass;

    /**
     * 按钮icon
     */
    private String pointIcon;

    /**
     * 状态
     */
    private Integer pointStatus;


}
