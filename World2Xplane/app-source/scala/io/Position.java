/*    */ package scala.io;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.math.package$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]4Q!\001\002\002\002\035\021\001\002U8tSRLwN\034\006\003\007\021\t!![8\013\003\025\tQa]2bY\006\034\001a\005\002\001\021A\021\021BC\007\002\t%\0211\002\002\002\007\003:L(+\0324\t\0135\001A\021\001\b\002\rqJg.\033;?)\005y\001C\001\t\001\033\005\021\001\"\002\n\001\r\003\031\022AC2iK\016\\\027J\0349viR\031Ac\006\017\021\005%)\022B\001\f\005\005\021)f.\033;\t\013a\t\002\031A\r\002\t1Lg.\032\t\003\023iI!a\007\003\003\007%sG\017C\003\036#\001\007\021$\001\004d_2,XN\034\005\b?\001\021\r\021\"\002!\003%a\025JT#`\005&#6+F\001\"\037\005\021S$\001\013\t\r\021\002\001\025!\004\"\003)a\025JT#`\005&#6\013\t\005\bM\001\021\r\021\"\002(\003-\031u\nT+N\035~\023\025\nV*\026\003!z\021!K\017\002\027!11\006\001Q\001\016!\nAbQ(M+6suLQ%U'\002Bq!\f\001C\002\023\025a&A\005M\023:+u,T!T\027V\tqfD\0011;\ryq\000@\005\007e\001\001\013QB\030\002\0251Ke*R0N\003N[\005\005C\0045\001\t\007IQA\033\002\027\r{E*V'O?6\0135kS\013\002m=\tq'\b\002\b$1\021\b\001Q\001\016Y\nAbQ(M+6su,T!T\027\002BQa\017\001\005\006q\na!\0328d_\022,GcA\r>}!)\001D\017a\0013!)QD\017a\0013!)\001\004\001C\003\001R\021\021$\021\005\006\005~\002\r!G\001\004a>\034\b\"B\017\001\t\013!ECA\rF\021\025\0215\t1\001\032\021\0259\005\001\"\001I\003!!xn\025;sS:<GCA%Q!\tQUJ\004\002\n\027&\021A\nB\001\007!J,G-\0324\n\0059{%AB*ue&twM\003\002M\t!)!I\022a\0013!\"\001AU+X!\tI1+\003\002U\t\tQA-\0329sK\016\fG/\0323\"\003Y\0131\004\0265jg\002\032G.Y:tA]LG\016\034\021cK\002\022X-\\8wK\022t\023%\001-\002\rIr\023\007\r\0301\017\025Q&\001#\001\\\003!\001vn]5uS>t\007C\001\t]\r\025\t!\001#\001^'\tav\002C\003\0169\022\005q\fF\001\\\021\035\tGL1A\005\006\t\fQAT(Q\037N+\022aY\b\002Iv\t\001\001\013\003a%\032D\027%A4\002)QC\027n\035\021xS2d\007EY3!e\026lwN^3eC\005I\027!\002\032/s9\002\004BB6]A\00351-\001\004O\037B{5\013\t\005\b[r\023\r\021\"\002o\003!1\025JU*U!>\033V#A\r)\t1\024f\r\033\005\007cr\003\013QB\r\002\023\031K%k\025+Q\037N\003\003\"\002\n]\t\003\031Hc\001\013uk\")\001D\035a\0013!)QD\035a\0013!\"ALU+X\001")
/*    */ public abstract class Position {
/*    */   private final int LINE_BITS;
/*    */   
/*    */   private final int COLUMN_BITS;
/*    */   
/*    */   private final int LINE_MASK;
/*    */   
/*    */   private final int COLUMN_MASK;
/*    */   
/*    */   public static int FIRSTPOS() {
/*    */     return Position$.MODULE$.FIRSTPOS();
/*    */   }
/*    */   
/*    */   public static int NOPOS() {
/*    */     return Position$.MODULE$.NOPOS();
/*    */   }
/*    */   
/*    */   public abstract void checkInput(int paramInt1, int paramInt2);
/*    */   
/*    */   public final int LINE_BITS() {
/* 42 */     return 20;
/*    */   }
/*    */   
/*    */   public final int COLUMN_BITS() {
/* 44 */     return 11;
/*    */   }
/*    */   
/*    */   public final int LINE_MASK() {
/* 46 */     return 1048575;
/*    */   }
/*    */   
/*    */   public final int COLUMN_MASK() {
/* 48 */     return 2047;
/*    */   }
/*    */   
/*    */   public final int encode(int line, int column) {
/* 52 */     checkInput(line, column);
/* 54 */     return (line >= 1048575) ? 
/* 55 */       2147481600 : (
/*    */       
/* 57 */       line << 11 | package$.MODULE$.min(2047, column));
/*    */   }
/*    */   
/*    */   public final int line(int pos) {
/* 61 */     return pos >> 11 & 0xFFFFF;
/*    */   }
/*    */   
/*    */   public final int column(int pos) {
/* 64 */     return pos & 0x7FF;
/*    */   }
/*    */   
/*    */   public String toString(int pos) {
/* 67 */     return (new StringBuilder()).append(line(pos)).append(":").append(BoxesRunTime.boxToInteger(column(pos))).toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\Position.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */