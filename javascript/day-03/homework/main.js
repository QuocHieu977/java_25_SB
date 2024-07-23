//Highlight tất cả các từ có độ dài lớn hơn hoặc bằng 5 ký tự trong đoạn văn (background = “yellow”)
const desciption = document.querySelector('p').innerText;
const words = desciption.split(" ");
const hilghtWords = words.map(e => {
    if(e.length >= 5) {
        return `<span style=background-color:yellow>${e}</span>`
    } else {
        return e;
    }
});
document.querySelector('p').innerHTML = hilghtWords.join(" ");

//Thêm link hiển thị text “facebook” link đến trang facebook.com ở sau thẻ p
const link = document.createElement('a');
link.href = 'http://www.facebook.com';
link.innerText = 'facebook.com';
document.body.appendChild(link);

//Đếm số từ có trong đoạn văn. Tạo 1 thẻ div để hiển thị số từ
const countWords = hilghtWords.map(e => {
    if(e.length >=5) {
        return e +`<div style=display:inline-block>[${e.length - 43}]</div>`;
    } else {
        return e +`<div style=display:inline-block>[${e.length}]</div>`;
    }
})
document.querySelector('p').innerHTML = countWords.join(" ");

//Thay thế ký hiệu (, - dấu phẩy) => 🤔 và (. - dấu chấm) => 😲
document.querySelector('p').innerHTML = countWords.join(" ").replace(/,/g, '🤔').replace(/\./g, '😲');