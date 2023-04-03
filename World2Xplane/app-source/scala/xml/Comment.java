/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ub\001B\001\003\001\036\021qaQ8n[\026tGO\003\002\004\t\005\031\0010\0347\013\003\025\tQa]2bY\006\034\001a\005\003\001\0211\001\002CA\005\013\033\005\021\021BA\006\003\005-\031\006/Z2jC2tu\016Z3\021\0055qQ\"\001\003\n\005=!!a\002)s_\022,8\r\036\t\003\033EI!A\005\003\003\031M+'/[1mSj\f'\r\\3\t\021Q\001!Q3A\005\002U\t1bY8n[\026tG\017V3yiV\ta\003\005\002\03059\021Q\002G\005\0033\021\ta\001\025:fI\0264\027BA\016\035\005\031\031FO]5oO*\021\021\004\002\005\t=\001\021\t\022)A\005-\005a1m\\7nK:$H+\032=uA!)\001\005\001C\001C\0051A(\0338jiz\"\"AI\022\021\005%\001\001\"\002\013 \001\0041\002\"B\023\001\t\0031\023!\0027bE\026dW#A\024\021\005!jS\"A\025\013\005)Z\023\001\0027b]\036T\021\001L\001\005U\0064\030-\003\002\034S!)q\006\001C!M\005!A/\032=u\021\025\t\004\001\"\0223\003M!wnQ8mY\026\034GOT1nKN\004\030mY3t+\005\031\004CA\0075\023\t)DAA\004C_>dW-\0318\t\013]\002AQ\t\032\002\027\021|GK]1og\032|'/\034\005\006s\001!\tEO\001\fEVLG\016Z*ue&tw\r\006\002<\007B\021A(Q\007\002{)\021ahP\001\b[V$\030M\0317f\025\t\001E!\001\006d_2dWm\031;j_:L!AQ\037\003\033M#(/\0338h\005VLG\016Z3s\021\025!\005\b1\001F\003\t\031(\r\005\002G\035:\021q\t\024\b\003\021.k\021!\023\006\003\025\032\ta\001\020:p_Rt\024\"A\003\n\0055#\021a\0029bG.\fw-Z\005\003\005>S!!\024\003\t\017E\003\021\021!C\001%\006!1m\0349z)\t\0213\013C\004\025!B\005\t\031\001\f\t\017U\003\021\023!C\001-\006q1m\0349zI\021,g-Y;mi\022\nT#A,+\005YA6&A-\021\005i{V\"A.\013\005qk\026!C;oG\",7m[3e\025\tqF!\001\006b]:|G/\031;j_:L!\001Y.\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004c\001\005\005I\021\t\024\002\033A\024x\016Z;diB\023XMZ5y\021\035!\007!!A\005\002\025\fA\002\035:pIV\034G/\021:jif,\022A\032\t\003\033\035L!\001\033\003\003\007%sG\017C\004k\001\005\005I\021A6\002\035A\024x\016Z;di\026cW-\\3oiR\021An\034\t\003\0335L!A\034\003\003\007\005s\027\020C\004qS\006\005\t\031\0014\002\007a$\023\007C\004s\001\005\005I\021I:\002\037A\024x\016Z;di&#XM]1u_J,\022\001\036\t\004kZdW\"A \n\005]|$\001C%uKJ\fGo\034:\b\017e\024\021\021!E\001u\00691i\\7nK:$\bCA\005|\r\035\t!!!A\t\002q\0342a_?\021!\025q\0301\001\f#\033\005y(bAA\001\t\0059!/\0368uS6,\027bAA\003\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\r\001ZH\021AA\005)\005Q\b\"CA\007w\006\005IQIA\b\003!!xn\025;sS:<G#A\024\t\023\005M10!A\005\002\006U\021!B1qa2LHc\001\022\002\030!1A#!\005A\002YA\021\"a\007|\003\003%\t)!\b\002\017Ut\027\r\0359msR!\021qDA\023!\021i\021\021\005\f\n\007\005\rBA\001\004PaRLwN\034\005\n\003O\tI\"!AA\002\t\n1\001\037\0231\021%\tYc_A\001\n\023\ti#A\006sK\006$'+Z:pYZ,GCAA\030!\rA\023\021G\005\004\003gI#AB(cU\026\034G\017")
/*    */ public class Comment extends SpecialNode implements Product, Serializable {
/*    */   private final String commentText;
/*    */   
/*    */   public String commentText() {
/* 16 */     return this.commentText;
/*    */   }
/*    */   
/*    */   public Comment copy(String commentText) {
/* 16 */     return new Comment(commentText);
/*    */   }
/*    */   
/*    */   public String copy$default$1() {
/* 16 */     return commentText();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 16 */     return "Comment";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 16 */     return 1;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 16 */     switch (x$1) {
/*    */       default:
/* 16 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 16 */     return commentText();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 16 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public Comment(String commentText) {
/* 16 */     Product.class.$init$(this);
/* 23 */     if (commentText.contains("--"))
/* 24 */       throw new IllegalArgumentException("text contains \"--\""); 
/*    */   }
/*    */   
/*    */   public String label() {
/*    */     return "#REM";
/*    */   }
/*    */   
/*    */   public String text() {
/*    */     return "";
/*    */   }
/*    */   
/*    */   public final boolean doCollectNamespaces() {
/*    */     return false;
/*    */   }
/*    */   
/*    */   public final boolean doTransform() {
/*    */     return false;
/*    */   }
/*    */   
/*    */   public StringBuilder buildString(StringBuilder sb) {
/* 29 */     return sb.append("<!--").append(commentText()).append("-->");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Comment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */