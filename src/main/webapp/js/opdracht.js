const apiUrl = "https://tandtechniek.herokuapp.com:443"

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const opdrachtNummer = urlParams.get('opdrachtNummer');

function getKlanten() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/klant/', fetchOptions)
        .then((res) => {
            return res.json();
        }).then((data) => {
        let klantSelect = document.getElementById('klant-select');
        data.forEach((klant) => {
            klantSelect.options.add(new Option(klant.voornaam + " " + klant.achternaam + ", " + klant.bedrijfsnaam, klant.klantId))
        })
    })
        .catch(function (e) {
            console.log(e);
        })
}

function getVoorraadType() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/opdrachtType/', fetchOptions)
        .then((res) => {
            return res.json();
        }).then((data) => {
        let opdrachtTypeSelect = document.getElementById('opdrachttype-select');
        console.log(data);
        data.forEach((opdrachtType) => {
            opdrachtTypeSelect.options.add(new Option(opdrachtType.opdrachtTypeNaam, opdrachtType.opdrachtTypeNummer));
        })
    })
        .catch(function (e) {
            console.log(e);
        })
}

function getOpdracht() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + "/opdracht/" + opdrachtNummer, fetchOptions)
        .then(response => Promise.all([response.status, response.json()]))
        .then(function ([status, myJson]) {
            if (status == 200) {

                if (myJson.status == "closed") {
                    document.querySelector('.title').innerHTML = "Opdracht inzien";
                    document.getElementById('finishButton').remove();
                    document.getElementById('saveForm').remove();
                    document.getElementById('input-eindDatum').disabled = true;
                    document.getElementById('input-startDatum').disabled = true;
                    document.getElementById('input-omschrijving').disabled = true;
                    document.getElementById('input-opdrachtNaam').disabled = true;
                    document.getElementById('klant-select').disabled = true;
                    document.getElementById('opdrachttype-select').disabled = true;

                }
                const startDatum = new Date(myJson.startDatum).toISOString().split('T')[0].slice(0, 10);
                const eindDatum = new Date(myJson.eindDatum).toISOString().split('T')[0].slice(0, 10);

                document.getElementById("input-opdrachtNaam").value = myJson.opdrachtNaam;
                document.getElementById("input-omschrijving").value = myJson.omschrijving;

                if (myJson.startDatum != null && myJson.eindDatum != null) {
                    document.getElementById("input-startDatum").value = startDatum;
                    document.getElementById("input-eindDatum").value = eindDatum;
                }

            } else {
                console.log("status was " + status)
            }
        }).catch(error => console.log(error.message));
}

document.getElementById('saveForm').addEventListener("click", () => {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());
    let newObj = {"opdrachtNummer":opdrachtNummer, ...encData};
    let stringifiedObj = JSON.stringify(newObj);
    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: stringifiedObj
    }
    fetch(apiUrl + '/opdracht/update/', fetchOptions)
        .then((res) => {
            if(Date.parse(newObj.startDatum) > Date.parse(newObj.eindDatum)) {
                throw new Error("startDatum kan niet na eindDatum zijn.")
            }

            if (res.status == 201) {
                location.href = "opdrachten.html";
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
        })
        .catch(function (e) {
            console.log(e);
        })
})

document.getElementById("confirmFinishButton").addEventListener("click", () => {
    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/opdracht/finish/' + opdrachtNummer, fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                location.href = "opdrachten.html";
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
        }).then(window.open("opdrachten.html", "_self"))
        .catch(function (e) {
            console.log(e);
        })
})



document.getElementById("confirmDeleteButton").addEventListener("click", () => {
    var fetchOptions = {
        method: 'DELETE',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/opdracht/' + opdrachtNummer, fetchOptions)
        .then((res) => {
            if (res.status == 200) {
                console.log("success");
            } else {
                window.alert("error");
            }
        }).then(window.open("opdrachten.html", "_self"))
        .catch(function (e) {
            console.log(e);
        })
})


function testApiCall() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + "/opdracht/" + opdrachtNummer, fetchOptions)
        .then(response => Promise.all([response.status, response.json()]))
        .then(function ([status, myJson]) {
            if (status == 200) {

                if (myJson.klant) {
                    document.getElementById("klant-select").value = myJson.klant.klantId;
                }

                if (myJson.opdrachtType) {
                    document.getElementById("opdrachttype-select").value = myJson.opdrachtType.opdrachtTypeNummer;
                }

            } else {
                console.log("status was " + status)
            }
        }).catch(error => console.log(error.message));
}

getKlanten();
getVoorraadType();
getOpdracht();
testApiCall();