function changePic(picture) {
	document.bigpicture.src = picture;
}

function reqFriend(id) {
	var answer = confirm("Really add this person as a friend?");
	if (answer) {
		profile.requestFriend(id, function(data) {
			window.alert("A friend request has been sent");
		});
	}
}

function deleteComment(id) {
	var answer = confirm("Really delete this comment?");
	if (answer) {
		profile.deleteComment(id, function(data) {
			location.reload(true);
		});
	}
}