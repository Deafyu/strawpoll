package back.spring.strawpoll.exception;

public final class UserAlreadyExistingException extends RuntimeException{

    public UserAlreadyExistingException() {
        super();
    }

    public UserAlreadyExistingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistingException(final String message) {
        super(message);
    }

    public UserAlreadyExistingException(final Throwable cause) {
        super(cause);
    }

}

