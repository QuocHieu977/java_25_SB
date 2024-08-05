const btnPost = document.querySelector('.btn-posts');
const btnPhoto = document.querySelector('.btn-photos');
const btnAlbum = document.querySelector('.btn-albums');
const h1El = document.getElementById('type');
const listEl = document.querySelector('.list');

const API_URL_POSTS = "https://jsonplaceholder.typicode.com/posts";
const API_URL_ALBUMS = "https://jsonplaceholder.typicode.com/albums";
const API_URL_PHOTOS = "https://jsonplaceholder.typicode.com/photos";

const getAllData = async(url) => {
    try {
        let res = await axios.get(url);
        renderEl(res.data);
    } catch (error) {
        console.log(error);
    }  
};

const renderEl = (arr) => {
    
    let html = "";
    arr.forEach(e => {
        html += `
            <li class="item">${e.title}}</li>
        `;
    })
    listEl.innerHTML = html;
};

btnPost.addEventListener('click', () => {
    btnPost.classList.add('active');
    btnPhoto.classList.remove('active');
    btnAlbum.classList.remove('active');
    h1El.innerText = "Type: posts";
    getAllData(API_URL_POSTS);
});

btnPhoto.addEventListener('click', () => {
    btnPhoto.classList.add('active');
    btnAlbum.classList.remove('active');
    btnPost.classList.remove('active');
    h1El.innerText = "Type: photos";
    getAllData(API_URL_PHOTOS);
});

btnAlbum.addEventListener('click', () => {
    btnAlbum.classList.add('active');
    btnPost.classList.remove('active');
    btnPhoto.classList.remove('active');
    h1El.innerText = "Type: albums";
    getAllData(API_URL_ALBUMS);
});

getAllData(API_URL_POSTS);