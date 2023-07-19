package io.github.ferhas.users_provider.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.QueryMapEncoder;
import io.github.ferhas.users_provider.models.request.UsersProviderPasswordAPIOptions;
import io.github.ferhas.users_provider.models.request.UsersProviderAPIOptions;

import java.util.Map;
import java.util.stream.Collectors;

public class UsersAPIEncoder implements QueryMapEncoder {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @Override
    public Map<String, Object> encode(Object obj) {
        Map<String, Object> params = null;

        if (obj instanceof UsersProviderAPIOptions) {
            UsersProviderAPIOptions options = (UsersProviderAPIOptions) obj;
            params = objectMapper.convertValue(options, new TypeReference<>() {});

            UsersProviderPasswordAPIOptions password = options.getPassword();
            if (password != null) {
                String passwordParam = extractPasswordParam(password);
                params.put(UsersProviderAPIOptions.Fields.password, passwordParam);
            }
        }

        return params;
    }

    private String extractPasswordParam(UsersProviderPasswordAPIOptions password) {
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
