package com.cq.edu.service.impl;

import com.cq.edu.dao.AccountMapper;
import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname UserDetailsServiceImpl
 * @Description 自定义一个UserDetailsService接口实现类进行用户认证信息封装
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 通过业务方法获取用户及权限信息
        Account customer = accountMapper.selectAccount(s);
        List<Authority> authorities = accountMapper.selectAuths(s);
        // 对用户权限进行封装 函数式编程

        //迭代器
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        Iterator<Authority> iterator = authorities.iterator();
        while (iterator.hasNext()) {
            list.add(new SimpleGrantedAuthority(iterator.next().getAuthority()));
        }

        // 返回封装的UserDetails用户详情类
        if(customer!=null){
            UserDetails userDetails= new User(customer.getAccount(),customer.getPassword(), list);
            return userDetails;
        } else {
            // 如果查询的用户不存在（用户名不存在），必须抛出此异常
            return null;
        }
    }
}

