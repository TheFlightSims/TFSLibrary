/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import com.ctc.wstx.cfg.ErrorConsts;
/*     */ import javax.xml.stream.Location;
/*     */ 
/*     */ public final class ElementId {
/*     */   private boolean mDefined;
/*     */   
/*     */   private final String mIdValue;
/*     */   
/*     */   private Location mLocation;
/*     */   
/*     */   private PrefixedName mElemName;
/*     */   
/*     */   private PrefixedName mAttrName;
/*     */   
/*     */   private ElementId mNextUndefined;
/*     */   
/*     */   private ElementId mNextColl;
/*     */   
/*     */   ElementId(String id, Location loc, boolean defined, PrefixedName elemName, PrefixedName attrName) {
/*  82 */     this.mIdValue = id;
/*  83 */     this.mLocation = loc;
/*  84 */     this.mDefined = defined;
/*  85 */     this.mElemName = elemName;
/*  86 */     this.mAttrName = attrName;
/*     */   }
/*     */   
/*     */   protected void linkUndefined(ElementId undefined) {
/*  91 */     if (this.mNextUndefined != null)
/*  92 */       throw new IllegalStateException("ElementId '" + this + "' already had net undefined set ('" + this.mNextUndefined + "')"); 
/*  94 */     this.mNextUndefined = undefined;
/*     */   }
/*     */   
/*     */   protected void setNextColliding(ElementId nextColl) {
/* 100 */     this.mNextColl = nextColl;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 109 */     return this.mIdValue;
/*     */   }
/*     */   
/*     */   public Location getLocation() {
/* 110 */     return this.mLocation;
/*     */   }
/*     */   
/*     */   public PrefixedName getElemName() {
/* 111 */     return this.mElemName;
/*     */   }
/*     */   
/*     */   public PrefixedName getAttrName() {
/* 112 */     return this.mAttrName;
/*     */   }
/*     */   
/*     */   public boolean isDefined() {
/* 114 */     return this.mDefined;
/*     */   }
/*     */   
/*     */   public boolean idMatches(char[] buf, int start, int len) {
/* 118 */     if (this.mIdValue.length() != len)
/* 119 */       return false; 
/* 122 */     if (buf[start] != this.mIdValue.charAt(0))
/* 123 */       return false; 
/* 125 */     int i = 1;
/* 126 */     len += start;
/* 127 */     while (++start < len) {
/* 128 */       if (buf[start] != this.mIdValue.charAt(i))
/* 129 */         return false; 
/* 131 */       i++;
/*     */     } 
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public boolean idMatches(String idStr) {
/* 138 */     return this.mIdValue.equals(idStr);
/*     */   }
/*     */   
/*     */   public ElementId nextUndefined() {
/* 141 */     return this.mNextUndefined;
/*     */   }
/*     */   
/*     */   public ElementId nextColliding() {
/* 142 */     return this.mNextColl;
/*     */   }
/*     */   
/*     */   public void markDefined(Location defLoc) {
/* 145 */     if (this.mDefined)
/* 146 */       throw new IllegalStateException(ErrorConsts.ERR_INTERNAL); 
/* 148 */     this.mDefined = true;
/* 149 */     this.mLocation = defLoc;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 159 */     return this.mIdValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\ElementId.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */