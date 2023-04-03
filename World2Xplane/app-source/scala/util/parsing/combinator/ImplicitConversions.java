/*    */ package scala.util.parsing.combinator;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Function3;
/*    */ import scala.Function4;
/*    */ import scala.Function5;
/*    */ import scala.MatchError;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005c!C\001\003!\003\r\taCA\033\005MIU\016\0357jG&$8i\0348wKJ\034\030n\0348t\025\t\031A!\001\006d_6\024\027N\\1u_JT!!\002\004\002\017A\f'o]5oO*\021q\001C\001\005kRLGNC\001\n\003\025\0318-\0317b\007\001\031\"\001\001\007\021\0055qQ\"\001\005\n\005=A!AB!osJ+g\rC\003\022\001\021\005!#\001\004%S:LG\017\n\013\002'A\021Q\002F\005\003+!\021A!\0268ji\")q\003\001C\0021\005Aa\r\\1ui\026t''\006\003\032K=\022DC\001\0165!\021i1$H\031\n\005qA!!\003$v]\016$\030n\03482!\021qrd\t\030\016\003\001I!\001I\021\003\r\021\"\030\016\0343f\023\t\021#AA\004QCJ\034XM]:\021\005\021*C\002\001\003\006MY\021\ra\n\002\002\003F\021\001f\013\t\003\033%J!A\013\005\003\0179{G\017[5oOB\021Q\002L\005\003[!\0211!\0218z!\t!s\006B\0031-\t\007qEA\001C!\t!#\007B\0034-\t\007qEA\001D\021\025)d\0031\0017\003\0051\007#B\0078G9\n\024B\001\035\t\005%1UO\\2uS>t'\007C\003;\001\021\r1(\001\005gY\006$H/\03284+\025a\024iQ#H)\ti\024\n\005\003\0167y2\005\003\002\020 \021\003BAH\020A\005B\021A%\021\003\006Me\022\ra\n\t\003I\r#Q\001M\035C\002\035\002\"\001J#\005\013MJ$\031A\024\021\005\021:E!\002%:\005\0049#!\001#\t\013UJ\004\031\001&\021\r5Y\005I\021#G\023\ta\005BA\005Gk:\034G/[8og!)a\n\001C\002\037\006Aa\r\\1ui\026tG'\006\004Q-bSFL\030\013\003#\002\004B!D\016S;B!adH*\\!\021qr\004V-\021\tyyRk\026\t\003IY#QAJ'C\002\035\002\"\001\n-\005\013Aj%\031A\024\021\005\021RF!B\032N\005\0049\003C\001\023]\t\025AUJ1\001(!\t!c\fB\003`\033\n\007qEA\001F\021\025)T\n1\001b!\035i!-V,Z7vK!a\031\005\003\023\031+hn\031;j_:$\004\"B3\001\t\0071\027\001\0034mCR$XM\\\033\026\017\035t\007O\035;wqR\021\001N\037\t\005\033mIw\017\005\003\037?),\b\003\002\020 WN\004BAH\020mcB!adH7p!\t!c\016B\003'I\n\007q\005\005\002%a\022)\001\007\032b\001OA\021AE\035\003\006g\021\024\ra\n\t\003IQ$Q\001\0233C\002\035\002\"\001\n<\005\013}#'\031A\024\021\005\021BH!B=e\005\0049#!\001$\t\013U\"\007\031A>\021\0215aXn\\9tk^L!! \005\003\023\031+hn\031;j_:,\004BB@\001\t\007\t\t!A\fiK\006$w\n\035;j_:$\026-\0337U_\032+h\016T5tiV1\0211AA\006\003[!B!!\002\0022A1QbGA\004\003W\001bAH\020\002\n\0055\001c\001\023\002\f\021)aE b\001OA)Q\"a\004\002\024%\031\021\021\003\005\003\r=\003H/[8o!\031\t)\"!\n\002\n9!\021qCA\021\035\021\tI\"a\b\016\005\005m!bAA\017\025\0051AH]8pizJ\021!C\005\004\003GA\021a\0029bG.\fw-Z\005\005\003O\tIC\001\003MSN$(bAA\022\021A\031A%!\f\005\r\005=bP1\001(\005\005!\006BB\033\001\004\t\031\004\005\004\0167\005M\0211\006\n\007\003o\tY$a\020\007\r\005e\002\001AA\033\0051a$/\0324j]\026lWM\034;?!\r\ti\004A\007\002\005A\031\021QH\021")
/*    */ public interface ImplicitConversions {
/*    */   <A, B, C> Function1<Parsers.$tilde<A, B>, C> flatten2(Function2<A, B, C> paramFunction2);
/*    */   
/*    */   <A, B, C, D> Function1<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D> flatten3(Function3<A, B, C, D> paramFunction3);
/*    */   
/*    */   <A, B, C, D, E> Function1<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D>, E> flatten4(Function4<A, B, C, D, E> paramFunction4);
/*    */   
/*    */   <A, B, C, D, E, F> Function1<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D>, E>, F> flatten5(Function5<A, B, C, D, E, F> paramFunction5);
/*    */   
/*    */   <A, T> Function1<Parsers.$tilde<A, Option<List<A>>>, T> headOptionTailToFunList(Function1<List<A>, T> paramFunction1);
/*    */   
/*    */   public class ImplicitConversions$$anonfun$flatten2$1 extends AbstractFunction1<Parsers.$tilde<A, B>, C> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function2 f$1;
/*    */     
/*    */     public final C apply(Parsers.$tilde p) {
/* 33 */       if (p != null)
/* 33 */         return (C)this.f$1.apply(p._1(), p._2()); 
/* 33 */       throw new MatchError(p);
/*    */     }
/*    */     
/*    */     public ImplicitConversions$$anonfun$flatten2$1(ImplicitConversions $outer, Function2 f$1) {}
/*    */   }
/*    */   
/*    */   public class ImplicitConversions$$anonfun$flatten3$1 extends AbstractFunction1<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function3 f$2;
/*    */     
/*    */     public final D apply(Parsers.$tilde p) {
/* 35 */       if (p != null && p._1() != null)
/* 35 */         return (D)this.f$2.apply(((Parsers.$tilde)p._1())._1(), ((Parsers.$tilde)p._1())._2(), p._2()); 
/* 35 */       throw new MatchError(p);
/*    */     }
/*    */     
/*    */     public ImplicitConversions$$anonfun$flatten3$1(ImplicitConversions $outer, Function3 f$2) {}
/*    */   }
/*    */   
/*    */   public class ImplicitConversions$$anonfun$flatten4$1 extends AbstractFunction1<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D>, E> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function4 f$3;
/*    */     
/*    */     public final E apply(Parsers.$tilde p) {
/* 37 */       if (p != null && p._1() != null && ((Parsers.$tilde)p._1())._1() != null)
/* 37 */         return (E)this.f$3.apply(((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._1(), ((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._2(), ((Parsers.$tilde)p._1())._2(), p._2()); 
/* 37 */       throw new MatchError(p);
/*    */     }
/*    */     
/*    */     public ImplicitConversions$$anonfun$flatten4$1(ImplicitConversions $outer, Function4 f$3) {}
/*    */   }
/*    */   
/*    */   public class ImplicitConversions$$anonfun$flatten5$1 extends AbstractFunction1<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<Parsers.$tilde<A, B>, C>, D>, E>, F> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function5 f$4;
/*    */     
/*    */     public final F apply(Parsers.$tilde p) {
/* 39 */       if (p != null && p._1() != null && ((Parsers.$tilde)p._1())._1() != null && ((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._1() != null)
/* 39 */         return (F)this.f$4.apply(((Parsers.$tilde)((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._1())._1(), ((Parsers.$tilde)((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._1())._2(), ((Parsers.$tilde)((Parsers.$tilde)p._1())._1())._2(), ((Parsers.$tilde)p._1())._2(), p._2()); 
/* 39 */       throw new MatchError(p);
/*    */     }
/*    */     
/*    */     public ImplicitConversions$$anonfun$flatten5$1(ImplicitConversions $outer, Function5 f$4) {}
/*    */   }
/*    */   
/*    */   public class ImplicitConversions$$anonfun$headOptionTailToFunList$1 extends AbstractFunction1<Parsers.$tilde<A, Option<List<A>>>, T> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function1 f$5;
/*    */     
/*    */     public final T apply(Parsers.$tilde p) {
/*    */       Nil$ nil$;
/* 41 */       Object object = p._1();
/* 41 */       Option option = (Option)p._2();
/* 41 */       if (option instanceof Some) {
/* 41 */         Some some = (Some)option;
/* 41 */         List list = (List)some.x();
/*    */       } else {
/* 41 */         if (None$.MODULE$ == null) {
/* 41 */           if (option != null)
/* 41 */             throw new MatchError(option); 
/* 41 */         } else if (!None$.MODULE$.equals(option)) {
/* 41 */           throw new MatchError(option);
/*    */         } 
/* 41 */         nil$ = Nil$.MODULE$;
/*    */       } 
/* 41 */       return (T)None$.MODULE$.apply(nil$.$colon$colon(object));
/*    */     }
/*    */     
/*    */     public ImplicitConversions$$anonfun$headOptionTailToFunList$1(ImplicitConversions $outer, Function1 f$5) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\ImplicitConversions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */