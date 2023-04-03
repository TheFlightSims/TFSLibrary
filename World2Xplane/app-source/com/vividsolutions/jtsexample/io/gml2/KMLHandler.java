/*     */ package com.vividsolutions.jtsexample.io.gml2;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.io.gml2.GMLHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ class KMLHandler extends DefaultHandler {
/*  59 */   private List geoms = new ArrayList();
/*     */   
/*     */   private GMLHandler currGeomHandler;
/*     */   
/*  62 */   private String lastEltName = null;
/*     */   
/*  63 */   private GeometryFactory fact = new FixingGeometryFactory();
/*     */   
/*     */   public List getGeometries() {
/*  72 */     return this.geoms;
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String name, String qName, Attributes atts) throws SAXException {
/*  87 */     if (name.equalsIgnoreCase("Polygon"))
/*  88 */       this.currGeomHandler = new GMLHandler(this.fact, null); 
/*  90 */     if (this.currGeomHandler != null)
/*  91 */       this.currGeomHandler.startElement(uri, name, qName, atts); 
/*  92 */     if (this.currGeomHandler == null)
/*  93 */       this.lastEltName = name; 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 100 */     if (this.currGeomHandler != null) {
/* 101 */       this.currGeomHandler.characters(ch, start, length);
/*     */     } else {
/* 104 */       String content = (new String(ch, start, length)).trim();
/* 105 */       if (content.length() > 0)
/* 106 */         System.out.println(this.lastEltName + "= " + content); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 113 */     if (this.currGeomHandler != null)
/* 114 */       this.currGeomHandler.ignorableWhitespace(ch, start, length); 
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String name, String qName) throws SAXException {
/* 130 */     if (this.currGeomHandler != null) {
/* 131 */       this.currGeomHandler.endElement(uri, name, qName);
/* 133 */       if (this.currGeomHandler.isGeometryComplete()) {
/* 134 */         Geometry g = this.currGeomHandler.getGeometry();
/* 135 */         System.out.println(g);
/* 136 */         this.geoms.add(g);
/* 139 */         this.currGeomHandler = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\io\gml2\KMLHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */