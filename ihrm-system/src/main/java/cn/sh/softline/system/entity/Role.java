package cn.sh.softline.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName("pe_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 说明
     */
    private String description;

    /**
     * 企业id
     */
    private String companyId;

    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    private Set<Permission> permissions = new HashSet<>();
}
