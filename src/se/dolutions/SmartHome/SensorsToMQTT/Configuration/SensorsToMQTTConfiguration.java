package se.dolutions.SmartHome.SensorsToMQTT.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SensorsToMQTTConfiguration {
	
	ArrayList<ConfigurationSensorNetwork> sensorNetworksList;
	ArrayList<ConfigurationAlarm> alarmList;
	
	XPath xPath;
	
	public ArrayList<ConfigurationSensorNetwork> getSensorNetworksList(){
		return sensorNetworksList;
	}
	
	public ArrayList<ConfigurationAlarm> getAlarmList() {
		return alarmList;
	}

	public SensorsToMQTTConfiguration(){
		sensorNetworksList = new ArrayList<ConfigurationSensorNetwork>();
		alarmList = new ArrayList<ConfigurationAlarm>();
		
    	xPath =  XPathFactory.newInstance().newXPath();
		
	}
	
	public String getNodeValue(Node inNode){
		try{
			if(inNode!=null)
				return inNode.getTextContent(); 
			else
				return "";
        } catch (DOMException e) {
			e.printStackTrace();
		} 			

		return "";
	}
	
	
	public void parseConfiguration(){
		
		ConfigurationSensorNetwork netwConfig;
		ConfigurationAlarm alarmConfig;
		
	    try{
	    	FileInputStream file = new FileInputStream(new File("SensorsToMQTT.xml"));
        
	    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
         
	    	DocumentBuilder builder =  builderFactory.newDocumentBuilder();
         
	    	Document xmlDocument = builder.parse(file);

	    	
	    	String expression = "/xml/alarms/alarm";
	    	NodeList alarmNodes = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
	    	Node subNode;
	    	for(int i = 0; null!=alarmNodes && i < alarmNodes.getLength(); i++){
	    		alarmConfig = new ConfigurationAlarm();
	    		
	    		subNode = (Node) xPath.evaluate("./type", alarmNodes.item(i), XPathConstants.NODE);
	    		alarmConfig.setAlarmType(getNodeValue(subNode));

	    		subNode = (Node) xPath.evaluate("./ip", alarmNodes.item(i), XPathConstants.NODE);
	    		alarmConfig.setIp(getNodeValue(subNode));
	    		
	    		subNode = (Node) xPath.evaluate("./port", alarmNodes.item(i), XPathConstants.NODE);
	    		alarmConfig.setIpport(Integer.parseInt(getNodeValue(subNode)));
	    		
	    		NodeList channelList = (NodeList) xPath.evaluate("./channels", alarmNodes.item(i), XPathConstants.NODESET);
	    		Node channelNode;
	    		ConfigurationChannel channelConfig;
	    		for(int x=0; null!=channelList && x < channelList.getLength(); x++){
	    			channelConfig = new ConfigurationChannel();
	    			
	    			channelNode = (Node) xPath.evaluate("./channel/url", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setChannelUrl(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/type", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setChannelType(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/rootTopic", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setRootTopic(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/clientId", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setClientID(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/username", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setUserName(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/password", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setPassword(getNodeValue(channelNode));
	    			
	    			alarmConfig.getChannelList().add(channelConfig);
	    		}
	    		
	    		alarmList.add(alarmConfig);
	    		
	    	}

	    	expression = "/xml/sensor-networks/network";
	    	NodeList networkList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
//	    	Node subNode;
	    	for(int i = 0; null!=networkList && i < networkList.getLength(); i++){
	    		//Ok - at least we found one sensor network!
	    		netwConfig = new ConfigurationSensorNetwork();

	    		subNode = (Node) xPath.evaluate("./id", networkList.item(i), XPathConstants.NODE);	
	    		netwConfig.setId(Integer.parseInt(getNodeValue(subNode)));

	    		subNode = (Node) xPath.evaluate("./comment", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.setComment(getNodeValue(subNode));
	    		
	    		//First - get all connection things..	    		
	    		subNode = (Node) xPath.evaluate("./ip/host", networkList.item(i), XPathConstants.NODE);	    		
	    		netwConfig.getConfigSensorGwIp().setIp_address(getNodeValue(subNode));
	    		
	    		subNode = (Node) xPath.evaluate("./ip/port", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigSensorGwIp().setIp_port(Integer.parseInt(getNodeValue(subNode)));
	    		
	    		//Now load the persistance configuration!
	    		subNode = (Node) xPath.evaluate("./persistance/type", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbType(getNodeValue(subNode));
	    		subNode = (Node) xPath.evaluate("./persistance/host", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbHost(getNodeValue(subNode));
	    		subNode = (Node) xPath.evaluate("./persistance/login", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbLogin(getNodeValue(subNode));
	    		subNode = (Node) xPath.evaluate("./persistance/password", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbPassword(getNodeValue(subNode));
	    		subNode = (Node) xPath.evaluate("./persistance/database", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbDatabase(getNodeValue(subNode));
	    		subNode = (Node) xPath.evaluate("./persistance/port", networkList.item(i), XPathConstants.NODE);
	    		netwConfig.getConfigDB().setDbPort(Integer.parseInt(getNodeValue(subNode)));

	    		NodeList channelList = (NodeList) xPath.evaluate("./channels", networkList.item(i), XPathConstants.NODESET);
	    		Node channelNode;
	    		ConfigurationChannel channelConfig;
	    		for(int x=0; null!=channelList && x < channelList.getLength(); x++){
	    			channelConfig = new ConfigurationChannel();
	    			
	    			channelNode = (Node) xPath.evaluate("./channel/url", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setChannelUrl(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/type", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setChannelType(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/rootTopic", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setRootTopic(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/clientId", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setClientID(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/username", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setUserName(getNodeValue(channelNode));
	    			channelNode = (Node) xPath.evaluate("./channel/password", channelList.item(x), XPathConstants.NODE);	    			
	    			channelConfig.setPassword(getNodeValue(channelNode));
	    			
	    			netwConfig.getChannelList().add(channelConfig);
	    		}
	    		
	    		sensorNetworksList.add(netwConfig);		    		
	    	}	    	
	    	
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
			e.printStackTrace();
		} 			
	}



}
