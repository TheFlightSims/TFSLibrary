/*     */ package scala.sys.process;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005MrAB\001\003\021\003\021\001\"A\bqe>\034Wm]:J]R,'O\\1m\025\t\031A!A\004qe>\034Wm]:\013\005\0251\021aA:zg*\tq!A\003tG\006d\027\r\005\002\n\0255\t!A\002\004\f\005!\005!\001\004\002\020aJ|7-Z:t\023:$XM\0358bYN\021!\"\004\t\003\035=i\021AB\005\003!\031\021a!\0218z%\0264\007\"\002\n\013\t\003!\022A\002\037j]&$hh\001\001\025\003!AqA\006\006C\002\023\025q#\001\007qe>\034Wm]:EK\n,x-F\001\031!\tq\021$\003\002\033\r\t9!i\\8mK\006t\007B\002\017\013A\0035\001$A\007qe>\034Wm]:EK\n,x\rI\003\005=)\001qDA\t%KF$\023/\\1sW\022:'/Z1uKJ,2\001I\0230!\021q\021e\t\030\n\005\t2!a\004)beRL\027\r\034$v]\016$\030n\0348\021\005\021*C\002\001\003\007MuA)\031A\024\003\003\005\013\"\001K\026\021\0059I\023B\001\026\007\005\035qu\016\0365j]\036\004\"A\004\027\n\00552!aA!osB\021Ae\f\003\007au!)\031A\024\003\003\t+AA\r\006\001g\tI1\t\\8tK\006\024G.\032\t\003iej\021!\016\006\003m]\n!![8\013\003a\nAA[1wC&\021!'N\003\005w)\001AH\001\003GS2,\007C\001\033>\023\tYT'\002\003@\025\001\001%aC%P\013b\034W\r\035;j_:\004\"\001N!\n\005}*T\001B\"\013\001\021\0231\"\0238qkR\034FO]3b[B\021A'R\005\003\007V*Aa\022\006\001\021\nA!\n\025:pG\026\0348\017\005\002J\0316\t!J\003\002Lo\005!A.\0318h\023\ti%JA\004Qe>\034Wm]:\006\t=S\001\001\025\002\020\025B\023xnY3tg\n+\030\016\0343feB\021\021*U\005\003%*\023a\002\025:pG\026\0348OQ;jY\022,'/\002\003U\025\001)&\001D(viB,Ho\025;sK\006l\007C\001\033W\023\t!V'\002\003Y\025\001I&aB*z]\0164\026M]\013\0035\002\0042a\0270`\033\005a&BA/\007\003)\031wN\\2veJ,g\016^\005\0031r\003\"\001\n1\005\013\005<&\031A\024\003\003Q+Aa\031\006\001I\n\031QK\025'\021\005\025DW\"\0014\013\005\035<\024a\0018fi&\0211M\032\005\006U*!\ta[\001\f_:Le\016^3seV\004H/\006\002myR\021Q. \t\005]vy70D\001\013!\t\001\bP\004\002rm:\021!/^\007\002g*\021AoE\001\007yI|w\016\036 \n\003\035I!a\036\004\002\017A\f7m[1hK&\021\021P\037\002\n)\"\024xn^1cY\026T!a\036\004\021\005\021bH!B1j\005\0049\003B\002@j\t\003\007q0A\004iC:$G.\032:\021\t9\t\ta_\005\004\003\0071!\001\003\037cs:\fW.\032 \t\017\005\035!\002\"\001\002\n\005I\021n\034$bS2,(/Z\013\005\003\027\t\t\002\006\003\002\016\005M\001#\0028\036_\006=\001c\001\023\002\022\0211\021-!\002C\002\035BqA`A\003\001\004\t)\002E\004\017\003/\tY\"a\004\n\007\005eaAA\005Gk:\034G/[8ocA\021aN\020\005\b\003?QA\021AA\021\003\r!'m\032\013\005\003G\tI\003E\002\017\003KI1!a\n\007\005\021)f.\033;\t\021\005-\022Q\004a\001\003[\tA!\\:hgB!a\"a\f,\023\r\t\tD\002\002\013yI,\007/Z1uK\022t\004")
/*     */ public final class processInternal {
/*     */   public static void dbg(Seq<Object> paramSeq) {
/*     */     processInternal$.MODULE$.dbg(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> PartialFunction<Throwable, T> ioFailure(Function1<IOException, T> paramFunction1) {
/*     */     return processInternal$.MODULE$.ioFailure(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> PartialFunction<Throwable, T> onInterrupt(Function0<T> paramFunction0) {
/*     */     return processInternal$.MODULE$.onInterrupt(paramFunction0);
/*     */   }
/*     */   
/*     */   public static boolean processDebug() {
/*     */     return processInternal$.MODULE$.processDebug();
/*     */   }
/*     */   
/*     */   public static class processInternal$$anonfun$onInterrupt$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 handler$1;
/*     */     
/*     */     public processInternal$$anonfun$onInterrupt$1(Function0 handler$1) {}
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x1) {
/*     */       boolean bool;
/* 241 */       if (x1 instanceof InterruptedException) {
/* 241 */         bool = true;
/*     */       } else {
/* 241 */         bool = false;
/*     */       } 
/* 241 */       return bool;
/*     */     }
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */       Object object;
/* 241 */       if (x1 instanceof InterruptedException) {
/* 241 */         object = this.handler$1.apply();
/*     */       } else {
/* 241 */         object = default.apply(x1);
/*     */       } 
/* 241 */       return (B1)object;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class processInternal$$anonfun$ioFailure$1 extends AbstractPartialFunction<Throwable, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 handler$2;
/*     */     
/*     */     public processInternal$$anonfun$ioFailure$1(Function1 handler$2) {}
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       boolean bool;
/* 245 */       if (x2 instanceof IOException) {
/* 245 */         bool = true;
/*     */       } else {
/* 245 */         bool = false;
/*     */       } 
/* 245 */       return bool;
/*     */     }
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/*     */       Object object;
/* 245 */       if (x2 instanceof IOException) {
/* 245 */         IOException iOException = (IOException)x2;
/* 245 */         object = this.handler$2.apply(iOException);
/*     */       } else {
/* 245 */         object = default.apply(x2);
/*     */       } 
/* 245 */       return (B1)object;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\process\processInternal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */