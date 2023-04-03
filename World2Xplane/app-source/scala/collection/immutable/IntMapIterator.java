/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.sys.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0254a!\001\002\002\002\tA!AD%oi6\013\007/\023;fe\006$xN\035\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027-F\002\nEA\031\"\001\001\006\021\007-aa\"D\001\005\023\tiAA\001\tBEN$(/Y2u\023R,'/\031;peB\021q\002\005\007\001\t\025\t\002A1\001\024\005\005!6\001A\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z\021!a\002A!A!\002\023i\022AA5u!\rqr$I\007\002\005%\021\001E\001\002\007\023:$X*\0319\021\005=\021C!B\022\001\005\004\031\"!\001,\t\013\025\002A\021\001\024\002\rqJg.\033;?)\t9\003\006\005\003\037\001\005r\001\"\002\017%\001\004i\002b\002\026\001\001\004%\taK\001\006S:$W\r_\013\002YA\021Q#L\005\003]\031\0211!\0238u\021\035\001\004\0011A\005\002E\n\021\"\0338eKb|F%Z9\025\005I*\004CA\0134\023\t!dA\001\003V]&$\bb\002\0340\003\003\005\r\001L\001\004q\022\n\004B\002\035\001A\003&A&\001\004j]\022,\007\020\t\005\bu\001\001\r\021\"\001<\003\031\021WO\0324feV\tA\bE\002\026{}J!A\020\004\003\013\005\023(/Y=\021\005U\001\025BA!\007\005\031\te.\037*fM\"91\t\001a\001\n\003!\025A\0032vM\032,'o\030\023fcR\021!'\022\005\bm\t\013\t\0211\001=\021\0319\005\001)Q\005y\0059!-\0364gKJ\004\003\"B%\001\t\003Q\025a\0019paV\tQ\004C\003M\001\021\005Q*\001\003qkNDGC\001\032O\021\025y5\n1\001\036\003\005A\b\"B)\001\r\003\021\026a\002<bYV,wJ\032\013\003\035MCQ\001\026)A\002U\0131\001^5q!\r1\026,\t\b\003=]K!\001\027\002\002\r%sG/T1q\023\tQ6LA\002USBT!\001\027\002\t\013u\003A\021\0010\002\017!\f7OT3yiV\tq\f\005\002\026A&\021\021M\002\002\b\005>|G.Z1o\021\025\031\007\001\"\002e\003\021qW\r\037;\025\0039\001")
/*     */ public abstract class IntMapIterator<V, T> extends AbstractIterator<T> {
/*     */   private int index;
/*     */   
/*     */   private Object[] buffer;
/*     */   
/*     */   public IntMapIterator(IntMap<V> it) {
/*  93 */     this.index = 0;
/*  94 */     this.buffer = new Object[33];
/* 105 */     push(it);
/*     */   }
/*     */   
/*     */   public int index() {
/*     */     return this.index;
/*     */   }
/*     */   
/*     */   public void index_$eq(int x$1) {
/*     */     this.index = x$1;
/*     */   }
/*     */   
/*     */   public Object[] buffer() {
/*     */     return this.buffer;
/*     */   }
/*     */   
/*     */   public void buffer_$eq(Object[] x$1) {
/*     */     this.buffer = x$1;
/*     */   }
/*     */   
/*     */   public IntMap<V> pop() {
/*     */     index_$eq(index() - 1);
/*     */     return (IntMap<V>)buffer()[index()];
/*     */   }
/*     */   
/*     */   public void push(IntMap x) {
/*     */     buffer()[index()] = x;
/*     */     index_$eq(index() + 1);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 112 */     return (index() != 0);
/*     */   }
/*     */   
/*     */   public final T next() {
/*     */     T t;
/*     */     while (true) {
/* 115 */       boolean bool = false;
/* 115 */       IntMap.Bin<V> bin = null;
/*     */       IntMap<V> intMap = pop();
/* 115 */       bool = true;
/* 115 */       if (intMap instanceof IntMap.Bin && (bin = (IntMap.Bin)intMap).left() instanceof IntMap.Tip) {
/* 115 */         IntMap.Tip tip = (IntMap.Tip)bin.left();
/* 116 */         push(bin.right());
/*     */       } 
/*     */       if (bool) {
/* 120 */         push(bin.right());
/* 121 */         push(bin.left());
/*     */         continue;
/*     */       } 
/* 124 */       if (intMap instanceof IntMap.Tip) {
/* 124 */         IntMap.Tip<V> tip = (IntMap.Tip)intMap;
/* 124 */         t = valueOf(tip);
/*     */         break;
/*     */       } 
/* 127 */       if (IntMap.Nil$.MODULE$ == null) {
/* 127 */         if (intMap != null)
/*     */           throw new MatchError(intMap); 
/* 127 */       } else if (!IntMap.Nil$.MODULE$.equals(intMap)) {
/*     */         throw new MatchError(intMap);
/*     */       } 
/* 127 */       throw package$.MODULE$.error("Empty maps not allowed as subtrees");
/*     */     } 
/*     */     return t;
/*     */   }
/*     */   
/*     */   public abstract T valueOf(IntMap.Tip<V> paramTip);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMapIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */