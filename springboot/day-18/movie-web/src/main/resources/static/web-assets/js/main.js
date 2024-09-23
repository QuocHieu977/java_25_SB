// trailer
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

// hiển thị danh sách review
const reviewListEl = document.querySelector(".review-list")
const renderReviews = (reviews) => {
    if(reviews.length === 0) {
        reviewListEl.innerHTML = "<div>Không có công việc trong danh sách</div>";
        return;
    }

    let html ="";

    reviews.forEach(review => {
        html += `
            <div class="row mb-4"">
                    <div class="col-1">
                        <div class="h-100 d-flex align-items-center justify-content-center">
                            <img class="rounded-circle" src="${review.user.avatar}" alt="${review.user}">
                        </div>
                    </div>
                    <div class="col">
                        <div>
                            <div class="d-flex align-items-center mb-1">
                                <div class="fw-bold">${review.user.name}</div>
                                <span class="fs-12 mt-1 ms-2 text-secondary">${formatDate(review.createdAt)}</span>
                            </div>

                            <p class="mb-1 fw-bold">
                                <i class="fa-solid fa-star" style="color: #ffd43b"></i>
                                <span>${review.rating}/10 ${rating(review.rating)}</span>
                        
                            </p>

                            <p class="mb-0 text-secondary">${review.content}</p>
                        </div>
                        
                         <div>
                            <button class="modify-review text-primary border-0 bg-transparent me-1"
                                data-bs-toggle="modal"
                                data-bs-target="#modal-comment"
                                onclick="updateReview(${review.id})">sửa
                            </button>
                            <button class="delete-review text-danger border-0 bg-transparent me-1"
                            onclick="deleteReview(${review.id})">xoá</button>
                        </div>
                    </div>
                </div>`;
    });
    reviewListEl.innerHTML = html;
};

const rating = e => {
    if (e >= 9) {
        return "Xuất Sắc"
    } else if (e >= 7 && e <=8) {
        return "Tuyệt Vời"
    } else if (e >= 5 && e <=6){
        return "Bình Thường"
    } else {
        return "Không Hay"
    }
}

// format date
const formatDate = dateString => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ("0" + (date.getMonth() + 1)).slice(-2); // 09 -> 09 , 011 -> 11
    const day = ("0" + date.getDate()).slice(-2);
    return `${day}-${month}-${year}`;
}
// review
const modalBodyComment = document.querySelector('#modal-comment .modal-body');
const textEl = document.querySelector('.form-control');
const nameEl = document.querySelector('.title-review')
const ratingEls = document.querySelectorAll('.rating-review');
let selectRating = -1;
let selectComment = -1;
let selectRatingValue = -1;
let messageEl = document.createElement('p');
let messageEl2 = document.createElement('p');
let checkReview = true;


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

const validateReview = () => {
    if (selectRating < 0) {
        messageEl.innerText = "Mời bạn đánh giá phim.";
        messageEl.style.color = 'red';

        if (selectRatingValue < 0) {
            modalBodyComment.appendChild(messageEl);
            selectRatingValue++;
        }
        return;
    }

    if (textEl.value === "") {
        messageEl2.innerText = "Mời bạn chia sẻ cảm nhận về bộ phim."
        messageEl2.style.color = 'red';

        if (selectComment < 0) {
            modalBodyComment.appendChild(messageEl2);
            selectComment++;
        }
    }
}

const closeModal = () => {
    const modal = document.querySelector('#modal-comment');
    const bootstrapModal = bootstrap.Modal.getInstance(modal);
    bootstrapModal.hide();
};

const resetData = () => {
    ratingEls.forEach(() => {
        highlightStart(-1);
        nameEl.innerText = `Bạn đã đánh giá 0/10`;
    });
    selectRating = -1;
    textEl.value = "";
    messageEl.remove();
    messageEl2.remove();
}

// btn-submit
document.getElementById('btn-submit').addEventListener('click', async () => {
    const submitButton = document.getElementById('btn-submit');
    const reviewId = parseInt(submitButton.getAttribute('data-review-id'));
    // updateReview
    if (!checkReview) {

        const request = {
            rating: selectRating,
            content: textEl.value,
        }
        validateReview();
        if (request.rating > 0 && request.content.length > 0) {
            try {
                let res = await axios.put(`/api/reviews/${reviewId}`, request);
                const updatedReview = res.data;
                reviews = reviews.map(review => review.id === reviewId ? updatedReview : review);
                renderReviews(reviews);

                closeModal();

                alert("Cập nhật đánh giá thành công!");
            } catch (e) {
                console.log(e);
            }
        }
    } else {
        createReview();
    }
});

// create review
document.querySelector('.btn-create-review').addEventListener('click', () => {
    checkReview = true;
    resetData();
});

const createReview = async () => {
    checkReview = true;
    validateReview();
    // create request
    const request = {
        rating: selectRating,
        content: textEl.value,
        movieId: movie.id
    }
    try {
        let res = await axios.post("/api/reviews", request);
        // insert review to reviews and the frist
        reviews.unshift(res.data);
        renderReviews(reviews);

        // reset data
        resetData();

        // close modal
        closeModal();
    } catch (error) {
        console.log(error);
    }
}

// update review
const updateReview = async (reviewId) => {
    checkReview = false;
    reviews.forEach(review => {
        if ((review.id === reviewId)) {
            selectRating = review.rating;
            ratingEls.forEach(() => {
                highlightStart(selectRating - 1);
            })
            textEl.value = review.content;
            nameEl.innerHTML = `Bạn đã đánh giá ${selectRating}/10`
        }
    });

    // save reviewId
    document.getElementById('btn-submit')
        .setAttribute('data-review-id', reviewId);
};

// delete review
const deleteReview = async (reviewId) => {
    try {
        await axios.delete(`/api/reviews/${reviewId}`);

        //update review when removed
        reviews = reviews.filter(review => review.id !== reviewId);

        // render review
        renderReviews(reviews);
    } catch (e) {
        console.log(e);
    }

    document.querySelector('.delete-review')
        .setAttribute('data-review-id', reviewId);
};
