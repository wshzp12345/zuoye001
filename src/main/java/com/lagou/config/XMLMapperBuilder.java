package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        addMoreMaapedStatmentWithoutResultType(namespace, list,Configuration.SELECT);

        List<Element> insertNode = rootElement.selectNodes("//insert");
        addMoreMaapedStatmentWithoutResultType(namespace, insertNode,Configuration.INSERT);

        List<Element> deleteNode = rootElement.selectNodes("//delete");
        addMoreMaapedStatmentWithoutResultType(namespace, deleteNode,Configuration.DELETE);

        List<Element> updateNode = rootElement.selectNodes("//update");
        addMoreMaapedStatmentWithoutResultType(namespace, updateNode,Configuration.UPDATE);
    }

    private void addMoreMaapedStatmentWithoutResultType(String namespace, List<Element> insertNode,String sqlType) {
        for (Element element : insertNode) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlType(sqlType);
            String key = namespace +"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }


}
