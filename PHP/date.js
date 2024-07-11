function refreshDate() {
    const dateDisplay = document.getElementById("date");
    const dateString = new Date().toLocaleDateString();
    dateDisplay.textContent = dateString;
}
setInterval(refreshDate, 100);

function refreshTime() {
    const timeDisplay = document.getElementById("time");
    const timeString = new Date().toLocaleTimeString();
    timeDisplay.textContent = timeString;
}
setInterval(refreshTime, 100);