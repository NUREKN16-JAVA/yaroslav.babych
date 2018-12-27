package ua.nure.kn16.babych.usermanagement.agent;

public class SearchException extends Exception{
    public SearchException(String s) {
        super(s);
    }

    public SearchException(Exception e) {
        super(e);
    }
}
