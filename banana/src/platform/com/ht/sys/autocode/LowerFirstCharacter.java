package com.ht.sys.autocode;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
* 类功能说明: FreeMarker的宏，首字母小写
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-6 下午10:27:00
* @version V1.0
*/
public class LowerFirstCharacter implements TemplateDirectiveModel{
	 public void execute(Environment env,
	            Map params, TemplateModel[] loopVars,
	            TemplateDirectiveBody body)
	            throws TemplateException, IOException {
	        // Check if no parameters were given:
	        if (!params.isEmpty()) {
	            throw new TemplateModelException(
	                    "This directive doesn't allow parameters.");
	        }
	        if (loopVars.length != 0) {
	                throw new TemplateModelException(
	                    "This directive doesn't allow loop variables.");
	        }
	        
	        // If there is non-empty nested content:
	        if (body != null) {
	            // Executes the nested body. Same as <#nested> in FTL, except
	            // that we use our own writer instead of the current output writer.
	            body.render(new UpperCaseFilterWriter(env.getOut()));
	        } else {
	            throw new RuntimeException("missing body");
	        }
	    }
	    
	    /**
	     * A {@link Writer} that transforms the character stream to upper case
	     * and forwards it to another {@link Writer}.
	     */ 
	    private static class UpperCaseFilterWriter extends Writer {
	       
	        private final Writer out;
	           
	        UpperCaseFilterWriter (Writer out) {
	            this.out = out;
	        }

	        public void write(char[] cbuf, int off, int len)
	                throws IOException {
	        	cbuf[0] = Character.toLowerCase(cbuf[0]);
	        	out.write(String.valueOf(cbuf).trim());///把右边空格去掉
	        }

	        public void flush() throws IOException {
	            out.flush();
	        }

	        public void close() throws IOException {
	            out.close();
	        }
	    }
}
