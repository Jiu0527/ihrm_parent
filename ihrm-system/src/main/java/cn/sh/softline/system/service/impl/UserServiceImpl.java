package cn.sh.softline.system.service.impl;

import cn.sh.softline.system.entity.Role;
import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.mapper.RoleMapper;
import cn.sh.softline.system.mapper.UserMapper;
import cn.sh.softline.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void changeDept(String deptId, String deptName, List<String> ids) {
        for (String id : ids) {
            User user = userMapper.selectById(id);
            user.setDepartmentId(deptId);
            user.setDepartmentName(deptName);
            userMapper.insert(user);
        }
    }

    @Override
    public void assignRole(String userId, List<String> roleIds) {
        User user = userMapper.selectById(userId);
        Set<Role> roles = new HashSet<Role>();
        for (String id:roleIds) {
            Role role = roleMapper.selectById(id);
            roles.add(role);
        }
        user.setRoles(roles);
        userMapper.insert(user);
    }
}
