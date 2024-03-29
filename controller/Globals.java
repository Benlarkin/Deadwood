package controller;
// Global constants and literals used across Classes.
public class Globals {
   public static final String PLAYMSG = "Please enter number of players.";
   public static final String ERROR = " error in Board.";
   public static final String EMPTY = "";
   public static final String CASTING = "Office";
   public static final String IN = "is in ";
   public static final String SPACE = " ";
   public static final String MAXRANK = "You are already max rank!";
   public static final String OFFICE = "Office";
   public static final String RANKREQUEST5 = "What rank would you like to promote to? (6). You are currently rank: ";
   public static final String RANKREQUEST = "What rank would you like to promote to? (";
   public static final String RANKREQUEST_PT2 = "-6). You are currently rank: ";
   public static final String CURRENCYMSG1 = "You currently have ";
   public static final String CURRENCYMSG2 = " dollars, and ";
   public static final String CURRENCYMSG3 = " credits";
   public static final String DESIREDCURRENCYMSG = "What currency will you use to upgrade? (d/c)";
   public static final String INSUFFICIENTCURRENCY = "Insufficient Currency";
   public static final String CREDITS_AS_STRING = "c";
   public static final String DOLLAR_STRING = "dollars";
   public static final String CREDITS_STRING = "credits";
   public static final String RANK_STRING = "Rank ";
   public static final String COST_EQUALS_STRING = " cost = ";
   public static final String NAMEREQ = " Name: ";
   public static final String INPUT = "Input Player ";
   public static final String SCOREMSG =  "%d: %s with a score of %d\n";
   public static final String PLAYERERR = "Must have 2-8 players.";
   public static final String MOEVEMSG = "Where would you like to move? ";
   public static final String INVALIDMSG = "Invalid room. Choose again.";
   public static final String SCENEMSG = "Would you like to act or rehearse? (Type: act/rehearse/active/where/end): ";
   public static final String TURNMSG = "It is %s's turn (Type: %s%sactive/where/end): ";
   public static final String EXTRAROLEMSG = "Extra Role: %s (Requirement: %d) \n";
   public static final String STARRINGROLEMSG = "Starring Role: %s (Requirement: %d) \n";
   public static final String PICKROLE = "Enter the name of your desired role.";
   public static final String ACTMSG = "You successfully acted your scene!";
   public static final String FAILACTMSG = "You failed to act your scene. Better luck next time!";
   public static final String REHEARSEMSG = "You rehearsed your scene and earned 1 rehearsal chip.";
   public static final String MOVESUCC = "%s moved from %s to %s.\n";
   public static final String WHEREMSG = "in %s\n";
   public static final String WHEREMSGWITHROLE = "in %s shooting %s scene %d\n";
   public static final String WORK = "work/";
   public static final String ACT = "act/";
   public static final String W = "w";
   public static final String H = "h";
   public static final String X = "x";
   public static final String Y = "y";
   public static final String SHOTCOUNTER_IMAGE = "images/shot.png";
   public static final String IMG = "img";
   public static final String AREA = "area";
   public static final String ACTACTION = "act";
   public static final String REHEARSEACTION = "rehearse";
   public static final String REHEARSE = "rehearse/";
   public static final String WORKACTION = "work";
   public static final String PROMOTE = "promote/";
   public static final String PROMOTEACTION = "promote";
   public static final String TRAILER = "Trailers";
   public static final String END = "end";
   public static final String MOVEPLAYER = "move/";
   public static final String ACTIVE = "active";
   public static final String WHERE = "where";
   public static final String ACTIVEMSG = "\nThe active player is %s. They have $%d, %d credits, and %d fame.\n";
   public static final String BLANK = "";
   public static final String MOVE = "move";
   public static final String ROLEMSG = "They are working %s, \"%s\"\n\n";
   public static final String CARDFILE = "model/cards.xml";
   public static final String BOARDFILE = "model/board.xml";
   public static final String CARDIMGLOCAL = "images/cards/%s";
   public static final String CARD = "card";
   public static final String NAME = "name";
   public static final String BUDGET = "budget";
   public static final String SCENE = "scene";
   public static final String NUMBER = "number";
   public static final String PART = "part";
   public static final String LEVEL = "level";
   public static final String LINE = "line";
   public static final String SET = "set";
   public static final String NEIGHBOR = "neighbor";
   public static final String TAKE = "take";
   public static final String INVALIDACTIONMSG = "Invalid command, please try again.";
   public static final int PLAYEREND = 0;
   public static final int PLAYERMOVE = 1;
   public static final int INVALIDACTION = -1;
   public static final int SUCCESSACTION = 3;
   public static final int PLAYERWORK = 2;
   public static final int FOUND = 4;
   public static final int NOTFOUND = 5;
   public static final int SUCCESSACTACTION = 6;
   public static final String NOPSBLEROLES = "You do not have enough fame \n to take any roles here.";
   public static final String DOLLARS = "Dollars";
   public static final String CREDITS = "Credits";
   public static final String REHEARSALPOPUP = "%s spent their turn rehearsing. They now have %d rehearsal chips.";
   public static final String ACTPOPUP = "%s %s their role this turn with a roll of %d (+%d).";
   public static final String SUCCESS = "succeeded on acting";
   public static final String FAIL = "failed to act";
   public static final String[] PLAYERDICE = {"images/dice/b%d.png",
                                              "images/dice/c%d.png",
                                              "images/dice/g%d.png",
                                              "images/dice/o%d.png",
                                              "images/dice/p%d.png",
                                              "images/dice/r%d.png",
                                              "images/dice/v%d.png",
                                              "images/dice/y%d.png"};

}
