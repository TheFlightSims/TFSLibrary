/*    */ package scala.xml.transform;
/*    */ 
/*    */ import scala.Function2;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.xml.Node;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A2A!\001\002\001\023\ty!+\0367f)J\fgn\0354pe6,'O\003\002\004\t\005IAO]1og\032|'/\034\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001A\n\003\001)\001\"a\003\007\016\003\tI!!\004\002\003!\t\0137/[2Ue\006t7OZ8s[\026\024\b\002C\b\001\005\003\005\013\021\002\t\002\013I,H.Z:\021\007E\021B#D\001\007\023\t\031bA\001\006=e\026\004X-\031;fIz\002\"aC\013\n\005Y\021!a\003*foJLG/\032*vY\026DQ\001\007\001\005\002e\ta\001P5oSRtDC\001\016\034!\tY\001\001C\003\020/\001\007\001\003C\003\004\001\021\005S\004\006\002\037]A\031qd\n\026\017\005\001*cBA\021%\033\005\021#BA\022\t\003\031a$o\\8u}%\tq!\003\002'\r\0059\001/Y2lC\036,\027B\001\025*\005\r\031V-\035\006\003M\031\001\"a\013\027\016\003\021I!!\f\003\003\t9{G-\032\005\006_q\001\rAK\001\002]\002")
/*    */ public class RuleTransformer extends BasicTransformer {
/*    */   private final Seq<RewriteRule> rules;
/*    */   
/*    */   public RuleTransformer(Seq<RewriteRule> rules) {}
/*    */   
/*    */   public Seq<Node> transform(Node n) {
/* 16 */     return (Seq<Node>)this.rules.foldLeft(super.transform(n), (Function2)new RuleTransformer$$anonfun$transform$1(this));
/*    */   }
/*    */   
/*    */   public class RuleTransformer$$anonfun$transform$1 extends AbstractFunction2<Seq<Node>, RewriteRule, Seq<Node>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Seq<Node> apply(Seq<Node> res, RewriteRule rule) {
/* 16 */       return rule.transform(res);
/*    */     }
/*    */     
/*    */     public RuleTransformer$$anonfun$transform$1(RuleTransformer $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\transform\RuleTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */