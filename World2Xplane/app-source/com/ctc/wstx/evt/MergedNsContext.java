/*     */ package com.ctc.wstx.evt;
/*     */ 
/*     */ import com.ctc.wstx.util.BaseNsContext;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamWriter;
/*     */ import javax.xml.stream.events.Namespace;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ 
/*     */ public class MergedNsContext extends BaseNsContext {
/*     */   final NamespaceContext mParentCtxt;
/*     */   
/*     */   final List mNamespaces;
/*     */   
/*  40 */   Map mNsByPrefix = null;
/*     */   
/*  42 */   Map mNsByURI = null;
/*     */   
/*     */   protected MergedNsContext(NamespaceContext parentCtxt, List localNs) {
/*  46 */     this.mParentCtxt = parentCtxt;
/*  47 */     this.mNamespaces = (localNs == null) ? Collections.EMPTY_LIST : localNs;
/*     */   }
/*     */   
/*     */   public static BaseNsContext construct(NamespaceContext parentCtxt, List localNs) {
/*  53 */     return new MergedNsContext(parentCtxt, localNs);
/*     */   }
/*     */   
/*     */   public String doGetNamespaceURI(String prefix) {
/*  65 */     if (this.mNsByPrefix == null)
/*  66 */       this.mNsByPrefix = buildByPrefixMap(); 
/*  68 */     Namespace ns = (Namespace)this.mNsByPrefix.get(prefix);
/*  69 */     if (ns == null && this.mParentCtxt != null)
/*  70 */       return this.mParentCtxt.getNamespaceURI(prefix); 
/*  72 */     return (ns == null) ? null : ns.getNamespaceURI();
/*     */   }
/*     */   
/*     */   public String doGetPrefix(String nsURI) {
/*  78 */     if (this.mNsByURI == null)
/*  79 */       this.mNsByURI = buildByNsURIMap(); 
/*  81 */     Namespace ns = (Namespace)this.mNsByURI.get(nsURI);
/*  82 */     if (ns == null && this.mParentCtxt != null)
/*  83 */       return this.mParentCtxt.getPrefix(nsURI); 
/*  85 */     return (ns == null) ? null : ns.getPrefix();
/*     */   }
/*     */   
/*     */   public Iterator doGetPrefixes(String nsURI) {
/*  91 */     ArrayList l = null;
/*  93 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/*  94 */       Namespace ns = this.mNamespaces.get(i);
/*  95 */       String uri = ns.getNamespaceURI();
/*  96 */       if (uri == null)
/*  97 */         uri = ""; 
/*  99 */       if (uri.equals(nsURI)) {
/* 100 */         if (l == null)
/* 101 */           l = new ArrayList(); 
/* 103 */         String prefix = ns.getPrefix();
/* 104 */         l.add((prefix == null) ? "" : prefix);
/*     */       } 
/*     */     } 
/* 108 */     if (this.mParentCtxt != null) {
/* 109 */       Iterator it = this.mParentCtxt.getPrefixes(nsURI);
/* 110 */       if (l == null)
/* 111 */         return it; 
/* 113 */       while (it.hasNext())
/* 114 */         l.add(it.next()); 
/*     */     } 
/* 118 */     return (l == null) ? (Iterator)EmptyIterator.getInstance() : l.iterator();
/*     */   }
/*     */   
/*     */   public Iterator getNamespaces() {
/* 133 */     return this.mNamespaces.iterator();
/*     */   }
/*     */   
/*     */   public void outputNamespaceDeclarations(Writer w) throws IOException {
/* 138 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/* 139 */       Namespace ns = this.mNamespaces.get(i);
/* 140 */       w.write(32);
/* 141 */       w.write("xmlns");
/* 142 */       if (!ns.isDefaultNamespaceDeclaration()) {
/* 143 */         w.write(58);
/* 144 */         w.write(ns.getPrefix());
/*     */       } 
/* 146 */       w.write("=\"");
/* 147 */       w.write(ns.getNamespaceURI());
/* 148 */       w.write(34);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void outputNamespaceDeclarations(XMLStreamWriter w) throws XMLStreamException {
/* 159 */     for (int i = 0, len = this.mNamespaces.size(); i < len; i++) {
/* 160 */       Namespace ns = this.mNamespaces.get(i);
/* 161 */       if (ns.isDefaultNamespaceDeclaration()) {
/* 162 */         w.writeDefaultNamespace(ns.getNamespaceURI());
/*     */       } else {
/* 164 */         w.writeNamespace(ns.getPrefix(), ns.getNamespaceURI());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Map buildByPrefixMap() {
/* 177 */     int len = this.mNamespaces.size();
/* 178 */     if (len == 0)
/* 179 */       return Collections.EMPTY_MAP; 
/* 182 */     LinkedHashMap m = new LinkedHashMap(1 + len + (len >> 1));
/* 183 */     for (int i = 0; i < len; i++) {
/* 184 */       Namespace ns = this.mNamespaces.get(i);
/* 185 */       String prefix = ns.getPrefix();
/* 186 */       if (prefix == null)
/* 187 */         prefix = ""; 
/* 189 */       m.put(prefix, ns);
/*     */     } 
/* 191 */     return m;
/*     */   }
/*     */   
/*     */   private Map buildByNsURIMap() {
/* 196 */     int len = this.mNamespaces.size();
/* 197 */     if (len == 0)
/* 198 */       return Collections.EMPTY_MAP; 
/* 201 */     LinkedHashMap m = new LinkedHashMap(1 + len + (len >> 1));
/* 202 */     for (int i = 0; i < len; i++) {
/* 203 */       Namespace ns = this.mNamespaces.get(i);
/* 204 */       String uri = ns.getNamespaceURI();
/* 205 */       if (uri == null)
/* 206 */         uri = ""; 
/* 208 */       m.put(uri, ns);
/*     */     } 
/* 210 */     return m;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\MergedNsContext.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */