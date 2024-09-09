package drs;

/**
 *
 * @author PJ
 */
public class AuthenticationSession {

    private static AuthenticationSession instance;

    private String email;
    private String role;
    private String firstName;
    private String lastName;

    public AuthenticationSession(String email, String role, String fn, String ln) {
        this.email = email;
        this.role = role;
        this.firstName = fn;
        this.lastName = ln;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    // Method to initialize the session
    public static void startSession(String email, String role, String fn, String ln) {
        if (instance == null) {
            instance = new AuthenticationSession(email, role, fn, ln);
        }
    }

    // Method to get the session instance
    public static AuthenticationSession getInstance() {
        return instance;
    }

    // Method to clear the session
    public static void clearSession() {
        instance = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to check if the user is logged in
    public static boolean isLoggedIn() {
        return instance != null;
    }
}
