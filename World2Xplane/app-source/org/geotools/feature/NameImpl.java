/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.xml.namespace.QName;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class NameImpl implements Name, Serializable, Comparable<NameImpl> {
/*     */   private static final long serialVersionUID = 4564070184645559899L;
/*     */   
/*     */   protected String namespace;
/*     */   
/*     */   protected String local;
/*     */   
/*     */   private String separator;
/*     */   
/*     */   public NameImpl(String local) {
/*  69 */     this(null, local);
/*     */   }
/*     */   
/*     */   public NameImpl(String namespace, String local) {
/*  80 */     this(namespace, ":", local);
/*     */   }
/*     */   
/*     */   public NameImpl(String namespace, String separator, String local) {
/*  90 */     this.namespace = namespace;
/*  91 */     this.separator = separator;
/*  92 */     this.local = local;
/*     */   }
/*     */   
/*     */   public NameImpl(QName qName) {
/*  99 */     this(qName.getNamespaceURI(), qName.getLocalPart());
/*     */   }
/*     */   
/*     */   public boolean isGlobal() {
/* 103 */     return (getNamespaceURI() == null);
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 106 */     return this.separator;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 109 */     return this.namespace;
/*     */   }
/*     */   
/*     */   public String getLocalPart() {
/* 113 */     return this.local;
/*     */   }
/*     */   
/*     */   public String getURI() {
/* 117 */     if (this.namespace == null && this.local == null)
/* 118 */       return null; 
/* 120 */     if (this.namespace == null)
/* 121 */       return this.local; 
/* 123 */     if (this.local == null)
/* 124 */       return this.namespace; 
/* 126 */     return this.namespace + this.separator + this.local;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 134 */     return ((this.namespace == null) ? 0 : this.namespace.hashCode()) + 37 * ((this.local == null) ? 0 : this.local.hashCode());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 142 */     if (obj == this)
/* 143 */       return true; 
/* 145 */     if (obj instanceof Name) {
/* 146 */       NameImpl other = (NameImpl)obj;
/* 147 */       if (!Utilities.equals(this.namespace, other.getNamespaceURI()))
/* 148 */         return false; 
/* 150 */       if (!Utilities.equals(this.local, other.getLocalPart()))
/* 151 */         return false; 
/* 153 */       return true;
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 160 */     return getURI();
/*     */   }
/*     */   
/*     */   public int compareTo(NameImpl other) {
/* 164 */     if (other == null)
/* 165 */       return 1; 
/* 167 */     return getURI().compareTo(other.getURI());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\NameImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */