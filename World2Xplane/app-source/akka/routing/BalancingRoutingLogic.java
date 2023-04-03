/*    */ package akka.routing;
/*    */ 
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e:a!\001\002\t\002\0211\021!\006\"bY\006t7-\0338h%>,H/\0338h\031><\027n\031\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\021\005\035AQ\"\001\002\007\r%\021\001\022\001\003\013\005U\021\025\r\\1oG&twMU8vi&tw\rT8hS\016\034\"\001C\006\021\0051yQ\"A\007\013\0039\tQa]2bY\006L!\001E\007\003\r\005s\027PU3g\021\025\021\002\002\"\001\025\003\031a\024N\\5u}\r\001A#\001\004\t\013YAA\021A\f\002\013\005\004\b\017\\=\025\003a\001\"aB\r\007\013%\021!\001\002\016\024\007eY1\004\005\002\b9%\021QD\001\002\r%>,H/\0338h\031><\027n\031\005\006%e!\ta\006\005\006Ae!\t%I\001\007g\026dWm\031;\025\007\t*#\006\005\002\bG%\021AE\001\002\007%>,H/Z3\t\013\031z\002\031A\024\002\0175,7o]1hKB\021A\002K\005\003S5\0211!\0218z\021\025Ys\0041\001-\003\035\021x.\036;fKN\0042!\f\032#\033\005q#BA\0301\003%IW.\\;uC\ndWM\003\0022\033\005Q1m\0347mK\016$\030n\0348\n\005Mr#AC%oI\026DX\rZ*fc\"\032\021$\016\035\021\00511\024BA\034\016\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\001")
/*    */ public final class BalancingRoutingLogic implements RoutingLogic {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   public static BalancingRoutingLogic apply() {
/*    */     return BalancingRoutingLogic$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public Routee select(Object message, IndexedSeq routees) {
/* 30 */     return routees.isEmpty() ? NoRoutee$.MODULE$ : 
/* 31 */       (Routee)routees.head();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\BalancingRoutingLogic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */