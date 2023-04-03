/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ 
/*     */ public class MergedNsContext implements NamespaceContext {
/*     */   final NamespaceContext mParentCtxt;
/*     */   
/*     */   final List mNamespaces;
/*     */   
/*     */   protected MergedNsContext(NamespaceContext parentCtxt, List localNs) {
/*  26 */     this.mParentCtxt = parentCtxt;
/*  27 */     this.mNamespaces = (localNs == null) ? Collections.EMPTY_LIST : localNs;
/*     */   }
/*     */   
/*     */   public static MergedNsContext construct(NamespaceContext parentCtxt, List localNs) {
/*  33 */     return new MergedNsContext(parentCtxt, localNs);
/*     */   }
/*     */   
/*     */   public String getNamespaceURI(String prefix) {
/*  44 */     if (prefix == null)
/*  45 */       throw new IllegalArgumentException("Illegal to pass null prefix"); 
/*  47 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/*  48 */       Namespace ns = this.mNamespaces.get(i);
/*  49 */       if (prefix.equals(ns.getPrefix()))
/*  50 */         return ns.getNamespaceURI(); 
/*     */     } 
/*  54 */     if (this.mParentCtxt != null) {
/*  55 */       String uri = this.mParentCtxt.getNamespaceURI(prefix);
/*  56 */       if (uri != null)
/*  57 */         return uri; 
/*     */     } 
/*  60 */     if (prefix.equals("xml"))
/*  61 */       return "http://www.w3.org/XML/1998/namespace"; 
/*  63 */     if (prefix.equals("xmlns"))
/*  64 */       return "http://www.w3.org/2000/xmlns/"; 
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public String getPrefix(String nsURI) {
/*  71 */     if (nsURI == null || nsURI.length() == 0)
/*  72 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/*  77 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/*  78 */       Namespace ns = this.mNamespaces.get(i);
/*  79 */       if (nsURI.equals(ns.getNamespaceURI()))
/*  80 */         return ns.getPrefix(); 
/*     */     } 
/*  84 */     if (this.mParentCtxt != null) {
/*  85 */       String prefix = this.mParentCtxt.getPrefix(nsURI);
/*  86 */       if (prefix != null) {
/*  88 */         String uri2 = getNamespaceURI(prefix);
/*  89 */         if (uri2.equals(nsURI))
/*  91 */           return prefix; 
/*     */       } 
/*  96 */       Iterator it = this.mParentCtxt.getPrefixes(nsURI);
/*  97 */       while (it.hasNext()) {
/*  98 */         String p2 = it.next();
/*  99 */         if (!p2.equals(prefix)) {
/* 101 */           String uri2 = getNamespaceURI(p2);
/* 102 */           if (uri2.equals(nsURI))
/* 104 */             return p2; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 111 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/* 112 */       return "xml"; 
/* 114 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/* 115 */       return "xmlns"; 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public Iterator getPrefixes(String nsURI) {
/* 124 */     if (nsURI == null || nsURI.length() == 0)
/* 125 */       throw new IllegalArgumentException("Illegal to pass null/empty prefix as argument."); 
/* 129 */     ArrayList l = null;
/* 130 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/* 131 */       Namespace ns = this.mNamespaces.get(i);
/* 132 */       if (nsURI.equals(ns.getNamespaceURI()))
/* 133 */         l = addToList(l, ns.getPrefix()); 
/*     */     } 
/* 138 */     if (this.mParentCtxt != null) {
/* 139 */       Iterator it = this.mParentCtxt.getPrefixes(nsURI);
/* 140 */       while (it.hasNext()) {
/* 141 */         String p2 = it.next();
/* 143 */         String uri2 = getNamespaceURI(p2);
/* 144 */         if (uri2.equals(nsURI))
/* 146 */           l = addToList(l, p2); 
/*     */       } 
/*     */     } 
/* 152 */     if (nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/* 153 */       l = addToList(l, "xml"); 
/* 155 */     if (nsURI.equals("http://www.w3.org/2000/xmlns/"))
/* 156 */       l = addToList(l, "xmlns"); 
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   protected ArrayList addToList(ArrayList l, String value) {
/* 170 */     if (l == null)
/* 171 */       l = new ArrayList(); 
/* 173 */     l.add(value);
/* 174 */     return l;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\MergedNsContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */