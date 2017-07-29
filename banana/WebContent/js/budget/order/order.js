order=Ext.extend(orderUi,{
	initComponent: function() {
		 order.superclass.initComponent.call(this);
		 //对每一个组件加事件
		 /**增加事件**/
		 Ext.getCmp("addButton").on("click",function(){
			var userForm=new orderForm();
			userWin = createWindow("添加用户",userForm, 380, 330); 
			userWin.setTitle("添加用户信息");
			userWin.show();
    		userForm.getForm().reset();
    		
    		Ext.getCmp("btn_form_save").on("click",function(){
			Ext.ux.Form.submitForm(userForm, ctx+"/wechar/order/save.do", 
		          function(form, action){
		            	Ext.Msg.alert('消息', action.result.data.instruction);
		            	Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
		        			if(btn == 'yes'){
		        				userForm.getForm().reset();
		        			}else{
		        				userWin.hide();
		        		}
		          })
		            Ext.getCmp("mainGrid").getStore().load();
		          }		
		        );
		 	});
		 });
		  /**修改事件**/
		 Ext.getCmp("updateButton").on("click",function(){
				userForm=new orderForm();
			 	userWin = createWindow("更新用户",userForm, 380, 330); 
			 	
    			//准备加载数据
    			var records = Ext.getCmp("mainGrid").getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}else if(records.length > 1){
    				Ext.ux.Msg.warning("您选择的记录过多，本操作不支持批量修改，您只能选择一条记录！");
    				return;
    			}
    			
    			Ext.getCmp("btn_form_save").hide();
    			Ext.getCmp("btn_form_update").show();
    			userWin.show();
    			Ext.ux.Form.loadForm(userForm, ctx+"/wechar/order/find.do", {id: records[0].id});
    			
    			Ext.getCmp("btn_form_update").on("click",function(){
					Ext.ux.Form.submitForm(Ext.getCmp("orderForm"), ctx+"/wechar/order/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.getCmp("mainGrid").getStore().load();
		            			userWin.hide();
		            			Ext.getCmp("orderForm").getForm().reset();
	            		}		
		            );
		 		});
		 });
		 /**删除事件**/
		  Ext.getCmp("deleteButton").on("click",function(){
			   var userGrid=Ext.getCmp("mainGrid");
			   var records = userGrid.getSelectionModel().getSelections();
    			if(records.length == 0){
    				Ext.ux.Msg.warning("请选择一条记录"); 
    				return;
    			}
    			
    			Ext.MessageBox.confirm('确认', '您确定要删除所选择的记录吗?', function(btn){
					if(btn == 'yes'){
						var ids = [];
        				for(var i = 0; i < records.length; i++){
        					ids.push(records[i].id);
        				}
        				Ext.ux.Ajax.request(ctx+"/wechar/order/delete.do", {ids: ids}, function(action, form){
   							var response =  Ext.util.JSON.decode(action.responseText);
   							if(Ext.isEmpty(response)){
								Ext.ux.Msg.info("删除成功");
							}else{
								Ext.ux.Msg.info(response.data.instruction);
							}
   							userGrid.getStore().reload();
   						});
					}
    			})
		  });
		 
	}
});
