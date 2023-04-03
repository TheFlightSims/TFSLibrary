/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.immutable.PagedSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\021<Q!\001\002\t\002-\ta\002U1hK\022\034V-\035*fC\022,'O\003\002\004\t\005)\021N\0349vi*\021QAB\001\ba\006\0248/\0338h\025\t9\001\"\001\003vi&d'\"A\005\002\013M\034\027\r\\1\004\001A\021A\"D\007\002\005\031)aB\001E\001\037\tq\001+Y4fIN+\027OU3bI\026\0248CA\007\021!\t\t\"#D\001\t\023\t\031\002B\001\004B]f\024VM\032\005\006+5!\tAF\001\007y%t\027\016\036 \025\003-Aq\001G\007C\002\023\025\021$A\003F_\032\034\005.F\001\033\037\005YB$\001\016\t\rui\001\025!\004\033\003\031)uNZ\"iA\031!aB\001\001 '\tq\002\005E\002\rC\rJ!A\t\002\003\rI+\027\rZ3s!\t\tB%\003\002&\021\t!1\t[1s\021!9cD!A!\002\023A\023aA:fcB\031\021FL\022\016\003)R!a\013\027\002\023%lW.\036;bE2,'BA\027\t\003)\031w\016\0347fGRLwN\\\005\003_)\022\001\002U1hK\022\034V-\035\005\tcy\021)\031!C!e\0051qN\0324tKR,\022a\r\t\003#QJ!!\016\005\003\007%sG\017\003\0058=\t\005\t\025!\0034\003\035ygMZ:fi\002BQ!\006\020\005\002e\"2AO\036=!\taa\004C\003(q\001\007\001\006C\0032q\001\0071\007\003\005?=!\025\r\021\"\021@\003\031\031x.\036:dKV\t\001\t\005\002B\r6\t!I\003\002D\t\006!A.\0318h\025\005)\025\001\0026bm\006L!a\022\"\003\031\rC\027M]*fcV,gnY3\t\021%s\002\022!Q!\n\001\013qa]8ve\016,\007\005C\003\026=\021\0051\n\006\002;\031\")qE\023a\001Q!)aJ\bC\001\037\006)a-\033:tiV\t1\005C\003R=\021\005!+\001\003sKN$X#\001\036\t\013QsB\021A+\002\007A|7/F\001W!\taq+\003\002Y\005\tA\001k\\:ji&|g\016C\003[=\021\0051,A\003bi\026sG-F\001]!\t\tR,\003\002_\021\t9!i\\8mK\006t\007\"\0021\037\t\003\n\027\001\0023s_B$\"A\0172\t\013\r|\006\031A\032\002\0039\004")
/*    */ public class PagedSeqReader extends Reader<Object> {
/*    */   private final PagedSeq<Object> seq;
/*    */   
/*    */   private final int offset;
/*    */   
/*    */   private CharSequence source;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   public PagedSeqReader(PagedSeq<Object> seq, int offset) {}
/*    */   
/*    */   public int offset() {
/* 32 */     return this.offset;
/*    */   }
/*    */   
/*    */   private CharSequence source$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (!this.bitmap$0) {
/* 35 */         this.source = Predef$.MODULE$.seqToCharSequence((IndexedSeq)this.seq);
/* 35 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/input/PagedSeqReader}} */
/* 35 */       return this.source;
/*    */     } 
/*    */   }
/*    */   
/*    */   public CharSequence source() {
/* 35 */     return this.bitmap$0 ? this.source : source$lzycompute();
/*    */   }
/*    */   
/*    */   public PagedSeqReader(PagedSeq<Object> seq) {
/* 40 */     this(seq, 0);
/*    */   }
/*    */   
/*    */   public char first() {
/* 45 */     return this.seq.isDefinedAt(offset()) ? BoxesRunTime.unboxToChar(this.seq.apply(offset())) : '\032';
/*    */   }
/*    */   
/*    */   public PagedSeqReader rest() {
/* 53 */     return this.seq.isDefinedAt(offset()) ? new PagedSeqReader(this.seq, offset() + 1) : 
/* 54 */       this;
/*    */   }
/*    */   
/*    */   public Position pos() {
/* 58 */     return new OffsetPosition(source(), offset());
/*    */   }
/*    */   
/*    */   public boolean atEnd() {
/* 63 */     return !this.seq.isDefinedAt(offset());
/*    */   }
/*    */   
/*    */   public PagedSeqReader drop(int n) {
/* 69 */     return new PagedSeqReader(this.seq, offset() + n);
/*    */   }
/*    */   
/*    */   public static char EofCh() {
/*    */     return PagedSeqReader$.MODULE$.EofCh();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\PagedSeqReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */