/*    */ package org.codehaus.stax2.ri;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import javax.xml.namespace.NamespaceContext;
/*    */ 
/*    */ public class EmptyNamespaceContext implements NamespaceContext {
/* 16 */   static final EmptyNamespaceContext sInstance = new EmptyNamespaceContext();
/*    */   
/*    */   public static EmptyNamespaceContext getInstance() {
/* 20 */     return sInstance;
/*    */   }
/*    */   
/*    */   public final String getNamespaceURI(String prefix) {
/* 33 */     if (prefix == null)
/* 34 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/* 36 */     if (prefix.length() > 0) {
/* 37 */       if (prefix.equals("xml"))
/* 38 */         return "http://www.w3.org/XML/1998/namespace"; 
/* 40 */       if (prefix.equals("xmlns"))
/* 41 */         return "http://www.w3.org/2000/xmlns/"; 
/*    */     } 
/* 44 */     return null;
/*    */   }
/*    */   
/*    */   public final String getPrefix(String nsURI) {
/* 52 */     if (nsURI == null || nsURI.length() == 0)
/* 53 */       throw new IllegalArgumentException("Illegal to pass null/empty URI as argument."); 
/* 55 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/* 56 */       return "xml"; 
/* 58 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/* 59 */       return "xmlns"; 
/* 61 */     return null;
/*    */   }
/*    */   
/*    */   public final Iterator getPrefixes(String nsURI) {
/* 69 */     if (nsURI == null || nsURI.length() == 0)
/* 70 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/* 72 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/* 73 */       return new SingletonIterator("xml"); 
/* 75 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/* 76 */       return new SingletonIterator("xmlns"); 
/* 79 */     return EmptyIterator.getInstance();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\EmptyNamespaceContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */