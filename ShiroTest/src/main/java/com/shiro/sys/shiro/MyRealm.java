package com.shiro.sys.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiro.role.entity.Role;
import com.shiro.role.ser.RoleSer;
import com.shiro.user.entity.User;
import com.shiro.user.ser.UserSer;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserSer userSer;
	@Autowired
	private RoleSer roleSer;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1. 从 PrincipalCollection 中来获取登录用户的信息
		User user = (User) principals.getPrimaryPrincipal();
		Role role = roleSer.getOne(new QueryWrapper<Role>().lambda().eq(Role::getId, user.getRole()));
		// 2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		roles.add(role.getRole());
		// 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		return new SimpleAuthorizationInfo(roles);
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 把AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 从UsernamePasswordToken 中来获取 username
		String name = upToken.getUsername();
		// 根据用户名查询用户信息
		User user = userSer.getOne(new QueryWrapper<User>().lambda().eq(User::getName, name));
		// 判断用户是否存在，不存在抛出UnknownAccountException异常
		if (user == null) {
			throw new UnknownAccountException("用户不存在!");
		}
		// realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		// 使用用户自身的名称当作盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(name);
		return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);
	}

}
