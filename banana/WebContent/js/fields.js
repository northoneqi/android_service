/**
* 基本form组件类
*/

/**
 ************ 验证*********************
 * */
//IP地址验证
Ext.apply(Ext.form.VTypes, {
    IPAddress:  function(v) {
        return /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/.test(v);
    },
    IPAddressText: '请输入合法的IP地址'
});

//验证身份证号
Ext.apply(Ext.form.VTypes, {
    idCard: function(v) {
        return  /^(\d{18,18}|\d{15,15}|\d{17,17}x)$/.test(v);
    },
    idCardText: '请输入合法的身份证号'
});


//验证电话号码
Ext.apply(Ext.form.VTypes, {
    tel: function(v) {
        return  (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(v) ||
        		/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(v));
    },
    telText: '请输入合法的电话号码\n\n例如:13916752109或0712-3614072'
});

/**
 * 创建下拉框
 * */
function createCombo(fieldLabel, fieldName, data){
	var combo = new Ext.form.ComboBox({
	    typeAhead: true,
	    fieldLabel: fieldLabel,
	    triggerAction: 'all',
	    editable: false,
	    lazyRender:true,
	    mode: 'local',
	    name: fieldName,
	    store: new Ext.data.ArrayStore({
	        fields: [
	            'value',
	            'displayText'
	        ],
	        data: data
	    }),
	    value: data[0][0],
	    valueField: 'value',
	    displayField: 'displayText'
	});
	
	return combo;
}

/**
 * 创建下拉框（需要向后台请求数据）
 * */
Ext.ux.form.ComboBox = function(config){
	var store = new Ext.data.JsonStore({ 
	     url: config.url, 
	     fields: config.fields
	    // data: [{code:'1001',name:'性别'},{code:'1002', name:'状态'}]
	}); 
	store.load(); 
	
	var combo = new Ext.form.ComboBox({
		dataIndex: Ext.isEmpty(config.name) ? "name"+Math.random() :  config.name,
		value: Ext.isEmpty(config.value) ? '' : config.value,
	    typeAhead: true,
	    submitValue: true,
	    //autoSelect: true, //自动选中第一个结果
	    allowBlank: Ext.isEmpty(config.allowBlank) ? true : config.allowBlank,
	    fieldLabel:  Ext.isEmpty(config.fieldLabel) ? "" : config.fieldLabel,
	    name: Ext.isEmpty(config.name) ? "name"+Math.random() :  config.name,
	    hiddenName: Ext.isEmpty(config.hiddenName) ? 'code'+Math.random(): config.hiddenName,
	    emptyText: Ext.isEmpty(config.emptyText)? "请选择" : config.emptyText,
	    editable: Ext.isEmpty(config.editable)? false : config.editable,
	    triggerAction: 'all',
	    lazyRender:true,
	    selectOnFocus:true, 
	    forceSelection: true,
	    mode: 'local',
	    store: store,
	    //listeners: [{"select": function(field, value, oldValue){alert("ok")}}], 
	   // listeners: Ext.isEmpty(config.listeners)? [] : config.listeners,
	    valueField: config.fields[0].name,
	    displayField: config.fields[1].name
	});
	
	if(!Ext.isEmpty(config.id)){
		combo.id = config.id;
	}
	
	//console.log(combo);
	return combo;
}

/**
 * 创建数据字典下拉框
 * */
function createDictCombo(config){
	var combo = Ext.ux.form.ComboBox({
		name: 'professionalTitle',
		fieldLabel: '<span style="color:red">*</span>技术级别',
	   	fields: [{name: 'name'}, {name: 'name'}],
	   	url: 'sys/dict/getDictByCode.do?code=1005'
	});
	
	return combo;
}

Ext.ux.form.TreeComboBox = Ext.extend(Ext.form.ComboBox, {
	initComponent : function(ct, position) {  
		this.divId = 'tree-' + Ext.id();  
		if (isNaN(this.maxHeight))
			this.maxHeight = 200;

		Ext.apply(this, {  
            tpl : '<tpl>' + '<div style="height:' + this.maxHeight + 'px;">' + '<div id="' + this.divId + '"></div>' + '</div></tpl>'  
		});  

		//tree的节点
		var root = new Ext.tree.AsyncTreeNode({  
            text : this.rootText,  
            id : this.rootId, 
            draggable : false,
            autoHeight : true,
            autoScroll : true
		}); 
		
		var treeLoader =  new Ext.tree.TreeLoader({
        	dataUrl : this.treeUrl,
        	preloadChildren: true,
            clearOnLoad : true  
        });
		
		//自动加载数据,否则load的时候没法设值
		treeLoader.load(root, function(root){
			treeLoader.load(root.childNodes[0]);
		});
		
		//一个tree
		this.tree = new Ext.tree.TreePanel({
            border : false,  
            root : root,
            rootVisible : this.rootVisible,
            loader : treeLoader,
            listeners : {  
            	scope : this,  
            	click : function(node) {
            		this.setValue(node);  
            		this.collapse();
            	}  
            }	  
		});
		
	    //判断选择的是否是节点
	    this.tree.on('click',this.onViewClick,this);
	    //继承父类的构造函数
	    Ext.ux.form.TreeComboBox.superclass.initComponent.call(this);  
    },  

    onRender : function(ct, position) {
    	//继承父类的Onrender展开
    	Ext.ux.form.TreeComboBox.superclass.onRender.call(this, ct, position);
    	//默认展开
    	this.on("expand", function() {
            if (!this.tree.rendered) {
            	this.tree.render(this.divId);
            	//this.tree.expandAll();
            }  

        }, this)  
    },
    onViewClick : function(doFocus){
        if(doFocus !== false){
            this.el.focus();  
        }
    }
});  

Ext.reg('uxtreecombobox', Ext.ux.form.TreeComboBox);

//重新定下下setValue方法
Ext.override(Ext.form.ComboBox, {
    setValue : function(node) {
		var self = this;
		//console.log(node);
    	if (typeof node == "object" && !Ext.isEmpty(node)) {
    		// 当node为object对象时 node和tree里面的对应  
    		this.lastSelectionText = node.text;  
    		// 设置显示文本为node的text  
    		this.setRawValue(node.text);  
    		if (this.hiddenField) {  
    			// 设置隐藏值为node的id  
    			this.hiddenField.value = node.attributes.code;  
    		}  

    		this.value = node.id;  
    		return this;  
       } else {  
    	   //在此判断该组件是否有树，如果有load的时候需要从tree里面提取数据
    	   if (!Ext.isEmpty(this.tree)){
    		   var rootNode = this.tree.getRootNode();
    		   var oldNode = node;
    		   var node = rootNode.findChild("code", node, true);
    		   
    		   if(!Ext.isEmpty(node)){
    			  // node.select(); //让该节点被选中
    			 // 当node为object对象时 node和tree里面的对应  
    	    		this.lastSelectionText = node.text;  
    	    		// 设置显示文本为node的text  
    	    		this.setRawValue(node.text);  
    	    		if (this.hiddenField) {  
    	    			// 设置隐藏值为node的id  
    	    			this.hiddenField.value = node.attributes.code;  
    	    		}  

    	    		this.value = node.id;
    	    		return this; 
    		   }else {
    			   this.tree.loader.load(rootNode, function(root){
    				   if(!Ext.isEmpty(oldNode)){
    					   self.tree.loader.load(root.childNodes[0], function(){
	    					   var node = rootNode.findChild("code", oldNode, true);
		    				   if(!Ext.isEmpty(node)){
			    				   self.lastSelectionText = node.text;  
				    	    		// 设置显示文本为node的text  
				    	    		self.setRawValue(node.text);  
				    	    		if (self.hiddenField) {  
				    	    			// 设置隐藏值为node的id  
				    	    			self.hiddenField.value = node.attributes.code;  
				    	    		}  
				
				    	    		self.value = node.id;
				    	    		Ext.form.ComboBox.superclass.setValue.call(self, node.text);  
    	   							//this.value = node;  
				    	    		return this;
				    	    	}
    				   		});
    				   }
    				   
    			   });
    		   }
    	   }else{
	    	   // 当node为文本时 
	    	   var text = node;  
	    	   if (this.valueField) { 
	    		   var r = this.findRecord(this.valueField, node); 
	    		   if (r) {
	    			   text = r.data[this.displayField];
	    			   if (this.hiddenField) {  
			    		   this.hiddenField.value = node;  
			    	   }
	    		   } else if (Ext.isDefined(this.valueNotFoundText)) {  
	    			   text = this.valueNotFoundText;  
	    		   }  
	    	   }  

	    	   this.lastSelectionText = text;  
    	   }
    	   Ext.form.ComboBox.superclass.setValue.call(this, text);  
    	   this.value = node;  
    	   return this;  
       }     

    }  
});

/**
 * 下拉树
 */
function createComboTree(config){
	var comboTree = new Ext.ux.form.TreeComboBox ({
    	fieldLabel: config.fieldLabel,
    	xtype : 'uxtreecombobox',
    	name: config.name,
    	hiddenName : config.hiddenName,
    	submitValue: true,
    	editable: false,
    	store: new Ext.data.SimpleStore({
			fields : ["text", "code"],
			data : [[]]
		}),
		treeUrl: config.url,
    	shadow : true,
    	mode : 'local',
    	selectMode: 'leaf',
    	displayField : 'text',  
    	valueField : 'code',  
    	triggerAction : 'all',  
    	allowBlank : Ext.isEmpty(config.allowBlank) ? true : config.allowBlank,  
    	forceSelection : true,
    	maxHeight : 150,
    	autoHeight : true,
    	listeners : {
    	}
    });
	
	if(!Ext.isEmpty(config.id)){
		comboTree.id = config.id;
	}
	return comboTree;
}

/**
 * 下拉树形列表
 * */
Ext.ux.form.TreeGridComboBox = Ext.extend(Ext.form.ComboBox, {
	initComponent : function(ct, position) {  
		this.divId = 'treeGrid-' + Ext.id();  
		if (isNaN(this.maxHeight))
			this.maxHeight = 200;
		if (isNaN(this.maxWidth))
			this.maxWidth = 100;

		Ext.apply(this, {  
            tpl : '<tpl>' + '<div style="height:' + this.maxHeight + 'px; width:100px;">' + '<div id="' + this.divId + '"></div>' + '</div></tpl>'  
		});  

		//tree的节点
		var root = new Ext.tree.AsyncTreeNode({  
            text : this.rootText,  
            id : this.rootId, 
            draggable : false,
            autoHeight : true,
            autoScroll : true
		}); 
		
		var treeLoader =  new Ext.tree.TreeLoader({
        	dataUrl : this.treeUrl,
        	preloadChildren: true,
            clearOnLoad : true  
        });
		
		//一个treeGrid
		this.treeGrid = new Ext.ux.tree.TreeGrid({
			loadMask : {msg:'正在加载数据，请稍侯...'},
	        columns:[{
	            header: '编号',
	            dataIndex: 'code',
	            width: 230
	        },{
	            header: '资源名称',
	            width: 100,
	            dataIndex: 'name',
	            align: 'center'
	        },{
	            header: '类型',
	            width: 150,
	            dataIndex: 'typeName',
	            align: 'center',
	            convert: function(v){
	            	if(v == 'button'){
	            		return "按钮";
	            	}else if(v == 'menu'){
	            		return '菜单';
	            	}
	            	
	            	return '布局';
	            }
	        },{
	            header: '状态',
	            width: 60,
	            dataIndex: 'state',
	            align: 'center'
	        },{
	            header: '备注',
	            width: 150,
	            dataIndex: 'remark',
	            align: 'center'
	        }],
			tools : [ {
				id : 'refresh',
				handler : function() {
					treeGrid.root.reload();
					treeGrid.expand();
				}
			} ],
			loader: treeLoader
		})
	    //判断选择的是否是节点
	    //this.treeGrid.on('click',this.onViewClick,this);
	    //继承父类的构造函数
	    Ext.ux.form.TreeGridComboBox.superclass.initComponent.call(this);  
    },  

    onRender : function(ct, position) {
    	//继承父类的Onrender展开
    	Ext.ux.form.TreeGridComboBox.superclass.onRender.call(this, ct, position);
    	//默认展开
    	/*this.on("expand", function() {
            if (!this.tree.rendered) {
            	this.tree.render(this.divId);
            	this.tree.expandAll();
            }  
        }, this)  */
    },
    onViewClick : function(doFocus){
        if(doFocus !== false){
            this.el.focus();
        }
    }
});  

Ext.reg('uxtreegridcombobox', Ext.ux.form.TreeGridComboBox);