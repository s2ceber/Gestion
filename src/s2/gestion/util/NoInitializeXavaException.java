package s2.gestion.util;

import org.openxava.util.XavaException;

public class NoInitializeXavaException extends XavaException {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	public NoInitializeXavaException() {
		super();
	}
	
	/**
	 * @param idOrMessage Id for look messsage in XavaResources, or text of the
	 * 		message
	 */
	public NoInitializeXavaException(String idOrMessage) {
		super(idOrMessage);		
	}
	
	public NoInitializeXavaException(String idOrMessage, Object argv0) {
		super(idOrMessage, argv0);
	}
}
