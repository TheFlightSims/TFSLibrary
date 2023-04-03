/*    */ package scala.xml.factory;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.Text;
/*    */ import scala.xml.Utility$;
/*    */ 
/*    */ public abstract class LoggedNodeFactory$class {
/*    */   public static void $init$(LoggedNodeFactory $this) {
/* 32 */     $this.scala$xml$factory$LoggedNodeFactory$_setter_$logNode_$eq(true);
/* 33 */     $this.scala$xml$factory$LoggedNodeFactory$_setter_$logText_$eq(false);
/* 34 */     $this.scala$xml$factory$LoggedNodeFactory$_setter_$logComment_$eq(false);
/* 35 */     $this.scala$xml$factory$LoggedNodeFactory$_setter_$logProcInstr_$eq(false);
/* 41 */     $this.scala$xml$factory$LoggedNodeFactory$_setter_$logCompressLevel_$eq(1);
/*    */   }
/*    */   
/*    */   public static Node makeNode(LoggedNodeFactory<Node> $this, String pre, String label, MetaData attrSeq, NamespaceBinding scope, Seq<Node> children) {
/* 48 */     if ($this.logNode())
/* 49 */       $this.log((new StringBuilder()).append("[makeNode for ").append(label).append("]").toString()); 
/* 51 */     int hash = Utility$.MODULE$.hashCode(pre, label, ScalaRunTime$.MODULE$.hash(attrSeq), ScalaRunTime$.MODULE$.hash(scope), children);
/* 61 */     if (!$this.cache().get(BoxesRunTime.boxToInteger(hash)).isEmpty() && $this.logCompressLevel() >= 1)
/* 62 */       $this.log("[cache hit !]"); 
/* 64 */     return $this.scala$xml$factory$LoggedNodeFactory$$super$makeNode(pre, label, attrSeq, scope, children);
/*    */   }
/*    */   
/*    */   public static Text makeText(LoggedNodeFactory $this, String s) {
/* 68 */     if ($this.logText())
/* 69 */       $this.log((new StringBuilder()).append("[makeText:\"").append(s).append("\"]").toString()); 
/* 70 */     return $this.scala$xml$factory$LoggedNodeFactory$$super$makeText(s);
/*    */   }
/*    */   
/*    */   public static Seq makeComment(LoggedNodeFactory $this, String s) {
/* 74 */     if ($this.logComment())
/* 75 */       $this.log((new StringBuilder()).append("[makeComment:\"").append(s).append("\"]").toString()); 
/* 76 */     return $this.scala$xml$factory$LoggedNodeFactory$$super$makeComment(s);
/*    */   }
/*    */   
/*    */   public static Seq makeProcInstr(LoggedNodeFactory $this, String t, String s) {
/* 80 */     if ($this.logProcInstr())
/* 81 */       $this.log((new StringBuilder()).append("[makeProcInstr:\"").append(t).append(" ").append(s).append("\"]").toString()); 
/* 82 */     return $this.scala$xml$factory$LoggedNodeFactory$$super$makeProcInstr(t, s);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\LoggedNodeFactory$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */