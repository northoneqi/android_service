Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
storeroomRecordEditWindow=Ext.extend(Ext.Window,{
	title:'库房档案表',
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
							height:340,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'storeroomRecordId',name:'storeroomRecordId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库房编号',allowBlank:false,name:'storeroomCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库房名称',allowBlank:false,name:'storeroomName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库房面积',allowBlank:true,name:'storeroomArea',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库房长宽高',allowBlank:true,name:'storeroomlongWidth',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库区编号',allowBlank:false,name:'storeroomAreaCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库区名称',allowBlank:false,name:'storeroomAreaName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'库区长宽高',allowBlank:true,name:'storeroomArealongWidth',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'货位编号',allowBlank:false,name:'goodsPlaceCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'货位名称',allowBlank:false,name:'goodsPlaceName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'货位长宽高',allowBlank:true,name:'goodsPlacelongWidth',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'备注',allowBlank:true,name:'remarks',xtype:'textfield'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButton',iconCls:'icon-disk'},{text:'取消',id:'cencelButton',iconCls:'icon-cencel'}]
					}]
	 });
		 storeroomRecordEditWindow.superclass.initComponent.call(this);
	}
});
