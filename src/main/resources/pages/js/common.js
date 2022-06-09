
//鑾峰彇鐢ㄦ埛鎵€鍦ㄥ煄甯備俊鎭�
function showCityInfo() {
    //瀹炰緥鍖栧煄甯傛煡璇㈢被
    var citysearch = new AMap.CitySearch();
    //鑷姩鑾峰彇鐢ㄦ埛IP锛岃繑鍥炲綋鍓嶅煄甯�
    citysearch.getLocalCity(function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            if (result && result.city && result.bounds) {
                var cityinfo = result.city;
                var provinceInfo = result.province;//鐪佷唤
                $.ajax({
                    url:"/location/insert",
                    type:"post",
                    data:{
                        "province":provinceInfo,
                        "city":cityinfo
                    },
                    dataType:"json",
                    success:function(data){

                    }
                });

            }
        } else {
            document.getElementById('info').innerHTML = result.info;
        }
    });
}
$(function(){
    $(document).click(function(e){


//		if($(e.target).attr("class")!="form-control"&&$(e.target).attr("class")!="intput-group_down-list"&&$(e.target).attr("class")!="search_list_one"&&$(e).parent().attr("class")!="numPage"){
//		if($(e.target).tagName=="body"){
        if(!($(e.target).parents(".input-border").length>0)){
            $(".intput-group_down-list").hide();
            $(".form-control").val("");
//alert($(e.target).className)
        }
    });
    // var jpage=new Jpage(".intput-group_down-list ul","li",3,".input_information");
    // jpage.createPage();

    showCityInfo();

    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            window.history.pushState('forward', null, '#');
            window.history.forward(1);
        });
    }
    window.history.pushState('forward', null, '#'); //鍦↖E涓繀椤诲緱鏈夎繖涓よ
    window.history.forward(1);
});
//鎼滅储妗嗘彁绀�
function searchInfo(){
    var searchList = $.cookie("searchList");
    $(".intput-group_down-list").html("");
    for(var i=0;i<searchList.split(";").length;i++){
        var search = searchList.split(";")[i];
        if(search!=""){
            $(".intput-group_down-list").append("<span onclick='search(this)'>"+search+"</span>");
        }
    }
    $(".intput-group_down-list").show();
}
function showRightMeun(){
    if($(".right_meun").offset().left<0){
        $(".right_meun").css({
            "position":"absoute",
            "margin-left":"-200px"
        }).show().animate({
            "left":"200"
        },800);
        $(".meun_min_block_logo").find("img").attr("src","/resources/img/meun2.png");
    }else{
        $(".right_meun").css({
            "position":"absoute",
            "margin-left":"-200px"
        }).show().animate({
            "left":"0"
        },800);
        $(".meun_min_block_logo").find("img").attr("src","/resources/img/meun1.png");
    }
}
var registerIndex;
var loginIndex;
function showLogin(){

    //椤甸潰灞�
    layer.close(registerIndex);
    loginIndex=layer.open({
        type: 1,
        title:'鐧诲綍  閫濋鏃犺█涓汉鍗氬',
        skin: 'layui-layer-molv', //鍔犱笂杈规
        area: ['320px', '420px'], //瀹介珮
        content:$(".login_form")
    });
}
//showLogin();
function loginServe(){
    $(".login").attr("onclick","");
    if($(".username").val()==""||$(".password").val()==""){
        layer.msg('閭鎴栧瘑鐮佹湭杈撳叆锛岃妫€鏌�');
    }else{
        var load_index = layer.load(0, {shade: false}); //0浠ｈ〃鍔犺浇鐨勯鏍硷紝鏀寔0-2
        $.ajax({
            url:"/user/login",
            type:"post",
            data:{
                username:$(".username").val(),
                password:$(".password").val()
            },
            dataType:"json",
            success:function(data){
                layer.close(load_index);
                if(data.success){
                    var index=layer.alert('鐧诲綍鎴愬姛 ', {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                        location.reload();
                    });

                }else{
                    var index=layer.alert('鐧诲綍澶辫触 鍘熷洜锛�'+data.error, {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                    });
                }
            }
        });
    }
    $(".login").attr("onclick","loginServe()");
}

function showRegister(){
    //椤甸潰灞�
    layer.close(loginIndex);
    registerIndex= layer.open({
        type: 1,
        title:'娉ㄥ唽 閫濋鏃犺█涓汉鍗氬',
        skin: 'layui-layer-molv', //鍔犱笂杈规
        area: ['320px', '420px'], //瀹介珮
        content:$(".register_form")
    });

}

function sendCheckcode(){
    $(".send_checkcode").attr("onclick","");
    if($(".register_password").val()!=$(".register_confirm_password").val()||$(".register_password").val()==null||$(".register_password").val()==""){//楠岃瘉杈撳叆
        layer.msg('閭鎴栧瘑鐮佹湭锛堟纭級杈撳叆锛岃妫€鏌�');
        $(".send_checkcode").attr("onclick","sendCheckcode()");
        return ;
    }else{
        $(".send_checkcode").css({
            "opacity":0.3
        });
        $.ajax({
            type:"post",
            url:"/user/checkcode",
            data:{
                "username":$(".register_username").val()
            },
            dataType:"json",
            error:function(data){

            },
            success:function (data) {
                if(data.success){
                    $(".send_checkcode").attr("onclick","");
                    layer.tips('楠岃瘉鐮佸凡鍙戦€�', '.send_checkcode', {
                        tips: [1, '#3595CC'],
                        time: 3000
                    });
                    //鍊掕鏃�
                    countDown(2 * 60);
                }else{
                    var index=layer.alert(data.response, {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                    });
                    $(".send_checkcode").attr("onclick","sendCheckcode()");
                }
            }
        });

    }
}
var countdown;
function countDown(time){

    if(time<=0){

        clearTimeout(countdown);
        $(".send_checkcode").css({
            "opacity":1
        });
        $(".send_checkcode").attr("onclick","sendCheckcode()");
        $(".send_checkcode").text("鍙戦€�");
    }else{

        $(".send_checkcode").text(""+time);
        time=time-1;
        setTimeout("countDown("+time+")",1000);
    }

}

function closeSho(){
    layer.close(la);
}
function loginOut(){
    $.ajax({
        type:"post",
        url:"/user/loginOut",
        asyn:false,
        success:function(){
            window.location.reload();
        }
    });

}
function registerServe(){
    // console.log("娉ㄥ唽")
    $(".register").attr("onclick","");
    if($(".register_username").val()==""||$(".register_password").val()==""){
        layer.msg('鐢ㄦ埛鍚嶆垨瀵嗙爜鐨勮緭鍏ワ紝璇锋鏌�');
    }else if($(".register_password").val()!=$(".register_confirm_password").val()){
        layer.msg('瀵嗙爜鏈‘璁わ紝璇锋鏌�');
    }else if($(".register_password").val().length<8||$(".register_password").length>18) {
        layer.msg('璇疯緭鍏� 8-18 浣嶆暟瀛楀瓧姣嶅瘑鐮佷笖蹇呴』鍚湁涓よ€�');
    }else{
        var load_index = layer.load(0, {shade: false}); //0浠ｈ〃鍔犺浇鐨勯鏍硷紝鏀寔0-2
        $.ajax({
            type:"post",
            url:"/user/register",
            data:{
                "username":$(".register_username").val(),
                "password":$(".register_password").val(),//鏈姞瀵嗭紝濡傛灉MD5鍔犲瘑鍚庢€庢牱楠岃瘉瀵嗙爜鏄悎娉曠殑瀛楃
                "checkcode":$(".register_checkcode").val()
            },
            dataType:"json",
            success:function(data){
                layer.close(load_index);
                if(data.success){
                    var index=layer.alert('娉ㄥ唽鎴愬姛', {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                    });
                    $(".register").attr("onclick","");
                    layer.close(registerIndex);
                    showLogin();//鏄剧ず鐧诲綍
                }else{
                    var index=layer.alert('娉ㄥ唽澶辫触 鍘熷洜锛�'+data.error, {
                        skin: 'layui-layer-molv' //鏍峰紡绫诲悕
                        ,closeBtn: 0
                    }, function(){
                        layer.close(index);
                    });
                }
            }
        });
    }
    $(".register").attr("onclick","registerServe()");
}
function search(t){
    if(t!=null){
        $(".form-control").val($(t).text());
    }
    if($(".form-control").val()==""||$(".form-control").val().replace(/ /g,"")==""){
        var index=layer.alert('璇疯緭鍏ュ唴瀹瑰悗鍦ㄨ繘琛屾煡璇� ', {
            skin: 'layui-layer-molv' //鏍峰紡绫诲悕
            ,closeBtn: 0
        }, function(){
            layer.close(index);
        });
        return ;
    }
    window.open("/common/search.html?keyword="+encodeURI(encodeURI($(".form-control").val())));
}
function enterArticle(id,link){
    window.open("/article/view/"+id+"/"+encodeURI(link)+"/show","_blank");
}
(function(window,document,undefined){
    var hearts = [];
    window.requestAnimationFrame = (function(){
        return window.requestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function (callback){
                setTimeout(callback,1000/60);
            }
    })();
    init();
    function init(){
        css(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: absolute;}.heart:after{top: -5px;}.heart:before{left: -5px;}");
        attachEvent();
        gameloop();
    }
    function gameloop(){
        for(var i=0;i<hearts.length;i++){
            if(hearts[i].alpha <=0){
                document.body.removeChild(hearts[i].el);
                hearts.splice(i,1);
                continue;
            }
            hearts[i].y--;
            hearts[i].scale += 0.004;
            hearts[i].alpha -= 0.013;
            hearts[i].el.style.cssText = "left:"+hearts[i].x+"px;top:"+hearts[i].y+"px;opacity:"+hearts[i].alpha+";transform:scale("+hearts[i].scale+","+hearts[i].scale+") rotate(45deg);background:"+hearts[i].color;
        }
        requestAnimationFrame(gameloop);
    }
    function attachEvent(){
        var old = typeof window.onclick==="function" && window.onclick;
        window.onclick = function(event){
            old && old();
            createHeart(event);
        }
    }
    function createHeart(event){
        var d = document.createElement("div");
        d.className = "heart";
        hearts.push({
            el : d,
            x : event.clientX - 5,
            y : event.clientY - 5,
            scale : 1,
            alpha : 1,
            color : randomColor()
        });
        document.body.appendChild(d);
    }
    function css(css){
        var style = document.createElement("style");
        style.type="text/css";
        try{
            style.appendChild(document.createTextNode(css));
        }catch(ex){
            style.styleSheet.cssText = css;
        }
        document.getElementsByTagName('head')[0].appendChild(style);
    }
    function randomColor(){
        return "rgb("+(~~(Math.random()*255))+","+(~~(Math.random()*255))+","+(~~(Math.random()*255))+")";
    }
})(window,document);
