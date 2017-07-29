DetailWindow = Ext.extend(Ext.Window,{
	id: 'detailWindow',
	title:'项目预算明细统计',
	margins:'1 1 1 1 ',
	modal:true,
	layout:'fit',
	maximized: true,
	frame:true,
	initComponent:function(){
		Ext.applyIf(this,{
			layout:'form',
			border:false,
			margins:'1 1 1 1 ',
			items:{
				xtype: 'tabpanel',
				margins:'1 1 1 1 ',
				activeTab: 0,
				items: [{
					title : "报销明细",
					layout:'border',
					autoScroll: true,
					items: [{
						autoScroll: true,
						region: 'west',
						width: '60%',
						contentEl: 'remburseContainer'
					},{
						layout: 'fit',
						region: 'center',
						items: new ReimburseBillDetailGrid()
					}]
				},{
					title : "借用明细",
					layout:'border',
					autoScroll: true,
					items: [{
						autoScroll: true,
						region: 'west',
						width: '60%',
						contentEl: 'borrowContainer'
					},{
						layout: 'fit',
						region: 'center',
						items: new BorrowBillDetailGrid()
					}]
				},{
					title : "付款明细",
					layout:'border',
					autoScroll: true,
					items: [{
						autoScroll: true,
						region: 'west',
						width: '60%',
						contentEl: 'paymentContainer'
					},{
						layout: 'fit',
						region: 'center',
						items: new PaymentBillDetailGrid()
					}]
				}]
			}
		});
		DetailWindow.superclass.initComponent.call(this);
	}
});