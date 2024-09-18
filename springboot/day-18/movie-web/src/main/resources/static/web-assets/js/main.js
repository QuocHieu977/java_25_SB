document.addEventListener('DOMContentLoaded', function () {
    // Lấy phần tử modal
    const modalTrailer = document.getElementById('modal-trailer');

    // Sử dụng sự kiện Bootstrap để phát hiện khi modal bị đóng
    modalTrailer.addEventListener('hidden.bs.modal', function () {
        // Lấy iframe
        const iframe = document.getElementById('video-trailer');
        // Lưu URL của video
        const videoSrc = iframe.src;
        // Tạm thời xóa src để dừng video
        iframe.src = '';
        // Đặt lại src khi modal đóng
        iframe.src = videoSrc;
    });
});

// review
const movieIdEl = document.getElementById('movie-id');
const modalBodyComment = document.querySelector('#modal-comment .modal-body');
const textEl = document.querySelector('.form-control');
const nameEl = document.querySelector('.title-review')
const ratingEls = document.querySelectorAll('.rating-review');
let selectRating = -1;
let selectComment = -1;
let selectRatingValue = -1;
let messageEl = document.createElement('p');;
let messageEl2 = document.createElement('p');;

ratingEls.forEach((e, index) => {
    e.addEventListener("mouseenter", (e) => {
        e.target.style.cursor = 'pointer';
        highlightStart(index);
        const ratingValue = index + 1;
        nameEl.innerText = `Bạn đã đánh giá ${ratingValue}/10`;
    });

    e.addEventListener("mouseleave", (e) => {
        if (selectRating === -1) {
            highlightStart(-1);
            nameEl.innerText = `Bạn đã đánh giá 0/10`;
        }
    })

    e.addEventListener("click", (el) => {
        selectRating = index +1;
        nameEl.innerText = `Bạn đã đánh giá ${selectRating}/10`;
        highlightStart(selectRating-1);
    });
})

const  highlightStart = (index) => {
    ratingEls.forEach((el, i) => {
        if (i <= index) {
            el.querySelector('i').style.color = "#FFD43B";
        } else {
            el.querySelector('i').style.color = "black";
        }
    })
};

const  btnSubmit = document.getElementById('btn-submit');
btnSubmit.addEventListener('click', () => {
    if (selectRating < 0) {
        messageEl.innerText = "Mời bạn đánh giá phim.";
        messageEl.style.color = 'red';

        if (selectRatingValue < 0) {
            modalBodyComment.appendChild(messageEl);
            selectRatingValue++;
        }

    } else if (textEl.value === "") {
        messageEl2.innerText = "Mời bạn chia sẻ cảm nhận về bộ phim."
        messageEl2.style.color = 'red';

        if (selectComment < 0) {
            modalBodyComment.appendChild(messageEl2);
            selectComment++;
        }
    } else {
        fetch('api/reviews', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                rating: selectRating,
                content: textEl.value,
                movieId: movieIdEl.value,
            })
        })
            .then(res => res.json())
            .then(data => {
                alert('Cảm ơn bạn đã đánh giá');
            })
            .catch(err => {
                console.log("Error: " + err)
            })

        ratingEls.forEach(() => {
            highlightStart(-1);
            nameEl.innerText = `Bạn đã đánh giá 0/10`;
        })
        selectRating = -1;
        textEl.value = "";
        messageEl.remove();
        messageEl2.remove();
    }

})
