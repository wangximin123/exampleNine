package com.example.administrator.test9;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHolder extends DefaultHandler {
    private StringBuilder id,name,grade;
    private String nodeName;
    @Override
    public void startDocument() throws SAXException {
        id=new StringBuilder();
        name=new StringBuilder();
        grade=new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName=localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Log.d("result",id.toString()+"--"+name.toString()+"--"+grade.toString());
        id.setLength(0);
        name.setLength(0);
        grade.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (nodeName.equals("id")){
            id.append(ch,start,length);
        }else if (nodeName.equals("name")){
            name.append(ch,start,length);
        }else if (nodeName.equals("grade")){
            grade.append(ch,start,length);
        }
    }
}
