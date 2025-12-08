//package com.example.authentication.security;
//
//import com.example.authentication.entity.User;
//import com.example.authentication.repository.UserRepository;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Data
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("Không tìm thấy user: " + username));
//
//        // Chuyển entity User của bạn sang dạng UserDetails mà Spring hiểu
//        SecurityUser user1 = new SecurityUser();
//        user1.setUsername(user.getUsername());
//        user1.setPassword(user.getPassword());
//        return user1;
//    }
//}