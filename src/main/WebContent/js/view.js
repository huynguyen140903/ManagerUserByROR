
function Show() {
	if (document.getElementById("mydiv").style.display === "none") {
		document.getElementById("mydiv").style.display = "block";

	}
	else {
		document.getElementById("mydiv").style.display = "none";

	}

}
function confirmDelete(message) {
    if(confirm(message)) {
		document.getElementById("viewform").action = "DeleteUser.do";
		document.getElementById("viewform").method = "post";
		//Submit 
		document.getElementById("viewform").submit();
	}
}