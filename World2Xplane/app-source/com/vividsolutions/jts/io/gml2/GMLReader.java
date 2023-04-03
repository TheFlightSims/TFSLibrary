/*     */ package com.vividsolutions.jts.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class GMLReader {
/*     */   public Geometry read(String gml, GeometryFactory geometryFactory) throws SAXException, IOException, ParserConfigurationException {
/* 101 */     return read(new StringReader(gml), geometryFactory);
/*     */   }
/*     */   
/*     */   public Geometry read(Reader reader, GeometryFactory geometryFactory) throws SAXException, IOException, ParserConfigurationException {
/* 116 */     SAXParserFactory fact = SAXParserFactory.newInstance();
/* 118 */     fact.setNamespaceAware(false);
/* 119 */     fact.setValidating(false);
/* 121 */     SAXParser parser = fact.newSAXParser();
/* 123 */     if (geometryFactory == null)
/* 124 */       geometryFactory = new GeometryFactory(); 
/* 126 */     GMLHandler gh = new GMLHandler(geometryFactory, null);
/* 127 */     parser.parse(new InputSource(reader), gh);
/* 129 */     return gh.getGeometry();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\gml2\GMLReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */