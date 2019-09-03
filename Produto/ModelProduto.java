package Produto;

import Armazem.IModelArmazem;
import Armazem.ModelArmazem;
import Categoria.IModelCateg;
import Categoria.ModelCateg;
import Cores.IModelCores;
import Cores.ModelCores;
import Fabricante.IModelFabricante;
import Fabricante.ModelFabricante;
import Tamanho.IModelTamanho;
import Tamanho.ModelTamanho;
import UnidadeMedida.IModelUnidMed;
import UnidadeMedida.ModelUnidMed;

public class ModelProduto implements IModelProduto {
	
	private String cCodigo;
	private String cNome;
	private String cCodbar;
	private double nVlrCusto;
	private double nVlrVenda;
	private double nPeso;
	private int nQtdMinima;
	private boolean lBlql;	
	private IModelArmazem 		oMdlArmazem	  	= new ModelArmazem();
	private IModelCateg 		oMdlCategoria 	= new ModelCateg();
	private IModelCores 		oMdlCores 		= new ModelCores();
	private IModelFabricante 	oMdlFabricante 	= new ModelFabricante();
	private IModelUnidMed 		oMdlUnidMed 	= new ModelUnidMed();
	private IModelTamanho 		oMdlTamanho		= new ModelTamanho();
	@Override
	public void setCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	@Override
	public void setNome(String cNome) {
		this.cNome = cNome;
	}
	@Override
	public void setCodBar(String cCodbar) {
		this.cCodbar = cCodbar;
	}
	@Override
	public void setVlrCusto(double nVlrCusto) {
		this.nVlrCusto = nVlrCusto;
	}
	@Override
	public void setVlrVenda(double nVlrVenda) {
		this.nVlrVenda = nVlrVenda;
	}
	@Override
	public void setVlrPeso(double nPeso) {
		this.nPeso = nPeso;
	}
	@Override
	public void setQtdMinima(int nQtdMinima) {
		this.nQtdMinima = nQtdMinima;
	}
	@Override
	public void setBloq(boolean lBlql) {
		this.lBlql = lBlql;
	}
	@Override
	public void setArmazem(IModelArmazem oModel) {
		this.oMdlArmazem = oModel;
	}
	@Override
	public void setCategoria(IModelCateg oModel) {
		this.oMdlCategoria = oModel;
	}
	@Override
	public void setCor(IModelCores oModel) {
		this.oMdlCores = oModel;
	}
	@Override
	public void setFabricante(IModelFabricante oModel) {
		this.oMdlFabricante = oModel;		
	}
	@Override
	public void setUM(IModelUnidMed oModel) {
		this.oMdlUnidMed = oModel;
	}
	@Override
	public void setTamanho(IModelTamanho oModel) {
		this.oMdlTamanho = oModel;
	}
	@Override
	public String getCodigo() {		
		return cCodigo;
	}
	@Override
	public String getNome() {
		return cNome;
	}
	@Override
	public String getCodBar() {
		return cCodbar;
	}
	@Override
	public double getVlrCusto() {
		return nVlrCusto;
	}
	@Override
	public double getVlrVenda() {
		return nVlrVenda;
	}
	@Override
	public double getVlrPeso() {
		return nPeso;
	}
	@Override
	public int getQtdMinima() {
		return nQtdMinima;
	}
	@Override
	public boolean getBloq() {
		return lBlql;
	}
	@Override
	public IModelArmazem getArmazem() {
		return oMdlArmazem;
	}
	@Override
	public IModelCateg getCategoria() {
		return oMdlCategoria;
	}
	@Override
	public IModelCores getCor() {
		return oMdlCores;
	}
	@Override
	public IModelFabricante getFabricante() {
		return oMdlFabricante;
	}
	@Override
	public IModelUnidMed getUM() {
		return oMdlUnidMed;
	}
	@Override
	public IModelTamanho getTamanho() {
		return oMdlTamanho;
	}
	
	public String toString(){
		return cCodigo + " - "+ cNome;
	}
}
