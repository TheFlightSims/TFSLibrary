/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParSet$;
/*     */ import scala.collection.script.Include;
/*     */ import scala.collection.script.Message;
/*     */ import scala.collection.script.Remove;
/*     */ import scala.collection.script.Script;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public abstract class SetLike$class {
/*     */   public static void $init$(SetLike $this) {}
/*     */   
/*     */   public static Builder newBuilder(SetLike $this) {
/*  69 */     return (Builder)$this.empty();
/*     */   }
/*     */   
/*     */   public static Combiner parCombiner(SetLike $this) {
/*  71 */     return ParSet$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public static boolean add(SetLike $this, Object elem) {
/*  79 */     boolean r = $this.contains(elem);
/*  80 */     $this.$plus$eq(elem);
/*  81 */     return !r;
/*     */   }
/*     */   
/*     */   public static boolean remove(SetLike $this, Object elem) {
/*  90 */     boolean r = $this.contains(elem);
/*  91 */     $this.$minus$eq(elem);
/*  92 */     return r;
/*     */   }
/*     */   
/*     */   public static void update(SetLike $this, Object elem, boolean included) {
/* 109 */     if (included) {
/* 109 */       $this.$plus$eq(elem);
/*     */     } else {
/* 109 */       $this.$minus$eq(elem);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void retain(SetLike $this, Function1 p) {
/* 124 */     List these1 = $this.toList();
/*     */     while (true) {
/* 124 */       if (these1.isEmpty())
/*     */         return; 
/* 124 */       Object object = these1.head();
/* 124 */       BoxesRunTime.unboxToBoolean(p.apply(object)) ? BoxedUnit.UNIT : $this.$minus$eq(object);
/* 124 */       these1 = (List)these1.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void clear(SetLike<A, This> $this) {
/* 130 */     $this.foreach((Function1)new SetLike$$anonfun$clear$1($this));
/*     */   }
/*     */   
/*     */   public static Set clone(SetLike $this) {
/* 132 */     return (Set)((Growable)$this.empty()).$plus$plus$eq((TraversableOnce)((Set)$this.repr()).seq());
/*     */   }
/*     */   
/*     */   public static Set result(SetLike $this) {
/* 137 */     return (Set)$this.repr();
/*     */   }
/*     */   
/*     */   public static Set $plus(SetLike $this, Object elem) {
/* 147 */     return (Set)$this.clone().$plus$eq(elem);
/*     */   }
/*     */   
/*     */   public static Set $plus(SetLike $this, Object elem1, Object elem2, Seq elems) {
/* 162 */     return (Set)$this.clone().$plus$eq(elem1).$plus$eq(elem2).$plus$plus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Set $plus$plus(SetLike $this, GenTraversableOnce xs) {
/* 173 */     return (Set)$this.clone().$plus$plus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public static Set $minus(SetLike $this, Object elem) {
/* 181 */     return (Set)$this.clone().$minus$eq(elem);
/*     */   }
/*     */   
/*     */   public static Set $minus(SetLike $this, Object elem1, Object elem2, Seq elems) {
/* 194 */     return (Set)$this.clone().$minus$eq(elem1).$minus$eq(elem2).$minus$minus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Set $minus$minus(SetLike $this, GenTraversableOnce xs) {
/* 204 */     return (Set)$this.clone().$minus$minus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public static void $less$less(SetLike<A, This> $this, Message cmd) {
/* 212 */     if (cmd instanceof Include) {
/* 212 */       Include include = (Include)cmd;
/* 212 */       $this
/* 213 */         .$plus$eq(include.elem());
/*     */     } else {
/* 214 */       if (cmd instanceof Remove) {
/* 214 */         Remove remove = (Remove)cmd;
/* 214 */         $this.$minus$eq(remove.elem());
/*     */       } 
/* 215 */       if (cmd instanceof scala.collection.script.Reset) {
/* 215 */         $this.clear();
/*     */       } else {
/* 216 */         if (cmd instanceof Script) {
/* 216 */           Script script = (Script)cmd;
/* 216 */           script.iterator().foreach((Function1)new SetLike$$anonfun$$less$less$1($this));
/*     */           return;
/*     */         } 
/* 217 */         throw new UnsupportedOperationException((new StringBuilder()).append("message ").append(cmd).append(" not understood").toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SetLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */