/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function3;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001u4A!\001\002\005\023\t!\001+Y4f\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"I\n\003\001-\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g\021!\001\002A!b\001\n\003\t\022a\0018v[V\t!\003\005\002\r'%\021AC\002\002\004\023:$\b\002\003\f\001\005\003\005\013\021\002\n\002\t9,X\016\t\005\t1\001\021\031\021)A\0063\005QQM^5eK:\034W\rJ\033\021\007iir$D\001\034\025\tab!A\004sK\032dWm\031;\n\005yY\"\001C\"mCN\034H+Y4\021\005\001\nC\002\001\003\006E\001\021\ra\t\002\002)F\021Ae\n\t\003\031\025J!A\n\004\003\0179{G\017[5oOB\021A\002K\005\003S\031\0211!\0218z\021\025Y\003\001\"\001-\003\031a\024N\\5u}Q\021Q&\r\013\003]A\0022a\f\001 \033\005\021\001\"\002\r+\001\bI\002\"\002\t+\001\004\021\002bB\032\001\005\004%i\001N\001\t!\006<WmU5{KV\tQgD\0017;\t\001\002\001\003\0049\001\001\006i!N\001\n!\006<WmU5{K\002BqA\017\001A\002\023\0051(\001\003oKb$X#\001\030\t\017u\002\001\031!C\001}\005Aa.\032=u?\022*\027\017\006\002@\005B\021A\002Q\005\003\003\032\021A!\0268ji\"91\tPA\001\002\004q\023a\001=%c!1Q\t\001Q!\n9\nQA\\3yi\002Bqa\022\001A\002\023\0051(A\003mCR,'\017C\004J\001\001\007I\021\001&\002\0231\fG/\032:`I\025\fHCA L\021\035\031\005*!AA\0029Ba!\024\001!B\023q\023A\0027bi\026\024\b\005C\004P\001\001\007I\021A\t\002\r\031LG\016\\3e\021\035\t\006\0011A\005\002I\013!BZ5mY\026$w\fJ3r)\ty4\013C\004D!\006\005\t\031\001\n\t\rU\003\001\025)\003\023\003\0351\027\016\0347fI\002Bqa\026\001A\002\023\005\001,\001\004jg2\0137\017^\013\0023B\021ABW\005\0037\032\021qAQ8pY\026\fg\016C\004^\001\001\007I\021\0010\002\025%\034H*Y:u?\022*\027\017\006\002@?\"91\tXA\001\002\004I\006BB1\001A\003&\021,A\004jg2\0137\017\036\021\t\017\r\004!\031!C\003I\006!A-\031;b+\005)\007c\001\007g?%\021qM\002\002\006\003J\024\030-\037\005\007S\002\001\013QB3\002\013\021\fG/\031\021\t\013-\004AQA\t\002\013M$\030M\035;\t\0135\004AQA\t\002\007\025tG\rC\003p\001\021\0251(\001\004mCR,7\017\036\005\006c\002!\tA]\001\006CB\004H.\037\013\003?MDQ\001\0369A\002I\tQ!\0338eKbDQA\036\001\005\006]\fq!\0313e\033>\024X\r\006\002/q\")\0210\036a\001u\006!Qn\034:f!\031a10\032\n\023%%\021AP\002\002\n\rVt7\r^5p]N\002")
/*     */ public class Page<T> {
/*     */   private final int num;
/*     */   
/*     */   private final ClassTag<T> evidence$5;
/*     */   
/*     */   private final int PageSize;
/*     */   
/*     */   private Page<T> next;
/*     */   
/*     */   private Page<T> later;
/*     */   
/*     */   private int filled;
/*     */   
/*     */   private boolean isLast;
/*     */   
/*     */   private final Object data;
/*     */   
/*     */   public int num() {
/* 209 */     return this.num;
/*     */   }
/*     */   
/*     */   public Page(int num, ClassTag<T> evidence$5) {
/* 214 */     this.next = null;
/* 217 */     this.later = this;
/* 220 */     this.filled = 0;
/* 224 */     this.isLast = false;
/* 227 */     this.data = evidence$5.newArray(4096);
/*     */   }
/*     */   
/*     */   private final int PageSize() {
/*     */     return 4096;
/*     */   }
/*     */   
/*     */   public Page<T> next() {
/*     */     return this.next;
/*     */   }
/*     */   
/*     */   public void next_$eq(Page<T> x$1) {
/*     */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public Page<T> later() {
/*     */     return this.later;
/*     */   }
/*     */   
/*     */   public void later_$eq(Page<T> x$1) {
/*     */     this.later = x$1;
/*     */   }
/*     */   
/*     */   public int filled() {
/*     */     return this.filled;
/*     */   }
/*     */   
/*     */   public void filled_$eq(int x$1) {
/*     */     this.filled = x$1;
/*     */   }
/*     */   
/*     */   public boolean isLast() {
/*     */     return this.isLast;
/*     */   }
/*     */   
/*     */   public void isLast_$eq(boolean x$1) {
/*     */     this.isLast = x$1;
/*     */   }
/*     */   
/*     */   public final Object data() {
/* 227 */     return this.data;
/*     */   }
/*     */   
/*     */   public final int start() {
/* 230 */     return num() * 4096;
/*     */   }
/*     */   
/*     */   public final int end() {
/* 234 */     return start() + filled();
/*     */   }
/*     */   
/*     */   public final Page<T> latest() {
/* 239 */     if (later().next() != null)
/* 239 */       later_$eq(later().next().latest()); 
/* 240 */     return later();
/*     */   }
/*     */   
/*     */   public T apply(int index) {
/* 246 */     if (index < start() || index - start() >= filled())
/* 246 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(index).toString()); 
/* 247 */     return (T)ScalaRunTime$.MODULE$.array_apply(data(), index - start());
/*     */   }
/*     */   
/*     */   public final Page<T> addMore(Function3 more) {
/* 254 */     while (filled() == 4096) {
/* 255 */       next_$eq(new Page(num() + 1, this.evidence$5));
/* 256 */       this = next();
/*     */     } 
/* 258 */     int count = BoxesRunTime.unboxToInt(more.apply(data(), BoxesRunTime.boxToInteger(filled()), BoxesRunTime.boxToInteger(4096 - filled())));
/* 259 */     if (count < 0) {
/* 259 */       isLast_$eq(true);
/*     */     } else {
/* 260 */       filled_$eq(filled() + count);
/*     */     } 
/* 261 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Page.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */