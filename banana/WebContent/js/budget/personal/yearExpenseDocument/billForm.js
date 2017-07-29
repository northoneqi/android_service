/**年度支出预算单管理**/
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
   					        {fieldLabel:'<span style="color:red">*</span>编撰人', name: 'writerPeopleCode', value: userCode, hidden: true},
   					        {fieldLabel:'<span style="color:red">*</span>编撰人', name: 'writePeople', value: userName},
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
							})
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
								fieldLabel: '<span style="color:red">*</span>编撰部门',
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
				 		    })
						 ]
					}, {
						defaults : {allowBlank: false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.34,
						xtype : 'container',
						items:[
						    Ext.ux.form.ComboBox({
						    	 id: 'documentTypeCombo',
	 							 name: 'documentType',
	 							 fieldLabel: '<span style="color:red">*</span>单据类型',
	 				        	 fields: [{name: 'name'}, {name: 'name'}],
	 				        	 url: 'sys/dict/getDictByCode.do?code=1005'
	 				        }),
						    {fieldLabel:'<span style="color:red">*</span>编撰人电话', name: 'writePeopleTel'},
						    {fieldLabel:'<span style="color:red">*</span>预算年度', name: 'budgetYear', xtype: 'numberfield', value: new Date().getFullYear()}
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
						details += "{budgetTargetCode: '"+data.budgetTargetCode+"'," +
								"budgetTargetName: '"+data.budgetTargetName+"'," +
								"budgetMoney: "+data.budgetMoney+"," +
								"addMoney: "+(Ext.isEmpty(data.addMoney) ? 0:data.addMoney)+","+
								"lastMoney: "+(Ext.isEmpty(data.lastMoney) ? 0:data.lastMoney)+","+
								"grandTotalMoney: "+(Ext.isEmpty(data.grandTotalMoney) ? 0: data.grandTotalMoney)+","+
								"remark: '"+data.remark+"'}";
					}
					
					details += "]"
					Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/yearExpenseDocument/save.do", 
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
									"budgetTargetCode: '"+data.budgetTargetCode+"'," +
									"budgetTargetName: '"+data.budgetTargetName+"'," +
									"budgetMoney: "+data.budgetMoney+"," +
									"addMoney: "+(Ext.isEmpty(data.addMoney) ? 0:data.addMoney)+","+
									"lastMoney: "+(Ext.isEmpty(data.lastMoney) ? 0:data.lastMoney)+","+
									"grandTotalMoney: "+(Ext.isEmpty(data.grandTotalMoney) ? 0: data.grandTotalMoney)+","+
									"remark: '"+data.remark+"'}";
						}
						
						details += "]"
						
						Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/yearExpenseDocument/update.do", 
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
	 	
	 	if(!Ext.isEmpty(this.getForm().findField("projectCombo"))){
			form = this.getForm();
			//根据项目和单据类型初始化单据明细
			//如果是预算单需要根据项目性质到数据库中去提取相关的预算指标
			form.findField("projectCombo").on({
				select: function(combo, record){
					if(form.findField("documentTypeCombo").getValue() == "预算单") {
						Ext.ux.Ajax.request("budget/budgetTargetController/getCombo.do", {budgetNatureCode: record.json.natureCode}, function(action, form){
							var targets =  Ext.util.JSON.decode(action.responseText);
							
							billDetailStore.removeAll(true);
							
							for(var i = 0; i < targets.length; i++){
								var target = targets[i];
								var targetCode = target.targetCode
								var targetName = target.targetName;
								
								var e = new BillDetail({
									budgetTargetCode: targetCode,
									budgetTargetName: targetName,
									budgetMoney: 0,
									addMoney: 0,
									lastMoney: 0,
									grandTotalMoney: 0,
									remark: ''
								});
								
					            billDetailEditor.stopEditing();
					            billDetailStore.insert(billDetailStore.getCount(), e);
					            Ext.getCmp("billDetailGrid").getView().refresh();
					            Ext.getCmp("billDetailGrid").getSelectionModel().selectRow(billDetailStore.getCount());
							}
						});
					}else if(form.findField("documentTypeCombo").getValue() == "追加预算") {
						Ext.getCmp("billDetailGrid").getStore().load({params: {projectCode:record.json.code}});
					}
				}	
			});
		 }
	 	
	 	 if(!Ext.isEmpty(this.getForm().findField("documentTypeCombo"))){
			form = this.getForm();
			//根据项目和单据类型初始化单据明细
			//如果是预算单需要根据项目性质到数据库中去提取相关的预算指标
			form.findField("documentTypeCombo").on({
				select: function(combo, record){
					var projectCode = form.findField("projectCombo").getValue();
					var index = form.findField("projectCombo").getStore().find("code", projectCode);
					var record = form.findField("projectCombo").getStore().getAt(index);
					if(Ext.isEmpty(record)) return;
					
					if(combo.getValue() == "预算单") {
						Ext.ux.Ajax.request("budget/budgetTargetController/getCombo.do", {budgetNatureCode: record.json.natureCode}, function(action, form){
							var targets =  Ext.util.JSON.decode(action.responseText);
							
							billDetailStore.removeAll(true);
							
							for(var i = 0; i < targets.length; i++){
								var target = targets[i];
								var targetCode = target.targetCode
								var targetName = target.targetName;
								
								var e = new BillDetail({
									budgetTargetCode: targetCode,
									budgetTargetName: targetName,
									budgetMoney: 0,
									addMoney: 0,
									lastMoney: 0,
									grandTotalMoney: 0,
									remark: ''
								});
								
					            billDetailEditor.stopEditing();
					            billDetailStore.insert(billDetailStore.getCount(), e);
					            Ext.getCmp("billDetailGrid").getView().refresh();
					            Ext.getCmp("billDetailGrid").getSelectionModel().selectRow(billDetailStore.getCount());
							}
						});
					}else if(combo.getValue() == "追加预算"){
					
						Ext.getCmp("billDetailGrid").getStore().load({params: {projectCode:record.json.code}});
					}
				}
			})
		 }
	 	 
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