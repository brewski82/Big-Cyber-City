    function sendPass() {
    	pleasewait();
    	var email = dwr.util.getValue("email");
    	password.sendPassword(email, function(data) {
    		
    		dwr.util.setValue("alerts", data);
    	});
    }