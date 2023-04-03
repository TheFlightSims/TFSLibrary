/*     */ package akka.util;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.Comparator;
/*     */ import java.util.regex.Pattern;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Helpers$ {
/*     */   public static final Helpers$ MODULE$;
/*     */   
/*     */   private final boolean isWindows;
/*     */   
/*     */   private final String base64chars;
/*     */   
/*     */   private Helpers$() {
/*  14 */     MODULE$ = this;
/*  16 */     this.isWindows = (System.getProperty("os.name", "").toLowerCase().indexOf("win") >= 0);
/*     */   }
/*     */   
/*     */   public boolean isWindows() {
/*  16 */     return this.isWindows;
/*     */   }
/*     */   
/*     */   public Pattern makePattern(String s) {
/*  18 */     return Pattern.compile((new StringBuilder()).append("^\\Q").append(s.replace("?", "\\E.\\Q").replace("*", "\\E.*\\Q")).append("\\E$").toString());
/*     */   }
/*     */   
/*     */   public int compareIdentityHash(Object a, Object b) {
/*  26 */     long diff = (System.identityHashCode(a) & 0xFFFFFFFFL) - (System.identityHashCode(b) & 0xFFFFFFFFL);
/*  27 */     return (diff > 0L) ? 1 : ((diff < 0L) ? -1 : 0);
/*     */   }
/*     */   
/*     */   public <T> Comparator<T> identityHashComparator(Comparator comp) {
/*  37 */     return new Helpers$$anon$1(comp);
/*     */   }
/*     */   
/*     */   public static class Helpers$$anon$1 implements Comparator<T> {
/*     */     private final Comparator comp$1;
/*     */     
/*     */     public Helpers$$anon$1(Comparator comp$1) {}
/*     */     
/*     */     public int compare(Object a, Object b) {
/*  38 */       int i = Helpers$.MODULE$.compareIdentityHash(a, b);
/*  38 */       switch (i) {
/*     */         case 0:
/*  39 */           if (BoxesRunTime.equals(a, b));
/*     */           break;
/*     */         default:
/*     */         
/*     */       } 
/*  39 */       return this.comp$1.compare(a, b);
/*     */     }
/*     */   }
/*     */   
/*     */   public String currentTimeMillisToUTCString(long timestamp) {
/*  54 */     long timeOfDay = timestamp % 86400000L;
/*  55 */     long hours = timeOfDay / 3600000L;
/*  56 */     long minutes = timeOfDay / 60000L % 60L;
/*  57 */     long seconds = timeOfDay / 1000L % 60L;
/*  58 */     long ms = timeOfDay % 1000L;
/*  59 */     long arg$1 = hours, arg$2 = minutes, arg$3 = seconds, arg$4 = ms;
/*  59 */     return (new StringOps(scala.Predef$.MODULE$.augmentString("%02d:%02d:%02d.%03dUTC"))).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToLong(arg$1), BoxesRunTime.boxToLong(arg$2), BoxesRunTime.boxToLong(arg$3), BoxesRunTime.boxToLong(arg$4) }));
/*     */   }
/*     */   
/*     */   public final String base64chars() {
/*  62 */     return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+~";
/*     */   }
/*     */   
/*     */   public StringBuilder base64$default$2() {
/*  65 */     return new StringBuilder("$");
/*     */   }
/*     */   
/*     */   public String base64(long l, StringBuilder sb) {
/*     */     while (true) {
/*  66 */       sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+~".charAt((int)l & 0x3F));
/*  67 */       long next = l >>> 6L;
/*  68 */       if (next == 0L)
/*  68 */         return sb.toString(); 
/*  69 */       sb = sb;
/*  69 */       l = next;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A> A Requiring(Object value) {
/*  90 */     return (A)value;
/*     */   }
/*     */   
/*     */   public Config ConfigOps(Config config) {
/* 119 */     return config;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Helpers$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */