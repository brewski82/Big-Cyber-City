
	function gotoPage(num) {
		dwr.util.setValue("pageNumber", num);
		update();
	}
	
    
    function reset() {
    	dwr.util.setValue("pageNumber", 0);
    	update();
    }
    
    window.onload = reset;