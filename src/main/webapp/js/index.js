let menuButton = document.querySelector("#menu");
let sidebar = document.querySelector(".sidebar");

menuButton.onclick = function () {
    sidebar.classList.toggle("active");

    if (sidebar.classList.contains("delayedActive")) {
        sidebar.classList.toggle("delayedActive")
    } else {
        setTimeout(function () {
            sidebar.classList.toggle("delayedActive")
        }, 230)
    }
}