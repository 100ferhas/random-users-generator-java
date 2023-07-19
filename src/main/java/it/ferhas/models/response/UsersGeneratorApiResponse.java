package it.ferhas.models.response;

import lombok.Getter;

import java.util.List;

@Getter
@SuppressWarnings("unused")
public class UsersGeneratorApiResponse {
    private List<Result> results;
    private Info info;
}
