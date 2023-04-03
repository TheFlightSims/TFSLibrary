/*    */ package scala.collection.convert;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Dictionary;
/*    */ import java.util.Enumeration;
/*    */ import scala.Function0;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.JavaConversions$;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\035b\001C\001\003!\003\r\t\001\002\005\003\025\021+7m\034:bi>\0248O\003\002\004\t\00591m\0348wKJ$(BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mCN\021\001!\003\t\003\025-i\021AB\005\003\031\031\021a!\0218z%\0264\007\"\002\b\001\t\003\001\022A\002\023j]&$He\001\001\025\003E\001\"A\003\n\n\005M1!\001B+oSR4A!\006\001\001-\t1\021i\035&bm\006,\"aF\020\024\005QI\001\002C\r\025\005\003%\013\021\002\016\002\005=\004\bc\001\006\034;%\021AD\002\002\ty\tLh.Y7f}A\021ad\b\007\001\t\025\001CC1\001\"\005\005\t\025C\001\022&!\tQ1%\003\002%\r\t9aj\034;iS:<\007C\001\006'\023\t9cAA\002B]fDQ!\013\013\005\002)\na\001P5oSRtDCA\026.!\raC#H\007\002\001!1\021\004\013CA\002iAQa\f\013\005\002A\na!Y:KCZ\fW#A\017\007\tI\002\001a\r\002\b\003N\0346-\0317b+\t!\004h\005\0022\023!A\021$\rB\001J\003%a\007E\002\0137]\002\"A\b\035\005\013\001\n$\031A\021\t\013%\nD\021\001\036\025\005mb\004c\001\0272o!1\021$\017CA\002YBQAP\031\005\002}\nq!Y:TG\006d\027-F\0018\r\021\t\005\001\001\"\003!\005\033(*\031<b\007>dG.Z2uS>tWCA\"L'\t\001\025\002\003\005F\001\n\005\t\025!\003G\003\005I\007cA$I\0256\tA!\003\002J\t\tA\021\n^3sC\ndW\r\005\002\037\027\022)\001\005\021b\001C!)\021\006\021C\001\033R\021aj\024\t\004Y\001S\005\"B#M\001\0041\005\"B)A\t\003\021\026\001E1t\025\0064\030mQ8mY\026\034G/[8o+\005\031\006c\001+Z\0256\tQK\003\002W/\006!Q\017^5m\025\005A\026\001\0026bm\006L!AW+\003\025\r{G\016\\3di&|gN\002\003]\001\001i&!E!t\025\0064\030-\0228v[\026\024\030\r^5p]V\021a\fZ\n\0037&A\001\"R.\003\002\003\006I\001\031\t\004\017\006\034\027B\0012\005\005!IE/\032:bi>\024\bC\001\020e\t\025\0013L1\001\"\021\025I3\f\"\001g)\t9\007\016E\002-7\016DQ!R3A\002\001DQA[.\005\002-\f\021#Y:KCZ\fWI\\;nKJ\fG/[8o+\005a\007c\001+nG&\021a.\026\002\f\013:,X.\032:bi&|gN\002\003q\001\001\t(\001E!t\025\0064\030\rR5di&|g.\031:z+\r\021HP`\n\003_&A\001\002^8\003\002\003\006I!^\001\002[B!a/_>~\033\0059(B\001=\005\003\035iW\017^1cY\026L!A_<\003\0075\013\007\017\005\002\037y\022)\001e\034b\001CA\021aD \003\006>\024\r!\t\002\002\005\"1\021f\034C\001\003\007!B!!\002\002\bA!Af\\>~\021\031!\030\021\001a\001k\"9\0211B8\005\002\0055\021\001E1t\025\0064\030\rR5di&|g.\031:z+\t\ty\001E\003U\003#YX0C\002\002\024U\023!\002R5di&|g.\031:z\017!\t9B\001E\001\t\005e\021A\003#fG>\024\030\r^8sgB!\0211DA\017\033\005\021aaB\001\003\021\003!\021qD\n\006\003;I\021\021\005\t\004\0037\001\001bB\025\002\036\021\005\021Q\005\013\003\0033\001")
/*    */ public interface Decorators {
/*    */   public class AsJava<A> {
/*    */     private final Function0<A> op;
/*    */     
/*    */     public AsJava(Decorators $outer, Function0<A> op) {}
/*    */     
/*    */     public A asJava() {
/* 18 */       return (A)this.op.apply();
/*    */     }
/*    */   }
/*    */   
/*    */   public class AsScala<A> {
/*    */     private final Function0<A> op;
/*    */     
/*    */     public AsScala(Decorators $outer, Function0<A> op) {}
/*    */     
/*    */     public A asScala() {
/* 24 */       return (A)this.op.apply();
/*    */     }
/*    */   }
/*    */   
/*    */   public class AsJavaCollection<A> {
/*    */     private final Iterable<A> i;
/*    */     
/*    */     public AsJavaCollection(Decorators $outer, Iterable<A> i) {}
/*    */     
/*    */     public Collection<A> asJavaCollection() {
/* 30 */       return JavaConversions$.MODULE$.asJavaCollection(this.i);
/*    */     }
/*    */   }
/*    */   
/*    */   public class AsJavaEnumeration<A> {
/*    */     private final Iterator<A> i;
/*    */     
/*    */     public AsJavaEnumeration(Decorators $outer, Iterator<A> i) {}
/*    */     
/*    */     public Enumeration<A> asJavaEnumeration() {
/* 36 */       return JavaConversions$.MODULE$.asJavaEnumeration(this.i);
/*    */     }
/*    */   }
/*    */   
/*    */   public class AsJavaDictionary<A, B> {
/*    */     private final Map<A, B> m;
/*    */     
/*    */     public AsJavaDictionary(Decorators $outer, Map<A, B> m) {}
/*    */     
/*    */     public Dictionary<A, B> asJavaDictionary() {
/* 42 */       return JavaConversions$.MODULE$.asJavaDictionary(this.m);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\Decorators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */