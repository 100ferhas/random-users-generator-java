package it.ferhas.client;

import feign.QueryMap;
import feign.RequestLine;
import it.ferhas.models.request.UserGeneratorRequestOptions;
import it.ferhas.models.response.UsersGeneratorApiResponse;

public interface RandomAPIClient {
    String API_VERSION = "1.4";

    @RequestLine("GET /" + API_VERSION)
    UsersGeneratorApiResponse users(@QueryMap UserGeneratorRequestOptions options);
}
