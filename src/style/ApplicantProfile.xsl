<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" standalone="yes"/>
<xsl:template match="/">
  <html>
  <body>
    <div id = "content">
      <h1>Applicant Profile</h1>
      <br />
      <div>
        <h3>Personal Info</h3>
        <table>
          <tr>
            <td>Name</td>
            <td><xsl:value-of select="//personalInfo/name" /></td>
          </tr>
          <tr>
            <td>Social security number</td>
            <td><xsl:value-of select="//personalInfo/ssn" /></td>
          </tr>
          <tr>
            <td>Personal Letter</td>
            <td><xsl:value-of select="//personalInfo/personalLetter" /></td>
          </tr>
        </table>
      </div>
      <br />
      
      
      <div>
        <h3>Studies</h3>
        <table>
          <tr>
            <td>University</td>
            <td><xsl:value-of select="//studyRecord/universityName" /></td>
          </tr>
          <tr>
            <td>Degree</td>
            <td><xsl:value-of select="//studyRecord/degree" /></td>
          </tr>
          <tr>
            <td>Graduation Year</td>
            <td><xsl:value-of select="//studyRecord/year" /></td>
          </tr>
          <tr>
              
            <td>Grade point average</td>
            <td>
                <!-- Smart implementation for calculating the GPA, lol -->
                <xsl:variable name="summation" select="sum(//tempCourses/tempCourse/@grade)" />
                <xsl:variable name="numberOfCourses" select="count(//tempCourses/tempCourse)" />
                
                <xsl:variable name="gpaCalc" select="$summation div $numberOfCourses " />

                <xsl:value-of select="$gpaCalc" />
                
            </td>
          </tr>
        </table>
      </div>
      <br />
      <div>
        <h3>Employment Record</h3>
        <xsl:for-each select="//employmentRecord/employment">
        <table>
          <tr>
            <td>Company Name</td>
            <td><xsl:value-of select="companyName" /></td>
          </tr>
          <tr>
            <td>From</td>
            <td><xsl:value-of select="fromDate" /></td>
          </tr>
          <tr>
            <td>To</td>
            <td><xsl:value-of select="toDate" /></td>
          </tr>
          <tr>
            <td>Telephone number</td>
            <td><xsl:value-of select="telephoneNumber" /></td>
          </tr>
        </table>
        <br />
        </xsl:for-each>
      </div>
 



      
    </div>
  </body>
  </html>
</xsl:template>
  
</xsl:stylesheet>
