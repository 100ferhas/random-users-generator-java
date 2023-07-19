package io.github.ferhas.users_provider.models.request;

import io.github.ferhas.users_provider.models.request.enums.PasswordCharset;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UsersProviderPasswordAPIOptions {
    private Set<PasswordCharset> passwordCharsets;
    private Integer minLength;
    private Integer maxLength;
}
