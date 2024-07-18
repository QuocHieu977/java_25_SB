

const findMax = (arr) => {
    return Math.max(...arr); // ES6: Spread operator
}

console.log(findMax([1,4,6,0,2,7]));

const modulo2 = (arr) => {
    let newArr = [];
    arr.forEach((value) => {
        newArr.push(value%2);
    })
    return newArr;
}

console.log(modulo2([1,4,6,0,2,7]));

const repeatString = (str) => {
    let arrStr = [];

    for(let i = 0; i < 10; i++){
        arrStr[i] = str
    }
    return arrStr.join("-");
}

console.log(repeatString('a'));