package back.spring.strawpoll.exception;

public final class RequestUnavailableException extends RuntimeException{
    public RequestUnavailableException(String msg){
        super(msg);
    }
}
