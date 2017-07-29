Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
carDriverEditWindow=Ext.extend(Ext.Window,{
	title:'驾驶员档案表',
	width:600,
	height:332, 
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
			    id:'saveFormPanel',
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
							height:240,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'carDriverId',name:'carDriverId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield',value:'Dri_'+new Date().getTime()+''+Math.round(Math.random()*100), msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'驾驶员编号',allowBlank:false,name:'driverCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'姓名',allowBlank:false,name:'driverName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '性别',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['男', '男'],['女','女']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'sex'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'驾驶证号',allowBlank:false,name:'driverProve',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'驾驶证准驾车型',allowBlank:false,name:'driverProveType',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'资格证',allowBlank:false,name:'titleCard',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'准驾车型',allowBlank:false,name:'driverType',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'入职日期',allowBlank:true,name:'entryDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '是否在职',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['是', '是'],['否','否']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'ifEntry'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'离退休日期',allowBlank:true,name:'retireDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .96,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'备注',allowBlank:true,name:'remarks',xtype:'textarea'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButton',iconCls:'icon-disk'},{text:'取消',id:'cencelButton',iconCls:'icon-cencel'}]
					}]
	 });
		 carDriverEditWindow.superclass.initComponent.call(this);
	}
});
