package it.ferhas.models.request.enums;

import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum APIGender {
    MALE, FEMALE;

    @JsonValue
    public String toLowerCase() {
        return toString().toLowerCase();
    }
}
