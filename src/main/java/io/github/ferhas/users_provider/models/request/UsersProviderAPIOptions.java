package io.github.ferhas.users_provider.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ferhas.users_provider.models.request.enums.APIFields;
import io.github.ferhas.users_provider.models.request.enums.APIGender;
import io.github.ferhas.users_provider.models.request.enums.APINationalities;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@Getter
@Builder
@FieldNameConstants
public class UsersProviderAPIOptions {
    private Integer results;

    @JsonProperty("noinfo")
    private Boolean noInfo;

    @JsonProperty("inc")
    private Set<APIFields> include;

    @JsonProperty("exc")
    private Set<APIFields> exclude;

    private Integer page;

    private String seed;

    @JsonProperty("nat")
    private Set<APINationalities> nationalities;

    private UsersProviderPasswordAPIOptions password;

    private APIGender gender;
}
