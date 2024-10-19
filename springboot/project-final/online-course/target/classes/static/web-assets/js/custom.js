"use strict";
window.addEventListener("load", function () {
    (document.getElementById("preloader").style.display = "none"),
        (document.body.style.overflow = "visible");
});
const navbar = document.querySelector(".navbar"),
    navOffCanvasBtn = document.querySelectorAll(".offcanvas-nav-btn"),
    navOffCanvas = document.querySelector(
        ".navbar:not(.navbar-clone) .offcanvas-nav"
    );
let bsOffCanvas;
function toggleOffCanvas() {
    bsOffCanvas &&
        (bsOffCanvas._isShown ? bsOffCanvas.hide() : bsOffCanvas.show());
}
function handleDropdownToggle(e) {
    const t = e.currentTarget,
        o = t.nextElementSibling;
    o.classList.contains("show") ||
        t
            .closest(".dropdown-menu")
            .querySelectorAll(".show")
            .forEach((e) => e.classList.remove("show")),
        o.classList.toggle("show");
    const r = t.closest("li.nav-item.dropdown.show");
    r &&
        r.addEventListener("hidden.bs.dropdown", () => {
            document
                .querySelectorAll(".dropdown-submenu .show")
                .forEach((e) => e.classList.remove("show"));
        }),
        e.preventDefault(),
        e.stopPropagation();
}
// navOffCanvas &&
//     ((bsOffCanvas = new bootstrap.Offcanvas(navOffCanvas, { scroll: !0 })),
//         navOffCanvasBtn.forEach((e) => {
//             e.addEventListener("click", toggleOffCanvas);
//         })),
//     document.querySelectorAll(".dropdown-menu a.dropdown-toggle").forEach((e) => {
//         e.addEventListener("click", handleDropdownToggle);
//     }),
//     window.addEventListener("scroll", function () {
//         let e = document.querySelector(".sticky-height"),
//             t = document.querySelector(".header-nav-wrapper"),
//             o = t.offsetHeight,
//             r = document.querySelector(".header-top"),
//             l = (r ? r.offsetHeight : 0) + 200;
//         window.scrollY > l
//             ? (t.classList.add("scroll-on"), (e.style.height = o + "px"))
//             : (t.classList.remove("scroll-on"), (e.style.height = "0"));
//     });
const backTotop = (function () {
    let e = window.scrollY,
        t = document.querySelector(".back-top");
    if (t) {
        let o = () => t.classList.add("back-top-show"),
            r = () => t.classList.remove("back-top-show");
        window.addEventListener("scroll", function () {
            (e = window.scrollY), e >= 800 ? o() : r();
        }),
            t.addEventListener("click", () =>
                window.scrollTo({ top: 0, behavior: "smooth" })
            );
    }
})();
new PureCounter({ selector: ".purecounter", delay: 200 });
const lightbox = GLightbox({
    touchNavigation: !0,
    loop: !0,
    selector: ".cover-video,.play-btn",
    autoplayVideos: !0,
});
let reviewSlider = new Swiper(".review-slider", {
    loop: !0,
    slidesPerView: 1,
    autoplay: !0,
});
const socialActions = document.querySelectorAll(".social-action"),
    socialWrap = document.querySelectorAll(".teacher-socials");
socialActions.forEach((e, t) => {
    e.addEventListener("click", function () {
        socialWrap[t].classList.toggle("active");
    });
});
var tooltipTriggerList = [].slice.call(
    document.querySelectorAll('[data-bs-toggle="tooltip"]')
),
    tooltipList = tooltipTriggerList.map(function (e) {
        return new bootstrap.Tooltip(e);
    });
let brandSlider = new Swiper(".brands-wrap", {
    spaceBetween: 10,
    loop: !0,
    autoplay: !0,
    breakpoints: {
        320: { slidesPerView: 1 },
        576: { slidesPerView: 2 },
        768: { slidesPerView: 3 },
        992: { slidesPerView: 4 },
        1367: { slidesPerView: 5 },
    },
}),
    reviewSlider2 = new Swiper(".review-wrap", {
        spaceBetween: 30,
        loop: !0,
        autoplay: !0,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        breakpoints: {
            320: { slidesPerView: 1 },
            768: { slidesPerView: 2 },
            992: { slidesPerView: 3 },
        },
    });
var courseSlider = new Swiper(".course-slider", {
    spaceBetween: 30,
    slidesPerView: "auto",
    centeredSlides: !0,
    loop: !0,
    autoplay: !0,
    navigation: { nextEl: ".button-next", prevEl: ".button-prev" },
}),
    isGridItem = document.querySelector(".grid-item");
if (isGridItem) {
    var onlyGrid = document.querySelector("[data-isotope]");
    if (onlyGrid) {
        var allGrid = document.querySelectorAll("[data-isotope]");
        allGrid.forEach((e) => {
            var t = e.getAttribute("data-isotope"),
                o = JSON.parse(t);
            new Isotope(e, {
                itemSelector: ".grid-item",
                layoutMode: o.layoutMode,
            }).layout();
        });
    }
    var portfolioMenu = document.querySelector(".portfolio-menu");
    if (portfolioMenu) {
        var filterMenu = document.querySelectorAll(".portfolio-menu");
        filterMenu.forEach((e) => {
            var t = e.getAttribute("data-target"),
                o = document.querySelector(t),
                r = o.getAttribute("data-isotope"),
                l = JSON.parse(r),
                n = new Isotope(o, {
                    itemSelector: ".grid-item",
                    transitionDuration: "0.7s",
                    layoutMode: l.layoutMode,
                }),
                c = e.querySelectorAll("li a");
            c.forEach((e) => {
                e.addEventListener("click", function (t) {
                    t.preventDefault();
                    var o = e.getAttribute("data-filter");
                    n.arrange({ filter: o }),
                        c.forEach((e) => e.classList.remove("active")),
                        e.classList.add("active");
                });
            }),
                n.layout();
        });
    }
}
let niceSelector = document.getElementById("select");
niceSelector &&
    NiceSelect.bind(niceSelector, { placeholder: "Choose Subject" });
let productSelect = document.getElementById("product-select");
productSelect &&
    NiceSelect.bind(productSelect, { placeholder: "Sort By: Default" });
let priceSelect = document.getElementById("select-price");
priceSelect && NiceSelect.bind(priceSelect, { placeholder: "Free" });
let courseSelect = document.getElementById("select-course");
courseSelect && NiceSelect.bind(courseSelect, { placeholder: "All" });
let categorySelect = document.getElementById("select-category");
categorySelect &&
    NiceSelect.bind(categorySelect, { placeholder: "Categories" });
let billingSelect = document.getElementById("billingform-country");
billingSelect &&
    NiceSelect.bind(billingSelect, { placeholder: "Select Country" });
let shipSelect = document.getElementById("shipform-country");
shipSelect && NiceSelect.bind(shipSelect, { placeholder: "Select Country" }),
    document.addEventListener(".accordion-button", function (e) {
        var t = e.target.closest(".accordion-item");
        t && t.classList.add("active");
    }),
    document.addEventListener(".accordion-button.collapsed", function (e) {
        var t = e.target.closest(".accordion-item");
        t && t.classList.remove("active");
    }),
    document.querySelectorAll(".course-nav li.nav-item a").forEach((e) => {
        e.addEventListener("click", function (e) {
            e.preventDefault();
            var t = this.getAttribute("href"),
                o =
                    document.querySelector(t).getBoundingClientRect().top +
                    window.pageYOffset -
                    160;
            window.scrollTo({ top: o, behavior: "smooth" });
        });
    });
// var coursePreview = document.querySelector(".course-preview");
// window.addEventListener("scroll", function () {
//     var e = window.scrollY || document.documentElement.scrollTop;
//     coursePreview.style.display = e > 250 ? "none" : "block";
// });
var slider = document.getElementById("priceRange");
if (slider) {
    noUiSlider.create(slider, {
        connect: !0,
        start: [10, 150],
        range: { min: 10, max: 250 },
    });
    var priceValue = document.getElementById("priceRange-value");
    slider.noUiSlider.on("update", function (e) {
        priceValue.innerHTML = e.join(" - ");
    });
}
var productThumb = new Swiper(".product-thumbs", {
    spaceBetween: 10,
    slidesPerView: 4,
    freeMode: !0,
    watchSlidesProgress: !0,
}),
    productSlide = new Swiper(".product-slider", {
        spaceBetween: 10,
        thumbs: { swiper: productThumb },
    });
const productQuantity = (e) => {
    document.querySelectorAll(e).forEach((e) => {
        const t = e.querySelector(".qty-plus"),
            o = e.querySelector(".qty-minus"),
            r = e.querySelector(".qty-count");
        let l = 1;
        t.addEventListener("click", () => {
            l++, (l = l < 10 ? "0" + l : l), (r.value = l);
        }),
            o.addEventListener("click", () => {
                l > 1 && (l--, (l = l < 10 ? "0" + l : l), (r.value = l));
            });
    });
};
productQuantity(".cart-number, .cart-quanty");
var relatedEvents = new Swiper(".related-events", {
    spaceBetween: 30,
    slidesPerView: 2,
    freeMode: !0,
    watchSlidesProgress: !0,
});
const player = new Plyr("#player");