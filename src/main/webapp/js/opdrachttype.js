const apiUrl = "https://tandtechniek.herokuapp.com:443"

document.getElementById('addOpdrachttypeButton').addEventListener("click", () => {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());

    let voorraadIds = [];
    $('#opdrachtTypeTable').bootstrapTable('getData').forEach((data) => {
        voorraadIds.push(data.voorraadId);
    });
    encData.voorraadIds = voorraadIds;
    var stringifiedData = JSON.stringify(encData);
    console.log(stringifiedData);
    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: stringifiedData
    }

    fetch(apiUrl + '/opdrachtType/', fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                $('#klantenModal').modal('hide');
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
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
    console.log($('#opdrachtTypeTable').bootstrapTable('getData'));
});

$('#opdrachtTypeTable').bootstrapTable({
    pagination: true,
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

function getOpdrachtType() {
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
            data.forEach((opdrachtTypeData) => {
                console.log(opdrachtTypeData)
                let button = document.createElement('BUTTON');
                let opdrachtTypeNaam = document.createElement('span');
                let listDiv = document.querySelector(".opdrachttype-list");
                opdrachtTypeNaam.innerHTML = `ID: ${opdrachtTypeData.opdrachtTypeNummer}: ${opdrachtTypeData.opdrachtTypeNaam}`;

                button.appendChild(opdrachtTypeNaam);
                listDiv.appendChild(button);

                // button.addEventListener("click", () => {
                //     const urlParams = new URLSearchParams(window.location.search);
                //     urlParams.set("opdrachtNummer", opdracht.opdrachtNummer);
                //     window.open("opdracht.html?" + urlParams, "_self");
                // });
            })
    })
        .catch(function (e) {
            console.log(e);
        })
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
getOpdrachtType();
