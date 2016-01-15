<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

  
	<div class="easyui-panel" style="padding:5px">
		<ul class="easyui-tree" id="role_right_tree">
		</ul>
	</div>
	<input type="hidden" id="role_right_roleId" name="role_right_roleId"  value="${result.dataSet.roleId}"/>
	<input type="hidden" id="role_right_moduleId" name="role_right_moduleId"  value="${result.dataSet.moduleId}"/>
	
	<!-- 操作按钮  -->
	<div>
		<table>
		  <tr>
			<td>
				<a onclick="isSelected()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">
					分配
				</a>
			</td>
			<td><div class="datagrid-btn-separator"></div></td>
			<td>
				<a onclick="isCanceled()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">
					取消
				</a>
			</td>
		  </tr>
		</table>
	</div>
	
	<script type="text/javascript">
		
		function isSelected() {
			
			 var nodes = $('#role_right_tree').tree('getChecked');
			 var params = new Object();
			 params.roleId = $("#role_right_roleId").val();
			 var s = '';
			 for(var i=0; i<nodes.length; i++){
				 if (s != '') s += ',';
				 //s += nodes[i].text;
				 s += nodes[i].id;
			 }
			 params.moduleId = s;
			 
			 var clientRequest = new Tcsp.ClientRequest();
			// 发送异步请求
			clientRequest.postJSON(managerPath+'/manager/system/TMRoleRightMgr.json?m=execute&f=modulesRightSave',params,function(data){
				if(data.result.success&&data.result.errorCode=='00000'){
					$.messager.alert("操作提示","权限分配成功");
				}else{
					$.messager.alert("操作提示",data.result.errorMsg);
				}
			});
			 
		}
		
		function isCanceled(){
			$.messager.confirm('操作提示','确定取消为角色分配权限吗?',function(confirm_status){
				if (confirm_status){
					//跳转到角色管理页面
					$('#center_tabs').tabs('close','分配权限');
				}
			});
		}
		
			$('#role_right_tree').tree({
			    url: managerPath+'/manager/system/TMRoleRightMgr.json?m=query&f=rightListQuery',
			    checkbox:"true",
			    loadFilter: function(data){    
			        if (data.result.dataSet.list){    
			            return data.result.dataSet.list;    
			        } else {    
			            return data;    
			        }    
			    },
			      onLoadSuccess:function(node, data){
			    	  
			    	  var str=$("#role_right_moduleId").val();
			    		var arr=str.split(",");
			    		
			    		for (var int = 0; int < arr.length; int++) {
							
							var n = $('#role_right_tree').tree('find',arr[int]);
				    		$('#role_right_tree').tree('check',n.target);
						}
			    		

			    }
			});  
				
		
	</script>
