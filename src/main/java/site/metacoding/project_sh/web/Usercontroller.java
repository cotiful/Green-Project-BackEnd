package site.metacoding.project_sh.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site.metacoding.project_sh.domain.user.User;
import site.metacoding.project_sh.service.UserService;
import site.metacoding.project_sh.web.api.dto.ResponseDto;
import site.metacoding.project_sh.web.api.dto.user.JoinDto;
import site.metacoding.project_sh.web.api.dto.user.LoginDto;

@RequiredArgsConstructor
@Controller
public class Usercontroller {

    private final UserService userService;
    private final HttpSession session;

    // 수정 페이지 이동
    @GetMapping("/s/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    // 유저수정 - 로그인
    // requestbody -> bufferedReader
    // responsebody -> bufferedWriter
    @PutMapping("/s/user/{id}")
    public @ResponseBody ResponseDto<String> update(@PathVariable Integer id, @RequestBody User user) {
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            return new ResponseDto<>(-1, "인증안됨", null);
        }
        User userEntity = userService.유저수정(id, user);
        session.setAttribute("principal", userEntity); // name , value

        return new ResponseDto<>(1, "성공", null);
    }

    // 유저네임 중복검사
    @GetMapping("/user/username/same-check")
    public @ResponseBody ResponseDto<?> usernameSameCheck(String username) {
        String data = userService.유저네임중복검사(username);
        return new ResponseDto<>(1, "통신성공", data);
    }

    // 유저닉네임 중복검사
    @GetMapping("/user/nickname/same-check")
    public @ResponseBody ResponseDto<?> nicknameSameCheck(String nickname) {
        String data = userService.닉네임중복검사(nickname);
        return new ResponseDto<>(1, "통신성공", data);
    }

    // 회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // 로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request, Model model) {
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies) {
                System.out.println("쿠키값:" + cookie.getName());
                if (cookie.getName().equals("remember")) {
                    model.addAttribute("remember", cookie.getValue());
                }
            }
        }
        return "user/loginForm";
    }

    // 회원가입 데이터 전송
    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "redirect:/joinForm";
    }

    // 로그인 데이터 전송
    @PostMapping("/login")
    public @ResponseBody ResponseDto<?> login(LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);
        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        if (loginDto.getRemember() != null) {
            if (loginDto.getRemember().equals("on")) {
                response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; path=/" + ";HttpOnly=true");
            }
        }
        session.setAttribute("principal", userEntity);
        return new ResponseDto<String>(1, "로그인성공", null);
    }

    // 메인페이지
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    // 준비 페이지
    @GetMapping("/s/ready")
    public String ready() {
        return "ready";
    }

}
