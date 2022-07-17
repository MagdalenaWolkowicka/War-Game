package card;


import card.model.Card;
import card.model.Rank;
import card.model.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardService {

    private List<Card> allCards = createDeck();
    private List<Card> shuffledCards = shuffleCards(allCards);
    //private List<Card> player1Cards = dealTheCards();
    //private List<Card> player2Cards = chooseCardsForPlayer2(allCards, player1Cards);


    public CardService() {
    }

    public void start() {
        System.out.println("Gra w wojnę ;)");
    }

    public List<Card> shuffleCards(List<Card> allCards) {
        List<Card> shuffledCards = new ArrayList<>();
        Random random = new Random();
        while (shuffledCards.size() != 52) {
            int number = random.nextInt(52);
            if (!shuffledCards.contains(allCards.get(number))) {
                shuffledCards.add(allCards.get(number));
            }
        }
        return shuffledCards;
    }

    public List<Card> getPlayer1Cards(List<Card> shuffledCards) {
        List<Card> player1Cards = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            player1Cards.add(shuffledCards.get(i));
        }
        return player1Cards;
    }

    public List<Card> getPlayer2Card(List<Card> shuffleCards) {
        List<Card> player2Cards = new ArrayList<>();
        for (int i = 26; i < 52; i++) {
            player2Cards.add(shuffleCards.get(i));
        }
        return player2Cards;
    }


    public Card compareCards(Card card1, Card card2) {
        Card biggerCard = null;
        if (card1.getRankNumber() > card2.getRankNumber()) {
            System.out.println(card1);
            biggerCard = card1;
        } else if (card1.getRankNumber() == card2.getRankNumber()) {
            System.out.println(card1);
            System.out.println(card2);
            System.out.println("Karty są równe");
        } else {
            System.out.println(card2);
            biggerCard = card2;
        }
        System.out.println(card1 + " " + card2);
        return biggerCard;
    }

    public void play(List<Card> player1Cards, List<Card> player2Cards) {
        int count1 = 0;
        int count2 = 0;
        int counter = 1;
        while (player1Cards.size() != 1 || player2Cards.size() != 1) {
            System.out.println(counter);
            if (count2 >= player2Cards.size()) {
                count2 = 0;
            }
            if (count1 >= player1Cards.size()) {
                count1 = 0;
            }
            Card card = compareCards(player1Cards.get(count1), player2Cards.get(count2));
            if (player1Cards.contains(card)) {
                player1Cards.add(player2Cards.get(count2));
                player2Cards.remove(count2);
                System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
            } else {
                player2Cards.add(player1Cards.get(count1));
                player1Cards.remove(count1);
                System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
            }
            count1++;
            count2++;
            counter++;
            if (player1Cards.size() == 0 || player2Cards.size() == 0) {
                int winnerNumber = 0;
                if (player1Cards.size() == 0) {
                    winnerNumber = 2;
                } else {
                    winnerNumber = 1;
                }
                System.out.println("Koniec gry. Wygrał gracz nr " + winnerNumber);
                return;
            }
        }



    }

    public List<Card> createSpadeCards() {
        ArrayList<Card> pikCardsList = new ArrayList<>();
        for (Rank value : Rank.values()) {
            pikCardsList.add(new Card(Suit.SPADE, value));
        }
        return pikCardsList;
    }

    public ArrayList<Card> createDeck() {
        ArrayList<Card> cardsList = new ArrayList<>();
        for (Suit suitValue : Suit.values()) {
            for (Rank value : Rank.values()) {
                cardsList.add(new Card(suitValue, value));
            }
        }
        return cardsList;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

//    public List<Card> getPlayer1Cards() {
//        return player1Cards;
//    }
}
