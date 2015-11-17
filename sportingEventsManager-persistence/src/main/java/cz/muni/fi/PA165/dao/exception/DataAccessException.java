package cz.muni.fi.PA165.dao.exception;

/**
 * Created by jbouska on 27.10.15.
 */

/**
 * Data access exception
 */
public class DataAccessException extends RuntimeException {

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable tr) {
		super(message, tr);


	}
}
