package spring_boot_security.Spring.Boot.Security.exceptions;

public class ApiException extends RuntimeException {

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}
