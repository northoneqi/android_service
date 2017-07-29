AddWindow = Ext.extend(Ext.Window,{
	id: 'addWindow',
	title:'项目预算单登记',
	margins:'1 1 1 1 ',
	modal:true,
	layout:'fit',
	maximized: true,
	frame:true,
	initComponent:function(){
		var form = new BillFormPanel();
		Ext.applyIf(this,{
			layout:'form',
			border:false,
			margins:'1 1 1 1 ',
			items:{
				layout:'fit',
				items: form,
				margins:'1 1 1 1 ',
				id:'mainFormPanel'
			}
		});
		AddWindow.superclass.initComponent.call(this);
	}
});