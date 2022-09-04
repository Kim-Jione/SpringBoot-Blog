package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

@RequiredArgsConstructor // 디펜더시 인젝션 코드
@Controller
public class UsersController {

	private final HttpSession session;
	private final UsersDao usersDao;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate(); 
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(LoginDto loginDto, HttpServletRequest request) { // 로그인만 예외로 select인데 post로 함.
		Users usersPS = usersDao.login(loginDto);
		if (usersPS != null) { // 인증됨
			session.setAttribute("principal", usersPS); // 일반적으로 principal 이라고 적는다
			return "redirect:/";
		} else { // 인증안됨
			return "redirect:/loginForm";
		}
		
	}

	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";// 미리만들어진거 쓰기
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
}