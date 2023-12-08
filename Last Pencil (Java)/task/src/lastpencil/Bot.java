package lastpencil;

import java.util.Random;

public class Bot {
    private int input;
    private int output;

    public Bot(int input){
        this.input = input;
    }

    public Bot() {

    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return  generateOutput();
    }

    public int getInput() {
        return input;
    }

     int generateOutput(){
         System.out.println();
        Random rand = new Random();
        int output = 0;
        int remainder  = input % 4;
        if(input == 1 || input ==  2){
            output =  1;
            return 1;
        }

        switch(remainder){
            case 0:
                output = 3;
                break;
            case 3:
                output = 2;
                break;
            case 2:
                output = 1;
                break;
            case 1:
                output = rand.nextInt(3 - 1 + 1) + 1;
                break;
        }
        return output;
    }
}
