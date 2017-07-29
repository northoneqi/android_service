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
			        {name: 'aid',type: 'string'},
			        {name: 'ctTime',type: 'string'},
			        {name: 'title',type: 'string'},
				    {name: 'videoUil',type: 'string'}, 
	    			{name: 'year',type: 'string'}, 
		    		{name: 'content',type: 'string'},
		    		{name: 'bgTime',type: 'string'},
		    		{name: 'edTime',type: 'string'},
		    		{name: 'state',type: 'int'},
		    		{name: 'createPerson',type: 'string'},
		    		{name: 'activeflag',type: 'string'}
		    		
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
		    proxy: new Ext.data.HttpProxy ({ 
				url:ctx+"/wechat/video/queryVideo.do"
			 })      
	 	});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center><span style='font-weight:bold'>序号</span></center>",width:50, align:"center"}),
	        {header: "<center><span style='font-weight:bold'>活动标题</span></span></center>", width: 300, sortable: true, dataIndex: 'title', renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>开始时间</span></center>", width: 150, sortable: true, dataIndex: 'bgTime',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else if(v.length>=10){
	      			return v.substr(0,4)+"年"+v.substr(5,2)+"月"+v.substr(8,2)+"日";
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>结束时间</span></center>", width: 150, sortable: true, dataIndex: 'edTime',renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else if(v.length>=10){
	      			return v.substr(0,4)+"年"+v.substr(5,2)+"月"+v.substr(8,2)+"日";
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>活动状态</span></center>", width: 100, sortable: true, dataIndex: 'state',renderer:function(v){
	      		  if(v=='1')return '<span style="color:#000093">未开始</span>';
	      		  if(v=='2')return '<span style="color:#006000">进行中</span>';
	      		  if(v=='3')return '<span style="color:#CE0000">已结束</span>';
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>创建时间</span></center>", width: 150, sortable: true, dataIndex: 'ctTime',renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else if(v.length>=10){
	      			return v.substr(0,4)+"年"+v.substr(5,2)+"月"+v.substr(8,2)+"日";
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>创建人</span></center>", width: 100, sortable: true, dataIndex: 'createPerson',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center><span style='font-weight:bold'>指定活动</span></center>", width: 100, sortable: true, dataIndex: 'activeflag',renderer:function(v){
	      		  if(v=='1')return '<span style="color:#000093">当前活动</span>';
	      		  if(v=='0')return '<span style="color:#006000">非当前活动</span>';
	      		 
	        }, align:"center"}
	        
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "videoGridId";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		videoGrid.superclass.initComponent.call(this);
	}
});