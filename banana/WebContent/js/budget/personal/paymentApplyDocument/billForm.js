/**项目预算单管理**/
BillFormPanel = Ext.extend(Ext.form.FormPanel,{
	 id: 'billFormPanel',
	 xtype: 'form', 
	 autoScroll:true,
	 frame: true,
	 buttonAlign:'center',
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			items:[{
				xtype:'fieldset',
				title: '单据信息',
				collapsible: true,
				items:[{
					layout:'column',
					columnWidth : 1,
					frame: false,
					items: [{
						defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth: 0.33,
						xtype: 'container',
						items:[
					        {name: 'id', hidden: true, allowBlank: true},
   					        {fieldLabel:'<span style="color:red">*</span>编撰日期', name: 'writeDate', xtype:'datefield',format:'Y-m-d', value: new Date(), maxValue: new Date()},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writerPeopleCode', value: userCode, hidden: true},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writePeople', value: userName},
   					        createComboTree({
								url: 'budget/zoneFile/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>地区',
								hiddenName: 'regionCode',
								name: 'region'
							}),
   					        createComboTree({
   					        	id: 'categoryCombo',
								url: 'budget/projectSubject/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>项目分类',
								hiddenName: 'categoryCode',
								name: 'categoryName'
							}),
							{fieldLabel:'<span style="color:red">*</span>对方账号', name: 'peerBankAccount'}
						 ]
					}, {
						defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.33,
						xtype : 'container',
						items:[
						    {fieldLabel:'<span style="color:red">*</span>单据编号', name: 'documentNum', emptyText: '系统自动产生', style:'background-color: #F6F6F6; background-image: none;',readOnly: true, allowBlank: true},
						    createComboTree({
								url: 'sys/department/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>所属办事处',
								hiddenName: 'writerDepartmentCode',
								name: 'writeDepartmentName'
							}),
							createComboTree({
								url: 'sys/department/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>部门名称',
								hiddenName: 'budgetDepartmentCode',
								name: 'budgetDepartmentName'
							}),
							Ext.ux.form.ComboBox({
								 id: 'projectCombo',
				 				 url: 'budget/itemArchives/getCombo.do',
				 				 fields: [{name: 'code'}, {name: 'name'}],
				 				 name: 'projectName',
						    	 hiddenName: 'projectCode',
								 fieldLabel: '<span style="color:red">*</span>项目名称'
				 		    }),
				 		    {fieldLabel:'<span style="color:red">*</span>对方联系人', name: 'peerPeople'}
						 ]
					}, {
						defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.34,
						xtype : 'container',
						items:[
						    {fieldLabel:'<span style="color:red">*</span>附件张数', name: 'attachNum', xtype:'numberfield', value: 0},
						    {fieldLabel:'<span style="color:red">*</span>申请人电话', name: 'writePeopleTel'},
						    {fieldLabel:'&nbsp;', xtype: 'displayfield', labelSeparator: ''},
						    {fieldLabel:'<span style="color:red">*</span>对方单位', name: 'peerUnit'},
						    {fieldLabel:'<span style="color:red">*</span>对方电话', name: 'peerTel'}
						 ]
					}, {
						labelAlign : 'right',
						layout:'form',
						columnWidth : 1,
						xtype : 'container',
						defaults : {allowBlank: true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						items: [
						    {xtype:'textarea', name:'remark', fieldLabel:'备注', height:30}
						]
					}]
       			}]
       		}, {
       			xtype:'fieldset',
				title: '单据明细',
				collapsible: true,
				items:[{
					layout:'form',
					columnWidth: 1,
					frame: false,
					items: new BillDetailGrid()
				}]
       		}],
       		buttons:[{
		    	text:'保存',
		    	id:'add1Button',
		    	iconCls: 'saveIcon',
				handler: function(){
					var datas = Ext.getCmp("billDetailGrid").getStore().data;
					var details = "[";
					for(var i = 0; i < datas.items.length; i++) {
						if(i > 0) {
							details += ","
						}
						var data = datas.items[i].data
						details += "{costCode: '"+data.costCode+"'," +
								"costName: '"+data.costName+"'," +
								"cost: "+data.cost+"," +
								"digest: '"+data.digest+"'," +
								"remark: '"+data.remark+"'}";
					}
					
					details += "]"
					Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/paymentApplyDocument/save.do", 
	            		function(form, action){
	            			Ext.Msg.alert('消息', action.result.data.instruction);
	            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
	        					if(btn == 'yes'){
	        						Ext.getCmp("billFormPanel").getForm().reset();
	        						Ext.getCmp("billDetailGrid").getStore().removeAll();
	        					}else{
	        						Ext.getCmp("addWindow").close();
	        					}
	            			})
	            			
	            			Ext.getCmp("mainGrid").getStore().load();
	            		}, "", {detailStr: details}
	            	);
				  }
			  },{text:'更新',id:'update1Button',
				  iconCls: 'edit1Icon',
				  handler: function(){
				  	  var datas = Ext.getCmp("billDetailGrid").getStore().data;
				  	  console.log(datas);
					  var details = "[";
					  for(var i = 0; i < datas.items.length; i++) {
							if(i > 0) {
								details += ","
							}
							var data = datas.items[i].data
							var id = datas.items[i].id;
							if(id.indexOf("ext-record-") > 0){
								id = "";
							}
							details += "{id: '" +id+"',"+
									"costCode: '"+data.costCode+"'," +
									"costName: '"+data.costName+"'," +
									"cost: "+data.cost+"," +
									"digest: '"+data.digest+"'," +
									"remark: '"+data.remark+"'}";
						}
						
						details += "]"
						
						Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/paymentApplyDocument/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.getCmp("addWindow").close();
		            			Ext.getCmp("mainGrid").getStore().load();
		            		},  "", {detailStr: details}	
	            		);
				  }
			  },{text:'返回',id:'reset1Button', 
				  iconCls: 'returnIcon',
				  handler: function(){
					  Ext.getCmp("addWindow").close();
				  }
			  }]
			
		});
		
	 	BillFormPanel.superclass.initComponent.call(this);
	 	
	 	if(!Ext.isEmpty(this.getForm().findField("categoryCombo"))){
	 		 form = this.getForm();
	 		 form.findField("categoryCombo").tree.on({
	 			 click: function(node){
	 			 	var projectCombo = form.findField("projectCombo");
	 			 	projectCombo.clearValue();
	 			 	projectCombo.getStore().baseParams = {categoryCode: node.attributes.code};
	 			 	projectCombo.getStore().removeAll(true);
	 			 	projectCombo.getStore().load();
	 			 }
	 		 });
	 	 }
	 }
})