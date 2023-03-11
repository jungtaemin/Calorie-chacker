package com.tripproject.article.adapter.in;

import com.tripproject.article.application.port.in.ArticleQueriesUseCase;
import com.tripproject.user.application.port.in.response.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
public class MainController {


    private final ArticleQueriesUseCase articleQueriesUseCase;

    @RequestMapping("/")
    public String main(Model model){



        var articleList = articleQueriesUseCase.findTop5ByIdOrderByDesc();
        model.addAttribute("articleList",articleList);




        return "index";
    }
}
