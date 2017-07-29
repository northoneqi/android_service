 var sm=new Ext.grid.CheckboxSelectionModel({singleSelect :false});
 store = new Ext.data.Store({
	   reader:new Ext.data.JsonReader({
	   root:"root",//从数据库中读取的总记录数          
	   totalProperty: 'total' ,
	   fields: [
		   { name: 'id' },{ name: 'note' },
		   { name: 'customer' }
	   ]
	  }),
	  proxy: new Ext.data.HttpProxy ({ 
		 url:ctx+"/testtt/zhaoxh/queryDate.do"
	  })      
}); 
var columns=[sm,
new Ext.grid.RowNumberer({header:"序号",sortable : true,width:50,align:"center"}),
		 {header: "ID", dataIndex: 'id',hidden:true},
		 {header: "姓名",dataIndex: 'customer',renderer:function(value){
			 return '<div ext:qtitle="" ext:qtip="' + value + '">'+ value +'</div>';}},
         {header: "备注",sortable: true, dataIndex: 'note',renderer:function(value){
			 return '<div ext:qtitle="" ext:qtip="' + value + '">'+ value +'</div>';}}
    ];	
	grid=new Ext.grid.GridPanel({
	 	loadMask: {msg:'加载数据中,请等待......'},//显示等待数据加载（loading）图标
	 	autoScroll:true,
	 	columnLines: true,
	 	loadMask: true,
		displayInfo:true,
	 	border:false,
	 	margins:'2 2 0 0 ',
   		/**viewConfig : { forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。},
		anchor : '100%',*/
	 	cm:new Ext.grid.ColumnModel(columns),
	 	sm:sm,
	 	store:store,
	 	listeners:{  
            'rowdblclick' : function(grid, rowIndex, e){  
                var rowid = getCheckRowId(3);
				 var c = rowid.split("@");
				 if(c[0]!=""){
					 companyCode = "";
					 var win = new peopleChangEditWindow();
					 win.title = "职工职级变化登记表<span style='color:red'>[查看]</span>";
					 win.show();
					 Ext.getCmp("id").setValue(c[0]);
					 loadForm(c[0]);//加载form
				 	 Ext.getCmp("saveButton").hide();//隐藏按钮
				 }else{
					 MsgTip.msg('系统提示', '请选择您要查看的记录',true);
				 }
				 /**取得取消按钮 定义返回事件**/
				 var cencelButton = Ext.getCmp("cencelButton");
					cencelButton.on("click",function(){
						win.close();
				});
             }
        }
	});
	
function rendStatus(v){
	if(v=="STATUS_XZ"){
		return '<div style="color:#3B65B0" ext:qtitle="" ext:qtip="新增">新增</div>';   
	}if(v=="STATUS_XG"){
		return '<div style="color:#DA5553" ext:qtitle="" ext:qtip="修改">修改</div>';   
	}if(v=="STATUS_TJ"){
		return '<div style="color:green" ext:qtitle="" ext:qtip="提交">提交</div>';   
	}if(v=="STATUS_BH"){
		return '<div style="color:red" ext:qtitle="" ext:qtip="驳回">驳回</div>';   
	}if(v=="STATUS_GD"){
		return '<div style="color:blue" ext:qtitle="" ext:qtip="归档">归档</div>';   
	}else{
		return "";
	}
}