package card;


import card.model.Card;
import card.model.Rank;
import card.model.Suit;

import java.util.*;

public class CardService {

    private List<Card> allCards = createDeck();
    private int time = 0;


    public CardService() {
    }

    public void start() {
        System.out.println("Gra w wojnę ;)");
        play(getPlayer1Cards(allCards), getPlayer2Card(allCards));
    }


    public List<Card> getPlayer1Cards(List<Card> allCards) {
        List<Card> player1Cards = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            player1Cards.add(allCards.get(i));
        }
        return player1Cards;
    }

    public List<Card> getPlayer2Card(List<Card> allCards) {
        List<Card> player2Cards = new ArrayList<>();
        for (int i = 26; i < 52; i++) {
            player2Cards.add(allCards.get(i));
        }
        return player2Cards;
    }


    public Card compareCards(Card card1, Card card2) {
        Card biggerCard = null;
        if (card1.getRankNumber() > card2.getRankNumber()) {
            biggerCard = card1;
        } else if (card1.getRankNumber() == card2.getRankNumber()) {
            System.out.println("Karty są równe");
        } else {
            biggerCard = card2;
        }
        printPlayersCards(card1, card2);
        return biggerCard;
    }

    private void printPlayersCards(Card card1, Card card2) {
        System.out.println("Karta gracza 1: " + card1 + ", Karta gracza 2: " + card2);
    }

    public void play(List<Card> player1Cards, List<Card> player2Cards) {
        int count1 = 0;
        int count2 = 0;
        int counter = 1;
        while (player1Cards.size() != 1 || player2Cards.size() != 1) {
            System.out.println(counter);
            count2 = getCount(player2Cards, count2);
            count1 = getCount(player1Cards, count1);
            Card card = compareCards(player1Cards.get(count1), player2Cards.get(count2));
            if (player1Cards.contains(card)) {
                System.out.println("Większa karta gracza 1 --> " + card);
                player1Cards.add(player2Cards.get(count2));
                player2Cards.remove(count2);
                printNumberOfPlayersCards(player1Cards, player2Cards);
            } else if (player2Cards.contains(card)) {
                System.out.println("Większa karta gracza 2 --> " + card);
                player2Cards.add(player1Cards.get(count1));
                player1Cards.remove(count1);
                printNumberOfPlayersCards(player1Cards, player2Cards);
            } else {
                decideWhatToDoWithEqualCards(count1, count2, player1Cards, player2Cards);
            }
            count1++;
            count2++;
            counter++;
            time = time + 3;
            if (checkWinner(player1Cards, player2Cards)) return;
        }
    }

    private void printNumberOfPlayersCards(List<Card> players1Cards, List<Card> players2Cards) {
        System.out.println("Karty gracza 1: " + players1Cards.size() + " Karty gracza 2: " + players2Cards.size());
    }

    private int getCount(List<Card> playerCards, int count) {
        if (count >= playerCards.size()) {
            count = 0;
        }
        return count;
    }


    private void decideWhatToDoWithEqualCards(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards) {
        List<Card> cardsWon1 = new ArrayList<>();
        List<Card> cardsWon2 = new ArrayList<>();
        boolean exit = false;
        cardsWon1.add(player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        count1 = increaseCount(count1);
        count2 = increaseCount(count2);
        time = time + 3;
        count2 = getCount(player2Cards, count2);
        count1 = getCount(player1Cards, count1);
        cardsWon1.add(player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        printInfoPlayersGiveCoveredCards();
        count1 = increaseCount(count1);
        count2 = increaseCount(count2);
        time++;
        count2 = getCount(player2Cards, count2);
        count1 = getCount(player1Cards, count1);
        do {
            if (player1Cards.get(count1).getRankNumber() > player2Cards.get(count2).getRankNumber()) {
                doWhenRank1BiggerThanRank2(count1, count2, player1Cards, player2Cards, cardsWon1);
                exit = true;
            } else if (player1Cards.get(count1).getRankNumber() < player2Cards.get(count2).getRankNumber()) {
                doWhenRank1IsSmallerThenRank2(count1, count2, player1Cards, player2Cards, cardsWon2);
                exit = true;
            } else if (player1Cards.get(count1).getRankNumber() == player2Cards.get(count2).getRankNumber()) {
                printPlayersCards(player1Cards.get(count1), player2Cards.get(count2));
                System.out.println("Karty są równe!");
                time = time + 3;
                for (int i = 0; i < 2; i++) {
                    cardsWon1.add(player2Cards.get(count2));
                    cardsWon2.add(player1Cards.get(count1));
                    count1 = increaseCount(count1);
                    count2 = increaseCount(count2);
                    count2 = getCount(player2Cards, count2);
                    count1 = getCount(player1Cards, count1);
                }
                printInfoPlayersGiveCoveredCards();
            }
        } while (!exit);
    }

    private int increaseCount(int count) {
        count++;
        return count;
    }

    private void printInfoPlayersGiveCoveredCards() {
        System.out.println("Obaj graczę kładą na wierzch po jednej zakrytej karcie");
    }

    private void doWhenRank1IsSmallerThenRank2(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards, List<Card> cardsWon2) {
        printPlayersCards(player1Cards.get(count1), player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        System.out.println("Większa karta gracza 2 --> " + player2Cards.get(count2));
        player2Cards.addAll(cardsWon2);  // dodaję wszystkie karty z listy wygranych kart
        for (Card card : cardsWon2) {
            player1Cards.removeIf(next -> next.equals(card));
        }
        printNumberOfPlayersCards(player1Cards, player2Cards);
    }

    private void doWhenRank1BiggerThanRank2(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards, List<Card> cardsWon1) {
        printPlayersCards(player1Cards.get(count1), player2Cards.get(count2));
        cardsWon1.add(player2Cards.get(count2));
        System.out.println("Większa karta gracza 1 --> " + player1Cards.get(count1));
        player1Cards.addAll(cardsWon1);  // dodaję wszystkie karty z listy wygranych kart
        for (Card card : cardsWon1) {
//            for (Iterator<Card> iterator = player2Cards.iterator(); iterator.hasNext(); ) {
//                Card next = iterator.next();
//                if (next.equals(card)) {
//                    iterator.remove();
//                }
//            }
            player2Cards.removeIf(next -> next.equals(card));
        }
        printNumberOfPlayersCards(player1Cards, player2Cards);
    }


    private boolean checkWinner(List<Card> player1Cards, List<Card> player2Cards) {
        if (player1Cards.size() == 0 || player2Cards.size() == 0) {
            int winnerNumber = 0;
            if (player1Cards.size() == 0) {
                winnerNumber = 2;
            } else {
                winnerNumber = 1;
            }
            System.out.println("Koniec gry. Wygrał gracz nr " + winnerNumber);
            System.out.println("Czas gry " + time / 60 + " minut.");
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
