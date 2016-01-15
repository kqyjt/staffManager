$.fn.DivSlider = function(arg) {
	var me = $(this);
	var hoverflag=false;
	var arrowhtml='<div class="prevdiv pointer prevtag" style="width:50px; height:50px; border:2px solid #fff; border-radius:50px;  text-align:center; box-shadow:1px 1px 1px #666">'
					+'<i style="font-size:24px; color:#fff;line-height:50px; text-shadow:1px 1px 1px #666;" class="icon-chevron-left prevtag"></i>    </div>'
					+'<div class="nextdiv pointer nexttag" style="width:50px; height:50px; border:2px solid #fff; border-radius:50px;  text-align:center;box-shadow:1px 1px 1px #666">'
					+'<i style="font-size:24px;line-height:50px; color:#fff;text-shadow:1px 1px 1px #666;" class=" icon-chevron-right nexttag"></i>   </div> ';
    
    
	var opt = $.extend({
				inteval : 2000,
				animatespeed:2000,
				auto : true,
				loop : false,
				direction:'left',
				itemclass:'slideritem',
				showpreb:true,
				prev : me.find(".prevdiv"),
				next : me.find(".nextdiv"),
				pagebtnsclass : '',
				pagebtn:false,
				movepx:'100%',
				before :function() {
				},
				finish : function() {
				},
				start : function() {
				}
			}, arg);
	var pageBtns,scrollable = me;
	var ori_items = me.find('.'+opt.itemclass);
	var total_num = ori_items.size();
	
	if(total_num<=1)
	{
		return;
	}
	
	if(opt.showpreb)
	{
		me.append(arrowhtml);
    //var halfheight= me.find(".slideritem").outerHeight()/2;
    me.find(".prevdiv").css({'top':'50%','position':'absolute','margin-top':'-25px','left':'50px','z-index':'9999'});
    me.find(".nextdiv").css({'top':'50%','position':'absolute','margin-top':'-25px','right':'50px','z-index':'9999'});
	}
	/*var templi='<div class="new_lunbotu_menuer" > <li class="new_lunbotu_menuer_li new_lunbotu_menuer_a " ></li>';
	for (var i=0;i<total_num-1;i++)
	{
		templi=templi+' <li  class="new_lunbotu_menuer_li" ></li>';
	}
	templi=templi+'</div>';
	
	if(opt.pagebtn)
	{
		 me.append(templi);
	}*/
	
	var mov_w = me.find('.'+opt.itemclass).outerWidth();
    var	cur_itemsn=0,
		pre_itemsn=0,
		next_itemsn=0,
		cur_item,
		next_item,
		pre_item,
		interval;
		//mov_w = document.body.scrollWidth, 
	var move_left = 0;
	if(opt.direction=='right')
	{
		move_left=1;
	}
			/*ori_items.each(function(index,item) {
			 if(index>0)
			 {
			 	$(this).css('left',index*100+'%');
			 	$(this).show();
			 }
			
			});*/
	
	cur_item=$(ori_items[cur_itemsn]);
	
	
	var move=function(e) {
		opt.before(cur_item);
		opt.prev.length && opt.prev.unbind("click", move);
		opt.next.length && opt.next.unbind("click", move);
		if (e) {
			clearInterval(interval);
			var cur_btn = $(e.target);
			if (cur_btn.hasClass("prevtag")) {
				move_left = 1
			};
			if (cur_btn.hasClass("nexttag")) {
				move_left = 0
			};
		}

		var cur_scrollLeft = move_left? + mov_w : - mov_w;
		//alert(scrollable.width());
		//alert(cur_scrollLeft);
		var tempnextitem=move_left? pre_item: next_item;
		
		/*alert(pre_itemsn);
		alert(cur_itemsn);
		alert(next_itemsn);*/
		if(total_num==2)
		{
			if(move_left==1)
			{
				pre_item.css('margin-left','-'+opt.movepx);
			}
			else
			{
				next_item.css('margin-left',opt.movepx);
			}
		}
		
		
		tempnextitem.stop().animate({
					'margin-left':'0px'
				}, {
					duration : opt.animatespeed,
					easing : 'swing',
					//complete : complate,
					queue : false
				});
		cur_item.stop().animate({
					'margin-left':cur_scrollLeft+'px'
				}, {
					duration : opt.animatespeed,
					easing : 'swing',
					complete : complate,
					queue : false
				});	
				
				if (e&&!hoverflag) {
					interval = setInterval(move, opt.inteval);
				}
	};
	var complate=function() {
		setcur_item();
		setpre_item();
        setnext_item();
		opt.prev.length && opt.prev.bind("click", move);
		opt.next.length && opt.next.bind("click", move);
		opt.finish(cur_item);
		pageBtns.removeClass("new_lunbotu_menuer_a");
		$(pageBtns[cur_itemsn]).addClass("new_lunbotu_menuer_a");
		
	};
	var setnext_item=function()
	{
		
		if(cur_itemsn==(total_num-1))
		{
			next_itemsn=0;
		}
		else
		{
		next_itemsn=cur_itemsn+1;
		}
		next_item=$(ori_items[next_itemsn]);
		next_item.css('margin-left',opt.movepx);
		next_item.show();
	};
	var setpre_item=function()
	{
		
		if(cur_itemsn==0)
		{
			pre_itemsn=total_num-1;
		}
		else
		{
		pre_itemsn=cur_itemsn-1;
		}
		pre_item=$(ori_items[pre_itemsn]);
		pre_item.css('margin-left','-'+opt.movepx);
		pre_item.show();
	};
	var setcur_item=function()
	{
		//cur_item.hide();
		if(move_left==1)
		{
			if(cur_itemsn==0)
			{
				cur_itemsn=total_num-1;
			}
			else
			{
				cur_itemsn=cur_itemsn-1;
			}
			cur_item=$(ori_items[cur_itemsn]);
		}
		else
		{
			if(cur_itemsn==(total_num-1))
			{
				cur_itemsn=0;
			}
			else
			{
				cur_itemsn=cur_itemsn+1;
			}
			cur_item=$(ori_items[cur_itemsn]);
			
		}
		
		
	};
	
	
	opt.start(cur_item);
    setpre_item();
    setnext_item();
	opt.prev.length && opt.prev.bind("click", move).hover(function() {
				$(this).addClass("hover")
			}, function() {
				$(this).removeClass("hover")
			});
	opt.next.length && opt.next.bind("click", move).hover(function() {
				$(this).addClass("hover")
			}, function() {
				$(this).removeClass("hover")
			});
	opt.prev.hover(function() {
			$(this).css({'background':'none repeat scroll 0 0 rgba(255, 255, 255, 0.6)'});
		}, function() {
			 $(this).css({'background':''});
		});
    opt.next.hover(function() {
		 	$(this).css({'background':'none repeat scroll 0 0 rgba(255, 255, 255, 0.6)'});
		}, function() {
			 $(this).css({'background':''});
		});
	if (opt.auto) {
		interval = setInterval(move, opt.inteval);
		me.hover(function() {
					clearInterval(interval);
					hoverflag=true;
				}, function() {
					interval = setInterval(move, opt.inteval);
					hoverflag=false;
				});
	}
	if (opt.pagebtn && opt.pagebtnsclass) {
		pageBtns = me.find('.'+opt.pagebtnsclass);
		pageBtns.bind("click", moveTo);
	}
	function moveTo(e) {
		cur_item.stop();
		pageBtns.unbind("click", moveTo);
		var cur_li = $(e.target).closest("li"), num = cur_li.index();
		var oldcur_item=cur_itemsn;
		cur_itemsn=num;
		cur_item=$(ori_items[cur_itemsn]);
		
		if(oldcur_item==cur_itemsn)
		{
			pageBtns.bind("click", moveTo);
			return;
		}
		
		
        //alert(cur_itemsn);
		//$(ori_items[oldcur_item]).css('margin-left','-100%');
		$(ori_items[oldcur_item]).hide();
		cur_item.css('margin-left','0px').hide();
		cur_item.fadeIn(600);
		
		
		//setcur_item();
		setpre_item();
        setnext_item();

		
		pageBtns.removeClass("new_lunbotu_menuer_a");
		cur_li.addClass("new_lunbotu_menuer_a");
		
		pageBtns.bind("click", moveTo);
		/*var cur_scrollLeft = num * mov_w;
		scrollable.animate({
						left:cur_scrollLeft+'px'
				}, {
					duration : opt.animatespeed,
					easing : 'swing',
					complete :complate,
					queue : false
				});*/
		
		
		
	}

	
};
