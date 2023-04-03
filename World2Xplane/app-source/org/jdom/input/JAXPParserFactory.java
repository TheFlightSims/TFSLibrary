/*     */ package org.jdom.input;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.jdom.JDOMException;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ class JAXPParserFactory {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JAXPParserFactory.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   private static final String JAXP_SCHEMA_LANGUAGE_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
/*     */   
/*     */   private static final String JAXP_SCHEMA_LOCATION_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaSource";
/*     */   
/*     */   public static XMLReader createParser(boolean validating, Map features, Map properties) throws JDOMException {
/*     */     try {
/* 122 */       SAXParser parser = null;
/* 125 */       SAXParserFactory factory = SAXParserFactory.newInstance();
/* 126 */       factory.setValidating(validating);
/* 127 */       factory.setNamespaceAware(true);
/*     */       try {
/* 131 */         parser = factory.newSAXParser();
/* 133 */       } catch (ParserConfigurationException e) {
/* 134 */         throw new JDOMException("Could not allocate JAXP SAX Parser", e);
/*     */       } 
/* 138 */       setProperty(parser, properties, "http://java.sun.com/xml/jaxp/properties/schemaLanguage");
/* 139 */       setProperty(parser, properties, "http://java.sun.com/xml/jaxp/properties/schemaSource");
/* 142 */       return parser.getXMLReader();
/* 144 */     } catch (SAXException e) {
/* 145 */       throw new JDOMException("Could not allocate JAXP SAX Parser", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setProperty(SAXParser parser, Map properties, String name) throws JDOMException {
/*     */     try {
/* 163 */       if (properties.containsKey(name))
/* 164 */         parser.setProperty(name, properties.get(name)); 
/* 167 */     } catch (SAXNotSupportedException e) {
/* 168 */       throw new JDOMException(name + " property not supported for JAXP parser " + parser.getClass().getName());
/* 172 */     } catch (SAXNotRecognizedException e) {
/* 173 */       throw new JDOMException(name + " property not recognized for JAXP parser " + parser.getClass().getName());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\input\JAXPParserFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */