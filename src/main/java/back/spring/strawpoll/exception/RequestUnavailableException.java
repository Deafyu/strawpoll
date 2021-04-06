package back.spring.strawpoll.exception;

public class RequestUnavailableException extends RuntimeException{
    public RequestUnavailableException(String msg){
        super(msg);
    }
}
