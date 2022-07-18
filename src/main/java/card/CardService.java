package card;


import card.model.Card;
import card.model.Rank;
import card.model.Suit;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CardService {

    private List<Card> allCards = createDeck();
    final private List<Card> shuffledCards = shuffleCards(allCards);
    private int time = 0;


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
            biggerCard = card1;
        } else if (card1.getRankNumber() == card2.getRankNumber()) {
            System.out.println("Karty są równe");
        } else {
            biggerCard = card2;
        }
        System.out.println("Karta gracza 1: " + card1 + ", Karta gracza 2: " + card2);
        return biggerCard;
    }


    private void decideWhatToDoWithEqualCards(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards) {
        List<Card> cardsWon1 = new ArrayList<>();
        List<Card> cardsWon2 = new ArrayList<>();
        boolean exit = false;
        cardsWon1.add(player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        count1++;
        count2++;
        time = time + 3;
        if (count2 >= player2Cards.size()) {
            count2 = 0;
        }
        if (count1 >= player1Cards.size()) {
            count1 = 0;
        }
        cardsWon1.add(player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        System.out.println("Obaj gracze kładą na wierzch po jednej zakrytej karcie");
        count1++;
        count2++;
        time++;
        if (count2 >= player2Cards.size()) {
            count2 = 0;
        }
        if (count1 >= player1Cards.size()) {
            count1 = 0;
        }
        do {
            if (player1Cards.get(count1).getRankNumber() > player2Cards.get(count2).getRankNumber()) {
                doWhenRank1BiggerThanRank2(count1, count2, player1Cards, player2Cards, cardsWon1);
                exit = true;
            } else if (player1Cards.get(count1).getRankNumber() < player2Cards.get(count2).getRankNumber()) {
                doWhenRank1IsSmallerThenRank2(count1, count2, player1Cards, player2Cards, cardsWon2);
                exit = true;
            } else if (player1Cards.get(count1).getRankNumber() == player2Cards.get(count2).getRankNumber()) {
                System.out.println("Karty są równe!");
                time = time + 3;
                for (int i = 0; i < 2; i++) {
                    cardsWon1.add(player2Cards.get(count2));
                    cardsWon2.add(player1Cards.get(count1));
                    count1++;
                    count2++;
                    if (count2 >= player2Cards.size()) {
                        count2 = 0;
                    }
                    if (count1 >= player1Cards.size()) {
                        count1 = 0;
                    }
                }
                System.out.println("Obaj graczę kładą na wierzch po jednej zakrytej karcie");
            }
        } while (!exit);


    }

    private void doWhenRank1IsSmallerThenRank2(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards, List<Card> cardsWon2) {
        System.out.println(player1Cards.get(count1) + " " + player2Cards.get(count2));
        cardsWon2.add(player1Cards.get(count1));
        System.out.println("Większa karta gracza 2 --> " + player2Cards.get(count2));
        for (Card card : cardsWon2) {
            player2Cards.add(card);
        }
        for (Card card : cardsWon2) {
            for (Iterator<Card> iterator = player1Cards.iterator(); iterator.hasNext(); ) {
                Card next = iterator.next();
                if (next.equals(card)) {
                    iterator.remove();
                }
            }
        }
        System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
    }

    private void doWhenRank1BiggerThanRank2(int count1, int count2, List<Card> player1Cards, List<Card> player2Cards, List<Card> cardsWon1) {
        System.out.println("Karta gracza 1: " + player1Cards.get(count1) + ", Karta gracza 2: " + player2Cards.get(count2));
        cardsWon1.add(player2Cards.get(count2));
        System.out.println("Większa karta gracza 1 --> " + player1Cards.get(count1));
        for (Card card : cardsWon1) {
            player1Cards.add(card);
        }
        for (Card card : cardsWon1) {
            for (Iterator<Card> iterator = player2Cards.iterator(); iterator.hasNext(); ) {
                Card next = iterator.next();
                if (next.equals(card)) {
                    iterator.remove();
                }
            }
        }
        System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
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
                System.out.println("Większa karta gracza 1 --> " + card);
                player1Cards.add(player2Cards.get(count2));
                player2Cards.remove(count2);
                System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
            } else if (player2Cards.contains(card)) {
                System.out.println("Większa karta gracza 2 --> " + card);
                player2Cards.add(player1Cards.get(count1));
                player1Cards.remove(count1);
                System.out.println("Karty gracza 1: " + player1Cards.size() + " Karty gracza 2: " + player2Cards.size());
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
        return cardsList;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

}
