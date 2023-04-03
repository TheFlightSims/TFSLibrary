/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.mutable.UnrolledBuffer;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001)3Q!\001\002\001\005)\021a\003R8vE2LgnZ+oe>dG.\0323Ck\0324WM\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006,\"aC\n\024\005\001a\001cA\007\020#5\taB\003\002\004\r%\021\001C\004\002\017+:\024x\016\0347fI\n+hMZ3s!\t\0212\003\004\001\005\013Q\001!\031\001\f\003\003Q\033\001!\005\002\0307A\021\001$G\007\002\021%\021!\004\003\002\b\035>$\b.\0338h!\tAB$\003\002\036\021\t\031\021I\\=\t\023}\001!\021!Q\001\f\0012\023!\001;\021\007\005\"\023#D\001#\025\t\031\003\"A\004sK\032dWm\031;\n\005\025\022#\001C\"mCN\034H+Y4\n\005\035z\021a\001;bO\")\021\006\001C\001U\0051A(\0338jiz\"\022a\013\013\003Y9\0022!\f\001\022\033\005\021\001\"B\020)\001\b\001\003\"\002\031\001\t\003\n\024AD2bY\016tU\r\037;MK:<G\017\033\013\003eU\002\"\001G\032\n\005QB!aA%oi\")ag\fa\001e\005\0211O\037\005\006q\001!\t&O\001\f]\026<XK\034:pY2,G-F\001;!\rYt)\005\b\003y\025s!!\020#\017\005y\032eBA C\033\005\001%BA!\026\003\031a$o\\8u}%\t\021\"\003\002\b\021%\0211AB\005\003\r:\ta\"\0268s_2dW\r\032\"vM\032,'/\003\002I\023\nAQK\034:pY2,GM\003\002G\035\001")
/*    */ public class DoublingUnrolledBuffer<T> extends UnrolledBuffer<T> {
/*    */   public DoublingUnrolledBuffer(ClassTag t) {
/* 22 */     super(t);
/*    */   }
/*    */   
/*    */   public int calcNextLength(int sz) {
/* 23 */     return (sz < 10000) ? (sz * 2) : sz;
/*    */   }
/*    */   
/*    */   public UnrolledBuffer.Unrolled<T> newUnrolled() {
/* 24 */     return new UnrolledBuffer.Unrolled(0, tag().newArray(4), null, this, tag());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\DoublingUnrolledBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */