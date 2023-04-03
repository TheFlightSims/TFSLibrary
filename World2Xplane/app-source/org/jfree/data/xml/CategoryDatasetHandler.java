/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class CategoryDatasetHandler extends RootHandler implements DatasetTags {
/*  63 */   private DefaultCategoryDataset dataset = null;
/*     */   
/*     */   public CategoryDataset getDataset() {
/*  72 */     return (CategoryDataset)this.dataset;
/*     */   }
/*     */   
/*     */   public void addItem(Comparable rowKey, Comparable columnKey, Number value) {
/*  83 */     this.dataset.addValue(value, rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 101 */     DefaultHandler current = getCurrentHandler();
/* 102 */     if (current != this) {
/* 103 */       current.startElement(namespaceURI, localName, qName, atts);
/* 105 */     } else if (qName.equals("CategoryDataset")) {
/* 106 */       this.dataset = new DefaultCategoryDataset();
/* 108 */     } else if (qName.equals("Series")) {
/* 109 */       CategorySeriesHandler subhandler = new CategorySeriesHandler(this);
/* 110 */       getSubHandlers().push(subhandler);
/* 111 */       subhandler.startElement(namespaceURI, localName, qName, atts);
/*     */     } else {
/* 114 */       throw new SAXException("Element not recognised: " + qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 132 */     DefaultHandler current = getCurrentHandler();
/* 133 */     if (current != this)
/* 134 */       current.endElement(namespaceURI, localName, qName); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\CategoryDatasetHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */