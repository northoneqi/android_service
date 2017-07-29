Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
carRecordEditWindow=Ext.extend(Ext.Window,{
	title:'车辆档案表',
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
					             items : [{xtype:'hidden',id:'carRecordId',name:'carRecordId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield',value:'Car_'+new Date().getFullYear()+''+Math.round(Math.random()*100), msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'车辆编号',allowBlank:false,name:'carCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'车牌号',allowBlank:false,name:'carMark',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'车牌颜色',allowBlank:true,name:'carColor',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'最大承载量',allowBlank:false,name:'loadNumber',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'吨位/座位',allowBlank:true,name:'tonSeat',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'发动机号',allowBlank:true,name:'motorNumber',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'总重量',allowBlank:true,name:'allWeight',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'核定载人数',allowBlank:true,name:'checkPeople',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'核定载质量',allowBlank:false,name:'checkTon',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'整备质量',allowBlank:true,name:'allTon',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'品牌型号',allowBlank:false,name:'carVersion',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'使用性质',allowBlank:true,name:'useState',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'注册日期',allowBlank:true,name:'enrollDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'车辆原值',allowBlank:true,name:'carValue',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'强制报废日期',allowBlank:false,name:'scrapDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'保险日期',allowBlank:true,name:'safeDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'二维日期',allowBlank:true,name:'twoDate',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'等级鉴定日期',allowBlank:false,name:'checkupDate',xtype:'datefield',format:'Y-m-d'}]
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
		 carRecordEditWindow.superclass.initComponent.call(this);
	}
});
