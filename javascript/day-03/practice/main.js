//1
const text = document.body.querySelector('#text');
text.style.color = '#777';
text.style.fontSize = '2rem';
text.innerHTML = 'Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.';

// 2
const li = document.querySelectorAll('#text + ul li');
for (let i = 0; i < li.length; i++) {
    li[i].style.color = 'blue';
}

//3
const li8 = document.createElement('li');
li8.innerText = 'Item8';
const li9 = document.createElement('li');
li9.innerText = 'Item9';
const li10 = document.createElement('li');
li10.innerText = 'Item10';

const list = document.querySelector('#list');
list.appendChild(li8);
list.appendChild(li9);
list.appendChild(li10);

const li1 = document.querySelector('#list li:nth-child(1)');
li1.style.color = 'red';

const li3 = document.querySelector('#list li:nth-child(3)');
li3.style.backgroundColor = '#00ffc0';

const li4 = document.querySelector('#list li:nth-child(4)');
list.removeChild(li4);

const liNew = document.createElement('li');
const li4_2 = document.querySelector('#list li:nth-child(4)');
liNew.innerText = 'this is a new li';
list.insertBefore(liNew, li4_2);

list.children[2].insertAdjacentHTML("afterend", "<li>this is tag new li</li>")