package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;

@RequiredArgsConstructor
@Controller
public class BoardsController {

   private final HttpSession session;
   @GetMapping({ "/", "/boards" })
   public String getBoardList() {
      return "boards/main";
   }

   @GetMapping("/boards/{id}")
   public String getBoardList(@PathVariable Integer id) {
      return "boards/detail";
   }

   @GetMapping("/boards/writeForm")
   public String writeForm() {//글쓰기는 항상 이공식 사용
      System.out.println("1234");
      Users principal = (Users) session.getAttribute("principal");
      if (principal == null) {
         return "redirect:/loginForm";
      } else {
         return "boards/writeForm";
      }
   }
}