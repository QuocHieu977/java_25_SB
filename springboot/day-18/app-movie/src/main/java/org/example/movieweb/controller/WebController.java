package org.example.movieweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.*;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.service.BlogService;
import org.example.movieweb.service.EpisodeService;
import org.example.movieweb.service.MovieService;
import org.example.movieweb.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(WebController.class);
    private final MovieService movieService;
    private final BlogService blogService;
    private final EpisodeService episodeService;
    private final ReviewService reviewService;
    private final ConversionService conversionService;

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

        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug ) {

        Movie movie = movieService.getMovieDetails(id, slug);
        model.addAttribute("movie", movie);

        List<Movie> moviesRecommended = movieService.getMovieRecommendations(movie.getId(), movie.getType());
        model.addAttribute("movieRecommended", moviesRecommended);

        List<Episode> episodes = episodeService.getEpisodesByMovieId(movie.getId());
        model.addAttribute("episodes", episodes);

        List<Review> reviews = reviewService.getReviewsByMovieId(movie.getId());
        model.addAttribute("reviews", reviews);

        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/phim/{id}/{slug}")
    public String getMovieStreamDetailPage(Model model,
                                           @PathVariable Integer id,
                                           @PathVariable String slug,
                                           @RequestParam String tap) {

        Movie movie = movieService.getMovieDetails(id, slug);
        model.addAttribute("movie", movie);

        List<Movie> moviesRecommended = movieService.getMovieRecommendations(movie.getId(), movie.getType());
        model.addAttribute("movieRecommended", moviesRecommended);

        List<Episode> episodes = episodeService.getEpisodesByMovieId(movie.getId());
        model.addAttribute("episodes", episodes);

        List<Review> reviews = reviewService.getReviewsByMovieId(movie.getId());
        model.addAttribute("reviews", reviews);


        Episode currentEpisode = episodeService.getEpisodeByDisplayOrder(movie.getId(), tap);
        model.addAttribute("currentEpisode", currentEpisode);

        return "web/xem-phim";
    }

    @GetMapping("/tin-tuc")
    public String getBlogPage(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "8") int pageSize) {
        Page<Blog> pageData = blogService.getBlogs(page, pageSize, true);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);

        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String getBlogDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug) {

        Blog blog = blogService.getBlogDetails(id, slug);
        model.addAttribute("blog", blog);
        return "web/chi-tiet-tin-tuc";
    }

    @GetMapping("/dang-nhap")
    public String loginPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            return "redirect:/";
        }
        return "web/dang-nhap";
        // nếu chưa có user thì điều hướng sang trang đăng nhập
        // nếu đã đăng nhập thì điều hướng sang trang chủ
        // nếu đã đăng nhập mà bấm vào nút đăng nhập thì sẽ k điều hướng về trang đăng nhập mà ở lại trang chủ ("redirect:/")
    }

    @GetMapping("/thong-tin-ca-nhan")
    public String profileUserPage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            return "web/dang-nhap";
        }
        return "web/thong-tin-ca-nhan";
    }


    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String registerPage() {
        return "web/dang-ky";
    }

}
