/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%s!B\001\003\021\0039\021AC've6,(\017S1tQ*\0211\001B\001\be>,H/\0338h\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\006NkJlWO\035%bg\"\034\"!\003\007\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g\021\025\031\022\002\"\001\025\003\031a\024N\\5u}Q\tq\001C\004\027\023\t\007IQB\f\002\031YL7/\0332mK6\013w-[2\026\003a\001\"!D\r\n\005iq!aA%oi\"1A$\003Q\001\016a\tQB^5tS\ndW-T1hS\016\004\003b\002\020\n\005\004%iaF\001\rQ&$G-\0328NC\036L7-\021\005\007A%\001\013Q\002\r\002\033!LG\rZ3o\033\006<\027nY!!\021\035\021\023B1A\005\016]\tA\002[5eI\026tW*Y4jG\nCa\001J\005!\002\033A\022!\0045jI\022,g.T1hS\016\024\005\005C\004'\023\t\007IQB\f\002\031YL7/\0332mK6K\0070\032:\t\r!J\001\025!\004\031\00351\030n]5cY\026l\025\016_3sA!9!&\003b\001\n\0339\022\001\0045jI\022,g.T5yKJ\f\005B\002\027\nA\0035\001$A\007iS\022$WM\\'jq\026\024\030\t\t\005\b]%\021\r\021\"\004\030\0031A\027\016\0323f]6K\0070\032:C\021\031\001\024\002)A\0071\005i\001.\0333eK:l\025\016_3s\005\002BqAM\005C\002\0235q#A\006gS:\fG.T5yKJ\f\004B\002\033\nA\0035\001$\001\007gS:\fG.T5yKJ\f\004\005C\0047\023\t\007IQB\f\002\027\031Lg.\0317NSb,'O\r\005\007q%\001\013Q\002\r\002\031\031Lg.\0317NSb,'O\r\021\t\017iJ!\031!C\007/\005Q1/Z3e'R\024\030N\\4\t\rqJ\001\025!\004\031\003-\031X-\0323TiJLgn\032\021\t\017yJ!\031!C\007/\005I1/Z3e\003J\024\030-\037\005\007\001&\001\013Q\002\r\002\025M,W\rZ!se\006L\b\005C\004C\023\t\007I\021B\"\002\031M$xN]3e\033\006<\027nY!\026\003\021\0032!D#\031\023\t1eBA\003BeJ\f\027\020\003\004I\023\001\006I\001R\001\016gR|'/\0323NC\036L7-\021\021\t\017)K!\031!C\005\007\006a1\017^8sK\022l\025mZ5d\005\"1A*\003Q\001\n\021\013Qb\035;pe\026$W*Y4jG\n\003\003\"\002(\n\t\003y\025!C:uCJ$\b*Y:i)\tA\002\013C\003R\033\002\007\001$\001\003tK\026$\007\"B*\n\t\0039\022aC:uCJ$X*Y4jG\006CQ!V\005\005\002]\t1b\035;beRl\025mZ5d\005\")q+\003C\0011\006QQ\r\037;f]\022D\025m\0355\025\013aI6,X0\t\013i3\006\031\001\r\002\t!\f7\017\033\005\0069Z\003\r\001G\001\006m\006dW/\032\005\006=Z\003\r\001G\001\007[\006<\027nY!\t\013\0014\006\031\001\r\002\r5\fw-[2C\021\025\021\027\002\"\001d\003)qW\r\037;NC\036L7-\021\013\0031\021DQAX1A\002aAQAZ\005\005\002\035\f!B\\3yi6\013w-[2C)\tA\002\016C\003aK\002\007\001\004C\003k\023\021\0051.\001\007gS:\fG.\033>f\021\006\034\b\016\006\002\031Y\")!,\033a\0011!)a.\003C\001_\006I\021M\035:bs\"\0137\017[\013\003aZ$\"\001G9\t\013Il\007\031A:\002\003\005\0042!D#u!\t)h\017\004\001\005\023]l\007\025!A\001\006\004A(!\001+\022\005ed\bCA\007{\023\tYhBA\004O_RD\027N\\4\021\0055i\030B\001@\017\005\r\te.\037\025\004m\006\005\001cA\007\002\004%\031\021Q\001\b\003\027M\004XmY5bY&TX\r\032\005\b\003\023IA\021AA\006\003)\031HO]5oO\"\0137\017\033\013\0041\0055\001\002CA\b\003\017\001\r!!\005\002\003M\004B!a\005\002\0329\031Q\"!\006\n\007\005]a\"\001\004Qe\026$WMZ\005\005\0037\tiB\001\004TiJLgn\032\006\004\003/q\001bBA\021\023\021\005\0211E\001\016gflW.\032;sS\016D\025m\0355\026\t\005\025\022Q\t\013\0061\005\035\022q\t\005\t\003S\ty\0021\001\002,\005\021\001p\035\t\007\003[\ti$a\021\017\t\005=\022\021\b\b\005\003c\t9$\004\002\0024)\031\021Q\007\004\002\rq\022xn\034;?\023\005y\021bAA\036\035\0059\001/Y2lC\036,\027\002BA \003\003\022q\002\026:bm\026\0248/\0312mK>s7-\032\006\004\003wq\001cA;\002F\0211q/a\bC\002aDa!UA\020\001\004A\002")
/*     */ public final class MurmurHash {
/*     */   public static <T> int symmetricHash(TraversableOnce<T> paramTraversableOnce, int paramInt) {
/*     */     return MurmurHash$.MODULE$.symmetricHash(paramTraversableOnce, paramInt);
/*     */   }
/*     */   
/*     */   public static int stringHash(String paramString) {
/*     */     return MurmurHash$.MODULE$.stringHash(paramString);
/*     */   }
/*     */   
/*     */   public static <T> int arrayHash(Object paramObject) {
/*     */     return MurmurHash$.MODULE$.arrayHash(paramObject);
/*     */   }
/*     */   
/*     */   public static int finalizeHash(int paramInt) {
/*     */     return MurmurHash$.MODULE$.finalizeHash(paramInt);
/*     */   }
/*     */   
/*     */   public static int nextMagicB(int paramInt) {
/*     */     return MurmurHash$.MODULE$.nextMagicB(paramInt);
/*     */   }
/*     */   
/*     */   public static int nextMagicA(int paramInt) {
/*     */     return MurmurHash$.MODULE$.nextMagicA(paramInt);
/*     */   }
/*     */   
/*     */   public static int extendHash(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*     */     return MurmurHash$.MODULE$.extendHash(paramInt1, paramInt2, paramInt3, paramInt4);
/*     */   }
/*     */   
/*     */   public static int startMagicB() {
/*     */     return MurmurHash$.MODULE$.startMagicB();
/*     */   }
/*     */   
/*     */   public static int startMagicA() {
/*     */     return MurmurHash$.MODULE$.startMagicA();
/*     */   }
/*     */   
/*     */   public static int startHash(int paramInt) {
/*     */     return MurmurHash$.MODULE$.startHash(paramInt);
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicA) {
/*  53 */       return apply$mcII$sp(magicA);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicA) {
/*  53 */       return MurmurHash$.MODULE$.nextMagicA(magicA);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction1.mcII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int magicB) {
/*  57 */       return apply$mcII$sp(magicB);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int magicB) {
/*  57 */       return MurmurHash$.MODULE$.nextMagicB(magicB);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MurmurHash$$anonfun$symmetricHash$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef a$1;
/*     */     
/*     */     private final IntRef b$1;
/*     */     
/*     */     private final IntRef n$1;
/*     */     
/*     */     private final IntRef c$1;
/*     */     
/*     */     public MurmurHash$$anonfun$symmetricHash$1(IntRef a$1, IntRef b$1, IntRef n$1, IntRef c$1) {}
/*     */     
/*     */     public final void apply(Object i) {
/* 137 */       int h = ScalaRunTime$.MODULE$.hash(i);
/* 138 */       this.a$1.elem += h;
/* 139 */       this.b$1.elem ^= h;
/* 140 */       if (h != 0)
/* 140 */         this.c$1.elem *= h; 
/* 141 */       this.n$1.elem++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\MurmurHash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */