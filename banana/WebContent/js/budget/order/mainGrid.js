mainGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'mainGrid',
	frame:true,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border:false,
    margins:'2 2 0 0 ',
	anchor : '100%',
    initComponent: function() {
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			        {name: 'sn',type: 'string', width: 80}, 
	    			{name: 'customer',type: 'string', width: 70}, 
		    		{name: 'phone',type: 'string', width: 70},
		    		{name: 'address',type: 'string', width: 70},
		    		{name: 'price',type: 'double', width: 120},
		    		{name: 'quantity',type: 'String', width: 100},
		    		{name: 'note',type: 'string'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
		    proxy: new Ext.data.HttpProxy ({ 
				url:ctx+"/wechar/order/query.do"
			 })      
	 	});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true, locked:true, align:"center"}),
	        {header: "<center>订单编号</center>", width: 120, sortable: true, dataIndex: 'sn', locked:false, renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return "<span style='color:red'>"+v+"</span>";
	      		}
	        }, align:"center"},
	        {header: "<center>客户姓名</center>", width: 60, sortable: true, dataIndex: 'customer',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>收货电话</center>", width: 60, sortable: true, dataIndex: 'phone',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>收货地址</center>", width: 60, sortable: true, dataIndex: 'address',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>订单金额</center>", width: 60, sortable: true, dataIndex: 'price',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>购货数量</center>", width: 120, sortable: true, dataIndex: 'quantity',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>备注</center>", width: 80, sortable: true, dataIndex: 'note',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"}
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "queryId";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});