searchFrom = Ext.extend(Ext.form.FormPanel,{
	id:'searchFrom',
	border:false,
	margins:'2 2 2 2 ',
	height:145,
	layout:'form',
	xtype: 'form',
	frame: false,
	initComponent: function() {
		 Ext.applyIf(this,{
			items:[{
				xtype:'fieldset',
				collapseFirst:false, 
				margins:'2 2 0 2 ',
				layout:'column',
				buttonAlign:'center',
				items:[
							{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'订单编号',id:'sn'},{fieldLabel:'收货人',id:'customer'}]
	           				},
	           				{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[
		           						{fieldLabel:'订单金额(<)'},
		           						{fieldLabel:'订单数量(<)'}
		           				]
	           				},
	           				{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'订单金额(>)'},{fieldLabel:'订单数量(>)'}]
	           				},
	           				{
								columnWidth :0.99,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'备注'}]
	           				}
				],
				buttonAlign:'center',
				buttons:[{text:'查询',id:'queryButton'},{text:'重置',id:'resetButton'}]
			}]
		 });
		 searchFrom.superclass.initComponent.call(this);
	}
});