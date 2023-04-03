/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\0015;Q!\001\002\t\002\035\t!\002V3yi\n+hMZ3s\025\t\031A!A\002y[2T\021!B\001\006g\016\fG.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005)!V\r\037;Ck\0324WM]\n\003\0231\001\"!\004\b\016\003\021I!a\004\003\003\r\005s\027PU3g\021\025\t\022\002\"\001\023\003\031a\024N\\5u}Q\tq\001C\003\025\023\021\005Q#\001\006ge>l7\013\036:j]\036$\"A\006#\021\005!9b\001\002\006\003\001a\031\"a\006\007\t\013E9B\021\001\016\025\003YAq\001H\fC\002\023\005Q$\001\002tEV\ta\004\005\002 I5\t\001E\003\002\"E\0059Q.\036;bE2,'BA\022\005\003)\031w\016\0347fGRLwN\\\005\003K\001\022Qb\025;sS:<')^5mI\026\024\bBB\024\030A\003%a$A\002tE\002BQ!K\f\005\002)\na!\0319qK:$GCA\026-\033\0059\002\"B\027)\001\004q\023AA2t!\rysG\017\b\003aUr!!\r\033\016\003IR!a\r\004\002\rq\022xn\034;?\023\005)\021B\001\034\005\003\035\001\030mY6bO\026L!\001O\035\003\007M+\027O\003\0027\tA\021QbO\005\003y\021\021Aa\0215be\")ah\006C\001\0051Ao\034+fqR,\022\001\021\t\004_]\n\005C\001\005C\023\t\031%A\001\003UKb$\b\"B#\024\001\0041\025aA:ueB\021qI\023\b\003\033!K!!\023\003\002\rA\023X\rZ3g\023\tYEJ\001\004TiJLgn\032\006\003\023\022\001")
/*    */ public class TextBuffer {
/* 25 */   private final StringBuilder sb = new StringBuilder();
/*    */   
/*    */   public static TextBuffer fromString(String paramString) {
/*    */     return TextBuffer$.MODULE$.fromString(paramString);
/*    */   }
/*    */   
/*    */   public StringBuilder sb() {
/* 25 */     return this.sb;
/*    */   }
/*    */   
/*    */   public TextBuffer append(Seq cs) {
/* 30 */     cs.foreach((Function1)new TextBuffer$$anonfun$append$1(this));
/* 34 */     return this;
/*    */   }
/*    */   
/*    */   public class TextBuffer$$anonfun$append$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public TextBuffer$$anonfun$append$1(TextBuffer $outer) {}
/*    */     
/*    */     public final Object apply(char c) {
/*    */       return Utility$.MODULE$.isSpace(c) ? ((!this.$outer.sb().isEmpty() && Utility$.MODULE$.isSpace(BoxesRunTime.unboxToChar(this.$outer.sb().last()))) ? BoxedUnit.UNIT : this.$outer.sb().append(' ')) : this.$outer.sb().append(c);
/*    */     }
/*    */   }
/*    */   
/*    */   public Seq<Text> toText() {
/* 41 */     String str = sb().toString().trim();
/* 42 */     if ("" == null) {
/* 42 */       "";
/* 42 */       if (str != null) {
/* 43 */         (new Text[1])[0] = Text$.MODULE$.apply(str);
/* 43 */         Seq seq = (Seq)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Text[1]));
/*    */       } 
/*    */     } else {
/*    */       if ("".equals(str))
/*    */         return (Seq<Text>)Nil$.MODULE$; 
/* 43 */       (new Text[1])[0] = Text$.MODULE$.apply(str);
/* 43 */       Seq seq = (Seq)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Text[1]));
/*    */     } 
/*    */     return (Seq<Text>)Nil$.MODULE$;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\TextBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */