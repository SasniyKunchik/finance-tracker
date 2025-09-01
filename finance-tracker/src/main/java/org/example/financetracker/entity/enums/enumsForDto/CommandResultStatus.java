package org.example.financetracker.entity.enums.enumsForDto;


import lombok.Getter;

@Getter
public enum CommandResultStatus {
    SUCCESS,
    FAILED;

    public static CommandResultStatus fromString(String status){
        for (CommandResultStatus commandResultStatus : CommandResultStatus.values()) {
            if (commandResultStatus.name().equalsIgnoreCase(status)){
                return commandResultStatus;
            }
        }
        throw new IllegalArgumentException("Invalid command result status: " + status);
    }
}
