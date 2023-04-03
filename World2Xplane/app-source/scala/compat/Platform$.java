/*     */ package scala.compat;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public final class Platform$ {
/*     */   public static final Platform$ MODULE$;
/*     */   
/*     */   private final String EOL;
/*     */   
/*     */   private Platform$() {
/*  15 */     MODULE$ = this;
/* 112 */     this.EOL = scala.util.Properties$.MODULE$.lineSeparator();
/*     */   }
/*     */   
/*     */   public void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
/*     */     System.arraycopy(src, srcPos, dest, destPos, length);
/*     */   }
/*     */   
/*     */   public Object createArray(Class<?> elemClass, int length) {
/*     */     return Array.newInstance(elemClass, length);
/*     */   }
/*     */   
/*     */   public void arrayclear(int[] arr) {
/*     */     Arrays.fill(arr, 0);
/*     */   }
/*     */   
/*     */   public Class<?> getClassForName(String name) {
/*     */     return Class.forName(name);
/*     */   }
/*     */   
/*     */   public String EOL() {
/* 112 */     return this.EOL;
/*     */   }
/*     */   
/*     */   public long currentTime() {
/* 121 */     return System.currentTimeMillis();
/*     */   }
/*     */   
/*     */   public void collectGarbage() {
/* 130 */     System.gc();
/*     */   }
/*     */   
/*     */   public String defaultCharsetName() {
/* 134 */     return Charset.defaultCharset().name();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\compat\Platform$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */