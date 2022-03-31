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

    @Transactional
    public User 회원가입(JoinDto joinDto) {
        return userRepository.save(joinDto.toEntity());
    }

    public User 로그인(LoginDto loginDto) {
        User userEntity = userRepository.mLogin(loginDto.getUsername(), loginDto.getPassword());
        return userEntity;
    }
}
