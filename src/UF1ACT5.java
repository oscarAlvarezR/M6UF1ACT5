import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UF1ACT5 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		Scanner teclado = new Scanner(System.in);

		try {
			File archivo = new File("alumnes.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();

			NodeList listaAlumnos = document.getDocumentElement().getChildNodes();

			for (int i = 0; i < listaAlumnos.getLength(); i++) {
				Node node = listaAlumnos.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) node;
					
					if (node.hasChildNodes()) {
						
						if (node.getAttributes().getNamedItem("id") != null){

							Element e = (Element) node;
							e.setIdAttribute("id", true);
						}
					}

					//if(eElement.hasChildNodes()) {
						//NodeList nl = node.getChildNodes();
						//recursivaSenseMostrar(nl);
					//}
				}
			}

			boolean parar = false;
			
			while (!parar) {
				
				System.out.println("<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>");
				System.out.println("0 Salir del programa");
				System.out.println("1 Mostrar XML:");
				System.out.println("2 Situarse en element XML a partir de la id:");
				int seleccio = teclado.nextInt();

				if (seleccio == 1) {

					try {
						//					File archivo = new File("alumnes.xml");
						//					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						//					DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
						//					Document document = documentBuilder.parse(archivo);
						//					document.getDocumentElement().normalize();
						System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
						System.out.println();

						//					listaAlumnos = document.getDocumentElement().getChildNodes();

						for (int i = 0; i < listaAlumnos.getLength(); i++) {
							Node node = listaAlumnos.item(i);

							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) node;

								if(eElement.hasChildNodes()) {
									NodeList nl = node.getChildNodes();
									System.out.println(eElement.getNodeName() + " id: " + eElement.getAttribute("id"));

									recursiva(nl);
								}
								System.out.println();
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (seleccio == 2) {

					//				File archivo = new File("alumnes.xml");
					//				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					//				DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
					//				Document document = documentBuilder.parse(archivo);

					System.out.println("Introdueix la ID:");
					int seleccioId = teclado.nextInt();

					Element eElement = document.getElementById(String.valueOf(seleccioId));
					
					System.out.println("1 Editar Elements");
					System.out.println("2 Editar Atributs");
					int seleccioEdicio = teclado.nextInt();
					
					if (seleccioEdicio == 1) {
						
						System.out.println("1 Crear Element");
						System.out.println("2 Modificar Element");
						System.out.println("3 Eliminar Element");
						seleccioEdicio = teclado.nextInt();
						
						if (seleccioEdicio == 1) {
							
							eElement = document.createElement("nouElement");
						}
						
					} else if (seleccioEdicio == 2) {
						
					}
					
				} else if (seleccio == 0) {
					
					parar = true;
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void recursivaSenseMostrar (NodeList nl) {

		for(int j=0; j<nl.getLength(); j++) {
			Node nd = nl.item(j);
			String nodeName = nd.getNodeName();
			if (nd.hasChildNodes()){
				NodeList nl2 = nd.getChildNodes();
				recursiva(nl2);
			}
		}
	}
	public static void recursiva (NodeList nl){

		for(int j=0; j<nl.getLength(); j++) {
			Node nd = nl.item(j);
			String nodeName = nd.getNodeName();
			if (!nodeName.equals("#text")){
				System.out.println(nodeName + ": " + nd.getTextContent());
			}
			if (nd.hasChildNodes()){
				NodeList nl2 = nd.getChildNodes();
				recursiva(nl2);
			}
		}
	}
}
