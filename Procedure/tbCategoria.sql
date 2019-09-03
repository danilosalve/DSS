USE [DSS]
GO


CREATE PROCEDURE [dbo].[SP_tbCategoria]
 --1 Criar/Recriar
 --2 Excluir Tabela
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF not (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbCategoria'))
			BEGIN
				CREATE TABLE tbCategoria (
				ID_CATEG INT IDENTITY(1,1) PRIMARY KEY,
				DESC_CATEG VARCHAR(30) NOT NULL,
				D_E_L_E_T_ CHAR(1) NOT NULL DEFAULT ' '
				)
			END
		END
	ELSE
		DROP TABLE tbCategoria
GO


