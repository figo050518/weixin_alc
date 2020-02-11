
/**
 * 登录成功后回调
 * @param {Object} data
 */
function signInSuccCallback(){
        var backUrl = getBackUrl();
        if(backUrl=="http://www.fcg.com/user/register"){
        	backUrl="http://www.fcg.com/uc/index";
        }
        window.parent.location.href = backUrl;
}

function getBackUrl(){
	var backUrl = $("#submitForm input[name='backUrl']").val();
	return backUrl;
}



