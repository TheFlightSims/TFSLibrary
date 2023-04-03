/*    */ package scala.xml.persistent;
/*    */ 
/*    */ import java.io.File;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.HashSet;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.xml.Node;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0353A!\001\002\001\023\tQ1+\032;Ti>\024\030mZ3\013\005\r!\021A\0039feNL7\017^3oi*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AA\005\003\033\t\021\021cQ1dQ\026$g)\0337f'R|'/Y4f\021!y\001A!A!\002\023\001\022\001\0024jY\026\004\"!\005\f\016\003IQ!a\005\013\002\005%|'\"A\013\002\t)\fg/Y\005\003/I\021AAR5mK\")\021\004\001C\0015\0051A(\0338jiz\"\"a\007\017\021\005-\001\001\"B\b\031\001\004\001\002b\002\020\001\001\004%IaH\001\007i\",7+\032;\026\003\001\0022!\t\024)\033\005\021#BA\022%\003\035iW\017^1cY\026T!!\n\004\002\025\r|G\016\\3di&|g.\003\002(E\t9\001*Y:i'\026$\bCA\025+\033\005!\021BA\026\005\005\021qu\016Z3\t\0175\002\001\031!C\005]\005QA\017[3TKR|F%Z9\025\005=\032\004C\001\0312\033\0051\021B\001\032\007\005\021)f.\033;\t\017Qb\023\021!a\001A\005\031\001\020J\031\t\rY\002\001\025)\003!\003\035!\b.Z*fi\002BQ\001\017\001\005\002e\n\001\002\n9mkN$S-\035\013\003_iBQaO\034A\002!\n\021!\032\005\006{\001!\tAP\001\nI5Lg.^:%KF$\"aL \t\013mb\004\031\001\025\t\013\005\003A\021\001\"\002\0139|G-Z:\026\003\r\0032\001R#)\033\005!\023B\001$%\005!IE/\032:bi>\024\b")
/*    */ public class SetStorage extends CachedFileStorage {
/*    */   private HashSet<Node> scala$xml$persistent$SetStorage$$theSet;
/*    */   
/*    */   public SetStorage(File file) {
/* 21 */     super(file);
/* 23 */     this.scala$xml$persistent$SetStorage$$theSet = new HashSet();
/* 28 */     Iterator<Node> it = initialNodes();
/* 29 */     dirty_$eq(it.hasNext());
/* 30 */     it.foreach((Function1)new SetStorage$$anonfun$1(this));
/*    */   }
/*    */   
/*    */   public HashSet<Node> scala$xml$persistent$SetStorage$$theSet() {
/*    */     return this.scala$xml$persistent$SetStorage$$theSet;
/*    */   }
/*    */   
/*    */   private void scala$xml$persistent$SetStorage$$theSet_$eq(HashSet<Node> x$1) {
/*    */     this.scala$xml$persistent$SetStorage$$theSet = x$1;
/*    */   }
/*    */   
/*    */   public class SetStorage$$anonfun$1 extends AbstractFunction1<Node, HashSet<Node>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public SetStorage$$anonfun$1(SetStorage $outer) {}
/*    */     
/*    */     public final HashSet<Node> apply(Node x) {
/* 31 */       return this.$outer.scala$xml$persistent$SetStorage$$theSet().$plus$eq(x);
/*    */     }
/*    */   }
/*    */   
/*    */   public void $plus$eq(Node e) {
/* 37 */     synchronized (this) {
/* 37 */       dirty_$eq(true);
/* 37 */       scala$xml$persistent$SetStorage$$theSet().$plus$eq(e);
/*    */       /* monitor exit ThisExpression{ObjectType{scala/xml/persistent/SetStorage}} */
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void $minus$eq(Node e) {
/* 39 */     synchronized (this) {
/* 39 */       dirty_$eq(true);
/* 39 */       scala$xml$persistent$SetStorage$$theSet().$minus$eq(e);
/*    */       /* monitor exit ThisExpression{ObjectType{scala/xml/persistent/SetStorage}} */
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized Iterator<Node> nodes() {
/* 41 */     return scala$xml$persistent$SetStorage$$theSet().iterator();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\persistent\SetStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */