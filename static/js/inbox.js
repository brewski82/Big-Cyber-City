    function update() {
    	searching();
    	var pageNum = dwr.util.getValue("pageNumber");
    	
    	inbox.viewPage(pageNum, function(data) {        	
            dwr.util._escapeHtml = false;            
            dwr.util.setValue("reply", data);
            dwr.util._escapeHtml = true;        
            });
    	} 
    
    function deleteItem(id) {
    	var answer = confirm("Really delete this message?");
    	if (answer) {
    		inbox.deleteMessage(id, function(data) {
    			location.reload(true);
    		});
    	}
    }