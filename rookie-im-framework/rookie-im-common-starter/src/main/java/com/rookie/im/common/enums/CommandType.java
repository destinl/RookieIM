package com.rookie.im.common.enums;

public enum CommandType {

    USER("4"),
    GROUP("2"),
    MESSAGE("1"),
    SYSTEM("9");

    private String commandType;

    public String getCommandType(){
        return commandType;
    }

    CommandType(String commandType){
        this.commandType = commandType;
    }

    public static CommandType getCommandType(String command){
        String sub = command.substring(0, 1);
        for(int i = 0; i < CommandType.values().length; i++){
            if (CommandType.values()[i].getCommandType().equals(sub)){
                return CommandType.values()[i];
            }
        }
        return null;
    }
}
