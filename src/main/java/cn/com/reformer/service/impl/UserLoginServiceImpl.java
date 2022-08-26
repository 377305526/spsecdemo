package cn.com.reformer.service.impl;

import cn.com.reformer.config.PassWordEncode;
import cn.com.reformer.entity.AdminPermission;
import cn.com.reformer.entity.PlusAdmin;
import cn.com.reformer.service.IAdminPermissionService;
import cn.com.reformer.service.IPlusAdminService;
import cn.com.reformer.service.UserLoginService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/25/9:30
 * @Description:
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserLoginServiceImpl implements UserDetailsService, UserLoginService {

    private final IPlusAdminService adminService;
    private final IAdminPermissionService adminPermissionService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        log.info("进入用户校验");
        // 根据用户名从数据库查询用户
        PlusAdmin admin = adminService.getOne(Wrappers.<PlusAdmin>lambdaQuery().eq(PlusAdmin::getUserName, username));
        // 判空
        if (admin == null) {
            throw new UsernameNotFoundException("不存在的管理员！");
        }
        // 实际上这里还应该具体去查出用户的角色、权限，再全部放进这个列表
        //声明一个用于存放用户权限的列表
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<AdminPermission> permissions = adminPermissionService.list(Wrappers.<AdminPermission>lambdaQuery().eq(AdminPermission::getAdminId, admin.getId()));
        for (AdminPermission permission : permissions) {
            // 把该用户的具体权限添加到列表中
            log.info("添加用户权限:{}", permission.getPermissionCode());
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionCode()));

        }


        // 查询数据库返回对象，获得用户名和密码
        return new User(admin.getUserName(), admin.getUserPassword(), grantedAuthorities);

    }
}
