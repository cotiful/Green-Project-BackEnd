package site.metacoding.project_sh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.project_sh.domain.user.User;
import site.metacoding.project_sh.domain.user.UserRepository;
import site.metacoding.project_sh.web.api.dto.user.JoinDto;
import site.metacoding.project_sh.web.api.dto.user.LoginDto;
import site.metacoding.project_sh.web.api.dto.user.UpdateDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User 코인업데이트(Integer id, UpdateDto updateDto) {
        Optional<User> userCoinOp = userRepository.findById(id);
        if (userCoinOp.isPresent()) {
            User userEntity = userCoinOp.get();
            userEntity.setCoin(updateDto.getCoin());

            return userEntity;
        }
        return null;
    }

    public String 유저네임중복검사(String username) {
        User userEntity = userRepository.mUsernameSameCheck(username);

        if (userEntity == null) {
            return "노중복";
        } else {
            return "중복";
        }
    }

    public String 닉네임중복검사(String nickname) {
        User userEntity = userRepository.mNicknameSameCheck(nickname);

        if (userEntity == null) {
            return "닉네임노중복";
        } else {
            return "닉네임중복";
        }
    }

    @Transactional
    public User 회원가입(JoinDto joinDto) {
        return userRepository.save(joinDto.toEntity());
    }

    public User 로그인(LoginDto loginDto) {
        User userEntity = userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
        System.out.println("로그인:" + userEntity);
        return userEntity;
    }

    public User 유저수정(Integer id, User user) {
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            userEntity.setNickname(user.getNickname());
            userEntity.setPassword(user.getPassword());
            userEntity.setEmail(user.getEmail());

            return userEntity;
        }
        return null;
    }

}
