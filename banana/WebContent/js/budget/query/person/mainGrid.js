mainGrid=Ext.extend(Ext.grid.GridPanel,{
	id: 'mainGrid',
	region: 'center',
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
				   {name: 'departmentCode'},
			       {name: 'departmentName'},
			       {name: 'peopleCode'},
			       {name: 'peopleName'},
			       {name: 'reimburseMoney'},
			       {name: 'borrowMoney'},
			       {name: 'paymentMoney'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'budget/query/queryPerson.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					//下拉树有bug
					var departmentCode = Ext.getCmp("searchFrom").getForm().findField("departmentCode").getValue();
					if(Ext.isEmpty(departmentCode)) {
						baseParams.departmentCode = "";
					}
					store.baseParams = baseParams;
				}
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
	
		var columns=[
	  	 	//sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true, locked:true, align:"center"}),
	        {header: "<center>人员</center>", width: 80, sortable: true, dataIndex: 'peopleName', locked: true, renderer:function(v, metaData, record){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return "<a href='javascript:void(0);' onclick='showDetail(\""+record.data.peopleCode+"\")'>"+v+"</a>";
	      		}
	        }, align:"center"},
	  	 	{header: "<center>所属部门</center>", width: 120, sortable: true, dataIndex: 'departmentName',renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>报销金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'reimburseMoney', format: '0.00', align:"right"},
	        {header: "<center>借用金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'borrowMoney', format: '0.00', align:"right"},
	        {header: "<center>付款金额</center>", width: 100, sortable: true,xtype: 'numbercolumn',
	        	dataIndex: 'paymentMoney', format: '0.00', align:"right"}
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "queryId";
		this.sm=sm;
		this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});