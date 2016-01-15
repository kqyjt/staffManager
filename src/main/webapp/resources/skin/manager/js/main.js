 function logout()
 {
 	 window.location.href=managerPath+"/manager/sysman/Login.htm?m=execute&f=logout"; 
 }


$(document).ready(function() {
	
	$("body").on("click",".listmenu", function () { 
		
		$('.listmenu').removeClass('level_02_over');
	  	$(this).addClass('level_02_over');
	  	
		var loadurl=$(this).attr('taburl');
		var tabtitle=$(this).html();
		var iconcls=$(this).attr('iconcls');
		OpenTab('center_tabs',tabtitle,loadurl,iconcls);
	});
	
	$("body").on("click",".topmenu", function () { 
		
	  	$('.topmenu').removeClass('bor-over');
	  	$(this).addClass('bor-over');
		var menugroupid=$(this).attr('menugroupid');
		
		var iconcls=$(this).attr('iconcls');
		var menugroupname=$(this).html();
		var url = managerPath + "/manager/pageloader/PageLoader.htm?m=query&f=getMenuModules&groupid="+menugroupid;
		$('#menupanel').panel({iconCls:iconcls,title:menugroupname,href:url});

	});
	
	    $('#center_tabs').tabs('bindDblclick', function(index, title){
	      	if(index!='0')
	      	{
	        $('#center_tabs').tabs('close',index);
	      	}
        });
	    
	    $('#center_tabs').tabs({
	  	  onBeforeClose: function(title,index){
	  		if(title=='编辑页面')
	  		{
	  			$("#mykindeditor").kindeditor('editor').remove();
	  		}
	  	  }
	    });
	
});



var aminframework=true;
