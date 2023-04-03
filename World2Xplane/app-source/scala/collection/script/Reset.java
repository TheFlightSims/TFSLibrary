/*     */ package scala.collection.script;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001m4A!\001\002A\023\t)!+Z:fi*\0211\001B\001\007g\016\024\030\016\035;\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005))2#\002\001\f\037y\t\003C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB\031\001#E\n\016\003\tI!A\005\002\003\0175+7o]1hKB\021A#\006\007\001\t\0311\002\001\"b\001/\t\t\021)\005\002\0317A\021A\"G\005\0035\031\021qAT8uQ&tw\r\005\002\r9%\021QD\002\002\004\003:L\bC\001\007 \023\t\001cAA\004Qe>$Wo\031;\021\0051\021\023BA\022\007\0051\031VM]5bY&T\030M\0317f\021\025)\003\001\"\001'\003\031a\024N\\5u}Q\tq\005E\002\021\001MAq!\013\001\002\002\023\005!&\001\003d_BLXCA\026/)\005a\003c\001\t\001[A\021AC\f\003\006-!\022\ra\006\005\ba\001\t\t\021\"\0212\0035\001(o\0343vGR\004&/\0324jqV\t!\007\005\0024q5\tAG\003\0026m\005!A.\0318h\025\0059\024\001\0026bm\006L!!\017\033\003\rM#(/\0338h\021\035Y\004!!A\005\002q\nA\002\035:pIV\034G/\021:jif,\022!\020\t\003\031yJ!a\020\004\003\007%sG\017C\004B\001\005\005I\021\001\"\002\035A\024x\016Z;di\026cW-\\3oiR\0211d\021\005\b\t\002\013\t\0211\001>\003\rAH%\r\005\b\r\002\t\t\021\"\021H\003=\001(o\0343vGRLE/\032:bi>\024X#\001%\021\007%S5$D\001\005\023\tYEA\001\005Ji\026\024\030\r^8s\021\035i\005!!A\005\0029\013\001bY1o\013F,\030\r\034\013\003\037J\003\"\001\004)\n\005E3!a\002\"p_2,\027M\034\005\b\t2\013\t\0211\001\034\021\035!\006!!A\005BU\013\001\002[1tQ\016{G-\032\013\002{!9q\013AA\001\n\003B\026\001\003;p'R\024\030N\\4\025\003IBqA\027\001\002\002\023\0053,\001\004fcV\fGn\035\013\003\037rCq\001R-\002\002\003\0071dB\004_\005\005\005\t\022A0\002\013I+7/\032;\021\005A\001gaB\001\003\003\003E\t!Y\n\004A.\t\003\"B\023a\t\003\031G#A0\t\017]\003\027\021!C#1\"9a\rYA\001\n\003;\027!B1qa2LXC\0015l)\005I\007c\001\t\001UB\021Ac\033\003\006-\025\024\ra\006\005\b[\002\f\t\021\"!o\003\035)h.\0319qYf,\"a\034;\025\005=\003\bbB9m\003\003\005\rA]\001\004q\022\002\004c\001\t\001gB\021A\003\036\003\006-1\024\ra\006\005\bm\002\f\t\021\"\003x\003-\021X-\0313SKN|GN^3\025\003a\004\"aM=\n\005i$$AB(cU\026\034G\017")
/*     */ public class Reset<A> implements Message<A>, Product, Serializable {
/*     */   public <A> Reset<A> copy() {
/*  60 */     return new Reset();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  60 */     return "Reset";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  60 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  60 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  60 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  60 */     return x$1 instanceof Reset;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  60 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  60 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/*  60 */     if (x$1 instanceof Reset) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     return (bool && ((Reset)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public Reset() {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Reset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */