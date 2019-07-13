import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;
import org.w3c.dom.*;

public class XMLReader {


private final String CARD = "card";
private final String NAME = "name";
private final String BUDGET = "budget";
private final String SCENE = "scene";
private final String NUMBER = "number";
private final String PART = "part";
private final String LEVEL = "level";
private final String LINE = "line";
private final String SET = "set";
private final String NEIGHBOR = "neighbor";
private final String TAKE = "take";


  public List<Room> arrangeBoard(String fileName) {
    try {
      File file = new File(fileName);
      DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
      DocumentBuilder build = buildFact.newDocumentBuilder();
      Document doc = build.parse(file);
      doc.getDocumentElement().normalize();
      return boardBuilder(doc);
    }
    catch (Exception e) {
    	 System.out.println("3");
      return null;
    }
  }

  public List<Room> boardBuilder(Document doc) {
      List<Room> spaceList = new ArrayList<Room>();
      NodeList roomList = doc.getElementsByTagName(SET);
      for(int i = 0; i < roomList.getLength(); i++) {
          spaceList.add(makeRoom(roomList.item(i)));
      }
      return spaceList;
  }

  public Room makeRoom(Node setNode) {
    Element setElement = (Element) setNode;
    String setName = setElement.getAttribute(NAME);
    List<String> setNeighbors = new ArrayList<String>();
    NodeList neighborList = setElement.getElementsByTagName(NEIGHBOR);
    for(int i = 0; i < neighborList.getLength(); i++) {
      Element neighborElement = (Element) neighborList.item(i);
      setNeighbors.add(neighborElement.getAttribute(NAME));
    }
    Element takesElement = (Element) setElement.getElementsByTagName(TAKE).item(0);
    int setTakes = Integer.parseInt(takesElement.getAttribute(NUMBER));
    NodeList roleList =  setElement.getElementsByTagName(PART);
    List<Role> setRoles = new ArrayList<Role>();
    for(int j = 0; j < roleList.getLength(); j++) {
      Element roleElement = (Element) roleList.item(j);
      setRoles.add(makeRole(roleElement, false));
    }
    return new MovieSet(setName, setTakes, setNeighbors, setRoles);
  }




  public Role makeRole(Node roleNode, boolean star) {
    Element roleElement = (Element) roleNode;
    String roleName = roleElement.getAttribute(NAME);
    int roleReq = Integer.parseInt(roleElement.getAttribute(LEVEL));
    String roleLine = roleElement.getElementsByTagName(LINE).item(0).getTextContent();
    if(star) {
          return new StarringRole(roleName, roleLine, roleReq);
    }
    else {
      return new ExtraRole(roleName, roleLine, roleReq);
    }
  }

  public List<Card> makeDeck(String fileName) {
    try {
    File file = new File(fileName);
    DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
    DocumentBuilder build = buildFact.newDocumentBuilder();
    Document doc = build.parse(file);
    doc.getDocumentElement().normalize();
    return cardBuilder(doc);
    }
    catch(Exception e) {
    	 System.out.println("4");
      return null;
    }
  }

  public List<Card> cardBuilder(Document doc) {
    List<Card> deckList = new ArrayList<Card>();
    NodeList cardList = doc.getElementsByTagName(CARD);
    for(int i = 0; i < cardList.getLength(); i++) {
        deckList.add(makeCard(cardList.item(i)));
    }
    return deckList;
  }

  public Card makeCard(Node cardNode) {
    Element cardElement = (Element) cardNode;
    Node sceneElement = cardElement.getElementsByTagName(SCENE).item(0);
    String cardName = cardElement.getAttribute(NAME);
    String cardLine = sceneElement.getTextContent().trim();
    int cardBudget = Integer.parseInt(cardElement.getAttribute(BUDGET));
    int sceneNumber = Integer.parseInt(((Element) sceneElement).getAttribute(NUMBER));
    NodeList roleList = cardElement.getElementsByTagName(PART);
    List<Role> cardRoles = new ArrayList<Role>();
    for(int i = 0; i < roleList.getLength(); i++) {
      cardRoles.add(makeRole(roleList.item(i), true));
    }
    return new Card(cardRoles, cardName, cardLine, cardBudget, sceneNumber, null);
  }


}
