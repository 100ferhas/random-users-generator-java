package io.github.ferhas.users_provider.models.response;

import lombok.Getter;

import java.util.List;

@Getter
@SuppressWarnings("unused")
public class UsersProviderAPIResponse {
    private List<Result> results;
    private Info info;
}
