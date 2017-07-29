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
         	{name: 'gatheringStage',type: 'string'},
         	{name: 'startDate',type: 'string'}, 
         	{name: 'receivablesMoney',type: 'int'},
         	{name: 'digest',type: 'string'},
         	{name: 'remark',type: 'string'},
       ]);
    	
       var billDetailStore = createStore(BillDetail, false, {}, "budget/receiptDocumentDetail/getDetails.do");
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
	    {
	        header: '<center>收款阶段</center>',
	        dataIndex: 'gatheringStage',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'datecolumn',
	        header: '<center>起始日期</center>',
	        dataIndex: 'startDate',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        format: 'Y-m-d'
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>收款金额</center>',
	        dataIndex: 'receivablesMoney',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	        header: '<center>摘要</center>',
	        dataIndex: 'digest',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200
	    }];
	    
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
