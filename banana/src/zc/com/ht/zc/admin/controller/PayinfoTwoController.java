package com.ht.zc.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.controller.BaseController;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;
import com.ht.zc.admin.bean.PayinfoTwo;
import com.ht.zc.admin.service.PayinfoTwoService;

/**
 * PayinfoTwo 访问控制层
 * <p>众筹支付信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:51:30
 */
@Controller("payinfoTwoController")
@RequestMapping("admin/payinfoTwo")
public class PayinfoTwoController extends BaseController<PayinfoTwo>{
	@Resource(name = "payinfoTwoServiceImpl")
	private PayinfoTwoService payinfoTwoService;
	
	/**
	 * 根据订单编号查找交易明细
	 * 
	 * @param orderCode  订单编号
	 * */
	@RequestMapping("getDetails")
	public void getDetails(HttpServletRequest request, HttpServletResponse response,
			String orderCode){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("crowdfundingCode", "=", orderCode);
			List<PayinfoTwo> list = payinfoTwoService.findByCondition(queryUtil);

			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(list.size()).append(",'success': true, 'root': [");
			for(PayinfoTwo info: list) {
				String remark ;
				if(info.getRemark()==null){
					 remark="";
				}else{
					remark=info.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
				} 
				
				sbf.append("{\"crowdfundingCode\":\"").append(info.getCrowdfundingCode()).append("\",")
				   .append("\"crowdfundingId\": \"").append(info.getCrowdfundingId()).append("\",")
				   .append("\"payOpenId\": \"").append(info.getPayOpenId()).append("\",") 
				   .append("\"payName\": \"").append(info.getPayName()).append("\",")
				   .append("\"payMoney\": \"").append(info.getPayMoney()).append("\",")  
				   .append("\"outTradeNo\": \"").append(info.getOutTradeNo()).append("\",")
				   .append("\"transportFee\": \"").append(info.getTransportFee()).append("\",")
				   .append("\"tradeState\":\"").append(info.getBankBillno()).append("\",")
				   .append("\"tradeMode\": \"").append(info.getTradeMode()).append("\",")
				   .append("\"partner\": \"").append(info.getPartner()).append("\",")
				   .append("\"bankType\": \"").append(info.getBankType()).append("\",")
				   .append("\"bankBillno\": \"").append(info.getBankBillno()).append("\",")
				   .append("\"totalFee\":\"").append(info.getTotalFee()).append("\",")
				   .append("\"feeType\": \"").append(info.getFeeType()).append("\",")
				   .append("\"notifyId\": \"").append(info.getNotifyId()).append("\",")
				   .append("\"transactionId\": \"").append(info.getTransactionId()).append("\",")
				   .append("\"productFee\": \"").append(info.getProductFee()).append("\",")
				   .append("\"discount\": \"").append(info.getDiscount()).append("\",")
				   .append("\"refundStatus\": \"").append(info.getRefundStatus()).append("\",");
				
				
				if(info.getAddTime() != null) {
					sbf.append("\"addTime\": \"").append(DateUtil.timesampToStr(info.getAddTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getTimeEnd() != null) {
					sbf.append("\"timeEnd\": \"").append(DateUtil.timesampToStr(info.getTimeEnd(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
			
	
				sbf.append("\"remark\": \"").append(remark).append("\"},");
				
			}
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, list.size(), list);
		}catch(Exception e){
			e.printStackTrace();
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	
}
