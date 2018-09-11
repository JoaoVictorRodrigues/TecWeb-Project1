function onEdit(id) {
    document.getElementById("overlayEdit").style.display = "block";
    document.getElementById("idAlterar").value = id;
}

function offEdit() {
    document.getElementById("overlayEdit").style.display = "none";
    document.getElementById("idAlterar").value = "";

}

function onDel(i) {
    document.getElementById("overlayDelete").style.display = "block";
    document.getElementById("qualApagar").value = i;
}

function offDel() {
    document.getElementById("overlayDelete").style.display = "none";
    document.getElementById("qualApagar").value = "";
}

	