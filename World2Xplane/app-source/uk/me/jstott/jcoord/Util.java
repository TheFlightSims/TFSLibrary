/*     */ package uk.me.jstott.jcoord;
/*     */ 
/*     */ class Util {
/*     */   protected static double sinSquared(double x) {
/*  37 */     return Math.sin(x) * Math.sin(x);
/*     */   }
/*     */   
/*     */   protected static double sinCubed(double x) {
/*  50 */     return sinSquared(x) * Math.sin(x);
/*     */   }
/*     */   
/*     */   protected static double cosSquared(double x) {
/*  63 */     return Math.cos(x) * Math.cos(x);
/*     */   }
/*     */   
/*     */   protected static double cosCubed(double x) {
/*  76 */     return cosSquared(x) * Math.cos(x);
/*     */   }
/*     */   
/*     */   protected static double tanSquared(double x) {
/*  89 */     return Math.tan(x) * Math.tan(x);
/*     */   }
/*     */   
/*     */   protected static double sec(double x) {
/* 102 */     return 1.0D / Math.cos(x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\Util.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */