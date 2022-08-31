const apiUrl = "http://localhost:8080"

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const opdrachtTypeNummer = urlParams.get('opdrachtTypeNummer');


document.getElementById('saveOpdrachttypeButton').addEventListener("click", () => {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());

    let voorraadIds = [];
    $('#opdrachtTypeTable').bootstrapTable('getData').forEach((data) => {
        voorraadIds.push(data.voorraadId);
    });
    encData.opdrachtTypeNummer = opdrachtTypeNummer;
    encData.voorraadIds = voorraadIds;
    var stringifiedData = JSON.stringify(encData);

    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: stringifiedData
    }

    fetch(apiUrl + '/opdrachtType/update', fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                window.open("opdrachttype.html", "_self");
            } else {
                window.alert("error");
                console.log(res.status);
            }
        })
})

document.getElementById("deleteOpdrachttypeButton").addEventListener("click", () => {
    var fetchOptions = {
        method: 'DELETE',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/opdrachtType/' + opdrachtTypeNummer, fetchOptions)
        .then((res) => {
            if (res.status == 200) {
                console.log("success");
            } else {
                window.alert("error");
            }
        }).then(window.open("opdrachttype.html", "_self"))
        .catch(function (e) {
            console.log(e);
        })
})

document.getElementById('addVoorraadButton').addEventListener("click", () => {
    let selectValue = document.getElementById('voorraad-select').value;

    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/voorraad/' + selectValue, fetchOptions)
        .then(response => Promise.all([response.status, response.json()]))
        .then(function ([status, myJson]) {
            if (status == 200) {
                let geofees = $('#opdrachtTypeTable').bootstrapTable('getData');
                $('#opdrachtTypeTable').bootstrapTable('insertRow', {
                    index: geofees.length,
                    row: myJson
                });
            } else {
                console.log("status was " + status)
            }
        }).catch(error => console.log(error.message));
});

function getOpdracht() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + "/opdrachtType/" + opdrachtTypeNummer, fetchOptions)
        .then(response => Promise.all([response.status, response.json()]))
        .then(function ([status, myJson]) {
            if (status == 200) {
                console.log(myJson);
                $('#opdrachtTypeTable').bootstrapTable('load', myJson.voorraad);
                document.getElementById("input-opdrachtTypeNaam").value = myJson.opdrachtTypeNaam;
                document.getElementById("input-beschrijving").value = myJson.beschrijving;
                document.getElementById("input-urenNodig").value = myJson.urenNodig;

            } else {
                console.log("status was " + status)
            }
        }).catch(error => console.log(error.message));
}

$('#opdrachtTypeTable').bootstrapTable({
    pagination: false,
    pageSize: 10,
    columns: [{
        field: 'voorraadId',
    }, {
        field: 'voorraadNaam',
        title: 'Voorraad Naam'
    }, {
        field: 'prijs',
        title: 'Prijs'
    },{
        field: 'inVoorraad',
        title: 'In Voorraad'
    }, {
        field: 'remove',
        title: '',
        width: '30px',
        formatter: (value, row) =>
            `<button type="button" class="btn btn-danger btn-xs" onclick="deleteOpdrachtTypeRow(${row.voorraadId})">
                            <i class='bx bx-trash'></i>
                        </button>`
    }],
    uniqueId: 'voorraadId'
})

function deleteOpdrachtTypeRow(id) {
    $('#opdrachtTypeTable').bootstrapTable('removeByUniqueId', id);
}

function fillVoorraad() {
    var fetchOptions = {
        method: 'GET',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/voorraad/', fetchOptions)
        .then((res) => {
            return res.json();
        }).then((data) => {
        let voorraadSelect = document.getElementById('voorraad-select');
        data.forEach((voorraad) => {
            voorraadSelect.options.add(new Option(voorraad.voorraadNaam, voorraad.voorraadId));
        })
    })
        .catch(function (e) {
            console.log(e);
        })
}

fillVoorraad();
getOpdracht();