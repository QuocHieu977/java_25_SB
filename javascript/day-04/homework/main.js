
//bài 1
const inputEl = document.querySelector(".input-password");

//btn
const btnShow = document.querySelector(".btn-show");
btnShow.addEventListener("click", () => {
    if(btnShow.innerText === "Show") {
        inputEl.type = "text";
        btnShow.innerText = "Hide";
        iconBtn.classList.remove("fa-eye-slash");
        iconBtn.classList.add("fa-eye");
    } else {
        inputEl.type = "password";
        btnShow.innerText = "Show";
        iconBtn.classList.remove("fa-eye");
        iconBtn.classList.add("fa-eye-slash");
    }
    
});

// icon
const iconBtn = document.querySelector(".icon");
iconBtn.addEventListener("click", () => {
    if(inputEl.type === "password") {
        inputEl.type = "text";
        iconBtn.classList.remove("fa-eye-slash");
        iconBtn.classList.add("fa-eye");
        btnShow.innerText = "Hide";
    } else {
        inputEl.type = "password";
        iconBtn.classList.remove("fa-eye");
        iconBtn.classList.add("fa-eye-slash");
        btnShow.innerText = "Show";
    }
});

