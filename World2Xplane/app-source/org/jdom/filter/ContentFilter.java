/*     */ package org.jdom.filter;
/*     */ 
/*     */ public class ContentFilter extends AbstractFilter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: ContentFilter.java,v $ $Revision: 1.15 $ $Date: 2007/11/10 05:29:00 $ $Name:  $";
/*     */   
/*     */   public static final int ELEMENT = 1;
/*     */   
/*     */   public static final int CDATA = 2;
/*     */   
/*     */   public static final int TEXT = 4;
/*     */   
/*     */   public static final int COMMENT = 8;
/*     */   
/*     */   public static final int PI = 16;
/*     */   
/*     */   public static final int ENTITYREF = 32;
/*     */   
/*     */   public static final int DOCUMENT = 64;
/*     */   
/*     */   public static final int DOCTYPE = 128;
/*     */   
/*     */   private int filterMask;
/*     */   
/*     */   public ContentFilter() {
/* 121 */     setDefaultMask();
/*     */   }
/*     */   
/*     */   public ContentFilter(boolean allVisible) {
/* 131 */     if (allVisible) {
/* 132 */       setDefaultMask();
/*     */     } else {
/* 135 */       this.filterMask &= this.filterMask ^ 0xFFFFFFFF;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ContentFilter(int mask) {
/* 145 */     setFilterMask(mask);
/*     */   }
/*     */   
/*     */   public int getFilterMask() {
/* 154 */     return this.filterMask;
/*     */   }
/*     */   
/*     */   public void setFilterMask(int mask) {
/* 163 */     setDefaultMask();
/* 164 */     this.filterMask &= mask;
/*     */   }
/*     */   
/*     */   public void setDefaultMask() {
/* 171 */     this.filterMask = 255;
/*     */   }
/*     */   
/*     */   public void setDocumentContent() {
/* 180 */     this.filterMask = 153;
/*     */   }
/*     */   
/*     */   public void setElementContent() {
/* 188 */     this.filterMask = 63;
/*     */   }
/*     */   
/*     */   public void setElementVisible(boolean visible) {
/* 199 */     if (visible) {
/* 200 */       this.filterMask |= 0x1;
/*     */     } else {
/* 203 */       this.filterMask &= 0xFFFFFFFE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCDATAVisible(boolean visible) {
/* 214 */     if (visible) {
/* 215 */       this.filterMask |= 0x2;
/*     */     } else {
/* 218 */       this.filterMask &= 0xFFFFFFFD;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTextVisible(boolean visible) {
/* 229 */     if (visible) {
/* 230 */       this.filterMask |= 0x4;
/*     */     } else {
/* 233 */       this.filterMask &= 0xFFFFFFFB;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCommentVisible(boolean visible) {
/* 244 */     if (visible) {
/* 245 */       this.filterMask |= 0x8;
/*     */     } else {
/* 248 */       this.filterMask &= 0xFFFFFFF7;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPIVisible(boolean visible) {
/* 259 */     if (visible) {
/* 260 */       this.filterMask |= 0x10;
/*     */     } else {
/* 263 */       this.filterMask &= 0xFFFFFFEF;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setEntityRefVisible(boolean visible) {
/* 274 */     if (visible) {
/* 275 */       this.filterMask |= 0x20;
/*     */     } else {
/* 278 */       this.filterMask &= 0xFFFFFFDF;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDocTypeVisible(boolean visible) {
/* 289 */     if (visible) {
/* 290 */       this.filterMask |= 0x80;
/*     */     } else {
/* 293 */       this.filterMask &= 0xFFFFFF7F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean matches(Object obj) {
/* 305 */     if (obj instanceof org.jdom.Element)
/* 306 */       return ((this.filterMask & 0x1) != 0); 
/* 308 */     if (obj instanceof org.jdom.CDATA)
/* 309 */       return ((this.filterMask & 0x2) != 0); 
/* 311 */     if (obj instanceof org.jdom.Text)
/* 312 */       return ((this.filterMask & 0x4) != 0); 
/* 314 */     if (obj instanceof org.jdom.Comment)
/* 315 */       return ((this.filterMask & 0x8) != 0); 
/* 317 */     if (obj instanceof org.jdom.ProcessingInstruction)
/* 318 */       return ((this.filterMask & 0x10) != 0); 
/* 320 */     if (obj instanceof org.jdom.EntityRef)
/* 321 */       return ((this.filterMask & 0x20) != 0); 
/* 323 */     if (obj instanceof org.jdom.Document)
/* 324 */       return ((this.filterMask & 0x40) != 0); 
/* 326 */     if (obj instanceof org.jdom.DocType)
/* 327 */       return ((this.filterMask & 0x80) != 0); 
/* 330 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 342 */     if (this == obj)
/* 342 */       return true; 
/* 343 */     if (!(obj instanceof ContentFilter))
/* 343 */       return false; 
/* 345 */     ContentFilter filter = (ContentFilter)obj;
/* 347 */     if (this.filterMask != filter.filterMask)
/* 347 */       return false; 
/* 349 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 354 */     return this.filterMask;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\ContentFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */