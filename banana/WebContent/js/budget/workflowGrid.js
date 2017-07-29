WorkFlowGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'workflowGrid',
	frame: true,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll: true,
	columnLines: true,
   // loadMask: true,
    border: true,
    margins:'2 2 0 0 ',
	anchor: '100%',
	height: 150,
    initComponent: function() {
		store = new Ext.data.Store({
		   //读取数据源用json方法(三种方法1.读取json用JsonReader,2读取数组使用ArrayReader3.读取XML用XmlReader)     
		   reader:new Ext.data.JsonReader({
			   root:"root",//从数据库中读取的总记录数          
			   totalProperty: 'total',//要读取出来的字段         
			   fields:[	
			       {name: 'billId'},
			       {name: 'billType'},
			       {name: 'sendFromPeopleCode'},
			       {name: 'sendFromPeople'},
			       {name: 'sendFromDepartmentCode'},
			       {name: 'sendFromDepartmentName'},
			       {name: 'sendToPeopleCode'},
			       {name: 'sendToPeople'},
			       {name: 'sendToDepartmentCode'},
			       {name: 'sendToDepartmentName'},
			       {name: 'handTime'},
			       {name: 'createTime'},
			       {name: 'opinion'},
			       {name: 'auditState'},
			       {name: 'id'}
			    ]
			}),
			autoLoad: false,
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'budget/workFlow/getAuditWorkFlow.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					store.baseParams = Ext.getCmp("searchFrom").getForm().getValues();
				}
			}
		});

		var columns=[
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true,align:"center"}),
	        {header: "<center>审核状态</center>", width: 60, sortable: true, dataIndex: 'auditState',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>提交人</center>", width: 120, sortable: true, dataIndex: 'sendFromPeople',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>审核人</center>", width: 80, sortable: true, dataIndex: 'sendToPeople',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>审核时间</center>", width: 150, sortable: true, dataIndex: 'createTime',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>审核意见</center>", width: 300, sortable: true, dataIndex: 'opinion',renderer:function(v){
	    		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	       }, align:"center"}
	    ];	
		this.cm=new Ext.grid.ColumnModel(columns),
		this.store=store;
		WorkFlowGrid.superclass.initComponent.call(this);
	}
});