/**付款单管理**/
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
   					        {fieldLabel:'<span style="color:red">*</span>申请日期', name: 'writeDate', xtype:'datefield',format:'Y-m-d', value: new Date(), maxValue: new Date()},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writerPeopleCode', value: userCode, hidden: true},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writePeople', value: userName},
   					        createComboTree({
								url: 'budget/zoneFile/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>地区',
								hiddenName: 'regionCode',
								name: 'region'
							}),
   					        createComboTree({
								url: 'budget/projectSubject/queryTree.do',
								fieldLabel: '<span style="color:red">*</span>项目分类',
								hiddenName: 'categoryCode',
								name: 'categoryName'
							}),
							{fieldLabel:'<span style="color:red">*</span>对方账号', name: 'peerBankAccount', allowBlank: true}
						 ]
					}, {
						defaults : {allowBlank: false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
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
				 				 url: 'budget/itemArchives/getCombo.do',
				 				 fields: [{name: 'code'}, {name: 'name'}],
				 				 name: 'projectName',
						    	 hiddenName: 'projectCode',
								 fieldLabel: '<span style="color:red">*</span>项目名称'
				 		    }),
				 		    {fieldLabel:'<span style="color:red">*</span>对方联系人', name: 'peerPeople', allowBlank: true}
						 ]
					}, {
						defaults : {allowBlank: false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.34,
						xtype : 'container',
						items:[
						    {fieldLabel:'<span style="color:red">*</span>附件张数', name: 'attachNum', xtype:'numberfield', value: 0},
						    {fieldLabel:'<span style="color:red">*</span>申请人电话', name: 'writePeopleTel'},
						    {fieldLabel:'<span style="color:red">*</span>单据来源', name: 'documentSource'},
						    {fieldLabel:'<span style="color:red">*</span>对方单位', name: 'peerUnit', allowBlank: true},
						    {fieldLabel:'<span style="color:red">*</span>对方电话', name: 'peerTel', allowBlank: true}
						 ]
					}, {
						labelAlign : 'right',
						layout:'form',
						columnWidth : 1,
						xtype : 'container',
						defaults : {allowBlank: true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						items: [
						    {xtype:'textarea', name:'remark', fieldLabel:'事由', height:30}
						]
					},{
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.33,
						xtype : 'container',
						defaults : {allowBlank: true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						items: [
						    {xtype:'numberfield', name:'borrowMoney', id:'borrowMoney', fieldLabel:'借款金额', readOnly: true, style:'background-color: #F6F6F6; background-image: none;', hidden: true}
						]
					},{
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.33,
						xtype : 'container',
						defaults : {allowBlank: true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						items: [
						    {xtype:'checkbox', name:'chargeOff', id:'chargeOff', fieldLabel:'是否销账', hidden: true},
						    {xtype: 'numberfield',name: 'totalMoney', hidden: true, allowBlank: true}
						]
					},{
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.33,
						xtype : 'container',
						defaults : {allowBlank: true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						items: [
						    {xtype:'numberfield', name:'chargeOffMoney', id:'chargeOffMoney', hidden: true, fieldLabel:'销账金额', readOnly: true}
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
       		}, {
       			xtype:'fieldset',
				title: '核销明细',
				id: 'vieryDetaiPanel',
				hidden: true,
				collapsible: true,
				items:[{
					layout:'form',
					columnWidth: 1,
					frame: false,
					items: new VeriBillDetailGrid()
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
						details += "{costName: '"+data.costName+"'," +
								"costName: '"+data.costName+"'," +
								"digest: '"+data.digest+"'," +
								"cost: "+data.cost+"," +
								"clearingSubject: '"+data.clearingSubject+"'," +
								"clearingSubjectCode: '"+data.clearingSubjectCode+"'," +
								"partySubject: '"+data.partySubject+"'," +
								"partySubjectCode: '"+data.partySubjectCode+"'," +
								"billNo: '"+data.billNo+"'," +
								"cashFlow: '"+data.cashFlow+"'," +
								"cashFlowCode: '"+data.cashFlowCode+"'," +
								"remark: '"+data.remark+"'}";
					}
					
					details += "]"
					Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/paymentDocument/save.do", 
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
			  },{text:'核销',id:'update1Button',
				  iconCls: 'edit1Icon',
				  handler: function(){
				  	  var datas = Ext.getCmp("billDetailGrid").getStore().data;
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
									"digest: '"+data.digest+"'," +
									"cost: "+data.cost+"," +
									"clearingSubject: '"+data.clearingSubject+"'," +
									"clearingSubjectCode: '"+data.clearingSubjectCode+"'," +
									"partySubject: '"+data.partySubject+"'," +
									"partySubjectCode: '"+data.partySubjectCode+"'," +
									"billNo: '"+data.billNo+"'," +
									"cashFlow: '"+data.cashFlow+"'," +
									"cashFlowCode: '"+data.cashFlowCode+"'," +
									"remark: '"+data.remark+"'}";
						}
						
						details += "]";
						
						var veriDatas = Ext.getCmp("veriBillDetailGrid").getStore().data;
					  	var veriDetails = "[";
					  	var totalVeriMoney = 0;
					  	for(var i = 0; i < veriDatas.items.length; i++) {
					  		if((data.borrowMoney-data.returnMoney)  < data.veriMoney ){
								Ext.Msg.alert("错误", "第"+(i+1)+"项没有足够的金额用于核销");
								return;
							}
							
							totalVeriMoney += data.veriMoney;
							
							if(data.veriMoney == 0){
								continue;
							}
							
							if(i > 0) {
								veriDetails += ","
							}
							var data = veriDatas.items[i].data
							var id = veriDatas.items[i].id;
							if(id.indexOf("ext-record-") > 0){
								id = "";
							}
							
							veriDetails += "{borrowId: '" +data.borrowId+"',"+
									"id: '"+id+"'," +
									"costCode: '"+data.costCode+"'," +
									"costName: '"+data.costName+"'," +
									"digest: '"+data.digest+"'," +
									"borrowMoney: "+data.borrowMoney+"," +
									"returnMoney: "+data.returnMoney+"," +
									"veriMoney: "+data.veriMoney+"," +
									"borrowDocumentNum: '"+data.borrowDocumentNum+"'," +
									"borrowDate: '"+data.borrowDate+"'," +
									"borrowPeopleCode: '"+data.borrowPeopleCode+"'," +
									"borrowPeopleName: '"+data.borrowPeopleName+"'," +
									"remark: '"+data.remark+"'}";
						}
						
						veriDetails += "]"
						
						var borrowMoney = form.findField("borrowMoney").getValue();
						if(totalVeriMoney > borrowMoney){
							Ext.Msg.alert("错误", "总核销金额大于借款金额");
							return;
						}
						
						Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "budget/paymentDocument/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.getCmp("addWindow").close();
		            			Ext.getCmp("mainGrid").getStore().load();
		            		},  "", {detailStr: details, veriDetailStr: veriDetails}	
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
	 	
	 	if(!Ext.isEmpty(this.getForm().findField("chargeOff"))){
	 		 form = this.getForm();
	 		 form.findField("chargeOff").on({
	 			 check: function(checkBox, checked){
	 			 	var chargeOffMoney = form.findField("chargeOffMoney");
	 			 	if(checked) {
	 			 		var totalMoney = form.findField("totalMoney").getValue();
		 			 	var borrowMoney = form.findField("borrowMoney").getValue();
		 			 	var money = totalMoney > borrowMoney ? borrowMoney: totalMoney;
		 			 	chargeOffMoney.setReadOnly(false);
		 			 	chargeOffMoney.setValue(money);
		 			 	chargeOffMoney.setMaxValue(money);
	 			 	}else{
	 			 		chargeOffMoney.setReadOnly(true);
		 			 	chargeOffMoney.setValue(0);
	 			 	}
	 			 	
	 			 }
	 		 });
	 	 }
	 }
})