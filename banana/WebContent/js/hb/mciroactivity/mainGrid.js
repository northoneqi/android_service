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
		var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		var store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段    
			   fields:[	
			       {name: 'activityId'},
			       {name: 'activityName'},
			       {name: 'beginDate'},
			       {name: 'stopDate'},
			       {name: 'prizeCount'},
			       {name: 'beginTime'},
			       {name: 'endTime'},
			       {name: 'peiSongCnt'},
			       {name: 'sku'},
			       {name: 'showUrl'} 
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'admin/mciroactivity/list.do'
			 })      
	 	});
		store.on({
			"beforeload": function(myStore){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					
					myStore.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50, sortable : true, align:"center"}),
	        {header: "<center>活动名称</center>", width: 100, sortable: true, dataIndex: 'activityName', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>开始日期</center>", width: 80, sortable: true, dataIndex: 'beginDate',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>结束日期</center>", width: 80, sortable: true, dataIndex: 'stopDate',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>奖品数量</center>", width: 80, sortable: true, dataIndex: 'prizeCount',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>开始时间</center>", width: 80, sortable: true, dataIndex: 'beginTime',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>结束时间</center>", width: 80, sortable: true, dataIndex: 'endTime',renderer:function(v){
	     		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>配送员配送数量</center>", width: 100, sortable: true, dataIndex: 'peiSongCnt',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>商品SKU</center>", width: 100, sortable: true, dataIndex: 'sku',renderer:function(v){
	    		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	       }, align:"center"},
	        {header: "<center>显示URL</center>", width: 100, sortable: true, dataIndex: 'showUrl',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        } , align:"center"}
	    ];	
		this.colModel = new Ext.grid.ColumnModel(columns);
		this.store = store;
		this.id = "queryId";
		this.sm=sm;
		//this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});