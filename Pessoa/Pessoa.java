package Pessoa;

/** Classe Abstrata Pessoa - Classe base 
 * @author Danilo Otavio Lima Salve
 * @since 03/03/2019
 * @version 1.0
 */
public class Pessoa implements IPessoa {
	
	private int nId;
	private String cNome;
	private boolean lStatus;
	@Override
	public void setId(int nId) {
		this.nId = nId;
	}
	
	@Override
	public void setNome(String cNome) {
		this.cNome = cNome;		
	}
	
	@Override
	public void setStatus(boolean lStatus) {
		this.lStatus = lStatus;		
	}
	
	@Override
	public int getId() {
		return nId;
	}
	@Override
	public String getNome() {
		return cNome;
	}
	@Override
	public boolean isBloqueado() {
		return lStatus;
	}
	
	public Pessoa(){
		this.nId = 0;
		this.cNome = "";
		this.lStatus = false;
	}
	
	public String toString(){
		return cNome;
	}
}
