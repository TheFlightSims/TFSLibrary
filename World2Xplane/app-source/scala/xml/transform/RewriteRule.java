/*    */ package scala.xml.transform;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.xml.Node;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]2Q!\001\002\002\002%\0211BU3xe&$XMU;mK*\0211\001B\001\niJ\fgn\0354pe6T!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\031\"\001\001\006\021\005-aQ\"\001\002\n\0055\021!\001\005\"bg&\034GK]1og\032|'/\\3s\021\025y\001\001\"\001\021\003\031a\024N\\5u}Q\t\021\003\005\002\f\001!91\003\001b\001\n\003!\022\001\0028b[\026,\022!\006\t\003-mi\021a\006\006\0031e\tA\001\\1oO*\t!$\001\003kCZ\f\027B\001\017\030\005\031\031FO]5oO\"1a\004\001Q\001\nU\tQA\\1nK\002BQa\001\001\005B\001\"\"!I\031\021\007\tRSF\004\002$Q9\021AeJ\007\002K)\021a\005C\001\007yI|w\016\036 \n\003\035I!!\013\004\002\017A\f7m[1hK&\0211\006\f\002\004'\026\f(BA\025\007!\tqs&D\001\005\023\t\001DA\001\003O_\022,\007\"\002\032 \001\004\t\023A\0018t\021\025\031\001\001\"\0215)\t\tS\007C\0037g\001\007Q&A\001o\001")
/*    */ public abstract class RewriteRule extends BasicTransformer {
/* 23 */   private final String name = toString();
/*    */   
/*    */   public String name() {
/* 23 */     return this.name;
/*    */   }
/*    */   
/*    */   public Seq<Node> transform(Seq<Node> ns) {
/* 24 */     return super.transform(ns);
/*    */   }
/*    */   
/*    */   public Seq<Node> transform(Node n) {
/* 25 */     return (Seq<Node>)n;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\transform\RewriteRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */