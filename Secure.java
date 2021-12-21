package app;

import org.mindrot.jbcrypt.BCrypt;

public class Secure {
  public Secure() {
    // Constructor
  }

  public String secure(String origPassword) {
    return BCrypt.hashpw(origPassword, BCrypt.gensalt(12));
  }

  public boolean verify(String origPassword, String passwordHash) {
    return BCrypt.checkpw(origPassword, passwordHash);
  }
}