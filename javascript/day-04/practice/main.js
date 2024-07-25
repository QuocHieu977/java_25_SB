const text = document.getElementById("text");
function changeContent() {
    text.innerText = "Đây là nội dung bất kỳ";
}

const btn_2 = document.querySelector('#btn-2');

const randomHex = () => {
    const randomNumber = Math.floor(Math.random() * 0xffffff);
    const randomColor = randomNumber.toString(16).padStart(6, '0');
    return '#' + randomColor;
}
btn_2.onclick = function() {
    text.style.color = randomHex();
}

const btn_3 = document.querySelector('#btn-3');
const randomRGB = () => {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    const randomColor = `rgb(${r}, ${g}, ${b})`;
    return randomColor;
}
btn_3.addEventListener('click', function() {
    text.style.backgroundColor = randomRGB();
})

//
// document.addEventListener('click', function(e) {
//     const circle = document.createElement('div');
//     circle.classList.add('circle');

//     circle.style.left = e.pageX + 'px';
//     circle.style.top = e.pageY+ 'px';

//     document.body.appendChild(circle);
    
// });


// b1

document.addEventListener('keydown', function(event) {
    if (event.keyCode === 13) {
      const circle = document.createElement('div');
      circle.classList.add('circle');

      const maxX = window.innerWidth - 50;
      const maxY = window.innerHeight - 50;
      const randomX = Math.floor(Math.random() * maxX);
      const randomY = Math.floor(Math.random() * maxY);
      circle.style.left = randomX + 'px';
      circle.style.top = randomY + 'px';
      document.body.appendChild(circle);

    } else if(event.keyCode === 32) {
        const square = document.createElement('div');
        square.classList.add('square');

        const maxX = window.innerWidth - 50;
        const maxY = window.innerHeight - 50;
        const randomX = Math.floor(Math.random() * maxX);
        const randomY = Math.floor(Math.random() * maxY);
        square.style.left = randomX + 'px';
        square.style.top = randomY + 'px';
        document.body.appendChild(square);
        console.log("day la hinh vuong")
    } else {
        document.body.style.backgroundColor = randomRGB();
    }
  });


