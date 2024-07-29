// Int data
const todos =[
    {id:1, title: "Đi chơi", status: true},
    {id:2, title: "Làm bài tập", status: false},
    {id:3, title: "Đá bóng", status: true},
    {id:4, title: "Đi bơi", status: false},
];

//Hiển thị danh sách công việc ban đầu, nếu không có công việc nào trong danh sách thì hiển thị Danh sách công việc trống
const todoContainer = document.querySelector("ul");
const renderTodo = (todos) => {
    if(todos.length == 0) {
        todoContainer.innerHTML = "<li>Không có công việc trong danh sách</li>";
        return;
    }
    let html ="";
    todos.forEach(todo => {
        html += `
            <li>
                <input onclick="isComplete(${todo.id})" type="checkbox" ${todo.status ? "checked" : ""}>
                <span class=${todo.status ? "active" : ""}>${todo.title}</span>
                <button onclick="editTodo(${todo.id})">Edit</button>
                <button onclick="deleteTodo(${todo.id})">Delete</button>
            </li>`;
    });
    todoContainer.innerHTML = html;
};
renderTodo(todos);

const createId = () => {
    //return Math.floor(Math.random() * 1000000);
    if(todos.length === 0) {
        return 1;
    }
    return Math.max(...todos.map(todo => todo.id)) + 1;
};

//Thêm công việc mới (nếu title rỗng -> alert “Tên công việc không được để trống”)
const inputTodo = document.querySelector('#input-todo');
const btnAdd = document.querySelector('#btn-add');

btnAdd.addEventListener("click", () => {
    // lấy ra dữ liệu trong ô input
    const title = inputTodo.value.trim();
    if(title.length == 0) {
        alert("Tên công việc không được để trống");
        return;
    }
    
    // tạo 1 công việc mới
    const newTodo = {
        id: createId(),
        title: title,
        status: false
    }
    // thêm vào cuối mảng todos
    todos.push(newTodo);

    //render lại giao diện
    renderTodo(todos);

    // clear dữ liệu trong ô input
    inputTodo.value = "";
});

//Xóa công việc (cần confirm trước khi xóa, sử dụng window.confirm)
const deleteTodo = (id) => {
    const isDelete = confirm("Are you sure you want to delete?");

    if(isDelete) {
        const index = todos.findIndex(todo => todo.id === id);
        todos.splice(index, 1);
        renderTodo(todos);
        return;
    }
};

//Chỉnh sửa tiêu đề công việc (sử dụng window.prompt cho đơn giản)
const editTodo = (id) => {
    const todo = todos.find(todo => todo.id === id);
    const newTodo = prompt("Cập nhật công việc", todo.title);
    if(newTodo === null) return;
    if(newTodo.trim().length ===0) {
        alert("Tên công việc không để trống");
        return;
    }
    todo.title = newTodo
    renderTodo(todos);   
};
//Thay đổi trạng thái công việc (status)
const isComplete = (id) => {
    const todo = todos.find(todo => todo.id === id);
    todo.status = !todo.status;
    renderTodo(todos);
};