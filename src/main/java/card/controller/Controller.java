package card.controller;

import card.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gra w wojnę ;)");
        System.out.println("Podaj imię gracza 1");
        String player1Name = scanner.nextLine();
        System.out.println("Podaj imię gracza 2");
        String player2Name = scanner.nextLine();
        playerNames.add(player1Name);
        playerNames.add(player2Name);
        return playerNames;
    }

    public void printPlayersCards(Card card1, Card card2) {
        System.out.println("Karta gracza 1: " + card1 + ", Karta gracza 2: " + card2);
    }

    public void printWhoseCardIsBigger(int playerNumber) {
        System.out.println("Większa karta gracza " + playerNumber);
    }

    public void printCardsAreEquals() {
        System.out.println("Karty są równe...");
    }

    public void checkPlayerCardListSize(int time, String playerName, int playerNumber) {
        System.out.println("Remis, ale została tylko 1 karta");
        printWinnerNameAndGameTime(time, playerName, playerNumber);
    }

    public void printNumberOfPlayersCards(List<Card> players1Cards, List<Card> players2Cards) {
        System.out.println("Karty gracza 1: " + players1Cards.size() + " Karty gracza 2: " + players2Cards.size());
    }

    public void printInfoPlayersGiveCoveredCards() {
        System.out.println("Obaj graczę kładą na wierzch po jednej zakrytej karcie");
    }

    public void printWinnerNameAndGameTime(int time, String playerName, int winnerNumber) {
        System.out.println("Koniec gry. Wygrał gracz nr " + winnerNumber + " - " + playerName);
        System.out.println("Czas gry " + time / 60 + " minut.");
    }
}
