/*     */ package com.ctc.wstx.sr;
/*     */ 
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ import org.codehaus.stax2.ri.SingletonIterator;
/*     */ import org.codehaus.stax2.ri.evt.NamespaceEventImpl;
/*     */ 
/*     */ public final class CompactNsContext extends BaseNsContext {
/*     */   final Location mLocation;
/*     */   
/*     */   final String[] mNamespaces;
/*     */   
/*     */   final int mNsLength;
/*     */   
/*     */   final int mFirstLocalNs;
/*     */   
/*     */   transient ArrayList mNsList;
/*     */   
/*     */   public CompactNsContext(Location loc, String defaultNsURI, String[] namespaces, int nsLen, int firstLocal) {
/*  65 */     this.mLocation = loc;
/*  66 */     this.mNamespaces = namespaces;
/*  67 */     this.mNsLength = nsLen;
/*  68 */     this.mFirstLocalNs = firstLocal;
/*     */   }
/*     */   
/*     */   public String doGetNamespaceURI(String prefix) {
/*  82 */     String[] ns = this.mNamespaces;
/*  83 */     if (prefix.length() == 0) {
/*  84 */       for (int j = this.mNsLength - 2; j >= 0; j -= 2) {
/*  85 */         if (ns[j] == null)
/*  86 */           return ns[j + 1]; 
/*     */       } 
/*  89 */       return null;
/*     */     } 
/*  91 */     for (int i = this.mNsLength - 2; i >= 0; i -= 2) {
/*  92 */       if (prefix.equals(ns[i]))
/*  93 */         return ns[i + 1]; 
/*     */     } 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public String doGetPrefix(String nsURI) {
/* 103 */     String[] ns = this.mNamespaces;
/* 104 */     int len = this.mNsLength;
/*     */     int i;
/* 107 */     label20: for (i = len - 1; i > 0; i -= 2) {
/* 108 */       if (nsURI.equals(ns[i])) {
/* 114 */         String prefix = ns[i - 1];
/* 115 */         for (int j = i + 1; j < len; j += 2) {
/* 117 */           if (ns[j] == prefix)
/*     */             continue label20; 
/*     */         } 
/* 121 */         String uri = ns[i - 1];
/* 126 */         return (uri == null) ? "" : uri;
/*     */       } 
/*     */     } 
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public Iterator doGetPrefixes(String nsURI) {
/* 136 */     String[] ns = this.mNamespaces;
/* 137 */     int len = this.mNsLength;
/* 138 */     String first = null;
/* 139 */     ArrayList all = null;
/*     */     int i;
/* 142 */     label29: for (i = len - 1; i > 0; i -= 2) {
/* 143 */       String currNS = ns[i];
/* 144 */       if (currNS == nsURI || currNS.equals(nsURI)) {
/* 148 */         String prefix = ns[i - 1];
/* 149 */         for (int j = i + 1; j < len; j += 2) {
/* 151 */           if (ns[j] == prefix)
/*     */             continue label29; 
/*     */         } 
/* 159 */         if (prefix == null)
/* 160 */           prefix = ""; 
/* 162 */         if (first == null) {
/* 163 */           first = prefix;
/*     */         } else {
/* 165 */           if (all == null) {
/* 166 */             all = new ArrayList();
/* 167 */             all.add(first);
/*     */           } 
/* 169 */           all.add(prefix);
/*     */         } 
/*     */       } 
/*     */     } 
/* 173 */     if (all != null)
/* 174 */       return all.iterator(); 
/* 176 */     if (first != null)
/* 177 */       return (Iterator)new SingletonIterator(first); 
/* 179 */     return (Iterator)EmptyIterator.getInstance();
/*     */   }
/*     */   
/*     */   public Iterator getNamespaces() {
/* 190 */     if (this.mNsList == null) {
/* 191 */       int firstLocal = this.mFirstLocalNs;
/* 192 */       int len = this.mNsLength - firstLocal;
/* 193 */       if (len == 0)
/* 194 */         return (Iterator)EmptyIterator.getInstance(); 
/* 196 */       if (len == 2)
/* 197 */         return (Iterator)new SingletonIterator(NamespaceEventImpl.constructNamespace(this.mLocation, this.mNamespaces[firstLocal], this.mNamespaces[firstLocal + 1])); 
/* 202 */       ArrayList l = new ArrayList(len >> 1);
/* 203 */       String[] ns = this.mNamespaces;
/* 204 */       for (len = this.mNsLength; firstLocal < len; 
/* 205 */         firstLocal += 2)
/* 206 */         l.add(NamespaceEventImpl.constructNamespace(this.mLocation, ns[firstLocal], ns[firstLocal + 1])); 
/* 209 */       this.mNsList = l;
/*     */     } 
/* 211 */     return this.mNsList.iterator();
/*     */   }
/*     */   
/*     */   public void outputNamespaceDeclarations(Writer w) throws IOException {
/* 222 */     String[] ns = this.mNamespaces;
/* 223 */     for (int i = this.mFirstLocalNs, len = this.mNsLength; i < len; i += 2) {
/* 224 */       w.write(32);
/* 225 */       w.write("xmlns");
/* 226 */       String prefix = ns[i];
/* 227 */       if (prefix != null && prefix.length() > 0) {
/* 228 */         w.write(58);
/* 229 */         w.write(prefix);
/*     */       } 
/* 231 */       w.write("=\"");
/* 232 */       w.write(ns[i + 1]);
/* 233 */       w.write(34);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void outputNamespaceDeclarations(XMLStreamWriter w) throws XMLStreamException {
/* 239 */     String[] ns = this.mNamespaces;
/* 240 */     for (int i = this.mFirstLocalNs, len = this.mNsLength; i < len; i += 2) {
/* 241 */       String nsURI = ns[i + 1];
/* 242 */       String prefix = ns[i];
/* 243 */       if (prefix != null && prefix.length() > 0) {
/* 244 */         w.writeNamespace(prefix, nsURI);
/*     */       } else {
/* 246 */         w.writeDefaultNamespace(nsURI);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sr\CompactNsContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */