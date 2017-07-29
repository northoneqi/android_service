manage = Ext.extend(manageUi,{
	initComponent: function() {
		 manage.superclass.initComponent.call(this);
		 
		 //审核
		 if(!Ext.isEmpty(Ext.getCmp("auditButton"))) {
			 Ext.getCmp("auditButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录"); 
	 				 return;
	 			 }
	 			 
	 			 for(var i = 0; i < records.length; i++){
	 				 var data = records[i].data;
	 				 if(data.documentState == "关闭") {
	 					 Ext.ux.Msg.warning("单据已关闭，无法再审核！");
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
	 				 //console.log(userCombo.getStore().baseParams);
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
		                	 fieldLabel:'意见',
		                     xtype:'textarea',
		                     name: 'opinion',
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
			            	Ext.MessageBox.confirm('确认', '您确定同意审核所选择的记录吗?', function(btn){
								if(btn == 'yes'){
						 			 var ids = [];
						 			 for(var i = 0; i < records.length; i++){
						 				 ids.push(records[i].id);
						 			 }
				 			 		 var values = userForm.getForm().getValues();
				 			 		 values.ids = ids;
						 			 Ext.ux.Ajax.request("budget/expenseDocument/audit.do", values, function(action, form){
										 var response =  Ext.util.JSON.decode(action.responseText);
										 if(Ext.isEmpty(response)){
											 Ext.ux.Msg.info("审核成功");
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
		 
		 
		 //退审
		 if(!Ext.isEmpty(Ext.getCmp("comeBackButton"))) {
			 Ext.getCmp("comeBackButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请选择一条记录"); 
	 				 return;
	 			 }
	 			 
	 			 for(var i = 0; i < records.length; i++){
	 				 var data = records[i].data;
	 				 if(data.documentState == "关闭") {
	 					 Ext.ux.Msg.warning("单据已关闭，无法再退审！");
	 				 	 return;
	 				 }
	 			 }
	 			 
	    		 var userForm = new Ext.form.FormPanel({
			         frame: true,
			         border: false,
			         buttonAlign: 'center',
			         autoHeight:true,
		             labelAlign: 'right',
		             //layout: 'column',
		             defaults: {width: 210, allowBlank: false},
		             defaultType: 'textfield',
			         items: [{
		                	 fieldLabel:'意见',
		                     xtype:'textarea',
		                     name: 'opinion',
		                     allowBlank: true,
		                     height: 30,
		                     width: 230
		                 }
		            ],
			        buttons: [{
		    			id: 'btn_form_save',
			            iconCls: 'saveIcon',
			            text: '退审',
			            handler: function(){
			        		if(!userForm.getForm().isValid()) return;
			            	Ext.MessageBox.confirm('确认', '您确定驳回所选择的记录吗?', function(btn){
								if(btn == 'yes'){
						 			 var ids = [];
						 			 for(var i = 0; i < records.length; i++){
						 				 ids.push(records[i].id);
						 			 }
				 			 		 var values = userForm.getForm().getValues();
				 			 		 values.ids = ids;
						 			 Ext.ux.Ajax.request("budget/expenseDocument/comeBack.do", values, function(action, form){
										 var response =  Ext.util.JSON.decode(action.responseText);
										 if(Ext.isEmpty(response)){
											 Ext.ux.Msg.info("退审成功");
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
	    		 
	    		 userWin = createWindow("退审单据", userForm, 400, 115);
	 			 userWin.show();
			 });
		 }
		 
		 //查看
		 if(!Ext.isEmpty(Ext.getCmp("scanButton"))) {
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
				 
				 Ext.ux.Form.loadForm(Ext.getCmp("billFormPanel"), 
					 "budget/expenseDocument/find.do", 
	 				 {id: records[0].id},
	 				 function(form, action){
	 					 Ext.getCmp("workflowGrid").getStore().load({params: {id:records[0].id, billType: 1}});
	 					 
	 					 Ext.getCmp("billDetailGrid").getStore().load({params: {id:records[0].id}});
	 				 }
	 			 );
				 
				 win.show();
			 });
		 }
		 
		 
		 //关闭
		 if(!Ext.isEmpty(Ext.getCmp("stopButton"))) {
			 Ext.getCmp("stopButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请至少选择一条记录！"); 
	 				 return;
	 			 }
	 			 
				 Ext.MessageBox.confirm('确认', '您确定关闭所选择的记录吗?', function(btn){
					if(btn == 'yes'){
			 			 var ids = [];
			 			 for(var i = 0; i < records.length; i++){
			 				 ids.push(records[i].id);
			 			 }

			 			 Ext.ux.Ajax.request("budget/expenseDocument/close.do", {ids:ids}, function(action, form){
							 var response =  Ext.util.JSON.decode(action.responseText);
							 if(Ext.isEmpty(response)){
								 Ext.ux.Msg.info("关闭成功");
							 }else{
								 Ext.ux.Msg.info(response.data.instruction);
							 }
							 grid.getStore().reload();
						 });
					}
	 			});
			 });
		 }
		 
		 //入账
		 if(!Ext.isEmpty(Ext.getCmp("enterButton"))) {
			 Ext.getCmp("enterButton").on("click",function(){
				 var records = grid.getSelectionModel().getSelections();
	 			 if(records.length == 0){
	 				 Ext.ux.Msg.warning("请至少选择一条记录！"); 
	 				 return;
	 			 }
	 			 
	 			 for(var i = 0; i < records.length; i++){
	 				 var data = records[i].data;
	 				/* if(data.documentState != "关闭") {
	 					 Ext.ux.Msg.warning("您所选择的记录包含未关闭的单据，无法入账！");
	 				 	 return;
	 				 }*/
	 				 
	 				 if(data.entered) {
	 					 Ext.ux.Msg.warning("您所选择的记录包含已入账，请重新选择！");
	 				 	 return;
	 				 }
	 			 }
	 			 
				 Ext.MessageBox.confirm('确认', '您确定入账所选择的记录吗?', function(btn){
					if(btn == 'yes'){
			 			 var ids = [];
			 			 for(var i = 0; i < records.length; i++){
			 				 ids.push(records[i].id);
			 			 }

			 			 Ext.ux.Ajax.request("budget/expenseDocument/enter.do", {ids:ids}, function(action, form){
							 var response =  Ext.util.JSON.decode(action.responseText);
							 if(Ext.isEmpty(response)){
								 Ext.ux.Msg.info("入账成功");
							 }else{
								 Ext.ux.Msg.info(response.data.instruction);
							 }
							 grid.getStore().reload();
						 });
					}
	 			});
			 });
		 }
		 
		 //导出excel
		 if(!Ext.isEmpty(Ext.getCmp("exportButton"))) {
			 Ext.getCmp("exportButton").on("click", function(){
				 alert("待完成");
			 });
		 }
		 
		 //打印预览
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
	 			 
	 			 printReport(records[0].id, "expense_document");
			 });
		 }
		 
	}
});
