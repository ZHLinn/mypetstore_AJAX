package org.csu.mypetstore.domain;

public class Log {
    public static final int LOG_IN = 0;
    public static final int SIGN_OUT = 1;
    public static final int VIEW = 2;
    public static final int UPDATE_CART = 3;
    public static final int SUBMIT_ORDER = 8;

    private String username;
    private int action;
    private String description;

    public Log(String username, int action, String description) {
        this.username = username;
        this.action = action;
        this.description = description;
    }

    public String getActionString(){
        String actionString;
        switch (action){
            case LOG_IN:
                actionString = "LOG_IN";
                break;
            case SIGN_OUT:
                actionString = "SIGN_OUT";
                break;
            case VIEW:
                actionString = "VIEW";
                break;
            case UPDATE_CART:
                actionString = "UPDATE_CART";
                break;
            case SUBMIT_ORDER:
                actionString = "SUBMIT_ORDER";
                break;
            default:
                actionString = "DEAFAULT";
                break;
        }

        return actionString;
    }

    public String getUsername() {
        return username;
    }

    public int getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }


}
