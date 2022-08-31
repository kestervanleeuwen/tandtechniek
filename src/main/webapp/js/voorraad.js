const apiUrl = "http://localhost:8080"

document.getElementById('addVoorraadButton').addEventListener("click", () => {
    var formData = new FormData(document.querySelector('form'));
    var encData = Object.fromEntries(formData.entries());
    var stringifiedData = JSON.stringify(encData);

    var fetchOptions = {
        method: 'POST',
        headers: {
            "Authorization": localStorage.getItem("auth"),
            "Content-Type": "application/json"
        },
        body: stringifiedData
    }

    fetch(apiUrl + '/voorraad/', fetchOptions)
        .then((res) => {
            if (res.status == 201) {
                $('#klantenModal').modal('hide');
                setTimeout(function (){window.location.reload(true);},100)
            } else {
                window.alert("error");
            }
        })
})


function getVoorraad() {
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
            data.forEach((voorraadData) => {
                let voorraadBody = document.querySelector('.voorraad-body');
                let voorraadContainer = document.createElement('div');

                voorraadContainer.className = "voorraad-container";
                voorraadContainer.innerHTML = `
                                <span class="voorraad-text"></span>
                                <div class="voorraad-footer">
                                    <input class="input-voorraad-edit" type="number">
                                        <button id="button-voorraad-delete" type="button" class="btn btn-danger btn-xs")">
                                            <i class='bx bx-trash'></i>
                                        </button>
                                </div>`
                voorraadContainer.querySelector('.voorraad-text').innerHTML = "Naam: " + voorraadData.voorraadNaam + "<br>Prijs: " + voorraadData.prijs + "$<br>In voorraad: " + voorraadData.inVoorraad;
                voorraadContainer.querySelector('.input-voorraad-edit').value = voorraadData.inVoorraad;
                voorraadContainer.querySelector('#button-voorraad-delete').addEventListener("click", () => {
                    deleteVoorraad(voorraadData.voorraadId);
                })
                voorraadBody.appendChild(voorraadContainer);
            });
    })
        .catch(function (e) {
            console.log(e);
        })
}

function deleteVoorraad(id) {
    var fetchOptions = {
        method: 'DELETE',
        headers: {
            "Authorization": localStorage.getItem("auth"),
        }
    }

    fetch(apiUrl + '/voorraad/' + id + '/', fetchOptions)
        .then((res) => {
            if (res.status == 200) {
                console.log("success");
                setTimeout(function (){window.location.reload(true);},100);
            } else {
                window.alert("error");
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}
getVoorraad();