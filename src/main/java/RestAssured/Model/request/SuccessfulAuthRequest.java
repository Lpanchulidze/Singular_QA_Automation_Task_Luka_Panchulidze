package RestAssured.Model.request;

public class SuccessfulAuthRequest {

    public String username;
    public String password;

    public SuccessfulAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
