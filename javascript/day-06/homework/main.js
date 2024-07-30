const btn = document.getElementById('btn');
const imageEl = document.getElementById('image');
const select = document.getElementById('breed-list');

// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
    try{
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");
        renderBreed(res.data.message);
    } catch(err) {
        console.log(err);
    }
}

async function getImageBreed(urlImg) {
    try{
        let res = await axios.get(urlImg);
        renderImageBreed(res.data.message);
    } catch(err) {
        console.log(err);
    }
}

const renderImageBreed = (image) => {
    imageEl.src = image;
};

const renderBreed = (breeds) => {
    // Duyệt qua object breeds -> tạo thẻ option -> gắn vào DOM
    const keys = Object.keys(breeds);
    let html ="";
    keys.forEach(key => {
        html += `
            <option value="${key}">${key}</option>;
        `;
    });
    select.innerHTML = html;
}

btn.addEventListener("click", () => {
    const urlImg = `https://dog.ceo/api/breed/${select.value}/images/random`;
    getImageBreed(urlImg);
});
getBreedList();
