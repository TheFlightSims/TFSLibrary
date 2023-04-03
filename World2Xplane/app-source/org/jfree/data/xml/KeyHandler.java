/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class KeyHandler extends DefaultHandler implements DatasetTags {
/*     */   private RootHandler rootHandler;
/*     */   
/*     */   private ItemHandler itemHandler;
/*     */   
/*     */   private StringBuffer currentText;
/*     */   
/*     */   public KeyHandler(RootHandler rootHandler, ItemHandler itemHandler) {
/*  73 */     this.rootHandler = rootHandler;
/*  74 */     this.itemHandler = itemHandler;
/*  75 */     this.currentText = new StringBuffer();
/*     */   }
/*     */   
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/*  94 */     if (qName.equals("Key")) {
/*  95 */       clearCurrentText();
/*     */     } else {
/*  98 */       throw new SAXException("Expecting <Key> but found " + qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 116 */     if (qName.equals("Key")) {
/* 117 */       this.itemHandler.setKey(getCurrentText());
/* 118 */       this.rootHandler.popSubHandler();
/* 119 */       this.rootHandler.pushSubHandler(new ValueHandler(this.rootHandler, this.itemHandler));
/*     */     } else {
/* 124 */       throw new SAXException("Expecting </Key> but found " + qName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {
/* 137 */     if (this.currentText != null)
/* 138 */       this.currentText.append(String.copyValueOf(ch, start, length)); 
/*     */   }
/*     */   
/*     */   protected String getCurrentText() {
/* 148 */     return this.currentText.toString();
/*     */   }
/*     */   
/*     */   protected void clearCurrentText() {
/* 155 */     this.currentText.delete(0, this.currentText.length());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\KeyHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */