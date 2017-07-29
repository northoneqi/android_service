videoGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'videoGridId',
	//frame:true,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border:false,
    margins:'2 2 0 0 ',
	anchor : '100%',
    initComponent: function() {
		var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			        {name: 'vid',type: 'int'},
			        {name: 'aid',type: 'string'},
			        {name: 'createTime',type: 'string'},
			        {name: 'title',type: 'string'},
				    {name: 'videoUil',type: 'string'}, 
	    			{name: 'author',type: 'string'}, 
		    		{name: 'content',type: 'string'},
		    		{name: 'tel',type: 'string'},
		    		{name: 'image',type: 'string'},
		    		{name: 'hostId',type: 'string'},
		    		{name: 'state',type: 'string'},
		    		{name: 'createPerson',type: 'string'},
		    		{name: 'zan',type: 'string'},
		    		{name: 'shi',type: 'string'},
		    		{name: 'zp',type: 'string'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
		    proxy: new Ext.data.HttpProxy ({ 
				url:ctx+"/wechat/video/queryVideoDetail.do"
			 })      
	 	});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50, align:"center"}),
	        {header: "<center>视频标题</center>", width: 320, sortable: true, dataIndex: 'title', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>作者</center>", width: 100, sortable: true, dataIndex: 'author',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>视频状态</center>", width: 100, sortable: true, dataIndex: 'state',renderer:function(v){
	      		  if(v=='0')return '<span style="color:#000093">海选</span>';
	      		  if(v=='1')return '<span style="color:#006000">入围</span>';
	      		  if(v=='2')return '<span style="color:#FF0000">获奖</span>';
	        }, align:"center"},
	        {header: "<center>赞</center>", width: 60, sortable: true, dataIndex: 'zan',renderer:function(v){
	      		  return '<span style="color:#000093">'+v+'</span>';
	        }, align:"center"},
	        {header: "<center>屎</center>", width: 60, sortable: true, dataIndex: 'shi',renderer:function(v){
	      		return '<span style="color:#006000">'+v+'</span>';
	        }, align:"center"},
	        {header: "<center>评</center>", width: 60, sortable: true, dataIndex: 'zp',renderer:function(v){
	      		 return '<span style="color:#CE0000">'+v+'</span>';;
	        }, align:"center"}
	        /*{header: "<center>详情</center>", width: 80, sortable: true, dataIndex: 'note',renderer:function(v){
	     		 return "<img src='"+ctx+"/images/ext/zoom.png'/>";
	        }, align:"center"}*/
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "videoGridId";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		videoGrid.superclass.initComponent.call(this);
	}
});
