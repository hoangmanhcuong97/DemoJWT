package com.example.demojwt.security.userprincal;

import com.example.demojwt.model.User;
import com.example.demojwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional //khi có Exception xảy ra thì Transaction sẽ rollback lại các thao tác trước đó.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found --> Username or password" + username)
        );
        return UserPrinciple.build(user);
        // sau khi tim kiem username ton tai trong DB thi bat dau build user thanh UserPrinciple cua he thong
        // Dung ham build xay dung ben lop UserPrinciple da tao, ko phai cua he thong.
    }
}
