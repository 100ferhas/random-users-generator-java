package io.github.ferhas.users_provider.models.response;

import lombok.Getter;

import java.util.Date;

@Getter
@SuppressWarnings("unused")
public class Dob {
    private Date date;
    private int age;
}
