/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Proxy;
/*    */ import scala.math.Ordered;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$Boolean$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]3A!\001\002\003\017\tY!+[2i\005>|G.Z1o\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001aE\002\001\0211\001\"!\003\006\016\003\021I!a\003\003\003\r\005s\027PV1m!\ria\002E\007\002\005%\021qB\001\002\r\037J$WM]3e!J|\0070\037\t\003\023EI!A\005\003\003\017\t{w\016\\3b]\"AA\003\001BC\002\023\005Q#\001\003tK24W#\001\t\t\021]\001!\021!Q\001\nA\tQa]3mM\002BQ!\007\001\005\002i\ta\001P5oSRtDCA\016\035!\ti\001\001C\003\0251\001\007\001\003C\003\037\001\021Eq$A\002pe\022,\022\001\t\b\003C5r!A\t\026\017\005\rBcB\001\023(\033\005)#B\001\024\007\003\031a$o\\8u}%\tQ!\003\002*\t\005!Q.\031;i\023\tYC&\001\005Pe\022,'/\0338h\025\tIC!\003\002/_\0059!i\\8mK\006t'BA\026-\021\035\t\004!!A\005BI\n\001\002[1tQ\016{G-\032\013\002gA\021\021\002N\005\003k\021\0211!\0238u\021\0359\004!!A\005Ba\na!Z9vC2\034HC\001\t:\021\035Qd'!AA\002m\n1\001\037\0232!\tIA(\003\002>\t\t\031\021I\\=\b\017}\022\021\021!E\001\001\006Y!+[2i\005>|G.Z1o!\ti\021IB\004\002\005\005\005\t\022\001\"\024\005\005\033\005CA\005E\023\t)EA\001\004B]f\024VM\032\005\0063\005#\ta\022\013\002\001\")\021*\021C\003\025\006iqN\0353%Kb$XM\\:j_:$\"\001I&\t\0131C\005\031A\016\002\013\021\"\b.[:\t\0179\013\025\021!C\003\037\006\021\002.Y:i\007>$W\rJ3yi\026t7/[8o)\t\021\004\013C\003M\033\002\0071\004C\004S\003\006\005IQA*\002!\025\fX/\0317tI\025DH/\0328tS>tGC\001+W)\t\001R\013C\004;#\006\005\t\031A\036\t\0131\013\006\031A\016")
/*    */ public final class RichBoolean implements OrderedProxy<Object> {
/*    */   private final boolean self;
/*    */   
/*    */   public int compare(Object y) {
/* 11 */     return OrderedProxy$class.compare(this, y);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 11 */     return Proxy.class.toString((Proxy)this);
/*    */   }
/*    */   
/*    */   public boolean $less(Object that) {
/* 11 */     return Ordered.class.$less(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater(Object that) {
/* 11 */     return Ordered.class.$greater(this, that);
/*    */   }
/*    */   
/*    */   public boolean $less$eq(Object that) {
/* 11 */     return Ordered.class.$less$eq(this, that);
/*    */   }
/*    */   
/*    */   public boolean $greater$eq(Object that) {
/* 11 */     return Ordered.class.$greater$eq(this, that);
/*    */   }
/*    */   
/*    */   public int compareTo(Object that) {
/* 11 */     return Ordered.class.compareTo(this, that);
/*    */   }
/*    */   
/*    */   public boolean self() {
/* 11 */     return this.self;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 11 */     return RichBoolean$.MODULE$.hashCode$extension(self());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 11 */     return RichBoolean$.MODULE$.equals$extension(self(), x$1);
/*    */   }
/*    */   
/*    */   public RichBoolean(boolean self) {
/* 11 */     Ordered.class.$init$(this);
/* 11 */     Proxy.class.$init$((Proxy)this);
/*    */   }
/*    */   
/*    */   public Ordering$Boolean$ ord() {
/* 12 */     return RichBoolean$.MODULE$.ord$extension(self());
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(boolean paramBoolean, Object paramObject) {
/*    */     return RichBoolean$.MODULE$.equals$extension(paramBoolean, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(boolean paramBoolean) {
/*    */     return RichBoolean$.MODULE$.hashCode$extension(paramBoolean);
/*    */   }
/*    */   
/*    */   public static Ordering$Boolean$ ord$extension(boolean paramBoolean) {
/*    */     return RichBoolean$.MODULE$.ord$extension(paramBoolean);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */