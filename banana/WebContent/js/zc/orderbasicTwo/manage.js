manage = Ext.extend(manageUi,{
	initComponent: function() {
		 manage.superclass.initComponent.call(this);

		 //修改
		 if(!Ext.isEmpty(Ext.getCmp("editButton"))){
			 Ext.getCmp("editButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录！"); 
	 				 return;
	 			 }else if(records.length > 1){
	 				 Ext.ux.Msg.warning("最多只能修改一条记录，请重新选择！");
	 				 return;
	 			 }
	 			 
	 			 for(var i = 0; i < records.length; i++){
	 				 var data = records[i].data;
	 				 if(data.submitState == "已提交" && !(data.auditState == "已驳回")) {
	 					 Ext.ux.Msg.warning("单据已提交，无法修改！");
	 				 	 return;
	 				 }
	 			 }
	 			 
				 var win = new AddWindow();
				 
				 Ext.getCmp("add1Button").hide();
				 
				 Ext.ux.Form.loadForm(Ext.getCmp("billFormPanel"), 
					 "admin/orderbasicTwo/find.do", 
	 				 {id: records[0].data.crowdfundingCode},
	 				 function(form, action){
	 					 Ext.getCmp("billDetailGrid").getStore().load({params: {id:records[0].data.crowdfundingCode}});
	 				 }
	 			 );
				 
				 win.show();
			 });
		}
		 
		
		 
		 //查看
		 if(!Ext.isEmpty(Ext.getCmp("scanButton"))){
			 Ext.getCmp("scanButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录！"); 
	 				 return;
	 			 }else if(records.length > 1){
	 				 Ext.ux.Msg.warning("最多只能查看一条记录，请重新选择！");
	 				 return;
	 			 }
	 			 
				 var win = new AddWindow();
				 
				 Ext.getCmp("add1Button").hide();
				 Ext.getCmp("update1Button").hide();
				 
				 Ext.ux.Form.loadForm(Ext.getCmp("billFormPanel"), 
					 "admin/orderbasicTwo/find.do", 
	 				 {id: records[0].data.crowdfundingCode},
	 				 function(form, action){
	 					 //Ext.getCmp("workflowGrid").getStore().load({params: {id:records[0].id, billType: 0}});
	 					 console.log(records);
	 					 Ext.getCmp("billDetailGrid").getStore().load({params: {orderCode:records[0].data.crowdfundingCode}});
	 				 }
	 			 );
				 
				 win.show();
			 });
		 }
		  
		 //给grid添加双击事件
		 if(!Ext.isEmpty(Ext.getCmp("mainGrid"))){
			 Ext.getCmp("mainGrid").on("rowdblclick", function(grid, index){
				 showDetail(grid.getStore().getAt(index).data.crowdfundingCode);
			 });
		 }
		 
		 //excel导出
		 Ext.getCmp("exportButton").on("click", function(){
			 var formValues = Ext.getCmp("searchFrom").getForm().getValues();
			 window.open(basePath+"/admin/orderbasicTwo/export.do?"+jsonToParams(formValues));
		 });
		 
		 if(!Ext.isEmpty(Ext.getCmp("printButton"))){
			 Ext.getCmp("printButton").on("click", function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录！"); 
	 				 return;
	 			 }else if(records.length > 1){
	 				 Ext.ux.Msg.warning("最多只能导出一条记录，请重新选择！");
	 				 return;
	 			 }
	 			 
	 			 printReport(records[0].id, "borrow_apply_document");
			 });
		 }
	}
});


function showDetail(id){
	 var win = new AddWindow();
				 
	 Ext.ux.Form.loadForm(Ext.getCmp("billFormPanel"), 
		 "admin/orderbasicTwo/find.do", 
 		 {id: id},
 		 function(form, action){
 			//Ext.getCmp("workflowGrid").getStore().load({params: {id:records[0].id, billType: 0}});
 			var result = Ext.util.JSON.decode(action.response.responseText);
 			Ext.getCmp("add1Button").hide();
 			Ext.getCmp("update1Button").hide();
 			Ext.getCmp("billDetailGrid").getStore().load({params: {orderCode:id}});
 		}
 	);
			 
	win.show();
}

