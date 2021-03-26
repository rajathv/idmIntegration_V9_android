<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output omit-xml-declaration="yes" indent="yes"/>
	<xsl:template match="/GenericInterface/CustomFormInterface">
		<IDData>
			<Status>
				<xsl:value-of select="CustomFormDynamicLookUpRS/Status/Status_Code"/>
			</Status>
			<Status_Message>
				<xsl:value-of select="CustomFormDynamicLookUpRS/Status/Status_Message"/>
			</Status_Message>
			
			<FormID>
				<xsl:value-of select="CustomFormDynamicLookUpRS/Entity_Id"/>
			</FormID>
			
			<xsl:apply-templates select="CustomFormDynamicLookUpRS/CustomFormWrapperType/CustomFormDynamicLookUpData/CustomFormData" />

		</IDData>
	</xsl:template>

	<xsl:template match="CustomFormDynamicLookUpRS/CustomFormWrapperType/CustomFormDynamicLookUpData/CustomFormData">

		<FormStatus>
			<xsl:value-of select="State"/>
		</FormStatus>

		<FaceVerificationStatus>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2308_S3986_G9783_F62653_TEXT']/PSGFValue/PSGFValue"/>
		</FaceVerificationStatus>
		
	</xsl:template>
	
</xsl:stylesheet>