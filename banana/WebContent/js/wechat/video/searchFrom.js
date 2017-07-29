searchFrom = Ext.extend(Ext.form.FormPanel,{
	id:'searchFrom',
	border:false,
	margins:'2 2 2 2 ',
	height:40,
	layout:'form',
	xtype: 'form',
	frame: false,
	initComponent: function() {
		 Ext.applyIf(this,{
			items:[{
				collapseFirst:false, 
				margins:'0 0 0 0 ',
				layout:'column',
				items:[
							{
								columnWidth :0.20,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'活动标题',id:'sn',name:'title'}]
	           				},
	           				{
								columnWidth :0.20,
								layout:'form',
								border:false,
								defaults : {anchor:'0', allowBlank : true,xtype : 'datefield',format:'Y-m-d', msgTarget : 'side',labelWidth:60},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'活动时间',name:'startTime'}]
	           				},
	           				{
								columnWidth :0.20,
								layout:'form',
								border:false,
								defaults : {anchor:'0', allowBlank : true,xtype : 'datefield',format:'Y-m-d', msgTarget : 'side',labelWidth:10},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'至',name:'endTime'}]
	           				},
	           				{
								columnWidth :0.20,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[state]
	           				},
	           				{
								columnWidth :0.08,
								layout:'form',
								border:false,
								buttonAlign:'center',
		           				items:[{xtype:'button', width:80,align:"right",text:'查询',iconCls:'previewIcon',id:'searchButton'}]
	           				},{
	           					columnWidth :0.08,
								layout:'form',
								border:false,
								buttonAlign:'center',
		           				items:[{xtype:'button', width:80,align:"right",text:'重置',id:'resetButton',iconCls:''}]
	           				}
				]
			}]
		 });
		 searchFrom.superclass.initComponent.call(this);
	}
});