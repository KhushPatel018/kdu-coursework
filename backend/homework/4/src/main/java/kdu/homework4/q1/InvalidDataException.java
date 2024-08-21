package kdu.homework4.q1;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}
