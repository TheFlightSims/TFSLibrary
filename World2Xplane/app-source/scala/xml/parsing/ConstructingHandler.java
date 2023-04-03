/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.xml.Comment;
/*    */ import scala.xml.Elem$;
/*    */ import scala.xml.EntityRef;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.ProcInstr;
/*    */ import scala.xml.Text;
/*    */ import scala.xml.Text$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t4Q!\001\002\002\002%\0211cQ8ogR\024Xo\031;j]\036D\025M\0343mKJT!a\001\003\002\017A\f'o]5oO*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AA\005\003\033\t\021Q\"T1sWV\004\b*\0318eY\026\024\b\"B\b\001\t\003\001\022A\002\037j]&$h\bF\001\022!\tY\001\001C\004\024\001\t\007i\021\001\013\002\025A\024Xm]3sm\026<6+F\001\026!\t1r#D\001\007\023\tAbAA\004C_>dW-\0318\t\013i\001A\021A\016\002\t\025dW-\034\013\t9\001*c\006M\033;yA\021QDH\007\002\t%\021q\004\002\002\b\035>$WmU3r\021\025\t\023\0041\001#\003\r\001xn\035\t\003-\rJ!\001\n\004\003\007%sG\017C\003'3\001\007q%A\002qe\026\004\"\001K\026\017\005YI\023B\001\026\007\003\031\001&/\0323fM&\021A&\f\002\007'R\024\030N\\4\013\005)2\001\"B\030\032\001\0049\023!\0027bE\026d\007\"B\031\032\001\004\021\024!B1uiJ\034\bCA\0174\023\t!DA\001\005NKR\fG)\031;b\021\0251\024\0041\0018\003\031\0018oY8qKB\021Q\004O\005\003s\021\021\001CT1nKN\004\030mY3CS:$\027N\\4\t\013mJ\002\031A\013\002\013\025l\007\017^=\t\013uJ\002\031\001\017\002\0139|G-Z:\t\013}\002A\021\001!\002\023A\024xnY%ogR\024H\003B!E\013\036\003\"!\b\"\n\005\r#!!\003)s_\016Len\035;s\021\025\tc\b1\001#\021\0251e\b1\001(\003\031!\030M]4fi\")\001J\020a\001O\005\031A\017\037;\t\013)\003A\021A&\002\017\r|W.\\3oiR\031Aj\024)\021\005ui\025B\001(\005\005\035\031u.\\7f]RDQ!I%A\002\tBQ\001S%A\002\035BQA\025\001\005\002M\013\021\"\0328uSRL(+\0324\025\007Q;\006\f\005\002\036+&\021a\013\002\002\n\013:$\030\016^=SK\032DQ!I)A\002\tBQ!W)A\002\035\n\021A\034\005\0067\002!\t\001X\001\005i\026DH\017F\002^A\006\004\"!\b0\n\005}#!\001\002+fqRDQ!\t.A\002\tBQ\001\023.A\002\035\002")
/*    */ public abstract class ConstructingHandler extends MarkupHandler {
/*    */   public NodeSeq elem(int pos, String pre, String label, MetaData attrs, NamespaceBinding pscope, boolean empty, NodeSeq nodes) {
/* 25 */     return (NodeSeq)Elem$.MODULE$.apply(pre, label, attrs, pscope, empty, (Seq)nodes);
/*    */   }
/*    */   
/*    */   public ProcInstr procInstr(int pos, String target, String txt) {
/* 28 */     return new ProcInstr(target, txt);
/*    */   }
/*    */   
/*    */   public Comment comment(int pos, String txt) {
/* 30 */     return new Comment(txt);
/*    */   }
/*    */   
/*    */   public EntityRef entityRef(int pos, String n) {
/* 31 */     return new EntityRef(n);
/*    */   }
/*    */   
/*    */   public Text text(int pos, String txt) {
/* 32 */     return Text$.MODULE$.apply(txt);
/*    */   }
/*    */   
/*    */   public abstract boolean preserveWS();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\ConstructingHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */