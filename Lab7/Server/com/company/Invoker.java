package com.company;

import Commands.Command;

public class Invoker {
    private Command command;

    public Invoker(){}

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        this.command.execute();
    }
}