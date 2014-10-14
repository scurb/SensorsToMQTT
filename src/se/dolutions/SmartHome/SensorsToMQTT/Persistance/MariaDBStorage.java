package se.dolutions.SmartHome.SensorsToMQTT.Persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Node;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.Sensor;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorType;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValue;
import se.dolutions.SmartHome.SensorsToMQTT.Sensors.SensorValueType;


public class MariaDBStorage implements INodeStorage {
	Connection con = null;
    PreparedStatement pst = null;

    String url;
    String user;
    String password;
    
    public MariaDBStorage(String dbHost, String dbName, String dbLogin, String dbPassword, Integer dbPort){
        url = "jdbc:mysql://"+dbHost+":"+dbPort.toString()+"/"+dbName;
        user = dbLogin;
        password = dbPassword;
        try{
        	con = DriverManager.getConnection(url, user, password);
        
        } catch(SQLException ex){
        	System.err.println(ex.toString());
        }		

    }
    
    public void executeStatement(PreparedStatement pst){
   	try{
   		pst.executeUpdate();
    } catch (SQLException ex) {
    	System.err.println(ex.toString());

    } finally {

    	try {
    		if (pst != null) {
    			pst.close();
    		}
    	} catch (SQLException ex) {
    		System.err.println(ex.toString());
    	}

    }	
    	
    }
    
	@Override
	public void saveNode(Node node) {
		String sqlString = "replace into nodes(radioID, sketchName, sketchVersion, relayNode, lastUpdated) values(?, ?, ?, ?, now())";
    	try{
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, node.getRadioID());
			pst.setString(2,node.getSketchName());
			pst.setString(3, node.getSketchVersion());
			pst.setBoolean(4, node.getRelayNode());
			executeStatement(pst);
    	} catch (SQLException ex){
    		System.err.println(ex.toString());
    	}
	}

	@Override
	public void deleteNode(Node node) {
        try {
        	String sqlString = "delete from nodes where radioID=?";
        	pst = con.prepareStatement(sqlString);
        	pst.setInt(1, node.getRadioID());
        	executeStatement(pst);

        } catch (SQLException ex) {
        	System.err.println(ex.toString());
        }	
	}

	@Override
	public void updateNode(Node node) {

		// TODO Auto-generated method stub

	}

	@Override
	public void saveSensor(Sensor sensor) {
		String sqlString = "replace into sensors(radioID, childID, sensorType, sensorSketchName, sensorSketchVersion, lastUpdated) values(?, ?, ?, ?, ?, now())";
    	try{
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, sensor.getRadioID());
			pst.setInt(2, sensor.getChildID());
			pst.setInt(3, sensor.getSensorType().ordinal());
			pst.setString(4,sensor.getSensorSketchName());
			pst.setString(5, sensor.getSensorSketchVersion());
			executeStatement(pst);
    	} catch (SQLException ex){
    		System.err.println(ex.toString());
    	}
		
	}

	@Override
	public void deleteSensor(Sensor sensor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSensor(Sensor sensor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer assignNextAvailableRadioID() {
		String sqlString = "SELECT n.radioID + 1 AS FirstAvailableId FROM nodes n LEFT JOIN nodes n1 ON n1.radioID = n.radioID + 1 WHERE n1.radioID IS NULL ORDER BY n.radioID LIMIT 0, 1";
		Integer useID;
		useID = 1;
		ResultSet rs = null;
		
		try{
			pst = con.prepareStatement(sqlString);
			rs = pst.executeQuery();
			
			while(rs.next()){
				useID = rs.getInt(1);
			}
			
			sqlString = "insert into nodes(radioID, lastUpdated) values(?, now())";
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, useID);
			executeStatement(pst);
			
			return useID;
			
		} catch (SQLException ex){
			System.err.println(ex.toString());
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
//                if (con != null) {
//                    con.close();
//                }

            } catch (SQLException ex) {
            	System.err.println(ex.toString());
            }			
		}
		
		return 0;
		
	}

	@Override
	public ArrayList<Node> getNodeList() {
		ResultSet rs = null;
		
		try{
			String sqlString = "select * from nodes";
			pst = con.prepareStatement(sqlString);
			rs = pst.executeQuery();
			
			ArrayList<Node> resultList = new ArrayList<Node>();
			Node newNode;
			
			while(rs.next()){
				newNode = new Node(this);
				newNode.setRadioID(rs.getInt(1));
				newNode.setRelayNode(rs.getBoolean(4));
				newNode.setSketchName(rs.getString(2));
				newNode.setSketchVersion(rs.getString(3));				
				resultList.add(newNode);
			}
			rs.close();
			pst.close();
			
			for(Node node : resultList){
				sqlString = "select * from sensors where radioID = ?";
				pst = con.prepareStatement(sqlString);
				pst.setInt(1, node.getRadioID());
				rs = pst.executeQuery();
				
				Sensor newSensor;
				
				while(rs.next()){
					newSensor = new Sensor(this);
					newSensor.setRadioID(rs.getInt(1));
					newSensor.setChildID(rs.getInt(2));
					newSensor.setSensorType(SensorType.getSensorType(rs.getInt(3)));
					newSensor.setSensorSketchName(rs.getString(4));
					newSensor.setSensorSketchVersion(rs.getString(5));
					
					node.addSensorToNode(newSensor);
				}
				rs.close();
				pst.close();
			}
			
			
			return resultList;
			
		} catch (SQLException ex){
			System.err.println(ex.toString());
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
//                if (con != null) {
//                    con.close();
//                }

            } catch (SQLException ex) {
            	System.err.println(ex.toString());
            }			
		}
		
		return null;
	}

	@Override
	public void saveSensorValue(Sensor sensor, SensorValue sensorValue) {
		String sqlString = "replace into sensorsvalue(radioID, childID, sensorType, sensorValue, timestamp) values(?, ?, ?, ?, now())";
    	try{
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, sensor.getRadioID());
			pst.setInt(2, sensor.getChildID());
			pst.setInt(3, sensorValue.getSensorValueType().ordinal());
			pst.setString(4,sensorValue.getValue());
			executeStatement(pst);
    	} catch (SQLException ex){
    		System.err.println(ex.toString());
    	}
		
	}

	@Override
	public SensorValue getLastSensorValue(Sensor sensor, SensorValueType sensorValueType) {
		
		String sqlString = "SELECT sensorType, sensorValue, timestamp from sensorsValue where radioID=? and childID=?";
		ResultSet rs = null;
		
		try{
			pst = con.prepareStatement(sqlString);
			pst.setInt(1, sensor.getRadioID());
			pst.setInt(2, sensor.getChildID());

			rs = pst.executeQuery();

			SensorValue sValue = new SensorValue();
			
			while(rs.next()){
				sValue.setValue(rs.getString(2));
				sValue.setSensorValueType(SensorValueType.getSensorValueType(rs.getInt(1)));
			}
			
			return sValue;
			
		} catch (SQLException ex){
			System.err.println(ex.toString());
		} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
            	System.err.println(ex.toString());
            }			
		}
		
		return null;				
	}

}
