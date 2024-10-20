package Spring.wallet.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import Spring.wallet.auth.JwtTokenProvider;
import Spring.wallet.model.request.LoginRequest;
import Spring.wallet.model.response.LoginResponse;
import Spring.wallet.service.CustomPersonDetailsService;
import javax.validation.Valid;
import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomPersonDetailsService personDetailsService;

    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws LoginException {
        try {
            // احراز هویت کاربر
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new LoginException("Invalid username or password");
        }

        // بارگذاری جزئیات کاربر
        UserDetails userDetails = personDetailsService.loadUserByUsername(loginRequest.getUsername());
        
        // تولید توکن JWT
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        // ساخت پاسخ و بازگشت آن
        LoginResponse loginResponse = new LoginResponse(userDetails.getUsername(), token);
        return ResponseEntity.ok(loginResponse);
    }
}
