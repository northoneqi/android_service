Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
contractProductDetailEditWindow=Ext.extend(Ext.Window,{
	title:'合同商品明细',
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
		spmc = new procitycountyll(
				'<span style=\'color:red\'>*</span>商品名称,spmcId,SKUName,false,,' + ctx + '/supplyChain/contractProductDetail/getWorkerInfo.do');
		spmc.on("select", function() {
			var SKUName = this.getStore()
					.getAt(this.selectedIndex).data.SKUName;
			var SKU = this.getStore()
					.getAt(this.selectedIndex).data.SKU;
			var sellPrice = this.getStore().getAt(
					this.selectedIndex).data.sellPrice;
			Ext.getCmp("spmcId").setValue(SKUName);
			Ext.getCmp("sellPriceId").setValue(sellPrice);
			Ext.getCmp("SKUId").setValue(SKU);
		});
	 Ext.applyIf(this,{ 
		 layout:'form',
		 border:false,
		 items:[{
			 	xtype:'form',
			 	layout:'form', 
			    id:'saveFormPanelCh',
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
					             items : [{xtype:'hidden',id:'contractProductDetailId',name:'contractProductDetailId'},
					                      
					                      {
					                    		columnWidth : .48,
					                    		layout : 'form',
					                    		border : false,
					                    		defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
					                    		labelAlign : 'right',
					                    		items :[spmc]
					                    	}
					                      ,{
	columnWidth : .48,
	layout : 'form',
	border : false,
	defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
	labelAlign : 'right',
	items :[{

		fieldLabel : '<span style="color:red">*</span>商品编码',
		name : 'productCode',
		id : 'SKUId',
		xtype : 'textfield',
		 style:'background:#E6E5E5;',
		allowBlank : false,
		readOnly:true,
		//anchor : '89%'

	}]
}

,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'规格',allowBlank:true,name:'specifications',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'计量单位',allowBlank:true,name:'unit',xtype:'textfield'}]
										}
,
{
	columnWidth : .48,
	layout : 'form',
	border : false,
	defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
	labelAlign : 'right',
	items :[{

		fieldLabel : '<span style="color:red">*</span>单价',
		name : 'buyPrice',
		id : 'sellPriceId',
		xtype : 'textfield',
		 style:'background:#E6E5E5;',
		allowBlank : false,
		readOnly:true,
		//anchor : '89%'

	}]
}

,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'数量',allowBlank:true,name:'quantity',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'金额',allowBlank:true,name:'subtotal',xtype:'textfield'}]
										}
,{
											columnWidth : .33,
											layout:'form',
											columnWidth : 1,
											xtype : 'container',
											defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : '95%'},
											items: [
											    {xtype:'textarea', name:'remark', fieldLabel:'备注', height:30}
											]
											}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'providerContractIds',allowBlank:true,name:'providerContractIds',xtype:'hidden'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButtonCh',iconCls:'icon-disk'},{text:'取消',id:'cencelButtonCh',iconCls:'icon-cencel'}]
					}]
	 });
		 contractProductDetailEditWindow.superclass.initComponent.call(this);
	}
});
