package com.ht.sys.autocode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ht.sys.annotation.Description;


/**
* 类功能说明: 实体对象转化为extjs的model
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:30:06
* @version V1.0
*/
public class ReflectObject2ExtJSModel {
	
	/**
	 * <p>得到class的所有字段信息</p>
	 * @author qj
	 * @date 2013-11-15 上午09:30:45
	 * @param clazz
	 * @return List<Field>   返回所有的字段
	 * @throws
	 */
	public static List<Field> getFields(Class<?> clazz){
		List<Field> list = new ArrayList<Field>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field: fields) {
			list.add(field);
		}
		
		Class<?> superClass = clazz.getSuperclass();// 父类
        
		if(superClass != null) {
			list.addAll(getFields(superClass));
		}
		return list;
	}
	
	
	/**
	 * <p>根据class的field上的注解生成extjs的field</p>
	 * @author qj
	 * @date 2013-11-15 上午09:31:39
	 * @param clazz
	 * @param @throws IOException    
	 * @return List<ModelField>   
	 * @throws
	 */
	public static List<ModelField> createModel(Class<?> clazz) throws IOException {  
        
        List<Field> fields = getFields(clazz);
        List<ModelField> list = new ArrayList<ModelField>();
        
        for (Field field : fields) {  
        	Description des = field.getAnnotation(Description.class);
        	
        	ModelField model = new ModelField();
        	
        	if(des != null) {
        		model.setFieldLabel(des.value());
        		model.setName(field.getName());
        		model.setType(des.type().toString());
        		model.setHidden(des.hidden());
        		model.setWidth(des.width());
        		
        		list.add(model);
        	}
        }  
        
        return list;
    }  
  
    public static void main(String[] args) {  
    }  
}
