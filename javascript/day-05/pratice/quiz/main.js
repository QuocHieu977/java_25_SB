const questions = [
    {
        id: 1,
        title: "1 + 1 = ?",
        choices: [1, 2, 3, 4],
        answers: 2
    },
    {
        id: 2,
        title: "Năm 2024 có phải năm nhuận không",
        choices: ["Có", "Không"],
        answers: "Có"
    },
    {
        id: 3,
        title: "4 + 4 = ?",
        choices: [8, 9, 1, 4],
        answers: 8
    },
    {
        id: 4,
        title: "4 + 4 = 8",
        choices: [true, false],
        answers: true
    },
];   

let result = [];
let currentQuestionIndex = 0;
const btnNext = document.querySelector("#btn-next");
const btnFinish = document.querySelector("#btn-finish");
const btnBack = document.querySelector("#btn-back");
const questionEl = document.querySelector("#question");
const choicesEl = document.querySelector(".choices");

const renderQuestion = () => {
    const question = questions[currentQuestionIndex];

    // Hiển thị câu hỏi
    const titleEl = document.querySelector("#question p");
    titleEl.innerText = `Câu ${currentQuestionIndex +1}: ${question.title}`;

    // Hiện thị các lựa chọn
    const choicesEl = document.querySelector(".choices");
    let html = "";
    question.choices.forEach((choice, index) => {
        html += `
            <div class="choice-item">
                <input type="radio" name="choice" id="${index}" value="${choice}"/>
                <label for="${index}">${choice}</label>
            </div>
        `;
    });
    if(currentQuestionIndex === questions.length-1) {
        btnFinish.classList.remove("hide");
        btnNext.classList.add("hide");
    }
    choicesEl.innerHTML = html;
    
};

renderQuestion(questions);

const selectAnswers = (choice) => {
    if(choice === String(questions[currentQuestionIndex].answers)) 
        result.push(choice);
}

btnNext.addEventListener("click", () => {
    const selectEl = document.querySelector('input[name="choice"]:checked');
    if(selectEl) {
        selectAnswers(selectEl.value);
        currentQuestionIndex++;
        renderQuestion(questions);
    } else {
        alert("Mời bạn chọn đáp án...");
    }
});

btnFinish.addEventListener("click", () => {
    const selectEl = document.querySelector('input[name="choice"]:checked');
    if(selectEl) {
        selectAnswers(selectEl.value);
        questionEl.classList.add("hide");
        choicesEl.classList.add("hide");
        btnFinish.classList.add("hide");

        alert(`Đúng ${result.length}/${questions.length} câu`);
        window.location.reload();
    } else {
        alert("Mời bạn chọn đáp án...");
    }
    
});
