console.log("hello world");

// Khai báo biến và không gán giá trị cho biến
let age;
console.log(age);
age = 35;
console.log(age);

// Khai báo biến và gán giá trị cho biến
let email = "hien@techmaster.vn";

// khởi tạo hàng số - không thể thay đổi
const NUMBER = 10;

// Template String (ES6) - truyền biểu thức, fuction... vào trong 1 chuỗi
let name = "Hiếu"
let year = 1997
console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2024 - year} tuổi`);

// Function
// TÍnh tổng 2 số a, b

// regular function
function sum(a, b) {
    return a+b;
}

// function expression
let sum1 = function(a, b) {
    return a+b;
}

// arrow function(es6) -> gần giống lambda function (java 8)
let sum2 = (a, b) => {
    return a+b;
};

 console.log(sum(1, 2));
 console.log(sum1("2", "2"));
 console.log(sum2(3, 2));

 // Trước khi bắt đầu thì số tiền trong ví = 0
let totalMoney = 0;

for (let day = 1; day <= 10; day++) {
    if(day == 8) {
        break;
    }
    if(day % 2 == 0) {
        totalMoney += 200000;
    } else {
        totalMoney += 100000;
    }
}

console.log(totalMoney);
