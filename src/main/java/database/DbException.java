package database;

/**
 * Uma exceção personalizada para representar erros relacionados ao banco de dados.
 * Esta exceção é usada para capturar e relatar problemas gerais relacionados ao banco de dados
 * que ocorrem durante a execução do aplicativo.
 */
public class DbException extends RuntimeException {
  /**
   * Construtor da classe DbException.
   *
   * @param msg A mensagem de erro explicando a causa da exceção.
   */
  public DbException(String msg) {
    super(msg);
  }
}
