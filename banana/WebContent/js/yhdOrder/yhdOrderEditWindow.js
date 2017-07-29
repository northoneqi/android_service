Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
var fatherId = 0;
var page_size=20;
yhdOrderEditWindow=Ext.extend(Ext.Window,{
	title:'要货单',
	width:'90%',
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
							width:550,
							height:340,
							layout:'form', 
							bodyStyle:'padding-top:6px;',
							labelAlign:'right',
							frame:true,
							anchor : '99%',
							items : [{  
					             layout : 'column', // 定义该元素为布局为列布局方式
					             items : [{xtype:'hidden',id:'yhdOrderId',name:'yhdOrderId'},
					             		{
											columnWidth : .33,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'门店名称',allowBlank:false,name:'mdCode',xtype:'textfield'}]
										}
,{
											columnWidth : .33,
											layout : 'form',
											border : false,
											defaults : {anchor:'-10',xtype : 'textfield', msgTarget : 'side',labelWidth:60},
											labelAlign : 'right',
											items :[{fieldLabel:'联系人',allowBlank:false,name:'mdName',xtype:'textfield'}]
										}
,{
											columnWidth : .99,
											layout : 'form',
											border : false,
											labelAlign : 'right',
											bodyStyle:'padding-top:6px;',
											items :[{ 
												tbar:[
												        {text:'增加',iconCls:'addIcon',handler:function(){
												        	 if(Ext.getCmp("yhdOrderId").getValue()!=null)
												        		 fatherId = Ext.getCmp("yhdOrderId").getValue();
												        	 var win2 = new yhdOrderItemsEditWindow();
															 win2.show();
															 var saveButtion=Ext.getCmp("saveButtonCh");
															 if(saveButtion){
																 /** 保存按钮点击事件 **/
																 saveButtion.on("click",function(){
																	var formPanel=Ext.getCmp("saveFormPanelCh").getForm();
																	formPanel.findField("yhdOrderIds").setValue(fatherId);
																	if(formPanel.isValid()){
																		formPanel.submit({
																			waitMsg : '正在保存数据,请稍等...',
																			waitTitle : '系统提示',// 标题
																			method:'POST',
																			url:ctx+"/auto/yhdOrderItems/save.do",
																			params:formPanel.getValue,
																			success:function(form,action){
																				var mid=action.result.msg;
																				if(mid!=null){
																					fatherId = mid;
																					win2.close();
																					grid2.getStore().setBaseParam("paraentId",mid);
																					grid2.getStore().load();
																					var formPanelP = Ext.getCmp("saveFormPanel").getForm();
																					formPanelP.findField("yhdOrderId").setValue(mid);
																					grid.getStore().load();
																				}else{
																					win2.close();
																					grid2.getStore().load();
																				}
																			},
																			failure:function(form,action){
																				Ext.Msg.alert("系统提示","添加失败");
																			}
																		});
																	}
																 });
															 }
															 /**取得取消按钮 定义返回事件**/
															 var cencelButton = Ext.getCmp("cencelButtonCh");
																cencelButton.on("click",function(){
																	win2.close();
															});
												        }},'-',
										  	 		    {text:'修改',iconCls:'edit1Icon',handler:function(){
										  	 		    	 var win2 = new yhdOrderItemsEditWindow();
											  	 			 var rowid = getCheckRowId2();
											  	 			 var c = rowid.split(",");
											  	 			 if(rowid!="" && c.length == 1){
											  	 				 win2.show();
											  	 				 Ext.getCmp("yhdOrderItemsId").setValue(rowid);
											  	 				 loadFormCh(rowid);//加载form
											  	 			 }else if(rowid!=""){
											  	 				 Ext.Msg.alert("系统提示","请选择您要修改的记录");
											  	 			 }else {
											  	 				 Ext.Msg.alert("系统提示","一次只能修改一条记录");
											  	 			 }
											  	 			 var saveButtion=Ext.getCmp("saveButtonCh");
											  	 			 if(saveButtion){
											  	 				 saveButtion.on("click",function(){
											  	 					var formPanel=Ext.getCmp("saveFormPanelCh").getForm();
											  	 					//alert(formPanel.getFieldValues() )
											  	 					if(formPanel.isValid()){
											  	 						formPanel.submit({
											  	 							waitMsg : '正在修改数据,请稍等...',
											  	 							waitTitle : '系统提示',// 标题
											  	 							method:'POST',
											  	 							url:ctx+"/auto/yhdOrderItems/update.do",
											  	 							params:formPanel.getValue,
											  	 							success:function(form,action){
											  	 								grid2.getStore().load();
											  	 								Ext.Msg.alert("系统提示","修改成功");
											  	 								win2.close();
											  	 							},
											  	 							failure:function(form,action){
											  	 								MsgTip.msg('系统提示', '修改失败',true);
											  	 								Ext.Msg.alert("系统提示","修改失败");
											  	 							}
											  	 						});
											  	 					}
											  	 				 });
											  	 			 }
											  	 			 /**取得取消按钮 定义返回事件**/
											  	 			 var cencelButton = Ext.getCmp("cencelButtonCh");
											  	 				cencelButton.on("click",function(){
											  	 					win2.close();
											  	 			});
										  	 		    }},'-',
										  				{text:'删除',iconCls:'deleteIcon',handler:function(){
										  					var rowid = getCheckRowId2();
										  					 if(rowid!=""){
										  						 Ext.Msg.confirm('系统提示', '您确定要删除吗?', function (opt) {
										  			                 if (opt == 'yes') {
										  			                    Ext.Ajax.request({
										  									 url:ctx+"/auto/yhdOrderItems/delete.do",
										  									 params:{ids:rowid},
										  									 success:function(response){
										  										 grid2.getStore().load();
										  										 Ext.Msg.alert("系统提示","删除成功");
										  									 }
										  								});
										  			                }
										  			             });
										  					 }else {
										  						 Ext.Msg.alert('系统提示', '请选择您要删除的记录');
										  					 }
										  				}}
									  		 	    ],
													region:'center',
													layout:'fit',
													items:grid2,
													bbar:new Ext.PagingToolbar({
									    				pageSize : page_size,
									    				store : grid2.getStore(),
									    				displayInfo : true,
									    				displayMsg:'每页显示'+page_size+'条记录，当前显示{0}到{1}条，共{2}条。',  
									        			emptyMsg:'没有记录'
									  		 		})
												}]
										}

					             		]
					            	}]
								}] 
							}],
							buttons:[{text:'保存',id:'saveButton',iconCls:'icon-disk'},{text:'取消',id:'cencelButton',iconCls:'icon-cencel'}]
					}]
	 });
	
	 yhdOrderEditWindow.superclass.initComponent.call(this);
	}
});
