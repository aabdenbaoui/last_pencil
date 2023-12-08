package lastpencil;

import java.util.Scanner;

public class Player {
    String name;
    int pencils;
    int take;
    Bot bot;
    boolean isWinner;

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Player() {

    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    boolean isBot;

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Player(String name, Bot bot) {
        this.name = name;
        this.bot = bot;
        isBot = true;
    }

    public Player(String name) {
        this.name = name;
        isBot = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPencils() {
        return pencils;
    }

    public void setPencils(int pencils) {
        if(isBot()){
            bot.setInput(pencils);
            pencils = bot.getInput();
        }
        this.pencils = pencils;
    }

    public int getTake() {
        if(isBot()){
            take = bot.generateOutput() ;
            return take;
        }
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int pencilsLeft(){
        if(isBot){
            return bot.getInput() - take;
        }
        return pencils - take;
    }
}
