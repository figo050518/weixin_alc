window.Info = {
	view: function(str, top, callback) { //黑框提示方法
		var t;
		if (top) {
			t = $('<div class="p_view" style="top:' + top + '%"><span>' + str + '</div></span>');
		} else {
			t = $('<div class="p_view"><span>' + str + '</div></span>');
		}
		$('body').append(t);
		if(t.width() > $(window).width()*0.8){
			t.css({maxWidth:'80%',left:'10%'});
		}else{
			t.css('left',($(window).width()-t.width())/2);
		}
		setTimeout(function() {
			t.animate({
				opacity: 0
			}, 1000);
		}, 1000);
		setTimeout(function() {
			if (typeof callback == 'function') {
				callback(t);
			}
			t.remove();
		}, 2000);
	},
	trimRegex: /^[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u2028\u2029\u202f\u205f\u3000]+|[\x09\x0a\x0b\x0c\x0d\x20\xa0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u2028\u2029\u202f\u205f\u3000]+$/g,
	trim: function(string) {
        return string.replace(Info.trimRegex, "");
    },
    getpos:function(e){
	    var t=e.offsetTop;   
	    var l=e.offsetLeft;   
	    while(e=e.offsetParent){
	         t+=e.offsetTop;   
	         l+=e.offsetLeft;   
	     }
	    return {top:t,left:l};
	}
};