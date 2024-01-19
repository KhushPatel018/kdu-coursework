package org.kdu;

public class Constants {
    public enum Role{
        BATSMAN,
        BOWLER,
        WICKET_KEEPER,
        ALL_ROUNDER

    }

    public Role getRole(String role)
    {
        switch (role){
            case "BATSMAN" -> {
                return Role.BATSMAN;
            }
            case "WICKET KEEPER" -> {
                return Role.WICKET_KEEPER;
            }
            case "BOWLER" -> {
                return Role.BOWLER;
            }
            case "ALL ROUNDER" -> {
                return Role.ALL_ROUNDER;
            }
        }
        return Role.BATSMAN;
    }
}
