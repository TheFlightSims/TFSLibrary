/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public abstract class TokenTests$class {
/*    */   public static void $init$(TokenTests $this) {}
/*    */   
/*    */   public static final boolean isSpace(TokenTests $this, char ch) {
/* 20 */     switch (ch) {
/*    */       default:
/*    */       
/*    */       case '\t':
/*    */       case '\n':
/*    */       case '\r':
/*    */       case ' ':
/*    */         break;
/*    */     } 
/* 20 */     return true;
/*    */   }
/*    */   
/*    */   public static final boolean isSpace(TokenTests $this, Seq cs) {
/* 27 */     return (cs.nonEmpty() && cs.forall((Function1)new TokenTests$$anonfun$isSpace$1($this)));
/*    */   }
/*    */   
/*    */   public static boolean isAlpha(TokenTests $this, char c) {
/* 30 */     return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
/*    */   }
/*    */   
/*    */   public static boolean isAlphaDigit(TokenTests $this, char c) {
/* 31 */     return ($this.isAlpha(c) || (c >= '0' && c <= '9'));
/*    */   }
/*    */   
/*    */   public static boolean isNameChar(TokenTests $this, char ch) {
/* 43 */     if (!$this.isNameStart(ch)) {
/*    */       Predef$ predef$;
/* 43 */       byte b = (byte)Character.getType(ch);
/* 43 */       switch (b) {
/*    */         default:
/* 47 */           predef$ = Predef$.MODULE$;
/*    */         case 4:
/*    */         case 6:
/*    */         case 7:
/*    */         case 8:
/*    */         case 9:
/*    */           break;
/*    */       } 
/*    */       if (true);
/*    */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isNameStart(TokenTests $this, char ch) {
/* 63 */     byte b = (byte)Character.getType(ch);
/* 63 */     switch (b) {
/*    */       default:
/* 63 */         return 
/*    */           
/* 67 */           (ch == '_');
/*    */       case 1:
/*    */       case 2:
/*    */       case 3:
/*    */       case 5:
/*    */       case 10:
/*    */         break;
/*    */     } 
/*    */     return true;
/*    */   }
/*    */   
/*    */   public static boolean isName(TokenTests $this, String s) {
/* 77 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$, predef$3 = Predef$.MODULE$;
/* 77 */     String str = (String)IndexedSeqOptimized.class.tail((IndexedSeqOptimized)new StringOps(s));
/* 77 */     Predef$ predef$4 = Predef$.MODULE$;
/* 77 */     return (TraversableOnce.class.nonEmpty((TraversableOnce)new StringOps(s)) && $this.isNameStart(BoxesRunTime.unboxToChar(IndexedSeqOptimized.class.head((IndexedSeqOptimized)new StringOps(s)))) && IndexedSeqOptimized.class.forall((IndexedSeqOptimized)new StringOps(str), (Function1)new TokenTests$$anonfun$isName$1($this)));
/*    */   }
/*    */   
/*    */   public static boolean isPubIDChar(TokenTests $this, char ch) {
/* 81 */     Predef$ predef$ = Predef$.MODULE$;
/* 81 */     return ($this.isAlphaDigit(ch) || ($this.isSpace(ch) && ch != '\t') || (new StringOps("-\\()+,./:=?;!*#@$_%")).contains(BoxesRunTime.boxToCharacter(ch)));
/*    */   }
/*    */   
/*    */   public static final boolean charOK$1(TokenTests $this, char c) {
/* 92 */     Predef$ predef$ = Predef$.MODULE$;
/* 92 */     return ($this.isAlphaDigit(c) || (new StringOps("._-")).contains(BoxesRunTime.boxToCharacter(c)));
/*    */   }
/*    */   
/*    */   public static boolean isValidIANAEncoding(TokenTests $this, Seq ianaEncoding) {
/* 94 */     return (ianaEncoding.nonEmpty() && $this.isAlpha(BoxesRunTime.unboxToChar(ianaEncoding.head())) && (
/* 95 */       (IterableLike)ianaEncoding.tail()).forall((Function1)new TokenTests$$anonfun$isValidIANAEncoding$1($this)));
/*    */   }
/*    */   
/*    */   public static boolean checkSysID(TokenTests $this, String s) {
/* 98 */     return List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapCharArray(new char[] { '"', '\'' })).exists((Function1)new TokenTests$$anonfun$checkSysID$1($this, s));
/*    */   }
/*    */   
/*    */   public static boolean checkPubID(TokenTests $this, String s) {
/* 99 */     Predef$ predef$ = Predef$.MODULE$;
/* 99 */     return IndexedSeqOptimized.class.forall((IndexedSeqOptimized)new StringOps(s), (Function1)new TokenTests$$anonfun$checkPubID$1($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\TokenTests$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */