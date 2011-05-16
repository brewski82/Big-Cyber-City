function makeDefault(id) {

	window.location = "/editPhotos.htm?action=makeDefault&photoId=" + id;
}

function deletePhoto(id) {
	var answer = confirm("Really delete this photo?");
	if (answer) {
		window.location = "/editPhotos.htm?action=delete&photoId=" + id;
	}

}