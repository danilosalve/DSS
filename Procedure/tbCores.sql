USE [DSS]
GO

/****** Object:  StoredProcedure [dbo].[SP_tbArmazem]    Script Date: 27/02/2019 21:24:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_tbCores]
 --1 Criar/Recriar
 --2 Excluir Tabela
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbCores'))
			BEGIN
				TRUNCATE TABLE tbCores
				INSERT INTO tbCores (DESC_COR) Values 
				('AMARELO'),('AZUL'),('BRANCO'),('CIANO'),('CINZA'),
				('LARANJA'),('MAGENTA'),('MARROM'),('PRETO'),
				('ROSA'),('ROXO'),('VERDE'),('VERMELHO')
			END
		ELSE
			BEGIN
				CREATE TABLE tbCores (
				ID_COR INT IDENTITY(1,1) PRIMARY KEY,
				DESC_COR VARCHAR(30) NOT NULL,
				D_E_L_E_T_ CHAR(1) NOT NULL DEFAULT ' '
				)
				EXEC SP_tbCores 1
			END
		END
	ELSE
		DROP TABLE tbCores
GO


