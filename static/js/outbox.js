    function update() {
    	searching();
    	var pageNum = dwr.util.getValue("pageNumber");
    	
    	outbox.viewPage(pageNum, function(data) {        	
            dwr.util._escapeHtml = false;            
            dwr.util.setValue("reply", data);
            dwr.util._escapeHtml = true;        
            });
    	} 
    
    function deleteItem(id) {
    	var answer = confirm("Really delete this message?");
    	if (answer) {
    		outbox.deleteMessage(id, function(data) {
    			location.reload(true);
    		});
    	}
    }