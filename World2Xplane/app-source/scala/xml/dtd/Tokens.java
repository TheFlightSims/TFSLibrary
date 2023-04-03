/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=4A!\001\002\001\023\t1Ak\\6f]NT!a\001\003\002\007\021$HM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\034\001a\005\002\001\025A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\t\013=\001A\021\001\t\002\rqJg.\033;?)\005\t\002C\001\n\001\033\005\021\001b\002\013\001\005\004%)!F\001\r)>[UIT0Q\007\022\013E+Q\013\002-=\tq#H\001\001\021\031I\002\001)A\007-\005iAkT&F\035~\0036\tR!U\003\002Bqa\007\001C\002\023\025A$\001\003O\0036+U#A\017\020\003yi\022!\001\005\007A\001\001\013QB\017\002\0139\013U*\022\021\t\017\t\002!\031!C\003G\0051A\nU!S\013:+\022\001J\b\002Ku\t1\001\003\004(\001\001\006i\001J\001\b\031B\013%+\022(!\021\035I\003A1A\005\006)\naA\025)B%\026sU#A\026\020\0031j\022\001\002\005\007]\001\001\013QB\026\002\017I\003\026IU#OA!9\001\007\001b\001\n\013\t\024!B\"P\0336\013U#\001\032\020\003Mj\022!\002\005\007k\001\001\013Q\002\032\002\r\r{U*T!!\021\0359\004A1A\005\006a\nAa\025+B%V\t\021hD\001;;\0051\001B\002\037\001A\0035\021(A\003T)\006\023\006\005C\004?\001\t\007IQA \002\tAcUkU\013\002\001>\t\021)H\001\b\021\031\031\005\001)A\007\001\006)\001\013T+TA!9Q\t\001b\001\n\0131\025aA(Q)V\tqiD\001I;\005A\001B\002&\001A\0035q)\001\003P!R\003\003b\002'\001\005\004%)!T\001\007\007\"{\025jQ#\026\0039{\021aT\017\002\023!1\021\013\001Q\001\0169\013qa\021%P\023\016+\005\005C\004T\001\t\007IQ\001+\002\007\025sE)F\001V\037\0051V$\001\006\t\ra\003\001\025!\004V\003\021)e\n\022\021\t\017i\003!\031!C\0037\006\t1+F\001]\037\005iV$A\007\t\r}\003\001\025!\004]\003\t\031\006\005C\003b\001\021\025!-\001\007u_.,gNM:ue&tw\r\006\002dUB\021Am\032\b\003\027\025L!A\032\004\002\rA\023X\rZ3g\023\tA\027N\001\004TiJLgn\032\006\003M\032AQa\0331A\0021\f\021!\033\t\003\0275L!A\034\004\003\007%sG\017")
/*    */ public class Tokens {
/*    */   private final int TOKEN_PCDATA;
/*    */   
/*    */   private final int NAME;
/*    */   
/*    */   private final int LPAREN;
/*    */   
/*    */   private final int RPAREN;
/*    */   
/*    */   private final int COMMA;
/*    */   
/*    */   private final int STAR;
/*    */   
/*    */   private final int PLUS;
/*    */   
/*    */   private final int OPT;
/*    */   
/*    */   private final int CHOICE;
/*    */   
/*    */   private final int END;
/*    */   
/*    */   private final int S;
/*    */   
/*    */   public final int TOKEN_PCDATA() {
/* 19 */     return 0;
/*    */   }
/*    */   
/*    */   public final int NAME() {
/* 20 */     return 1;
/*    */   }
/*    */   
/*    */   public final int LPAREN() {
/* 21 */     return 3;
/*    */   }
/*    */   
/*    */   public final int RPAREN() {
/* 22 */     return 4;
/*    */   }
/*    */   
/*    */   public final int COMMA() {
/* 23 */     return 5;
/*    */   }
/*    */   
/*    */   public final int STAR() {
/* 24 */     return 6;
/*    */   }
/*    */   
/*    */   public final int PLUS() {
/* 25 */     return 7;
/*    */   }
/*    */   
/*    */   public final int OPT() {
/* 26 */     return 8;
/*    */   }
/*    */   
/*    */   public final int CHOICE() {
/* 27 */     return 9;
/*    */   }
/*    */   
/*    */   public final int END() {
/* 28 */     return 10;
/*    */   }
/*    */   
/*    */   public final int S() {
/* 29 */     return 13;
/*    */   }
/*    */   
/*    */   public final String token2string(int i) {
/* 31 */     switch (i) {
/*    */       default:
/* 31 */         throw new MatchError(BoxesRunTime.boxToInteger(i));
/*    */       case 13:
/*    */       
/*    */       case 10:
/*    */       
/*    */       case 9:
/*    */       
/*    */       case 8:
/*    */       
/*    */       case 7:
/*    */       
/*    */       case 6:
/*    */       
/*    */       case 5:
/*    */       
/*    */       case 4:
/*    */       
/*    */       case 3:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 31 */     return 
/* 32 */       "#PCDATA";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\Tokens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */