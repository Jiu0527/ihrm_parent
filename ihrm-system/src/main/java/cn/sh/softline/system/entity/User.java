package cn.sh.softline.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@TableName("bs_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0是禁用，1是启用
     */
    private Integer enableState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 入职时间
     */
    private Date timeOfEntry;

    /**
     * 聘用形式
     */
    private Integer formOfEmployment;

    /**
     * 工号
     */
    private String workNumber;

    /**
     * 管理形式
     */
    private String formOfManagement;

    /**
     * 工作城市
     */
    private String workingCity;

    /**
     * 转正时间
     */
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;

    /**
     * 企业ID
     */
    private String companyId;

    private String companyName;

    private String departmentName;

    /**
     * 用户级别：saasAdmin，coAdmin，user
     */
    private String level;

    private String staffPhoto;

    /**
     * 离职时间
     */
    private Date timeOfDimission;

    @JsonIgnore
    @TableField(exist = false)
    Set<Role> roles = new HashSet<Role>();
}
