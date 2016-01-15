
/*
 * 使用方法 DaytimeDown("2015/9/8 11:11:59","#colockbox"); 
 * dom结构如下 #colockbox 下 为   #day #hour #minute #second
 * */

function DaytimeDown(time,id,callbackfun){
	var day_elem = $(id).find('#day');
	var hour_elem = $(id).find('#hour');
	var minute_elem = $(id).find('#minute');
	var second_elem = $(id).find('#second');
	var end_time = new Date(time).getTime(),//月份是实际月份-1
	sys_second = (end_time-new Date().getTime())/1000;
	var timer = setInterval(function(){
		if (sys_second >= 1) {
			sys_second -= 1;
			var day = Math.floor((sys_second / 3600) / 24);
			var hour = Math.floor((sys_second / 3600) % 24);
			var minute = Math.floor((sys_second / 60) % 60);
			var second = Math.floor(sys_second % 60);
			day_elem && $(day_elem).text(day);//计算天
			$(hour_elem).text(hour<10?'0'+hour:hour);//计算小时
			$(minute_elem).text(minute<10?'0'+minute:minute);//计算分钟
			$(second_elem).text(second<10?'0'+second:second);//计算秒杀
		} else { 
			clearInterval(timer);
			if(typeof(callbackfun) != 'undefined'){callbackfun();}
		}
	}, 1000);
}

var  t;//shh added  目的为了停掉倒计时
/*
 * 参考结构为：
 * <a class="huoquyanzm" href="javascript:void(0);"  id="sendverfcode">获取验证码</a><span id="seconddown" style="display:none;width:50px;margin-left:35px;"></span>
*/
function SectimeDown(time,timespan,verifycodebtn)
{
    if(time=='undefined')
        time = 60;
    $('#'+verifycodebtn).hide();
    $('#'+timespan).show();
    $('#'+timespan).text(time+'秒');
    time = time-1;
    if(time>=0)
    {
    	// 变量 t     shh added  目的为了停掉倒计时
        t = setTimeout('SectimeDown('+time+',"'+timespan+'","'+verifycodebtn+'")',1000);
    }else
    {
        $('#'+verifycodebtn).show();
        $('#'+timespan).hide();
    }
}


/*
 * 使用方法 DaytimeDown($(this)); 
 * 
 * "2015/9/8 11:11:59",
 * */

function DaytimeDownClass(jqobjs,callbackfun){
	$.each(jqobjs, function(i,jqobj){ 
		var time=$(jqobj).attr('time'); 
		var day_elem = $(jqobj).find('.day');
		var hour_elem = $(jqobj).find('.hour');
		var minute_elem = $(jqobj).find('.minute');
		var second_elem = $(jqobj).find('.second');
		var end_time = new Date(time).getTime(),//月份是实际月份-1
		sys_second = (end_time-new Date().getTime())/1000;
		var timer = setInterval(function(){
			if (sys_second >= 1) {
				sys_second -= 1;
				var day = Math.floor((sys_second / 3600) / 24);
				var hour = Math.floor((sys_second / 3600) % 24);
				var minute = Math.floor((sys_second / 60) % 60);
				var second = Math.floor(sys_second % 60);
				day_elem && $(day_elem).text(day);//计算天
				$(hour_elem).text(hour<10?'0'+hour:hour);//计算小时
				$(minute_elem).text(minute<10?'0'+minute:minute);//计算分钟
				$(second_elem).text(second<10?'0'+second:second);//计算秒杀
			} else { 
				clearInterval(timer);
				if(typeof(callbackfun) != 'undefined'){callbackfun($(jqobj));}
			}
		}, 1000);
	});
}


/*
 * 使用方法 DaytimeDown($(this)); 
 * 
 * "2015/9/8 11:11:59",
 * */

function DaytimeDownSEClass(jqobjs,callbackfun){
	$.each(jqobjs, function(i,jqobj){ 
		var endtime=$(jqobj).attr('endtime'); 
		var starttime=$(jqobj).attr('starttime');
		var day_elem = $(jqobj).find('.day');
		var hour_elem = $(jqobj).find('.hour');
		var minute_elem = $(jqobj).find('.minute');
		var second_elem = $(jqobj).find('.second');
		var end_time = new Date(endtime).getTime(),//月份是实际月份-1
		sys_second = (end_time-new Date(starttime).getTime())/1000;
		var timer = setInterval(function(){
			if (sys_second >=1) {
				sys_second -= 1;
				var day = Math.floor((sys_second / 3600) / 24);
				var hour = Math.floor((sys_second / 3600) % 24);
				var minute = Math.floor((sys_second / 60) % 60);
				var second = Math.floor(sys_second % 60);
				day_elem && $(day_elem).text(day);//计算天
				$(hour_elem).text(hour<10?'0'+hour:hour);//计算小时
				$(minute_elem).text(minute<10?'0'+minute:minute);//计算分钟
				$(second_elem).text(second<10?'0'+second:second);//计算秒杀
			} else { 
				clearInterval(timer);
				if(typeof(callbackfun) != 'undefined'){callbackfun($(jqobj));}
			}
		}, 1000);
	});
}
