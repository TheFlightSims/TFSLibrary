/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.Tuple4;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ 
/*    */ public final class Attribute$ implements Serializable {
/*    */   public static final Attribute$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 17 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Attribute$() {
/* 17 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Option<Tuple3<String, Seq<Node>, MetaData>> unapply(Attribute x) {
/* 18 */     if (x instanceof PrefixedAttribute) {
/* 18 */       PrefixedAttribute prefixedAttribute = (PrefixedAttribute)x;
/* 18 */       Some<Tuple4<String, String, Seq<Node>, MetaData>> some = PrefixedAttribute$.MODULE$.unapply(prefixedAttribute);
/* 18 */       if (!some.isEmpty())
/* 18 */         return (Option<Tuple3<String, Seq<Node>, MetaData>>)new Some(new Tuple3(((Tuple4)some.get())
/* 19 */               ._2(), ((Tuple4)some.get())._3(), ((Tuple4)some.get())._4())); 
/*    */     } 
/* 20 */     if (x instanceof UnprefixedAttribute) {
/* 20 */       UnprefixedAttribute unprefixedAttribute = (UnprefixedAttribute)x;
/* 20 */       Some<Tuple3<String, Seq<Node>, MetaData>> some = UnprefixedAttribute$.MODULE$.unapply(unprefixedAttribute);
/* 20 */       if (!some.isEmpty())
/* 20 */         return (Option<Tuple3<String, Seq<Node>, MetaData>>)new Some(new Tuple3(((Tuple3)some.get())._1(), ((Tuple3)some.get())._2(), ((Tuple3)some.get())._3())); 
/*    */     } 
/* 21 */     return (Option<Tuple3<String, Seq<Node>, MetaData>>)scala.None$.MODULE$;
/*    */   }
/*    */   
/*    */   public Attribute apply(String key, Seq<Node> value, MetaData next) {
/* 26 */     return new UnprefixedAttribute(key, value, next);
/*    */   }
/*    */   
/*    */   public Attribute apply(String pre, String key, String value, MetaData next) {
/* 29 */     if (pre != null)
/* 29 */       if (pre == null) {
/* 29 */         if ("" != null);
/* 29 */       } else if (pre.equals("")) {
/*    */       
/*    */       }  
/*    */   }
/*    */   
/*    */   public Attribute apply(String pre, String key, Seq value, MetaData next) {
/* 33 */     if (pre != null)
/* 33 */       if (pre == null) {
/* 33 */         if ("" != null);
/* 33 */       } else if (pre.equals("")) {
/*    */       
/*    */       }  
/*    */   }
/*    */   
/*    */   public Attribute apply(Option pre, String key, Seq<Node> value, MetaData next) {
/*    */     // Byte code:
/*    */     //   0: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   3: dup
/*    */     //   4: ifnonnull -> 15
/*    */     //   7: pop
/*    */     //   8: aload_1
/*    */     //   9: ifnull -> 22
/*    */     //   12: goto -> 38
/*    */     //   15: aload_1
/*    */     //   16: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   19: ifeq -> 38
/*    */     //   22: new scala/xml/UnprefixedAttribute
/*    */     //   25: dup
/*    */     //   26: aload_2
/*    */     //   27: aload_3
/*    */     //   28: aload #4
/*    */     //   30: invokespecial <init> : (Ljava/lang/String;Lscala/collection/Seq;Lscala/xml/MetaData;)V
/*    */     //   33: astore #6
/*    */     //   35: goto -> 72
/*    */     //   38: aload_1
/*    */     //   39: instanceof scala/Some
/*    */     //   42: ifeq -> 75
/*    */     //   45: aload_1
/*    */     //   46: checkcast scala/Some
/*    */     //   49: astore #5
/*    */     //   51: new scala/xml/PrefixedAttribute
/*    */     //   54: dup
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   60: checkcast java/lang/String
/*    */     //   63: aload_2
/*    */     //   64: aload_3
/*    */     //   65: aload #4
/*    */     //   67: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;Lscala/collection/Seq;Lscala/xml/MetaData;)V
/*    */     //   70: astore #6
/*    */     //   72: aload #6
/*    */     //   74: areturn
/*    */     //   75: new scala/MatchError
/*    */     //   78: dup
/*    */     //   79: aload_1
/*    */     //   80: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   83: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     //   #37	-> 0
/*    */     //   #39	-> 38
/*    */     //   #37	-> 55
/*    */     //   #39	-> 57
/*    */     //   #37	-> 72
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	84	0	this	Lscala/xml/Attribute$;
/*    */     //   0	84	1	pre	Lscala/Option;
/*    */     //   0	84	2	key	Ljava/lang/String;
/*    */     //   0	84	3	value	Lscala/collection/Seq;
/*    */     //   0	84	4	next	Lscala/xml/MetaData;
/*    */   }
/*    */   
/*    */   public class Attribute$$anonfun$iterator$1 extends AbstractFunction0<Iterator<MetaData>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Iterator<MetaData> apply() {
/* 79 */       return this.$outer.next().iterator();
/*    */     }
/*    */     
/*    */     public Attribute$$anonfun$iterator$1(Attribute $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Attribute$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */