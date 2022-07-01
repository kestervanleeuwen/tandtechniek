let menuButton = document.querySelector("#menu");
let sidebar = document.querySelector(".sidebar");

menuButton.onclick = function () {
    sidebar.classList.toggle("active");
    if (sidebar.classList.contains("active")) {
        localStorage.setItem("sidebar", "active")
    } else {
        localStorage.setItem("sidebar", "inactive")
    }

    if (sidebar.classList.contains("delayedActive")) {
        sidebar.classList.toggle("delayedActive")
    } else {
        setTimeout(function () {
            sidebar.classList.toggle("delayedActive")
        }, 230)
    }
}


if (!window.localStorage.getItem("auth") || isTokenExpired(window.localStorage.getItem("auth"))) {
    localStorage.removeItem("auth");
    window.open("login.html", "_self");
}

// gepakt van https://stackoverflow.com/questions/51292406/check-if-token-expired-using-this-jwt-library
function isTokenExpired(token) {
    try {
        const base64Url = token.split(".")[1];
        const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        const jsonPayload = decodeURIComponent(
            atob(base64)
                .split("")
                .map(function (c) {
                    return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
                })
                .join("")
        );

        const {exp} = JSON.parse(jsonPayload);
        const expired = Date.now() >= exp * 1000
        return expired
    } catch (e) {
        localStorage.removeItem("auth");
        window.open("login.html", "_self");
    }
}


document.getElementById('logoutButton').addEventListener("click", () => {
    localStorage.removeItem("auth");
    window.open("login.html", "_self");
})
