const apiUrl = "https://tandtechniek.herokuapp.com:443"
const todoList = document.querySelector('.todo-list');
const finishedList = document.querySelector('.finished-list');
const list = document.createDocumentFragment();

var fetchOptions = {
    method: 'GET',
    headers: {
        "Authorization": localStorage.getItem("auth"),
    }
}

fetch(apiUrl + '/opdracht/', fetchOptions)
    .then((res) => {
        return res.json();
    }).then((data) => {
    let openOpdrachten = data.filter((opdracht) => opdracht.status == 'open')
    let finishedOpdrachten = data.filter((opdracht) => opdracht.status == 'closed')

    finishedOpdrachten.forEach((opdracht) => {
        let button = document.createElement('BUTTON');
        let opdrachtNaam = document.createElement('span');

        opdrachtNaam.innerHTML = `ID: ${opdracht.opdrachtNummer}: ${opdracht.opdrachtNaam}`;

        button.appendChild(opdrachtNaam);
        finishedList.appendChild(button);

        button.addEventListener("click", () => {
            const urlParams = new URLSearchParams(window.location.search);
            urlParams.set("opdrachtNummer", opdracht.opdrachtNummer);
            window.open("opdracht.html?" + urlParams, "_self");
        });
    })

    openOpdrachten.forEach((opdracht) => {
        let button = document.createElement('BUTTON');
        let opdrachtNaam = document.createElement('span');

        opdrachtNaam.innerHTML = `ID: ${opdracht.opdrachtNummer}: ${opdracht.opdrachtNaam}`;

        button.appendChild(opdrachtNaam);
        todoList.appendChild(button);

        button.addEventListener("click", () => {
            const urlParams = new URLSearchParams(window.location.search);
            urlParams.set("opdrachtNummer", opdracht.opdrachtNummer);
            window.open("opdracht.html?" + urlParams, "_self");
        });
    })
}).catch(function (e) {
    console.log(e);
})
