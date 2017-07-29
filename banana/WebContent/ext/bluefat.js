/************变量定义********************/
var pageSize = 20;

tbar = [
	{text:'查看',iconCls:'previewIcon',id:'scanButton'}, {xtype:'tbseparator'},
	{text:'增加',iconCls:'addIcon',id:'addButton'},{xtype:'tbseparator'},
	{text:'更新',iconCls:'edit1Icon',id:'editButton'},{xtype:'tbseparator'},
	{text:'删除',iconCls:'deleteIcon',id:'deleteButton'},{xtype:'tbseparator'},
	{text:'提交',iconCls:'submitIcon',id:'submitButton'},{xtype:'tbseparator'},
	{text:'审核',iconCls:'acceptIcon',id:'auditButton'}, {xtype:'tbseparator'},
	{text:'退审',iconCls:'returnIcon',id:'comeBackButton'}, {xtype:'tbseparator'},
	//{text:'关闭',iconCls:'stopUploadIcon',id:'stopButton'}, {xtype:'tbseparator'},
	{text:'入账',iconCls:'app_boxesIcon',id:'enterButton'}, {xtype:'tbseparator'},
	{text:'反入账',iconCls:'app_boxesIcon',id:'disEnterButton'}, {xtype:'tbseparator'},
	{text:'核销',iconCls:'edit1Icon',id:'editButton'}, {xtype:'tbseparator'},
	{text:'支付',iconCls:'app_boxesIcon',id:'enterButton'}, {xtype:'tbseparator'},
	{text:'导出Excel',iconCls:'excelIcon',id:'exportButton'}, {xtype:'tbseparator'},
	{text:'打印预览',iconCls:'printerIcon',id:'printButton'}, {xtype:'tbseparator'}
]

function initToolBar(buttons){
	var removeButtons = [];
	for(var i = 0; i < tbar.length; i++){
		var boo = false;
		if(tbar[i].xtype == "tbseparator"){
			continue;
		}
		
		for(var j = 0; j < buttons.length; j++){
			if(buttons[j] == tbar[i].text) {
				boo = true;
				break;
			}
		}
		
		if(!boo) {
			removeButtons.push(i);
			removeButtons.push(i+1)
		}
	}

	for(var i = removeButtons.length-1; i >= 0; i -= 2){
		tbar.remove(tbar[removeButtons[i]]);
		tbar.remove(tbar[removeButtons[i-1]]);
	}
}

//审核状态
AuditState = [
	"未审核", "审核中", "已通过", "已驳回"
]

/**
 * 序号自增
 * */
Ext.grid.PageRowNumberer = Ext.extend(Ext.grid.RowNumberer, {
	renderer : function(value,metadata,record,rowIndex) {
		return 　1　+　rowIndex;
	}
});


/**
*常用对话框的简单封装
*/
Ext.ux.Msg = {
	alert: function(msg){
		Ext.MessageBox.alert("消息", msg);
	},
	info: function(msg){
		Ext.MessageBox.show({
			//width: 200,
            title: '消息',
            msg: msg,
            buttons: Ext.MessageBox.OK,
            icon: Ext.MessageBox.INFO
       });
	},
	question: function(msg){
		Ext.MessageBox.show({
			//width: 200,
            title: '请选择',
            msg: msg,
            buttons: Ext.MessageBox.OK,
            icon: Ext.MessageBox.QUESTION
       });
	},
	warning: function(msg){
		Ext.MessageBox.show({
			//width: 200,
            title: '警告',
            msg: msg,
            buttons: Ext.MessageBox.OK,
            icon: Ext.MessageBox.WARNING
       });
	},
	error: function(msg){
		Ext.MessageBox.show({
			//width: 200,
            title: '错误',
            msg: msg,
            buttons: Ext.MessageBox.OK,
            icon: Ext.MessageBox.ERROR
       });
	}
};

/**
* 功能描述： 创建一个store
* model： 实体描述信息
* autoLoad：数据是否自动加载（true或者false）
* params： 要传递给后台的参数（如{id:1}）
* url: 请求地址
*/
function createStore(model, autoLoad, params, url){
	 var reader = new Ext.data.JsonReader({
    	idProperty: 'id',
	    root: 'root',
	    totalProperty: 'total',
	    successProperty: 'success',
	    messageProperty: "msg",
	    fields: model
    });

	var store = new Ext.data.GroupingStore({
        reader: reader,
        timeout: 300000,
        pageSize: pageSize,
        baseParams: params,
        proxy : new Ext.data.HttpProxy({
		    method: 'POST',
		    url: url
		}),
		listeners: {
			"loadexception": function(proxy, type, action){
				var response =  Ext.util.JSON.decode(action.responseText);
				if(Ext.isEmpty(response)){
					Ext.ux.Msg.info("加载数据失败");
				}else{
					Ext.ux.Msg.error(response.msg);
				}
			}
		}
    });
	
	if(autoLoad){
		store.load({params:{start:0, limit: pageSize}});
	}
	
	return store;
}


/**
*创建extjs的grid
*/
function createExtGrid(title, model, autoLoad, params, url, sm,id){
	var store = createStore(model, autoLoad, params, url);
	
	var columns = [];
	
	var selModel = null;
 	if (sm == 'radio') {
 		selModel = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
 	} else if (sm == 'multi') {
 		selModel = new Ext.grid.CheckboxSelectionModel({});
 	} else {
 		selModel = new Ext.grid.RowSelectionModel({});
 	}
 	
 	columns.push(selModel);
 	
 	columns.push(new Ext.grid.PageRowNumberer({header:'序号', width: 50}));
 	
	for(var i = 0; i < model.length; i++){
		var field = model[i];
		
		if(!Ext.isEmpty(field.hide)){
			if(field.hide){
				continue;
			}
		}
		
		var column = [];
		column.header = '<center>'+field.header+'</center>';
		column.dataIndex = field.name;
		column.sortable = true;
		
		if(!Ext.isEmpty(field.width)){
			column.width = field.width;
		}
		
		if(!Ext.isEmpty(field.align)){
			column.align = field.align;
		}else{
			if(field.name == 'code'){
				column.align = "left";
			}else{
				column.align = "center";
			}
		}
		
		columns.push(column);
	}
	
	var grid = new Ext.grid.GridPanel({
        store: store,
        region : 'center',
        loadMask : {msg:'正在加载数据，请稍侯...'},
        title: title,
        selModel: selModel,
        id:id,
        columns: columns,
        columnLines: true,
        collapsible: true,
        autoScroll : true,
        stripeRows: true,
        border: false,
        frame : true,
        tbar: new Ext.Toolbar({}),
        bbar: new Ext.PagingToolbar({
            pageSize: pageSize,
            store: store,
            displayInfo: true
            /*
            items:[
                new Ext.form.Label({text:"　每页显示"}),
		        new Ext.form.ComboBox({
					id : "seapageSize",
					triggerAction: 'all',
					editable : false,
					width : 45, 
					store : [20,30,50,100,200],
					value : pageSize,
					name : "seapageSize",
					listeners:{
						'select':function(obj){
		        			pageSize = obj.value;
		        			store.reload({params:{start:0, limit: obj.value}});
						}
					}
				})
            ]*/
        })
    });
	
	return grid;
}

/**
 * 创建treePanel
 * */
function createTreePanel(title, url, baseParams, expanded){
	var treeloader = new Ext.tree.TreeLoader({
		dataUrl: url,
		baseParams: baseParams
	});
	
	var treePanel = new Ext.tree.TreePanel({
		region : 'west',
	    useArrows: true,
	    autoScroll: true,
	    animate: true,
	    enableDD: true,
	    containerScroll: true,
	    border: false,
	    loader: treeloader,
	    title : '<span class="commoncss">'+title+'</span>',
		iconCls : 'chart_organisationIcon',
		collapsible : true,
		width : 210,
		minSize : 160,
		maxSize : 280,
		split : true,
		rootVisible : false,
		tools : [ {
			id : 'refresh',
			handler : function() {
				treePanel.root.reload();
				treePanel.expandAll();
			}
		} ],
		root: {
	        text: '根节点',
	        draggable: false,
	        id: 'source'
	    }
	});
	
	if(!Ext.isEmpty(expanded) && expanded){
		treePanel.expandAll();
	}else{
		treePanel.getRootNode().expand();
	}
	
	return treePanel;
}


/**
 * 创建一个窗口
 * title 标题
 * item 主题内容
 * t_width 宽度（默认500）
 * t_height 高度（默认300）
 * */
function createWindow(title, item, t_width, t_height){
	var width = Ext.isEmpty(t_width) ? 500 : t_width;
	var height = Ext.isEmpty(t_height) ? 300 : t_height;
	
	var win = new Ext.Window({
		title: title,
		layout: 'fit',
		closeAction:'hide',
		maximizable: true,
		height: height,
		width: width,
		autoScroll : true,
		border: true,
		frame: true,
		items: [item]
	});
	
	return win;
}

/**
*提交表单
*/
Ext.ux.Form = {
	submitForm: function(form, url, okfn, waitMsg, params){
		if (form.getForm().isValid()) {
			form.getForm().submit({
				url: url,
				waitTitle: '提示',
				method: 'POST',
				params: Ext.isEmpty(params ) ? {} : params,
				waitMsg: Ext.isEmpty(waitMsg)? '正在提交,请稍候.....': waitMsg,
				success: okfn,
				failure: function(form, action) {
					var errmsg = action.result.data.instruction;
					switch (action.failureType) {
			            case Ext.form.Action.CLIENT_INVALID:
			                Ext.Msg.alert('错误', '表单字段验证失败');
			                break;
			            case Ext.form.Action.CONNECT_FAILURE:
			                Ext.Msg.alert('错误', '链接服务器失败');
			                break;
			            case Ext.form.Action.SERVER_INVALID:
			               Ext.ux.Msg.info(errmsg);
			       	}
				}
			});
		}
	},
	loadForm: function(formPanel, url, params, okfn){
		formPanel.getForm().load({
		    url: url,
		    params: params,
		    waitMsg: '正在加载数据，请稍后......', 
		    success: Ext.isEmpty(okfn) ? function(){} : okfn,
		    failure: function(form, action) {
		        Ext.Msg.alert("错误", action.result.errMessage);
		    }
		});
	}
};

/**
*异步请求
*/
Ext.ux.Ajax = {
	request: function(url, params, okfn, fafn){
		var ok = Ext.isFunction(okfn) ? okfn : function (a, b){
		};
		
		var fa = Ext.isFunction(fafn) ? fafn : function(action, form){
			var response =  Ext.util.JSON.decode(action.responseText);
			if(Ext.isEmpty(response)){
				Ext.ux.Msg.error("请求失败");
			}else{
				Ext.ux.Msg.error(response.data.instruction);
			}
		};
		
		//var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在加载，请稍候......"});
		//myMask.show();
		
		Ext.Ajax.request({
			url: url,
			timeout: 600000,
			method: 'POST',
			params: params,
			success: ok,
			failure: fa
		});
		
	}
};

Ext.override(Ext.layout.BorderLayout.Region,{
    getCollapsedEl:function(){
        if(!this.collapsedEl){
            if(!this.toolTemplate){
                var tt = new Ext.Template(
                     '<div class="x-tool x-tool-{id}">&#160;</div>'
                );
                tt.disableFormats = true;
                tt.compile();
                Ext.layout.BorderLayout.Region.prototype.toolTemplate = tt;
            }
            this.collapsedEl = this.targetEl.createChild({
                cls: "x-layout-collapsed x-layout-collapsedText x-layout-collapsed-"+this.position,
                id: this.panel.id + '-xcollapsed'
            });
            this.collapsedEl.enableDisplayMode('block');

            if(this.collapseMode == 'mini'){
                this.collapsedEl.addClass('x-layout-cmini-'+this.position);
                this.miniCollapsedEl = this.collapsedEl.createChild({
                    cls: "x-layout-mini x-layout-mini-"+this.position, html: "&#160;"
                });
                this.miniCollapsedEl.addClassOnOver('x-layout-mini-over');
                this.collapsedEl.addClassOnOver("x-layout-collapsed-over");
                this.collapsedEl.on('click', this.onExpandClick, this, {stopEvent:true});
            }else {
                var t = this.toolTemplate.append(
                        this.collapsedEl.dom,
                        {id:'expand-'+this.position}, true);
                 
                 this.panel.collapsedText=this.panel.collapsedText||this.panel.title||"";
                 var ct=(this.position=="west"||this.position=="east")?new Ext.Template("<table valign='middle'   width='100%' height='100%'><tr><td>{collapsedText}<td></tr></table>"):new Ext.Template("<span >{collapsedText}</span>");
                 ct.append(
                        this.collapsedEl.dom,
                        {collapsedText:this.panel.collapsedText},true); 
                          
                t.addClassOnOver('x-tool-expand-'+this.position+'-over');
                t.on('click', this.onExpandClick, this, {stopEvent:true});
                
                if(this.floatable !== false){
                   this.collapsedEl.addClassOnOver("x-layout-collapsed-over");
                   this.collapsedEl.on("click", this.collapseClick, this);
                }
            }
        }
        return this.collapsedEl;
    }
});

//解决列锁定的bug
sm=new Ext.grid.CheckboxSelectionModel({
	handleMouseDown: function(g, rowIndex, e) {
        if (e.button !== 0 || this.isLocked()) {
            return;
        }
        var view = this.grid.getView();
        if (e.shiftKey && !this.singleSelect && this.last !== false) {
            var last = this.last;
            this.selectRange(last, rowIndex, e.ctrlKey);
            this.last = last;
            view.focusRow(rowIndex);
        } else {
            var isSelected = this.isSelected(rowIndex);
            if (isSelected) {
                this.deselectRow(rowIndex);
            } else if (!isSelected || this.getCount() > 1) {
                this.selectRow(rowIndex, true);
                view.focusRow(rowIndex);
            }
        }
    },
    isLocked: Ext.emptyFn,
    initEvents: function() {
        Ext.grid.CheckboxSelectionModel.superclass.initEvents.call(this);
        this.grid.on('render', function() {
            var view = this.grid.getView();
            view.mainBody.on('mousedown', this.onMouseDown, this);
            Ext.fly(view.lockedInnerHd).on('mousedown', this.onHdMouseDown, this);
        }, this);
    }
});
function createProcess(note){
	var progressBar = Ext.Msg.show({ 
		title:'系统提示',
        msg:note,
        width: 300,
        wait: true,
        waitConfig: {interval: 500,
			duration: 50000000,
			fn: function () { 
				Ext.Msg.alert("数据操作超时,原因数据量过大.");
				Ext.Msg.hide();
			} },
        closable: true
   });
	return progressBar;
}
//sm.sortLock();