//实现分页的js
(function ($, doc) {
    $.init();
    $.ready(function () {

    });
})(mui, document);

//-----------------------------------------------------------------
mui.init({
    pullRefresh: {
        container: '#pullrefresh',
//      down: {
//          callback: pulldownRefresh
//      },
        up: {
            contentrefresh: '正在加载...',
            callback: pullupRefresh
        }
    }
});

/**
* 下拉刷新具体业务实现
*/
//function pulldownRefresh() {
//  setTimeout(function () {
//      mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
//  }, 500);
//}

/**
* 上拉加载具体业务实现
*/
var pageIndex = 1;
function pullupRefresh() {
    setTimeout(function () {
        mui('#pullrefresh').pullRefresh().endPullupToRefresh();
        var table = document.body.querySelector('.mui-table-view');
        var response = getResponseData(pageIndex);
        if (response.length < 6) {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh(true); //参数为true代表没有更多数据了。
        }
        else {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh(false); //参数为true代表没有更多数据了
        }
        for (var e in response) {
        	var html="";
			html+="<div class='recommendedOne'><img src="+response[e].schemeImg+" id="+response[e].id+"></div>"+
			"<div class='fahuo'>"+
				"<span>"+江宁区发货+"</span><span>|</span><span>666</span>"+
					"</tr>"+
					"<div class='pdtName'>皇家太阳奢华复古吊灯</div>"+
					"<div class='pdtPrice'>"+
						"<span>¥</span><span>163.00</span>"+
					"</div>"+
					"</div>";
            $("#goodsInfoType1").append(html);
        }
    }, 500);
}


//获取实时分页数据
function getResponseData(obj) {
    /*alert(pageIndex);*/
    var response = new Array();
    //查询被选中的,初次
    if (obj == 1) {
        pageIndex = 2;//初次加载，从第一页开始
    } else {
        pageIndex = obj + 1;
    }
    var id=$(".designId").val();
    $.ajax({
        type: 'get',
        url: "/achemeDesign/findByDesignId?id="+id,
        data: { "pageIndex": pageIndex },
        async: false,
        success: function (obj) {
            response = obj;
        }
    });
    return response;
}