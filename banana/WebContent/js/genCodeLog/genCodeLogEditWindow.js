Ext.BLANK_IMAGE_URL=ctx+"/ext/resources/images/default/s.gif"; 
genCodeLogEditWindow=Ext.extend(Ext.Window,{
	title:'自动代码生成',
	width:800,
	height:432, 
	layout:'fit',
	modal:true,
	//autoScroll: true,  
	iconCls:'icon-window',
	constrainHeader:true,//是否能移出界面
	resizable:true,//窗口是否能改变大小 
	buttonAlign:'center',
	initComponent: function() { 
		var alltable ;
		$.ajax({    
		     type:'post',    
		     async: false, 
		     url:ctx+"/auto/sysAllTables/findBizTable.do",
		     success:function(data){   
		    	 alltable = Ext.decode(data);
		     },    
		     error:function(){}    
		});
	    Ext.applyIf(this,{ 
		 layout:'fit',
		 border:false,
		 items:[{
			 	xtype:'form',
			 	layout:'form', 
			    id:'saveFormPanel',
			    border:false,
				buttonAlign:'center',
				items:[{
					layout:'column',
					items:[{
						width:334,
						height:360,
						xtype: 'multiselect',  
					    name: 'lmultiselect',  
					    store: alltable,  
					    tbar:[{  
					        text: '系统业务表'
					    }], 
					    listeners:{
					    	dblclick:function(obj,event){
					    		var formPanel=Ext.getCmp("saveFormPanel").getForm();
								var m = formPanel.findField('lmultiselect');
								var value=m.getValue();
								var store=m.store;
								var rm = formPanel.findField('rmultiselect');
								var rstore=rm.store;
								store.each(function(record) {  
									//if(value.indexOf(record.data.value)>=0){
									if(value == record.data.value){
										rstore.add(record);
										store.remove(record);
									}
								});
					    	}
					    },
					    ddReorder: true 
					},{
						width:110,
						height:360,
						layout:'form',
						border:false,
						bodyStyle:'padding-top:90',
						items:[{
							border:false,
							buttonAlign:'center',
							buttons:[{text:'右移',id:'rightButton'}]
						},{
							border:false,
							buttonAlign:'center',
							buttons:[{text:'全部右移',id:'allRightButton'}]
						},{
							border:false,
							buttonAlign:'center',
							buttons:[{text:'左移',id:'leftButton'}]
						},{
							border:false,
							buttonAlign:'center',
							buttons:[{text:'全部左移',id:'allLeftButton'}]
						}]
					},{
						width:342,
						height:360,
						xtype: 'multiselect',  
						name: 'rmultiselect',  
					    store: [],  
					    tbar:[{  
					        text: '即将生成列表'
					    }],  
					    listeners:{
					    	dblclick:function(obj,event){
					    		var formPanel=Ext.getCmp("saveFormPanel").getForm();
								var m = formPanel.findField('rmultiselect');
								var value=m.getValue();
								var store=m.store;
								var rm = formPanel.findField('lmultiselect');
								var rstore=rm.store;
								store.each(function(record) {  
									if(value == record.data.text){
										rstore.add(record);
										store.remove(record);
									}
								});
					    	}
					    },
					    ddReorder: true 
					}]
				}],
				buttons:[{text:'执行自动生成代码',id:'saveButton',iconCls:'icon-gencode'},{text:'关闭窗口',id:'cencelButton',iconCls:'icon-cencel'}]
			}]
	     });
		 genCodeLogEditWindow.superclass.initComponent.call(this);
	}
});
