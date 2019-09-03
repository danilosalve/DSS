USE [DSS]
GO

CREATE PROCEDURE [dbo].[SP_tbTamanho]
 --1 Criar
 --2 Excluir Tabela
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF NOT (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbTamanho'))
			BEGIN
				CREATE TABLE tbTamanho (
				COD_TAM Varchar(6) PRIMARY KEY,
				DESC_TAM Varchar(30) NOT NULL,
				PESO_TAM Decimal(14,3) NOT NULL DEFAULT 0,
				ALT_TAM Decimal (14,3) NOT NULL DEFAULT 0,
				LARG_TAM Decimal (14,3) NOT NULL DEFAULT 0,
				COMP_TAM Decimal(14,3) NOT NULL DEFAULT 0,
				D_E_L_E_T_ CHAR(1) NOT NULL DEFAULT ' '
				)
			END
		END
	ELSE
		DROP TABLE tbTamanho

GO


