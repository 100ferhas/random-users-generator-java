package io.github.ferhas.users_provider.models.response;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Location {
    private Street street;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private Coordinates coordinates;
    private Timezone timezone;
}
