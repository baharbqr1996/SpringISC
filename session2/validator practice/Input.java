package ir.freeland.spring.validator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import net.bytebuddy.implementation.bind.annotation.Empty;


public class Input {

  @Min(1)
  @Max(10)
  private int numberBetweenOneAndTen;

  // Note that this is actually not a valid IP address pattern, since
  // it allows values greater than 255 per octet.
  @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
  private String ipAddress;
  
  @Email
  private String emailAddress;
  
  @NotEmpty
  private String notEmpty;
  

  public String getEmailAddress() {
	return emailAddress;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public String getNotEmpty() {
	return notEmpty;
}

public void setNotEmpty(String notEmpty) {
	this.notEmpty = notEmpty;
}

public int getNumberBetweenOneAndTen() {
    return numberBetweenOneAndTen;
  }

  public void setNumberBetweenOneAndTen(int numberBetweenOneAndTen) {
    this.numberBetweenOneAndTen = numberBetweenOneAndTen;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
}
