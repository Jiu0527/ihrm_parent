package cn.sh.softline.system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * 用户登录成功后，再次请求用户的详细信息
 * @author rz-045
 */
@Getter
@Setter
@NoArgsConstructor
public class ProfileResult {

    private String mobile;

    private String username;

    private String company;

    private Map roles;

    public ProfileResult(User user){
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        //todo:获取用户权限
    }
}
