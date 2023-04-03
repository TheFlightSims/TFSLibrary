/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class PieDatasetHandler extends RootHandler implements DatasetTags {
/*  63 */   private DefaultPieDataset dataset = null;
/*     */   
/*     */   public PieDataset getDataset() {
/*  72 */     return (PieDataset)this.dataset;
/*     */   }
/*     */   
/*     */   public void addItem(Comparable key, Number value) {
/*  82 */     this.dataset.setValue(key, value);
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 100 */     DefaultHandler current = getCurrentHandler();
/* 101 */     if (current != this) {
/* 102 */       current.startElement(namespaceURI, localName, qName, atts);
/* 104 */     } else if (qName.equals("PieDataset")) {
/* 105 */       this.dataset = new DefaultPieDataset();
/* 107 */     } else if (qName.equals("Item")) {
/* 108 */       ItemHandler subhandler = new ItemHandler(this, this);
/* 109 */       getSubHandlers().push(subhandler);
/* 110 */       subhandler.startElement(namespaceURI, localName, qName, atts);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 128 */     DefaultHandler current = getCurrentHandler();
/* 129 */     if (current != this)
/* 130 */       current.endElement(namespaceURI, localName, qName); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\PieDatasetHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */