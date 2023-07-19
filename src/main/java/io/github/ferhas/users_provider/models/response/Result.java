package io.github.ferhas.users_provider.models.response;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Result {
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Id id;
    private Picture picture;
    private String nat;
}
