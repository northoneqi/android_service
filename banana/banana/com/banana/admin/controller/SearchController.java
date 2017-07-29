package com.banana.admin.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banana.admin.bean.Sentiment;
import com.banana.admin.service.SearchService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.MD5Util;

@Controller
@RequestMapping("/query")
public class SearchController extends BaseController<Sentiment> {

	private Logger logger = Logger.getLogger(SearchController.class);

	private SearchService searchService;

	/**
	 * 负面监控以及导出报告接口
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryLoadCheck", method = RequestMethod.GET)
	public void queryLoadCheck(HttpServletRequest request,
			HttpServletResponse response) {
		String responseStr = "";
		try {
			StringBuilder sb = new StringBuilder();
			InputStream inputStream = request.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inputStream);
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = bis.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, read, "UTF-8"));
			}

			sb.append("{\"sentiment\":6,\"type\":0,\"timeRules\":[{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"Xplay5\",\"opic_id\":35,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"Xplay5拍照\",\"type\":\"0\"},{\"name\":\"拍照;拍摄;照相;摄像头;像素;分辨率\",\"topic_id\":35,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"Xplay5拍照\",\"type\":\"0\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"小米\",\"topic_id\":38,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"小米\",\"type\":\"1\"},{\"name\":\"手机\",\"topic_id\":38,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"小米\",\"type\":\"1\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"vivo;VIVO\",\"topic_id\":39,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"VIVO全部\",\"type\":\"0\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"华为\",\"topic_id\":36,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"华为\",\"type\":\"1\"},{\"name\":\"手机\",\"topic_id\":36,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"华为\",\"type\":\"1\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"魅族;MEIZU\",\"topic_id\":37,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"魅族\",\"type\":\"0\"},{\"name\":\"手机\",\"topic_id\":37,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"魅族\",\"type\":\"0\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"vivo\",\"topic_id\":42,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"品牌\",\"type\":\"0\"},{\"name\":\"手机;Xplay5;xplay;x6;宋仲基\",\"topic_id\":42,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"品牌\",\"type\":\"0\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"OPPO\",\"topic_id\":40,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"OPPO\",\"type\":\"1\"},{\"name\":\"R7;R9\",\"topic_id\":40,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"竞品\",\"topicName\":\"OPPO\",\"type\":\"1\"}],\"stime\":30},{\"startDay\":\"2016-09-08 00:00\",\"endDay\":\"2016-09-14 23:59\",\"rulelist\":[{\"name\":\"vivo\",\"topic_id\":41,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"代言人\",\"type\":\"0\"},{\"name\":\"宋钟基\",\"topic_id\":41,\"typeid\":5,\"historyDays\":30,\"subjectName\":\"本品\",\"topicName\":\"代言人\",\"type\":\"0\"}],\"stime\":30}],\"Zfmbs\":\"e81827bcb301fba463334a9113ae66b7;d33c71ac3c90b8ee4b6e688e7c286ac4;628065b4d1135953c7eae1a2e5dc5f1e;3d2f711b04a024d1db44fb449b0dc8fa;562cdf21c7bcd3172ac7e009e9c5e32b;80e7d42303031d62dbb8e9cb9500e31a;d0229bdae702f04f6ced2e08dfd61bce;cf1bfafbcd3bfda819407459828d9287;fd85362ebfc9f3536afac68fd3235904;fd85362ebfc9f3536afac68fd3235904;cf1bfafbcd3bfda819407459828d9287;562cdf21c7bcd3172ac7e009e9c5e32b;3d2f711b04a024d1db44fb449b0dc8fa;08af477af7d3574e8e106e332d0e1f5b;6a18847384ed2127dc1f4d383f4eb56c;87015576083dd6c626e9a98c1068757d;3f311ce4745e58a21a74ca861aa24b25\",\"useSimilarity\":0,\"updateIds\":[{\"id\":\"22df5b27d63596d6db1615bf5bd76e21\",\"sentiment\":\"0\",\"oldsentiment\":\"6\"}],\"operator\":1,\"recNumPerPage\":9,\"currPage\":1,\"timestamp\":\"2016-09-14 14:42:22\",\"sign\":\"f077e7e457641c37fd0cbf7d05da63f4\",\"stype\":1}");

			if (!"".equals(sb.toString())) {

				logger.warn("\n query by keyword recieve datas  export):"
						+ sb.toString());

				JSONObject dataJsonObject = JSONObject
						.fromObject(sb.toString());

				// 获取时间
				String timestamp = dataJsonObject.containsKey("timestamp") ? dataJsonObject
						.getString("timestamp") : "";

				// key值
				String sign = dataJsonObject.containsKey("sign") ? dataJsonObject
						.getString("sign") : "";

				// 验证当前请求是否合法 first
				if (validSign(timestamp, sign)) {

					// 不同接口
					String stype = dataJsonObject.containsKey("stype") ? dataJsonObject
							.getString("stype") : "";

					if (stype != null && !"".equals(stype)) {
						switch (Integer.parseInt(stype)) {

						case 1:

							String dd = searchService.queryList(dataJsonObject);

							break;
						case 8:
						case 9:
							break;
						case 10:
							break;
						case 11:
							break;
						case 12:
							break;
						case 13:
							break;
						default:
							break;
						}

					} else {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("code", "100");
						jsonObject.put("message", "stype is empty");
						responseStr = jsonObject.toString();
					}
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", "100");
					jsonObject.put("message", "key not right");
					responseStr = jsonObject.toString();
				}
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", "100");
				jsonObject.put("message", "there is not data");
				responseStr = jsonObject.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("query by keyword occur exception", e);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", "100");
			jsonObject.put("message", "query by keyword occur exception");
			responseStr = jsonObject.toString();
		} finally {

			setSearchMsg(response, responseStr);
		}
	}

	private boolean validSign(String timestamp, String sign)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String key = "querySignkey";
		String signString = MD5Util.getMD5(timestamp + key);
		boolean validResult = signString.equals(sign);
		if (!validResult) {
			logger.warn("our sign string:" + signString
					+ ",recieve sign string:" + sign);
		}
		return validResult;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	@Autowired
	public void setCityService(SearchService cityService) {
		this.searchService = cityService;
	}

}
