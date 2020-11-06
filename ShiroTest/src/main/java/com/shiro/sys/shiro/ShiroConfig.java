package com.shiro.sys.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfig {

	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 注入自定义的 Realm
	 * 
	 * @return MyRealm
	 */
	@Bean
	public MyRealm myRealm() {
		MyRealm myRealm = new MyRealm();
		HashedCredentialsMatcher hcm = new HashedCredentialsMatcher();
		hcm.setHashAlgorithmName("MD5");
		hcm.setHashIterations(1024);
		myRealm.setCredentialsMatcher(hcm);
		return myRealm;
	}

	/**
	 * 安全管理器
	 * 
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		return new DefaultWebSecurityManager(myRealm());
	}

	/**
	 * 注入 Shiro 过滤器
	 * 
	 * @param securityManager 安全管理器
	 * @return ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		// 定义 shiroFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置自定义的 securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 设置默认登录的 URL，身份认证失败会访问该 URL
		shiroFilterFactoryBean.setLoginUrl("/templates/login.html");
		// 设置成功之后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/templates/main.htl");
		// 设置未授权界面，权限认证失败会访问该 URL
		shiroFilterFactoryBean.setUnauthorizedUrl("/templates/unauthorized.html");
		// LinkedHashMap 是有序的，进行顺序拦截器配置
		Map<String, String> filterChainMap = new LinkedHashMap<>();
		// 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
		filterChainMap.put("/templates/register.html", "anon");
		filterChainMap.put("/user/register.g", "anon");
		filterChainMap.put("/templates/login.html", "anon");
		filterChainMap.put("/login.html", "anon");
		filterChainMap.put("/user/login.g", "anon");
		// 配置 logout 过滤器
		filterChainMap.put("/user/logout.g", "logout");
		// 其他地址需要认证后访问
		filterChainMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
		return shiroFilterFactoryBean;
	}
}
