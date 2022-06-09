function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
window.onload=function(){
    if(getQueryString("keyword")==""||getQueryString("keyword")==null){
        return ;
    }
    $(".form-control").val(decodeURI(getQueryString("keyword")));
    //鍏抽敭瀛楁煡璇�
    $(".input-group").find("button").click();
}
function searchKey(){
    $(".input-group").find("button").attr("onclick","");
    $(".search-result").find("ul").html("");
    $(".input_information").html("");
    if($(".form-control").val()==""){
        return ;
    }else{
        var key =$(".form-control").val();
        var load_index = layer.load(0, {shade: false}); //0浠ｈ〃鍔犺浇鐨勯鏍硷紝鏀寔0-2
        $.ajax({
            url:"/search/"+$(".form-control").val(),
            type:"post",
            dataType:"json",
            success:function(data){
                layer.close(load_index);
                if(data==null){
                    var index=layer.alert('鏌ヨ澶辫触锛岃妫€鏌ュ叧閿瓧', {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                    });
                }else{

                    data.forEach(function(value,index){
                        $(".search-result").find("ul").append("<li><h2><a onclick=\"enterArticle('"+value.flag+"','"+value.link+"')\">"+value.title+"</a></h2><div>"+value.description+"</div></li>");
                    });
                    var jpage=new Jpage(".search-result ul","li",4,".input_information");
                    //			var jpage=new Jpage("鐖跺潡绱㈠紩","瀛愬潡绱㈠紩",姣忎竴椤典釜鏁�,"杩欎釜鍒嗛〉瑕佹斁鍒颁綅缃殑绱㈠紩");
                    jpage.createPage();
                    if(data.length<=0){
                        $(".search-result").find("ul").html("鏈煡璇㈠埌鍐呭");
                    }else{
                        //鎼滅储璁板綍鍐欏叆cookies
                        var searchList = $.cookie("searchList");

                        if(searchList==""||searchList==null){
                            searchList = ""+ key;
                        }else{

                            var newSearchList="";
                            var i=0,flag=false;
                            if(searchList.split(";").length>=10){
                                i=1;
                            }

                            for (;i<searchList.split(";").length;i++){
                                searchkey = searchList.split(";")[i];
                                if(searchkey != ""&& searchkey != null && searchkey != key){
                                    newSearchList = newSearchList + searchkey +";" ;
                                }else if(searchkey == key){
                                    flag=true;
                                }
                            }
                            if(searchList.split(";").length>=10 && flag == true && searchList.split(";")[0] != key){
                                newSearchList = searchList.split(";")[0] +";" + newSearchList;
                            }
                            newSearchList = newSearchList + key ;
                            searchList = newSearchList;
                        }
                        $.cookie("searchList",searchList,{expires: 7,path:"/"});
                    }
                }

            }
        });
    }
    $(".input-group").find("button").attr("onclick","searchKey()");
}
