/*     */ package org.opengis.filter.identity;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public final class Version {
/*     */   static final long UNION_MASK = 1152921504606846975L;
/*     */   
/*     */   static final long UNION_INTEGER = 1152921504606846976L;
/*     */   
/*     */   static final long UNION_DATE = 2305843009213693952L;
/*     */   
/*     */   static final long UNION_ACTION = 4611686018427387904L;
/*     */   
/*     */   final long union;
/*     */   
/*     */   public enum Action {
/*  19 */     FIRST, LAST, NEXT, PREVIOUS, ALL;
/*     */     
/*     */     public static Action lookup(int ordinal) {
/*  30 */       for (Action action : values()) {
/*  31 */         if (action.ordinal() == ordinal)
/*  32 */           return action; 
/*     */       } 
/*  35 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public Version() {
/*  61 */     this.union = 0L;
/*     */   }
/*     */   
/*     */   private Version(long union) {
/*  65 */     this.union = union;
/*     */   }
/*     */   
/*     */   public static Version valueOf(long union) {
/*  70 */     return new Version(union);
/*     */   }
/*     */   
/*     */   public Version(Action action) {
/*  74 */     if (action == null)
/*  75 */       throw new IllegalArgumentException("action can't be null"); 
/*  77 */     this.union = 0x4000000000000000L | action.ordinal();
/*     */   }
/*     */   
/*     */   public Version(Integer index) {
/*  85 */     if (index == null)
/*  86 */       throw new IllegalArgumentException("index can't be null"); 
/*  88 */     if (0 >= index.intValue())
/*  89 */       throw new IllegalArgumentException("Invalid version index: " + index + ". Must be a positive integer > 0."); 
/*  92 */     this.union = 0x1000000000000000L | index.intValue();
/*     */   }
/*     */   
/*     */   public Version(Date dateTime) {
/*  96 */     if (dateTime == null)
/*  97 */       throw new IllegalArgumentException("dateTime can't be null"); 
/*  99 */     this.union = 0x2000000000000000L | dateTime.getTime();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 103 */     return (this.union == 0L);
/*     */   }
/*     */   
/*     */   public boolean isVersionAction() {
/* 107 */     return ((0x4000000000000000L & this.union) > 0L);
/*     */   }
/*     */   
/*     */   public long union() {
/* 120 */     return this.union;
/*     */   }
/*     */   
/*     */   public Action getVersionAction() {
/* 134 */     if ((0x4000000000000000L & this.union) > 0L) {
/* 135 */       int ordinal = (int)(this.union & 0xFFFFFFFFFFFFFFFL);
/* 136 */       Action action = Action.lookup(ordinal);
/* 137 */       return action;
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isIndex() {
/* 148 */     return ((0x1000000000000000L & this.union) > 0L);
/*     */   }
/*     */   
/*     */   public Integer getIndex() {
/* 160 */     if ((0x1000000000000000L & this.union) > 0L) {
/* 161 */       int index = (int)(this.union & 0xFFFFFFFFFFFFFFFL);
/* 162 */       return Integer.valueOf(index);
/*     */     } 
/* 164 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDateTime() {
/* 168 */     return ((0x2000000000000000L & this.union) > 0L);
/*     */   }
/*     */   
/*     */   public Date getDateTime() {
/* 179 */     if ((0x2000000000000000L & this.union) > 0L) {
/* 180 */       long time = this.union & 0xFFFFFFFFFFFFFFFL;
/* 181 */       return new Date(time);
/*     */     } 
/* 183 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 188 */     if (!(o instanceof Version))
/* 189 */       return false; 
/* 191 */     Version v = (Version)o;
/* 192 */     return (this.union == v.union);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 197 */     return 17 * (int)this.union;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\identity\Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */