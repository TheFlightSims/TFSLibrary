/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\teq!B\001\003\021\0039\021\001C'fi\006$\025\r^1\013\005\r!\021a\001=nY*\tQ!A\003tG\006d\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\0215+G/\031#bi\006\0342!\003\007\021!\tia\"D\001\005\023\tyAA\001\004B]f\024VM\032\t\003\033EI!A\005\003\003\031M+'/[1mSj\f'\r\\3\t\013QIA\021A\013\002\rqJg.\033;?)\0059\001\"B\f\n\t\013A\022aC2p]\016\fG/\0328bi\026$R!GAm\003;\004\"\001\003\016\007\013)\021\021\021A\016\024\013ia\"E\f\t\021\007u\001\023$D\001\037\025\tyB!\001\006d_2dWm\031;j_:L!!\t\020\003!\005\0237\017\036:bGRLE/\032:bE2,\007cA\022,39\021A%\013\b\003K!j\021A\n\006\003O\031\ta\001\020:p_Rt\024\"A\003\n\005)\"\021a\0029bG.\fw-Z\005\003Y5\022\001\"\023;fe\006\024G.\032\006\003U\021\001\"\001C\030\n\005A\022!\001C#rk\006d\027\016^=\t\013QQB\021\001\032\025\003eAQ\001\016\016\005\002U\na!\0319qK:$GcA\r7q!)qg\ra\0013\0059Q\017\0353bi\026\034\bbB\0354!\003\005\rAO\001\006g\016|\007/\032\t\003\021mJ!\001\020\002\003!9\013W.Z:qC\016,')\0338eS:<\007\"\002 \033\r\003y\024!B1qa2LHC\001!G!\r\031\023iQ\005\003\0056\0221aU3r!\tAA)\003\002F\005\t!aj\0343f\021\0259U\b1\001I\003\rYW-\037\t\003\0232s!!\004&\n\005-#\021A\002)sK\022,g-\003\002N\035\n11\013\036:j]\036T!a\023\003\t\013yRBQ\001)\025\t\001\0136+\026\005\006%>\003\r\001S\001\016]\006lWm\0359bG\026|VO]5\t\013Q{\005\031A\"\002\013=<h.\032:\t\013\035{\005\031\001%\t\013yRb\021A,\025\t\001C\026l\027\005\006%Z\003\r\001\023\005\0065Z\003\rAO\001\004g\016\004\b\"\002/W\001\004A\025!A6\t\013ySb\021A0\002\t\r|\007/\037\013\0033\001DQ!Y/A\002e\tAA\\3yi\")1M\007D\001I\006aq-\032;OC6,7\017]1dKR\021\001*\032\005\006)\n\004\ra\021\005\006Oj!\t\001[\001\bQ\006\034h*\032=u+\005I\007CA\007k\023\tYGAA\004C_>dW-\0318\t\0135TB\021\0018\002\r1,gn\032;i+\005y\007CA\007q\023\t\tHAA\002J]RDQ!\034\016\005\002M$\"a\034;\t\013U\024\b\031A8\002\003%DQa\036\016\007\002!\f!\"[:Qe\0264\027\016_3e\021\025I(\004\"\021{\003!\031\027M\\#rk\006dGCA5|\021\025a\b\0201\001~\003\025yG\017[3s!\tia0\003\002\000\t\t\031\021I\\=\t\017\005\r!\004\"\021\002\006\005i1\017\036:jGR|F%Z9%KF$2![A\004\021\031a\030\021\001a\001]!9\0211\002\016\005\022\0055\021\001\0052bg&\034hi\034:ICND7i\0343f+\t\ty\001E\002$\003vDq!a\005\033\t\003\n)\"\001\004gS2$XM\035\013\0043\005]\001\002CA\r\003#\001\r!a\007\002\003\031\004R!DA\0173%L1!a\b\005\005%1UO\\2uS>t\027\007\003\004H5\031\005\0211E\013\002\021\"9\021q\005\016\007\002\005%\022!\002<bYV,W#\001!\t\017\0055\"\004\"\001\0020\005Y\001O]3gSb,GmS3z+\t\t\t\004\005\003\0024\005uRBAA\033\025\021\t9$!\017\002\t1\fgn\032\006\003\003w\tAA[1wC&\031Q*!\016\t\017\005\005#\004\"\001\002D\005I\021m]!uiJl\025\r]\013\003\003\013\002R!SA$\021\"K1!!\023O\005\ri\025\r\035\005\007Cj1\t!!\024\026\003eAq!!\025\033\t\013\t\031&A\002hKR$B!!\026\002\\A!Q\"a\026A\023\r\tI\006\002\002\007\037B$\030n\0348\t\r\035\013y\0051\001I\021\035\t\tF\007C\003\003?\"\002\"!\026\002b\005\025\024q\r\005\b\003G\ni\0061\001I\003\r)(/\033\005\007)\006u\003\031A\"\t\r\035\013i\0061\001I\021\035\t\tF\007C\003\003W\"\002\"!\026\002n\005=\024\021\017\005\b\003G\nI\0071\001I\021\031I\024\021\016a\001u!1q)!\033A\002!Cq!!\036\033\t#\t9(A\005u_N#(/\0338hcQ\t\001\nC\004\002vi1\t\"a\037\025\t\005u\0241\021\t\004\033\005}\024bAAA\t\t!QK\\5u\021!\t))!\037A\002\005\035\025AA:c!\r\031\023\021R\005\004\003\027k#!D*ue&twMQ;jY\022,'\017C\004\002\020j!\t%a\036\002\021Q|7\013\036:j]\036Dq!a%\033\t\003\t)*A\006ck&dGm\025;sS:<G\003BAD\003/C\001\"!\"\002\022\002\007\021q\021\005\b\0037Sb\021AAO\003)9X\r\0347g_JlW\r\032\013\004S\006}\005BB\035\002\032\002\007!\bC\004\002$j1\t!!*\002\rI,Wn\034<f)\rI\022q\025\005\007\017\006\005\006\031\001%\t\017\005\r&D\"\001\002,R9\021$!,\0022\006M\006bBAX\003S\003\r\001S\001\n]\006lWm\0359bG\026Da!OAU\001\004Q\004BB$\002*\002\007\001\nC\004\002$j!)!a.\025\017e\tI,a/\002>\"9\021qVA[\001\004A\005B\002+\0026\002\0071\t\003\004H\003k\003\r\001\023\005\n\003\003T\022\023!C\001\003\007\f\001#\0319qK:$G\005Z3gCVdG\017\n\032\026\005\005\025'f\001\036\002H.\022\021\021\032\t\005\003\027\f).\004\002\002N*!\021qZAi\003%)hn\0315fG.,GMC\002\002T\022\t!\"\0318o_R\fG/[8o\023\021\t9.!4\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\003\004\002\\Z\001\r!G\001\bCR$(/\0332t\021\031\tyN\006a\0013\005Aa.Z<`i\006LG\016K\002\027\003G\004B!!:\002h6\021\021\021[\005\005\003S\f\tNA\004uC&d'/Z2\t\017\0055\030\002\"\001\002p\006Ian\034:nC2L'0\032\013\0063\005E\0301\037\005\b\0037\fY\0171\001\032\021\031I\0241\036a\001u!9\021q_\005\005\002\005e\030aD4fiVs\027N^3sg\006d7*Z=\025\r\005E\0221`A\000\021\035\ti0!>A\002e\ta!\031;ue&\024\007BB\035\002v\002\007!\bC\004\003\004%!\tA!\002\002\rU\004H-\031;f)\035I\"q\001B\005\005\027Aq!a7\003\002\001\007\021\004\003\004:\005\003\001\rA\017\005\007o\t\005\001\031A\r\t\023\t=\021\"!A\005\n\tE\021a\003:fC\022\024Vm]8mm\026$\"Aa\005\021\t\005M\"QC\005\005\005/\t)D\001\004PE*,7\r\036")
/*     */ public abstract class MetaData extends AbstractIterable<MetaData> implements Iterable<MetaData>, Equality, Serializable {
/*     */   public boolean strict_$bang$eq(Equality other) {
/*  75 */     return Equality$class.strict_$bang$eq(this, other);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  75 */     return Equality$class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  75 */     return Equality$class.equals(this, other);
/*     */   }
/*     */   
/*     */   public final boolean xml_$eq$eq(Object other) {
/*  75 */     return Equality$class.xml_$eq$eq(this, other);
/*     */   }
/*     */   
/*     */   public final boolean xml_$bang$eq(Object other) {
/*  75 */     return Equality$class.xml_$bang$eq(this, other);
/*     */   }
/*     */   
/*     */   public MetaData() {
/*  76 */     Equality$class.$init$(this);
/*     */   }
/*     */   
/*     */   public NamespaceBinding append$default$2() {
/*  89 */     return TopScope$.MODULE$;
/*     */   }
/*     */   
/*     */   public MetaData append(MetaData updates, NamespaceBinding scope) {
/*  90 */     return MetaData$.MODULE$.update(this, scope, updates);
/*     */   }
/*     */   
/*     */   public final Seq<Node> apply(String namespace_uri, Node owner, String key) {
/* 107 */     return apply(namespace_uri, owner.scope(), key);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 126 */     MetaData metaData = next();
/* 126 */     if (Null$.MODULE$ == null) {
/* 126 */       if (metaData != null);
/* 126 */     } else if (Null$.MODULE$.equals(metaData)) {
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public int length() {
/* 128 */     return length(0);
/*     */   }
/*     */   
/*     */   public int length(int i) {
/* 130 */     return next().length(i + 1);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*     */     boolean bool;
/* 134 */     if (other instanceof MetaData) {
/* 134 */       bool = true;
/*     */     } else {
/* 136 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public boolean strict_$eq$eq(Equality other) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/xml/MetaData
/*     */     //   4: ifeq -> 50
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/xml/MetaData
/*     */     //   11: astore_2
/*     */     //   12: aload_0
/*     */     //   13: invokevirtual asAttrMap : ()Lscala/collection/immutable/Map;
/*     */     //   16: aload_2
/*     */     //   17: invokevirtual asAttrMap : ()Lscala/collection/immutable/Map;
/*     */     //   20: astore_3
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 33
/*     */     //   25: pop
/*     */     //   26: aload_3
/*     */     //   27: ifnull -> 40
/*     */     //   30: goto -> 44
/*     */     //   33: aload_3
/*     */     //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   37: ifeq -> 44
/*     */     //   40: iconst_1
/*     */     //   41: goto -> 45
/*     */     //   44: iconst_0
/*     */     //   45: istore #4
/*     */     //   47: goto -> 53
/*     */     //   50: iconst_0
/*     */     //   51: istore #4
/*     */     //   53: iload #4
/*     */     //   55: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #139	-> 0
/*     */     //   #138	-> 0
/*     */     //   #140	-> 50
/*     */     //   #138	-> 53
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	56	0	this	Lscala/xml/MetaData;
/*     */     //   0	56	1	other	Lscala/xml/Equality;
/*     */   }
/*     */   
/*     */   public Seq<Object> basisForHashCode() {
/* 142 */     (new Map[1])[0] = asAttrMap();
/* 142 */     return (Seq<Object>)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Map[1]));
/*     */   }
/*     */   
/*     */   public MetaData filter(Function1<MetaData, Object> f) {
/* 146 */     return BoxesRunTime.unboxToBoolean(f.apply(this)) ? copy(next().filter(f)) : 
/* 147 */       next().filter(f);
/*     */   }
/*     */   
/*     */   public String prefixedKey() {
/* 158 */     if (this instanceof Attribute) {
/* 158 */       Attribute attribute = (Attribute)this;
/* 158 */       if (attribute.isPrefixed())
/* 158 */         return (new StringBuilder()).append(attribute.pre()).append(":").append(key()).toString(); 
/*     */     } 
/* 158 */     return 
/*     */       
/* 160 */       key();
/*     */   }
/*     */   
/*     */   public Map<String, String> asAttrMap() {
/* 166 */     return iterator().map((Function1)new MetaData$$anonfun$asAttrMap$1(this)).toMap(Predef$.MODULE$.conforms());
/*     */   }
/*     */   
/*     */   public class MetaData$$anonfun$asAttrMap$1 extends AbstractFunction1<MetaData, Tuple2<String, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<String, String> apply(MetaData x) {
/* 166 */       return new Tuple2(x.prefixedKey(), NodeSeq$.MODULE$.seqToNodeSeq(x.value()).text());
/*     */     }
/*     */     
/*     */     public MetaData$$anonfun$asAttrMap$1(MetaData $outer) {}
/*     */   }
/*     */   
/*     */   public final Option<Seq<Node>> get(String key) {
/* 177 */     return Option$.MODULE$.apply(apply(key));
/*     */   }
/*     */   
/*     */   public final Option<Seq<Node>> get(String uri, Node owner, String key) {
/* 181 */     return get(uri, owner.scope(), key);
/*     */   }
/*     */   
/*     */   public final Option<Seq<Node>> get(String uri, NamespaceBinding scope, String key) {
/* 191 */     return Option$.MODULE$.apply(apply(uri, scope, key));
/*     */   }
/*     */   
/*     */   public String toString1() {
/* 193 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new MetaData$$anonfun$toString1$1(this));
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
/*     */   public String toString() {
/* 198 */     return Utility$.MODULE$.sbToString((Function1<StringBuilder, BoxedUnit>)new MetaData$$anonfun$toString$1(this));
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
/*     */   
/*     */   public StringBuilder buildString(StringBuilder sb) {
/* 201 */     sb.append(' ');
/* 202 */     toString1(sb);
/* 203 */     return next().buildString(sb);
/*     */   }
/*     */   
/*     */   public final MetaData remove(String namespace, Node owner, String key) {
/* 215 */     return remove(namespace, owner.scope(), key);
/*     */   }
/*     */   
/*     */   public static MetaData update(MetaData paramMetaData1, NamespaceBinding paramNamespaceBinding, MetaData paramMetaData2) {
/*     */     return MetaData$.MODULE$.update(paramMetaData1, paramNamespaceBinding, paramMetaData2);
/*     */   }
/*     */   
/*     */   public static String getUniversalKey(MetaData paramMetaData, NamespaceBinding paramNamespaceBinding) {
/*     */     return MetaData$.MODULE$.getUniversalKey(paramMetaData, paramNamespaceBinding);
/*     */   }
/*     */   
/*     */   public static MetaData normalize(MetaData paramMetaData, NamespaceBinding paramNamespaceBinding) {
/*     */     return MetaData$.MODULE$.normalize(paramMetaData, paramNamespaceBinding);
/*     */   }
/*     */   
/*     */   public static MetaData concatenate(MetaData paramMetaData1, MetaData paramMetaData2) {
/*     */     return MetaData$.MODULE$.concatenate(paramMetaData1, paramMetaData2);
/*     */   }
/*     */   
/*     */   public abstract Seq<Node> apply(String paramString);
/*     */   
/*     */   public abstract Seq<Node> apply(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2);
/*     */   
/*     */   public abstract MetaData copy(MetaData paramMetaData);
/*     */   
/*     */   public abstract String getNamespace(Node paramNode);
/*     */   
/*     */   public abstract boolean isPrefixed();
/*     */   
/*     */   public abstract String key();
/*     */   
/*     */   public abstract Seq<Node> value();
/*     */   
/*     */   public abstract MetaData next();
/*     */   
/*     */   public abstract void toString1(StringBuilder paramStringBuilder);
/*     */   
/*     */   public abstract boolean wellformed(NamespaceBinding paramNamespaceBinding);
/*     */   
/*     */   public abstract MetaData remove(String paramString);
/*     */   
/*     */   public abstract MetaData remove(String paramString1, NamespaceBinding paramNamespaceBinding, String paramString2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\MetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */