/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0254A!\001\002\001\017\t!\021\t^8n\025\t\031A!A\002y[2T\021!B\001\006g\016\fG.Y\002\001+\tAacE\002\001\0235\001\"AC\006\016\003\tI!\001\004\002\003\027M\003XmY5bY:{G-\032\t\003\035=i\021\001B\005\003!\021\021AbU3sS\006d\027N_1cY\026D\001B\005\001\003\006\004%\taE\001\005I\006$\030-F\001\025!\t)b\003\004\001\005\r]\001AQ1\001\031\005\005\t\025CA\r\035!\tq!$\003\002\034\t\t9aj\034;iS:<\007C\001\b\036\023\tqBAA\002B]fD\001\002\t\001\003\002\003\006I\001F\001\006I\006$\030\r\t\005\006E\001!\taI\001\007y%t\027\016\036 \025\005\021*\003c\001\006\001)!)!#\ta\001)!)q\005\001C)Q\005\001\"-Y:jg\032{'\017S1tQ\016{G-Z\013\002SA\031!F\r\017\017\005-\002dB\001\0270\033\005i#B\001\030\007\003\031a$o\\8u}%\tQ!\003\0022\t\0059\001/Y2lC\036,\027BA\0325\005\r\031V-\035\006\003c\021AQA\016\001\005B]\nQb\035;sS\016$x\fJ3rI\025\fHC\001\035<!\tq\021(\003\002;\t\t9!i\\8mK\006t\007\"\002\0376\001\004i\024!B8uQ\026\024\bC\001\006?\023\ty$A\001\005FcV\fG.\033;z\021\025\t\005\001\"\021C\003!\031\027M\\#rk\006dGC\001\035D\021\025a\004\t1\001\035\021\025)\005\001\"\022G\003M!wnQ8mY\026\034GOT1nKN\004\030mY3t+\005A\004\"\002%\001\t\0132\025a\0033p)J\fgn\0354pe6DQA\023\001\005\002-\013Q\001\\1cK2,\022\001\024\t\003\033Jk\021A\024\006\003\037B\013A\001\\1oO*\t\021+\001\003kCZ\f\027BA*O\005\031\031FO]5oO\")Q\013\001C\001-\006Y!-^5mIN#(/\0338h)\t9&\f\005\002+1&\021\021\f\016\002\016'R\024\030N\\4Ck&dG-\032:\t\013m#\006\031A,\002\005M\024\007\"B/\001\t\003r\026\001\002;fqR,\022a\030\t\003A\016t!AD1\n\005\t$\021A\002)sK\022,g-\003\002TI*\021!\r\002")
/*    */ public class Atom<A> extends SpecialNode implements Serializable {
/*    */   private final A data;
/*    */   
/*    */   public A data() {
/* 17 */     return this.data;
/*    */   }
/*    */   
/*    */   public Atom(Object data) {
/* 18 */     if (data == null)
/* 19 */       throw new IllegalArgumentException((new StringBuilder()).append("cannot construct ").append(getClass().getSimpleName()).append(" with null").toString()); 
/*    */   }
/*    */   
/*    */   public Seq<Object> basisForHashCode() {
/* 21 */     return (Seq<Object>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { data() }));
/*    */   }
/*    */   
/*    */   public boolean strict_$eq$eq(Equality other) {
/*    */     boolean bool;
/* 23 */     if (other instanceof Atom) {
/* 23 */       Atom<Object> atom = (Atom)other;
/* 23 */       A a2 = (A)atom.data();
/*    */       A a1;
/* 23 */       bool = (((a1 = data()) == a2) ? true : ((a1 == null) ? false : ((a1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)a1, a2) : ((a1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)a1, a2) : a1.equals(a2))))) ? true : false;
/*    */     } else {
/* 25 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/*    */     boolean bool;
/* 28 */     if (other instanceof Atom) {
/* 28 */       bool = true;
/*    */     } else {
/* 30 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public final boolean doCollectNamespaces() {
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public final boolean doTransform() {
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public String label() {
/* 36 */     return "#PCDATA";
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 42 */     return Utility$.MODULE$.escape(data().toString(), sb);
/*    */   }
/*    */   
/*    */   public String text() {
/* 44 */     return data().toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Atom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */