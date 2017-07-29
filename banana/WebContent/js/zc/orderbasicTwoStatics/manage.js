manage = Ext.extend(manageUi,{
	initComponent: function() {
		 manage.superclass.initComponent.call(this);

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
		 
		 
		 //excel导出
		 Ext.getCmp("exportButton").on("click", function(){
			 var formValues = Ext.getCmp("searchFrom").getForm().getValues();
			 window.open(basePath+"/admin/orderbasicTwo/exportStatics.do?"+jsonToParams(formValues));
		 });
	}
});