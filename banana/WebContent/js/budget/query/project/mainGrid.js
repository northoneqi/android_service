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
			       {name: 'projectCode'},
			       {name: 'projectName'},
			       {name: 'projectStatus'},
			       {name: 'categoryName'},
			       {name: 'contractMoney'},
			       {name: 'costMoney'},
			       {name: 'budgetMoney'},
			       {name: 'addMoney'},
			       {name: 'lastMoney'},
			       {name: 'grandTotalMoney'},
			       {name: 'reimburseMoney'},
			       {name: 'borrowMoney'},
			       {name: 'paymentMoney'},
			       {name: 'factMoney'},
			       {name: 'otherExpenseMoney'},
			       {name: 'receiptMoney'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'budget/query/queryProject.do'
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
	  	 	//sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true, locked: true, align:"center"}),
	        {header: "<center>项目编号</center>", width: 120, sortable: true, dataIndex: 'projectCode', locked: true, renderer:function(v){
	      		if(v=='null'){
	      			return '';
	      		}else{
	      			return "<a href='javascript:void(0);' onclick='showDetail(\""+v+"\")'>"+v+"</a>";
	      		}
	        }, align:"center"},
	        {header: "<center>项目名称</center>", width: 120, sortable: true, dataIndex: 'projectName', locked:true, renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>项目分类</center>", width: 80, sortable: true, dataIndex: 'categoryName', locked: true, renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>立项状态</center>", width: 80, sortable: true, dataIndex: 'projectStatus', locked: true, renderer:function(v){
	      		 if(v=='null'){
	      			return '';
	      		}else{
	      			return v;
	      		}
	        }, align:"center"},
	        {header: "<center>合同金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'contractMoney', format: '0.00', align:"right"},
	        {header: "<center>成本估算</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'costMoney', format: '0.00', align:"right"},
	        {header: "<center>预算金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'budgetMoney', format: '0.00', align:"right"},
	        {header: "<center>追加预算</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'addMoney', format: '0.00', align:"right"},
	        {header: "<center>剩余预算</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'lastMoney', format: '0.00', align:"right"},
	        {header: "<center>累计预算</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'grandTotalMoney', format: '0.00', align:"right"},
	        {header: "<center>报销金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'reimburseMoney', format: '0.00', align:"right"},
	        {header: "<center>借用金额</center>", width: 100, sortable: true, xtype: 'numbercolumn',
	        	dataIndex: 'borrowMoney', format: '0.00', align:"right"},
	        {header: "<center>付款金额</center>", width: 100, sortable: true,xtype: 'numbercolumn',
	        	dataIndex: 'paymentMoney', format: '0.00', align:"right"},
	        {header: "<center>支付金额</center>", width: 100, sortable: true,xtype: 'numbercolumn',
	        	dataIndex: 'factMoney', format: '0.00', align:"right"},
	        {header: "<center>其他费用</center>", width: 100, sortable: true,xtype: 'numbercolumn',
	        	dataIndex: 'otherExpenseMoney', format: '0.00', align:"right"},
	        {header: "<center>收款金额</center>", width: 100, sortable: true,xtype: 'numbercolumn',
	        	dataIndex: 'receiptMoney', format: '0.00', align:"right"}
	    ];	
		this.colModel = new Ext.ux.grid.LockingColumnModel(columns);
		this.store=store;
		this.id = "queryId";
		this.view = new Ext.ux.grid.LockingGridView();
		mainGrid.superclass.initComponent.call(this);
	}
});