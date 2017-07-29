mcirowater=Ext.extend(mcirowaterUi,{
	initComponent: function() {
		mcirowater.superclass.initComponent.call(this);
		 //对每一个组件加事件
		Ext.getCmp("acceptIcon").on("click",function(){
			 var grid=Ext.getCmp("mcirowaterGrid");
			 fun_plsp(grid,1,"通过");
		});
		Ext.getCmp("returnIcon").on("click",function(){
			 var grid=Ext.getCmp("mcirowaterGrid");
			 fun_plsp(grid,2,"驳回");
		});
		
		Ext.getCmp("resetButton").on("click",function(){
			var searchForm = Ext.getCmp("searchForm").getForm();
			searchForm.reset();
		});
		
		
		Ext.getCmp("exportexcelButton").on("click",function(){
			window.location.href=ctx+'/wechat/mcirowater/exportPersonExcel.do';
		});
		Ext.getCmp("queryButton").on("click",function(){
			var searchForm = Ext.getCmp("searchForm").getForm();
			 var shequ=searchForm.findField("shequ").getValue();
			 var startTime=searchForm.findField("startTime").getValue();
			 var endTime=searchForm.findField("endTime").getValue();
			 var state=searchForm.findField("state").getValue();
			 Ext.getCmp("mcirowaterGrid").getStore().baseParams={start: 0, limit: pageSize,shequ:shequ,startTime:startTime,endTime:endTime,state:state};
			 Ext.getCmp("mcirowaterGrid").getStore().load();
		});
		shtg=function (id){
			Ext.Msg.confirm("系统提示","确定要通过？",function(btn){
				 if(btn=="yes"){
					 fun_sp(id,1,"审批已通过");
				}
			  },this);
		} 
		shbh=function (id){
			Ext.Msg.confirm("系统提示","确定要驳回？",function(btn){
				 if(btn=="yes"){
					 fun_sp(id,2,"审批不通过");
				}
			  },this);
		}
	}
	
});

function fun_sp(id,type,msg){
	Ext.Ajax.request({
		url:ctx+"/wechat/mcirowater/updatePerson.do",
		params:{id:id,type:type},
		type:'POST',
		success:function(){
			Ext.Msg.alert("系统提示",msg);
			Ext.getCmp("mcirowaterGrid").getStore().load();
		}
	});
}
function fun_plsp(grid,type,msg){
	var ids=new Array();
	var sm=grid.getSelectionModel(); 
	if(sm.getSelected()){
		 var recs=sm.getSelections(); // 把所有选中项放入数组 
		 
		 Ext.Msg.confirm("系统提示","确定要批量"+msg+"？",function(btn){
		 if(btn=="yes"){
		 	for(var i=0;i<recs.length;i++){ 
		 		 if(recs[i].get("examine")=="2"||recs[i].get("examine")=="1"){
		 			Ext.MessageBox.alert("系统提示", "请选择未审批的活动！");
		 			return;
		 		 }
				 ids.push(recs[i].get("id"));
		 	}
		 	fun_sp(ids,type,"审批"+msg+"已完成!");
		}
	  },this);
	}else{
			Ext.MessageBox.alert("系统提示", "请选择需要"+msg+"活动！"); 
	}
}