/*     */ package akka.util;
/*     */ 
/*     */ import java.security.MessageDigest;
/*     */ import java.security.SecureRandom;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Crypt$ {
/*     */   public static final Crypt$ MODULE$;
/*     */   
/*     */   private final String hex;
/*     */   
/*     */   private final String lineSeparator;
/*     */   
/*     */   private SecureRandom random;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private Crypt$() {
/*   9 */     MODULE$ = this;
/*  10 */     this.hex = "0123456789ABCDEF";
/*  11 */     this.lineSeparator = System.getProperty("line.separator");
/*     */   }
/*     */   
/*     */   public String hex() {
/*     */     return this.hex;
/*     */   }
/*     */   
/*     */   public String lineSeparator() {
/*  11 */     return this.lineSeparator;
/*     */   }
/*     */   
/*     */   private SecureRandom random$lzycompute() {
/*  13 */     synchronized (this) {
/*  13 */       if (!this.bitmap$0) {
/*  13 */         this.random = SecureRandom.getInstance("SHA1PRNG");
/*  13 */         this.bitmap$0 = true;
/*     */       } 
/*  13 */       return this.random;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SecureRandom random() {
/*  13 */     return this.bitmap$0 ? this.random : random$lzycompute();
/*     */   }
/*     */   
/*     */   public String md5(String text) {
/*  15 */     return md5(unifyLineSeparator(text).getBytes("ASCII"));
/*     */   }
/*     */   
/*     */   public String md5(byte[] bytes) {
/*  17 */     return digest(bytes, MessageDigest.getInstance("MD5"));
/*     */   }
/*     */   
/*     */   public String sha1(String text) {
/*  19 */     return sha1(unifyLineSeparator(text).getBytes("ASCII"));
/*     */   }
/*     */   
/*     */   public String sha1(byte[] bytes) {
/*  21 */     return digest(bytes, MessageDigest.getInstance("SHA1"));
/*     */   }
/*     */   
/*     */   public String generateSecureCookie() {
/*  24 */     byte[] bytes = (byte[])scala.Array$.MODULE$.fill(32, (Function0)new Crypt$$anonfun$1(), scala.reflect.ClassTag$.MODULE$.Byte());
/*  25 */     random().nextBytes(bytes);
/*  26 */     return sha1(bytes);
/*     */   }
/*     */   
/*     */   public static class Crypt$$anonfun$1 extends AbstractFunction0.mcB.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte apply() {
/*     */       return apply$mcB$sp();
/*     */     }
/*     */     
/*     */     public byte apply$mcB$sp() {
/*     */       return scala.Predef$.MODULE$.int2Integer(0).byteValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public String digest(byte[] bytes, MessageDigest md) {
/*  30 */     md.update(bytes);
/*  31 */     return hexify(md.digest());
/*     */   }
/*     */   
/*     */   public String hexify(byte[] bytes) {
/*  35 */     StringBuilder builder = new StringBuilder(bytes.length * 2);
/*  36 */     scala.Predef$.MODULE$.byteArrayOps(bytes).foreach((Function1)new Crypt$$anonfun$hexify$1(builder));
/*  37 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public static class Crypt$$anonfun$hexify$1 extends AbstractFunction1<Object, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder builder$1;
/*     */     
/*     */     public final StringBuilder apply(byte byte) {
/*     */       return this.builder$1.append(Crypt$.MODULE$.hex().charAt((byte & 0xF0) >> 4)).append(Crypt$.MODULE$.hex().charAt(byte & 0xF));
/*     */     }
/*     */     
/*     */     public Crypt$$anonfun$hexify$1(StringBuilder builder$1) {}
/*     */   }
/*     */   
/*     */   private String unifyLineSeparator(String text) {
/*  40 */     return text.replaceAll(lineSeparator(), "\n");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Crypt$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */