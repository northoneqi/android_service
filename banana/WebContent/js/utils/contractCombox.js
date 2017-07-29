 procitycountyll=Ext.extend(Ext.form.ComboBox,{
		constructor: function(config) {
	 		 /**根据传入参数 设置指定combox
	 		 1:fieldLabel,2:id,3:name,4:是否为空,5:提示内容,6:url
	         **/
	 		 var fields=config.split(",");
	 		 
			 var store = new Ext.data.Store({
				 proxy: new Ext.data.ScriptTagProxy({
           			url:fields[5]
       		 	 }),
        		 reader: new Ext.data.JsonReader({
        			totalProperty:'count',
           	 		root: 'roots',
            		id:'id'
        		  }, [  {name: 'SKUName', mapping: 'SKUName'},
        				{name: 'sellPrice', mapping: 'sellPrice'},
        				{name: 'SKU', mapping: 'SKU'}
        		  ])
			 });
			 config = Ext.apply({
				fieldLabel:fields[0],
				id:fields[1],
				name:fields[2],
				allowBlank : false,
				blankText :fields[4],
				mode:"romote",
        		triggerAction:'all',
        		valueField :'SKUName',  
        		minListWidth : 300,
        		pageSize:20,
        		anchor : '30%',
      		    displayField:'SKUName',  
				//readOnly:true,
				store: store,
				listeners : {
						beforequery : function(e) {
							var combo = e.combo;   
							if(!e.forceAll){   
								var value = e.query;   
								combo.store.filterBy(function(record,id){   
									var text = record.get(combo.displayField);   
									return (text.indexOf(value)!=-1);   
								});
								combo.expand();   
								return false;   
							}
						},
						'focus':{fn:function(e){e.expand();this.doQuery(this.allQuery, true);},buffer:200}
					}
			 });
			procitycountyll.superclass.constructor.call(this, config);
	},
	initComponent:function(){
		//var userYear=0;
		//组件实例化之前开始判断是否往里面填充值
//		this.on('beforerender',function(){
//			/**如果当前组件是门类组件**/
//			   if(ml!=""&&ml!=null){
//					if(this.id=="menLei"){
//						this.setValue(mlc);
//						this.store.baseParams={code:ml};
//					}
//				}
//				if(this.id=="daLei"){
//					if(dl!=""&&dl!=null){
//						this.setValue(dlc);
//						this.store.baseParams={pcode:ml};
//					}
//					if(dl==null||dl==""){
//						this.store.baseParams={pcode:ml};
//					}
//				}
//				if(this.id=="zhongLei"){
//					if((zl==null||zl=="")&&dl!=null&&dl!=""){
//						this.store.baseParams={pcode:dl};
//					}
//					if(zl!=""&&zl!=null){
//						if(this.id=="zhongLei"){
//							this.setValue(zlc);
//							this.store.baseParams={pcode:dl};
//						}
//					}
//				}
//				if(this.id=="xiaoLei"){
//					if(xl!=""&&xl!=null){
//						this.setValue(xlc);
//						Ext.getCmp("subjectCode").setValue(xl);
//						Ext.getCmp("yjUserYears").setValue(useyears);
//						this.store.baseParams={pcode:zl};
//					}
//					if((xl==null||xl=="")&&zl!=null&&zl!=""){
//						this.store.baseParams={pcode:zl};
//					}
//				}
//		});
		/**当前组件选中事件 
		this.on("select",function(){
			var code=this.getStore().getAt(this.selectedIndex).data.code;
			var name=this.getStore().getAt(this.selectedIndex).data.name;
			
			if(this.id=="proviceName"){
				
				Ext.getCmp("cityID").store.baseParams={proviceCode:code};
				Ext.getCmp("countyID").store.baseParams={cityCode:-1};
				Ext.getCmp("cityID").store.load();
				Ext.getCmp("countyID").store.load();
				
			}
			if(this.id=="cityName"){
				Ext.getCmp("countyId").setValue();
				Ext.getCmp("countyName").setValue();
				Ext.getCmp("cityId").setValue(code);
				Ext.getCmp("countyName").store.baseParams={cityCode:code};
				Ext.getCmp("countyName").store.load();
			}
			if(this.id=="countyName"){
				Ext.getCmp("countyId").setValue(code);			
			}
			
		});**/
		procitycountyll.superclass.initComponent.call(this);
	}
});