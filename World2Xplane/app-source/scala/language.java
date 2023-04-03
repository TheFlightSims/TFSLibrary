/*     */ package scala;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001a;Q!\001\002\t\002\025\t\001\002\\1oOV\fw-\032\006\002\007\005)1oY1mC\016\001\001C\001\004\b\033\005\021a!\002\005\003\021\003I!\001\0037b]\036,\030mZ3\024\005\035Q\001C\001\004\f\023\ta!A\001\004B]f\024VM\032\005\006\035\035!\taD\001\007y%t\027\016\036 \025\003\025A\001\"E\004\t\006\004%\031AE\001\tIft\027-\\5dgV\t1\003\005\002\025/9\021a!F\005\003-\t\tq\002\\1oOV\fw-\032$fCR,(/Z\005\0031e\021\001\002Z=oC6L7m\035\006\003-\tA\001bG\004\t\002\003\006KaE\001\nIft\027-\\5dg\002B\001\"H\004\t\006\004%\031AH\001\013a>\034HOZ5y\037B\034X#A\020\021\005Q\001\023BA\021\032\005)\001xn\035;gSb|\005o\035\005\tG\035A\t\021)Q\005?\005Y\001o\\:uM&Dx\n]:!\021!)s\001#b\001\n\0071\023a\004:fM2,7\r^5wK\016\013G\016\\:\026\003\035\002\"\001\006\025\n\005%J\"a\004:fM2,7\r^5wK\016\013G\016\\:\t\021-:\001\022!Q!\n\035\n\001C]3gY\026\034G/\033<f\007\006dGn\035\021\t\0215:\001R1A\005\0049\n1#[7qY&\034\027\016^\"p]Z,'o]5p]N,\022a\f\t\003)AJ!!M\r\003'%l\007\017\\5dSR\034uN\034<feNLwN\\:\t\021M:\001\022!Q!\n=\nA#[7qY&\034\027\016^\"p]Z,'o]5p]N\004\003\002C\033\b\021\013\007I1\001\034\002\027!Lw\r[3s\027&tGm]\013\002oA\021A\003O\005\003se\0211\002[5hQ\026\0248*\0338eg\"A1h\002E\001B\003&q'\001\007iS\036DWM]&j]\022\034\b\005\003\005>\017!\025\r\021b\001?\0031)\0070[:uK:$\030.\0317t+\005y\004C\001\013A\023\t\t\025D\001\007fq&\034H/\0328uS\006d7\017\003\005D\017!\005\t\025)\003@\0035)\0070[:uK:$\030.\0317tA\035)Qi\002E\001\r\006aQ\r\0379fe&lWM\034;bYB\021q\tS\007\002\017\031)\021j\002E\001\025\naQ\r\0379fe&lWM\034;bYN\021\001J\003\005\006\035!#\t\001\024\013\002\r\"Aa\n\023EC\002\023\rq*\001\004nC\016\024xn]\013\002!B\021\021k\025\b\003)IK!!R\r\n\005Q+&AB7bGJ|7O\003\002F3!Aq\013\023E\001B\003&\001+A\004nC\016\024xn\035\021")
/*     */ public final class language {
/*     */   public static languageFeature.existentials existentials() {
/*     */     return language$.MODULE$.existentials();
/*     */   }
/*     */   
/*     */   public static languageFeature.higherKinds higherKinds() {
/*     */     return language$.MODULE$.higherKinds();
/*     */   }
/*     */   
/*     */   public static languageFeature.implicitConversions implicitConversions() {
/*     */     return language$.MODULE$.implicitConversions();
/*     */   }
/*     */   
/*     */   public static languageFeature.reflectiveCalls reflectiveCalls() {
/*     */     return language$.MODULE$.reflectiveCalls();
/*     */   }
/*     */   
/*     */   public static languageFeature.postfixOps postfixOps() {
/*     */     return language$.MODULE$.postfixOps();
/*     */   }
/*     */   
/*     */   public static languageFeature.dynamics dynamics() {
/*     */     return language$.MODULE$.dynamics();
/*     */   }
/*     */   
/*     */   public static class experimental$ {
/*     */     public static final experimental$ MODULE$;
/*     */     
/*     */     private languageFeature.experimental$.acros macros;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public experimental$() {
/* 156 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public languageFeature.experimental$.acros macros() {
/* 171 */       return this.bitmap$0 ? this.macros : macros$lzycompute();
/*     */     }
/*     */     
/*     */     private languageFeature.experimental$.acros macros$lzycompute() {
/* 171 */       synchronized (this) {
/* 171 */         if (!this.bitmap$0) {
/* 171 */           this.macros = languageFeature.experimental$.acros$.MODULE$;
/* 171 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/language}.Lscala/language$experimental$;}} */
/* 171 */         return this.macros;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\language.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */