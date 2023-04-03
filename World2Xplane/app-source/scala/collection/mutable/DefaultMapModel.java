/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001a3q!\001\002\021\002\007\005\021BA\bEK\032\fW\017\034;NCBlu\016Z3m\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))rdE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\0075\013\007\017\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\003)}!Q\001\t\001C\002]\021\021A\021\005\006E\001!\taI\001\007I%t\027\016\036\023\025\003\021\002\"\001D\023\n\005\0312!\001B+oSR,A\001\013\001\001S\t)QI\034;ssB!\001CK\n\037\023\tY#A\001\007EK\032\fW\017\034;F]R\024\030\020C\003.\001\031Ea&A\005gS:$WI\034;ssR\021q&\r\t\003a\035j\021\001\001\005\006e1\002\raE\001\004W\026L\b\"\002\033\001\r#)\024\001C1eI\026sGO]=\025\005\0212\004\"B\0344\001\004y\023!A3\t\013e\002a\021\003\036\002\017\025tGO]5fgV\t1\bE\002={=j\021\001B\005\003}\021\021\001\"\023;fe\006$xN\035\005\006\001\002!\t!Q\001\004O\026$HC\001\"F!\ra1IH\005\003\t\032\021aa\0249uS>t\007\"\002\032@\001\004\031\002\"B$\001\t\003B\025a\0019viR\031!)\023&\t\013I2\005\031A\n\t\013-3\005\031\001\020\002\013Y\fG.^3\t\0135\003A\021\001(\002\021\021\002H.^:%KF$\"\001M(\t\013Ac\005\031A)\002\005-4\b\003\002\007S'yI!a\025\004\003\rQ+\b\017\\33\021\025)\006\001\"\001W\003!IG/\032:bi>\024X#A,\021\007qj\024\013")
/*    */ public interface DefaultMapModel<A, B> extends Map<A, B> {
/*    */   DefaultEntry<A, B> findEntry(A paramA);
/*    */   
/*    */   void addEntry(DefaultEntry<A, B> paramDefaultEntry);
/*    */   
/*    */   Iterator<DefaultEntry<A, B>> entries();
/*    */   
/*    */   Option<B> get(A paramA);
/*    */   
/*    */   Option<B> put(A paramA, B paramB);
/*    */   
/*    */   DefaultMapModel<A, B> $plus$eq(Tuple2<A, B> paramTuple2);
/*    */   
/*    */   Iterator<Tuple2<A, B>> iterator();
/*    */   
/*    */   public class DefaultMapModel$$anonfun$iterator$1 extends AbstractFunction1<DefaultEntry<A, B>, Tuple2<A, B>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<A, B> apply(DefaultEntry e) {
/* 43 */       return new Tuple2(e.key(), e.value());
/*    */     }
/*    */     
/*    */     public DefaultMapModel$$anonfun$iterator$1(DefaultMapModel $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\DefaultMapModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */