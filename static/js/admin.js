    function deleteAccount() {
    	var answer = confirm("Are you sure you want to delete this account?");
    	if (answer) {
    		pleasewait();
    		var accountId = dwr.util.getValue("accountId");
        	admin.deleteAccount(accountId, function(data) {
        		dwr.util.setValue("alerts", data);
        	});
    	}
    }