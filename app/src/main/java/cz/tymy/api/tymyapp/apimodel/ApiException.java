package cz.tymy.api.tymyapp.apimodel;

/**
 * Created by ph on 10/13/14.
 */
public class ApiException extends Exception {

    private static final String ACCOUNT_PROBLEM = "Wrong user or password";
    private String message = null;

    public ApiException (String message) {
        super(message);
        setMessage(message);
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return message;
    }

    void setMessage(String message) {
        if (message.equals(ApiMsg.V_NOT_LOGGED_IN_1) ||
                message.equals(ApiMsg.V_NOT_LOGGED_IN_2)) {
            this.message = ACCOUNT_PROBLEM;
        } else {
            this.message = message;
        }
    }
}
