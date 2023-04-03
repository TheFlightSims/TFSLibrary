/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.xml.dtd.EntityDef;
/*    */ import scala.xml.dtd.IntDef;
/*    */ import scala.xml.dtd.ParsedEntityDecl;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001E;Q!\001\002\t\002%\tQ\002\0275u[2,e\016^5uS\026\034(BA\002\005\003\035\001\030M]:j]\036T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\001\"AC\006\016\003\t1Q\001\004\002\t\0025\021Q\002\0275u[2,e\016^5uS\026\0348CA\006\017!\ty\001#D\001\007\023\t\tbA\001\004B]f\024VM\032\005\006'-!\t\001F\001\007y%t\027\016\036 \025\003%AqAF\006C\002\023\005q#A\004f]Rd\025n\035;\026\003a\0012!\007\020!\033\005Q\"BA\016\035\003%IW.\\;uC\ndWM\003\002\036\r\005Q1m\0347mK\016$\030n\0348\n\005}Q\"\001\002'jgR\004BaD\021$W%\021!E\002\002\007)V\004H.\032\032\021\005\021JS\"A\023\013\005\031:\023\001\0027b]\036T\021\001K\001\005U\0064\030-\003\002+K\t11\013\036:j]\036\004\"a\004\027\n\00552!aA%oi\"1qf\003Q\001\na\t\001\"\0328u\031&\034H\017\t\005\bc-\021\r\021\"\0013\003\031)g\016^'baV\t1\007\005\0035oibdBA\b6\023\t1d!\001\004Qe\026$WMZ\005\003qe\0221!T1q\025\t1d\001\005\0025w%\021!&\017\t\003\037uJ!A\020\004\003\t\rC\027M\035\005\007\001.\001\013\021B\032\002\017\025tG/T1qA!9!i\003b\001\n\003\031\025\001C3oi&$\030.Z:\026\003\021\0032!\007\020F!\021y\021e\t$\021\005\035SU\"\001%\013\005%#\021a\0013uI&\0211\n\023\002\021!\006\0248/\0323F]RLG/\037#fG2Da!T\006!\002\023!\025!C3oi&$\030.Z:!\021\025y5\002\"\001Q\003\025\t\007\017\0357z)\005!\005")
/*    */ public final class XhtmlEntities {
/*    */   public static List<Tuple2<String, ParsedEntityDecl>> apply() {
/*    */     return XhtmlEntities$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public static List<Tuple2<String, ParsedEntityDecl>> entities() {
/*    */     return XhtmlEntities$.MODULE$.entities();
/*    */   }
/*    */   
/*    */   public static Map<String, Object> entMap() {
/*    */     return XhtmlEntities$.MODULE$.entMap();
/*    */   }
/*    */   
/*    */   public static List<Tuple2<String, Object>> entList() {
/*    */     return XhtmlEntities$.MODULE$.entList();
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, Object> apply(Tuple2 x0$2) {
/* 47 */       if (x0$2 != null)
/* 47 */         return new Tuple2(x0$2._1(), BoxesRunTime.boxToCharacter((char)x0$2._2$mcI$sp())); 
/* 47 */       throw new MatchError(x0$2);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<String, ParsedEntityDecl>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<String, ParsedEntityDecl> apply(Tuple2 x0$1) {
/* 50 */       if (x0$1 != null)
/* 50 */         return new Tuple2(x0$1._1(), new ParsedEntityDecl((String)x0$1._1(), (EntityDef)new IntDef(BoxesRunTime.boxToCharacter((char)x0$1._2$mcI$sp()).toString()))); 
/* 50 */       throw new MatchError(x0$1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\XhtmlEntities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */