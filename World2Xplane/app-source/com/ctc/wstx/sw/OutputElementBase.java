/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.util.BijectiveNsMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.ri.EmptyIterator;
/*     */ 
/*     */ public abstract class OutputElementBase implements NamespaceContext {
/*     */   public static final int PREFIX_UNBOUND = 0;
/*     */   
/*     */   public static final int PREFIX_OK = 1;
/*     */   
/*     */   public static final int PREFIX_MISBOUND = 2;
/*     */   
/*     */   static final String sXmlNsPrefix = "xml";
/*     */   
/*     */   static final String sXmlNsURI = "http://www.w3.org/XML/1998/namespace";
/*     */   
/*     */   protected NamespaceContext mRootNsContext;
/*     */   
/*     */   protected String mDefaultNsURI;
/*     */   
/*     */   protected BijectiveNsMap mNsMapping;
/*     */   
/*     */   protected boolean mNsMapShared;
/*     */   
/*     */   protected OutputElementBase() {
/*  88 */     this.mNsMapping = null;
/*  89 */     this.mNsMapShared = false;
/*  90 */     this.mDefaultNsURI = "";
/*  91 */     this.mRootNsContext = null;
/*     */   }
/*     */   
/*     */   protected OutputElementBase(OutputElementBase parent, BijectiveNsMap ns) {
/*  96 */     this.mNsMapping = ns;
/*  97 */     this.mNsMapShared = (ns != null);
/*  98 */     this.mDefaultNsURI = parent.mDefaultNsURI;
/*  99 */     this.mRootNsContext = parent.mRootNsContext;
/*     */   }
/*     */   
/*     */   protected void relink(OutputElementBase parent) {
/* 107 */     this.mNsMapping = parent.mNsMapping;
/* 108 */     this.mNsMapShared = (this.mNsMapping != null);
/* 109 */     this.mDefaultNsURI = parent.mDefaultNsURI;
/* 110 */     this.mRootNsContext = parent.mRootNsContext;
/*     */   }
/*     */   
/*     */   protected abstract void setRootNsContext(NamespaceContext paramNamespaceContext);
/*     */   
/*     */   public abstract boolean isRoot();
/*     */   
/*     */   public abstract String getNameDesc();
/*     */   
/*     */   public final String getDefaultNsUri() {
/* 131 */     return this.mDefaultNsURI;
/*     */   }
/*     */   
/*     */   public final String getExplicitPrefix(String uri) {
/* 147 */     if (this.mNsMapping != null) {
/* 148 */       String prefix = this.mNsMapping.findPrefixByUri(uri);
/* 149 */       if (prefix != null)
/* 150 */         return prefix; 
/*     */     } 
/* 153 */     if (this.mRootNsContext != null) {
/* 154 */       String prefix = this.mRootNsContext.getPrefix(uri);
/* 155 */       if (prefix != null)
/* 157 */         if (prefix.length() > 0)
/* 158 */           return prefix;  
/*     */     } 
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public final int isPrefixValid(String prefix, String nsURI, boolean isElement) throws XMLStreamException {
/*     */     String act;
/* 188 */     if (nsURI == null)
/* 189 */       nsURI = ""; 
/* 197 */     if (prefix == null || prefix.length() == 0) {
/* 198 */       if (isElement) {
/* 200 */         if (nsURI == this.mDefaultNsURI || nsURI.equals(this.mDefaultNsURI))
/* 201 */           return 1; 
/* 207 */       } else if (nsURI.length() == 0) {
/* 208 */         return 1;
/*     */       } 
/* 211 */       return 2;
/*     */     } 
/* 217 */     if (prefix.equals("xml")) {
/* 220 */       if (!nsURI.equals("http://www.w3.org/XML/1998/namespace"))
/* 221 */         throwOutputError("Namespace prefix 'xml' can not be bound to non-default namespace ('" + nsURI + "'); has to be the default '" + "http://www.w3.org/XML/1998/namespace" + "'"); 
/* 225 */       return 1;
/*     */     } 
/* 231 */     if (this.mNsMapping != null) {
/* 232 */       act = this.mNsMapping.findUriByPrefix(prefix);
/*     */     } else {
/* 234 */       act = null;
/*     */     } 
/* 237 */     if (act == null && this.mRootNsContext != null)
/* 238 */       act = this.mRootNsContext.getNamespaceURI(prefix); 
/* 242 */     if (act == null)
/* 243 */       return 0; 
/* 246 */     return (act == nsURI || act.equals(nsURI)) ? 1 : 2;
/*     */   }
/*     */   
/*     */   public abstract void setDefaultNsUri(String paramString);
/*     */   
/*     */   public final String generateMapping(String prefixBase, String uri, int[] seqArr) {
/* 261 */     if (this.mNsMapping == null) {
/* 263 */       this.mNsMapping = BijectiveNsMap.createEmpty();
/* 264 */     } else if (this.mNsMapShared) {
/* 268 */       this.mNsMapping = this.mNsMapping.createChild();
/* 269 */       this.mNsMapShared = false;
/*     */     } 
/* 271 */     return this.mNsMapping.addGeneratedMapping(prefixBase, this.mRootNsContext, uri, seqArr);
/*     */   }
/*     */   
/*     */   public final void addPrefix(String prefix, String uri) {
/* 277 */     if (this.mNsMapping == null) {
/* 279 */       this.mNsMapping = BijectiveNsMap.createEmpty();
/* 280 */     } else if (this.mNsMapShared) {
/* 284 */       this.mNsMapping = this.mNsMapping.createChild();
/* 285 */       this.mNsMapShared = false;
/*     */     } 
/* 287 */     this.mNsMapping.addMapping(prefix, uri);
/*     */   }
/*     */   
/*     */   public final String getNamespaceURI(String prefix) {
/* 298 */     if (prefix.length() == 0)
/* 299 */       return this.mDefaultNsURI; 
/* 301 */     if (this.mNsMapping != null) {
/* 302 */       String uri = this.mNsMapping.findUriByPrefix(prefix);
/* 303 */       if (uri != null)
/* 304 */         return uri; 
/*     */     } 
/* 307 */     return (this.mRootNsContext != null) ? this.mRootNsContext.getNamespaceURI(prefix) : null;
/*     */   }
/*     */   
/*     */   public final String getPrefix(String uri) {
/* 313 */     if (this.mDefaultNsURI.equals(uri))
/* 314 */       return ""; 
/* 316 */     if (this.mNsMapping != null) {
/* 317 */       String prefix = this.mNsMapping.findPrefixByUri(uri);
/* 318 */       if (prefix != null)
/* 319 */         return prefix; 
/*     */     } 
/* 322 */     return (this.mRootNsContext != null) ? this.mRootNsContext.getPrefix(uri) : null;
/*     */   }
/*     */   
/*     */   public final Iterator getPrefixes(String uri) {
/* 328 */     List l = null;
/* 330 */     if (this.mDefaultNsURI.equals(uri)) {
/* 331 */       l = new ArrayList();
/* 332 */       l.add("");
/*     */     } 
/* 334 */     if (this.mNsMapping != null)
/* 335 */       l = this.mNsMapping.getPrefixesBoundToUri(uri, l); 
/* 342 */     if (this.mRootNsContext != null) {
/* 343 */       Iterator it = this.mRootNsContext.getPrefixes(uri);
/* 344 */       while (it.hasNext()) {
/* 345 */         String prefix = it.next();
/* 346 */         if (prefix.length() == 0)
/*     */           continue; 
/* 350 */         if (l == null) {
/* 351 */           l = new ArrayList();
/* 352 */         } else if (l.contains(prefix)) {
/*     */           continue;
/*     */         } 
/* 355 */         l.add(prefix);
/*     */       } 
/*     */     } 
/* 358 */     return (l == null) ? (Iterator)EmptyIterator.getInstance() : l.iterator();
/*     */   }
/*     */   
/*     */   protected final void throwOutputError(String msg) throws XMLStreamException {
/* 371 */     throw new XMLStreamException(msg);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\OutputElementBase.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */