package com.ht.sys.core.function;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseXml extends DefaultHandler {
	private Menu function;
	private String tagName;

	public Menu getFunction() {
		return function;
	}

	public void setFunction(Menu function) {
		this.function = function;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();

	}

	// 调用多次 开始解析
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("function")) {
			function = new Menu();
			// 获取function节点上的name属性值
			function.setName(attributes.getValue(0));
			// 获取function节点上的url属性值
			function.setUrl(attributes.getValue(1));
		}
		this.tagName = qName;
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

}
