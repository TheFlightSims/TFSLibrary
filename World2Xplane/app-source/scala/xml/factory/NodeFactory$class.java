/*    */ package scala.xml.factory;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Some;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.LinearSeqOptimized;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqView$;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.Comment;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.ProcInstr;
/*    */ import scala.xml.Text;
/*    */ import scala.xml.Text$;
/*    */ import scala.xml.Utility$;
/*    */ 
/*    */ public abstract class NodeFactory$class {
/*    */   public static void $init$(NodeFactory $this) {
/* 17 */     $this.scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(false);
/* 18 */     $this.scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(false);
/* 21 */     $this.scala$xml$factory$NodeFactory$_setter_$cache_$eq(new HashMap());
/*    */   }
/*    */   
/*    */   public static Node construct(NodeFactory<Node> $this, int hash, List old, String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq<Node> children) {
/* 26 */     Node el = $this.create(pre, name, attrSeq, scope, children);
/* 27 */     $this.cache().update(BoxesRunTime.boxToInteger(hash), old.$colon$colon(el));
/* 28 */     return el;
/*    */   }
/*    */   
/*    */   public static boolean eqElements(NodeFactory<A> $this, Seq ch1, Seq ch2) {
/* 32 */     return ((IterableLike)ch1.view().zipAll((GenIterable)ch2.view(), null, null, SeqView$.MODULE$.canBuildFrom())).forall((Function1)new NodeFactory$$anonfun$eqElements$1($this));
/*    */   }
/*    */   
/*    */   public static boolean nodeEquals(NodeFactory $this, Node n, String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq children) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: invokevirtual prefix : ()Ljava/lang/String;
/*    */     //   4: dup
/*    */     //   5: ifnonnull -> 16
/*    */     //   8: pop
/*    */     //   9: aload_2
/*    */     //   10: ifnull -> 23
/*    */     //   13: goto -> 90
/*    */     //   16: aload_2
/*    */     //   17: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   20: ifeq -> 90
/*    */     //   23: aload_1
/*    */     //   24: invokevirtual label : ()Ljava/lang/String;
/*    */     //   27: dup
/*    */     //   28: ifnonnull -> 39
/*    */     //   31: pop
/*    */     //   32: aload_3
/*    */     //   33: ifnull -> 46
/*    */     //   36: goto -> 90
/*    */     //   39: aload_3
/*    */     //   40: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   43: ifeq -> 90
/*    */     //   46: aload_1
/*    */     //   47: invokevirtual attributes : ()Lscala/xml/MetaData;
/*    */     //   50: dup
/*    */     //   51: ifnonnull -> 63
/*    */     //   54: pop
/*    */     //   55: aload #4
/*    */     //   57: ifnull -> 71
/*    */     //   60: goto -> 90
/*    */     //   63: aload #4
/*    */     //   65: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   68: ifeq -> 90
/*    */     //   71: aload_0
/*    */     //   72: aload_1
/*    */     //   73: invokevirtual child : ()Lscala/collection/Seq;
/*    */     //   76: aload #6
/*    */     //   78: invokeinterface eqElements : (Lscala/collection/Seq;Lscala/collection/Seq;)Z
/*    */     //   83: ifeq -> 90
/*    */     //   86: iconst_1
/*    */     //   87: goto -> 91
/*    */     //   90: iconst_0
/*    */     //   91: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #35	-> 0
/*    */     //   #36	-> 23
/*    */     //   #37	-> 46
/*    */     //   #39	-> 71
/*    */     //   #37	-> 86
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	92	0	$this	Lscala/xml/factory/NodeFactory;
/*    */     //   0	92	1	n	Lscala/xml/Node;
/*    */     //   0	92	2	pre	Ljava/lang/String;
/*    */     //   0	92	3	name	Ljava/lang/String;
/*    */     //   0	92	4	attrSeq	Lscala/xml/MetaData;
/*    */     //   0	92	5	scope	Lscala/xml/NamespaceBinding;
/*    */     //   0	92	6	children	Lscala/collection/Seq;
/*    */   }
/*    */   
/*    */   public static Node makeNode(NodeFactory $this, String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq children) {
/* 42 */     int hash = Utility$.MODULE$.hashCode(pre, name, ScalaRunTime$.MODULE$.hash(attrSeq), ScalaRunTime$.MODULE$.hash(scope), children);
/* 45 */     Option option = $this.cache().get(BoxesRunTime.boxToInteger(hash));
/* 46 */     if (option instanceof Some) {
/* 46 */       Some some = (Some)option;
/* 47 */       Option option1 = ((LinearSeqOptimized)some.x()).find((Function1)new NodeFactory.$anonfun$1($this, pre, name, attrSeq, scope, (NodeFactory<A>)children));
/*    */     } 
/* 51 */     if (None$.MODULE$ == null) {
/* 51 */       if (option != null)
/*    */         throw new MatchError(option); 
/* 51 */     } else if (!None$.MODULE$.equals(option)) {
/*    */       throw new MatchError(option);
/*    */     } 
/* 51 */     return cons$1($this, (List)Nil$.MODULE$, hash, pre, name, attrSeq, scope, children);
/*    */   }
/*    */   
/*    */   private static final Node cons$1(NodeFactory<Node> $this, List<Node> old, int hash$1, String pre$1, String name$1, MetaData attrSeq$1, NamespaceBinding scope$1, Seq<Node> children$1) {
/*    */     return $this.construct(hash$1, old, pre$1, name$1, attrSeq$1, scope$1, children$1);
/*    */   }
/*    */   
/*    */   public static Text makeText(NodeFactory $this, String s) {
/* 55 */     return Text$.MODULE$.apply(s);
/*    */   }
/*    */   
/*    */   public static Seq makeComment(NodeFactory $this, String s) {
/* 57 */     (new Comment[1])[0] = new Comment(s);
/* 57 */     return $this.ignoreComments() ? (Seq)Nil$.MODULE$ : (Seq)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Comment[1]));
/*    */   }
/*    */   
/*    */   public static Seq makeProcInstr(NodeFactory $this, String t, String s) {
/* 59 */     (new ProcInstr[1])[0] = new ProcInstr(t, s);
/* 59 */     return $this.ignoreProcInstr() ? (Seq)Nil$.MODULE$ : (Seq)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new ProcInstr[1]));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\NodeFactory$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */