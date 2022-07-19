package card;


import card.service.CardService;

public class CardDemo {

    public static void main(String[] args) {

        CardService cardService = new CardService();
        cardService.start();

    }
}