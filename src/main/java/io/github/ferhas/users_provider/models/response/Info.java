package io.github.ferhas.users_provider.models.response;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Info {
    private String seed;
    private int results;
    private int page;
    private String version;
}
