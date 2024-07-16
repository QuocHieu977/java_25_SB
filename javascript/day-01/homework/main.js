// Ex-1
let calculateFactorial = (n) => {
    let result = 1;
    for(let i=n; i>0; i--){
        result *= i;
    }
    return result;
}

console.log(calculateFactorial(5));

// Ex-2
let reverseString = (str) => {
    let reversed = "";

    for(let i=str.length; i>=0; i--){
        reversed += str[i];
    }
    return reversed;
}
console.log(reverseString("hello"));

// Ex-3
let translateLanguages = (str) => {
    let translated;
    switch(str) {
        case "en":
            translated = "hello";
            break;
        case "vn":
            translated = "xin chào";
            break;
        case "cn":
            translated = "nihao";
            break;
        case "kr":
            translated = "an-nyeong-ha-se-yo";
            break;
        case "jp":
            translated = "konnichiwa";
            break;
        default:
            translated = "Mã quốc gia không xác định";
            break;           
    }
    return translated;
}

console.log(translateLanguages("vn"));

// Ex-4
let subString = (str) =>  {
    let subString = "";

    if(str.length > 15) {
        for(let i = 0; i < str.length; i++) {
            if(i<10) {
                subString += str[i]
            }
        }
        return subString+"...";
    } else {
        return str;
    }
}

console.log(subString("xinchaocacbandenvoiTechmaster"));