ProductWindow = Ext.extend(Ext.Window,{
	id: 'productWindow',
	title:'选择商品',
	margins:'1 1 1 1 ',
	modal:true,
	layout:'fit',
	maximized: true,
	frame:true,
	initComponent:function(){
		var productGrid = new ProductGrid();
		Ext.applyIf(this,{
			layout:'form',
			border:false,
			margins:'1 1 1 1 ',
			items:{
				layout:'border',
				items:[{
					region:'north',
					margins:'1 1 1 1',
					frame:true,
					height:110,
					layout:'fit',
					items: new ProductSearchFrom()
				},{
					region:'center',
					margins:'1 1 1 1',
					frame:true,
					layout:'fit',
					tbar:  [{
						text:'确定',
						iconCls:'previewIcon',
						handler: function(){
							var productGrid = Ext.getCmp("productGrid");
							var records = productGrid.getSelectionModel().getSelections();
				 			if(records.length == 0){
				 				 Ext.ux.Msg.warning("请选择一条记录！"); 
				 				 return;
				 			}else if(records.length > 1){
				 				Ext.ux.Msg.warning("最多只能选择一条记录，请重新选择！");
				 				return;
				 			}
				 			console.log(records[0]);
				 			
				 			Ext.getCmp("billFormPanel").getForm().findField("productId").setValue(records[0].data.ProSKUId); 
				 			Ext.getCmp("billFormPanel").getForm().findField("productSKU").setValue(records[0].data.SKU);
				 			Ext.getCmp("billFormPanel").getForm().findField("productName").setValue(records[0].data.productName);
				 			Ext.getCmp("billFormPanel").getForm().findField("productOldPrice").setValue(records[0].data.SellPrice);
				 			Ext.getCmp("billFormPanel").getForm().findField("productPrice").setValue(records[0].data.SellPrice);
				 			Ext.getCmp("productWindow").close();
						}}, {xtype:'tbseparator'},{
						text:'取消',
						iconCls:'previewIcon',
						handler: function(){
							Ext.getCmp("productWindow").close();
						}}
					],
				    items: productGrid,
					bbar: new Ext.PagingToolbar({
    					pageSize: pageSize,
    					store: productGrid.getStore(),
    					displayInfo: true
  		 			})
				}],
				margins:'1 1 1 1 ',
				id:'productFormPanel'
			}
		});
		ProductWindow.superclass.initComponent.call(this);
	}
});