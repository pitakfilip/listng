package sk.fmfi.listng.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.List;

/**
 * Api covering LIST security as getting the users info for login, request for password reset and handling changes.
 */
public interface UserAuthApi {

    /**
     * Request user object used for authentification and permitting access to app features based on permissions.
     * @param email User email used for login (username).
     * @return User object containing permissions and encoded password.
     * @Deprecated Only used by GUI-REST for authentification, as this provided the users password as well.
     *  Instead use {@link UserApi#getUserByEmail(String email)} providing the same object without the password.
     */
    @GetMapping("/auth/login")
    @Deprecated
    UserAuthDto getAuthUserByEmail(@RequestParam("email") String email);

    /**
     * Request a password change to user via email, which is firstly validated.
     * Email sent to user contains a generated URL for the password to be changed, containing a Base64 hash 
     * created from a JSON object with 'username', 'expires' and 'secret'.
     * @param email User email used for login (username).
     * @return success of operation, as the provided email must be validated first.
     */
    @GetMapping("/auth/reset/init")
    boolean requestPasswordReset(@RequestParam("email") String email);

    /**
     * Change given users password to the provided value.
     * @param hash JSON object with keys 'username', 'password' and 'secret' encoded with Base64
     *             Secret value is dynamically generated and only may be obtained by requesting 
     *             password reset URL via {@link #requestPasswordReset(String hash)}.
     */
    @PostMapping("/auth/reset")
    void changePassword(@RequestParam("hash") String hash) throws Exception;

    /**
     * Create singe new user.
     * @param user
     * @return
     */
    @PostMapping("/auth/user/new")
    void newUser(@RequestBody UserAuthDto user);

    /**
     * Create collection of new users.
     * @param users
     * @return
     */
    @PostMapping("/auth/users/new")
    void newUsers(@RequestBody List<UserAuthDto> users);
}
