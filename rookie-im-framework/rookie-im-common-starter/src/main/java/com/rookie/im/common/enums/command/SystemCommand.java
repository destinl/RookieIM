package com.rookie.im.common.enums.command;

public enum SystemCommand implements Command{
    LOGIN(0x2328);


    private int command;

    SystemCommand(int command){
        this.command = command;
    }

    @Override
    public int getCommand() {
        return 0;
    }
}
