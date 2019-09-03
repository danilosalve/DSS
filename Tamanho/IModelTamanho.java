package Tamanho;
/**
 * Interface da classe Model Tamanho
 * @author Danilo Salve
 * @since 03/03/2019
 * @version 1.0
 */
public interface IModelTamanho {
	
	public void setCodigo(String cCodigo);
	public void setDescricao(String cDescricao);
	public void setPeso(double nPeso);
	public void setAltura(double nAltura);
	public void setLargura (double nLargura);
	public void setComprimento (double nComprimento);
	public String getCodigo();
	public String getDescricao();
	public double getPeso();
	public double getAltura();
	public double getLargura();
	public double getComprimento();
	public String toString();
	
}
