package taranova.spring_rest.exception_handling;

public class EmployeeIsAlreadyInDBException extends RuntimeException {
    public EmployeeIsAlreadyInDBException(String message) {
        super(message);
    }
}
