package Fabricante;

import Pessoa.IPessoa;

public interface IModelFabricante {
	
	public void setTipo(int nTipo);
	public void setPessoa(IPessoa oPessoa);
	public int getTipo();
	public IPessoa getPessoa();
	public String toString();
}
