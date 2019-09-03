USE [DSS]
GO

/****** Object:  StoredProcedure [dbo].[SP_tbFabricante]    Script Date: 04/03/2019 15:16:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[SP_tbFabricante]
 @DROP CHAR(1)
 AS
	IF @DROP = '1' 
	BEGIN
		IF not (EXISTS(SELECT name FROM SYS.objects WHERE NAME = 'tbFabricante'))
			BEGIN
				CREATE TABLE tbFabricante (
					ID_FAB INT IDENTITY(1,1) PRIMARY KEY,
					NOME_FAB VARCHAR(60) NOT NULL,
					BLOQ_FAB INT DEFAULT 2,
					TIPO_FAB INT DEFAULT 0,
					D_E_L_E_T_ CHAR(1) DEFAULT ' '
				)
			END
		END
	ELSE
		DROP TABLE tbFabricante

GO

