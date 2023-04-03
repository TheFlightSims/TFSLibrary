/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ import java.io.Writer;
/*    */ import java.util.Iterator;
/*    */ import javax.xml.stream.XMLStreamWriter;
/*    */ import org.codehaus.stax2.ri.EmptyIterator;
/*    */ 
/*    */ public final class EmptyNamespaceContext extends BaseNsContext {
/* 23 */   static final EmptyNamespaceContext sInstance = new EmptyNamespaceContext();
/*    */   
/*    */   public static EmptyNamespaceContext getInstance() {
/* 27 */     return sInstance;
/*    */   }
/*    */   
/*    */   public Iterator getNamespaces() {
/* 36 */     return (Iterator)EmptyIterator.getInstance();
/*    */   }
/*    */   
/*    */   public void outputNamespaceDeclarations(Writer w) {}
/*    */   
/*    */   public void outputNamespaceDeclarations(XMLStreamWriter w) {}
/*    */   
/*    */   public String doGetNamespaceURI(String prefix) {
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public String doGetPrefix(String nsURI) {
/* 65 */     return null;
/*    */   }
/*    */   
/*    */   public Iterator doGetPrefixes(String nsURI) {
/* 69 */     return (Iterator)EmptyIterator.getInstance();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\EmptyNamespaceContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */