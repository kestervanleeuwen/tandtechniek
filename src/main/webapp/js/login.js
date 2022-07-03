//https://tandtechniek.herokuapp.com:443
//http://localhost:8080
// const apiUrl = "https://tandtechniek.herokuapp.com:443"
const apiUrl = "https://tandtechniek.herokuapp.com:443"
function login(event) {
    event.preventDefault();

    let formData = new FormData(document.querySelector("#nieuw-login"));
    var encData = Object.fromEntries(formData.entries());

    var fetchOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(encData)
    }

    fetch(apiUrl + "/login", fetchOptions)
        .then(function(response) {
            console.log(encData);
            if (response.status == 200  ) {
                localStorage.setItem("auth", response.headers.get("authorization"));
                window.open("index.html", "_self")
            }
            else {
                window.alert("Onbekende gebruikersnaam en/of wachtwoord");
                document.getElementById("username-input").value = "";
                document.getElementById("wachtwoord-input").value = "";
            }
        })
        .catch(error => console.log(error));

}
document.querySelector("#saveForm").addEventListener("click", login);
