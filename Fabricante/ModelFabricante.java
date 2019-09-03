package Fabricante;
import Pessoa.*;

/**
 * Classe Model Fabricante
 * @author Danilo Salve
 * @version 1.0
 * @since 03/03/2019
 * @see Pessoa.Pessoa
 */

public class ModelFabricante implements IModelFabricante{
	private int nTipo;
	private IPessoa oPessoa = new Pessoa();
	
	@Override
	public void setTipo(int nTipo) {
		this.nTipo = nTipo;
	}

	@Override
	public int getTipo() {
		return nTipo;
	}
	
	public void setPessoa(IPessoa oPessoa){
		this.oPessoa = oPessoa;
	}
	
	public IPessoa getPessoa(){
		return oPessoa;
	}
	
	public String toString(){
		return oPessoa.getNome();
	}
}
