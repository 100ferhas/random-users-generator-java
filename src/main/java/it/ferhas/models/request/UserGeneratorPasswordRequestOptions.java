package it.ferhas.models.request;

import it.ferhas.models.request.enums.PasswordCharset;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UserGeneratorPasswordRequestOptions {
    private Set<PasswordCharset> passwordCharsets;
    private Integer minLength;
    private Integer maxLength;
}
