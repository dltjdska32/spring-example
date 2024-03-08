/*
package lsn.example.testproject.common.exception;

import lsn.example.testproject.common.Constant;
import org.springframework.http.HttpStatus;

public class LeeException extends Exception {

    private  static final long serialVersionUID = 4663380430591151694L;

    private Constant.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public LeeException(Constant.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {

        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constant.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public  int getHttpStatusCode() {
        return httpStatus.value();
    }

    public  String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
*/
