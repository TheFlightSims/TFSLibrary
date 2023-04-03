/*     */ package org.jfree.data.xml;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class RootHandler extends DefaultHandler implements DatasetTags {
/*  62 */   private Stack subHandlers = new Stack();
/*     */   
/*     */   public Stack getSubHandlers() {
/*  71 */     return this.subHandlers;
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  85 */     DefaultHandler handler = getCurrentHandler();
/*  86 */     if (handler != this)
/*  87 */       handler.characters(ch, start, length); 
/*     */   }
/*     */   
/*     */   public DefaultHandler getCurrentHandler() {
/*  97 */     DefaultHandler result = this;
/*  98 */     if (this.subHandlers != null && 
/*  99 */       this.subHandlers.size() > 0) {
/* 100 */       Object top = this.subHandlers.peek();
/* 101 */       if (top != null)
/* 102 */         result = (DefaultHandler)top; 
/*     */     } 
/* 106 */     return result;
/*     */   }
/*     */   
/*     */   public void pushSubHandler(DefaultHandler subhandler) {
/* 115 */     this.subHandlers.push(subhandler);
/*     */   }
/*     */   
/*     */   public DefaultHandler popSubHandler() {
/* 124 */     return this.subHandlers.pop();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xml\RootHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */