    function deleteAccount() {    	
    	var answer = confirm("Are you sure you want to delete your account?  All your data will be lost forever!");
    	if (answer) {
    		pleasewait();
        	account.deleteAccount(function(data) {
        		
        		dwr.util.setValue("alerts", data);
        		window.location = "/j_spring_security_logout";
        	});    		
    	}
    }
    
    function changeEmail() {
    	pleasewait();
    	var email = dwr.util.getValue("email");
    	var password = dwr.util.getValue("password");
    	account.changeEmail(email, password, function(data) {    		
    		dwr.util.setValue("alerts", data);
    	});
    }
    
    function changePassword() {
    	pleasewait();
    	var oldPassword = dwr.util.getValue("oldPassword");
    	var newPassword = dwr.util.getValue("newPassword");
    	var newPassword2 = dwr.util.getValue("newPassword2");
    	account.changePassword(oldPassword, newPassword, newPassword2, function(data) {
    		
    		dwr.util.setValue("alerts", data);
    	});
    }