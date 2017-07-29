/**职员工作简历管理**/
BillDetailGrid = Ext.extend(Ext.grid.GridPanel, {
	id: 'billDetailGrid',
	frame: false,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border: false,
    height: 240,
    margins:'2 2 0 0 ',
    region: 'center',
    viewConfig : {
      // forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
    },
    initComponent: function() {
    	var BillDetail = Ext.data.Record.create([
            {name: 'id'},
         	//{name: 'budgetTargetCode',type: 'string'},
         	{name: 'budgetTargetName',type: 'string'}, 
         	{name: 'budgetMoney',type: 'int'},
         	{name: 'addMoney',type: 'int'},
         	{name: 'lastMoney',type: 'int'},
         	{name: 'grandTotalMoney',type: 'int'},
         	{name: 'remark',type: 'string'}
       ]);

        var billDetailStore = createStore(BillDetail, false, {}, "budget/projectBudgetDocumentDetail/getDetails.do");
	    this.store = billDetailStore;
	    var summary = new Ext.ux.grid.GridSummary();
	    this.plugins = [summary];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	
	    var renderSummary = function(o, cs, cm) {
	        return '合计：';
	    }
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	   /* {
	        header: '预算指标编码',
	        dataIndex: 'budgetTargetCode',
	        width: 100,
	        align: 'center'
	    },*/{
	        header: '预算指标',
	        dataIndex: 'budgetTargetName',
	        width: 100,
	        sortable: true,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>预算金额</center>',
	        dataIndex: 'budgetMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>追加预算</center>',
	        dataIndex: 'addMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>剩余预算</center>',
	        dataIndex: 'lastMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>累计预算</center>',
	        dataIndex: 'grandTotalMoney',
	        width: 120,
	        format: '0.00',
	        sortable: true,
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200,
	        align: 'center'
	    }];
	    
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
