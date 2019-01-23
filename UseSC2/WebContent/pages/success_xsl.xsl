<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head><title><xsl:value-of select="view/header/title"/></title></head>
			<body>
				<xsl:for-each select="view/body/form">
					<form>
						<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
						<xsl:attribute name="action"><xsl:value-of select="action"/></xsl:attribute>
						<xsl:attribute name="method"><xsl:value-of select="method"/></xsl:attribute>
						<xsl:for-each select="textView">
							<label><xsl:value-of select="label"/></label>
							<input>
								<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
								<xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>
							</input><br/>
						</xsl:for-each>
						<xsl:for-each select="buttonView">
							<label><xsl:value-of select="label"/></label>
							<input type="submit">
								<xsl:attribute name="value"><xsl:value-of select="name"/></xsl:attribute>
							</input><br/>
						</xsl:for-each>
					</form>
				</xsl:for-each>
			</body>
		</html>
	
	</xsl:template>

</xsl:stylesheet>