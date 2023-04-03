/*    */ package scala.text;
/*    */ 
/*    */ import java.io.Writer;
/*    */ import scala.MatchError;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y3Q!\001\002\002\002\035\021\001\002R8dk6,g\016\036\006\003\007\021\tA\001^3yi*\tQ!A\003tG\006d\027m\001\001\024\005\001A\001CA\005\013\033\005!\021BA\006\005\005\031\te.\037*fM\")Q\002\001C\001\035\0051A(\0338jiz\"\022a\004\t\003!\001i\021A\001\005\006%\001!\taE\001\rI\r|Gn\0348%G>dwN\034\013\003\037QAQ!F\tA\002=\t!\001\0333\t\013I\001A\021A\f\025\005=A\002\"B\013\027\001\004I\002C\001\016\036\035\tI1$\003\002\035\t\0051\001K]3eK\032L!AH\020\003\rM#(/\0338h\025\taB\001C\003\"\001\021\005!%\001\t%G>dwN\034\023eSZ$3m\0347p]R\021qb\t\005\006+\001\002\ra\004\005\006C\001!\t!\n\013\003\037\031BQ!\006\023A\002eAQ\001\013\001\005\002%\naAZ8s[\006$Hc\001\026.eA\021\021bK\005\003Y\021\021A!\0268ji\")af\na\001_\005)q/\0333uQB\021\021\002M\005\003c\021\0211!\0238u\021\025\031t\0051\0015\003\0319(/\033;feB\021QGO\007\002m)\021q\007O\001\003S>T\021!O\001\005U\0064\030-\003\002<m\t1qK]5uKJ<Q!\020\002\t\002y\n\001\002R8dk6,g\016\036\t\003!}2Q!\001\002\t\002\001\033\"a\020\005\t\0135yD\021\001\"\025\003yBQ\001R \005\002\025\013Q!Z7qif,\022A\022\b\003!\035K!\001\023\002\002\r\021{7MT5m\021\025Qu\b\"\001L\003\025\021'/Z1l+\005aeB\001\tN\023\tq%!\001\005E_\016\024%/Z1l\021\025\031q\b\"\001Q)\ty\021\013C\003S\037\002\007\021$A\001t\021\025!v\b\"\001V\003\0259'o\\;q)\tya\013C\003X'\002\007q\"A\001e\021\025Iv\b\"\001[\003\021qWm\035;\025\007=YV\fC\003]1\002\007q&A\001j\021\0259\006\f1\001\020\001")
/*    */ public abstract class Document {
/*    */   public Document $colon$colon(Document hd) {
/* 28 */     return new DocCons(hd, this);
/*    */   }
/*    */   
/*    */   public Document $colon$colon(String hd) {
/* 29 */     return new DocCons(new DocText(hd), this);
/*    */   }
/*    */   
/*    */   public Document $colon$div$colon(Document hd) {
/* 30 */     DocBreak$ docBreak$ = DocBreak$.MODULE$;
/* 30 */     return $colon$colon(docBreak$).$colon$colon(hd);
/*    */   }
/*    */   
/*    */   public Document $colon$div$colon(String hd) {
/* 31 */     DocBreak$ docBreak$ = DocBreak$.MODULE$;
/* 31 */     return $colon$colon(docBreak$).$colon$colon(hd);
/*    */   }
/*    */   
/*    */   private final boolean fits$1(int w, List state) {
/*    */     while (true) {
/* 45 */       boolean bool = false;
/* 45 */       Object object = null;
/*    */     } 
/*    */     throw new MatchError(state);
/*    */   }
/*    */   
/*    */   private final void spaces$1(int n, Writer writer$1) {
/* 62 */     int rem = n;
/* 63 */     while (rem >= 16) {
/* 63 */       writer$1.write("                ");
/* 63 */       rem -= 16;
/*    */     } 
/* 64 */     if (rem >= 8) {
/* 64 */       writer$1.write("        ");
/* 64 */       rem -= 8;
/*    */     } 
/* 65 */     if (rem >= 4) {
/* 65 */       writer$1.write("    ");
/* 65 */       rem -= 4;
/*    */     } 
/* 66 */     if (rem >= 2) {
/* 66 */       writer$1.write("  ");
/* 66 */       rem -= 2;
/*    */     } 
/* 67 */     if (rem == 1)
/* 67 */       writer$1.write(" "); 
/*    */   }
/*    */   
/*    */   private final void fmt$1(int k, List state, int width$1, Writer writer$1) {
/* 70 */     for (;; some = List$.MODULE$.unapplySeq((Seq)state)) {
/* 72 */       boolean bool = false;
/* 72 */       Object object = null;
/*    */       continue;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void format(int width, Writer writer) {
/* 95 */     Tuple3 tuple3 = new Tuple3(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToBoolean(false), new DocGroup(this));
/* 95 */     fmt$1(0, Nil$.MODULE$.$colon$colon(tuple3), width, writer);
/*    */   }
/*    */   
/*    */   public static Document nest(int paramInt, Document paramDocument) {
/*    */     return Document$.MODULE$.nest(paramInt, paramDocument);
/*    */   }
/*    */   
/*    */   public static Document group(Document paramDocument) {
/*    */     return Document$.MODULE$.group(paramDocument);
/*    */   }
/*    */   
/*    */   public static Document text(String paramString) {
/*    */     return Document$.MODULE$.text(paramString);
/*    */   }
/*    */   
/*    */   public static DocBreak$ break() {
/*    */     return Document$.MODULE$.break();
/*    */   }
/*    */   
/*    */   public static DocNil$ empty() {
/*    */     return Document$.MODULE$.empty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\Document.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */