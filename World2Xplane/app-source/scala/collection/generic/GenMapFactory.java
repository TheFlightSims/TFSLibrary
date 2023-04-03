/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.MapBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001}4Q!\001\002\002\002%\021QbR3o\033\006\004h)Y2u_JL(BA\002\005\003\0359WM\\3sS\016T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"A\003\f\024\005\001Y\001C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fM\")\001\003\001C\001#\0051A(\0338jiz\"\022A\005\t\004'\001!R\"\001\002\021\005U1B\002\001\003\006/\001\021\r\001\007\002\003\007\016+2!G\023-#\tQR\004\005\002\r7%\021AD\002\002\b\035>$\b.\0338h%\rq\002E\f\004\005?\001\001QD\001\007=e\0264\027N\\3nK:$h\b\005\003\"E\021ZS\"\001\003\n\005\r\"!AB$f]6\013\007\017\005\002\026K\021)aE\006b\001O\t\t\021)\005\002\033QA\021A\"K\005\003U\031\0211!\0218z!\t)B\006B\003.-\t\007qEA\001C!\025\ts\006J\0262\023\t\001DA\001\006HK:l\025\r\035'jW\026\004B!\006\f%W\025!1\007\001\0015\005\021\031u\016\03471\007U:$\b\005\003\026-YJ\004CA\0138\t%A$'!A\001\002\013\005qEA\002`IE\002\"!\006\036\005\023m\022\024\021!A\001\006\0039#aA0%e!)Q\b\001D\001}\005)Q-\0349usV\031qH\021#\026\003\001\003B!\006\fB\007B\021QC\021\003\006Mq\022\ra\n\t\003+\021#Q!\f\037C\002\035BQA\022\001\005\002\035\013Q!\0319qYf,2\001S&N)\tIe\n\005\003\026-)c\005CA\013L\t\0251SI1\001(!\t)R\nB\003.\013\n\007q\005C\003P\013\002\007\001+A\003fY\026l7\017E\002\r#NK!A\025\004\003\025q\022X\r]3bi\026$g\b\005\003\r)*c\025BA+\007\005\031!V\017\0357fe!)q\013\001C\0011\006Qa.Z<Ck&dG-\032:\026\007e\023G-F\001[!\021Yf\fY3\016\003qS!!\030\003\002\0175,H/\0312mK&\021q\f\030\002\b\005VLG\016Z3s!\021aA+Y2\021\005U\021G!\002\024W\005\0049\003CA\013e\t\025icK1\001(!\021)b#Y2\007\t\035\004\001\001\033\002\020\033\006\0048)\0318Ck&dGM\022:p[V\031\021.]:\024\007\031\\!\016E\003\024W6|G/\003\002m\005\ta1)\0318Ck&dGM\022:p[B\021aNM\007\002\001A!A\002\0269s!\t)\022\017B\003'M\n\007q\005\005\002\026g\022)QF\032b\001OA!QC\0069s\021\025\001b\r\"\001w)\0059\b\003\0028gaJDQA\0224\005\002e$\"A_>\021\tmsv\016\036\005\006yb\004\r!\\\001\005MJ|W\016C\003GM\022\005a\020F\001{\001")
/*    */ public abstract class GenMapFactory<CC extends GenMap<Object, Object>> {
/*    */   public abstract <A, B> CC empty();
/*    */   
/*    */   public <A, B> CC apply(Seq elems) {
/* 47 */     return (CC)((Builder)newBuilder().$plus$plus$eq((TraversableOnce)elems)).result();
/*    */   }
/*    */   
/*    */   public <A, B> Builder<Tuple2<A, B>, CC> newBuilder() {
/* 53 */     return (Builder<Tuple2<A, B>, CC>)new MapBuilder(empty());
/*    */   }
/*    */   
/*    */   public class MapCanBuildFrom<A, B> implements CanBuildFrom<CC, Tuple2<A, B>, CC> {
/*    */     public MapCanBuildFrom(GenMapFactory $outer) {}
/*    */     
/*    */     public Builder<Tuple2<A, B>, CC> apply(GenMap from) {
/* 58 */       return scala$collection$generic$GenMapFactory$MapCanBuildFrom$$$outer().newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, CC> apply() {
/* 59 */       return scala$collection$generic$GenMapFactory$MapCanBuildFrom$$$outer().newBuilder();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenMapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */