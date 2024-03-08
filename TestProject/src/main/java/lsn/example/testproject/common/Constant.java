package lsn.example.testproject.common;

public class Constant {
    public enum ExceptionClass {
        //상수정의
        PRODUCT("Product"), ORDER("Order"), PROVIDER("Provider");

        private  String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this. exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {return exceptionClass;}

        public String toString() {
            return getExceptionClass() + "Exception. " ;
        }
    }
}
