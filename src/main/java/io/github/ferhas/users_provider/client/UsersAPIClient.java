package io.github.ferhas.users_provider.client;

import feign.QueryMap;
import feign.RequestLine;
import io.github.ferhas.users_provider.models.request.UsersProviderAPIOptions;
import io.github.ferhas.users_provider.models.response.UsersProviderAPIResponse;

public interface UsersAPIClient {
    String API_URL = "https://randomuser.me/api/";

    String API_VERSION = "1.4";

    @RequestLine("GET /" + API_VERSION)
    UsersProviderAPIResponse users(@QueryMap UsersProviderAPIOptions options);
}
