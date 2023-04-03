/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ public final class PrefixedName implements Comparable {
/*     */   private String mPrefix;
/*     */   
/*     */   private String mLocalName;
/*     */   
/*  42 */   volatile int mHash = 0;
/*     */   
/*     */   public PrefixedName(String prefix, String localName) {
/*  52 */     this.mLocalName = localName;
/*  53 */     this.mPrefix = (prefix != null && prefix.length() == 0) ? null : prefix;
/*     */   }
/*     */   
/*     */   public PrefixedName reset(String prefix, String localName) {
/*  59 */     this.mLocalName = localName;
/*  60 */     this.mPrefix = (prefix != null && prefix.length() == 0) ? null : prefix;
/*  62 */     this.mHash = 0;
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public static PrefixedName valueOf(QName n) {
/*  68 */     return new PrefixedName(n.getPrefix(), n.getLocalPart());
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/*  77 */     return this.mPrefix;
/*     */   }
/*     */   
/*     */   public String getLocalName() {
/*  79 */     return this.mLocalName;
/*     */   }
/*     */   
/*     */   public boolean isaNsDeclaration() {
/*  87 */     if (this.mPrefix == null)
/*  88 */       return (this.mLocalName == "xmlns"); 
/*  90 */     return (this.mPrefix == "xmlns");
/*     */   }
/*     */   
/*     */   public boolean isXmlReservedAttr(boolean nsAware, String localName) {
/* 102 */     if (nsAware) {
/* 103 */       if ("xml" == this.mPrefix)
/* 104 */         return (this.mLocalName == localName); 
/* 107 */     } else if (this.mLocalName.length() == 4 + localName.length()) {
/* 108 */       return (this.mLocalName.startsWith("xml:") && this.mLocalName.endsWith(localName));
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     if (this.mPrefix == null || this.mPrefix.length() == 0)
/* 124 */       return this.mLocalName; 
/* 126 */     StringBuffer sb = new StringBuffer(this.mPrefix.length() + 1 + this.mLocalName.length());
/* 127 */     sb.append(this.mPrefix);
/* 128 */     sb.append(':');
/* 129 */     sb.append(this.mLocalName);
/* 130 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 135 */     if (o == this)
/* 136 */       return true; 
/* 138 */     if (!(o instanceof PrefixedName))
/* 139 */       return false; 
/* 141 */     PrefixedName other = (PrefixedName)o;
/* 143 */     if (this.mLocalName != other.mLocalName)
/* 144 */       return false; 
/* 146 */     return (this.mPrefix == other.mPrefix);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 150 */     int hash = this.mHash;
/* 152 */     if (hash == 0) {
/* 153 */       hash = this.mLocalName.hashCode();
/* 154 */       if (this.mPrefix != null)
/* 155 */         hash ^= this.mPrefix.hashCode(); 
/* 157 */       this.mHash = hash;
/*     */     } 
/* 159 */     return hash;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 164 */     PrefixedName other = (PrefixedName)o;
/* 167 */     String op = other.mPrefix;
/* 170 */     if (op == null || op.length() == 0) {
/* 171 */       if (this.mPrefix != null && this.mPrefix.length() > 0)
/* 172 */         return 1; 
/*     */     } else {
/* 174 */       if (this.mPrefix == null || this.mPrefix.length() == 0)
/* 175 */         return -1; 
/* 177 */       int result = this.mPrefix.compareTo(op);
/* 178 */       if (result != 0)
/* 179 */         return result; 
/*     */     } 
/* 183 */     return this.mLocalName.compareTo(other.mLocalName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\PrefixedName.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */