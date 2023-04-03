/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u3q!\001\002\021\002\007\005\021B\001\006U_.,g\016V3tiNT!a\001\003\002\017A\f'o]5oO*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\023j]&$H\005F\001\022!\tY!#\003\002\024\r\t!QK\\5u\021\025)\002\001\"\002\027\003\035I7o\0259bG\026$\"a\006\016\021\005-A\022BA\r\007\005\035\021un\0347fC:DQa\007\013A\002q\t!a\0315\021\005-i\022B\001\020\007\005\021\031\005.\031:\t\013U\001AQ\001\021\025\005]\t\003\"\002\022 \001\004\031\023AA2t!\r!C\006\b\b\003K)r!AJ\025\016\003\035R!\001\013\005\002\rq\022xn\034;?\023\0059\021BA\026\007\003\035\001\030mY6bO\026L!!\f\030\003\007M+\027O\003\002,\r!)\001\007\001C\001c\0059\021n]!ma\"\fGCA\f3\021\025\031t\0061\001\035\003\005\031\007\"B\033\001\t\0031\024\001D5t\0032\004\b.\031#jO&$HCA\f8\021\025\031D\0071\001\035\021\025I\004\001\"\001;\003)I7OT1nK\016C\027M\035\013\003/mBQa\007\035A\002qAQ!\020\001\005\002y\n1\"[:OC6,7\013^1siR\021qc\020\005\0067q\002\r\001\b\005\006\003\002!\tAQ\001\007SNt\025-\\3\025\005]\031\005\"\002#A\001\004)\025!A:\021\005\031KeBA\006H\023\tAe!\001\004Qe\026$WMZ\005\003\025.\023aa\025;sS:<'B\001%\007\021\025i\005\001\"\001O\003-I7\017U;c\023\022\033\005.\031:\025\005]y\005\"B\016M\001\004a\002\"B)\001\t\003\021\026aE5t-\006d\027\016Z%B\035\006+enY8eS:<GCA\fT\021\025!\006\0131\001$\0031I\027M\\1F]\016|G-\0338h\021\0251\006\001\"\001X\003)\031\007.Z2l'f\034\030\n\022\013\003/aCQ\001R+A\002\025CQA\027\001\005\002m\013!b\0315fG.\004VOY%E)\t9B\fC\003E3\002\007Q\t")
/*    */ public interface TokenTests {
/*    */   boolean isSpace(char paramChar);
/*    */   
/*    */   boolean isSpace(Seq<Object> paramSeq);
/*    */   
/*    */   boolean isAlpha(char paramChar);
/*    */   
/*    */   boolean isAlphaDigit(char paramChar);
/*    */   
/*    */   boolean isNameChar(char paramChar);
/*    */   
/*    */   boolean isNameStart(char paramChar);
/*    */   
/*    */   boolean isName(String paramString);
/*    */   
/*    */   boolean isPubIDChar(char paramChar);
/*    */   
/*    */   boolean isValidIANAEncoding(Seq<Object> paramSeq);
/*    */   
/*    */   boolean checkSysID(String paramString);
/*    */   
/*    */   boolean checkPubID(String paramString);
/*    */   
/*    */   public class TokenTests$$anonfun$isSpace$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char ch) {
/* 27 */       return this.$outer.isSpace(ch);
/*    */     }
/*    */     
/*    */     public TokenTests$$anonfun$isSpace$1(TokenTests $outer) {}
/*    */   }
/*    */   
/*    */   public class TokenTests$$anonfun$isName$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char ch) {
/* 77 */       return this.$outer.isNameChar(ch);
/*    */     }
/*    */     
/*    */     public TokenTests$$anonfun$isName$1(TokenTests $outer) {}
/*    */   }
/*    */   
/*    */   public class TokenTests$$anonfun$isValidIANAEncoding$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char c) {
/* 95 */       return TokenTests$class.charOK$1(this.$outer, c);
/*    */     }
/*    */     
/*    */     public TokenTests$$anonfun$isValidIANAEncoding$1(TokenTests $outer) {}
/*    */   }
/*    */   
/*    */   public class TokenTests$$anonfun$checkSysID$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String s$1;
/*    */     
/*    */     public final boolean apply(char c) {
/* 98 */       String str = this.s$1;
/* 98 */       Predef$ predef$ = Predef$.MODULE$;
/* 98 */       return !(new StringOps(str)).contains(BoxesRunTime.boxToCharacter(c));
/*    */     }
/*    */     
/*    */     public TokenTests$$anonfun$checkSysID$1(TokenTests $outer, String s$1) {}
/*    */   }
/*    */   
/*    */   public class TokenTests$$anonfun$checkPubID$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char ch) {
/* 99 */       return this.$outer.isPubIDChar(ch);
/*    */     }
/*    */     
/*    */     public TokenTests$$anonfun$checkPubID$1(TokenTests $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\TokenTests.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */