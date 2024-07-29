
//Lab-1
const para = document.querySelector('p');
let content = para.innerText.split(" ").map(e => e.length >=8
    ? `<span style="background-color: yellow">${e}</span>`
    : e
).join(" ");

para.innerHTML = content;

// Lab-2
//Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
const ul = document.querySelector('#list');
for(let i = 8; i <=10; i++){
    let li = document.createElement("li");
    li.innerText = `Item ${i}`;
    ul.appendChild(li);
}
//Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
document.querySelector("#list li:nth-child(1)").style.color = "red"; 

//Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
document.querySelector("#list li:nth-child(3)").style.backgroundColor = "green";

//Remove thẻ <li> 4
let li4 = document.querySelector("#list li:nth-child(4)");
ul.removeChild(li4);

//Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
let liNew = document.createElement("li");
liNew.innerText = "Item new";
let li5 = document.querySelector("#list li:nth-child(4)");
ul.insertBefore(liNew, li5);

//Thêm 1 nút “add” + 1 ô input để nhập text. Mỗi khi bấm nút thêm 1 thẻ <li> có nội dung là nội dung trong ô input vào cuối danh sách
const btnAdd = document.createElement("button");
const input = document.createElement("input");
input.placeholder = "please enter your info...."
btnAdd.innerText = "Add";
document.body.appendChild(input);
document.body.appendChild(btnAdd);

btnAdd.addEventListener("click", () => {
    let li = document.createElement("li");
    if(input.value === "") {
        li.innerText = "Item New";
    } else {
        li.innerText = input.value;
    }
    ul.appendChild(li);
    input.value = "";
});

//Thêm 1 nút “remove”. Chức năng để xóa thẻ <li> cuối cùng của danh sách
const btnRemove = document.createElement("button");
btnRemove.innerText = "Remove";
document.body.appendChild(btnRemove);

btnRemove.addEventListener("click", () => {
    const liRemove = document.querySelector("#list li:last-child");
    if(liRemove) {
        ul.removeChild(liRemove);
    }
});

//Thêm 1 nút “Hide” trên đầu của danh sách <ul>.
const btnDisplay = document.createElement("button");
btnDisplay.innerText = "Hide";
document.body.insertBefore(btnDisplay, ul);

//Khi bấm vào “Hide” thì <ul> sẽ ẩn. Đồng thời label của nút “Hide” => “Show”
btnDisplay.addEventListener("click", () => {
    ul.classList.toggle("hide");

    if(ul.classList.contains("hide")) {
        btnDisplay.innerText = "Show";
    } else {
        btnDisplay.innerText = "Hide";
    }
});

//Và ngược lại Khi bấm vào “Show” thì <ul> sẽ hiện. Đồng thời label của nút “Show” => “Hide”
