import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;
import org.w3c.dom.*;


public class Deadwood {
  private final String FILENAME = "cards.xml";
  private final String TAGNAME = "card";
  private final String NAME = "name";
  private final String BUDGET = "budget";
  private final String SCENE = "scene";
  private final String NUMBER = "number";
  private final String PART = "part";
  private final String LEVEL = "level";
  private final String LINE = "line";

  public List<Player> players;
  public Board board;
  public List<Card> deck;
  public Banker banker;
  private Timer timer;

  public Deadwood() {
    try {

    deck = makeDeck(FILENAME);
  }
  catch (Exception e) {

  }
  }

  public void addPlayer(Player newPlayer) {

  }

  public void startGame() {

  }

  private List<Card> makeDeck(String fileName) {
	  try {
    File file = new File(fileName);
    DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
    DocumentBuilder build = buildFact.newDocumentBuilder();
    Document doc = build.parse(file);
    doc.getDocumentElement().normalize();
    return cardBuilder(doc);
	  }
	  catch(Exception e) {
		  return null;
	  }
  }

  private List<Card> cardBuilder(Document doc) {
    List<Card> deckList = new ArrayList<Card>();
    NodeList cardList = doc.getElementsByTagName(TAGNAME);
    for(int i = 0; i < cardList.getLength(); i++) {
        deckList.add(makeCard(cardList.item(i)));
    }
    return deckList;
  }

  private Card makeCard(Node cardNode) {
    Element cardElement = (Element) cardNode;
    Node sceneElement = cardElement.getElementsByTagName(SCENE).item(0);
    String cardName = cardElement.getAttribute(NAME);
    String cardLine = sceneElement.getTextContent();
    int cardBudget = Integer.parseInt(cardElement.getAttribute(BUDGET));
    int sceneNumber = Integer.parseInt(((Element) sceneElement).getAttribute(NUMBER));
    NodeList roleList = cardElement.getElementsByTagName(PART);
    List<Role> cardRoles = new ArrayList<Role>();
    for(int i = 0; i < roleList.getLength(); i++) {
      cardRoles.add(makeRole(roleList.item(i)));
    }
    return new Card(cardRoles, cardName, cardLine, cardBudget, sceneNumber, null);
  }


  private Role makeRole(Node roleNode) {
    Element roleElement = (Element) roleNode;
    String roleName = roleElement.getAttribute(NAME);
    int roleReq = Integer.parseInt(roleElement.getAttribute(LEVEL));
    String roleLine = roleElement.getElementsByTagName(LINE).item(0).getTextContent();
    return new StarringRole(roleName, roleLine, roleReq);
  }

}
