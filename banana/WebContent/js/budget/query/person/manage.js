manage = Ext.extend(manageUi,{
	initComponent: function() {
		 manage.superclass.initComponent.call(this);
		 //导出excel
		 if(!Ext.isEmpty(Ext.getCmp("exportButton"))) {
			 Ext.getCmp("exportButton").on("click", function(){
				 alert("待完成");
			 });
		 }
		 
		 //打印预览
		 if(!Ext.isEmpty(Ext.getCmp("printButton"))){
			 Ext.getCmp("printButton").on("click", function(){
	 			 var condition="";
	 			 var formPanel=Ext.getCmp("searchFrom").getForm();
	 			 var baseParams=Ext.getCmp("searchFrom").getForm().getValues();
	 		
	 			 if(formPanel.findField("departmentCode").getValue()!=""){
	 				 condition+="&departmentCode="+baseParams.departmentCode;
	 			 }
	 			 if(formPanel.findField("startDate").getValue()!=""){
	 				 condition+="&startDate="+baseParams.startDate;
	 			 }
	 			 if(formPanel.findField("peopleName").getValue()!=""){
	 				 condition+="&peopleName="+baseParams.peopleName;
	 			 }
	 			 if(formPanel.findField("endDate").getValue()!=""){
	 				 condition+="&endDate="+baseParams.endDate;
	 			 }
	 			 printReport("213", "query_person",condition);
			 });
		 }
		 
	}
});


function showDetail(peopleCode){
	if(Ext.isEmpty(Ext.getDom("remburseContainer"))) {
		$("#myBody").append("<div id='remburseContainer' style='width: 400px; min-height: 430px; margin: 0 auto'></div>");
	}
	
	if(Ext.isEmpty(Ext.getDom("borrowContainer"))) {
		$("#myBody").append("<div id='borrowContainer' style='width: 400px; min-height: 430px; margin: 0 auto'></div>");
	}
	
	if(Ext.isEmpty(Ext.getDom("paymentContainer"))) {
		$("#myBody").append("<div id='paymentContainer' style='width: 400px; min-height: 430px; margin: 0 auto'></div>");
	}
	
	new DetailWindow().show();
	
	Ext.getCmp("reimburseBillDetail").getStore().load({
		params:{peopleCode: peopleCode},
		callback: function(r){
			var datas = [];
			for(var i = 0; i < r.length; i++){
				var record = r[i];
				var data = [];
				data.push(record.data.costName);
				data.push(record.data.cost);
				datas.push(data);
			}
			$('#remburseContainer').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        credits: {
		        	enabled: false
		        },
		        title: {
		            text: '报销比例统计表'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '所占比例',
		            data: datas
		        }]
		    });
		}
	});
	
	
	Ext.getCmp("borrowBillDetailGrid").getStore().load({
		params:{peopleCode: peopleCode},
		callback: function(r){
			var datas = [];
			for(var i = 0; i < r.length; i++){
				var record = r[i];
				var data = [];
				data.push(record.data.costName);
				data.push(record.data.cost);
				datas.push(data);
			}
			$('#borrowContainer').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        credits: {
		        	enabled: false
		        },
		        title: {
		            text: '借款比例统计表'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '所占比例',
		            data: datas
		        }]
		    });
		}
	});
	
	Ext.getCmp("paymentBillDetailGrid").getStore().load({
		params:{peopleCode: peopleCode},
		callback: function(r){
			var datas = [];
			for(var i = 0; i < r.length; i++){
				var record = r[i];
				var data = [];
				data.push(record.data.costName);
				data.push(record.data.cost);
				datas.push(data);
			}
			$('#paymentContainer').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        credits: {
		        	enabled: false
		        },
		        title: {
		            text: '付款比例统计表'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '所占比例',
		            data: datas
		        }]
		    });
		}
	});
    
}