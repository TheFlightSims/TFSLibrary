/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!4Q!\001\002\002\002%\021QbR3o'\026$h)Y2u_JL(BA\002\005\003\0359WM\\3sS\016T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\t\024\005\001Y\001c\001\007\016\0375\t!!\003\002\017\005\t\001r)\0328fe&\0347i\\7qC:LwN\034\t\003!Ea\001\001B\003\023\001\t\0071C\001\002D\007V\021A#I\t\003+e\001\"AF\f\016\003\031I!\001\007\004\003\0179{G\017[5oOJ\031!\004H\024\007\tm\001\001!\007\002\ryI,g-\0338f[\026tGO\020\t\004;y\001S\"\001\003\n\005}!!AB$f]N+G\017\005\002\021C\021)!%\005b\001G\t\t\001,\005\002\026IA\021a#J\005\003M\031\0211!\0218z!\021i\002\006\t\026\n\005%\"!AC$f]N+G\017T5lKB\031\001#\005\021\t\0131\002A\021A\027\002\rqJg.\033;?)\005q\003c\001\007\001\037!)\001\007\001D\001c\005Qa.Z<Ck&dG-\032:\026\005IRT#A\032\021\tQ:\024\bP\007\002k)\021a\007B\001\b[V$\030M\0317f\023\tATGA\004Ck&dG-\032:\021\005AQD!B\0360\005\004\031#!A!\021\007A\t\022\bC\003?\001\021\005q(A\btKR\034\025M\034\"vS2$gI]8n+\t\001e-F\001B%\r\0215I\022\004\0057u\002\021\t\005\002\027\t&\021QI\002\002\007\003:L(+\0324\021\01319\025*Z4\n\005!\023!\001D\"b]\n+\030\016\0343Ge>l\007G\001&M!\r\001\022c\023\t\003!1#\021\"\024(\002\002\003\005)\021A\022\003\007}#\023G\002\003P{\t\001&!\002\023b]>t7c\001(D#B)AbR%S)B\021\001c\025\003\006wu\022\ra\t\t\004!E\021\006\"\002\027O\t\0031F#A,\021\005Aq\005\"B-O\t\003Q\026!B1qa2LHCA.]!\021!tG\025+\t\013uC\006\031\0010\002\t\031\024x.\034\031\003?\006\0042\001E\ta!\t\001\022\rB\005c9\006\005\t\021!B\001G\t\031q\f\n\032\t\013esE\021\0013\025\003m\003\"\001\0054\005\013mj$\031A\022\021\007A\tR\r")
/*    */ public abstract class GenSetFactory<CC extends GenSet<Object>> extends GenericCompanion<CC> {
/*    */   public abstract <A> Builder<A, CC> newBuilder();
/*    */   
/*    */   public <A> Object setCanBuildFrom() {
/* 41 */     return new GenSetFactory$$anon$1(this);
/*    */   }
/*    */   
/*    */   public class GenSetFactory$$anon$1 implements CanBuildFrom<CC, A, CC> {
/*    */     public GenSetFactory$$anon$1(GenSetFactory $outer) {}
/*    */     
/*    */     public Builder<A, CC> apply(GenSet from) {
/* 42 */       return this.$outer.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<A, CC> apply() {
/* 43 */       return this.$outer.newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */