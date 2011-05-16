    function update() {
    	searching();
    	var userId = dwr.util.getValue("userId");
    	var pageNum = dwr.util.getValue("pageNumber");
    	

        comments.viewPage(userId, pageNum, function(data) {        	
            dwr.util._escapeHtml = false;            
            dwr.util.setValue("reply", data);
            dwr.util._escapeHtml = true;        
            });
    	} 
    
    function deleteItem(id) {
    	var answer = confirm("Really delete this comment?");
    	if (answer) {
    		comments.deleteComment(id, function(data) {
    			location.reload(true);
    		});
    	}
    }