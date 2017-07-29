package com.ht.sys.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
* 类功能说明: 实体描述注解，方便在自动代码生成的时候生成表头，form表单信息
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-7 上午10:18:20
* @version V1.0
*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	/**字段描述*/
	String value() default "描述";
	/**字段类型*/
	TypeEnum type() default TypeEnum.string;
	/**是否在列表中隐藏*/
	boolean hidden() default false;
	/**列宽*/
	int width() default 80;
}
