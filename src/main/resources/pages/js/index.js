function closeInformation(){
	var offset = $(".forum_Information").offset();
	console.log(offset.left)
	if(offset.left>=0){
		$(".forum_Information").animate({
			left:"-300px" 
		},1000).find(".information_close").animate({left:"15px"},1000);
	}else{
		$(".forum_Information").animate({
			left:"0px" 
		},1000).find(".information_close").animate({left:"0px"},1000);
	}
}
$(function(){
	$(".article").css({"opacity":0})
	lazyloadDiv(".article");
	$(document).scroll(function(){
		lazyloadDiv(".article");
	});
	
	
});

