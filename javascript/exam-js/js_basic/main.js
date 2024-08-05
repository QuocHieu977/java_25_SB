//Bai 1: Viết function truyền vào mảng các chuỗi có độ dài bất kỳ. Kết quả trả về là 1 mảng các chuỗi có độ dài lớn nhất
const words = ['aba', 'aa', 'ad', 'c', 'vcd'];

const getStringHasMaxLength = (arr) => {
    const maxLength = arr.reduce((max, str) => Math.max(max, str.length ), 0);
    
    wordLength = arr.filter(word => word.length === maxLength);
    return wordLength;
};
console.log(getStringHasMaxLength(words));

// Bài 2:
//Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age > 25 và isStatus = true
//Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age tăng dần
const users = [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
];

const filterUsers = (users) => {
    return users.filter((user) => user.isStatus === true && user.age > 25);
};

const sortByUsers = (users) => {
    return users.sort((a, b) => a.age - b.age);
};

console.log(filterUsers(users));
console.log(sortByUsers(users));

//Bài 3. Viết function truyền vào 1 mảng các chuỗi. Trả về Object hiển thị xem mỗi phần tử trong mảng xuất hiện bao nhiêu lần
const words2 = ["one", "two", "three", "one", "one", "three"];

const getCountElement = (words) => {
    const wordCount = {};

    words.forEach(word => {
        if(wordCount[word]) {
            wordCount[word]++;
        } else {
            wordCount[word] = 1;
        }
    });

    return wordCount;
};

console.log(getCountElement(words2));