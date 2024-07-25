const words = ["xin chào", "hello", "nihao", "a", "hi", "ci"];

const randomHexColor = () => {
    const randomNumber = Math.floor(Math.random() * 0xffffff);
    const randomColor = randomNumber.toString(16).padStart(6, '0');
    return '#' + randomColor;
}
const randomRGBColor = () => {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    const randomColor = `rgb(${r}, ${g}, ${b})`;
    return randomColor;
}

// HTML-atribute
function changeContent() {
    const text = document.querySelector('#text');
    const radomNumber = Math.floor(Math.random() * words.length);
    text.innerText = words[radomNumber];
}

// DOM property
const btn2 = document.querySelector('#btn-2');
btn2.onclick = () => {
    text.style.color = randomHexColor();
}

// addEventListener
const btn3 = document.querySelector('#btn-3');
btn3.addEventListener('click', () => {
    text.style.backgroundColor = randomRGBColor();
});