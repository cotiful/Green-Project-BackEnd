package site.metacoding.project_sh.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/api/user/username/same-check")
    public ResponseDto<?> sameCheck(String username) {
        String data = userService.유저네임중복검사(username);
        return new ResponseDto<>(1, "통신성공", data);
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

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

    @PostMapping("/join")
    public String join(JoinDto joinDto) {
        userService.회원가입(joinDto);
        return "redirect:/joinForm";

    }

    @PostMapping("/login")
    public @ResponseBody ResponseDto<?> login(LoginDto loginDto, HttpServletResponse response) {
        User userEntity = userService.로그인(loginDto);
        if (userEntity == null) {
            return new ResponseDto<>(-1, "로그인실패", null);
        }
        if (loginDto.getRemember().equals("on")) {
            response.addHeader("Set-Cookie", "remember=" + loginDto.getUsername() + "; path=/");
        }

        session.setAttribute("principal", userEntity);
        return new ResponseDto<String>(1, "로그인성공", null);
    }

}
