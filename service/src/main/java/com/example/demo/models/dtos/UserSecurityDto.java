package com.example.demo.models.dtos;

import com.example.demo.models.entities.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSecurityDto implements UserDetails {
    private String username;
    private String password;
    private Boolean enabled;
    @Getter
    private String token;
    @Getter
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public void setRoles(List<Roles> roles){
        authorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_"+r.getName())).toList();
    }

}
