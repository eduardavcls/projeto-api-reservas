package com.api.reservas.demo.validation;

import com.api.reservas.demo.dto.ReservaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ValidDateRangeValidator implements ConstraintValidator<ValidDateRange, ReservaDTO> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(ReservaDTO reservaDTO, ConstraintValidatorContext context) {
        LocalDateTime inicio = reservaDTO.getInicio();
        LocalDateTime fim = reservaDTO.getFim();

        if (inicio == null || fim == null) {
            return true; // Se uma das datas for nula, a validação padrão do @NotNull lidará com isso.
        }

        return inicio.isBefore(fim);
    }
}