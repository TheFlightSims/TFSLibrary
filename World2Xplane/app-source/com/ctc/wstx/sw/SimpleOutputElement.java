/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import com.ctc.wstx.compat.QNameCreator;
/*     */ import com.ctc.wstx.util.BijectiveNsMap;
/*     */ import java.util.HashSet;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class SimpleOutputElement extends OutputElementBase {
/*     */   SimpleOutputElement mParent;
/*     */   
/*     */   String mPrefix;
/*     */   
/*     */   String mLocalName;
/*     */   
/*     */   String mURI;
/*     */   
/*  86 */   protected HashSet mAttrSet = null;
/*     */   
/*     */   private SimpleOutputElement() {
/* 100 */     this.mParent = null;
/* 101 */     this.mPrefix = null;
/* 102 */     this.mLocalName = "";
/* 103 */     this.mURI = null;
/*     */   }
/*     */   
/*     */   private SimpleOutputElement(SimpleOutputElement parent, String prefix, String localName, String uri, BijectiveNsMap ns) {
/* 110 */     super(parent, ns);
/* 111 */     this.mParent = parent;
/* 112 */     this.mPrefix = prefix;
/* 113 */     this.mLocalName = localName;
/* 114 */     this.mURI = uri;
/*     */   }
/*     */   
/*     */   private void relink(SimpleOutputElement parent, String prefix, String localName, String uri) {
/* 126 */     relink(parent);
/* 127 */     this.mParent = parent;
/* 128 */     this.mPrefix = prefix;
/* 129 */     this.mLocalName = localName;
/* 130 */     this.mURI = uri;
/* 131 */     this.mNsMapping = parent.mNsMapping;
/* 132 */     this.mNsMapShared = (this.mNsMapping != null);
/* 133 */     this.mDefaultNsURI = parent.mDefaultNsURI;
/* 134 */     this.mRootNsContext = parent.mRootNsContext;
/*     */   }
/*     */   
/*     */   public static SimpleOutputElement createRoot() {
/* 139 */     return new SimpleOutputElement();
/*     */   }
/*     */   
/*     */   protected SimpleOutputElement createChild(String localName) {
/* 153 */     this.mAttrSet = null;
/* 154 */     return new SimpleOutputElement(this, null, localName, this.mDefaultNsURI, this.mNsMapping);
/*     */   }
/*     */   
/*     */   protected SimpleOutputElement reuseAsChild(SimpleOutputElement parent, String localName) {
/* 164 */     this.mAttrSet = null;
/* 165 */     SimpleOutputElement poolHead = this.mParent;
/* 166 */     relink(parent, (String)null, localName, this.mDefaultNsURI);
/* 167 */     return poolHead;
/*     */   }
/*     */   
/*     */   protected SimpleOutputElement reuseAsChild(SimpleOutputElement parent, String prefix, String localName, String uri) {
/* 174 */     this.mAttrSet = null;
/* 175 */     SimpleOutputElement poolHead = this.mParent;
/* 176 */     relink(parent, prefix, localName, uri);
/* 177 */     return poolHead;
/*     */   }
/*     */   
/*     */   protected SimpleOutputElement createChild(String prefix, String localName, String uri) {
/* 191 */     this.mAttrSet = null;
/* 192 */     return new SimpleOutputElement(this, prefix, localName, uri, this.mNsMapping);
/*     */   }
/*     */   
/*     */   protected void addToPool(SimpleOutputElement poolHead) {
/* 201 */     this.mParent = poolHead;
/*     */   }
/*     */   
/*     */   public SimpleOutputElement getParent() {
/* 211 */     return this.mParent;
/*     */   }
/*     */   
/*     */   public boolean isRoot() {
/* 216 */     return (this.mParent == null);
/*     */   }
/*     */   
/*     */   public String getNameDesc() {
/* 225 */     if (this.mPrefix != null && this.mPrefix.length() > 0)
/* 226 */       return this.mPrefix + ":" + this.mLocalName; 
/* 228 */     if (this.mLocalName != null && this.mLocalName.length() > 0)
/* 229 */       return this.mLocalName; 
/* 231 */     return "#error";
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 235 */     return this.mPrefix;
/*     */   }
/*     */   
/*     */   public String getLocalName() {
/* 239 */     return this.mLocalName;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 243 */     return this.mURI;
/*     */   }
/*     */   
/*     */   public QName getName() {
/* 247 */     return QNameCreator.create(this.mURI, this.mLocalName, this.mPrefix);
/*     */   }
/*     */   
/*     */   public void checkAttrWrite(String nsURI, String localName) throws XMLStreamException {
/* 259 */     AttrName an = new AttrName(nsURI, localName);
/* 260 */     if (this.mAttrSet == null)
/* 265 */       this.mAttrSet = new HashSet(); 
/* 267 */     if (!this.mAttrSet.add(an))
/* 268 */       throw new XMLStreamException("Duplicate attribute write for attribute '" + an + "'"); 
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 279 */     this.mPrefix = prefix;
/*     */   }
/*     */   
/*     */   public void setDefaultNsUri(String uri) {
/* 283 */     this.mDefaultNsURI = uri;
/*     */   }
/*     */   
/*     */   protected final void setRootNsContext(NamespaceContext ctxt) {
/* 292 */     this.mRootNsContext = ctxt;
/* 294 */     String defURI = ctxt.getNamespaceURI("");
/* 295 */     if (defURI != null && defURI.length() > 0)
/* 296 */       this.mDefaultNsURI = defURI; 
/*     */   }
/*     */   
/*     */   static final class AttrName implements Comparable {
/*     */     final String mNsURI;
/*     */     
/*     */     final String mLocalName;
/*     */     
/*     */     final int mHashCode;
/*     */     
/*     */     public AttrName(String nsURI, String localName) {
/* 324 */       this.mNsURI = (nsURI == null) ? "" : nsURI;
/* 325 */       this.mLocalName = localName;
/* 326 */       this.mHashCode = this.mNsURI.hashCode() * 31 ^ this.mLocalName.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 330 */       if (o == this)
/* 331 */         return true; 
/* 333 */       if (!(o instanceof AttrName))
/* 334 */         return false; 
/* 336 */       AttrName other = (AttrName)o;
/* 337 */       String otherLN = other.mLocalName;
/* 339 */       if (otherLN != this.mLocalName && !otherLN.equals(this.mLocalName))
/* 340 */         return false; 
/* 342 */       String otherURI = other.mNsURI;
/* 343 */       return (otherURI == this.mNsURI || otherURI.equals(this.mNsURI));
/*     */     }
/*     */     
/*     */     public String toString() {
/* 347 */       if (this.mNsURI.length() > 0)
/* 348 */         return "{" + this.mNsURI + "} " + this.mLocalName; 
/* 350 */       return this.mLocalName;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 354 */       return this.mHashCode;
/*     */     }
/*     */     
/*     */     public int compareTo(Object o) {
/* 358 */       AttrName other = (AttrName)o;
/* 360 */       int result = this.mNsURI.compareTo(other.mNsURI);
/* 361 */       if (result == 0)
/* 362 */         result = this.mLocalName.compareTo(other.mLocalName); 
/* 364 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\SimpleOutputElement.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */