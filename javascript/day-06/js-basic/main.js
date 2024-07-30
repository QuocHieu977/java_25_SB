//  API
// Application Programming Interface
// Giao diện lập trình ứng dụng -> các bộ quy tắc cho phép các ứng dụng/hệ thông có thể giao tiếp kết nối làm vc với nhau
// Các điểm truy cập cho phép các ứng dụng Client giao tiếp và truy cập vào

const promise = new Promise((resolve, reject) => {
    // lấy dữ liệu từ cơ sở dữ liệu
    let data;
    setTimeout(() => {
        data = "Dữ liệu";


        resolve(data); // công việc hoàn thành -> khi gọi resolve -> kích hoạt then
    }, 3000);// giả sử mất 3s để query dữ liệu
});

promise.then((data) => {
    console.log("Tôi nhận được data", data);
});

// gọi API và chả về kq -> convert dữ liệu sang json -> kq vẫn là promise

const getProducts = () => {
    return fetch('https://dummyjson.com/products').then(res => {
        return res.json();
    })
};

function renderProducts(products = []) {
    const productListEl = document.querySelector(".products");
  
    const html = products
      .map(function (product) {
        const productHtml = [
          `<div>`,
          `<div class="product-image">`,
          `<img src="${product.thumbnail}" alt="${product.title}" />`,
          `</div>`,
          `<div class="product-info">`,
          `<h3 class="product-title">${product.title}</h3>`,
          `<p class="product-price">${product.price}</p>`,
          `</div>`,
          `<div>`,
          `<button data-product-id="${product.id}" class="btn-add-to-cart">Add to cart</button>`,
          `</div>`,
          `</div>`,
        ].join("");
  
        return productHtml;
      })
      .join("");
  
    productListEl.innerHTML = html;
}

const setupEventHandler =() => {
    document.querySelectorAll('button.btn-add-to-cart').forEach(button => {
        button.addEventListener('click', () => {
            const productId = button.getAttribute('data-product-id');
            console.log(productId);
            alert("Add product with ID " + productId + " to cart");
        });
    })
};

const main = () => {
    getProducts().then(data => {
        renderProducts(data.products);
    }).then(() => {
        setupEventHandler();
    });
}
main();
