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
			       {name: 'writeDate'},
			       {name: 'documentNum'},
			       {name: 'documentType'},
			       {name: 'writerPeopleCode'},
			       {name: 'writePeople'},
			       {name: 'writerDepartmentCode'},
			       {name: 'writeDepartmentName'},
			       {name: 'writePeopleTel'},
			       {name: 'documentState'},
			       {name: 'categoryName'},
			       {name: 'projectName'},
			       {name: 'nextPerson'},
			       {name: 'submitState'},
			       {name: 'auditState'},
			       {name: 'documentState'},
			       {name: 'totalMoney', type: 'int'},
			       {name: 'remark'},
			       {name: 'id'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'budget/otherExpenseDocument/queryPersonal.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					//下拉树有bug
					var categoryCode = Ext.getCmp("searchFrom").getForm().findField("categoryCode").getValue();
					if(Ext.isEmpty(categoryCode)) {
						baseParams.categoryCode = "";
					}
					store.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true, locked:true, align:"center"}),
	        {header: "<center>单据编号</center>", width: 120, sortable: true, dataIndex: 'documentNum', locked:true, renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>提交状态</center>", width: 60, sortable: true, dataIndex: 'submitState',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>审核状态</center>", width: 60, sortable: true, dataIndex: 'auditState',renderer:function(v){
	      		  if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>单据所在位置</center>", width: 120, sortable: true, dataIndex: 'nextPerson',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>单据类型</center>", width: 80, sortable: true, dataIndex: 'documentType',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>编撰人</center>", width: 60, sortable: true, dataIndex: 'writePeople',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>编撰部门</center>", width: 100, sortable: true, dataIndex: 'writeDepartmentName',renderer:function(v){
	    		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	       }, align:"center"},
	        {header: "<center>编撰日期</center>", width: 80, sortable: true, dataIndex: 'writeDate',renderer:function(v){
	     		 if(v=='null'){
	       			return '';
	       		}else{
	       			return v;
	       		}
	        }, align:"center"},
	        {header: "<center>项目分类</center>", width: 100, sortable: true, dataIndex: 'categoryName',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>项目名称</center>", width: 100, sortable: true, dataIndex: 'projectName',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>合计金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'totalMoney', format: '0.00', align:"right"},
	       {header: "<center>备注</center>", width: 135, sortable: true, dataIndex: 'remark',renderer:function(v){
	     		if(v=='null' || v == ""){
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