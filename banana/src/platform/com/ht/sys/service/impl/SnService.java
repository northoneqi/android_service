/**
* @Package com.bluefat.budget.service
* @Description: 单据编号
* @author qj  
* @date 2013-12-9 13:36:00 
* @version V1.0
*/
package com.ht.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.Sn;
import com.ht.sys.bean.mapper.SnType;
import com.ht.sys.dao.impl.SnDao;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.db.QueryUtil;

/**
* 类功能说明: 单据编号
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>该代码由系统自动产生，请按需求修改</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-12-9 13:36:00
* @version V1.0
*/

@Service("sys_SnService")
public class SnService extends BaseServiceImpl<Sn>{

	private SnDao snDao;
	private int snNum = 0;
	private Sn sn;
	
	public SnDao getSnDao() {
		return snDao;
	}

	@Autowired
	public void setSnDao(SnDao snDao) {
		super.setBaseDao(snDao);
		this.snDao = snDao;
	}
	
	
	private void initSn(SnType snType){
		QueryUtil queryUtil = new QueryUtil();
		queryUtil.put("snType", "=", snType);
		
		List<Sn> list = snDao.findByCondition(queryUtil);
		String strFormat = "yyyyMMdd";
		if(list.size() > 0){
			sn = list.get(0);
			strFormat = sn.getRule();
			if(strFormat == null || strFormat.equals("")){
				strFormat = "yyyyMMdd";
			}
			
			if(DateUtil.format(new Date(), strFormat).equals(sn.getDate())) {
				snNum = sn.getMaxValue();
			}else{
				sn.setDate(DateUtil.format(new Date(), strFormat));
			}
			
		}else {
			sn = new Sn();
			sn.setSnType(snType);
			sn.setDate(DateUtil.format(new Date(), strFormat));
		}
		
	}
	
	/**
	 * <p>产生一个单据编号</p>
	 * @author qj
	 * @date 2013-11-21 上午08:50:59
	 * @param @param userName
	 * @param @param date
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public synchronized String createSn(SnType snType){
		String nextBillCode = "";
		this.initSn(snType);
		snNum++;
		sn.setMaxValue(snNum);
		snDao.update(sn);
		String prefix = sn.getPrefix();
		prefix = prefix==null ? "" : prefix;
		String suffix = sn.getSuffix();
		suffix = suffix==null ? "" : suffix;
		int length = sn.getLength();
		
		nextBillCode = prefix+sn.getDate()+String.format("%0"+length+"d", snNum)+suffix;
		return nextBillCode;
	}
}
