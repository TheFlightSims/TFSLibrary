/*     */ package scala.util.grammar;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=b\001B\001\003\001&\021qaQ8ogJC5K\003\002\004\t\0059qM]1n[\006\024(BA\003\007\003\021)H/\0337\013\003\035\tQa]2bY\006\034\001a\005\003\001\0259\021\002CA\006\r\033\005\021\021BA\007\003\005!AU\rZ4f%\"\033\006CA\b\021\033\0051\021BA\t\007\005\035\001&o\0343vGR\004\"aD\n\n\005Q1!\001D*fe&\fG.\033>bE2,\007\002\003\f\001\005+\007I\021A\f\002\007QtG/F\001\031!\ty\021$\003\002\033\r\t\031\021J\034;\t\021q\001!\021#Q\001\na\tA\001\0368uA!Aa\004\001BK\002\023\005q#A\002i]RD\001\002\t\001\003\022\003\006I\001G\001\005Q:$\b\005C\003#\001\021\0051%\001\004=S:LGO\020\013\004I\0252\003CA\006\001\021\0251\022\0051\001\031\021\025q\022\0051\001\031\021\035A\003!!A\005\002%\nAaY8qsR\031AEK\026\t\017Y9\003\023!a\0011!9ad\nI\001\002\004A\002bB\027\001#\003%\tAL\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005y#F\001\r1W\005\t\004C\001\0328\033\005\031$B\001\0336\003%)hn\0315fG.,GM\003\0027\r\005Q\021M\0348pi\006$\030n\0348\n\005a\032$!E;oG\",7m[3e-\006\024\030.\0318dK\"9!\bAI\001\n\003q\023AD2paf$C-\0324bk2$HE\r\005\by\001\t\t\021\"\021>\0035\001(o\0343vGR\004&/\0324jqV\ta\b\005\002@\t6\t\001I\003\002B\005\006!A.\0318h\025\005\031\025\001\0026bm\006L!!\022!\003\rM#(/\0338h\021\0359\005!!A\005\002]\tA\002\035:pIV\034G/\021:jifDq!\023\001\002\002\023\005!*\001\bqe>$Wo\031;FY\026lWM\034;\025\005-s\005CA\bM\023\tieAA\002B]fDqa\024%\002\002\003\007\001$A\002yIEBq!\025\001\002\002\023\005#+A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005\031\006c\001+X\0276\tQK\003\002W\r\005Q1m\0347mK\016$\030n\0348\n\005a+&\001C%uKJ\fGo\034:\t\017i\003\021\021!C\0017\006A1-\0318FcV\fG\016\006\002]?B\021q\"X\005\003=\032\021qAQ8pY\026\fg\016C\004P3\006\005\t\031A&\t\017\005\004\021\021!C!E\006A\001.Y:i\007>$W\rF\001\031\021\035!\007!!A\005B\025\f\001\002^8TiJLgn\032\013\002}!9q\rAA\001\n\003B\027AB3rk\006d7\017\006\002]S\"9qJZA\001\002\004Y\005\006\002\001l]B\004\"a\0047\n\00554!A\0033faJ,7-\031;fI\006\nq.\001\016UQ&\034\be\0317bgN\004s/\0337mA\t,\007E]3n_Z,G-I\001r\003\031\021d&\r\031/a\03591OAA\001\022\003!\030aB\"p]N\024\006j\025\t\003\027U4q!\001\002\002\002#\005aoE\002voJ\001R\001_>\0311\021j\021!\037\006\003u\032\tqA];oi&lW-\003\002}s\n\t\022IY:ue\006\034GOR;oGRLwN\034\032\t\013\t*H\021\001@\025\003QDq\001Z;\002\002\023\025S\rC\005\002\004U\f\t\021\"!\002\006\005)\021\r\0359msR)A%a\002\002\n!1a#!\001A\002aAaAHA\001\001\004A\002\"CA\007k\006\005I\021QA\b\003\035)h.\0319qYf$B!!\005\002\036A)q\"a\005\002\030%\031\021Q\003\004\003\r=\003H/[8o!\025y\021\021\004\r\031\023\r\tYB\002\002\007)V\004H.\032\032\t\023\005}\0211BA\001\002\004!\023a\001=%a!I\0211E;\002\002\023%\021QE\001\fe\026\fGMU3t_24X\r\006\002\002(A\031q(!\013\n\007\005-\002I\001\004PE*,7\r\036\025\005k.t\007\017")
/*     */ public class ConsRHS extends HedgeRHS implements Product, Serializable {
/*     */   private final int tnt;
/*     */   
/*     */   private final int hnt;
/*     */   
/*     */   public static Function1<Tuple2<Object, Object>, ConsRHS> tupled() {
/*     */     return ConsRHS$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<Object, ConsRHS>> curried() {
/*     */     return ConsRHS$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public int tnt() {
/*  18 */     return this.tnt;
/*     */   }
/*     */   
/*     */   public int hnt() {
/*  18 */     return this.hnt;
/*     */   }
/*     */   
/*     */   public ConsRHS copy(int tnt, int hnt) {
/*  18 */     return new ConsRHS(tnt, hnt);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*  18 */     return tnt();
/*     */   }
/*     */   
/*     */   public int copy$default$2() {
/*  18 */     return hnt();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  18 */     return "ConsRHS";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  18 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  18 */     switch (x$1) {
/*     */       default:
/*  18 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  18 */     return BoxesRunTime.boxToInteger(tnt());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  18 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  18 */     return x$1 instanceof ConsRHS;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  18 */     return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, tnt()), hnt()), 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  18 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*  18 */     if (this != x$1) {
/*     */       boolean bool;
/*  18 */       if (x$1 instanceof ConsRHS) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         ConsRHS consRHS = (ConsRHS)x$1;
/*     */         if ((tnt() == consRHS.tnt() && hnt() == consRHS.hnt() && consRHS.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ConsRHS(int tnt, int hnt) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\ConsRHS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */