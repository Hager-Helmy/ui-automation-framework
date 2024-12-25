package utils;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Cred {
        @JsonProperty("validCredentials")
        private Credential validCredentials;

        @JsonProperty("invalidCredentials")
        private Credential invalidCredentials;

        // Getter methods for valid and invalid credentials
        public Credential getValidCredentials() {
            return validCredentials;
        }

        public Credential getInvalidCredentials() {
            return invalidCredentials;
        }

        // Nested class to represent individual credentials
        public static class Credential {
            private String username;
            private String password;

            // Getters and Setters
            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }
    }


