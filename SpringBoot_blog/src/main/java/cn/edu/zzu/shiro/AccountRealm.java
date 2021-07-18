package cn.edu.zzu.shiro;

import cn.edu.zzu.entity.User;
import cn.edu.zzu.service.UserService;
import cn.edu.zzu.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getById(userId);

        if(user == null ){
            throw new UnknownAccountException("账户不存在！");
        }
        if(user.getStatus() == -1 ){
            throw new LockedAccountException("账户被锁定！");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user , accountProfile);
        return new SimpleAuthenticationInfo(accountProfile , jwtToken.getCredentials() , getName());
    }
}
