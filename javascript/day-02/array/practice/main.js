
let repeatString = (str) => {
    let arrStr = [];

    for(let i = 0; i < 10; i++){
        arrStr[i] = str
    }
    return arrStr.toString().replaceAll(",", "-");
}

console.log(repeatString('a'));

let isSymmertricString = (str) => {
    str = str.toLowerCase();
    str = str.replaceAll(" ", "");

    let reversedStr = "";

    for(let i = str.length-1; i >= 0; i--) {
        reversedStr += str[i];
    }


    
    return str == reversedStr;
}

console.log(isSymmertricString('aloola'));

let findMinNumber = (number) => {
    let arrNumber = [];
    for(let i = 0; i < number.length; i++) {
        arrNumber[i] = number[i];
    }

    return arrNumber.sort().toString();
}

console.log(findMinNumber("50974"));


let checkElementExists = (arr, value) => {
    return arr.some(e => e == value);
}

console.log(checkElementExists([1,2,3,4,5], 5));

let getElementGreater = (arr, value) => {
    return arr.filter((e) => e > value);
}

console.log(getElementGreater([1,2,3,4,5], 3));

