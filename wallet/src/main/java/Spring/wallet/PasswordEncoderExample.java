package Spring.wallet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "nik123"; // رمز عبور اصلی
        String hashedPassword = passwordEncoder.encode(plainPassword);
        System.out.println(hashedPassword); // خروجی رمزنگاری شده را در کنسول مشاهده کنید
    }
}

