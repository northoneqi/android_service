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
				margins:'1 1 1 1 ',
				layout:'column',
				items:[
							{
								columnWidth :0.23,
								layout:'form',
								border:false,
								defaults : {anchor:'-8', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'视频标题',id:'title',name:'title'}]
	           				},
	           				{
								columnWidth :0.22,
								layout:'form',
								border:false,
								defaults : {anchor:'-8', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'作者',id:'author',name:'author'}]
	           				},
	           				{
								columnWidth :0.20,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'numberfield', msgTarget : 'side',labelWidth:60},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'点赞数',name:'dzstar'}]
	           				},
	           				{
								columnWidth :0.10,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'numberfield', msgTarget : 'side'},
		           				labelAlign : 'left',
		           				labelWidth:12,
		           				items:[{fieldLabel:'至',name:'dzend'}]
	           				},{
	           					columnWidth :0.12,
								layout:'form',
								border:false,
								buttonAlign:'center',
		           				items:[{xtype:'button', width:80,align:"right",text:'查询',id:'searchDetailButton',iconCls:'previewIcon'}]
	           				},{
	           					columnWidth :0.12,
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