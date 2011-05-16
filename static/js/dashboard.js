function acceptFriend(id) {
	dashboard.addFriend(id, function(data) {});
	delAlert(id);
}

function denyFriend(id) {
	delAlert(id);
}

function readMail(id) {
	dashboard.deleteAlert(id, function(data) {
		dwr.util.setValue(id + '', "");
		window.location = "/inbox.htm";
	});	
	
}

function readComments(id, userId) {
	
	dashboard.deleteAlert(id, function(data) {
		dwr.util.setValue(id + '', "");
		window.location = "/comments.htm?id=" + userId;
	});	
}

function delAlert(id) {
	dashboard.deleteAlert(id, function(data) {
		dwr.util.setValue(id + '', "");
	});
}