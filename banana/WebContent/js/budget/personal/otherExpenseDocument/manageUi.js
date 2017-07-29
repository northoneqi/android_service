manageUi = Ext.extend(Ext.Panel,{
	title:'其他费用确认单管理',
	id:'manageUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		  var User = [
		    	{header: '订单编号',name: 'sn',type: 'string', width: 80}, 
	    		{header: '客户姓名', name: 'customer',type: 'string', width: 70}, 
	    		{header: '收货电话', name: 'phone',type: 'string', width: 70},
	    		{header: '收货地址', name: 'address',type: 'string', width: 70},
	    		{header: '订单金额', name: 'price',type: 'double', width: 120},
	    		{header: '购货数量', name: 'quantity',type: 'String', width: 100},
	    		{header: '备注', name: 'note',type: 'string'}
		    ];
		    
		    var userGrid = createExtGrid("订单列表", User, true, {},"wechar/order/query.do", "multi");
		   
		    
		    var gridTopbar = userGrid.getTopToolbar();
		    
		    var gridTbar = [{
	            iconCls: 'addIcon',
	            text: '添加',
	            handler: function(){
		    		showAdd();
	            }
	        },  
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'edit1Icon',
	            text: '更新',
	            handler: function(){
	            	showUpdate();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        , {
	            iconCls: 'deleteIcon',
	            text: '删除',
	            handler: function(){
	            	showDelete();
	            }
	        },
	        	{xtype: 'tbseparator'}
	        ];
    	  gridTopbar.add(gridTbar);
    	  
		  Ext.applyIf(this,{
			items:[{
				layout:'border',
				items:[{
					region:'north',
					margins:'1 1 1 1',
					frame:true,
					height:150,
					layout:'fit',
					items:new searchFrom()
				},{
					region:'center',
					margins:'1 1 1 1',
					frame:true,
					layout:'fit',
					items: userGrid
				}]
			}]
		 });
		 manageUi.superclass.initComponent.call(this);
	}
});
