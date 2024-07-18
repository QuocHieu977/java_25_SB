let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

console.log("----------- câu 1-----------");
const printProduct = (arr) => {
    arr.map((e) => {
        console.log(`${e.name} - ${e.brand} - ${e.price} - ${e.count}`)
    })
}
printProduct(products);

console.log("----------- câu 2------------");
const totalAmount = (arr) => {
    let sum = arr.reduce((total, e) => {
        return total + (e.price * e.count)
    }, 0)
    return sum;
}
console.log(totalAmount(products));

console.log("----------- câu 3 ------------");
const findByBrand = (arr, nameBrand) => {
    return arr.filter((e) => e.brand == nameBrand);
}
printProduct(findByBrand(products, 'Apple'));

console.log("----------- câu 4------------");
const findByPrice = (arr, price) => {
    return arr.filter((e) => e.price > price);
}
printProduct(findByPrice(products, 20000000));

console.log("------------ câu 5 -----------");
const findByName = (arr, nameProduct) => {
    return arr.filter((e) => e.name.toLowerCase().includes(nameProduct.toLowerCase()));
}
printProduct(findByName(products, "Pro"));

console.log("----------- câu 6 ------------");
const addProduct = (arr, product) => {
    arr.push(product);
    return arr;
}

let product = {
        name: "Samsung Galaxy S24",
        price: 51000000,
        brand: "Samsung",
        count: 1,
}
printProduct(addProduct(products, product));

console.log("------------ câu 7 -----------");
const deleteProduct = (arr, dName) => {

    arr.forEach((e, index) => {
        if(e.brand === dName) {
            arr.splice(index, 1);
        }
    })
    return arr;
}
printProduct(deleteProduct(products, "Samsung"));

console.log("----------- câu 8 ------------");
const sortByPrice = (arr) => {
    arr.sort(function(a, b) {
        return a.price - b.price
    })
}

sortByPrice(products);
printProduct(products);

console.log("----------- câu 9 ------------");
const sortByQuantity = (arr) => {
    arr.sort((a, b) => {
        return b.count - a.count;
    })
}

sortByQuantity(products);
printProduct(products);

console.log("----------- câu 10 ------------");
const randomNumber = (n, max, min) => {
    let resultArr = [];

    while(resultArr.length < n) {
        const randomInteger = Math.floor(Math.random() * (max - min + 1)) + min;
        if(!resultArr.includes(randomInteger)) {
            resultArr.push(randomInteger);
        }
    }

    return resultArr;
}

const getProduct = (arr1, arr2) => {
    let newArr = [];
    for(let i = 0; i < arr2.length; i++) {
        newArr.push(arr1[i]);
    }
    return newArr;
}

printProduct(getProduct(products, randomNumber(2, products.length-1, 0)))