package ua.skorobahatyi.exceptions;

public class SystemException extends Exception{

    private int httpStatus;
    public SystemException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
