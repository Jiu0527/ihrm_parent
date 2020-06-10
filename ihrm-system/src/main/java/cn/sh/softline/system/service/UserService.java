package cn.sh.softline.system.service;

import cn.sh.softline.system.entity.User;
import cn.sh.softline.system.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rz045
 * @since 2020-06-09
 */
public interface UserService extends IService<User> {

    void changeDept(String deptId, String deptName, List<String> ids);

    void assignRole(String userId,List<String> roleIds);
}
