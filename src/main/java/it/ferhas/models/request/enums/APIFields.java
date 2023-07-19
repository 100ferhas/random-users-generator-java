package it.ferhas.models.request.enums;

import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum APIFields {
    GENDER,
    NAME,
    LOCATION,
    EMAIL,
    LOGIN,
    REGISTERED,
    DOB,
    PHONE,
    CELL,
    ID,
    PICTURE,
    NAT;

    @JsonValue
    public String toLowerCase() {
        return toString().toLowerCase();
    }
}
