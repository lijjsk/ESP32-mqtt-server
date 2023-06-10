package com.Li.esp32mqttserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //切记，登录类和实现UserDetails的类不能是同一个，不然会造成找不到密码。而且继承UserDetails的类中，username和password记得返回数据库中用户username和password。
    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }
    //是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}