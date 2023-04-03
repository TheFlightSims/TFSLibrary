/*     */ package scala.xml;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ 
/*     */ public final class MetaData$ implements Serializable {
/*     */   public static final MetaData$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  19 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private MetaData$() {
/*  19 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final MetaData concatenate(MetaData attribs, MetaData new_tail) {
/*     */     while (true) {
/*  30 */       if (attribs == Null$.MODULE$)
/*  30 */         return new_tail; 
/*  31 */       new_tail = attribs.copy(new_tail);
/*  31 */       attribs = attribs.next();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String key$lzycompute$1(NamespaceBinding scope$1, MetaData md$1, ObjectRef key$lzy$1, VolatileByteRef bitmap$0$1) {
/*  39 */     synchronized (this) {
/*  39 */       if ((byte)(bitmap$0$1.elem & 0x1) == 
/*     */         
/* 188 */         0) {
/*     */         key$lzy$1.elem = getUniversalKey(md$1, scope$1);
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/xml/MetaData$}} */
/*     */       return (String)key$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String key$1(NamespaceBinding scope$1, MetaData md$1, ObjectRef key$lzy$1, VolatileByteRef bitmap$0$1) {
/* 188 */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? key$lzycompute$1(scope$1, md$1, key$lzy$1, bitmap$0$1) : (String)key$lzy$1.elem;
/*     */   }
/*     */   
/*     */   public MetaData normalize(MetaData attribs, NamespaceBinding scope) {
/*     */     return iterate$1(attribs, Null$.MODULE$, (Set)scala.Predef$.MODULE$.Set().apply((Seq)scala.collection.immutable.Nil$.MODULE$), scope);
/*     */   }
/*     */   
/*     */   public String getUniversalKey(MetaData attrib, NamespaceBinding scope) {
/*     */     if (attrib instanceof PrefixedAttribute) {
/*     */       PrefixedAttribute prefixedAttribute = (PrefixedAttribute)attrib;
/*     */       String str = (new StringBuilder()).append(scope.getURI(prefixedAttribute.pre())).append(prefixedAttribute.key()).toString();
/*     */     } else {
/*     */       if (attrib instanceof UnprefixedAttribute) {
/*     */         UnprefixedAttribute unprefixedAttribute = (UnprefixedAttribute)attrib;
/*     */         return unprefixedAttribute.key();
/*     */       } 
/*     */       throw new MatchError(attrib);
/*     */     } 
/*     */     return (String)SYNTHETIC_LOCAL_VARIABLE_5;
/*     */   }
/*     */   
/*     */   public MetaData update(MetaData attribs, NamespaceBinding scope, MetaData updates) {
/*     */     return normalize(concatenate(updates, attribs), scope);
/*     */   }
/*     */   
/*     */   public class MetaData$$anonfun$asAttrMap$1 extends AbstractFunction1<MetaData, Tuple2<String, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<String, String> apply(MetaData x) {
/*     */       return new Tuple2(x.prefixedKey(), NodeSeq$.MODULE$.seqToNodeSeq(x.value()).text());
/*     */     }
/*     */     
/*     */     public MetaData$$anonfun$asAttrMap$1(MetaData $outer) {}
/*     */   }
/*     */   
/*     */   private final MetaData iterate$1(MetaData md, MetaData normalized_attribs, Set set, NamespaceBinding scope$1) {
/*     */     while (true) {
/* 188 */       VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/*     */       ObjectRef key$lzy = new ObjectRef(null);
/*     */       if (md.value() == null || set.apply(key$1(scope$1, md, key$lzy, bitmap$0))) {
/*     */         md = md.next();
/*     */         continue;
/*     */       } 
/*     */       return (md == Null$.MODULE$) ? normalized_attribs : md.copy(iterate$1(md.next(), normalized_attribs, (Set)set.$plus(key$1(scope$1, md, key$lzy, bitmap$0)), scope$1));
/*     */     } 
/*     */   }
/*     */   
/*     */   public class MetaData$$anonfun$toString1$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 193 */       this.$outer.toString1(sb);
/*     */     }
/*     */     
/*     */     public MetaData$$anonfun$toString1$1(MetaData $outer) {}
/*     */   }
/*     */   
/*     */   public class MetaData$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 198 */       this.$outer.buildString(sb);
/*     */     }
/*     */     
/*     */     public MetaData$$anonfun$toString$1(MetaData $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\MetaData$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */