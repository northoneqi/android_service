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
	 			 if(formPanel.findField("budgetDepartmentCode").getValue()!=""){
	 				 condition+="&budgetDepartmentCode="+baseParams.budgetDepartmentCode;
	 			 }
	 			 if(formPanel.findField("startDate").getValue()!=""){
	 				 condition+="&startDate="+baseParams.startDate;
	 			 }
	 			 if(formPanel.findField("endDate").getValue()!=""){
	 				 condition+="&endDate="+baseParams.endDate;
	 			 }
	 			 printReport("213", "query_department",condition);
			 });
		 }
		 
	}
});


function showDetail(departmentCode){
	if(Ext.isEmpty(Ext.getDom("budgetContainer"))) {
		$("#myBody").append("<div id='budgetContainer' style='min-width: 400px; min-height: 430px; margin: 0 auto'></div>");
	}
	
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
	
	Ext.getCmp("budgetBillDetail").getStore().load({
		params:{departmentCode: departmentCode},
		callback: function(r){
			var datas_budget = [];
			var datas_add = [];
			var datas_last = [];
			var datas_grand = [];
			var categories = [];
			for(var i = 0; i < r.length; i++){
				var record = r[i];
				categories.push(record.data.budgetTargetName);
				datas_budget.push(record.data.budgetMoney);
				datas_add.push(record.data.addMoney);
				datas_last.push(record.data.lastMoney);
				datas_grand.push(record.data.grandTotalMoney);
			}
			
			$('#budgetContainer').highcharts({
				chart: {                                                           
		            type: 'line'                                                    
		        },
		        credits: {
		        	enabled: false
		        },
		        title: {
		            text: '预算指标明细',
		            x: -20
		        },
		        xAxis: {
		            categories: categories
		        },
		        yAxis: {
		            title: {
		                text: '金额'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: '元'
		        },
		        legend: {
		            layout: 'horizontal',
		            align: 'center',
		            verticalAlign: 'bottom',
		            borderWidth: 0
		        },
		        plotOptions: {
		            column: {
		        		minPointLength: 10,
		        		pointWidth: 20,
		        		pointPadding: 0.5,
		                dataLabels: {                                              
		                    enabled: true                                          
		                }                                                          
		            }                                                              
		        }, 
		        series: [{
		        	name: '预算费',
		        	data: datas_budget
		        },{
		        	name: '追加预算',
		        	data: datas_add
		        },{
		        	name: '剩余预算',
		        	data: datas_last
		        },{
		        	name: '累计预算',
		        	data: datas_grand
		        }]
		    });
		}
    });
	
	Ext.getCmp("reimburseBillDetail").getStore().load({
		params:{departmentCode: departmentCode},
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
		params:{departmentCode: departmentCode},
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
		params:{departmentCode: departmentCode},
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