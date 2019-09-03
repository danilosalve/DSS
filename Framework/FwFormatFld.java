package Framework;

import javax.swing.text.MaskFormatter;

	public class FwFormatFld {
		
	public static MaskFormatter MascaraData(String MascaraData){
        
        MaskFormatter F_MascaraData = new MaskFormatter();
        try{
        	F_MascaraData.setMask(MascaraData); //Atribui a mascara
            F_MascaraData.setPlaceholderCharacter(' '); //Caracter para preencimento 
        }
        catch (Exception excecao) {
        excecao.printStackTrace();
        } 
        return F_MascaraData; 
	}
	
	public static MaskFormatter MascaraMedidas(String MascaraMedidas){
        
        MaskFormatter F_MascaraData = new MaskFormatter();
        try{
        	F_MascaraData.setMask(MascaraMedidas); //Atribui a mascara
            F_MascaraData.setPlaceholderCharacter(' '); //Caracter para preencimento 
        }
        catch (Exception excecao) {
        excecao.printStackTrace();
        } 
        return F_MascaraData; 
	}
}
