package io.github.ferhas.users_provider.models.request.enums;

import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum PasswordCharset {
    SPECIAL,
    UPPER,
    LOWER,
    NUMBER;

    @JsonValue
    public String toLowerCase() {
        return toString().toLowerCase();
    }
}
