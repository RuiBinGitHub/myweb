//$(document).ready(function() {
//	
//	function show(text){
//		alert("123" + text);
//	}
//});

(function(action, $) {
	$.message = function(options) {
		console.log(options.message);
	};
})(this, jQuery != "undefined" ? jQuery : this);