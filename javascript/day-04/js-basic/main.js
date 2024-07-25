function sayHello() {
    alert('xin chao cac ban 1')
}

const btn2 = document.getElementById('btn2');
btn2.onclick = () => {
    alert('xin chao cac ban 2');
};

const btn3 = document.getElementById('btn3');
btn3.addEventListener('click', () => {
    alert('xin chao bạn 3');
});

const btnPlay = document.getElementById('play');
const btnPause = document.getElementById('pause');
const timeEl = document.getElementById('time');
const messageEl = document.getElementById('message');

let time = 10;

let interval;

btnPlay.addEventListener('click', () => {
    interval = setInterval(() => {
        time--;
        timeEl.innerText = `${time}s`;
        if(time === 0) {
            messageEl.innerText = "Hết giờ";
            clearInterval(interval);
            time = 10;
        }
    }, 1000);
});

btnPause.addEventListener('click', () => {
    clearInterval(interval);
});

// document.addEventListener('click', (e) => {
//     const currentEl = document.querySelector('.cricle');

//     // if(currentEl) {
//     //     currentEl.parentElement.removeChild(currentEl);
//     // }

//     if(currentEl) {
//         currentEl.style.left = `${e.offsetX - 50}px`;
//         currentEl.style.top = `${e.offsetY -50}px`;
//         return;
//     }

//     const cricleEl = document.createElement('div');
//     cricleEl.classList.add('cricle');

//     cricleEl.style.left = `${e.offsetX - 50}px`;
//     cricleEl.style.top = `${e.offsetY -50}px`;

//     document.body.appendChild(cricleEl);



// });

// tìm kiếm user
const users = [
    {id: 1, name: "Trần Hùng Anh"},
    {id: 2, name: "Trịnh Thu Hà"},
    {id: 3, name: "Trần Hùng Em"},
    {id: 4, name: "Trịnh Xuân Chánh"},
    {id: 5, name: "Nguyễn Châu Tuấn"},
    {id: 6, name: "Ngô Quốc Hiêu"},
]

const inputEl = document.querySelector('#input-name');
const btnShowAll = document.querySelector('#btn-show-all');
const listUsers = document.querySelector('#list');

const renderUsers = (users) => {
    let html = "";
    users.forEach(user => {
        html += `<li>${user.id} - ${user.name}</li>`
    });
    listUsers.innerHTML = html;
};

inputEl.addEventListener('keydown', e => {
    if(e.key === "Enter") {
        const keywords = e.target.value;
        const result = users.filter(e => 
            e.name.toLowerCase().includes(keywords.toLowerCase()));
    
        renderUsers(result);
        console.log(e);
    }
});

btnShowAll.addEventListener('click', () =>{
    renderUsers(users);
});

renderUsers(users);

// Scroll back to top

const btnTop = document.getElementById("back-to-top");
window.addEventListener('scroll', () =>{
    
    if(document.documentElement.scrollTop > 300) {
        // hiển thị back-to-top
        btnTop.classList.remove("hide");
    } else {
        //ẩn đi
        btnTop.classList.add("hide");
    }
    
});
btnTop.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    })
});
