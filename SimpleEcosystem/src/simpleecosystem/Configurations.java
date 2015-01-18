package simpleecosystem;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("unchecked")
// Class that reads configuration file and enables aeasz configuration querying
public class Configurations
{
	// Root configurations
	public static Configurations Root;
	
	// Initializes configurations from default configurations file
	public static void Init() throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse("bin\\configurations\\Configurations.xml");
		Root = new Configurations(dom.getFirstChild());
	}
	
	// Mapping configName to list of configurations
	private HashMap<String, ArrayList<Object>> configurationMap = new HashMap<String, ArrayList<Object>>(); 
	
	// Constructor	
	public Configurations(Node rootNode)
	{
		NodeList nodeList = rootNode.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				String name = node.getNodeName().toLowerCase();		
				Node valueNode = node.getChildNodes().item(0);
				
				if(!configurationMap.containsKey(name))
				{
					configurationMap.put(name, new ArrayList<Object>());
				}
				
				if(node.getChildNodes().getLength() == 1 && valueNode.getNodeType() == Node.TEXT_NODE)
				{					
					Object obj = valueNode.getNodeValue();

					try
					{
						obj = Float.parseFloat(valueNode.getNodeValue());
					}
					catch(Exception e) {}
					
					configurationMap.get(name).add(obj);					
				}
				else
				{
					configurationMap.get(name).add(new Configurations(node));
				}
			}
		}
	}
	
	// Get generic element for given configName - Configurations, String, or Float
	public <T> T Get(String configName)
	{
		// divide path
		if(configName.contains("."))
		{
			String firstName = configName.substring(0, configName.indexOf("."));
			String lastName = configName.substring(configName.indexOf(".") + 1);
			
			Configurations firstConfigurations = this.<Configurations>Get(firstName);
			if(firstConfigurations == null)
			{
				return null;
			}
			
			return firstConfigurations.<T>Get(lastName);
		}
		
		// return first castable object
		if(configurationMap.get(configName.toLowerCase()) != null)
		{
			for(Object obj : configurationMap.get(configName.toLowerCase()))
			{
				try
				{
					return (T) obj;
				}
				catch(Exception e)
				{}
			}			
		}
		
		return null;
	}

	// Get a list of generic elements for given configName - Configurations, String, or Float
	public <T> ArrayList<T> GetAll(String configName)
	{
		ArrayList<T> ret = new ArrayList<T>();

		// divide path
		if(configName.contains("."))
		{
			String firstName = configName.substring(0, configName.indexOf("."));
			String lastName = configName.substring(configName.indexOf(".") + 1);
			
			ArrayList<Configurations> allFirstConfigurations = this.<Configurations>GetAll(firstName);
			if(allFirstConfigurations == null)
			{
				return null;
			}
			
			for(Configurations firstConfigurations : allFirstConfigurations)
			{
				ret.addAll(firstConfigurations.<T>GetAll(lastName));
			}
			
			return ret;
		}
		
		// return all castable objects
		if(configurationMap.get(configName.toLowerCase()) != null)
		{
			for(Object obj : configurationMap.get(configName.toLowerCase()))
			{
				try
				{
					ret.add((T) obj);
				}
				catch(Exception e) {}
			}				
		}
		
		return ret;
	}
	
	public static Configurations GetMapConfigurations(String mapName)
	{
		ArrayList<Configurations> allMapConfigurations = Root.<Configurations>GetAll("Map");
		
		for(Configurations mapConfigurations : allMapConfigurations)
		{
			if(mapName.equals(mapConfigurations.<String>Get("Name")))
			{
				return mapConfigurations;
			}
		}
			
		return null;
	}
}
