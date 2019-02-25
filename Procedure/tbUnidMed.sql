CREATE PROCEDURE SP_tbUnidMed
 --1 Criar Tabela
 --2 Excluir Tabela
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbUnidMed'))
			BEGIN
				TRUNCATE TABLE tbUnidMed;
				INSERT INTO tbUnidMed (CD_UM,DESC_UM,D_E_L_E_T_) VALUES
				('CX','CAIXA',' '),
				('G','GRAMA',' '),
				('KG','QUILOGRAMA',' '),
				('L','LITRO',' '),
				('MG','MILIGRAMA',' '),
				('ML','MILILITRO',' '),
				('UN','UNIDADE',' ')
			END
		ELSE
			BEGIN
				CREATE TABLE tbUnidMed (
				ID_UM INT IDENTITY (1,1) PRIMARY KEY,
				CD_UM CHAR(2) UNIQUE NOT NULL,
				DESC_UM VARCHAR(30) NOT NULL,
				D_E_L_E_T_ CHAR(1) NOT NULL
				)
				EXEC SP_tbUnidMed '1'
			END
		END
	ELSE
		DROP TABLE tbUnidMed