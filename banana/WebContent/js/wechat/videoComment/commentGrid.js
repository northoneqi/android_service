commentoGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'commentoGrid',
	//frame:true,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border:false,
    margins:'2 2 0 0 ',
	anchor : '100%',
    initComponent: function() {
		//var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			        {name: 'comid',type: 'int'},
			        {name: 'hostid',type: 'string'},
			        {name: 'comment',type: 'string'},
			        {name: 'recomid',type: 'int'},
				    {name: 'comtime',type: 'string'}, 
	    			{name: 'delflag',type: 'string'}, 
		    		{name: 'name',type: 'string'},
		    		{name: 'account',type: 'string'},
		    		{name: 'accounttype',type: 'string'},
		    		{name: 'memo',type: 'string'},
		    		{name: 'dicid',type: 'int'},
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
		    proxy: new Ext.data.HttpProxy ({ 
				url:ctx+"/wechat/video/queryComment.do"
			 })      
	 	});
		store.load({params: {start: 0, limit: pageSize,vid:vid}});
		
		var columns=[
	  	 	//sm,
	  	 	//new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50, align:"center"}),
	        
	        {header: "<center><span style='font-weight:bold'>大众评论</span></center>", align:'left',width: 360,height:170,sortable: true, dataIndex: 'comment', renderer:function(c,medel,record,row,col,data){
	      		if(c=='null'){
	      			return '';
	      		}else{
	      			return   "<div style=\"width:340px;padding:0px;margin:0px;border:0px solid;white-space:normal; word-break:break-all;overflow:hidden;line-height:20px;\"><span style=\"color:red\">"+record.get("name")+"</span> ："+c+"<br>&nbsp;";+"</div>";
	      			}
	        }}
	  	 	
	  	 	
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "commentoGrid";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		commentoGrid.superclass.initComponent.call(this);
	}
});