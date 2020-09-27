package tp.cap5buddy.parser;

import java.util.Scanner;

public class ParserManager {
    private String current_Input;
    private String command;
    private String nonCommand;
    private int count;
    public ParserManager() {
        this.current_Input = null;
        this.command = null;
        this.nonCommand = null;
    }

    public String parse(String input) {
        this.current_Input = input;
        getCommand();
        getNonCommand();
        return this.command + " Non: " + this.nonCommand;
    }

    private void getCommand() {
        String command = "";
        Scanner sc = new Scanner(this.current_Input);
        while (sc.hasNext()) {
            String now = sc.next();
            if (Prefix.isPrefix(now)) {
                break;
            } else {
                this.count++;
                command += now;
            }
        }
        this.command = command;
    }

    private void getNonCommand() {
        String nonCommand = "";
        String[] input = this.current_Input.split(" ");
        int limit = input.length;
        for(int i = this.count; i < limit; i++) {
            if (i == this.count) {
                nonCommand += input[i].substring(2) + " ";
            } else {
                nonCommand += input[i] + " ";
            }
        }
        this.nonCommand = nonCommand;
        this.count = 0; // to reset the counter
    }
}
