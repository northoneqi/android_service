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
						defaults : {allowBlank: true, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth: 0.33,
						xtype: 'container',
						items:[
					        {name: 'id', hidden: true},
   					        {fieldLabel:'<span style="color:red">*</span>编撰日期', name: 'writeDate', xtype:'datefield',format:'Y-m-d', value: new Date(), maxValue: new Date()},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writerPeopleCode', value: userCode, hidden: true},
   					        {fieldLabel:'<span style="color:red">*</span>申请人', name: 'writePeople', value: userName},
	   					    {fieldLabel:'<span style="color:red">*</span>地区', name: 'region'},
	   					    {fieldLabel:'<span style="color:red">*</span>项目分类', name: 'categoryName'},
	   					    {fieldLabel:'<span style="color:red">*</span>对方账号', name: 'peerBankAccount'}
						 ]
					}, {
						defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.33,
						xtype : 'container',
						items:[
						    {fieldLabel:'<span style="color:red">*</span>单据编号', name: 'documentNum'},
						    {fieldLabel:'<span style="color:red">*</span>所属办事处', name: 'writeDepartmentName'},
						    {fieldLabel:'<span style="color:red">*</span>部门名称', name: 'budgetDepartmentName'},
						    {fieldLabel:'<span style="color:red">*</span>项目名称', name: 'projectName'},
						    {fieldLabel:'<span style="color:red">*</span>对方联系人', name: 'peerPeople'}
						 ]
					}, {
						defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth : 0.34,
						xtype : 'container',
						items:[
						    {fieldLabel:'<span style="color:red">*</span>附件张数', name: 'attachNum', xtype:'numberfield', value: 0},
						    {fieldLabel:'<span style="color:red">*</span>编撰人电话', name: 'writePeopleTel'},
						    {fieldLabel:'&nbsp;', xtype: 'displayfield', labelSeparator: ''},
						    {fieldLabel:'<span style="color:red">*</span>对方单位', name: 'peerUnit'},
						    {fieldLabel:'<span style="color:red">*</span>对方电话', name: 'peerTel'}
						 ]
					}, {
						labelAlign : 'right',
						layout:'form',
						columnWidth : 1,
						xtype : 'container',
						defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
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
       		}, {
       			xtype:'fieldset',
				title: '审核明细',
				collapsible: true,
				items:[{
					layout:'form',
					columnWidth: 1,
					frame: false,
					items: new WorkFlowGrid()
				}]
       		}],
       		buttons:[{text:'返回',id:'reset1Button', 
				  iconCls: 'returnIcon',
				  handler: function(){
					  Ext.getCmp("addWindow").close();
				  }
			  }]
		});
		
	 	BillFormPanel.superclass.initComponent.call(this);
	 }
})