Ext.BLANK_IMAGE_URL=ctx+"/ext-3.4.0/resources/images/default/s.gif"; 
orderEditWindow=Ext.extend(Ext.Window,{
	title:'住宅用房周转情况登记',
	width:800,
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
							animCollapse:true, 
							frame:true,
							width:750,
							height:340,
							layout:'form', 
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{
					                         columnWidth : .33,
					                         layout : 'form',
					                         border : false,
					                         defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
					                         labelAlign : 'right',
					                         items :[{xtype:'hidden',id:'id',name:'id'},
					                        	     {fieldLabel:'拆迁日期',name:'customer',format:'Y-m-d'},
					                        	     {fieldLabel:'批准人',name:'pzr'},
					                        	     {fieldLabel:'弃产日期',name:'qcrq',xtype:'datefield',format:'Y-m-d'}
					                         ]
					                     },{
					                         columnWidth : .33, // 该列占用的宽度，标识为50％
					                         layout : 'form',
					                         border : false,
					                         defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
					                         labelAlign : 'right',
					                         items :[{fieldLabel:'拆迁经办人',name:'cqjbr'},
					                        	     {fieldLabel:'批准日期',name:'pzrq',xtype:'datefield',format:'Y-m-d'},
					                        	     {fieldLabel:'弃产面积(㎡)',name:'qcmj',xtype:'numberfield'}
					                        	 ]
					                     },{
					                    	 columnWidth : .33, // 该列占用的宽度，标识为50％
					                         layout : 'form',
					                         border : false,
					                         defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
					                         labelAlign : 'right',
					                         items:[{fieldLabel:'拆迁单位',name:'cqdw'},
					                        	    {fieldLabel:'经办人',name:'jbr'},
					                        	    {fieldLabel:'原值金额(元)',name:'yzje',xtype:'numberfield'}
					                         ]
					                     }]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButton',iconCls:'icon-disk'},{text:'取消',id:'cencelButton',iconCls:'icon-cencel'}]
					}]
	 });
		 orderEditWindow.superclass.initComponent.call(this);
	}
});