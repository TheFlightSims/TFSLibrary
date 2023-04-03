/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A3Q!\001\002\002\002%\021\001cR3oKJL7mQ8na\006t\027n\0348\013\005\r!\021aB4f]\026\024\030n\031\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025Y\031\"\001A\006\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rC\003\021\001\021\005\021#\001\004=S:LGO\020\013\002%A\0311\003\001\013\016\003\t\001\"!\006\f\r\001\0211q\003\001CC\002a\021!aQ\"\026\005e\021\023C\001\016\036!\ta1$\003\002\035\r\t9aj\034;iS:<\007c\001\020 C5\tA!\003\002!\t\tqq)\0328Ue\0064XM]:bE2,\007CA\013#\t\025\031cC1\001%\005\005A\026C\001\016&!\taa%\003\002(\r\t\031\021I\\=\006\r%\002\001\025!\005+\005\021\031u\016\03471\005-j\003cA\013\027YA\021Q#\f\003\n]!\n\t\021!A\003\002\021\0221a\030\0232\021\025\001\004A\"\0012\003)qWm\036\"vS2$WM]\013\003ei*\022a\r\t\005i]JD(D\0016\025\t1D!A\004nkR\f'\r\\3\n\005a*$a\002\"vS2$WM\035\t\003+i\"QaO\030C\002\021\022\021!\021\t\004+YI\004\"\002 \001\t\003y\024!B3naRLXC\001!D+\005\t\005cA\013\027\005B\021Qc\021\003\006wu\022\r\001\n\005\006\013\002!\tAR\001\006CB\004H._\013\003\017*#\"\001S&\021\007U1\022\n\005\002\026\025\022)1\b\022b\001I!)A\n\022a\001\033\006)Q\r\\3ngB\031ABT%\n\005=3!A\003\037sKB,\027\r^3e}\001")
/*    */ public abstract class GenericCompanion<CC extends GenTraversable<Object>> {
/*    */   public abstract <A> Builder<A, CC> newBuilder();
/*    */   
/*    */   public <A> CC empty() {
/* 37 */     return (CC)newBuilder().result();
/*    */   }
/*    */   
/*    */   public <A> CC apply(Seq elems) {
/* 47 */     Builder<?, GenTraversable> b = newBuilder();
/* 48 */     b.$plus$plus$eq((TraversableOnce)elems);
/* 49 */     return elems.isEmpty() ? empty() : (CC)b.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericCompanion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */