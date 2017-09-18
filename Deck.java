/*
 * Deck.java
 * Emily Van Laarhoven
 * CS230 Problem 4
 * Due: 11/12/16 @11:59pm
 */

/*
 * I believe this code works perfectly - I have commented out the print statements, 
 * but I have tested multiple times how the constructed deck prints as well as the methods
 * and especially the shuffle method.
 * I'm concerned that the shuffle method is expensive in terms of runtime, but it's the 
 * most authentically random method I could come up with for shuffling a deck of cards.
 */

import java.util.*;

/**
 * Represents a collection of cards
 */
public class Deck{
  // The initial number of cards in the Deck
  private final int NUM_OF_CARDS = 52;
  // The stack of cards in the Deck
  Stack<Card> allCards;
  
  /**
   * Constructor that fills the deck with Card objects
   */
  public Deck(){
    allCards = new Stack<Card>();
    String [] suits = new String[4];
    suits[0]="spade";
    suits[1]="heart";
    suits[2]="club";
    suits[3]="diamond";
    for (int j=0; j<suits.length; j++) {
      for (int i=1; i<14; i++) { 
        Card nextCard = new Card(i,suits[j]);
        allCards.push(nextCard);
      }
    }
  }
  
  /**
   * Shuffles the cards within this Deck object
   */
  public void shuffle(){
    Card[] cardArray = new Card[52];
    Hashtable<Integer,String> usedNums = new Hashtable <Integer,String>();
    Random rand = new Random();
    int arrayNum = rand.nextInt(52);
    for (int i=0; i<52; i++) {
      while (usedNums.containsKey(arrayNum)) { //make sure index hasn't been used before
        arrayNum = rand.nextInt(52);
      }
      cardArray[arrayNum] = allCards.pop();
      usedNums.put(arrayNum,"");
    }
    for (int j=0; j<52; j++) {
      allCards.push(cardArray[j]);
    }
  }

  /** 
   * Checks if there are any more cards in the deck
   * @return True if there is at least one more card
   */
  public boolean hasNext(){
    return !(allCards.isEmpty());
  }
  
  /**
   * Retrieves the next card from the deck
   * @return The top card from the deck
   */
  public Card getNext(){
   return allCards.pop();
  }
    
  
  /** 
   * Gets the original size of the deck of cards
   * @return Number of cards originally in the deck
   */
  public int originalSize(){
    return NUM_OF_CARDS;
  }
  
  
  // main method added for testing only
  public static void main(String [] args) {
    Deck test = new Deck();
//    for(int i=0; i<52; i++) {
//      System.out.println(test.getNext());
//    }
    test.shuffle();
    for(int i=0; i<52; i++) {
      System.out.println(test.getNext());
    }
    System.out.println(test.hasNext());

  }
  
    
}
