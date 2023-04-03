/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class ItemHandler extends DefaultHandler implements DatasetTags {
/*     */   private RootHandler root;
/*     */   
/*     */   private DefaultHandler parent;
/*     */   
/*     */   private Comparable key;
/*     */   
/*     */   private Number value;
/*     */   
/*     */   public ItemHandler(RootHandler root, DefaultHandler parent) {
/*  73 */     this.root = root;
/*  74 */     this.parent = parent;
/*  75 */     this.key = null;
/*  76 */     this.value = null;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/*  85 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(Comparable key) {
/*  94 */     this.key = key;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/* 103 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Number value) {
/* 112 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 130 */     if (qName.equals("Item")) {
/* 131 */       KeyHandler subhandler = new KeyHandler(this.root, this);
/* 132 */       this.root.pushSubHandler(subhandler);
/* 134 */     } else if (qName.equals("Value")) {
/* 135 */       ValueHandler subhandler = new ValueHandler(this.root, this);
/* 136 */       this.root.pushSubHandler(subhandler);
/*     */     } else {
/* 139 */       throw new SAXException("Expected <Item> or <Value>...found " + qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) {
/* 157 */     if (this.parent instanceof PieDatasetHandler) {
/* 158 */       PieDatasetHandler handler = (PieDatasetHandler)this.parent;
/* 159 */       handler.addItem(this.key, this.value);
/* 160 */       this.root.popSubHandler();
/* 162 */     } else if (this.parent instanceof CategorySeriesHandler) {
/* 163 */       CategorySeriesHandler handler = (CategorySeriesHandler)this.parent;
/* 164 */       handler.addItem(this.key, this.value);
/* 165 */       this.root.popSubHandler();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\ItemHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */