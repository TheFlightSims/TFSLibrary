/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import org.codehaus.stax2.ri.SingletonIterator;
/*     */ 
/*     */ public abstract class BaseNsContext implements NamespaceContext {
/*     */   protected static final String UNDECLARED_NS_URI = "";
/*     */   
/*     */   public final String getNamespaceURI(String prefix) {
/*  55 */     if (prefix == null)
/*  56 */       throw new IllegalArgumentException(ErrorConsts.ERR_NULL_ARG); 
/*  58 */     if (prefix.length() > 0) {
/*  59 */       if (prefix.equals("xml"))
/*  60 */         return "http://www.w3.org/XML/1998/namespace"; 
/*  62 */       if (prefix.equals("xmlns"))
/*  63 */         return "http://www.w3.org/2000/xmlns/"; 
/*     */     } 
/*  66 */     return doGetNamespaceURI(prefix);
/*     */   }
/*     */   
/*     */   public final String getPrefix(String nsURI) {
/*  74 */     if (nsURI == null || nsURI.length() == 0)
/*  75 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/*  77 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/*  78 */       return "xml"; 
/*  80 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/*  81 */       return "xmlns"; 
/*  83 */     return doGetPrefix(nsURI);
/*     */   }
/*     */   
/*     */   public final Iterator getPrefixes(String nsURI) {
/*  91 */     if (nsURI == null || nsURI.length() == 0)
/*  92 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/*  94 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/*  95 */       return (Iterator)new SingletonIterator("xml"); 
/*  97 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/*  98 */       return (Iterator)new SingletonIterator("xmlns"); 
/* 101 */     return doGetPrefixes(nsURI);
/*     */   }
/*     */   
/*     */   public abstract Iterator getNamespaces();
/*     */   
/*     */   public abstract void outputNamespaceDeclarations(Writer paramWriter) throws IOException;
/*     */   
/*     */   public abstract void outputNamespaceDeclarations(XMLStreamWriter paramXMLStreamWriter) throws XMLStreamException;
/*     */   
/*     */   public abstract String doGetNamespaceURI(String paramString);
/*     */   
/*     */   public abstract String doGetPrefix(String paramString);
/*     */   
/*     */   public abstract Iterator doGetPrefixes(String paramString);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\BaseNsContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */