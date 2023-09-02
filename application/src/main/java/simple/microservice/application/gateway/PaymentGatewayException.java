package simple.microservice.application.gateway;

public class PaymentGatewayException extends Exception {
    public PaymentGatewayException(String msg) {
        super(msg);
    }
}