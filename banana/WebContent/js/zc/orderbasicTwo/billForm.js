OrderStatus = [
    ['0', '进行中'],
    ['1', '已完成'],
    ['2', '已取消'] 
];

var OrderStatusStore = new Ext.data.ArrayStore({
    fields: ['value', 'state'],
    data : OrderStatus // from states.js
});

var activityStatusCombo = new Ext.form.ComboBox({
	fieldLabel: '订单状态',
    store: OrderStatusStore,
    displayField:'state',
    valueField: 'value',
    typeAhead: true,
    mode: 'local',
    forceSelection: true,
    triggerAction: 'all',
    value: 0,
    hiddenName: 'orderStatus',
    name: 'orderStatus',
    selectOnFocus:true
});

/**众筹活动订单**/
BillFormPanel = Ext.extend(Ext.form.FormPanel,{
	 id: 'billFormPanel',
	 xtype: 'form', 
	 autoScroll:true,
	 frame: true,
	 buttonAlign: 'center',
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			items:[{
				xtype:'fieldset',
				title: '众筹活动订单',
				collapsible: true,
				layout:'column',
				items: [{
					defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth: 0.33,
					xtype: 'container',
					items:[
				        {name: 'id', hidden: true, allowBlank: true},
   					    {fieldLabel:'<span style="color:red">*</span>订单编号', name: 'crowdfundingCode'},
   					    {fieldLabel:'<span style="color:red">*</span> 是否在支付',
   					    	xtype: 'radiogroup',
   					    	items: [
				                {boxLabel: '是', name: 'isPay', inputValue: 1},
				                {boxLabel: '否', name: 'isPay', inputValue: 0, checked: true}
				            ]
   					    },
   					    {fieldLabel:'<span style="color:red">*</span>发送时间', name: 'sendDate',  xtype:'datetimefield',format:'Y-m-d', value: new Date()},
   					  {fieldLabel:'<span style="color:red">*</span>省', name: 'province'},
   					    {fieldLabel:'<span style="color:red">*</span>微信订单编号', name: 'wxOrdercode'},
       					  {fieldLabel:'<span style="color:red">*</span>发起人住址', name: 'address'},
					    {fieldLabel:'<span style="color:red">*</span>商品单价', name: 'goodPrice'}
 					    
					 ]
				}, {
					defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth : 0.33,
					xtype : 'container',
					items:[
					    {fieldLabel:'<span style="color:red">*</span>添加时间', name: 'addTime',  xtype:'datetimefield',format:'Y-m-d', value: new Date()},
					    new Ext.form.ComboBox({
					    	fieldLabel: '订单状态',
					        store: OrderStatusStore,
					        displayField:'state',
					        valueField: 'value',
					        typeAhead: true,
					        mode: 'local',
					        forceSelection: true,
					        triggerAction: 'all',
					        value: 0,
					        hiddenName: 'orderStatus',
					        name: 'orderStatus',
					        selectOnFocus:true
					    }),
			 		    {fieldLabel:'<span style="color:red">*</span>发送时间段', name: 'sendTimes'},
   					  	{fieldLabel:'<span style="color:red">*</span>市', name: 'city'},
					    {fieldLabel:'<span style="color:red">*</span>发起人姓名', name: 'name'},
					    {fieldLabel:'<span style="color:red">*</span>购买份数', name: 'buyNumber', value: userName, xtype:'numberfield', value: 1, min: 0}
					 ]
				}, {
					defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth : 0.34,
					xtype : 'container',
					items:[
					    {fieldLabel:'<span style="color:red">*</span>结束时间', name: 'endTime',  xtype:'datetimefield',format:'Y-m-d', value: new Date()},
					    {fieldLabel:'<span style="color:red">*</span>支付时间', name: 'paymenTtime',  xtype:'datetimefield',format:'Y-m-d', value: new Date()},
					    {fieldLabel:'<span style="color:red">*</span>众筹人份数', name: 'playNum', value: userName, xtype:'numberfield', value: 1, min: 0},
   					  {fieldLabel:'<span style="color:red">*</span>区', name: 'area'},
					    {fieldLabel:'<span style="color:red">*</span>发起人电话', name: 'tel'},
					    {fieldLabel:'<span style="color:red">*</span>商品名称', name: 'skuName'}
					 ]
				}, {
					labelAlign : 'right',
					layout:'form',
					columnWidth : 1,
					xtype : 'container',
					defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					items: [
					    {xtype:'textarea', name:'payurl', fieldLabel:'支付url', height:30}
					]
       			}, {
					labelAlign : 'right',
					layout:'form',
					columnWidth : 1,
					xtype : 'container',
					defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					items: [
					    {xtype:'textarea', name:'remark', fieldLabel:'备注', height:30}
					]
       			}]
       		}, {
				xtype:'fieldset',
				title: '众筹支付信息',
				collapsible: true,
				items:[{
					layout:'form',
					columnWidth: 1,
					frame: false,
					items: new BillDetailGrid()
				}]
			}],
       		buttons:[{
		    	text:'保存',
		    	id:'add1Button',
		    	iconCls: 'saveIcon',
				handler: function(){
					var datas = Ext.getCmp("billDetailGrid").getStore().data;
					var details = "[";
					for(var i = 0; i < datas.items.length; i++) {
						if(i > 0) {
							details += ","
						}
						var data = datas.items[i].data
						details += "{costCode: '"+data.costCode+"'," +
								"costName: '"+data.costName+"'," +
								"cost: "+data.cost+"," +
								"digest: '"+data.digest+"'," +
								"remark: '"+data.remark+"'}";
					}
					
					details += "]"
					Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "admin/orderbasicTwo/save.do", 
	            		function(form, action){
	            			Ext.Msg.alert('消息', action.result.data.instruction);
	            			Ext.MessageBox.confirm('确认', '您确定要继续添加吗?', function(btn){
	        					if(btn == 'yes'){
	        						Ext.getCmp("billFormPanel").getForm().reset();
	        						Ext.getCmp("billDetailGrid").getStore().removeAll();
	        					}else{
	        						Ext.getCmp("addWindow").close();
	        					}
	            			})
	            			
	            			Ext.getCmp("mainGrid").getStore().load();
	            		}, "", {detailStr: details}
	            	);
				  }
			  },{text:'更新',id:'update1Button',
				  iconCls: 'edit1Icon',
				  handler: function(){
				  	  var datas = Ext.getCmp("billDetailGrid").getStore().data;
				  	  console.log(datas);
					  var details = "[";
					  for(var i = 0; i < datas.items.length; i++) {
							if(i > 0) {
								details += ","
							}
							var data = datas.items[i].data
							var id = datas.items[i].id;
							if(id.indexOf("ext-record-") > 0){
								id = "";
							}
							details += "{id: '" +id+"',"+
									"costCode: '"+data.costCode+"'," +
									"costName: '"+data.costName+"'," +
									"cost: "+data.cost+"," +
									"digest: '"+data.digest+"'," +
									"remark: '"+data.remark+"'}";
						}
						
						details += "]"
						
						Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "admin/orderbasicTwo/update.do", 
		            		function(form, action){
		            			Ext.Msg.alert('消息', action.result.data.instruction);
		            			Ext.getCmp("addWindow").close();
		            			Ext.getCmp("mainGrid").getStore().load();
		            		},  "", {detailStr: details}	
	            		);
				  }
			  },{text:'返回',id:'reset1Button', 
				  iconCls: 'returnIcon',
				  handler: function(){
					  Ext.getCmp("addWindow").close();
				  }
			  }]
		});
		
	 	BillFormPanel.superclass.initComponent.call(this);
	 	
	 	if(!Ext.isEmpty(this.getForm().findField("categoryCombo"))){
	 		 form = this.getForm();
	 		 form.findField("categoryCombo").tree.on({
	 			 click: function(node){
	 			 	var projectCombo = form.findField("projectCombo");
	 			 	projectCombo.clearValue();
	 			 	projectCombo.getStore().baseParams = {categoryCode: node.attributes.code};
	 			 	projectCombo.getStore().removeAll(true);
	 			 	projectCombo.getStore().load();
	 			 }
	 		 });
	 	 }
	 }
})