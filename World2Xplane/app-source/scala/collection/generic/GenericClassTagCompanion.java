/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y3Q!\001\002\002\002%\021\001dR3oKJL7m\0217bgN$\026mZ\"p[B\fg.[8o\025\t\031A!A\004hK:,'/[2\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\005)12C\001\001\f!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\005\006!\001!\t!E\001\007y%t\027\016\036 \025\003I\0012a\005\001\025\033\005\021\001CA\013\027\031\001!aa\006\001\005\006\004A\"AA\"D+\tI\"%\005\002\033;A\021AbG\005\0039\031\021qAT8uQ&tw\rE\002\037?\005j\021\001B\005\003A\021\0211\002\026:bm\026\0248/\0312mKB\021QC\t\003\006GY\021\r\001\n\002\0021F\021!$\n\t\003\031\031J!a\n\004\003\007\005s\0270\002\004*\001\001\006\tB\013\002\005\007>dG\016\r\002,[A\031QC\006\027\021\005UiC!\003\030)\003\003\005\tQ!\001%\005\ryF%\r\005\006a\0011\t!M\001\013]\026<()^5mI\026\024XC\001\032;)\t\031T\b\005\0035oebT\"A\033\013\005Y\"\021aB7vi\006\024G.Z\005\003qU\022qAQ;jY\022,'\017\005\002\026u\021)1h\fb\001I\t\t\021\tE\002\026-eBQAP\030A\004}\n1a\034:e!\r\0015)O\007\002\003*\021!IB\001\be\0264G.Z2u\023\t!\025I\001\005DY\006\0348\017V1h\021\0251\005\001\"\001H\003\025)W\016\035;z+\tA5\n\006\002J\031B\031QC\006&\021\005UYE!B\036F\005\004!\003bB'F\003\003\005\035AT\001\013KZLG-\0328dK\022\n\004c\001!D\025\")\001\013\001C\001#\006)\021\r\0359msV\021!K\026\013\003'f#\"\001V,\021\007U1R\013\005\002\026-\022)1h\024b\001I!)ah\024a\0021B\031\001iQ+\t\013i{\005\031A.\002\013\025dW-\\:\021\0071aV+\003\002^\r\tQAH]3qK\006$X\r\032 ")
/*    */ public abstract class GenericClassTagCompanion<CC extends Traversable<Object>> {
/*    */   public abstract <A> Builder<A, CC> newBuilder(ClassTag<A> paramClassTag);
/*    */   
/*    */   public <A> CC empty(ClassTag<A> evidence$1) {
/* 26 */     return (CC)newBuilder(evidence$1).result();
/*    */   }
/*    */   
/*    */   public <A> CC apply(Seq elems, ClassTag<?> ord) {
/* 29 */     Builder<?, Traversable> b = newBuilder(ord);
/* 30 */     b.$plus$plus$eq((TraversableOnce)elems);
/* 31 */     return (CC)b.result();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericClassTagCompanion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */