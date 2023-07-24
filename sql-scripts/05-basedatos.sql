USE QuirkyBookProject
GO

/****** Object:  Table [dbo].[tipo_repuesto]    Script Date: 18/07/2023 7:49:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tipo_repuesto](
	pk_id int IDENTITY(1,1) NOT NULL,
	nombre varchar(50) default '',
	descripcion varchar(100) default '',
	fecha_registro date,
	CONSTRAINT [PK_tipo_repuesto] PRIMARY KEY(pk_id)
)
GO

/****** Object:  Table [dbo].[repuesto]    Script Date: 18/07/2023 7:49:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[repuesto](
	pk_id int IDENTITY(1,1) NOT NULL,
	nombre varchar(50) default '',
	descripcion varchar(100) default '',
	fecha_registro datetime,
	tipo_repuesto_id int not null,
	CONSTRAINT [PK_repuesto] PRIMARY KEY(pk_id)
)
GO
ALTER TABLE [dbo].[repuesto]  WITH CHECK ADD  CONSTRAINT [fk_tipo_repuesto] FOREIGN KEY([tipo_repuesto_id])
REFERENCES [dbo].[tipo_repuesto] ([pk_id])
ON DELETE CASCADE
GO

/****** Object:  Table [dbo].[cb_address]    Script Date: 18/07/2023 7:49:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO





CREATE TABLE [dbo].[cb_address](
	[idaddress] [int] IDENTITY(1,1) NOT NULL,
	[idaddresses] [int] NULL,
	[address] [varchar](500) NULL,
	[postalnumber] [varchar](20) NULL,
	[mainphone] [varchar](100) NULL,
	[movilephone] [varchar](100) NULL,
	[phone2] [varchar](100) NULL,
	[phone3] [varchar](100) NULL,
	[carrier] [varchar](200) NULL,
	[addresstype] [varchar](100) NULL,
	[locality] [varchar](250) NULL,
	[estate] [varchar](250) NULL,
	[idcountry] [int] NULL,
	[notes1] [varchar](500) NULL,
 CONSTRAINT [pk_cb_address] PRIMARY KEY CLUSTERED 
(
	[idaddress] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[cb_address]  WITH CHECK ADD  CONSTRAINT [fk_cb_address_idaddresses] FOREIGN KEY([idaddresses])
REFERENCES [dbo].[cb_addresses] ([idaddresses])
GO

ALTER TABLE [dbo].[cb_address] CHECK CONSTRAINT [fk_cb_address_idaddresses]
GO

ALTER TABLE [dbo].[cb_address]  WITH CHECK ADD  CONSTRAINT [fk_cb_address_idcountry] FOREIGN KEY([idcountry])
REFERENCES [dbo].[cb_country] ([idcountry])
GO

ALTER TABLE [dbo].[cb_address] CHECK CONSTRAINT [fk_cb_address_idcountry]
GO


