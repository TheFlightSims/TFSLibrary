/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class LinkedListLike$class {
/*     */   public static void $init$(LinkedListLike $this) {}
/*     */   
/*     */   public static boolean isEmpty(LinkedListLike $this) {
/*  65 */     return ($this.next() == $this);
/*     */   }
/*     */   
/*     */   public static int length(LinkedListLike $this) {
/*  70 */     return length0($this, (Seq)$this.repr(), 0);
/*     */   }
/*     */   
/*     */   private static int length0(LinkedListLike $this, Seq elem, int acc) {
/*     */     while (true) {
/*  73 */       if (((LinkedListLike)elem).isEmpty())
/*  73 */         return acc; 
/*  73 */       acc++;
/*  73 */       elem = (Seq)((LinkedListLike)elem).next();
/*  73 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object head(LinkedListLike $this) {
/*  76 */     if ($this.isEmpty())
/*  76 */       throw new NoSuchElementException(); 
/*  76 */     return 
/*  77 */       $this.elem();
/*     */   }
/*     */   
/*     */   public static Seq tail(LinkedListLike $this) {
/*  80 */     boolean bool = $this.nonEmpty();
/*  80 */     Predef$ predef$ = Predef$.MODULE$;
/*  80 */     if (bool)
/*  81 */       return (Seq)$this.next(); 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("tail of empty list").toString());
/*     */   }
/*     */   
/*     */   private static final void loop$1(LinkedListLike $this, Seq x, Seq that$1) {
/*     */     while (true) {
/* 123 */       if (((LinkedListLike)((LinkedListLike)x).next()).isEmpty()) {
/* 123 */         ((LinkedListLike)x).next_$eq(that$1);
/*     */         return;
/*     */       } 
/* 124 */       x = (Seq)((LinkedListLike)x).next();
/* 124 */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Seq append(LinkedListLike $this, Seq that) {
/* 127 */     loop$1($this, (Seq)$this.repr(), that);
/* 127 */     return $this.isEmpty() ? that : (Seq)$this.repr();
/*     */   }
/*     */   
/*     */   public static void insert(LinkedListLike $this, Seq that) {
/* 134 */     boolean bool = $this.nonEmpty();
/* 134 */     Predef$ predef$ = Predef$.MODULE$;
/* 134 */     if (bool) {
/* 135 */       if (that.nonEmpty()) {
/* 136 */         ((LinkedListLike)that).append($this.next());
/* 137 */         $this.next_$eq(that);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("insert into empty list").toString());
/*     */   }
/*     */   
/*     */   public static Seq drop(LinkedListLike $this, int n) {
/* 142 */     int i = 0;
/* 143 */     Seq these = (Seq)$this.repr();
/* 144 */     while (i < n && !((LinkedListLike)these).isEmpty()) {
/* 145 */       these = (Seq)((LinkedListLike)these).next();
/* 146 */       i++;
/*     */     } 
/* 148 */     return these;
/*     */   }
/*     */   
/*     */   private static Object atLocation(LinkedListLike $this, int n, Function1 f) {
/* 152 */     Seq loc = (Seq)$this.drop(n);
/* 153 */     if (loc.nonEmpty())
/* 153 */       return f.apply(loc); 
/* 154 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString());
/*     */   }
/*     */   
/*     */   public static Object apply(LinkedListLike<A, This> $this, int n) {
/* 157 */     return atLocation($this, n, (Function1)new LinkedListLike$$anonfun$apply$1($this));
/*     */   }
/*     */   
/*     */   public static void update(LinkedListLike $this, int n, Object x) {
/* 158 */     atLocation($this, n, (Function1)new LinkedListLike$$anonfun$update$1($this, (LinkedListLike<A, This>)x));
/*     */   }
/*     */   
/*     */   public static Option get(LinkedListLike $this, int n) {
/* 161 */     Seq loc = (Seq)$this.drop(n);
/* 162 */     return loc.nonEmpty() ? (Option)new Some(((LinkedListLike)loc).elem()) : 
/* 163 */       (Option)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public static Iterator iterator(LinkedListLike<A, This> $this) {
/* 166 */     return (Iterator)new LinkedListLike$$anon$1($this);
/*     */   }
/*     */   
/*     */   public static void foreach(LinkedListLike $this, Function1 f) {
/* 177 */     LinkedListLike these = $this;
/* 178 */     while (these.nonEmpty()) {
/* 179 */       f.apply(these.elem());
/* 180 */       these = (LinkedListLike)these.next();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Seq clone(LinkedListLike $this) {
/* 189 */     Builder bf = $this.newBuilder();
/* 190 */     bf.$plus$plus$eq((TraversableOnce)$this);
/* 191 */     return (Seq)bf.result();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedListLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */