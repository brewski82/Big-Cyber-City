function createAccount() {
	pleasewait();
	var email = dwr.util.getValue("email");
	var password = dwr.util.getValue("password");
	register.reg(email, password, function(data) {
		dwr.util.setValue("alerts", data);
	});
}