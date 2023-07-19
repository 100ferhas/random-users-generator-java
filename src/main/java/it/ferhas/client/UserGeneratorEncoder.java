package it.ferhas.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.QueryMapEncoder;
import it.ferhas.models.request.UserGeneratorPasswordRequestOptions;
import it.ferhas.models.request.UserGeneratorRequestOptions;

import java.util.Map;
import java.util.stream.Collectors;

public class UserGeneratorEncoder implements QueryMapEncoder {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @Override
    public Map<String, Object> encode(Object obj) {
        Map<String, Object> params = null;

        if (obj instanceof UserGeneratorRequestOptions) {
            UserGeneratorRequestOptions options = (UserGeneratorRequestOptions) obj;
            params = objectMapper.convertValue(options, new TypeReference<>() {});

            UserGeneratorPasswordRequestOptions password = options.getPassword();
            if (password != null) {
                String passwordParam = extractPasswordParam(password);
                params.put(UserGeneratorRequestOptions.Fields.password, passwordParam);
            }
        }

        return params;
    }

    private String extractPasswordParam(UserGeneratorPasswordRequestOptions password) {
        String passwordParam = password.getPasswordCharsets()
                .stream()
                .map(x -> x.toString().toLowerCase())
                .collect(Collectors.joining(","));

        if (password.getMinLength() != null && password.getMaxLength() != null) {
            passwordParam += String.format(",%s-%s", password.getMinLength(), password.getMinLength());
        } else if (password.getMinLength() != null) {
            passwordParam += String.format(",%s", password.getMinLength());
        } else if (password.getMaxLength() != null) {
            passwordParam += String.format(",%s", password.getMaxLength());
        }

        return passwordParam;
    }
}
