import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

import static constants.RandomData.*;
import static constants.RandomData.randomName;
public class ChangingUserDataTest {
    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps();
        ValidatableResponse responseCreate = userSteps.createUser(randomEmail, randomPassword, randomName);
        userSteps.checkAnswerSuccess(responseCreate);
        ValidatableResponse responseLogin = userSteps.login(randomEmail, randomPassword);
        userSteps.checkAnswerSuccess(responseLogin);
        accessToken = userSteps.getAccessToken(responseLogin);
        ValidatableResponse responseChangeWithToken = userSteps.authorizationWithToken(accessToken, "x" + randomEmail, "x" + randomPassword, "x" + randomName);
        userSteps.checkAnswerSuccess(responseChangeWithToken);
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void createChangingDataWithAuth() {
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    public void createChangingDataWithoutAuth() {
    }

    @After
    public void close() {
        userSteps.deleteUsersAfterTests(accessToken);
    }
}
