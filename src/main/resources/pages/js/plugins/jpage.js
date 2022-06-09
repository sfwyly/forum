/**
 * by 閫濋鏃犺█ 2018/8/18
 * @param {Object} parentDiv 鐖跺潡鐨勭储寮�
 * @param {Object} childDiv 瀛愬潡鐨勭储寮�
 * @param {Object} onePageNumber 姣忛〉鐨勬暟閲�
 * @param {Object} showDiv 鍒嗛〉鏄剧ず鐨勫潡
 */
function Jpage(parentDiv,childDiv,onePageNumber,showDiv){
	var t=this;
	this.onePageNumber=onePageNumber;//姣忛〉鏄剧ず涓暟
	this.currentPage=1;//鍒濆褰撳墠椤垫暟涓�0
	this.parentDiv=$(parentDiv);
	this.childDiv=$(childDiv);
	//瀛愬潡鏁伴噺
	this.sumNumber=function(){
		return this.parentDiv.find(childDiv).length;
	};
	this.pageNumber=Math.ceil(this.sumNumber()/onePageNumber);//椤垫暟
	//涓嬩竴椤�
	this.nextPage=function(){
		if(this.currentPage==this.pageNumber){
			this.currentPage=1;
		}else{
			this.currentPage++;
		}
		this.showCurrentPage();
	}
	//涓婁竴椤�
	this.prePage=function(){
		if(this.currentPage==1){
			this.currentPage=this.pageNumber;
		}else{
			this.currentPage--;
		}
		this.showCurrentPage();
	}
	//棣栭〉
	this.firstPage=function(){
		this.currentPage=1;
		this.showCurrentPage();
	}
	//灏鹃〉
	this.endPage=function(){
		this.currentPage=this.pageNumber;
		this.showCurrentPage();
	}
	//璺宠浆椤甸潰
	this.goPage=function(pageNum){
		if(pageNum<=0){
			this.currentPage=1;
		}else if(pageNum>this.pageNumber){
			this.currentPage=this.pageNumber;
		}else{
			this.currentPage=pageNum;
		}
//		console.log(this.currentPage)
		this.showCurrentPage();
		
	}
	//涓嬮潰鐨勭殑鏁板瓧鎸夐挳瑁呴グ
	this.decoratePage=function(){
		$(".bottomButton .numPage span").css({
			"border":"1px solid gray",
			"border-radius":"3px",
			"padding":"2px 8px 2px 8px",
			"color":"black",
			"margin":"0px 3px 0px 3px",
			"cursor":"pointer",
			"font-size":"1rem"
		}).click(function(){
			
			t.goPage(parseInt($(this).text()));
		});
		$(".bottomButton .numPage .currentPage").css({
			"background-color":"lightseagreen",
			"border":"1px solid lightseagreen",
			"color":"white"
		});
	}
	//涓轰笅闈㈠嚑涓寜閽坊鍔犳牱寮�
	this.decorateOther=function(){
		$(".bottomButton .first").click(function(){
		t.firstPage();
	}).css({
		"font-size":"1rem",
		"display":"inline-block",
		"padding":"2px",
		"border":"1px solid gray",
		"border-radius":"3px",
		"cursor":"pointer",
		"font-family":"STfangsong",
		"font-weight":"600"
	}).mouseover(function(){
		$(this).css({
			"border":"1px solid lightseagreen",
			"color":"lightseagreen"
		});
	}).mouseout(function(){
		$(this).css({
			"border":"1px solid gray",
			"color":"black"
		});
	});
	$(".bottomButton .end").click(function(){
		t.endPage();
	}).css({
		"font-size":"1rem",
		"display":"inline-block",
		"padding":"2px",
		"border":"1px solid gray",
		"border-radius":"3px",
		"cursor":"pointer",
		"font-family":"STfangsong",
		"font-weight":"600"
	}).mouseover(function(){
		$(this).css({
			"border":"1px solid lightseagreen",
			"color":"lightseagreen"
		});
	}).mouseout(function(){
		$(this).css({
			"border":"1px solid gray",
			"color":"black"
		});
	});
	$(".bottomButton .pre").click(function(){
		t.prePage();
	}).css({
		"font-size":"1rem",
		"display":"inline-block",
		"padding":"2px",
		"border":"1px solid gray",
		"border-radius":"3px",
		"cursor":"pointer",
		"font-family":"STfangsong",
		"font-weight":"600"
	}).mouseover(function(){
		$(this).css({
			"border":"1px solid lightseagreen",
			"color":"lightseagreen"
		});
	}).mouseout(function(){
		$(this).css({
			"border":"1px solid gray",
			"color":"black"
		});
	});
	$(".bottomButton a").css({
		"margin":"5px 5px 0 5px"
	});
	$(".bottomButton .next").click(function(){
		t.nextPage();
	}).css({
		"font-size":"1rem",
		"display":"inline-block",
		"padding":"2px",
		"border":"1px solid gray",
		"border-radius":"3px",
		"cursor":"pointer",
		"font-family":"STfangsong",
		"font-weight":"600"
	}).mouseover(function(){
		$(this).css({
			"border":"1px solid lightseagreen",
			"color":"lightseagreen"
		});
	}).mouseout(function(){
		$(this).css({
			"border":"1px solid gray",
			"color":"black"
		});
	});
	}
	//鍒涘缓甯冨眬
	this.createPage=function(){
		if(!this.parentDiv||this.parentDiv.text()==""){
			// alert("鐖跺潡涓嶅瓨鍦紒")
			return ;
		}else{
			if(!this.parentDiv.find(childDiv)||this.parentDiv.find(childDiv).text()==""){
				// alert("瀛愬潡涓嶅瓨鍦紒");
				return ;
			}
		}
		this.showCurrentPage();
		this.decoratePage();
		this.decorateOther();
	}
	//鏄剧ず褰撳墠椤甸潰
	this.showCurrentPage=function(){
//		console.log("鎵ц");
		if(this.currentPage<=0){
			this.currentPage=1;
		}
		var start=(this.currentPage-1)*this.onePageNumber;//寮€濮嬬储寮曚綅缃紝涓嶇敤鍑忎竴
		this.showChildDiv(start);//璋冪敤鍑芥暟鏄剧ず瀛愬潡
		this.addButton();
	}
	//鏄剧ず褰撳墠椤甸潰鐨勫潡
	this.showChildDiv=function(start){
		this.parentDiv.find(childDiv).hide();
		for(var i=start;i<this.parentDiv.find(childDiv).length&&i< parseInt(start)+this.onePageNumber;i++){
			this.parentDiv.find(childDiv).eq(i).show();
		}
	}
	//娣诲姞涓嬫柟涓婁竴椤典笅涓€椤电殑button
	this.addButton=function(){
		var str="";
		var strBtn="";
		if(this.pageNumber<5||this.currentPage<=3){//鎬婚〉鏁板皬浜�5
			for(var i=1;i<=this.pageNumber&&i<=5;i++){
				if(i==this.currentPage){//褰撳墠椤甸潰
					strBtn+="<span class='currentPage'>"+this.currentPage+"</span>";
				}else{
					strBtn+="<span class='page_num'>"+i+"</span>";
				}
			}
		}else{//鎬婚〉鏁�>=5骞朵笖褰撳墠椤�>3
			if(this.pageNumber-2>=this.currentPage){//鑳藉線鍓嶅悗鏁颁袱涓�
				for(var j=this.currentPage-2;j<=parseInt(this.currentPage)+2;j++){
					if(j==this.currentPage){//褰撳墠椤甸潰
						strBtn+="<span class='currentPage'>"+this.currentPage+"</span>";
					}else{
						strBtn+="<span class='page_num'>"+j+"</span>";
					}
				}
			}else{
				for(var i=this.pageNumber-4;i<=this.pageNumber;i++){
					if(i==this.currentPage){//褰撳墠椤甸潰
						strBtn+="<span class='currentPage'>"+this.currentPage+"</span>";
					}else{
						strBtn+="<span class='page_num'>"+i+"</span>";
					}
				}
			}
		}
		//澶勭悊涓€涓嬪綋鍓嶉〉闈袱绔殑椤垫暟
		if(!$(".bottomButton")||$(".bottomButton").text()==""){
			str="<div class='bottomButton' ><span class='sumNumber'>"+this.sumNumber()+" 鏉�</span><span class='pageNumber'>  鍏�"+this.pageNumber+"椤�</span>  <span class='first'>棣栭〉</span><span class='pre'><<</span> <span class='numPage'>"
			+strBtn+" </span> <span class='next'>>></span><span class='end'>灏鹃〉</span></div>";
			$(showDiv).html(str);
		}
		else{
			$(".bottomButton .numPage").html(strBtn);
		}
		this.decoratePage();
	}
}
