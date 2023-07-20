package io.github.ferhas.users_provider.client;

import feign.QueryMap;
import feign.RequestLine;
import io.github.ferhas.users_provider.models.request.UsersProviderAPIOptions;
import io.github.ferhas.users_provider.models.response.UsersProviderAPIResponse;

interface UsersAPIClient {

    @RequestLine("GET /" + Constants.API_VERSION)
    UsersProviderAPIResponse users(@QueryMap UsersProviderAPIOptions options);
}
