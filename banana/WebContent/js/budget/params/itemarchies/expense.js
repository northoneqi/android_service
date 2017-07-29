ExpenseGrid = Ext.extend(Ext.grid.GridPanel, {
	id: 'expenseGrid',
	frame: false,
	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	autoScroll:true,
	columnLines: true,
    loadMask: true,
    border: false,
    height: 150,
    margins:'2 2 0 0 ',
    region: 'center',
    viewConfig : {
      // forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
    },
    initComponent: function() {
    	var BillDetail = Ext.data.Record.create([
            {name: 'id'},
         	{name: 'projectSpending',type: 'string'},
         	{name: 'proportion',type: 'string'}, 
         	{name: 'price',type: 'int'},
         	{name: 'categories',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/spending/getDetails.do");
       var billDetailEditor = new Ext.ux.grid.RowEditor({
             saveText: '保存',
             cancelText: '取消',
             commitChangesText: '您需要先保存或者取消',
             errorText: '错误'
        });
	    this.store = billDetailStore;
	    var summary = new Ext.ux.grid.GridSummary();
	    this.plugins = [billDetailEditor, summary];
	    this.view = new Ext.grid.GroupingView({
	        markDirty: false
	    });
	    this.tbar = [{
	        text: '增加',
	        iconCls:'addIcon',
	        handler: function(){
	            var e = new BillDetail({});
	            billDetailEditor.stopEditing();
	            billDetailStore.insert(billDetailStore.getCount(), e);
	            Ext.getCmp("expenseGrid").getView().refresh();
	            Ext.getCmp("expenseGrid").getSelectionModel().selectRow(billDetailStore.getCount());
	            billDetailEditor.startEditing(billDetailStore.getCount()-1);
	        }
	    }, '-', {
	        text: '删除',
	        iconCls:'deleteIcon',
	        handler: function(){
	            billDetailEditor.stopEditing();
	            var s = Ext.getCmp("expenseGrid").getSelectionModel().getSelections();
	            for(var i = 0, r; r = s[i]; i++){
	            	billDetailStore.remove(r);
	            }
	        }
	    }, '-'];
	
	    var renderSummary = function(o, cs, cm) {
	        return '合计：';
	    }
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	    {
	        header: '<center>支出项目</center>',
	        dataIndex: 'projectSpending',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: false
	        },
	        summaryRenderer: renderSummary
	    },{
	        header: '<center>占用比例</center>',
	        dataIndex: 'proportion',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: false
	        }
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'price',
	        width: 120,
	        sortable: true,
	        format: '0.00',
	        align: 'right',
	        editor: {
	        	xtype: 'numberfield',
	        	submitValue: false,
	            allowBlank: false
	        },
	        summaryRenderer: Ext.util.Format.numberRenderer('0.00')
	    },{
	        header: '<center>预算指标类别</center>',
	        dataIndex: 'categories',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        align: 'center',
	        width: 200,
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    }];
	    
	    ExpenseGrid.superclass.initComponent.call(this);
    }
});
