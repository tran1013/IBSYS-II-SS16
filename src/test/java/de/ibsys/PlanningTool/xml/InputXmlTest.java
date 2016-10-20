package de.ibsys.PlanningTool.xml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.ibsys.planningTool.model.XmlInputData;
import de.ibsys.planningTool.model.xmlInputModel.Article;


public class InputXmlTest {
	
	@Autowired
	private XmlInputData xmlInputData;
	
	@Resource
	File xmlInputFile;
	
	@Resource
	File xmlFailureFile;
	
	@Before
	public void initComponent() {
		ClassLoader classLoader = getClass().getClassLoader();
		xmlInputData = new XmlInputData();
		xmlInputFile = new File(classLoader.getResource("xml/result.xml").getFile());
		xmlFailureFile = new File(classLoader.getResource("xml/input.xml").getFile());
 	}
	
	@Test
	public void testIfFileExists() {
		assertTrue(xmlInputFile.exists());
	}
	
	@Test
	public void testIfFileIsCorrect() throws IOException {
		assertTrue(xmlInputData.checkXMLFile(xmlInputFile));
	}
	
	@Test
	public void testIfFileIsInCorrect() throws IOException {
		assertFalse(xmlInputData.checkXMLFile(xmlFailureFile));
	}
	
	@Test
	public void testIfParseIsFine() throws IOException, ParserConfigurationException {
		assertTrue(xmlInputData.parseXML(xmlInputFile));
	}
	
	@Test
	public void testGetAllArticles() throws IOException, ParserConfigurationException {
		if(xmlInputData.parseXML(xmlInputFile)) {
			assertEquals(59, xmlInputData.getWareHouseArticles().size());
			assertEquals(0, xmlInputData.getWareHouseArticles().get("1").getAmount());
		}
	}
	
	
}
