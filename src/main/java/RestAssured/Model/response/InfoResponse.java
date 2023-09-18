package RestAssured.Model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResponse {

    public int code;
    public String message;

    public Data data;
    public static class Data {
        public String name;
        public String surname;
        public String age;
        public int gender;
        public String language;
        public String status;
        public boolean isBlocked;
    }


}
