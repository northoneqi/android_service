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
			       {name: 'activituId'},
			       {name: 'activityNum'},
			       {name: 'playNum'},
			       {name: 'activityBeginTime'},
			       {name: 'activityEndTime'},
			       {name: 'totalOrderNum'},
			       {name: 'totalOrderMoney'},
			       {name: 'validOrderNum'},
			       {name: 'validOrderMoney'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'admin/orderbasicTwo/orderStatics.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					store.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	//sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true,  align:"center"}),
	        {header: "<center>众筹活动Id</center>", width: 120, sortable: true, dataIndex: 'activituId', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>众筹期数</center>", width: 80, sortable: true, dataIndex: 'activityNum',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>总订单数量</center>", width: 80, sortable: true, dataIndex: 'totalOrderNum',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>总订单金额</center>", width: 80, sortable: true, dataIndex: 'totalOrderMoney',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v ;
	      		}
	        }, align:"center"},
	        {header: "<center>完成订单数量</center>", width: 80, sortable: true, dataIndex: 'validOrderNum',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>完成订单金额</center>", width: 80, sortable: true, dataIndex: 'validOrderMoney',renderer:function(v){
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