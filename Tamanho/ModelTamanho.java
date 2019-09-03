package Tamanho;

/**
 * Model Tamanho
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */

public class ModelTamanho implements IModelTamanho {
	
	private String cCodigo;
	private String cDescricao;
	private double nPeso;
	private double nAltura; 
	private double nLargura;
	private double nComprimento;
	
	public String getCodigo() {
		return cCodigo;
	}
	public void setCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getDescricao() {
		return cDescricao;
	}
	public void setDescricao(String cDescricao) {
		this.cDescricao = cDescricao;
	}
	public double getPeso() {
		return nPeso;
	}
	public void setPeso(double nPeso) {
		this.nPeso = nPeso;
	}
	public double getAltura() {
		return nAltura;
	}
	public void setAltura(double nAltura) {
		this.nAltura = nAltura;
	}
	public double getLargura() {
		return nLargura;
	}
	public void setLargura(double nLargura) {
		this.nLargura = nLargura;
	}
	public double getComprimento() {
		return nComprimento;
	}
	public void setComprimento(double nComprimento) {
		this.nComprimento = nComprimento;
	}
	public String toString(){
		return cCodigo + "|" + cDescricao;
	}
}
