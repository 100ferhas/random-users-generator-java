import it.ferhas.client.RandomAPIClient;
import it.ferhas.client.UsersGenerator;
import it.ferhas.models.request.UserGeneratorPasswordRequestOptions;
import it.ferhas.models.request.enums.APIFields;
import it.ferhas.models.request.UserGeneratorRequestOptions;
import it.ferhas.models.request.enums.APIGender;
import it.ferhas.models.request.enums.APINationalities;
import it.ferhas.models.request.enums.PasswordCharset;
import it.ferhas.models.response.UsersGeneratorApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratorTest {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    private final int REQUESTED_RESULTS = 20;

    private UsersGenerator usersGenerator;

    @BeforeEach
    void setup() {
        usersGenerator = new UsersGenerator();
    }

    @Test
    @DisplayName("Check API Version")
    void testCorrectAPIVersion() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers();
        assertNotNull(response, "No response received");
        assertNotNull(response.getInfo(), "No info received");
        assertEquals(RandomAPIClient.API_VERSION, response.getInfo().getVersion());

        LOGGER.info(String.format("API Version is %s", response.getInfo().getVersion()));
    }

    @Test
    @DisplayName("Get users without options")
    void testRetrieveUserWithoutOptions() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers();
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");
        assertEquals(1, response.getResults().size(), "Unexpected users size!");
    }

    @Test
    @DisplayName("Get users without infos")
    void testRetrieveUserWithoutInfo() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .noInfo(true)
                .build()
        );
        assertNotNull(response, "No response received");
        assertNull(response.getInfo(), "Info received, not expected!");
    }

    @Test
    @DisplayName("Get users with option results number")
    void testRetrieveUserWithOptionsResults() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");
        assertEquals(REQUESTED_RESULTS, response.getResults().size(), "Unexpected users size!");
    }

    @Test
    @DisplayName("Get users with option exclude login")
    void testRetrieveUserWithOptionsExcludeLogin() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .exclude(Set.of(APIFields.LOGIN))
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");

        for (int i = 0; i < REQUESTED_RESULTS; i++) {
            assertNull(response.getResults().get(i).getLogin(), "Login field not expected!");
        }
    }

    @Test
    @DisplayName("Get users with option include login")
    void testRetrieveUserWithOptionsIncludeLogin() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .include(Set.of(APIFields.LOGIN))
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");

        for (int i = 0; i < REQUESTED_RESULTS; i++) {
            assertNotNull(response.getResults().get(i).getLogin(), "Login field expected!");
            assertNull(response.getResults().get(i).getLocation(), "Location not expected!");
        }
    }

    @Test
    @DisplayName("Get users with option page")
    void testRetrieveUserWithOptionPage() {
        int requestedPage = 2;
        String seed = "randomSeed";

        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .page(requestedPage)
                .seed(seed)
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");
        assertNotNull(response.getInfo(), "Info expected!");
        assertEquals(requestedPage, response.getInfo().getPage(), "Page number not expected!");
    }

    @Test
    @DisplayName("Get users with option nationalities")
    void testRetrieveUserWithOptionNationalities() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .nationalities(Set.of(APINationalities.AU))
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");

        for (int i = 0; i < REQUESTED_RESULTS; i++) {
            assertTrue(APINationalities.AU.toString().equalsIgnoreCase(response.getResults().get(i).getNat()), "Different nationality expected!");
        }
    }

    @Test
    @DisplayName("Get users with option gender")
    void testRetrieveUserWithOptionGender() {
        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .gender(APIGender.MALE)
                .build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");

        for (int i = 0; i < REQUESTED_RESULTS; i++) {
            assertTrue(APIGender.MALE.toString().equalsIgnoreCase(response.getResults().get(i).getGender()), "Different gender expected!");
        }
    }

    @Test
    @DisplayName("Get users with option password")
    void testRetrieveUserWithOptionPassword() {
        int requestedPassLength = 6;

        UsersGeneratorApiResponse response = usersGenerator.getUsers(() -> UserGeneratorRequestOptions
                .builder()
                .results(REQUESTED_RESULTS)
                .password(
                        UserGeneratorPasswordRequestOptions.builder()
                                .passwordCharsets(Set.of(PasswordCharset.UPPER))
                                .minLength(requestedPassLength)
                                .maxLength(requestedPassLength)
                                .build()
                ).build()
        );
        assertNotNull(response, "No response received");
        assertNotNull(response.getResults(), "No results received");
        assertNotNull(response.getResults().get(0).getLogin(), "No password received!");

        for (int i = 0; i < REQUESTED_RESULTS; i++) {
            String password = response.getResults().get(i).getLogin().getPassword();
            assertEquals(password.toUpperCase(), password, "Password uppercase expected!");
            assertEquals(requestedPassLength, password.length());
        }
    }
}
