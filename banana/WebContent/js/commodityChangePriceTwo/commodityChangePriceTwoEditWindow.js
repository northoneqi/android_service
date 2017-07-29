Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
commodityChangePriceTwoEditWindow=Ext.extend(Ext.Window,{
	title:'调价单从表',
	width:650,
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
							width:600,
							height:340,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'commodityChangePriceTwoId',name:'commodityChangePriceTwoId'},
					                      {xtype:'hidden',id:'goodsClassifyId',name:'goodsClassifyId'},
					             {
										columnWidth : .49,
										layout : 'form',
										border : false,
										defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
										labelAlign : 'right',
										items :[{fieldLabel:'商品类别',allowBlank:false,name:'goodsClassify',id:'categoriesGoodsId',xtype:'textfield',
											readOnly:true,cls:'combox-img',value:'点击选择商品类别',
											listeners: {   
												render: function(p) {   
												     p.getEl().on('click', function(p){   
												    	 var win=new Ext.Window({
												    	        title:'商品分类树形结构',
												    	        height:410,
												    	        width:380,
												    	        modal:true,
												    	        iconCls:'icon-window',
												    	        layout:'accordion',
												    	        items:[{
												    	            title:'当前选择：',
												    	            xtype:'treepanel',
												    	            id:'goodCategoryTree',
												    	            autoScroll:true,
												    	            dataUrl: ctx+"/supplyChain/goodCategory/queryTree.do" ,
												    	            listeners:{
												    	            	'beforeload':function(node){
												    	            		var parentId = node.attributes.id;
												    	            		var goodCategoryTree = Ext.getCmp("goodCategoryTree");
												    	            		goodCategoryTree.loader.dataUrl= ctx+'/supplyChain/goodCategory/queryTree.do?parentId='+parentId;
												    	            	},
												    	            	'click':function(node){
												    	            		leaf = node.attributes.leaf;
												    	            		checkId = node.attributes.id;
												    	            		checkName = node.attributes.text;
												    	            		var tip = "&nbsp;|&nbsp;不是最末级";
												    	            		if(leaf) tip = "&nbsp;|&nbsp;已为最末级";
												    	            		Ext.getCmp("goodCategoryTree").setTitle("当前选择："+checkName+tip);
												    	            	}
												                    },
												    	            root:new Ext.tree.AsyncTreeNode({
												    	                text:'商品分类树',
												    	                id:'0'
												    	            })
												    	        }],
												    	        buttons:[{text:'确定',id:'saveButton',iconCls:'addIcon',
												    	        			handler:function(){
												    	        				if(leaf){
												    	        					Ext.getCmp("categoriesGoodsId").setValue(checkName);
												    	        					Ext.getCmp("goodsClassifyId").setValue(checkId);
													    	        				win.hide();
												    	        				}else{
												    	        					Ext.Msg.alert("系统提示","您所选择的【"+checkName+"】不是最末级");
												    	        				}
												    	        			}
												    	        	     },
												    	        	     {text:'关闭',id:'cencelButton',iconCls:'icon-cencel',
												    	        	    	 handler:function(){
												    	        	    		 win.hide();
													    	        		 }
												    	        }]
												    	  });
												    	  Ext.getCmp("goodCategoryTree").root.expand();
												    	  win.show();
												     });   
											}}
										}]
									}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品条码',allowBlank:false,name:'commercialBarCode',xtype:'textfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品编号',allowBlank:false,name:'itemNumber',xtype:'textfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品名称',allowBlank:false,name:'nameCommodity',xtype:'textfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'规格',allowBlank:false,name:'specifications',xtype:'textfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '计量单位',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['千克', '千克'],['盒','盒'],['份','份']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'company'
											}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '原销售渠道',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['线上', '线上'],['门店','门店'],['大客户','大客户'],['加盟商','加盟商'],['散客','散客']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'theOriginalSalesChannels'
											}]
										}


,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '现销售渠道',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['线上', '线上'],['门店','门店'],['大客户','大客户'],['加盟商','加盟商'],['散客','散客']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'theSalesChannel'
											}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'原进货单价',allowBlank:false,name:'theOriginalPurchasePrice',xtype:'numberfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'原销售单价',allowBlank:true, width : 420, name:'theOriginalSalesPrice',xtype:'numberfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'现进货单价',allowBlank:false,name:'nowThePurchasePrice',xtype:'numberfield'}]
										}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'现销售单价',allowBlank:false,name:'nowTheSalesPrice',xtype:'numberfield'}]
										}
//,{
//											columnWidth : .98,
//											layout : 'form',
//											border : false,
//											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
//											labelAlign : 'right',
//											items :[{fieldLabel:'备注',allowBlank:true,name:'remarks',xtype:'textfield'}]
//										}

,{
	columnWidth : .98,
	layout : 'form',
	border : false,
	defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
	labelAlign : 'right',
	items :[{fieldLabel:'备注',allowBlank:true,name:'remarks',xtype:'textarea'}]
}
,{
											columnWidth : .49,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'commodityChangePriceIds',allowBlank:true,name:'commodityChangePriceIds',xtype:'hidden'}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButtonCh',iconCls:'icon-disk'},{text:'取消',id:'cencelButtonCh',iconCls:'icon-cencel'}]
					}]
	 });
		 commodityChangePriceTwoEditWindow.superclass.initComponent.call(this);
	}
});
