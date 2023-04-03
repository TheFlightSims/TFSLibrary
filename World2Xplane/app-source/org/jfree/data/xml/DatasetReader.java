/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class DatasetReader {
/*     */   public static PieDataset readPieDatasetFromXML(File file) throws IOException {
/*  74 */     InputStream in = new FileInputStream(file);
/*  75 */     return readPieDatasetFromXML(in);
/*     */   }
/*     */   
/*     */   public static PieDataset readPieDatasetFromXML(InputStream in) throws IOException {
/*  90 */     PieDataset result = null;
/*  91 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */     try {
/*  93 */       SAXParser parser = factory.newSAXParser();
/*  94 */       PieDatasetHandler handler = new PieDatasetHandler();
/*  95 */       parser.parse(in, handler);
/*  96 */       result = handler.getDataset();
/*  98 */     } catch (SAXException e) {
/*  99 */       System.out.println(e.getMessage());
/* 101 */     } catch (ParserConfigurationException e2) {
/* 102 */       System.out.println(e2.getMessage());
/*     */     } 
/* 104 */     return result;
/*     */   }
/*     */   
/*     */   public static CategoryDataset readCategoryDatasetFromXML(File file) throws IOException {
/* 119 */     InputStream in = new FileInputStream(file);
/* 120 */     return readCategoryDatasetFromXML(in);
/*     */   }
/*     */   
/*     */   public static CategoryDataset readCategoryDatasetFromXML(InputStream in) throws IOException {
/* 135 */     CategoryDataset result = null;
/* 137 */     SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */     try {
/* 139 */       SAXParser parser = factory.newSAXParser();
/* 140 */       CategoryDatasetHandler handler = new CategoryDatasetHandler();
/* 141 */       parser.parse(in, handler);
/* 142 */       result = handler.getDataset();
/* 144 */     } catch (SAXException e) {
/* 145 */       System.out.println(e.getMessage());
/* 147 */     } catch (ParserConfigurationException e2) {
/* 148 */       System.out.println(e2.getMessage());
/*     */     } 
/* 150 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\DatasetReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */