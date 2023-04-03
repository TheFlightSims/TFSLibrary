/*     */ package scala.util.matching;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ 
/*     */ public abstract class UnanchoredRegex$class {
/*     */   public static void $init$(UnanchoredRegex $this) {}
/*     */   
/*     */   public static boolean runMatcher(UnanchoredRegex $this, Matcher m) {
/* 401 */     return m.find();
/*     */   }
/*     */   
/*     */   public static UnanchoredRegex unanchored(UnanchoredRegex $this) {
/* 402 */     return $this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\matching\UnanchoredRegex$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */