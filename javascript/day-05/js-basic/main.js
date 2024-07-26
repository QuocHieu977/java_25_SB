const para = document.querySelector('p');
let content = para.innerText.split(" ").map(e => e.length >=8
    ? `<span style="background-color: yellow">${e}</span>`
    : e
).join(" ");

para.innerHTML = content;