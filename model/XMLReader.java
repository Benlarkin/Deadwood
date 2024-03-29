package model;
  /* Instructions on how to begin XML parsing were learned from
   * https://stackoverflow.com/questions/428073/what-is-the-best-simplest-way-to-read-in-an-xml-file-in-java-application
   */
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;
import org.w3c.dom.*;
import controller.*;
public class XMLReader extends Globals {


   // Creates a List of Rooms to make up the board from an XML file.
   public List<Room> arrangeBoard() {
      try {
         File file = new File(BOARDFILE);
         DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
         DocumentBuilder build = buildFact.newDocumentBuilder();
         Document doc = build.parse(file);
         doc.getDocumentElement().normalize();
         return boardBuilder(doc);
      }
      catch (Exception e) {
         return null;
      }
   }

   // Parses an XML file to create individual Rooms to add to the list for the Board.
   private List<Room> boardBuilder(Document doc) {
      List<Room> spaceList = new ArrayList<Room>();
      NodeList roomList = doc.getElementsByTagName(SET);
      for(int i = 0; i < roomList.getLength(); i++) {
         spaceList.add(makeRoom(roomList.item(i)));
      }
      spaceList.add(makeTrailer(doc.getElementsByTagName(TRAILER).item(0)));
      spaceList.add(makeOffice(doc.getElementsByTagName(OFFICE).item(0)));
      return spaceList;
   }

   // Makes and returns a single Trailer Room from the given XML Node
   private Room makeTrailer(Node trailNode) {
      List<String> adjacent = new ArrayList<String>();
      Element trailElement = (Element) trailNode;
      Area location = makeArea(trailElement.getElementsByTagName(AREA).item(0));
      NodeList neighborList = trailElement.getElementsByTagName(NEIGHBOR);
      for(int i = 0; i < neighborList.getLength(); i++) {
         Element neighborElement = (Element) neighborList.item(i);
         adjacent.add(neighborElement.getAttribute(NAME));
      }
      return new Trailers(adjacent, location);
   }

   // Makes and returns a single CastingOffice Room from the given XML Node
   private Room makeOffice(Node offNode) {
      List<String> adjacent = new ArrayList<String>();
      Element offElement = (Element) offNode;
      NodeList neighborList = offElement.getElementsByTagName(NEIGHBOR);
      Area location = makeArea(offElement.getElementsByTagName(AREA).item(0));
      for(int i = 0; i < neighborList.getLength(); i++) {
         Element neighborElement = (Element) neighborList.item(i);
         adjacent.add(neighborElement.getAttribute(NAME));
      }
      return new CastingOffice(adjacent, location);
   }

    // Makes individual Rooms from the XML file.
    private Room makeRoom(Node setNode) {
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
      ArrayList<Area> takeLocations = new ArrayList<Area>();
      for(int k = 0; k<setTakes; k++){
    	  takeLocations.add(makeArea(((Element) setElement.getElementsByTagName(TAKE).item(k)).getElementsByTagName(AREA).item(0)));
      }
      Area location = makeArea(setElement.getElementsByTagName(AREA).item(0));
      NodeList roleList =  setElement.getElementsByTagName(PART);
      List<Role> setRoles = new ArrayList<Role>();
      for(int j = 0; j < roleList.getLength(); j++) {
         Element roleElement = (Element) roleList.item(j);
         setRoles.add(makeRole(roleElement, false));
      }
      return new MovieSet(setName, setTakes, setNeighbors, setRoles, location, takeLocations);
   }

   // Makes a new Role. If the Role is on the card, it is a StarringRole, else its an ExtraRole.
   private Role makeRole(Node roleNode, boolean star) {
      Element roleElement = (Element) roleNode;
      String roleName = roleElement.getAttribute(NAME);
      int roleReq = Integer.parseInt(roleElement.getAttribute(LEVEL));
      String roleLine = roleElement.getElementsByTagName(LINE).item(0).getTextContent();
      Area location = makeArea(roleElement.getElementsByTagName(AREA).item(0));
      if(star) {
         return new StarringRole(roleName, roleLine, roleReq, location);
      }
      else {
         return new ExtraRole(roleName, roleLine, roleReq, location);
      }
   }

   // Makes a new deck of cards.
   public List<Card> makeDeck() {
      try {
         File file = new File(CARDFILE);
         DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
         DocumentBuilder build = buildFact.newDocumentBuilder();
         Document doc = build.parse(file);
         doc.getDocumentElement().normalize();
         List<Card> resultDeck = cardBuilder(doc);
         Collections.shuffle(resultDeck);
         return resultDeck;
      }
      catch(Exception e) {
         return null;
      }
   }

   // Builds the deck of cards out of an XML file.
   private List<Card> cardBuilder(Document doc) {
      List<Card> deckList = new ArrayList<Card>();
      NodeList cardList = doc.getElementsByTagName(CARD);
      for(int i = 0; i < cardList.getLength(); i++) {
         deckList.add(makeCard(cardList.item(i)));
      }
      return deckList;
   }

   // Makes individual cards to add to the list for the deck.
   private Card makeCard(Node cardNode) {
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
      String cardImg = cardElement.getAttribute(IMG);
      return new Card(cardRoles, cardName, cardLine, cardBudget, sceneNumber, String.format(CARDIMGLOCAL,cardImg));
   }

   // Stores the area from the XML as an Area
   private Area makeArea(Node areaNode) {
	   Element areaElement = (Element) areaNode;
	   int xArea = Integer.parseInt(areaElement.getAttribute(X));
	   int yArea = Integer.parseInt(areaElement.getAttribute(Y));
	   int hArea = Integer.parseInt(areaElement.getAttribute(H));
	   int wArea = Integer.parseInt(areaElement.getAttribute(W));
	   return new Area(xArea, yArea, hArea, wArea);
   }

}
