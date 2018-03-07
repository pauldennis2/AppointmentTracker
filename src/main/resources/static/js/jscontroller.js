console.log("I'm in the js file");

function doFunction () {
	console.log("I'm the function");
}

function showForm() {
	console.log("I'm showing the form");
	document.getElementById("newForm").style.display="block";
	document.getElementById("cancelButton").style.display="block";
	
}

function hideForm() {
	console.log("I'm hiding the form");
	document.getElementById("newForm").style.display="none";
	document.getElementById("cancelButton").style.display="none";
	
}