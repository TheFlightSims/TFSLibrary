/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Map$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Set$;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.immutable.Vector$;
/*     */ import scala.collection.mutable.ArrayBuffer$;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class TraversableOnce$class {
/*     */   public static void $init$(TraversableOnce $this) {}
/*     */   
/*     */   public static List reversed(TraversableOnce $this) {
/*  98 */     ObjectRef elems = new ObjectRef(Nil$.MODULE$);
/*  99 */     $this.seq().foreach((Function1)new TraversableOnce$$anonfun$reversed$1($this, (TraversableOnce<A>)elems));
/* 100 */     return (List)elems.elem;
/*     */   }
/*     */   
/*     */   public static int size(TraversableOnce $this) {
/* 104 */     IntRef result = new IntRef(0);
/* 105 */     $this.foreach((Function1)new TraversableOnce$$anonfun$size$1($this, (TraversableOnce<A>)result));
/* 106 */     return result.elem;
/*     */   }
/*     */   
/*     */   public static boolean nonEmpty(TraversableOnce $this) {
/* 109 */     return !$this.isEmpty();
/*     */   }
/*     */   
/*     */   public static int count(TraversableOnce $this, Function1 p) {
/* 112 */     IntRef cnt = new IntRef(0);
/* 113 */     $this.foreach((Function1)new TraversableOnce$$anonfun$count$1($this, cnt, (TraversableOnce<A>)p));
/* 116 */     return cnt.elem;
/*     */   }
/*     */   
/*     */   public static Option collectFirst(TraversableOnce $this, PartialFunction pf) {
/* 130 */     Object object = new Object();
/*     */     try {
/* 131 */       $this.toIterator().foreach((Function1)new TraversableOnce$$anonfun$collectFirst$1($this, object, (TraversableOnce<A>)pf));
/*     */     } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */       NonLocalReturnControl nonLocalReturnControl1;
/*     */       if ((nonLocalReturnControl1 = null).key() == object)
/*     */         return (Option)nonLocalReturnControl1.value(); 
/*     */     } 
/* 135 */     return (Option)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public static Object $div$colon(TraversableOnce $this, Object z, Function2 op) {
/* 138 */     return $this.foldLeft(z, op);
/*     */   }
/*     */   
/*     */   public static Object $colon$bslash(TraversableOnce $this, Object z, Function2 op) {
/* 140 */     return $this.foldRight(z, op);
/*     */   }
/*     */   
/*     */   public static Object foldLeft(TraversableOnce $this, Object z, Function2 op) {
/* 143 */     ObjectRef result = new ObjectRef(z);
/* 144 */     $this.seq().foreach((Function1)new TraversableOnce$$anonfun$foldLeft$1($this, result, (TraversableOnce<A>)op));
/* 145 */     return result.elem;
/*     */   }
/*     */   
/*     */   public static Object foldRight(TraversableOnce<A> $this, Object z, Function2 op) {
/* 149 */     return $this.reversed().foldLeft(z, (Function2)new TraversableOnce$$anonfun$foldRight$1($this, (TraversableOnce<A>)op));
/*     */   }
/*     */   
/*     */   public static Object reduceLeft(TraversableOnce $this, Function2 op) {
/* 166 */     if ($this.isEmpty())
/* 167 */       throw new UnsupportedOperationException("empty.reduceLeft"); 
/* 169 */     BooleanRef first = new BooleanRef(true);
/* 170 */     ObjectRef acc = new ObjectRef(BoxesRunTime.boxToInteger(0));
/* 172 */     $this.foreach((Function1)new TraversableOnce$$anonfun$reduceLeft$1($this, first, acc, (TraversableOnce<A>)op));
/* 179 */     return acc.elem;
/*     */   }
/*     */   
/*     */   public static Object reduceRight(TraversableOnce<A> $this, Function2 op) {
/* 183 */     if ($this.isEmpty())
/* 184 */       throw new UnsupportedOperationException("empty.reduceRight"); 
/* 186 */     return $this.reversed().reduceLeft((Function2)new TraversableOnce$$anonfun$reduceRight$1($this, (TraversableOnce<A>)op));
/*     */   }
/*     */   
/*     */   public static Option reduceLeftOption(TraversableOnce $this, Function2 op) {
/* 190 */     return $this.isEmpty() ? (Option)None$.MODULE$ : (Option)new Some($this.reduceLeft(op));
/*     */   }
/*     */   
/*     */   public static Option reduceRightOption(TraversableOnce $this, Function2 op) {
/* 193 */     return $this.isEmpty() ? (Option)None$.MODULE$ : (Option)new Some($this.reduceRight(op));
/*     */   }
/*     */   
/*     */   public static Object reduce(TraversableOnce $this, Function2 op) {
/* 195 */     return $this.reduceLeft(op);
/*     */   }
/*     */   
/*     */   public static Option reduceOption(TraversableOnce $this, Function2 op) {
/* 197 */     return $this.reduceLeftOption(op);
/*     */   }
/*     */   
/*     */   public static Object fold(TraversableOnce $this, Object z, Function2 op) {
/* 199 */     return $this.foldLeft(z, op);
/*     */   }
/*     */   
/*     */   public static Object aggregate(TraversableOnce $this, Object z, Function2 seqop, Function2 combop) {
/* 201 */     return $this.foldLeft(z, seqop);
/*     */   }
/*     */   
/*     */   public static Object sum(TraversableOnce $this, Numeric num) {
/* 203 */     return $this.foldLeft(num.zero(), (Function2)new TraversableOnce$$anonfun$sum$1($this, (TraversableOnce<A>)num));
/*     */   }
/*     */   
/*     */   public static Object product(TraversableOnce $this, Numeric num) {
/* 205 */     return $this.foldLeft(num.one(), (Function2)new TraversableOnce$$anonfun$product$1($this, (TraversableOnce<A>)num));
/*     */   }
/*     */   
/*     */   public static Object min(TraversableOnce $this, Ordering cmp) {
/* 208 */     if ($this.isEmpty())
/* 209 */       throw new UnsupportedOperationException("empty.min"); 
/* 211 */     return $this.reduceLeft((Function2)new TraversableOnce$$anonfun$min$1($this, (TraversableOnce<A>)cmp));
/*     */   }
/*     */   
/*     */   public static Object max(TraversableOnce $this, Ordering cmp) {
/* 215 */     if ($this.isEmpty())
/* 216 */       throw new UnsupportedOperationException("empty.max"); 
/* 218 */     return $this.reduceLeft((Function2)new TraversableOnce$$anonfun$max$1($this, (TraversableOnce<A>)cmp));
/*     */   }
/*     */   
/*     */   public static Object maxBy(TraversableOnce $this, Function1 f, Ordering cmp) {
/* 222 */     if ($this.isEmpty())
/* 223 */       throw new UnsupportedOperationException("empty.maxBy"); 
/* 225 */     return $this.reduceLeft((Function2)new TraversableOnce$$anonfun$maxBy$1($this, f, (TraversableOnce<A>)cmp));
/*     */   }
/*     */   
/*     */   public static Object minBy(TraversableOnce $this, Function1 f, Ordering cmp) {
/* 228 */     if ($this.isEmpty())
/* 229 */       throw new UnsupportedOperationException("empty.minBy"); 
/* 231 */     return $this.reduceLeft((Function2)new TraversableOnce$$anonfun$minBy$1($this, f, (TraversableOnce<A>)cmp));
/*     */   }
/*     */   
/*     */   public static void copyToBuffer(TraversableOnce $this, Buffer dest) {
/* 238 */     dest.$plus$plus$eq($this.seq());
/*     */   }
/*     */   
/*     */   public static void copyToArray(TraversableOnce $this, Object xs, int start) {
/* 241 */     $this.copyToArray(xs, start, ScalaRunTime$.MODULE$.array_length(xs) - start);
/*     */   }
/*     */   
/*     */   public static void copyToArray(TraversableOnce $this, Object xs) {
/* 244 */     $this.copyToArray(xs, 0, ScalaRunTime$.MODULE$.array_length(xs));
/*     */   }
/*     */   
/*     */   public static Object toArray(TraversableOnce $this, ClassTag evidence$1) {
/* 248 */     Object result = evidence$1.newArray($this.size());
/* 249 */     $this.copyToArray(result, 0);
/* 250 */     return $this.isTraversableAgain() ? result : 
/*     */       
/* 252 */       $this.toBuffer().toArray(evidence$1);
/*     */   }
/*     */   
/*     */   public static List toList(TraversableOnce $this) {
/* 257 */     return (List)$this.to(List$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Iterable toIterable(TraversableOnce $this) {
/* 259 */     return (Iterable)$this.toStream();
/*     */   }
/*     */   
/*     */   public static Seq toSeq(TraversableOnce $this) {
/* 261 */     return (Seq)$this.toStream();
/*     */   }
/*     */   
/*     */   public static IndexedSeq toIndexedSeq(TraversableOnce $this) {
/* 263 */     return (IndexedSeq)$this.to(Predef$.MODULE$.fallbackStringCanBuildFrom());
/*     */   }
/*     */   
/*     */   public static Buffer toBuffer(TraversableOnce $this) {
/* 265 */     return (Buffer)$this.to(ArrayBuffer$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Set toSet(TraversableOnce $this) {
/* 267 */     return (Set)$this.to(Set$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Vector toVector(TraversableOnce $this) {
/* 269 */     return (Vector)$this.to(Vector$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public static Object to(TraversableOnce $this, CanBuildFrom cbf) {
/* 272 */     Builder b = cbf.apply();
/* 273 */     b.$plus$plus$eq($this.seq());
/* 274 */     return b.result();
/*     */   }
/*     */   
/*     */   public static Map toMap(TraversableOnce $this, Predef$.less.colon.less ev) {
/* 278 */     Builder b = Map$.MODULE$.newBuilder();
/* 279 */     $this.foreach((Function1)new TraversableOnce$$anonfun$toMap$1($this, b, (TraversableOnce<A>)ev));
/* 282 */     return (Map)b.result();
/*     */   }
/*     */   
/*     */   public static String mkString(TraversableOnce $this, String start, String sep, String end) {
/* 286 */     return $this.addString(new StringBuilder(), start, sep, end).toString();
/*     */   }
/*     */   
/*     */   public static String mkString(TraversableOnce $this, String sep) {
/* 288 */     return $this.mkString("", sep, "");
/*     */   }
/*     */   
/*     */   public static String mkString(TraversableOnce $this) {
/* 290 */     return $this.mkString("");
/*     */   }
/*     */   
/*     */   public static StringBuilder addString(TraversableOnce $this, StringBuilder b, String start, String sep, String end) {
/* 317 */     BooleanRef first = new BooleanRef(true);
/* 319 */     b.append(start);
/* 320 */     $this.foreach((Function1)new TraversableOnce$$anonfun$addString$1($this, first, b, (TraversableOnce<A>)sep));
/* 330 */     b.append(end);
/* 332 */     return b;
/*     */   }
/*     */   
/*     */   public static StringBuilder addString(TraversableOnce $this, StringBuilder b, String sep) {
/* 356 */     return $this.addString(b, "", sep, "");
/*     */   }
/*     */   
/*     */   public static StringBuilder addString(TraversableOnce $this, StringBuilder b) {
/* 378 */     return $this.addString(b, "");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableOnce$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */