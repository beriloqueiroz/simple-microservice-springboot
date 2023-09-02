package simple.microservice.domain.helpers.validation.error;

import simple.microservice.domain.helpers.Cpf;
import com.google.common.collect.ImmutableSet;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CpfValidator implements ConstraintValidator<Cpf, String> {
    private final ImmutableSet<Object> invalidCPFs = ImmutableSet.builder().add(new String[]{"11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666"}).add(new String[]{"77777777777", "88888888888", "99999999999", "00000000000"}).build();

    public CpfValidator() {
    }

    public void initialize(Cpf constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        String cpf = value.replaceAll("\\D", "");
        if (cpf.length() == 11 && !this.invalidCPFs.contains(cpf)) {
            String digits = cpf.substring(9);
            int first = this.firstDigit(cpf);
            int second = this.secondDigit(cpf);
            String er = String.valueOf(first) + second;
            return digits.equals(er);
        } else {
            return false;
        }
    }

    private int secondDigit(String cpf) {
        int mult = 11;
        int sum = 0;

        int rest;
        for(rest = 0; rest < 10; ++rest) {
            sum += mult * Integer.parseInt(String.valueOf(cpf.charAt(rest)));
            --mult;
        }

        rest = 11 - sum % 11;
        if (rest >= 10) {
            rest = 0;
        }

        return rest;
    }

    private int firstDigit(String cpf) {
        int mult = 10;
        int sum = 0;

        int rest;
        for(rest = 0; rest < 9; ++rest) {
            sum += mult * Integer.parseInt(String.valueOf(cpf.charAt(rest)));
            --mult;
        }

        rest = 11 - sum % 11;
        if (rest >= 10) {
            rest = 0;
        }

        return rest;
    }
}
