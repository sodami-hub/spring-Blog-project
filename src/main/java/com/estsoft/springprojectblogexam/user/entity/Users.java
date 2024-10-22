package com.estsoft.springprojectblogexam.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /* 날짜 정보는 빼고 진행
    @CreatedDate
    @Column(name="created_at" )
    private LocalDateTime createdAt;
    */

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // UserDetails 인터페이스의 구현

    // 사용자의 (접근)권한 정보(인가와 관련된 정보)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_ADMIN,
        return List.of(new SimpleGrantedAuthority("user"), new SimpleGrantedAuthority("ROLE_ADMIN")); // 새싹, 주니어, 시니어, ... ,  관리자
        // 롤 이름을 정의하고 서버의 api 접근 여부를 각각 설정
    }

    // 사용자의 인증(로그인)에 필요한 정보
    @Override
    public String getUsername() {
        return email; // 사용자의 신원을 확인하는데 사용할 정보를 리턴값으로 넣는다.
    }

    @Override
    public boolean isAccountNonExpired() {
        // 예를들어 user의 마지막 접속일부터 6개월이 지났으면 false 를 리턴해서 휴면계정 복구 뭐 이런 로직 추가 가능
        return true; // 지금은 그냥 true로 해서 다 접속할 수 있도록
    }

    @Override
    public boolean isAccountNonLocked() {
        // 해당 계정이 잠겨있지 않은 계정인가?
        return true;
    }

    // 계정의 pw정보 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않았다.
    }

    // 활성화된 계정인가?
    @Override
    public boolean isEnabled() {
        return true;
    }
}
