// Int data
let todos =[];

// Gọi API để lấy dữ liệu và hiển thị bên ngoài giao diện
const API_URL = "http://localhost:8000/todos";

const getAllTodos = async () => {
    try {
        let res = await axios.get(API_URL);
        todos = res.data;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }
};

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

// const createId = () => {
//     //return Math.floor(Math.random() * 1000000);
//     if(todos.length === 0) {
//         return 1;
//     }
//     return Math.max(...todos.map(todo => todo.id)) + 1;
// };

//Thêm công việc mới (nếu title rỗng -> alert “Tên công việc không được để trống”)
const inputTodo = document.querySelector('#input-todo');
const btnAdd = document.querySelector('#btn-add');

btnAdd.addEventListener("click", async () => {
    // lấy ra dữ liệu trong ô input
    const title = inputTodo.value.trim();
    if(title.length == 0) {
        alert("Tên công việc không được để trống");
        return;
    }
    
    // tạo 1 công việc mới
    const newTodo = {

        title: title,
        status: false
    }
    

    // Gọi API để thêm công việc lên server
    try { 
        let res = await axios.post(API_URL, newTodo);  

        // thêm vào cuối mảng todos
        todos.push(res.data); 

        //render lại giao diện
        renderTodo(todos);

        // clear dữ liệu trong ô input
        inputTodo.value = "";
    } catch (error) {
        console.log(error);
    } 
});

//Xóa công việc (cần confirm trước khi xóa, sử dụng window.confirm)
const deleteTodo = async (id) => {
    const isDelete = confirm("Are you sure you want to delete?");

    if(isDelete) {
        // Gọi API để xoá công việc
        try {
            await axios.delete(`${API_URL}/${id}`);

            const index = todos.findIndex(todo => todo.id === id);
            todos.splice(index, 1);
            renderTodo(todos);
            return;
        } catch (error) {
            console.log(error);
        }
    }
};

//Chỉnh sửa tiêu đề công việc (sử dụng window.prompt cho đơn giản)
const editTodo = async(id) => {
    const todo = todos.find(todo => todo.id === id);
    const newTodo = prompt("Cập nhật công việc", todo.title);
    if(newTodo === null) return;
    if(newTodo.trim().length ===0) {
        alert("Tên công việc không để trống");
        return;
    }
    // Dữ liệu để gửi lên server
    const data = {
        "title": newTodo,
        "status": todo.status
    };        

    // Gọi API để gửi thông tin chỉnh sửa lên server
    try {
        let res = await axios.put(`${API_URL}/${id}/`, data);
        todo.title = res.data.title;

        // await axios.put(`${API_URL}/${id}/`, data);
        // todo.title = newTodo;
        renderTodo(todos);
    } catch (error) {
        console.log(error);
    }   
};

//Thay đổi trạng thái công việc (status)
const isComplete = async (id) => {
    const todo = todos.find(todo => todo.id === id);
    todo.status = !todo.status;
    const data = {
        "title": todo.title,
        "status": todo.status
    };

    try {
        await axios.put(`${API_URL}/${id}/`, data);
        renderTodo(todos);
    } catch (error) {
        console.assert(error);
    }
    renderTodo(todos);
};

getAllTodos();

// Tìm kiếm
inputTodo.addEventListener("change", async() => {
    const title = inputTodo.value.trim();
    try {
        let res = await axios.get(`${API_URL}?q=${title}`);
        renderTodo(res.data);
    } catch (error) {
        console.log(error);
    }
});