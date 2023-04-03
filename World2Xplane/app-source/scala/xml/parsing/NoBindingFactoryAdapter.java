/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.xml.Comment;
/*    */ import scala.xml.Elem;
/*    */ import scala.xml.Elem$;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.ProcInstr;
/*    */ import scala.xml.Text;
/*    */ import scala.xml.Text$;
/*    */ import scala.xml.factory.NodeFactory;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001-4A!\001\002\001\023\t9bj\034\"j]\022Lgn\032$bGR|'/_!eCB$XM\035\006\003\007\021\tq\001]1sg&twM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001aE\002\001\0259\001\"a\003\007\016\003\tI!!\004\002\003\035\031\0137\r^8ss\006#\027\r\035;feB\031qB\005\013\016\003AQ!!\005\003\002\017\031\f7\r^8ss&\0211\003\005\002\f\035>$WMR1di>\024\030\020\005\002\026-5\tA!\003\002\030\t\t!Q\t\\3n\021\025I\002\001\"\001\033\003\031a\024N\\5u}Q\t1\004\005\002\f\001!)Q\004\001C\001=\005\001bn\0343f\007>tG/Y5ogR+\007\020\036\013\003?\r\002\"\001I\021\016\003\031I!A\t\004\003\017\t{w\016\\3b]\")A\005\ba\001K\005)A.\0312fYB\021a%\013\b\003A\035J!\001\013\004\002\rA\023X\rZ3g\023\tQ3F\001\004TiJLgn\032\006\003Q\031AQ!\f\001\005\0229\naa\031:fCR,GC\002\0130cI:D\bC\0031Y\001\007Q%A\002qe\026DQ\001\n\027A\002\025BQa\r\027A\002Q\nQ!\031;ueN\004\"!F\033\n\005Y\"!\001C'fi\006$\025\r^1\t\013ab\003\031A\035\002\013M\034w\016]3\021\005UQ\024BA\036\005\005Aq\025-\\3ta\006\034WMQ5oI&tw\rC\003>Y\001\007a(\001\005dQ&dGM]3o!\rytI\023\b\003\001\026s!!\021#\016\003\tS!a\021\005\002\rq\022xn\034;?\023\0059\021B\001$\007\003\035\001\030mY6bO\026L!\001S%\003\007M+\027O\003\002G\rA\021QcS\005\003\031\022\021AAT8eK\")a\n\001C\001\037\006Q1M]3bi\026tu\016Z3\025\rQ\001\026KU*U\021\025\001T\n1\001&\021\025!S\n1\001&\021\025\031T\n1\0015\021\025AT\n1\001:\021\025iT\n1\001V!\rydKS\005\003/&\023A\001T5ti\")\021\f\001C\0015\006Q1M]3bi\026$V\r\037;\025\005ms\006CA\013]\023\tiFA\001\003UKb$\b\"B0Y\001\004)\023\001\002;fqRDQ!\031\001\005\002\t\fqb\031:fCR,\007K]8d\023:\034HO\035\013\004G\036L\007cA HIB\021Q#Z\005\003M\022\021\021\002\025:pG&s7\017\036:\t\013!\004\007\031A\023\002\rQ\f'oZ3u\021\025Q\007\r1\001&\003\021!\027\r^1")
/*    */ public class NoBindingFactoryAdapter extends FactoryAdapter implements NodeFactory<Elem> {
/*    */   private final boolean ignoreComments;
/*    */   
/*    */   private final boolean ignoreProcInstr;
/*    */   
/*    */   private final HashMap<Object, List<Node>> cache;
/*    */   
/*    */   public boolean ignoreComments() {
/* 18 */     return this.ignoreComments;
/*    */   }
/*    */   
/*    */   public boolean ignoreProcInstr() {
/* 18 */     return this.ignoreProcInstr;
/*    */   }
/*    */   
/*    */   public HashMap<Object, List<Elem>> cache() {
/* 18 */     return (HashMap)this.cache;
/*    */   }
/*    */   
/*    */   public void scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(boolean x$1) {
/* 18 */     this.ignoreComments = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(boolean x$1) {
/* 18 */     this.ignoreProcInstr = x$1;
/*    */   }
/*    */   
/*    */   public void scala$xml$factory$NodeFactory$_setter_$cache_$eq(HashMap<Object, List<Node>> x$1) {
/* 18 */     this.cache = x$1;
/*    */   }
/*    */   
/*    */   public Elem construct(int hash, List old, String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq children) {
/* 18 */     return (Elem)NodeFactory.class.construct(this, hash, old, pre, name, attrSeq, scope, children);
/*    */   }
/*    */   
/*    */   public boolean eqElements(Seq ch1, Seq ch2) {
/* 18 */     return NodeFactory.class.eqElements(this, ch1, ch2);
/*    */   }
/*    */   
/*    */   public boolean nodeEquals(Node n, String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq children) {
/* 18 */     return NodeFactory.class.nodeEquals(this, n, pre, name, attrSeq, scope, children);
/*    */   }
/*    */   
/*    */   public Elem makeNode(String pre, String name, MetaData attrSeq, NamespaceBinding scope, Seq children) {
/* 18 */     return (Elem)NodeFactory.class.makeNode(this, pre, name, attrSeq, scope, children);
/*    */   }
/*    */   
/*    */   public Text makeText(String s) {
/* 18 */     return NodeFactory.class.makeText(this, s);
/*    */   }
/*    */   
/*    */   public Seq<Comment> makeComment(String s) {
/* 18 */     return NodeFactory.class.makeComment(this, s);
/*    */   }
/*    */   
/*    */   public Seq<ProcInstr> makeProcInstr(String t, String s) {
/* 18 */     return NodeFactory.class.makeProcInstr(this, t, s);
/*    */   }
/*    */   
/*    */   public NoBindingFactoryAdapter() {
/* 18 */     NodeFactory.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean nodeContainsText(String label) {
/* 21 */     return true;
/*    */   }
/*    */   
/*    */   public Elem create(String pre, String label, MetaData attrs, NamespaceBinding scope, Seq children) {
/* 25 */     return Elem$.MODULE$.apply(pre, label, attrs, scope, children);
/*    */   }
/*    */   
/*    */   public Elem createNode(String pre, String label, MetaData attrs, NamespaceBinding scope, List children) {
/* 29 */     return Elem$.MODULE$.apply(pre, label, attrs, scope, (Seq)children);
/*    */   }
/*    */   
/*    */   public Text createText(String text) {
/* 32 */     return Text$.MODULE$.apply(text);
/*    */   }
/*    */   
/*    */   public Seq<ProcInstr> createProcInstr(String target, String data) {
/* 35 */     return makeProcInstr(target, data);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\NoBindingFactoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */