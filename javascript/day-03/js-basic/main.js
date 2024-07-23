//Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const heading = document.getElementById('heading');
heading.style.color = 'red';
heading.style.textTransform = 'uppercase';

//Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const para = document.querySelectorAll('.para')
for(let i = 0; i < para.length; i++){
    para[i].style.color = 'blue';
    para[i].style.fontSize = '20px';
}

//Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const link = document.createElement('a');
link.href= 'http://www.facebook.com';
link.innerText = "Facebook.com";
const para_3 = document.querySelector('.para-3');
document.body.insertBefore(link, para_3); 

//Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.querySelector('#title');
title.innerText = 'Đây là thẻ tiêu đề';

//Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
const desciption = document.querySelector('.description');
desciption.classList.add('text-bold');
const text_bold = document.querySelector('.text-bold');
text_bold.style.fontWeight = '600';

//Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const para_3_2 = document.querySelector('.para-3');
const button = document.createElement('button');
button.innerText = 'Click me';
document.body.replaceChild(button, para_3_2);

//Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
const para_2 = document.querySelector('.para-2');
const paraCopy = para_2.cloneNode(true);
paraCopy.innerText = 'para copy';
document.body.insertBefore(paraCopy, button);

//Xóa thẻ có class=“para-1”
const p1 = document.querySelector('.para-1');
document.body.removeChild(p1);


