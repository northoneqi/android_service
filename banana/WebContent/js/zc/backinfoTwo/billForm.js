ActivityStatus = [ [ '0', '未开始' ], [ '1', '进行中' ], [ '2', '暂停' ], [ '3', '结束' ] ];

var ActivityStatusStore = new Ext.data.ArrayStore( {
	fields : [ 'value', 'state' ],
	data : ActivityStatus
// from states.js
		});

activityStatusCombo = new Ext.form.ComboBox( {
	fieldLabel : '活动状态',
	store : ActivityStatusStore,
	displayField : 'state',
	valueField : 'value',
	typeAhead : true,
	mode : 'local',
	forceSelection : true,
	triggerAction : 'all',
	value : 0,
	hiddenName : 'activityStatus',
	name : 'activityStatusw',
	selectOnFocus : true
});

/**众筹活动计划**/
BillFormPanel =Ext.extend(Ext.form.FormPanel,{
	 id: 'billFormPanel',
	 xtype: 'form', 
	 autoScroll:true,
	 frame: true,
	 buttonAlign: 'center',
	 fileUpload : true, 
	 initComponent:function(){
		Ext.applyIf(this,{
			border:false,
			items:[{
				xtype:'fieldset',
				title: '众筹活动计划',
				collapsible: true,
				layout:'column',
				items: [{
					defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth: 0.33,
					xtype: 'container',
					items:[ 
				        {fieldLabel:'<span style="color:red">*</span>活动id', name: 'id', xtype: 'numberfield', id: 'id'},
				        Ext.ux.form.ComboBox({
					    	 id: 'documentTypeCombo',
 							 name: 'playNum',
 							 fieldLabel: '<span style="color:red">*</span>众筹人份数',
 				        	 fields: [{name: 'name'}, {name: 'name'}],
 				        	 url: 'sys/dict/getDictByCode.do?code=1009'
 				        }),
				       // {fieldLabel:'<span style="color:red">*</span>众筹人份数', name: 'playNum', xtype:'numberfield', value: 0},
				        {fieldLabel:'<span style="color:red">*</span>最少允许支付剩余金额的份数', name: 'minPayNumber', type: 'numberfield',  value: 1,regex:/^[0-9]*[1-9][0-9]*$/,regexText:"只能是正整数！"}, 
					    {fieldLabel:'<span style="color:red">*</span>活动开始时间', name: 'activityBeginTimes', xtype:'datetimefield',format:'Y-m-d', value: new Date()},
   					    {fieldLabel:'<span style="color:red">*</span>活动计划时间', name: 'activityAddTimes', xtype:'datetimefield',format:'Y-m-d', value: new Date()}
					    ]
				}, {
					defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth : 0.33,
					xtype : 'container',
					items:[
	   					{fieldLabel:'<span style="color:red">*</span>活动期数', name: 'activityNum'},
	   				 {fieldLabel:'<span style="color:red">*</span>未完成是否可以取消',
   					    	xtype: 'radiogroup',
   					    	items: [
				                {boxLabel: '是', name: 'isCanal', inputValue: 1},
				                {boxLabel: '否', name: 'isCanal', inputValue: 0, checked: true}
				            ]
   					    },
   					 {fieldLabel:'<span style="color:red">*</span>最多发起次数', name: 'maxPlayMore', value: userName, xtype:'numberfield', value: 1,regex:/^[0-9]*[1-9][0-9]*$/,regexText:"只能是正整数！"},
   					  {fieldLabel:'<span style="color:red">*</span>活动结束时间', name: 'activityEndTimes', id: 'activityEndTimes',  xtype:'datetimefield',format:'Y-m-d', value: new Date()},
   					  {fieldLabel:'<span style="color:red">*</span>每天配送上限', name: 'deliveryUpLimit', value: 1, xtype:'numberfield', regex:/^[0-9]*[1-9][0-9]*$/,regexText:"只能是正整数！"}
   					  
					 ]
				}, {
					defaults : {allowBlank : false,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth : 0.34,
					xtype : 'container',
					items:[
							new Ext.form.ComboBox({
								fieldLabel: '活动状态',
							    store: ActivityStatusStore,
							    displayField:'state',
							    valueField: 'value',
							    typeAhead: true,
							    mode: 'local',
							    forceSelection: true,
							    triggerAction: 'all',
							    value: 0,
							    hiddenName: 'activityStatus',
							    name: 'activityStatusw',
							    selectOnFocus:true
							}),
							{fieldLabel:'<span style="color:red">*</span>是否可以自己支付', 
	   					    	xtype: 'radiogroup',
	   					    	items: [
					                {boxLabel: '是', name: 'isPayall', inputValue: 1},
					                {boxLabel: '否', name: 'isPayall', inputValue: 0, checked: true}
					            ]
	   					    },
	   					    {fieldLabel:'<span style="color:red">*</span>最多支付次数', name: 'maxPayMore', xtype:'numberfield',  value: 1,regex:/^[0-9]*[1-9][0-9]*$/,regexText:"只能是正整数！"},
	   					   {fieldLabel:'<span style="color:red">*</span>活动计划经办人', name: 'activityAuto', value: userName},
	   					   {fieldLabel:'<span style="color:red">*</span>每天发起上限', name: 'launchUpLimit',  xtype:'numberfield', value: 1,regex:/^[0-9]*[1-9][0-9]*$/,regexText:"只能是正整数！"}
   					  
	   					  ]
				}, {
					labelAlign : 'right',
					layout:'form',
					columnWidth : 1,
					xtype : 'container',
					defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					items: [
					    {xtype:'textarea', name:'activityInfo', fieldLabel:'活动介绍', height:30}
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
				title: '活动商品',
				collapsible: true,
				layout:'column',
				items:[{
						defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth: 0.33,
						xtype: 'container',
						items:[
					        {name: 'productSKU', hidden: true, allowBlank: true},
					        {name: 'productId', hidden: true, allowBlank: true},
	   					    {fieldLabel:'<span style="color:red">*</span>商品名称', name: 'productName', id: 'productName', emptyText: '点击选择', style:'background-color: #F6F6F6; background-image: none;',readOnly: true},
	   					    {fieldLabel:'<span style="color:red">*</span>商品图片', name: 'file', inputType : 'file', allowBlank: true, id: 'fileInput'}
						 ]
					},{
						defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
						labelAlign : 'right',
						layout:'form',
						columnWidth: 0.33,
						xtype: 'container',
						items:[
	   					    {fieldLabel:'<span style="color:red">*</span>商品原价格', name: 'productOldPrice', xtype:'numberfield', style:'background-color: #F6F6F6; background-image: none;',readOnly: true},
	   					    {fieldLabel:'图片位置',name: 'productImg', hidden: false, allowBlank: true, style:'background-color: #F6F6F6; background-image: none;',readOnly: true}
						 ]
				},{
					defaults : {allowBlank: false, xtype: 'textfield', msgTarget: 'side', labelWidth:80, anchor : anchor_w},
					labelAlign : 'right',
					layout:'form',
					columnWidth: 0.33,
					xtype: 'container',
					items:[
   					    {fieldLabel:'<span style="color:red">*</span>众筹价',name: 'productPrice', xtype:'numberfield',style:'background-color: #F6F6F6; background-image: none;', readOnly:true,regex:/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,regexText:"只能非零的浮点数或整数！"},
   					    {name: 'productNum', xtype:'numberfield',hidden: true,value:1}
					 ]
				}, {
					labelAlign : 'right',
					layout:'form',
					columnWidth : 1,
					xtype : 'container',
					defaults : {allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:80, anchor : anchor_w},
					items: [
					    {xtype:'textarea', name:'productRemark', fieldLabel:'备注', height:30}
					]
       			}]
       		}],
       		buttons:[{
		    	text:'保存',
		    	id:'add1Button',
		    	iconCls: 'saveIcon',
				handler: function(){
					var productImg = Ext.getCmp("billFormPanel").getForm().findField("file").getValue();
       				//console.log(productImg);
					if(productImg == "") {
       					Ext.Msg.alert("消息", "请选择商品图片");
       					return;
       				}
					var activityBeginTimes = Ext.getCmp("billFormPanel").getForm().findField("activityBeginTimes").getValue();
					var activityEndTimes = Ext.getCmp("billFormPanel").getForm().findField("activityEndTimes").getValue();
					//alert(activityBeginTimes+"--"+activityEndTimes);
					if(activityEndTimes != null && activityBeginTimes != null && activityBeginTimes > activityEndTimes){
							Ext.Msg.alert('消息', "时间输入不正确（开始日期不能大于结束日期）");
							 return;
					} 
					
					
					Ext.ux.Ajax.request("admin/backinfoTwo/find.do", {id: Ext.getCmp("id").getValue()}, function(request, action){
						var result = eval("("+request.responseText+")")
						if(result.success) {
							Ext.Msg.alert("提示", "该活动Id已经被使用，请重新输入");
							Ext.getCmp("id").focus(true);
							return;
						} else{
							Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "admin/backinfoTwo/save.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			Ext.getCmp("mainGrid").getStore().load();
			            			Ext.getCmp("addWindow").close();
			            		}, "", {}
			            	);
						}
					});
		
				  }
			  },{text:'更新',id:'update1Button',
				  iconCls: 'edit1Icon',
				  handler: function(){
				  		Ext.ux.Ajax.request("admin/backinfoTwo/find.do", {id: Ext.getCmp("id").getValue()}, function(request, action){
				  			 
							var activityBeginTimes = Ext.getCmp("billFormPanel").getForm().findField("activityBeginTimes").getValue();
							var activityEndTimes = Ext.getCmp("billFormPanel").getForm().findField("activityEndTimes").getValue();
							//alert(activityBeginTimes+"--"+activityEndTimes);
							if(activityEndTimes != null && activityBeginTimes != null && activityBeginTimes > activityEndTimes){
									Ext.Msg.alert('消息', "时间输入不正确（开始日期不能大于结束日期）");
									 return;
							} 
							
							Ext.ux.Form.submitForm(Ext.getCmp("billFormPanel"), "admin/backinfoTwo/update.do", 
			            		function(form, action){
			            			Ext.Msg.alert('消息', action.result.data.instruction);
			            			Ext.getCmp("addWindow").close();
			            			Ext.getCmp("mainGrid").getStore().load();
			            		},  "", {}	
		            		);
						});
				  }
			  },{text:'返回',id:'reset1Button', 
				  iconCls: 'returnIcon',
				  handler: function(){
					  Ext.getCmp("addWindow").close();
				  }
			  }]
		});
		
		
	 	BillFormPanel.superclass.initComponent.call(this);
	 	
	 	Ext.getCmp("productName").addListener('focus', function(){
			 var win = new ProductWindow();
			 win.show();
		})
		
		Ext.getCmp("activityEndTimes").addListener('change', function(field, value, oldValue){
			var activityBeginTimes = Ext.getCmp("billFormPanel").getForm().findField("activityBeginTimes").getValue();
			var activityEndTimes = field.getValue();
			if(activityEndTimes != null && activityBeginTimes != null && activityBeginTimes > activityEndTimes){
                Ext.Msg.alert('消息', "时间输入不正确（开始日期不能大于结束日期）");
                return;
			} 
		}) 
		
	 }
})