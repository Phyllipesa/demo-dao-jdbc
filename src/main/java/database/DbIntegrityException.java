package database;

/**
 * Uma exceção personalizada que representa problemas de integridade de banco de dados.
 * Esta exceção é lançada quando ocorrem violações de integridade no banco de dados,
 * como tentativas de exclusão de registros que têm dependências referenciadas.
 */
public class DbIntegrityException extends RuntimeException {
  /**
   * Construtor da classe DbIntegrityException.
   *
   * @param msg A mensagem de erro explicando a causa da exceção.
   */
  public DbIntegrityException(String msg) {
    super(msg);
  }
}
