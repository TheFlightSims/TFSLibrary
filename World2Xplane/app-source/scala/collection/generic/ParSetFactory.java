/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParSet;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001E4Q!\001\002\002\002%\021Q\002U1s'\026$h)Y2u_JL(BA\002\005\003\0359WM\\3sS\016T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\t\024\007\001YA\007E\002\r\033=i\021AA\005\003\035\t\021QbR3o'\026$h)Y2u_JL\bC\001\t\022\031\001!QA\005\001C\002M\021!aQ\"\026\005Q\031\023CA\013\032!\t1r#D\001\007\023\tAbAA\004O_RD\027N\\4\023\tia\022&\r\004\0057\001\001\021D\001\007=e\0264\027N\\3nK:$h\bE\002\036A\tj\021A\b\006\003?\021\t\001\002]1sC2dW\r\\\005\003Cy\021a\001U1s'\026$\bC\001\t$\t\025!\023C1\001&\005\005A\026CA\013'!\t1r%\003\002)\r\t\031\021I\\=1\005)z\003#B\017,E5r\023B\001\027\037\005)\001\026M]*fi2K7.\032\t\004!E\021\003C\001\t0\t%\001\024#!A\001\002\013\005QEA\002`IE\002B\001\004\032#\037%\0211G\001\002\023\017\026tWM]5d!\006\024H+Z7qY\006$X\rE\002\rk=I!A\016\002\003'\035+g.\032:jGB\013'oQ8na\006t\027n\0348\t\013a\002A\021A\035\002\rqJg.\033;?)\005Q\004c\001\007\001\037!)A\b\001C\001{\005Qa.Z<Ck&dG-\032:\026\005y\032U#A \021\tu\001%)R\005\003\003z\021\001bQ8nE&tWM\035\t\003!\r#Q\001R\036C\002\025\022\021!\021\t\004!E\021\005\"B$\001\r\003A\025a\0038fo\016{WNY5oKJ,\"!\023'\026\003)\003B!\b!L\033B\021\001\003\024\003\006\t\032\023\r!\n\t\004!EYe\001B(\001\001A\023QcR3oKJL7mQ1o\007>l'-\0338f\rJ|W.\006\002R=N\031aJU+\021\005Y\031\026B\001+\007\005\031\te.\037*fMB)AB\026-^?&\021qK\001\002\017\007\006t7i\\7cS:,gI]8na\tI6\fE\002\021#i\003\"\001E.\005\023qs\025\021!A\001\006\003)#aA0%eA\021\001C\030\003\006\t:\023\r!\n\t\004!Ei\006\"\002\035O\t\003\tG#\0012\021\007\rtU,D\001\001\021\025)g\n\"\021g\003\025\t\007\017\0357z)\t9\007\016\005\003\036\001v{\006\"B5e\001\004Q\027\001\0024s_6\004\"aY6\n\0051l'\001B\"pY2L!A\034\002\003!\035+g.\032:jG\016{W\016]1oS>t\007\"B3O\t\003\002H#A4")
/*    */ public abstract class ParSetFactory<CC extends ParSet<Object>> extends GenSetFactory<CC> implements GenericParCompanion<CC> {
/*    */   public abstract <A> Combiner<A, CC> newCombiner();
/*    */   
/*    */   public <A> Combiner<A, CC> newBuilder() {
/* 25 */     return newCombiner();
/*    */   }
/*    */   
/*    */   public class GenericCanCombineFrom<A> implements CanCombineFrom<CC, A, CC> {
/*    */     public GenericCanCombineFrom(ParSetFactory $outer) {}
/*    */     
/*    */     public Combiner<A, CC> apply(ParSet from) {
/* 30 */       return from.genericCombiner();
/*    */     }
/*    */     
/*    */     public Combiner<A, CC> apply() {
/* 31 */       return scala$collection$generic$ParSetFactory$GenericCanCombineFrom$$$outer().newCombiner();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\ParSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */