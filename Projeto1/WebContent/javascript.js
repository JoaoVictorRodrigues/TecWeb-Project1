function onEditID(id) {
    document.getElementById("overlayEdit").style.display = "block";
    document.getElementById("idAlterar").value = id;
    }
function onEditMsg(msg) {
	document.getElementById("msgEdit").value = msg;
	}
function onEditTag(tag) {
	document.getElementById("tagEdit").value = tag;
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

	