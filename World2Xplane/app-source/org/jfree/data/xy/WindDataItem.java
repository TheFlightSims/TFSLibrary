/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ class WindDataItem implements Comparable {
/*     */   private Number x;
/*     */   
/*     */   private Number windDir;
/*     */   
/*     */   private Number windForce;
/*     */   
/*     */   public WindDataItem(Number x, Number windDir, Number windForce) {
/* 285 */     this.x = x;
/* 286 */     this.windDir = windDir;
/* 287 */     this.windForce = windForce;
/*     */   }
/*     */   
/*     */   public Number getX() {
/* 296 */     return this.x;
/*     */   }
/*     */   
/*     */   public Number getWindDirection() {
/* 305 */     return this.windDir;
/*     */   }
/*     */   
/*     */   public Number getWindForce() {
/* 314 */     return this.windForce;
/*     */   }
/*     */   
/*     */   public int compareTo(Object object) {
/* 325 */     if (object instanceof WindDataItem) {
/* 326 */       WindDataItem item = (WindDataItem)object;
/* 327 */       if (this.x.doubleValue() > item.x.doubleValue())
/* 328 */         return 1; 
/* 330 */       if (this.x.equals(item.x))
/* 331 */         return 0; 
/* 334 */       return -1;
/*     */     } 
/* 338 */     throw new ClassCastException("WindDataItem.compareTo(error)");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\WindDataItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */