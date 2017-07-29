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
            {name: 'costCode',type: 'string'},
         	{name: 'costName',type: 'string'},
         	{name: 'digest',type: 'string'}, 
         	{name: 'cost',type: 'int'},
         	{name: 'clearingSubject',type: 'string'},
         	{name: 'clearingSubjectCode',type: 'string'},
         	{name: 'partySubject',type: 'string'},
         	{name: 'partySubjectCode',type: 'string'},
         	{name: 'billNo',type: 'string'},
         	{name: 'cashFlow',type: 'string'},
         	{name: 'cashFlowCode',type: 'string'},
         	{name: 'remark',type: 'string'}
       ]);

       var billDetailStore = createStore(BillDetail, false, {}, "budget/paymentDocumentDetail/getDetails.do");
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
	   /* this.tbar = [{
	        text: '增加',
	        iconCls:'addIcon',
	        handler: function(){
	            var e = new BillDetail({});
	            billDetailEditor.stopEditing();
	            billDetailStore.insert(billDetailStore.getCount(), e);
	            Ext.getCmp("billDetailGrid").getView().refresh();
	            Ext.getCmp("billDetailGrid").getSelectionModel().selectRow(billDetailStore.getCount());
	            billDetailEditor.startEditing(billDetailStore.getCount()-1);
	        }
	    }, '-', {
	        text: '删除',
	        iconCls:'deleteIcon',
	        handler: function(){
	            billDetailEditor.stopEditing();
	            var s = Ext.getCmp("billDetailGrid").getSelectionModel().getSelections();
	            for(var i = 0, r; r = s[i]; i++){
	            	billDetailStore.remove(r);
	            }
	        }
	    }, '-'];*/
	   
	    var partySubject = Ext.ux.form.ComboBox({
 			url: 'budget/accountingSubject/getCombo.do',
 			fields: [{name: 'code'}, {name: 'name'}],
 			name: 'partySubject',
    		hiddenName: 'partySubjectCode',
    		listeners: [{
    			"change": function(field, value, oldValue){
		 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
		 			record.partySubjectCode = value;
		 		}
    		}]
 		})
 		
 		partySubject.on("change", function(field, value, oldValue){
 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
 			record.data.partySubjectCode = value;
 		});
	    
	    var cashFlow = Ext.ux.form.ComboBox({
 			url: 'budget/flowsController/getCombo.do',
 			fields: [{name: 'flowsCode'}, {name: 'flowsName'}],
 			name: 'cashFlow',
    		hiddenName: 'cashFlowCode',
    		listeners: [{
    			"change": function(field, value, oldValue){
		 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
		 			record.cashFlowCode = value;
		 		}
    		}]
 		})
 		
 		cashFlow.on("change", function(field, value, oldValue){
 			var record = billDetailStore.getAt(billDetailEditor.getEditingRow());
 			record.data.cashFlowCode = value;
 		});
	    
	    var renderSummary = function(o, cs, cm) {
	        return '合计：';
	    }
	    
	    this.columns = [
	    new Ext.grid.RowNumberer(),
	   {
	        header: '<center>费用项名称</center>',
	        dataIndex: 'costName',
	        width: 120,
	        align: 'center',
	        summaryRenderer: renderSummary
	    },{
	    	xtype: 'numbercolumn',
	        header: '<center>金额</center>',
	        dataIndex: 'cost',
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
	        header: '<center>结算科目</center>',
	        dataIndex: 'clearingSubject',
	        width: 120,
	        sortable: true,
	        align: 'center'
	    },{
	        header: '<center>对方科目</center>',
	        dataIndex: 'partySubject',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        editor: partySubject
	    },{
	        header: '<center>票据号</center>',
	        dataIndex: 'billNo',
	        width: 120,
	        sortable: true,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    },{
	        header: '<center>现金流量</center>',
	        dataIndex: 'cashFlow',
	        width: 120,
	        sortable: true,
	        align: 'right',
	        editor: cashFlow
	    },{
	        header: '备注',
	        dataIndex: 'remark',
	        width: 200,
	        align: 'center',
	        editor: {
	        	xtype: 'textfield',
	        	submitValue: false,
	            allowBlank: true
	        }
	    }];
	    
	    BillDetailGrid.superclass.initComponent.call(this);
    }
});
