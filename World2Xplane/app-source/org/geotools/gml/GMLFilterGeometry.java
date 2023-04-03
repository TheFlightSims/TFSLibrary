/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.XMLFilterImpl;
/*     */ 
/*     */ public class GMLFilterGeometry extends XMLFilterImpl implements GMLHandlerGeometry {
/*     */   private GMLHandlerJTS parent;
/*     */   
/*  47 */   private GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*  50 */   private SubHandlerFactory handlerFactory = new SubHandlerFactory();
/*     */   
/*     */   private SubHandler currentHandler;
/*     */   
/*     */   public GMLFilterGeometry(GMLHandlerJTS parent) {
/*  62 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public void geometryStart(String localName, Attributes atts) throws SAXException {
/*  80 */     String srs = null;
/*  81 */     for (int i = 0; i < atts.getLength(); i++) {
/*  82 */       String NAME = atts.getQName(i);
/*  83 */       if ("srs".equalsIgnoreCase(NAME))
/*  84 */         srs = atts.getValue(i); 
/*     */     } 
/*  87 */     if (this.currentHandler == null) {
/*  88 */       this.currentHandler = this.handlerFactory.create(localName);
/*     */     } else {
/*  90 */       this.currentHandler.subGeometry(localName, 1);
/*     */     } 
/*  92 */     this.currentHandler.setSRS(srs);
/*     */   }
/*     */   
/*     */   public void geometryEnd(String localName) throws SAXException {
/* 107 */     if (this.currentHandler.isComplete(localName)) {
/* 108 */       this.parent.geometry(this.currentHandler.create(this.geometryFactory));
/* 109 */       this.currentHandler = null;
/*     */     } else {
/* 111 */       this.currentHandler.subGeometry(localName, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void geometrySub(String localName) throws SAXException {
/* 125 */     this.currentHandler.subGeometry(localName, 3);
/*     */   }
/*     */   
/*     */   public void gmlCoordinates(double x, double y) throws SAXException {
/* 138 */     this.currentHandler.addCoordinate(new Coordinate(x, y));
/*     */   }
/*     */   
/*     */   public void gmlCoordinates(double x, double y, double z) throws SAXException {
/* 154 */     this.currentHandler.addCoordinate(new Coordinate(x, y, z));
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 174 */     this.parent.startElement(namespaceURI, localName, qName, atts);
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 192 */     this.parent.characters(ch, start, length);
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 210 */     this.parent.endElement(namespaceURI, localName, qName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\GMLFilterGeometry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */