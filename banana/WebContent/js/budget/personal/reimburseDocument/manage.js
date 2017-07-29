manage = Ext.extend(manageUi,{
	initComponent: function() {
		 manage.superclass.initComponent.call(this);

		 //增加
		 if(!Ext.isEmpty(Ext.getCmp("addButton"))){
			 Ext.getCmp("addButton").on("click",function(){
				 var win = new AddWindow();
				 Ext.getCmp("update1Button").hide();
				 win.show();
				 Ext.getCmp("billFormPanel").getForm().reset();
				 Ext.getCmp("billFormPanel").getForm().findField("writerDepartmentCode").setValue(departmentCode);
			 });
		 }
		 
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
					 "budget/reimburseDocument/find.do", 
	 				 {id: records[0].id},
	 				 function(form, action){
	 					 Ext.getCmp("billDetailGrid").getStore().load({params: {id:records[0].id}});
	 				 }
	 			 );
				 
				 win.show();
			 });
		}
		 
		 //删除
		 if(!Ext.isEmpty(Ext.getCmp("deleteButton"))){
			 Ext.getCmp("deleteButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
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
	 			 
			 			 Ext.ux.Ajax.request("budget/reimburseDocument/delete.do", {ids: ids}, function(action, form){
							 var response =  Ext.util.JSON.decode(action.responseText);
							 if(Ext.isEmpty(response)){
							 Ext.ux.Msg.info("删除成功");
						 }else{
							 Ext.ux.Msg.info(response.data.instruction);
						 }
							 grid.getStore().reload();
						 });
					}
	 			});
			 });
	     }
		 
		 //提交
		 if(!Ext.isEmpty(Ext.getCmp("submitButton"))){
			 Ext.getCmp("submitButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录"); 
	 				 return;
	 			 }
	 			 
	 			 for(var i = 0; i < records.length; i++){
	 				 var data = records[i].data;
	 				 if(data.submitState == "已提交") {
	 					 Ext.ux.Msg.warning("单据【"+data.documentNum+"】已提交，不能再次提交！");
	 				 	 return;
	 				 }
	 			 }
	 			 
	 			 var deptComboTree =  createComboTree({
	           		 url: 'sys/department/queryTree.do',
	           		 fieldLabel: '<span style="color:red">*</span>审批部门',
	           		 hiddenName: 'departmentCode',
	           		 name: 'departmentName'
	             });
	 			 
	 			 deptComboTree.tree.on("click", function(node){
	 				 userCombo.clearValue();
	 				 userCombo.getStore().baseParams = {departmentCode: node.attributes.code};
	 				 userCombo.getStore().load();
	 				 console.log(userCombo.getStore().baseParams);
	 			 });
	 			 
	 			 var userCombo =  Ext.ux.form.ComboBox({
	 				 url: 'sys/user/getCombo.do',
	 				 fields: [{name: 'code'}, {name: 'name'}],
	 				 name: 'peopleName',
			    	 hiddenName: 'peopleCode',
					 fieldLabel: '<span style="color:red">*</span>审批人'
	 			 });
	 			 
	    		 var userForm = new Ext.form.FormPanel({
			         frame: true,
			         border: false,
			         buttonAlign: 'center',
			         autoHeight:true,
		             labelAlign: 'right',
		             //layout: 'column',
		             defaults: {width: 210, allowBlank: false},
		             defaultType: 'textfield',
			         items: [
			                deptComboTree
		             	 , 
		             	 	userCombo
		             	 ,{
		                	 fieldLabel:'备注',
		                     xtype:'textarea',
		                     name: 'remark',
		                     allowBlank: true,
		                     height: 30,
		                     width: 230
		                 }
		            ],
			        buttons: [{
		    			id: 'btn_form_save',
			            iconCls: 'saveIcon',
			            text: '提交',
			            handler: function(){
			        		if(!userForm.getForm().isValid()) return;
			            	Ext.MessageBox.confirm('确认', '提交之后无法修改，您确定要提交所选择的记录吗?', function(btn){
								if(btn == 'yes'){
						 			 var ids = [];
						 			 for(var i = 0; i < records.length; i++){
						 				 ids.push(records[i].id);
						 			 }
				 			 		 var values = userForm.getForm().getValues();
				 			 		 //console.log(values);
				 			 		 values.ids = ids;
						 			 Ext.ux.Ajax.request("budget/reimburseDocument/submit.do", values, function(action, form){
										 var response =  Ext.util.JSON.decode(action.responseText);
										 if(Ext.isEmpty(response)){
											 Ext.ux.Msg.info("提交成功");
										 }else{
											 Ext.ux.Msg.info(response.data.instruction);
										 }
										 userWin.hide();
										 grid.getStore().reload();
									 });
								}
				 			});
			            }
			        },{
			        	id: 'btn_form_cancle',
			            iconCls: 'deleteIcon',
			            text: '取消',
			            handler: function(){
			            	userWin.hide();
			            }
			         }]
	    		 });
	    		 
	    		 userWin = createWindow("提交单据", userForm, 400, 165);
	 			 userWin.show();
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
					 "budget/reimburseDocument/find.do", 
	 				 {id: records[0].id},
	 				 function(form, action){
	 					 //Ext.getCmp("workflowGrid").getStore().load({params: {id:records[0].id, billType: 0}});
	 					 
	 					 Ext.getCmp("billDetailGrid").getStore().load({params: {id:records[0].id}});
	 				 }
	 			 );
				 
				 win.show();
			 });
		 }
		 
		/* Ext.getCmp("exportButton").on("click", function(){
			 var formValues = Ext.getCmp("condition").getForm().getValues();
			 window.open(basePath+"/budget/reimburseDocument/queryList.do?export=excel&"+jsonToParams(formValues));
		 });*/
		 
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
	 			 
	 			 printReport(records[0].id, "reimburse_document");
			 });
		 }
		 //controller.superclass.initComponent.call(this);

	}
});
