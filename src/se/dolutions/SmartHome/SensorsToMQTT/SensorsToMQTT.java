package se.dolutions.SmartHome.SensorsToMQTT;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.Channels.ChannelMQTT;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationChannel;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.ConfigurationSensorNetwork;
import se.dolutions.SmartHome.SensorsToMQTT.Configuration.SensorsToMQTTConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SensorsToMQTT {

	private static Logger logger = LogManager.getFormatterLogger("SensorsToMQTT - Main");
		
	public static void main(String[] args) {

		SensorsToMQTTConfiguration appConfiguration = new SensorsToMQTTConfiguration();
		appConfiguration.parseConfiguration();
		
		List<SensorGatewayCommunicator> threadList = new ArrayList<SensorGatewayCommunicator>();

		for(ConfigurationSensorNetwork cfgNetwork : appConfiguration.getSensorNetworksList()){
			SensorPool sensorPool = new SensorPool();

			SensorGatewayCommunicator gwCommunicator = new SensorGatewayCommunicator(cfgNetwork, sensorPool);
			
			ChannelMQTT channelMQTT;
			for(ConfigurationChannel cfgChannel : cfgNetwork.getChannelList()){
				channelMQTT = new ChannelMQTT();
				channelMQTT.setLogger(gwCommunicator);
				channelMQTT.channelConfigure(cfgChannel);		
				gwCommunicator.addChannelPublisher(channelMQTT);
			}
			gwCommunicator.start();	
			threadList.add(gwCommunicator);
		}
		
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            do{
                input = br.readLine();
                if(input.equals("start-inclusion")){
                	for(SensorGatewayCommunicator gw : threadList){
                		gw.startInclusionMode();
                	}
                } else if(input.equals("stop-inclusion")) {
                	for(SensorGatewayCommunicator gw : threadList){
                		gw.stopInclusionMode();
                	}   	
                } else if(input.equals("exit")){
                	System.exit(0);
                } else if(input.equals("help")){
                	logger.warn("start-inclusion: Starts the Inclusion mode on all Gateways.");
                	logger.info("stop-inclusion: Stops the Inclusion mode on all Gateways.");
                	logger.error("exit: Exits the application.");

                }
            }while(true);
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
	


}
