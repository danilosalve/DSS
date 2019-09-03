package Pessoa;

public interface IPessoa {
	
	public void setId(int nId);
	public void setNome(String cNome);
	public void setStatus(boolean lStatus);
	public int getId();
	public String getNome();
	public boolean isBloqueado();
	public String toString();

}
