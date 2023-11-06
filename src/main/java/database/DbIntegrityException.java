package database;

public class DbIntegrityException extends RuntimeException {
  public DbIntegrityException(String msg) {
    super(msg);
  }
}
