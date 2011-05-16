    function update() {
    	searching();
    	var userId = dwr.util.getValue("userId");
    	var pageNum = dwr.util.getValue("pageNumber");
    	

        friends.viewPage(userId, pageNum, function(data) {        	
            dwr.util._escapeHtml = false;            
            dwr.util.setValue("reply", data);
            dwr.util._escapeHtml = true;        
            });
    	} 
    
    function deleteItem(id) {
    	var answer = confirm("Really remove this person from your friends list?");
    	if (answer) {
    		friends.deleteFriend(id, function(data) {
    			location.reload(true);
    		});
    	}
    }