/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.jfree.data.DefaultKeyedValues;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class CategorySeriesHandler extends DefaultHandler implements DatasetTags {
/*     */   private RootHandler root;
/*     */   
/*     */   private Comparable seriesKey;
/*     */   
/*     */   private DefaultKeyedValues values;
/*     */   
/*     */   public CategorySeriesHandler(RootHandler root) {
/*  73 */     this.root = root;
/*  74 */     this.values = new DefaultKeyedValues();
/*     */   }
/*     */   
/*     */   public void setSeriesKey(Comparable key) {
/*  83 */     this.seriesKey = key;
/*     */   }
/*     */   
/*     */   public void addItem(Comparable key, Number value) {
/*  93 */     this.values.addValue(key, value);
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 111 */     if (qName.equals("Series")) {
/* 112 */       setSeriesKey(atts.getValue("name"));
/* 113 */       ItemHandler subhandler = new ItemHandler(this.root, this);
/* 114 */       this.root.pushSubHandler(subhandler);
/* 116 */     } else if (qName.equals("Item")) {
/* 117 */       ItemHandler subhandler = new ItemHandler(this.root, this);
/* 118 */       this.root.pushSubHandler(subhandler);
/* 119 */       subhandler.startElement(namespaceURI, localName, qName, atts);
/*     */     } else {
/* 123 */       throw new SAXException("Expecting <Series> or <Item> tag...found " + qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) {
/* 140 */     if (this.root instanceof CategoryDatasetHandler) {
/* 141 */       CategoryDatasetHandler handler = (CategoryDatasetHandler)this.root;
/* 143 */       Iterator iterator = this.values.getKeys().iterator();
/* 144 */       while (iterator.hasNext()) {
/* 145 */         Comparable key = iterator.next();
/* 146 */         Number value = this.values.getValue(key);
/* 147 */         handler.addItem(this.seriesKey, key, value);
/*     */       } 
/* 150 */       this.root.popSubHandler();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\CategorySeriesHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */