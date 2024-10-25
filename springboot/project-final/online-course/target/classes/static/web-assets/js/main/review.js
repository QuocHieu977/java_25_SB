// format date
const formatDate = dateString => {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = ("0" + (date.getMonth() + 1)).slice(-2); // 09 -> 09 , 011 -> 11
  const day = ("0" + date.getDate()).slice(-2);
  return `${day}-${month}-${year}`;
}

const reviewListEl = document.querySelector(".comment-list");
const renderReviews = (reviews) => {
  if (reviews.length === 0) {
    reviewListEl.innerHTML = "<ol>Chưa có đánh giá </ol>"
    return;
  }

  let html = "";

  reviews.forEach((review) => {
    html += `
     <li th:each="review: ${reviews}">
        <article class="comment-entry">
          <div class="d-sm-flex align-items-top">
            <div class="comment-thumb">
              <img width="80" class="img-fluid rounded-circle"
                   src="${review.user.avatar}" alt="Comments">
            </div>
            <div class="commentor ms-lg-4 bg-shade p-4 rounded-2">
              <div class="d-flex justify-content-between mb-3">
                <div class="comment-head">
                  <h4 class="display-5 mb-0">${review.user.full_name}</h4>
                  <small class="text-muted">${formatDate(review.createAt)}</small>
                </div>
                <div class="ratings pt-2">
                    <img src="/web-assets/images/icons/star.png" alt="Star">
                </div>
              </div>
              <p>${review.content}</p>
            </div>
          </div>
        </article>
      </li>
  `;

    reviewListEl.innerHTML = html;
  })
}

const render = () => {
  $('#review-pagination').pagination({
    dataSource: reviews,
    pageSize: 3,
    callback: function (data, pagination) {
      renderReviews(data);
    }
  })
}

render();