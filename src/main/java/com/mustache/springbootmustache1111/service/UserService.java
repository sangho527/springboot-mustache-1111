package com.mustache.springbootmustache1111.service;

import com.mustache.springbootmustache1111.domain.dto.UserRequest;
import com.mustache.springbootmustache1111.domain.dto.UserResponse;
import com.mustache.springbootmustache1111.domain.entity.User;
import com.mustache.springbootmustache1111.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(Long id){
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            return new UserResponse(id, "", "해당 id의 유저가 없습니다.");
        } else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }
    }

    public UserResponse addUser(UserRequest dto) {
        // dto를 entity로
        User user = dto.toEntity();

        // 저장하기 전 username으로 select 하기
        // 있으면 중복되었습니다 라는 메시지로 알려주고 .save하지 않기
        Optional<User> selectedUser = userRepository.findByUsername(dto.getUsername());
        if (selectedUser.isEmpty()){
            User savedUser = userRepository.save(user);
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
        } else {
            return new UserResponse(null, dto.getUsername(), "이 유저는 이미 존재 합니다. 다시 생성해주세요.");
        }
    }
}
