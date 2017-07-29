Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
var isLeaf = false; //判断当前选择的节点是不是最末级
var checkId = "";//当前选择的节点的ID
var checkName = "";//当前选择的节点名称
commodityRecordEditWindow=Ext.extend(Ext.Window,{
	title:'商品登记表',
	width:750,
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
							width:700,
							height:340,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'commodityRecordId',name:'commodityRecordId'},
					                      {xtype:'hidden',id:'goodsClassifyId',name:'goodsClassifyId'},
					             		{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品类别',allowBlank:false,name:'goodsClassify',id:'goodsClassifyName',xtype:'textfield',
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
													    	        					Ext.getCmp("goodsClassifyName").setValue(checkName);
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
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
			                                     fieldLabel : '是否是赠品',
			                                     allowBlank : false,
			                                     store:new Ext.data.SimpleStore({ 
					    							 fields : ['name', 'code'],
					    							 data : [['是', '是'],['否','否']]
					    				   		 }),
					    				   		 mode:"local",
					    				   		 editable:false,
					    				   		 value:'请选择',
					    				   		 triggerAction:"all", 
					    				   		 valueField :'code',  
					    					     displayField:'name',
					    						 name:'isNotGift'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'厂商货号',allowBlank:false,name:'companySKU',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'产地',allowBlank:false,name:'productionPlace',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品品牌',allowBlank:false,name:'commodityBrand',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'关键字',allowBlank:true,name:'keyword',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品条码',allowBlank:false,name:'productCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
			                                     fieldLabel : '是否电子称码',
			                                     allowBlank : false,
			                                     store:new Ext.data.SimpleStore({ 
					    							 fields : ['name', 'code'],
					    							 data : [['是', '是'],['否','否']]
					    				   		 }),
					    				   		 mode:"local",
					    				   		 editable:false,
					    				   		 value:'请选择',
					    				   		 triggerAction:"all", 
					    				   		 valueField :'code',  
					    					     displayField:'name',
					    						 name:'isNotElectronicCode'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品编号',allowBlank:false,name:'commodityCode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品名称',allowBlank:false,name:'commodityName',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'商品助记码',allowBlank:true,name:'commodityMnemoniccode',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'规格',allowBlank:false,name:'format',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
			                                     fieldLabel : '是否线上专供',
			                                     allowBlank : false,
			                                     store:new Ext.data.SimpleStore({ 
					    							 fields : ['name', 'code'],
					    							 data : [['是', '是'],['否','否']]
					    				   		 }),
					    				   		 mode:"local",
					    				   		 editable:false,
					    				   		 value:'请选择',
					    				   		 triggerAction:"all", 
					    				   		 valueField :'code',  
					    					     displayField:'name',
					    						 name:'isNotOnLineSupply'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'计量单位(kg)',allowBlank:false,name:'measurementCompany',xtype:'numberfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
										         fieldLabel : '商品状态',
										         allowBlank : false,
										         store:new Ext.data.SimpleStore({ 
													 fields : ['name', 'code'],
													 data : [['可定可销', '可定可销'],['可定不可销','可定不可销'],['不可定可销','不可定可销'],['不可定不可销','不可定不可销'],['待删除','待删除']]
										   		 }),
										   		 mode:"local",
										   		 editable:false,
										   		 value:'请选择',
										   		 triggerAction:"all", 
										   		 valueField :'code',  
											     displayField:'name',
												 name:'commodityState'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'保质期天数',allowBlank:false,name:'qualityNummberDay',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'临界期天数',allowBlank:false,name:'criticalPeriodNummberDay',xtype:'datefield',format:'Y-m-d'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'最少起量(kg)',allowBlank:true,name:'minimumQuantity',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{
												 xtype : 'combo', 
			                                     fieldLabel : '是否允许退货',
			                                     allowBlank : false,
			                                     store:new Ext.data.SimpleStore({ 
					    							 fields : ['name', 'code'],
					    							 data : [['是', '是'],['否','否']]
					    				   		 }),
					    				   		 mode:"local",
					    				   		 editable:false,
					    				   		 value:'请选择',
					    				   		 triggerAction:"all", 
					    				   		 valueField :'code',  
					    					     displayField:'name',
					    						 name:'isNotAllowReturnGoods'
											}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'安全库存',allowBlank:true,name:'safetyStock',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'录入人',allowBlank:false,name:'entryPerson',xtype:'textfield'}]
										}
,{
											columnWidth : .48,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'录入日期',allowBlank:false,name:'entryDate',xtype:'datefield',format:'Y-m-d'}]
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
		 commodityRecordEditWindow.superclass.initComponent.call(this);
	}
});
