package site.metacoding.project_sh.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.project_sh.domain.user.User;
import site.metacoding.project_sh.domain.user.UserRepository;
import site.metacoding.project_sh.web.api.dto.user.JoinDto;
import site.metacoding.project_sh.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String 유저네임중복검사(String username) {
        User userEntity = userRepository.mUsernameSameCheck(username);

        if (userEntity == null) {
            return "아이디 중복이 없습니다.";
        } else {
            return "아이디가 중복됩니다.";
        }
    }

    public String 닉네임중복검사(String nickname) {
        User userEntity = userRepository.mNicknameSameCheck(nickname);

        if (userEntity == null) {
            return "닉네임 중복이 없습니다.";
        } else {
            return "닉네임이 중복됩니다.";
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
}
