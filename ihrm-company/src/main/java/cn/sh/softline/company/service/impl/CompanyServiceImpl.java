package cn.sh.softline.company.service.impl;

import cn.sh.softline.company.entity.Company;
import cn.sh.softline.company.mapper.CompanyMapper;
import cn.sh.softline.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rz045
 * @since 2020-06-04
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
