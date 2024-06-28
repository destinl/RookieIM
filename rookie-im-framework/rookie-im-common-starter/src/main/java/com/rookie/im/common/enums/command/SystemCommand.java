package com.rookie.im.common.enums.command;

public enum SystemCommand implements Command{
    LOGIN(0x2328),
    LOGOUT(0x232b)
    ;


    private int command;

    SystemCommand(int command){
        this.command = command;
    }

    @Override
    public int getCommand() {
        return command;
    }
}
