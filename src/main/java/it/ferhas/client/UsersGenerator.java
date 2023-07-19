package it.ferhas.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import it.ferhas.models.request.UserGeneratorRequestOptions;
import it.ferhas.models.response.UsersGeneratorApiResponse;

import java.util.function.Supplier;

public class UsersGenerator {
    private final String API_URL = "https://randomuser.me/api/";

    protected final RandomAPIClient client = Feign
            .builder()
            .queryMapEncoder(new UserGeneratorEncoder())
            .decoder(new JacksonDecoder())
            .target(RandomAPIClient.class, API_URL);

    public final UsersGeneratorApiResponse getUsers() {
        return getUsers(() -> UserGeneratorRequestOptions.builder().build());
    }

    public final UsersGeneratorApiResponse getUsers(Supplier<UserGeneratorRequestOptions> optionSupplier) {
        UserGeneratorRequestOptions options = optionSupplier.get();
        return getUsers(options);
    }

    public final UsersGeneratorApiResponse getUsers(UserGeneratorRequestOptions options) {
        return client.users(options);
    }
}
