package it.ferhas.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.ferhas.models.request.enums.APIFields;
import it.ferhas.models.request.enums.APIGender;
import it.ferhas.models.request.enums.APINationalities;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

import java.util.Set;

@Getter
@Builder
@FieldNameConstants
public class UserGeneratorRequestOptions {
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

    private UserGeneratorPasswordRequestOptions password;

    private APIGender gender;
}
