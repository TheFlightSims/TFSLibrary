/*    */ package com.vividsolutions.jtsexample.io.gml2;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.LineNumberReader;
/*    */ import java.io.Reader;
/*    */ import java.util.List;
/*    */ import org.xml.sax.InputSource;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.XMLReader;
/*    */ import org.xml.sax.helpers.XMLReaderFactory;
/*    */ 
/*    */ class KMLReader {
/*    */   private String filename;
/*    */   
/*    */   public KMLReader(String filename) {
/* 35 */     this.filename = filename;
/*    */   }
/*    */   
/*    */   public void read() throws IOException, SAXException {
/* 43 */     XMLReader xr = XMLReaderFactory.createXMLReader();
/* 45 */     KMLHandler kmlHandler = new KMLHandler();
/* 46 */     xr.setContentHandler(kmlHandler);
/* 47 */     xr.setErrorHandler(kmlHandler);
/* 49 */     Reader r = new BufferedReader(new FileReader(this.filename));
/* 50 */     LineNumberReader myReader = new LineNumberReader(r);
/* 51 */     xr.parse(new InputSource(myReader));
/* 53 */     List geoms = kmlHandler.getGeometries();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\io\gml2\KMLReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */