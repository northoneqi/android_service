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
			       {name: 'ActivityId'},
			       {name: 'countAll'},
			       {name: 'Send'},
			       {name: 'ActivityName'},
			       {name: 'PeiSongCnt'},
			       {name: 'BeginDate'},
			       {name: 'StopDate'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'wechat/box/list.do'
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
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true,  align:"center"}),
	        {header: "<center>抢盒子期数</center>", width: 120, sortable: true, dataIndex: 'ActivityId', renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动名字</center>", width: 80, sortable: true, dataIndex: 'ActivityName',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v ;
	      		}
	        }, align:"center"},
	        {header: "<center>抢盒子总量</center>", width: 80, sortable: true, dataIndex: 'countAll',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>已配送量数</center>", width: 80, sortable: true, dataIndex: 'Send',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},	        
	        {header: "<center>活动开始日期</center>", width: 80, sortable: true, dataIndex: 'BeginDate',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>活动结束日期</center>", width: 80, sortable: true, dataIndex: 'StopDate',renderer:function(v){
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