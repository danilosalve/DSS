package Produto;

import Armazem.IModelArmazem;
import Categoria.IModelCateg;
import Cores.IModelCores;
import Fabricante.IModelFabricante;
import Tamanho.IModelTamanho;
import UnidadeMedida.IModelUnidMed;

public interface IModelProduto {
	
	public void setCodigo(String cCodigo);
	public void setNome(String cNome);
	public void setCodBar(String cCodbar);
	public void setVlrCusto(double nVlrCusto);
	public void setVlrVenda(double nVlrVenda);
	public void setVlrPeso(double nPeso);
	public void setQtdMinima(int nQtdMinima);
	public void setBloq(boolean lBlql);	
	public void setArmazem(IModelArmazem oModel);
	public void setCategoria(IModelCateg oModel);
	public void setCor(IModelCores oModel);
	public void setFabricante(IModelFabricante oModel);
	public void setUM(IModelUnidMed oModel);
	public void setTamanho(IModelTamanho oModel);	
	public String getCodigo();
	public String getNome();
	public String getCodBar();
	public double getVlrCusto();
	public double getVlrVenda();
	public double getVlrPeso();
	public int getQtdMinima();
	public boolean getBloq();	
	public IModelArmazem getArmazem();
	public IModelCateg getCategoria();
	public IModelCores getCor();
	public IModelFabricante getFabricante();
	public IModelUnidMed getUM();
	public IModelTamanho getTamanho();

}
