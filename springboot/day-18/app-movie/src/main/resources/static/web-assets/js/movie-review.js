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
                        
                         <div style="${idCurrentUser !== review.user.id ? 'display: none' : ''}">
                            <button class="modify-review text-primary border-0 bg-transparent me-1"             
                                     onclick="openModalUpdateReview(${review.id})" 
                                    >sửa
                            </button>
                            <button class="delete-review text-danger border-0 bg-transparent me-1"
                                    onclick="deleteReview(${review.id})">xoá
                            </button>
                        </div>
                    </div>
            </div>`;
    });
    reviewListEl.innerHTML = html;
};

const render = () => {
    $('#review-pagination').pagination({
        dataSource: reviews,
        pageSize: 5,
        callback: function(data, pagination) {
            renderReviews(data);
        }
    })
}

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
const modalReviewObj = new bootstrap.Modal('#modal-comment', {
    keyboard: false
})
const modalReviewEL = document.getElementById('modal-comment');
let selectRating = -1;

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
        toastr.error("Vui lòng hãy nhập đánh giá");
    }

    if (textEl.value === "") {
        toastr.error("Vui lòng nhập nội dung bình luận");

    }
}

const resetReview = () => {
    ratingEls.forEach(() => {
        highlightStart(-1);
    });
    nameEl.innerText = `Bạn đã đánh giá 0/10`;
    selectRating = -1;
    textEl.value = "";
}

let idUpdate = null;

modalReviewEL.addEventListener('hidden.bs.modal', (event) => {
    resetReview();
    idUpdate = null;
});

const openModalUpdateReview = (reviewId) => {
    const review = reviews.find(review => review.id === reviewId);
    selectRating = review.rating;
    highlightStart(selectRating);
    textEl.value = review.content;
    nameEl.value = `Bạn đã đánh giá ${selectRating}/10`;
    modalReviewObj.show();
    idUpdate = reviewId;
};

// btn-submit
document.getElementById('btn-submit').addEventListener('click',  () => {
    if (idUpdate) {
        updateReview();
    } else {
        createReview();
    }
});

// create review
const createReview = async () => {
    validateReview();
    // create request
    const request = {
        rating: selectRating,
        content: textEl.value,
        movieId: movie.id
    }
    if (request.rating > 0 && request.content.length > 0) {
        try {
            let res = await axios.post("/api/reviews", request);
            // insert review to reviews and the frist
            reviews.unshift(res.data);
            render(reviews);
            // reset data
            resetReview();
            // close modal
            modalReviewObj.hide();
            toastr.success("Tạo bình luận thành công");
        } catch (error) {
            console.log(error);
            toastr.error(error.response.data.message);
        }
    }
}

// update review
const updateReview = async () => {
    validateReview();
    const request = {
        rating: selectRating,
        content: textEl.value,
    }

    if (request.rating > 0 && request.content.length > 0) {
        try {
            let res = await axios.put(`/api/reviews/${idUpdate}`, request);
            const index = reviews.findIndex(review => review.id === idUpdate);
            reviews[index] = res.data;
            render(reviews);
            modalReviewObj.hide();
            toastr.success("Cập nhật bình luận thành công");
        } catch (e) {
            console.log(e)
            toastr.error(e.response.data.message);
        }
    }
};

// delete review
const deleteReview = async (reviewId) => {
    const isConfirm = confirm('Bạn có muốn chắc chắn xoá bình luận này không?')
    if (!isConfirm) return;

    try {
        let res = await axios.delete(`/api/reviews/${reviewId}`);

        //update review when removed
        reviews = reviews.filter(review => review.id !== reviewId);

        // render review
        render(reviews);
        toastr.success("Bạn đã xoá thành công!")
    } catch (e) {
        console.log(e);
        toastr.error(e.response.data.message);
    }
};

render();