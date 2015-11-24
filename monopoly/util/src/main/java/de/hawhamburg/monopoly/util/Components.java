package de.hawhamburg.monopoly.util;

/**
 * Created by Ole on 24.11.2015.
 */
public class Components {
    private static final String HTTP_LOCALHOST_4567_GAMES = "http://localhost:4567/games";
    private static final String HTTP_LOCALHOST_4567_DICE = "http://localhost:4567/dice";
    private static final String HTTP_LOCALHOST_4567_BOARDS = "http://localhost:4567/boards";
    private static final String HTTP_LOCALHOST_4567_BANKS = "http://localhost:4567/banks";
    private static final String HTTP_LOCALHOST_4567_BROKERS = "http://localhost:4567/brokers";
    private static final String HTTP_LOCALHOST_4567_DECKS = "http://localhost:4567/decks";
    private static final String HTTP_LOCALHOST_4567_EVENTS = "http://localhost:4567/events";

    private String game;
    private String dice;
    private String board;
    private String bank;
    private String broker;
    private String decks;
    private String events;

    private Components(){

    }

    public String getGame() {
        return game;
    }

    public String getDice() {
        return dice;
    }

    public String getBoard() {
        return board;
    }

    public String getBank() {
        return bank;
    }

    public String getBroker() {
        return broker;
    }

    public String getDecks() {
        return decks;
    }

    public String getEvents() {
        return events;
    }

    public static Components getComponents(){
        Components components = new Components();
        components.game = HTTP_LOCALHOST_4567_GAMES;
        components.dice = HTTP_LOCALHOST_4567_DICE;
        components.board = HTTP_LOCALHOST_4567_BOARDS;
        components.bank = HTTP_LOCALHOST_4567_BANKS;
        components.broker = HTTP_LOCALHOST_4567_BROKERS;
        components.decks = HTTP_LOCALHOST_4567_DECKS;
        components.events = HTTP_LOCALHOST_4567_EVENTS;
        return components;
    }

}