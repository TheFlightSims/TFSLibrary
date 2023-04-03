/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import java.net.URI;
/*     */ 
/*     */ public final class DTDId {
/*     */   protected final String mPublicId;
/*     */   
/*     */   protected final URI mSystemId;
/*     */   
/*     */   protected final int mConfigFlags;
/*     */   
/*     */   protected final boolean mXml11;
/*     */   
/*  43 */   protected int mHashCode = 0;
/*     */   
/*     */   private DTDId(String publicId, URI systemId, int configFlags, boolean xml11) {
/*  53 */     this.mPublicId = publicId;
/*  54 */     this.mSystemId = systemId;
/*  55 */     this.mConfigFlags = configFlags;
/*  56 */     this.mXml11 = xml11;
/*     */   }
/*     */   
/*     */   public static DTDId constructFromPublicId(String publicId, int configFlags, boolean xml11) {
/*  62 */     if (publicId == null || publicId.length() == 0)
/*  63 */       throw new IllegalArgumentException("Empty/null public id."); 
/*  65 */     return new DTDId(publicId, null, configFlags, xml11);
/*     */   }
/*     */   
/*     */   public static DTDId constructFromSystemId(URI systemId, int configFlags, boolean xml11) {
/*  71 */     if (systemId == null)
/*  72 */       throw new IllegalArgumentException("Null system id."); 
/*  74 */     return new DTDId(null, systemId, configFlags, xml11);
/*     */   }
/*     */   
/*     */   public static DTDId construct(String publicId, URI systemId, int configFlags, boolean xml11) {
/*  79 */     if (publicId != null && publicId.length() > 0)
/*  80 */       return new DTDId(publicId, null, configFlags, xml11); 
/*  82 */     if (systemId == null)
/*  83 */       throw new IllegalArgumentException("Illegal arguments; both public and system id null/empty."); 
/*  85 */     return new DTDId(null, systemId, configFlags, xml11);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  95 */     int hash = this.mHashCode;
/*  96 */     if (hash == 0) {
/*  97 */       hash = this.mConfigFlags;
/*  98 */       if (this.mPublicId != null) {
/*  99 */         hash ^= this.mPublicId.hashCode();
/*     */       } else {
/* 101 */         hash ^= this.mSystemId.hashCode();
/*     */       } 
/* 103 */       if (this.mXml11)
/* 104 */         hash ^= 0x1; 
/* 106 */       this.mHashCode = hash;
/*     */     } 
/* 108 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 112 */     StringBuffer sb = new StringBuffer(60);
/* 113 */     sb.append("Public-id: ");
/* 114 */     sb.append(this.mPublicId);
/* 115 */     sb.append(", system-id: ");
/* 116 */     sb.append(this.mSystemId);
/* 117 */     sb.append(" [config flags: 0x");
/* 118 */     sb.append(Integer.toHexString(this.mConfigFlags));
/* 119 */     sb.append("], xml11: ");
/* 120 */     sb.append(this.mXml11);
/* 121 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 126 */     if (o == this)
/* 126 */       return true; 
/* 127 */     if (o == null || o.getClass() != getClass())
/* 127 */       return false; 
/* 128 */     DTDId other = (DTDId)o;
/* 129 */     if (other.mConfigFlags != this.mConfigFlags || other.mXml11 != this.mXml11)
/* 131 */       return false; 
/* 133 */     if (this.mPublicId != null) {
/* 134 */       String op = other.mPublicId;
/* 135 */       return (op != null && op.equals(this.mPublicId));
/*     */     } 
/* 137 */     return this.mSystemId.equals(other.mSystemId);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDId.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */