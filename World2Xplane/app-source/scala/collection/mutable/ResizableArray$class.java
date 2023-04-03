/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.compat.Platform$;
/*     */ import scala.math.package$;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class ResizableArray$class {
/*     */   public static GenericCompanion companion(ResizableArray $this) {
/*  29 */     return (GenericCompanion)ResizableArray$.MODULE$;
/*     */   }
/*     */   
/*     */   public static int initialSize(ResizableArray $this) {
/*  31 */     return 16;
/*     */   }
/*     */   
/*     */   public static void $init$(ResizableArray $this) {
/*  32 */     $this.array_$eq(new Object[package$.MODULE$.max($this.initialSize(), 1)]);
/*  33 */     $this.size0_$eq(0);
/*     */   }
/*     */   
/*     */   public static int length(ResizableArray $this) {
/*  40 */     return $this.size0();
/*     */   }
/*     */   
/*     */   public static Object apply(ResizableArray $this, int idx) {
/*  43 */     if (idx >= $this.size0())
/*  43 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/*  44 */     return $this.array()[idx];
/*     */   }
/*     */   
/*     */   public static void update(ResizableArray $this, int idx, Object elem) {
/*  48 */     if (idx >= $this.size0())
/*  48 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/*  49 */     $this.array()[idx] = elem;
/*     */   }
/*     */   
/*     */   public static void foreach(ResizableArray $this, Function1 f) {
/*  53 */     int i = 0;
/*  57 */     int top = $this.size();
/*  58 */     while (i < top) {
/*  59 */       f.apply($this.array()[i]);
/*  60 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void copyToArray(ResizableArray $this, Object xs, int start, int len) {
/*  76 */     Predef$ predef$1 = Predef$.MODULE$;
/*  76 */     int i = RichInt$.MODULE$.min$extension(len, ScalaRunTime$.MODULE$.array_length(xs) - start);
/*  76 */     Predef$ predef$2 = Predef$.MODULE$;
/*  76 */     int len1 = RichInt$.MODULE$.min$extension(i, $this.length());
/*  77 */     Array$.MODULE$.copy($this.array(), 0, xs, start, len1);
/*     */   }
/*     */   
/*     */   public static void reduceToSize(ResizableArray $this, int sz) {
/*  85 */     Predef$.MODULE$.require((sz <= $this.size0()));
/*  86 */     while ($this.size0() > sz) {
/*  87 */       $this.size0_$eq($this.size0() - 1);
/*  88 */       $this.array()[$this.size0()] = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void ensureSize(ResizableArray $this, int n) {
/*  94 */     if (n > ($this.array()).length) {
/*  95 */       int newsize = ($this.array()).length * 2;
/*  96 */       while (n > newsize)
/*  97 */         newsize *= 2; 
/*  99 */       Object[] newar = new Object[newsize];
/* 100 */       int i = $this.size0();
/* 100 */       Object[] arrayOfObject1 = $this.array();
/* 100 */       Platform$ platform$ = Platform$.MODULE$;
/* 100 */       System.arraycopy(arrayOfObject1, 0, newar, 0, i);
/* 101 */       $this.array_$eq(newar);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void swap(ResizableArray $this, int a, int b) {
/* 108 */     Object h = $this.array()[a];
/* 109 */     $this.array()[a] = $this.array()[b];
/* 110 */     $this.array()[b] = h;
/*     */   }
/*     */   
/*     */   public static void copy(ResizableArray $this, int m, int n, int len) {
/* 116 */     Object[] arrayOfObject2 = $this.array(), arrayOfObject1 = $this.array();
/* 116 */     Platform$ platform$ = Platform$.MODULE$;
/* 116 */     System.arraycopy(arrayOfObject1, m, arrayOfObject2, n, len);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ResizableArray$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */