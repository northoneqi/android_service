searchForm = Ext.extend(Ext.form.FormPanel,{
	id:'searchForm',
	border:false,
	margins:'2 2 2 2 ',
	height:100,
	layout:'form',
	xtype: 'form',
	frame:true,
	initComponent: function() {
		 state=createCombo("申请状态", "state", [["-1","请选择"], ["0","未审批"], ["1","审批通过"],["2","审批未通过"]]);
		 /**combox下拉框**/
		 var shequStore=new Ext.data.Store({
				proxy: new Ext.data.HttpProxy({
					method:'post',
		            url: ctx+'/wechat/mcirowater.do?method=getRoles'
		        }),
		        reader: new Ext.data.JsonReader({
		        	totalProperty:'total',
		            root: 'root'
		        }, [
		        	{name: 'name', mapping: 'name'}
		        ])
			});

//			var shequCombox=new Ext.form.ComboBox({
//				store:shequStore,
//		        typeAhead: true,
//		        minChars:1,
//		        mode: 'remote',  
//		        triggerAction: 'all',
//		        fieldLabel:'社区名称',
//		        editable: true,
//		        hiddenName:'shequ',
//		        hiddenId:'shequ',
//		        minListWidth : 210,
//		        pageSize:10,
//		        displayField:'name',
//		        valueField :'name',  
//		        hideTrigger:false,
//		        selectOnFocus:false,
//		        forceSelection:true
//			});
			
		 Ext.applyIf(this,{
			border:false,
			items:[{
				collapseFirst:false, 
				margins:'1 1 1 1 ',
				border:false,
				layout:'column',
				bodyStyle:'padding-top:5px;',
				items:[
							{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'开始时间',id:'startTime',name:'tistarTimetle',xtype:'datefield',format:'Y-m-d'}
		           				]
	           				},
	           				{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:40},
		           				labelAlign : 'right',
		           				items:state
	           				},
	           				{
								columnWidth :0.33,
								layout:'form',
								border:false,
								defaults : {anchor:'-10', allowBlank : true,xtype : 'textfield', msgTarget : 'side',labelWidth:60},
		           				labelAlign : 'right',
		           				items:[{fieldLabel:'社区名称',id:'shequ',name:'shequ'}]
	           				}
				]
			}],
			buttonAlign:'right',
			buttons:[{text:'查询',id:'queryButton'},{text:'清空',id:'resetButton'},{text:'EXCEL导出',id:'exportexcelButton'}]
		 });
		 searchForm.superclass.initComponent.call(this);
	}
});