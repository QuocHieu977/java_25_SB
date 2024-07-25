// bài 2
const mainEl = document.querySelector(".main-container");
const counterEl = document.querySelector("#counter");
const btnNext = document.querySelector(".nextBtn");
const btnPrev = document.querySelector(".prevBtn");

mainEl.style.backgroundColor = "#333333";

function updateCounter() {
    if(Number(counterEl.innerText) > 0) {
        mainEl.style.backgroundColor = "green";
    } else if(Number(counterEl.innerText) === 0) {
        mainEl.style.backgroundColor = "#333333";
    } else {
        mainEl.style.backgroundColor = "red";
    }
}

btnNext.addEventListener("click", () => {
    counterEl.innerText = Number(counterEl.innerText) + 1;
    updateCounter();
});

btnPrev.addEventListener("click", () => {
    counterEl.innerText = Number(counterEl.innerText) - 1;
    updateCounter();
});





