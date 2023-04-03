/*    */ package scala.xml.factory;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.xml.Comment;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.ProcInstr;
/*    */ import scala.xml.Text;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0055caB\001\003!\003\r\t!\003\002\f\035>$WMR1di>\024\030P\003\002\004\t\0059a-Y2u_JL(BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\001QC\001\006>'\t\0011\002\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032DQ\001\005\001\005\002E\ta\001J5oSR$C#\001\n\021\0051\031\022B\001\013\007\005\021)f.\033;\t\017Y\001!\031!C\001/\005q\021n\0328pe\026\034u.\\7f]R\034X#\001\r\021\0051I\022B\001\016\007\005\035\021un\0347fC:Da\001\b\001!\002\023A\022aD5h]>\024XmQ8n[\026tGo\035\021\t\017y\001!\031!C\001/\005y\021n\0328pe\026\004&o\\2J]N$(\017\003\004!\001\001\006I\001G\001\021S\036twN]3Qe>\034\027J\\:ue\002BqA\t\001C\002\023\0051%A\003dC\016DW-F\001%!\021)#\006L\030\016\003\031R!a\n\025\002\0175,H/\0312mK*\021\021FB\001\013G>dG.Z2uS>t\027BA\026'\005\035A\025m\0355NCB\004\"\001D\027\n\00592!aA%oiB\031\001\007O\036\017\005E2dB\001\0326\033\005\031$B\001\033\t\003\031a$o\\8u}%\tq!\003\0028\r\0059\001/Y2lC\036,\027BA\035;\005\021a\025n\035;\013\005]2\001C\001\037>\031\001!QA\020\001C\002}\022\021!Q\t\003\001\016\003\"\001D!\n\005\t3!a\002(pi\"Lgn\032\t\003\t\026k\021\001B\005\003\r\022\021AAT8eK\"1\001\n\001Q\001\n\021\naaY1dQ\026\004\003\"\002&\001\r#Y\025AB2sK\006$X\r\006\004<\031V;F,\031\005\006\033&\003\rAT\001\004aJ,\007CA(S\035\ta\001+\003\002R\r\0051\001K]3eK\032L!a\025+\003\rM#(/\0338h\025\t\tf\001C\003W\023\002\007a*\001\003oC6,\007\"\002-J\001\004I\026!B1uiJ\034\bC\001#[\023\tYFA\001\005NKR\fG)\031;b\021\025i\026\n1\001_\003\025\0318m\0349f!\t!u,\003\002a\t\t\001b*Y7fgB\f7-\032\"j]\022Lgn\032\005\006E&\003\raY\001\tG\"LG\016\032:f]B\031\001\007Z\"\n\005\025T$aA*fc\")q\r\001C\tQ\006I1m\0348tiJ,8\r\036\013\tw%\\WN\\8re\")!N\032a\001Y\005!\001.Y:i\021\025ag\r1\0010\003\ryG\016\032\005\006\033\032\004\rA\024\005\006-\032\004\rA\024\005\006a\032\004\r!W\001\bCR$(oU3r\021\025if\r1\001_\021\025\021g\r1\001d\021\025!\b\001\"\001v\003))\027/\0227f[\026tGo\035\013\0041YD\b\"B<t\001\004\031\027aA2ic!)\021p\035a\001G\006\0311\r\033\032\t\013m\004A\021\001?\002\0259|G-Z#rk\006d7\017F\006\031{~\f\t!a\001\002\006\005\035\001\"\002@{\001\004\031\025!\0018\t\0135S\b\031\001(\t\013YS\b\031\001(\t\013AT\b\031A-\t\013uS\b\031\0010\t\013\tT\b\031A2\t\017\005-\001\001\"\001\002\016\005AQ.Y6f\035>$W\rF\006<\003\037\t\t\"a\005\002\026\005]\001BB'\002\n\001\007a\n\003\004W\003\023\001\rA\024\005\007a\006%\001\031A-\t\ru\013I\0011\001_\021\031\021\027\021\002a\001G\"9\0211\004\001\005\002\005u\021\001C7bW\026$V\r\037;\025\t\005}\021Q\005\t\004\t\006\005\022bAA\022\t\t!A+\032=u\021\035\t9#!\007A\0029\013\021a\035\005\b\003W\001A\021AA\027\003-i\027m[3D_6lWM\034;\025\t\005=\022q\007\t\005a\021\f\t\004E\002E\003gI1!!\016\005\005\035\031u.\\7f]RDq!a\n\002*\001\007a\nC\004\002<\001!\t!!\020\002\0335\f7.\032)s_\016Len\035;s)\031\ty$a\022\002LA!\001\007ZA!!\r!\0251I\005\004\003\013\"!!\003)s_\016Len\035;s\021\035\tI%!\017A\0029\013\021\001\036\005\b\003O\tI\0041\001O\001")
/*    */ public interface NodeFactory<A extends Node> {
/*    */   void scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(boolean paramBoolean);
/*    */   
/*    */   void scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(boolean paramBoolean);
/*    */   
/*    */   void scala$xml$factory$NodeFactory$_setter_$cache_$eq(HashMap paramHashMap);
/*    */   
/*    */   boolean ignoreComments();
/*    */   
/*    */   boolean ignoreProcInstr();
/*    */   
/*    */   HashMap<Object, List<A>> cache();
/*    */   
/*    */   A create(String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
/*    */   
/*    */   A construct(int paramInt, List<A> paramList, String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
/*    */   
/*    */   boolean eqElements(Seq<Node> paramSeq1, Seq<Node> paramSeq2);
/*    */   
/*    */   boolean nodeEquals(Node paramNode, String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
/*    */   
/*    */   A makeNode(String paramString1, String paramString2, MetaData paramMetaData, NamespaceBinding paramNamespaceBinding, Seq<Node> paramSeq);
/*    */   
/*    */   Text makeText(String paramString);
/*    */   
/*    */   Seq<Comment> makeComment(String paramString);
/*    */   
/*    */   Seq<ProcInstr> makeProcInstr(String paramString1, String paramString2);
/*    */   
/*    */   public class NodeFactory$$anonfun$eqElements$1 extends AbstractFunction1<Tuple2<Node, Node>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Tuple2 x0$1) {
/* 32 */       if (x0$1 != null)
/* 32 */         return (x0$1._1() == x0$1._2()); 
/* 32 */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public NodeFactory$$anonfun$eqElements$1(NodeFactory $outer) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String pre$1;
/*    */     
/*    */     private final String name$1;
/*    */     
/*    */     private final MetaData attrSeq$1;
/*    */     
/*    */     private final NamespaceBinding scope$1;
/*    */     
/*    */     private final Seq children$1;
/*    */     
/*    */     public final boolean apply(Node x$2) {
/* 47 */       return this.$outer.nodeEquals(x$2, this.pre$1, this.name$1, this.attrSeq$1, this.scope$1, this.children$1);
/*    */     }
/*    */     
/*    */     public $anonfun$1(NodeFactory $outer, String pre$1, String name$1, MetaData attrSeq$1, NamespaceBinding scope$1, Seq children$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\NodeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */