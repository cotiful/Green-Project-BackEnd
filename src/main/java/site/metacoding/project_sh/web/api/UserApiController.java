package site.metacoding.project_sh.web.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.project_sh.domain.user.User;
import site.metacoding.project_sh.service.UserService;
import site.metacoding.project_sh.web.api.dto.ResponseDto;
import site.metacoding.project_sh.web.api.dto.user.JoinDto;
import site.metacoding.project_sh.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public ResponseDto<?> join(@RequestBody JoinDto joinDto) {
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입성공", null);

    };

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);

        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인 실패", null);
        } else {
            session.setAttribute("principal", userEntity);
            return new ResponseDto<>(1, "로그인 성공", null);
        }
    };

    @PostMapping("/logout")
    public void logout() {
    };

    @PutMapping()
    public void 수정() {
    };
}
