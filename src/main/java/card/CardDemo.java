package card;

import card.model.Card;

import java.util.List;

public class CardDemo {

    public static void main(String[] args) {

        CardService cardService = new CardService();
        List<Card> allCards = cardService.getAllCards();
        List<Card> shuffledCards = cardService.shuffleCards(allCards);
//        for (Card shuffledCard : shuffledCards) {
//            System.out.println(shuffledCard);
//        }
//        System.out.println(" ");
        List<Card> player1Cards = cardService.getPlayer1Cards(shuffledCards);
//        for (Card player1Card : player1Cards) {
//            System.out.println(player1Card);
//        }
        System.out.println(" ");
        List<Card> player2Cards = cardService.getPlayer2Card(shuffledCards);
//        for (Card player2Card : player2Cards) {
//            System.out.println(player2Card);
//        }
        System.out.println("");
        cardService.play(player1Cards, player2Cards);


//        Card card1 = new Card(Suit.DIAMOND, Rank.QUEEN);
//        Card card2 = new Card(Suit.HEART, Rank.KING);
//
//        System.out.println(card1);
//        System.out.println(card2);
//
//        for (Rank value : Rank.values()) {
//            System.out.print(value + " ");
//        }
//        System.out.println("");
//
//        int numberCard1 = card1.getRankNumber();
//        System.out.println(numberCard1);
//
//        CardService cardService = new CardService();
//        cardService.compareCards(card1, card2);
//        System.out.println("");
//
//        List<Card> cardListPik = cardService.createSpadeCards();
//        for (Card card : cardListPik) {
//            System.out.println(card);
//        }
//        System.out.println("");
//
//        ArrayList<Card> cardDeck = cardService.createDeck();
//        for (Card card : cardDeck) {
//            System.out.println(card);
//        }

    }
}
