/*    */ package scala.math;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\raaB\001\003!\003\r\na\002\002\006\013F,\030N\036\006\003\007\021\tA!\\1uQ*\tQ!A\003tG\006d\027m\001\001\026\005!Q2c\001\001\n\033A\021!bC\007\002\t%\021A\002\002\002\004\003:L\bC\001\006\017\023\tyAA\001\007TKJL\027\r\\5{C\ndW\rC\003\022\001\031\005!#A\003fcVLg\017F\002\024-\001\002\"A\003\013\n\005U!!a\002\"p_2,\027M\034\005\006/A\001\r\001G\001\002qB\021\021D\007\007\001\t\025Y\002A1\001\035\005\005!\026CA\017\n!\tQa$\003\002 \t\t9aj\034;iS:<\007\"B\021\021\001\004A\022!A=\b\013\r\022\001\022\001\023\002\013\025\013X/\033<\021\005\0252S\"\001\002\007\013\005\021\001\022A\024\024\t\031B3&\004\t\003\025%J!A\013\003\003\r\005s\027PU3g!\t)C&\003\002.\005\t\001Bj\\<Qe&|'/\033;z\013F,\030N\036\005\006_\031\"\t\001M\001\007y%t\027\016\036 \025\003\021BQA\r\024\005\002M\n\021B]3gKJ,gnY3\026\005Q:T#A\033\021\007\025\002a\007\005\002\032o\021)1$\rb\001qE\021Q\004\013\005\006u\031\"\taO\001\nk:Lg/\032:tC2,\"\001P \026\003u\0022!\n\001?!\tIr\bB\003\034s\t\007A\004C\003BM\021\005!)\001\bge>l7i\\7qCJ\fGo\034:\026\005\r3EC\001#H!\r)\003!\022\t\0033\031#Qa\007!C\002qAQ\001\023!A\002%\0131aY7q!\rQu*R\007\002\027*\021A*T\001\005kRLGNC\001O\003\021Q\027M^1\n\005A[%AC\"p[B\f'/\031;pe\")!K\nC\001'\006aaM]8n\rVt7\r^5p]V\021Ak\026\013\003+b\0032!\n\001W!\tIr\013B\003\034#\n\007A\004C\003I#\002\007\021\fE\003\0135Z36#\003\002\\\t\tIa)\0368di&|gN\r\005\006;\032\"\tAX\001\003Ef,2aX2i)\t\001'\016\006\002bIB\031Q\005\0012\021\005e\031G!B\016]\005\004a\002bB3]\003\003\005\035AZ\001\013KZLG-\0328dK\022\n\004cA\023\001OB\021\021\004\033\003\006Sr\023\r\001\b\002\002'\")1\016\030a\001Y\006\ta\r\005\003\013[\n<\027B\0018\005\005%1UO\\2uS>t\027\007C\003qM\021\005\021/A\003baBd\0270\006\002skR\0211O\036\t\004K\001!\bCA\rv\t\025YrN1\001\035\021\0359x.!AA\004M\f!\"\032<jI\026t7-\032\0233\021\035Ih%!A\005\ni\f1B]3bIJ+7o\0347wKR\t1\020\005\002}6\tQP\003\002\033\006!A.\0318h\023\r\t\t! \002\007\037\nTWm\031;")
/*    */ public interface Equiv<T> extends Serializable {
/*    */   boolean equiv(T paramT1, T paramT2);
/*    */   
/*    */   public static class Equiv$$anon$1 implements Equiv<T> {
/*    */     public boolean equiv(Object x, Object y) {
/* 46 */       return (x == y);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$2 implements Equiv<T> {
/*    */     public boolean equiv(Object x, Object y) {
/* 49 */       return ((x == y) ? true : ((x == null) ? false : ((x instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x, y) : ((x instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x, y) : x.equals(y)))));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$3 implements Equiv<T> {
/*    */     private final Comparator cmp$1;
/*    */     
/*    */     public Equiv$$anon$3(Comparator cmp$1) {}
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 52 */       return (this.cmp$1.compare(x, y) == 0);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Equiv$$anon$4 implements Equiv<T> {
/*    */     private final Function2 cmp$2;
/*    */     
/*    */     public Equiv$$anon$4(Function2 cmp$2) {}
/*    */     
/*    */     public boolean equiv(Object x, Object y) {
/* 55 */       return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Equiv$$anonfun$by$1 extends AbstractFunction2<T, T, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 f$1;
/*    */     
/*    */     private final Equiv evidence$1$1;
/*    */     
/*    */     public final boolean apply(Object x, Object y) {
/* 58 */       Equiv<Object> equiv = this.evidence$1$1;
/* 58 */       Predef$ predef$ = Predef$.MODULE$;
/* 58 */       return equiv.equiv(this.f$1.apply(x), this.f$1.apply(y));
/*    */     }
/*    */     
/*    */     public Equiv$$anonfun$by$1(Function1 f$1, Equiv evidence$1$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Equiv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */