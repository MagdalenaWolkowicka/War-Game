package card.service;


import card.controller.Controller;
import card.model.Card;
import card.model.Rank;
import card.model.Suit;

import java.util.*;

public class CardService {

    private Controller controller = new Controller();
    private List<Card> allCards = createDeck();
    private List<String> playerNames = controller.getPlayerNames();
    private int time = 0;


    public void start() {
        playGame(getPlayer1Cards(allCards), getPlayer2Cards(allCards));
    }

    public List<Card> getPlayer1Cards(List<Card> allCards) {
        List<Card> player1Cards = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            player1Cards.add(allCards.get(i));
        }
        return player1Cards;
    }

    public List<Card> getPlayer2Cards(List<Card> allCards) {
        List<Card> player2Cards = new ArrayList<>();
        for (int i = 26; i < 52; i++) {
            player2Cards.add(allCards.get(i));
        }
        return player2Cards;
    }

    public int compareCardsReturnNumber(Card card1, Card card2) {
        int number;
        if (card1.getRankNumber() > card2.getRankNumber()) {
            number = 1;
        } else if (card1.getRankNumber() == card2.getRankNumber()) {
            number = 0;
        } else {
            number = 2;
        }
        return number;
    }

    public void playGame(List<Card> player1Cards, List<Card> player2Cards) {
        List<Card> wonCards = new ArrayList<>();
        int winnerNumber = 0;
        int counter = 1;
        int time = 0;
        while (!player1Cards.isEmpty() || !player2Cards.isEmpty()) {
            if (winnerNumber != 0) {
                System.out.println(counter);
            }
            Card card1 = player1Cards.get(0);
            Card card2 = player2Cards.get(0);
            controller.printPlayersCards(card1, card2);
            wonCards.add(card1);
            wonCards.add(card2);
            winnerNumber = compareCardsReturnNumber(card1, card2);
            player1Cards.remove(card1);
            player2Cards.remove(card2);
            if (winnerNumber == 1) {
                controller.printWhoseCardIsBigger(1);
                addWonCards(player1Cards, wonCards);
                counter++;
                time = time + 3;
            } else if (winnerNumber == 2) {
                controller.printWhoseCardIsBigger(2);
                addWonCards(player2Cards, wonCards);
                counter++;
                time = time + 3;
            } else if (winnerNumber == 0) {
                if (player1Cards.size() < 2) {
                    controller.checkPlayerCardListSize(time, playerNames.get(1), 2);
                    return;
                } else if (player2Cards.size() < 2) {
                    controller.checkPlayerCardListSize(time, playerNames.get(0), 1);
                    return;
                }
                controller.printCardsAreEquals();
                Card card11 = player1Cards.get(0);
                Card card22 = player2Cards.get(0);
                wonCards.add(card11);
                wonCards.add(card22);
                controller.printInfoPlayersGiveCoveredCards();
                player1Cards.remove(card11);
                player2Cards.remove(card22);
                time = time + 6;
                continue;
            }
            controller.printNumberOfPlayersCards(player1Cards, player2Cards);
            if (checkWinner(player1Cards, player2Cards, time)) return;
        }
    }

    private void addWonCards(List<Card> player1Cards, List<Card> wonCards) {
        Collections.shuffle(wonCards);
        player1Cards.addAll(wonCards);
        wonCards.clear();
    }

    private boolean checkWinner(List<Card> player1Cards, List<Card> player2Cards, int time) {
        String playerName = " ";
        if (player1Cards.size() == 0 || player2Cards.size() == 0) {
            int winnerNumber = 0;
            if (player1Cards.size() == 0) {
                winnerNumber = 2;
                playerName = playerNames.get(1);
            } else {
                winnerNumber = 1;
                playerName = playerNames.get(0);
            }
            controller.printWinnerNameAndGameTime(time, playerName, winnerNumber);
            return true;
        }
        return false;
    }

    public ArrayList<Card> createDeck() {
        ArrayList<Card> cardsList = new ArrayList<>();
        for (Suit suitValue : Suit.values()) {
            for (Rank value : Rank.values()) {
                cardsList.add(new Card(suitValue, value));
            }
        }
        Collections.shuffle(cardsList);
        return cardsList;
    }


}
