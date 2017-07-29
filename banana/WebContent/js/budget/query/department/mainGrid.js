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
			       {name: 'projectName'},
			       {name: 'budgetMoney'},
			       {name: 'addMoney'},
			       {name: 'lastMoney'},
			       {name: 'grandTotalMoney'},
			       {name: 'reimburseMoney'},
			       {name: 'borrowMoney'},
			       {name: 'paymentMoney'}
			    ]
			}),
			pageSize: pageSize,
			 //获取数据源(该方法返回一个json格式的数据源)
			 proxy: new Ext.data.HttpProxy ({ 
				url:'budget/query/queryDepartment.do'
			 })      
	 	});
		store.on({
			"beforeload": function(store){
				var baseParams = {pageSize:pageSize};
				if(!Ext.isEmpty(Ext.getCmp("searchFrom"))){
					baseParams = Ext.getCmp("searchFrom").getForm().getValues();
					//下拉树有bug
					var budgetDepartmentCode = Ext.getCmp("searchFrom").getForm().findField("budgetDepartmentCode").getValue();
					if(Ext.isEmpty(budgetDepartmentCode)) {
						baseParams.budgetDepartmentCode = "";
					}
					store.baseParams = baseParams;
				}
			},
			"load": function(){
				var mygrid = Ext.getCmp('mainGrid');

               // gridSpan(mygrid, 'row',"departmentName",  '1px solid #9BCEDB');
			}
		});
		store.load({params: {start: 0, limit: pageSize}});
		
		var columns=[
	  	 	//sm,
	  	 	new Ext.grid.RowNumberer({header:"<center>序号</center>",width:50,sortable : true, locked:true, align:"center"}),
	        {header: "<center>部门名称</center>", width: 120, sortable: true, dataIndex: 'departmentName', locked: true, renderer:function(v, metaData, record){
	  	 		if(v=='null'){
	      			return '';
	      		}else{
	      			return "<a href='javascript:void(0);' onclick='showDetail(\""+record.data.departmentCode+"\")'>"+v+"</a>";
	      		}
	        }, align:"center"},
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


//地市名称相同的列合并
function gridSpan(grid, rowOrCol,colName, borderStyle) {
    var array1 = new Array();
    var count1 = 0;
    var count2 = 0;
    var index1 = 0;
    var index2 = 0;
    var aRow = undefined;
    var preValue = undefined;
    var firstSameCell = 0;
    var allRecs = grid.getStore().getRange();
    if (rowOrCol == "row") {
        count1 = grid.getColumnModel().getColumnCount();
        count2 = grid.getStore().getCount();
    } else {
        count1 = grid.getStore().getCount();
        count2 = grid.getColumnModel().getColumnCount();
    }
    count1 = 2; // 对第二列合并
    for (i = 1; i < count1; i++) {
        preValue = undefined;
        firstSameCell = 0;
        array1[i] = new Array();
        for (j = 0; j < count2; j++) {
            if (rowOrCol == "row") {
                index1 = j;
                index2 = i;
            } else {
                index1 = i;
                index2 = j;
            } 
            if (allRecs[index1].get(colName) == preValue) {
                allRecs[index1].set(colName, "&nbsp;");
                array1[i].push(j);
                //alert(i + "\r\n"+j);
                if (j == count2 - 1) {
                    var index = firstSameCell + Math.round((j + 1 - firstSameCell) / 2 - 1);
                    if (rowOrCol == "row") {
                        allRecs[index].set(colName, preValue);
                    } else {
                        allRecs[index1].set(grid.getColumnModel().getColumnId(index), preValue);
                    }
                }
            } else {
                if (j != 0) {
                    var index = firstSameCell + Math.round((j + 1 - firstSameCell) / 2 - 1);
                    if (rowOrCol == "row") {
                        allRecs[index].set(colName, preValue);
                    } else {
                        allRecs[index1].set(grid.getColumnModel().getColumnId(index), preValue);
                    }
                }
                firstSameCell = j;
                preValue = allRecs[index1].get(colName);
                allRecs[index1].set(colName, "&nbsp;");
                if (j == count2 - 1) {
                    allRecs[index1].set(colName, preValue);
                }
            }
        }
    }
    grid.getStore().commitChanges();//添加所有分隔线
    for (i = 0; i < grid.getStore().getCount(); i++) {
        for (j = 0; j < grid.getColumnModel().getColumnCount(); j++) {
            aRow = grid.getView().getCell(i, j);
            aRow.style.borderTop = borderStyle;
            aRow.style.borderLeft = borderStyle;
        }
    }//去除合并的单元格的分隔线
    for (i = 1; i < array1.length; i++) {
        for (j = 0; j < array1[i].length; j++) {
            
            if (rowOrCol == "row") {
                aRow = grid.getView().getCell(array1[i][j], i);
                aRow.style.borderTop = 'none';
            } else {
                aRow = grid.getView().getCell(i, array1[i][j]);
                aRow.style.borderLeft = "none";
            }
        }
    }
}