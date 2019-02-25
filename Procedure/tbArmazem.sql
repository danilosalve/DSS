CREATE PROCEDURE SP_tbArmazem
 --1 Criar/Recriar
 --2 Excluir Tabela
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbArmazem'))
			BEGIN
				DROP TABLE tbArmazem
				CREATE TABLE tbArmazem (
				ID_ARM INT IDENTITY (1,1) PRIMARY KEY,
				DESC_ARM VARCHAR(30) NOT NULL,
				BLOQ_ARM CHAR(1) NOT NULL,
				D_E_L_E_T_ CHAR(1)  NOT NULL
				)
			END
		ELSE
			BEGIN
				CREATE TABLE tbArmazem (
				ID_ARM INT IDENTITY (1,1) PRIMARY KEY,
				DESC_ARM VARCHAR(30) NOT NULL,
				BLOQ_ARM CHAR(1) NOT NULL,
				D_E_L_E_T_ CHAR(1)  NOT NULL
				)
			END
		END
	ELSE
		DROP TABLE tbArmazem