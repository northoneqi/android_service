Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
providerLinkManEditWindow=Ext.extend(Ext.Window,{
	title:'联系人',
	width:600,
	height:432, 
	layout:'fit',
	modal:true,
	autoScroll: true,  
	iconCls:'icon-window',
	constrainHeader:true,//是否能移出界面
	resizable:true,//窗口是否能改变大小 
	buttonAlign:'center',
	initComponent: function() { 
	 Ext.applyIf(this,{ 
		 layout:'form',
		 border:false,
		 items:[{
			 	xtype:'form',
			 	layout:'form', 
			    id:'saveFormPanelCh',
			    autoScroll:true,
			    border:false,
				buttonAlign:'center',
				items:[{
					layout:'form',
					frame:true,
					border:false,
					buttonAlign:'center',
					items:[{
							xtype:'fieldset',
							collapseFirst:false, 
							header : true,
							autoScroll:true,
							animCollapse:true, 
							frame:true,
							width:550,
							height:340,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'providerLinkManId',name:'providerLinkManId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'<span style="color:red">*</span>联系人',allowBlank:false,name:'linkman',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'职务',allowBlank:true,name:'position',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'<span style="color:red">*</span>手机',allowBlank:false,name:'mobile',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'<span style="color:red">*</span>联系电话',allowBlank:false,name:'telephone',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'传真号码',allowBlank:true,name:'faxNumber',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'QQ',allowBlank:true,name:'qq',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'邮箱',allowBlank:true,name:'email',xtype:'textfield'}]
										}
,{
										columnWidth : .33,
										layout:'form',
										columnWidth : 1,
										xtype : 'container',
										defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : '95%'},
										items: [
										    {xtype:'textarea', name:'remarks', fieldLabel:'备注', height:30}
										]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'providerDocIds',allowBlank:true,name:'providerDocIds',xtype:'hidden'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButtonCh',iconCls:'icon-disk'},{text:'取消',id:'cencelButtonCh',iconCls:'icon-cencel'}]
					}]
	 });
		 providerLinkManEditWindow.superclass.initComponent.call(this);
	}
});
