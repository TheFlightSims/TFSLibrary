/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.sys.package$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0254a!\001\002\002\002\tA!a\004'p]\036l\025\r]%uKJ\fGo\034:\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\013\004\023\t\0022C\001\001\013!\rYABD\007\002\t%\021Q\002\002\002\021\003\n\034HO]1di&#XM]1u_J\004\"a\004\t\r\001\021)\021\003\001b\001'\t\tAk\001\001\022\005QA\002CA\013\027\033\0051\021BA\f\007\005\035qu\016\0365j]\036\004\"!F\r\n\005i1!aA!os\"AA\004\001B\001B\003%Q$\001\002jiB\031adH\021\016\003\tI!\001\t\002\003\0171{gnZ'baB\021qB\t\003\006G\001\021\ra\005\002\002-\")Q\005\001C\001M\0051A(\0338jiz\"\"a\n\025\021\ty\001\021E\004\005\0069\021\002\r!\b\005\bU\001\001\r\021\"\001,\003\025Ig\016Z3y+\005a\003CA\013.\023\tqcAA\002J]RDq\001\r\001A\002\023\005\021'A\005j]\022,\007p\030\023fcR\021!'\016\t\003+MJ!\001\016\004\003\tUs\027\016\036\005\bm=\n\t\0211\001-\003\rAH%\r\005\007q\001\001\013\025\002\027\002\r%tG-\032=!\021\035Q\004\0011A\005\002m\naAY;gM\026\024X#\001\037\021\007Uit(\003\002?\r\t)\021I\035:bsB\021Q\003Q\005\003\003\032\021a!\0218z%\0264\007bB\"\001\001\004%\t\001R\001\013EV4g-\032:`I\025\fHC\001\032F\021\0351$)!AA\002qBaa\022\001!B\023a\024a\0022vM\032,'\017\t\005\006\023\002!\tAS\001\004a>\004H#A\017\t\0131\003A\021A'\002\tA,8\017\033\013\003e9CQaT&A\002u\t\021\001\037\005\006#\0021\tAU\001\bm\006dW/Z(g)\tq1\013C\003U!\002\007Q+A\002uSB\0042AV-\"\035\tqr+\003\002Y\005\0059Aj\0348h\033\006\004\030B\001.\\\005\r!\026\016\035\006\0031\nAQ!\030\001\005\002y\013q\001[1t\035\026DH/F\001`!\t)\002-\003\002b\r\t9!i\\8mK\006t\007\"B2\001\t\013!\027\001\0028fqR$\022A\004")
/*     */ public abstract class LongMapIterator<V, T> extends AbstractIterator<T> {
/*     */   private int index;
/*     */   
/*     */   private Object[] buffer;
/*     */   
/*     */   public LongMapIterator(LongMap<V> it) {
/*  89 */     this.index = 0;
/*  90 */     this.buffer = new Object[65];
/* 101 */     push(it);
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
/*     */   public LongMap<V> pop() {
/*     */     index_$eq(index() - 1);
/*     */     return (LongMap<V>)buffer()[index()];
/*     */   }
/*     */   
/*     */   public void push(LongMap x) {
/*     */     buffer()[index()] = x;
/*     */     index_$eq(index() + 1);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 108 */     return (index() != 0);
/*     */   }
/*     */   
/*     */   public final T next() {
/*     */     T t;
/*     */     while (true) {
/* 111 */       boolean bool = false;
/* 111 */       LongMap.Bin<V> bin = null;
/*     */       LongMap<V> longMap = pop();
/* 111 */       bool = true;
/* 111 */       if (longMap instanceof LongMap.Bin && (bin = (LongMap.Bin)longMap).left() instanceof LongMap.Tip) {
/* 111 */         LongMap.Tip tip = (LongMap.Tip)bin.left();
/* 112 */         push(bin.right());
/*     */       } 
/*     */       if (bool) {
/* 116 */         push(bin.right());
/* 117 */         push(bin.left());
/*     */         continue;
/*     */       } 
/* 120 */       if (longMap instanceof LongMap.Tip) {
/* 120 */         LongMap.Tip<V> tip = (LongMap.Tip)longMap;
/* 120 */         t = valueOf(tip);
/*     */         break;
/*     */       } 
/* 123 */       if (LongMap.Nil$.MODULE$ == null) {
/* 123 */         if (longMap != null)
/*     */           throw new MatchError(longMap); 
/* 123 */       } else if (!LongMap.Nil$.MODULE$.equals(longMap)) {
/*     */         throw new MatchError(longMap);
/*     */       } 
/* 123 */       throw package$.MODULE$.error("Empty maps not allowed as subtrees");
/*     */     } 
/*     */     return t;
/*     */   }
/*     */   
/*     */   public abstract T valueOf(LongMap.Tip<V> paramTip);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */