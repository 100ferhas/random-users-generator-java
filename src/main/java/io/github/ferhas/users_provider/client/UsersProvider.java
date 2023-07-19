package io.github.ferhas.users_provider.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import io.github.ferhas.users_provider.models.request.UsersProviderAPIOptions;
import io.github.ferhas.users_provider.models.response.UsersProviderAPIResponse;

import java.util.function.Supplier;

public class UsersProvider {

    protected final UsersAPIClient client = Feign
            .builder()
            .queryMapEncoder(new UsersAPIEncoder())
            .decoder(new JacksonDecoder())
            .target(UsersAPIClient.class, UsersAPIClient.API_URL);

    public final UsersProviderAPIResponse getUsers() {
        return getUsers(UsersProviderAPIOptions.builder().build());
    }

    public final UsersProviderAPIResponse getUsers(Supplier<UsersProviderAPIOptions> optionSupplier) {
        UsersProviderAPIOptions options = optionSupplier.get();
        return getUsers(options);
    }

    public final UsersProviderAPIResponse getUsers(UsersProviderAPIOptions options) {
        return client.users(options);
    }
}
