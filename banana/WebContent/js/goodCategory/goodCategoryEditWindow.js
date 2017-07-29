Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
goodCategoryEditWindow=Ext.extend(Ext.Window,{
	title:'商品分类',
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
					             items : [{xtype:'hidden',id:'goodCategoryId',name:'goodCategoryId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品分类代码',allowBlank:false,name:'goodCategoryCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品分类名称',allowBlank:false,name:'goodCategoryName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'父级',allowBlank:false,name:'goodCategoryParent',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'备注',allowBlank:true,name:'goodCategoryRemark',xtype:'textfield'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButton',iconCls:'icon-disk'},{text:'取消',id:'cencelButton',iconCls:'icon-cencel'}]
					}]
	 });
		 goodCategoryEditWindow.superclass.initComponent.call(this);
	}
});
