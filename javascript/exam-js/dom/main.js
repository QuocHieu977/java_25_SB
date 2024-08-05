const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];
const btnRandomAnswer = document.getElementById('btn');
const quizContainer = document.querySelector('.quiz-container');

const renderQuestion = (questions) => {
    if(questions.length === 0) {
        quizContainer.innerHTML = "<div>Không có câu hỏi trong danh sách</div>";
        return;
    }
    let html = "";
    questions.forEach((e, index) => {
        let answersHtml = "";
        e.answers.forEach((answer) => {
            answersHtml += `
                <div class="quiz-answer">
                    <div class="quiz-answer-item">
                        <input type="radio" name="${e.id}">
                        <label>${answer}</label>
                    </div>
                </div>
            `;
        });

        html += `
            <div class="quiz-item">
                <h3>Câu ${index + 1} : ${e.question}</h3>
                ${answersHtml}
            </div>
        `;
    });

    quizContainer.innerHTML = html;

};

btnRandomAnswer.addEventListener('click', () => {
    const quizItems = document.querySelectorAll('.quiz-item');
        quizItems.forEach(item => {
        const answers = item.querySelectorAll('input[type="radio"]');
        const randomIndex = Math.floor(Math.random() * answers.length);
        answers[randomIndex].checked = true;
    });
});


renderQuestion(quizes);