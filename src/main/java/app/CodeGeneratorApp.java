package app;

import java.util.*;

import org.w3c.dom.*;

import helpers.H;

public class CodeGeneratorApp {

	private static final String XML_PATH = "./Diagrams/MessageOrganizer.xml";
	private static final String classStart = "./Codes/classStart.txt";
	private static NodeList mxCellElements;
	private static NodeList objectElements;
	private static ArrayList<Klasse> klassen = new ArrayList<>();
	private static ArrayList<Assoc> assocs = new ArrayList<>();
	private static ArrayList<Änum> änums = new ArrayList<>();

	public static void main(String[] args){
		
		readClassModel();
		
		writeClasses();
	}

	private static void writeClasses() {

		klassen.forEach(klasse -> {
			
			H.
		});
	}

	/**
	 * 
	 */
	private static void readClassModel() {

		Document doc = H.getXmlDocument(XML_PATH);
		
		mxCellElements = doc.getElementsByTagName("mxCell");
		
		for(int i = 0 ; i < mxCellElements.getLength() ; i++) {
			
			Node node = mxCellElements.item(i);
			
			if(!hasStyle(node)) continue;
			
			if (isAttributes(node)) {
				
				String attributesString = getValue(node);
				System.out.println("We have a some attributes:");
				
				String[] split = attributesString.split("\n");
				HashMap<String, String> attributes = new HashMap<String, String>();
				
				for(int a = 0 ; a < split.length ; a++) {
					
					String line = split[a];
					
					String type = H.substringAfter(line, ": ");
					String name = H.substringBetween(line, "+ ", ": ");
					
					attributes.put(type, name);
					
					System.out.println(type + " " + name);
				}
				
				getKlasse(getParent(node)).withAttributes(attributes);
			}
			if (isClass(node)) {

				Klasse klasse = new Klasse()
									.withName(getClassName(node))
									.withId(getId(node));
				
				klassen.add(klasse);
				
				System.out.println("We have a class \"" + klasse.getName() + "\"");
			}
			if (isÄnum(node)) {

				Änum änum = new Änum()
									.withName(getEnumName(node))
									.withId(getId(node));
				
				änums.add(änum);
				
				System.out.println("We have an enum \"" + änum.getName() + "\"");
			}
		}
		
		// Edges
		objectElements = doc.getElementsByTagName("object");
		
		for(int i = 0 ; i < objectElements.getLength() ; i++) {
			
			Node parentNode = objectElements.item(i);
			NamedNodeMap parentAttributes = parentNode.getAttributes();
			Node FromCardinality = parentAttributes.getNamedItem("FromCardinatliy");
			String fromCard = FromCardinality.getNodeValue();
			Node ToCardinality = parentAttributes.getNamedItem("ToCardinality");
			String toCard = ToCardinality.getNodeValue();
			
			NodeList childNodes = parentNode.getChildNodes();
			Node node = null;
			
			for(int j = 0 ; j < childNodes.getLength() ; j++) {
				Node childNode = childNodes.item(j);
				
				if (childNode.getNodeName().equals("mxCell")) {
					
					node = childNode;
				}
			}
			
			NamedNodeMap attributes = node.getAttributes();
			
			String type = getType(node);
			
			if (isEdge(type)) {
				
				String sourceId = attributes.getNamedItem("source").getNodeValue();
				String targetId = attributes.getNamedItem("target").getNodeValue();
				
				Node source = findIdInMxCells(sourceId);
				Node target = findIdInMxCells(targetId);
				
				if(getType(source).equals("text")) {
					
					source = getParent(source);
				}
				
				if(getType(target).equals("text")) {
					
					target = getParent(target);
				}
				
				Klasse sourceKlasse = getKlasse(source);
				Klasse targetKlasse = getKlasse(target);
				
				Assoc assoc = new Assoc()
								.withFrom(sourceKlasse)
								.withFromName(sourceKlasse.getName()+"s")
								.withFromCard(fromCard)
								.withTo(targetKlasse)
								.withToName(targetKlasse.getName()+"s")
								.withToCard(toCard)
								;
				
				assocs.add(assoc);
				
				System.out.println("We have an edge from " + 
									sourceKlasse + " " + fromCard + " to " + toCard + 
									" " + targetKlasse
									);
			}
		}
	}

	/*
	 * 
	 * Helpers
	 * 
	 */

	private static String getEnumName(Node node) {

		return H.substringAfter(getValue(node), "Enum: ");
	}

	private static boolean isAttributes(Node node) {
		
		// Wenn der Parent keine Klasse ist, dann continue
		if(getParent(node) == null || !isClass(getParent(node))) return false;
		
		String type = getType(node);

		if(type.equals("text")) {
			
			return true;
		}
		
		return false;
	}

	private static Klasse getKlasse(Node source) {
		
		String value = getClassName(source);
		for(Klasse klasse : klassen) {
			
			if(klasse.getName().equals(value)) {
				
				return klasse;
			}
		}

		System.err.println("Klasse mit dem Namen " + value + " wurde nicht gefunden");
		return null; 
	}

	/**
	 * @param source
	 * @return
	 */
	private static String getClassName(Node source) {

		return H.substringAfter(getValue(source), "Class: ");
	}

	private static boolean isÄnum(Node node) {
		
		String value = getValue(node);
		
		if(value == null) return false;

		if(value.contains("Enum")) return true;
		
		return false;
	}
	
	private static boolean isClass(Node node) {
		
		String value = getValue(node);
		
		if(value == null) return false;

		if(value.toUpperCase().startsWith("CLASS:")) {
			
			return true;
		}
		
		return false;
	}

	private static boolean isEdge(Node node) {

		String type = getType(node);
		
		if(type == null) return false;
		
		return isEdge(type);
	}
	
	private static boolean isEdge(String type) {
		
		if(type == null) return false;

		if(type.contains("edgeStyle=")) {
			
			return true;
		}
		
		return false;
	}

	private static String getValue(Node node) {

		Node valueItem = node.getAttributes().getNamedItem("value");
		
		if(valueItem == null) {
			
//			System.err.print(node.getNodeName() + " hat kein value: ");
//			
//			for (int i = 0; i < node.getAttributes().getLength(); i++) {
//				
//				Node item = node.getAttributes().item(i);
//				
//				System.err.print(item + " ");
//			}
//			System.out.println("");
			return null;
		}
		
		String value = valueItem.getNodeValue().toString();
		
		if(value.endsWith("\n")) {
			
			return H.substringBeforeLast(value, "\n");
		}
		
		return value;
	}

	private static Node getParent(Node node) {

		String parentText = node.getAttributes().getNamedItem("parent").getNodeValue().toString();
		
		Node parent = findIdInMxCells(parentText);
		
		if(parent == null) {
			
			System.err.println("Es konnte kein Parent namens " + parentText + " gefunden werden.");
		}
		return parent;
	}

	private static Node findIdInMxCells(String targetId) {

		for(int i = 0 ; i < mxCellElements.getLength() ; i++) {

			Node node = mxCellElements.item(i);
			if(getId(node).equals(targetId)) return node;
			
		}
		
		System.err.println("Die id " + targetId + " existiert in den mxCellElements nicht.");
		return null;
	}

	/**
	 * @param node
	 * @return
	 */
	private static String getId(Node node) {

		Node id = node.getAttributes().getNamedItem("id");
		
		if(id == null) {
			
			id = getParent(node).getAttributes().getNamedItem("id");
		}
		
		if(id == null) {
			
			System.err.println("Die id für Node " + node + " konnte nicht gefunden werden.");
		}
		
		return id.getNodeValue();
	}

	private static String getStyle(Node node) {
		
		Node style = node.getAttributes().getNamedItem("style");
		
		if(style == null) return null;
		
		return style.getNodeValue();
	}

	private static boolean hasStyle(Node node) {
		
		if (getStyle(node) == null) {
			
			return false;
		}
		
		return true;
	}

	/**
	 * @param style
	 * @return
	 */
	private static String getType(Node node) {

		return H.substringBefore(getStyle(node), ";");
	}
}