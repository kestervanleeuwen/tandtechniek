const apiUrl = "https://tandtechniek.herokuapp.com:443"

function getKlanten() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/klant/', fetchOptions)
        .then((res) => {
            console.log(res);
            return res.json();
        }).then((data) => {
        let klantSelect = document.getElementById('klant-select');
        data.forEach((klant) => {
            klantSelect.options.add(new Option(klant.voornaam + " " + klant.achternaam + ", " + klant.bedrijfsnaam , klant.klantId))
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

document.getElementById('saveFormExtra').addEventListener("click", () => {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());
    var stringifiedData = JSON.stringify(encData);
    stringifiedData.klantId = document.getElementById('klant-select').value;

    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: stringifiedData
    }

    fetch(apiUrl + '/opdracht/', fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                window.alert("Opdracht aangemaakt. Je keert nu terug naar het opdrachten scherm.");
                location.href = "opdrachten.html";
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
        })
})

getKlanten();
getVoorraadType();