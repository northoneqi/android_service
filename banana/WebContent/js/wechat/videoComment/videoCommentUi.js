videoCommentUi=Ext.extend(Ext.Panel,{
	id:'videoCommentUi',
	border:false,
	margins:'1 1 1 1',
	layout:'fit',
	initComponent: function() {
		//state=createCombo("活动状态", "state", [["-1","请选择"],["0","未开始"], ["1","进行中"], ["2","已结束"]]);
		//videoForm=new videoForm();
		// var title="点评视频";
//		 if(videoDetailsId!=null&&videoDetailsId!=""){
//			 title="编辑";
//		 }
		 Ext.applyIf(this,{
			//bodyStyle:'padding-right:80', 
			items:[{
				layout:'fit',
				border:false,
				//frame:true,
				tbar:{height:30,items:['<span style="text-align:center;">' +
											'<img src="'+ctx+'/images/list.png"/>' +
									   '</span>',
									   '<span id="formPanelTitle" style="font-weight:bold;font-size:20px;">'+title+'</span>',
									   				 '->',
													// '-',{text:'保存',iconCls:'saveIcon',id:'saveButton'},
													 '-',{text:'取消',iconCls:'closeIcon',id:'cancleButton'},
													 //'-',{text:'预览',iconCls:'application_view_listIcon',id:'printerIcon'},
													 '-']},
				items:[{
					margins:'1 1 1 1',
					//frame:true,
					layout:'border',
					border:false,
					items:[{
						region:'west',
						width:'70%',
						layout:'border',
						margins:'1 1 1 1',
						border:false,
						items:[{
							region:'north',
							margins:'1 0 1 1',
							height:350,
							layout:'border',
							items:[{
								layout:'fit',
								region:'west',
								border:false,
								width :'58%',
								items:[{
									width:'100%',
									style:"padding-left:9px;padding-top:1px;",
									html:'<object id="video" classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA"   width="520">'+'<param name="AudioStream" value="-1">'+
								      '<param name="AudioSize" value="-1">'+'<param name="AutoStart" value="1">'+'<param name="FileName" value="/white.avi">'+'<embed width="500" height="350"  filename="/white.avi" src="'+imageURL+'">'+'</embed>'+'</object>'
							 
								}]
							},{
								region:'center',
								border:false,
								layout:'form',
								xtype:'form',
								id:'dpFormPanel',
								labelWidth:60,
								bodyStyle:'padding-top:10', 
								defaults : {height:30,xtype : 'textfield', msgTarget : 'side'},
								labelAlign:'right',
								items:[{xtype:'hidden',id:'hostid',name:'hostid',value:vid},{
									anchor:'-10',fieldLabel:'视频名称',readOnly:true,value:title
								},{
									anchor:'-10',fieldLabel:'视频作者',readOnly:true,value:author
								},{
									anchor:'-10',fieldLabel:'视频简介',readOnly:true,value:content,xtype:'textarea',height:'90',preventScrollbars:false
								},{
									anchor:'-10',fieldLabel:'评委点评',id:'dpnr',readOnly:true,name:'comment',xtype:'textarea',height:'80',value:commentNode
								}]
							}],
							bbar:{
								height:30,
								items:['->',
									      '<img src="'+ctx+'/images/new/good.png"/>','<span>'+zan+'</span>',
									      '<img src="'+ctx+'/images/new/bad.png"/>','<span>'+shi+'</span>',
									      '<img src="'+ctx+'/images/new/remark.png"/>','<span>'+zp+'</span>',
									      '-']
							}
						},{
							region:'center',
							margins:'0 0 1 1',
							layout:'form',
							xtype:'form',
							labelWidth:0,
							buttonAlign:'center',
							autoScroll : true,
							defaults : {height:30,xtype : 'textarea', msgTarget : 'side',id: 'commentext'},
							labelAlign:'right',
							bodyStyle:'padding-top:5px;',
							items:[{fieldLabel:'发表评价',anchor:'-10',name:'comment',id:'fabiaopinglun',height:100}],
							buttons:[{text:'评委点评',widht:80,id:'comments'},{xtype:'hidden',value:hostId}]
						}]
					},{
						region:'center',
						layout:'fit',
						items:new commentoGrid()
					}]
				}]
			}]
		 });
		 videoCommentUi.superclass.initComponent.call(this);
	}
});