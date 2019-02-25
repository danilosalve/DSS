package Framework;

public class FwVldChar {
	/**
	 * Verifica se e letra ou numero inteiro
	 * @since 25/02/2019
	 * @param s - Caracter a ser validado
	 * @return boolean
	 */
	public static boolean isNumeric (String s) {
	    try {
	        Long.parseLong (s); 
	        return true;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
}
