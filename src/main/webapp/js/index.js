let menuButton = document.querySelector("#menu");
let sidebar = document.querySelector(".sidebar");

// voor het nakijken => fetches staan in de HTML files

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

