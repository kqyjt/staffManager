<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

  
	<div class="easyui-panel" style="padding:5px">
		<ul class="easyui-tree" id="role_tree">
		</ul>
	</div>
	<input type="hidden" id="roles_staffId" name="roles_staffId"  value="${result.dataSet.staffId}"/>
	<input type="hidden" id="roles_staffRoleId" name="roles_staffRoleId"  value="${result.dataSet.staffRoleId}"/>
	<input type="hidden" id="roles_roleId" name="roles_roleId"  value="${result.dataSet.roleId}"/>
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
			
			 var nodes = $('#role_tree').tree('getSelected');
			 var params = new Object();
			 params.staffId = $("#roles_staffId").val();
			 params.roleId = nodes.id;
			 var clientRequest = new Tcsp.ClientRequest();
			// 发送异步请求
			clientRequest.postJSON(managerPath+'/manager/system/TMRolesMgr.json?m=execute&f=staffRoleSave',params,function(data){
				if(data.result.success&&data.result.errorCode=='00000'){
					$.messager.alert("操作提示","角色分配成功");
				}else{
					$.messager.alert("操作提示",data.result.errorMsg);
				}
			});
			 
		}
		
		function isCanceled(){
			$.messager.confirm('操作提示','确定取消为操作员分配角色吗?',function(confirm_status){
				if (confirm_status){
					//跳转到操作员管理页面
					$('#center_tabs').tabs('close','分配角色');
				}
			});
		}
		

			$('#role_tree').tree({
			    url: managerPath+'/manager/system/TMRolesMgr.json?m=query&f=roleTreeQuery',
			    loadFilter: function(data){    
			        if (data.result.dataSet.list){    
			            return data.result.dataSet.list;    
			        } else {    
			            return data;    
			        }    
			    },
			    onLoadSuccess:function(node, data){
			    	var n = $('#role_tree').tree('find',$("#roles_roleId").val());
			    	if(n!=null){
			    		$('#role_tree').tree('select',n.target);  
			    	}
			    }
			});  

		
	</script>
