package org.example.movieweb.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Blog;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.service.BlogService;
import org.example.movieweb.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final BlogService blogService;

    @GetMapping
    public  String getHomePage(Model model) {
        // lấy 6 bộ phim để hiển thị ra ngoài trang chủ
        // getMoviesByType() --> lấy ra loại phim ở trạng thái true và sắp xếp thời gian tạo giảm dần -> lấy ở trang đầu tiên có 6 bản ghi
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        List<Movie> listPhimHot = movieService.getMovieSortByRating(true);

        List<Blog> listBlog = blogService.getBlogByStatus(true);

        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
        model.addAttribute("listPhimHot", listPhimHot);
        model.addAttribute("listBlog", listBlog);

        return "web/index";
    }

    //phân trang: /phim-bo?page=1&pageSize=12 -> mặc định pageSize=12
    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {

        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLe(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "12") int pageSize) {

        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);

        return "web/phim-le";

    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "12") int pageSize) {

        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);

        return "web/phim-le";

    }
}
