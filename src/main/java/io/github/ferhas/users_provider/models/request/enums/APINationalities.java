package io.github.ferhas.users_provider.models.request.enums;

import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum APINationalities {
    AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IN, IR, MX, NL, NO, NZ, RS, TR, UA, US;

    @JsonValue
    public String toLowerCase() {
        return toString().toLowerCase();
    }
}
