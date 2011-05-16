 
	function gotoPage(num) {
		dwr.util.setValue("pageNumber", num);
		update();
	}
	
	function send() {
		dwr.util.setValue("pageNumber", 1);
		update();
	}
    
    function update() {
    	searching();
    	var sAge = dwr.util.getValue("startAge");
    	if(sAge == "--") sAge = 0;
    	var eAge = dwr.util.getValue("endAge");
    	if(eAge == "--") eAge = 0;
    	
    	var searchObj = {
	    	gender:dwr.util.getValue("gender"),
	    	startAge:sAge,
	    	endAge:eAge,
	    	status:dwr.util.getValue("status"),
	    	miles:dwr.util.getValue("miles"),
	    	zip:dwr.util.getValue("zip"),
	    	pageNumber:dwr.util.getValue("pageNumber")
    	};
        peopleSearch.search(searchObj, function(data) {        	
            dwr.util._escapeHtml = false;            
            dwr.util.setValue("reply", data);
            dwr.util._escapeHtml = true;        
            });
    	}  
    
    function reset() {
    	dwr.util.setValue("gender", "Gender");
    	dwr.util.setValue("startAge", "--");
    	dwr.util.setValue("endAge", "--");
    	dwr.util.setValue("status", "Status");
    	dwr.util.setValue("miles", 10);
    	dwr.util.setValue("zip", "");
    	dwr.util.setValue("pageNumber", 1);
    	update();
    }
    
    window.onload = reset;
    
    
  