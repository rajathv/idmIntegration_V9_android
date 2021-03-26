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

			<xsl:apply-templates select="CustomFormDynamicLookUpRS/CustomFormWrapperType/CustomFormDynamicLookUpData/CustomFormData" />

		</IDData>
	</xsl:template>


	<xsl:template match="CustomFormDynamicLookUpRS/CustomFormWrapperType/CustomFormDynamicLookUpData/CustomFormData">

		<FormStatus>
			<xsl:value-of select="State"/>
		</FormStatus>

		<Name>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59471_TEXT']/PSGFValue/PSGFValue"/>
		</Name>


		<IDState>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59465_TEXT']/PSGFValue/PSGFValue"/>
		</IDState>

		<!--<MatchingTemplateId>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59487_TEXT']/PSGFValue/PSGFValue"/>
		</MatchingTemplateId>-->
		<!--<ColorCode>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59536_TEXT']/PSGFValue/PSGFValue"/>
		</ColorCode>-->
		<MRZDetected>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59474_TEXT']/PSGFValue/PSGFValue"/>
		</MRZDetected>
		<IssuingCountry>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59509_TEXT']/PSGFValue/PSGFValue"/>
		</IssuingCountry>



		<FaceDetected>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59473_TEXT']/PSGFValue/PSGFValue"/>
		</FaceDetected>

		<ExpirationDate>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59468_TEXT']/PSGFValue/PSGFValue"/>
		</ExpirationDate>
		<IDNumber2>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59530_TEXT']/PSGFValue/PSGFValue"/>
		</IDNumber2>
		<IDType>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59466_TEXT']/PSGFValue/PSGFValue"/>
		</IDType>


		<!--<ValidIDNumber>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59476_TEXT']/PSGFValue/PSGFValue"/>
		</ValidIDNumber>-->



		<MRZValid>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59500_TEXT']/PSGFValue/PSGFValue"/>
		</MRZValid>

		<DateofBirth>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59470_TEXT']/PSGFValue/PSGFValue"/>
		</DateofBirth>


		<ProcessedImageID_BACK>
			<xsl:call-template name="getJsonValueByKey">
				<xsl:with-param name="value" select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59527_BLOB']/PSGFValue/PSGFValue"/>
				<xsl:with-param name="key">
					<xsl:value-of select="'fileContent'"/>
				</xsl:with-param>
			</xsl:call-template>
		</ProcessedImageID_BACK>

		<Exception>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59488_TEXT']/PSGFValue/PSGFValue"/>
		</Exception>
		<SecurityFeatureMatch>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59475_TEXT']/PSGFValue/PSGFValue"/>
		</SecurityFeatureMatch>


		<AgeOver18>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59479_TEXT']/PSGFValue/PSGFValue"/>
		</AgeOver18>

		<LastName>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59499_TEXT']/PSGFValue/PSGFValue"/>
		</LastName>




		<ProcessedImage>
			<xsl:call-template name="getJsonValueByKey">
				<xsl:with-param name="value" select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59483_BLOB']/PSGFValue/PSGFValue"/>
				<xsl:with-param name="key">
					<xsl:value-of select="'fileContent'"/>
				</xsl:with-param>
			</xsl:call-template>
		</ProcessedImage>




		<ImageUpload>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59459_BLOB']/PSGFValue/PSGFValue"/>
		</ImageUpload>
		<IDNumber>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59467_TEXT']/PSGFValue/PSGFValue"/>
		</IDNumber>
		<IssueDate>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59469_TEXT']/PSGFValue/PSGFValue"/>
		</IssueDate>


		<MRZErrorMessages>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59524_TEXT']/PSGFValue/PSGFValue"/>
		</MRZErrorMessages>
		<IDNotExpired>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59477_TEXT']/PSGFValue/PSGFValue"/>
		</IDNotExpired>

		<MRZData>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59497_TEXT']/PSGFValue/PSGFValue"/>
		</MRZData>



		<FirstName>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59498_TEXT']/PSGFValue/PSGFValue"/>
		</FirstName>
		<IDBackImage>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59528_BLOB']/PSGFValue/PSGFValue"/>
		</IDBackImage>

		<ValidIssueDate>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59478_TEXT']/PSGFValue/PSGFValue"/>
		</ValidIssueDate>
		<Address>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59472_TEXT']/PSGFValue/PSGFValue"/>
		</Address>


		<IDCountry>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59464_TEXT']/PSGFValue/PSGFValue"/>
		</IDCountry>

		<!--<ID_THIRD_IMAGE>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59531_BLOB']/PSGFValue/PSGFValue"/>
		</ID_THIRD_IMAGE>-->

		<Nationality>
			<xsl:value-of select="CustomFormDetail[PSGFKey = 'P2184_S3765_G9300_F59502_TEXT']/PSGFValue/PSGFValue"/>
		</Nationality>


	</xsl:template>


	<xsl:template name="getJsonValueByKey">
		<xsl:param name="key"/>
		<xsl:param name="value"/>
		<xsl:variable name="quot">&quot;</xsl:variable>
		<xsl:value-of select="substring-before(translate(substring-after($value,concat($key,$quot,':',$quot)),$quot,'_'),'_')"/>
		<!-- <xsl:value-of select="substring-after($value,concat($key,$quot,':',$quot))"/>  -->
		<!-- <xsl:value-of select="'imageValue'"/>  -->
	</xsl:template>

</xsl:stylesheet>