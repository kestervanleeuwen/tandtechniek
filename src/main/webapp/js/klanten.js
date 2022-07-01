const apiUrl = "https://tandtechniek.herokuapp.com:443"

document.getElementById("addKlantButton").addEventListener("click", function () {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());

    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: JSON.stringify(encData)
    }

    fetch(apiUrl + '/klant/', fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                $('#klantenModal').modal('hide');
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
        })
})

function deleteKlant(klantId) {
    var fetchOptions = {
        method: 'DELETE',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/klant/' + klantId + '/', fetchOptions)
        .then((res) => {
            if (res.status == 200) {
                console.log("success");
                $('#klantenTable').bootstrapTable('removeByUniqueId', klantId);

            } else {
                window.alert("error");
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

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
        $('#klantenTable').bootstrapTable({
            pagination: true,
            pageSize: 10,
            columns: [{
                field: 'klantId',
            }, {
                field: 'voornaam',
                title: 'Voornaam'
            }, {
                field: 'achternaam',
                title: 'Achternaam'
            },{
                field: 'bedrijfsnaam',
                title: 'Bedrijf'
            }, {
                field: 'email',
                title: 'Email'
            }, {
                field: 'telefoon',
                title: 'Telefoon'
            }, {
                field: 'plaats',
                title: 'Plaats'
            }, {
                field: 'adres',
                title: 'Adres'
            }, {
                field: 'postcode',
                title: 'Postcode',
            }, {
                field: 'remove',
                title: '',
                width: '30px',
                formatter: (value, row) =>
                    `<button type="button" class="btn btn-danger btn-xs" onclick="deleteKlant(${row.klantId})">
                            <i class='bx bx-trash'></i>
                        </button>`
            }],
            data: data,
            uniqueId: 'klantId'
        })
    })
        .catch(function (e) {
            console.log(e);
        })
}
getKlanten();