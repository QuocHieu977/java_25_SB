// bài 2
const mainEl = document.querySelector(".main-container");
const counterEl = document.querySelector("#counter");
const btnNext = document.querySelector(".nextBtn");
const btnPrev = document.querySelector(".prevBtn");
let count = 0;

btnNext.addEventListener("click", () => {
    count++;
    updateCounter();
});

btnPrev.addEventListener("click", () => {
    count--;
    updateCounter();
});

function updateCounter() {
    counterEl.innerText = count;
    if(count > 0) {
        counterEl.style.color = "green";
    } else if(count === 0) {
        counterEl.style.color = "#333333";
    } else {
        counterEl.style.color = "red";
    }
}







