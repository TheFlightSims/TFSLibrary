/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.xml.dtd.DTD;
/*    */ import scala.xml.dtd.EntityDecl;
/*    */ import scala.xml.dtd.NotationDecl;
/*    */ import scala.xml.pull.XMLEvent;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005%c\001B\001\003\001\035\021\001\002R8dk6,g\016\036\006\003\007\021\t1\001_7m\025\005)\021!B:dC2\f7\001A\n\005\001!a!\003\005\002\n\0255\t!!\003\002\f\005\t9aj\0343f'\026\f\bCA\007\021\033\005q!BA\b\003\003\021\001X\017\0347\n\005Eq!\001\003-N\031\0263XM\034;\021\005M!R\"\001\003\n\005U!!\001D*fe&\fG.\033>bE2,\007\"B\f\001\t\003A\022A\002\037j]&$h\bF\001\032!\tI\001\001C\005\034\001\001\007\t\031!C\0019\005A1\r[5mIJ,g.F\001\036!\rqb%\013\b\003?\021r!\001I\022\016\003\005R!A\t\004\002\rq\022xn\034;?\023\005)\021BA\023\005\003\035\001\030mY6bO\026L!a\n\025\003\007M+\027O\003\002&\tA\021\021BK\005\003W\t\021AAT8eK\"IQ\006\001a\001\002\004%\tAL\001\rG\"LG\016\032:f]~#S-\035\013\003_I\002\"a\005\031\n\005E\"!\001B+oSRDqa\r\027\002\002\003\007Q$A\002yIEBa!\016\001!B\023i\022!C2iS2$'/\0328!\021%9\004\0011AA\002\023\005\001(A\004e_\016,E.Z7\026\003%B\021B\017\001A\002\003\007I\021A\036\002\027\021|7-\0227f[~#S-\035\013\003_qBqaM\035\002\002\003\007\021\006\003\004?\001\001\006K!K\001\tI>\034W\t\\3nA!I\001\t\001a\001\002\004%\t!Q\001\004IR$W#\001\"\021\005\r+U\"\001#\013\005\001\023\021B\001$E\005\r!E\013\022\005\n\021\002\001\r\0211A\005\002%\013q\001\032;e?\022*\027\017\006\0020\025\"91gRA\001\002\004\021\005B\002'\001A\003&!)\001\003ei\022\004\003\"\002(\001\t\003y\025!\0038pi\006$\030n\0348t+\005\001\006c\001\020'#B\0211IU\005\003'\022\023ABT8uCRLwN\034#fG2DQ!\026\001\005\002Y\013\001#\0368qCJ\034X\rZ#oi&$\030.Z:\026\003]\0032A\b\024Y!\t\031\025,\003\002[\t\nQQI\034;jif$Um\0317\t\023q\003\001\031!a\001\n\003i\026a\0022bg\026,&+S\013\002=B\021qL\031\b\003'\001L!!\031\003\002\rA\023X\rZ3g\023\t\031GM\001\004TiJLgn\032\006\003C\022A\021B\032\001A\002\003\007I\021A4\002\027\t\f7/Z+S\023~#S-\035\013\003_!DqaM3\002\002\003\007a\f\003\004k\001\001\006KAX\001\tE\006\034X-\026*JA!IA\016\001a\001\002\004%\t!\\\001\tK:\034w\016Z5oOV\ta\016E\002\024_zK!\001\035\003\003\r=\003H/[8o\021%\021\b\0011AA\002\023\0051/\001\007f]\016|G-\0338h?\022*\027\017\006\0020i\"91']A\001\002\004q\007B\002<\001A\003&a.A\005f]\016|G-\0338hA!I\001\020\001a\001\002\004%\t!_\001\013gR\fg\016Z!m_:,W#\001>\021\007My7\020\005\002\024y&\021Q\020\002\002\b\005>|G.Z1o\021)y\b\0011AA\002\023\005\021\021A\001\017gR\fg\016Z!m_:,w\fJ3r)\ry\0231\001\005\bgy\f\t\0211\001{\021\035\t9\001\001Q!\ni\f1b\035;b]\022\fEn\0348fA!Q\0211\002\001A\002\003\007I\021A7\002\017Y,'o]5p]\"Y\021q\002\001A\002\003\007I\021AA\t\003-1XM]:j_:|F%Z9\025\007=\n\031\002\003\0054\003\033\t\t\0211\001o\021\035\t9\002\001Q!\n9\f\001B^3sg&|g\016\t\005\n\0037\001\001\031!C\001\003;\t\001$\0317m\t\026\034G.\031:bi&|gn\035)s_\016,7o]3e+\005Y\b\"CA\021\001\001\007I\021AA\022\003q\tG\016\034#fG2\f'/\031;j_:\034\bK]8dKN\034X\rZ0%KF$2aLA\023\021!\031\024qDA\001\002\004Y\bbBA\025\001\001\006Ka_\001\032C2dG)Z2mCJ\fG/[8ogB\023xnY3tg\026$\007\005\003\004\002.\001!\t\001H\001\007i\",7+Z9\t\017\005E\002\001\"\021\0024\005A1-\0318FcV\fG\016F\002|\003kA\001\"a\016\0020\001\007\021\021H\001\006_RDWM\035\t\004'\005m\022bAA\037\t\t\031\021I\\=)\013\001\t\t%a\022\021\007M\t\031%C\002\002F\021\021\001cU3sS\006dg+\032:tS>tW+\023#\037\021\001X4w\005\\6\033m\007")
/*    */ public class Document extends NodeSeq implements XMLEvent, Serializable {
/*    */   public static final long serialVersionUID = -2289320563321795109L;
/*    */   
/*    */   private Seq<Node> children;
/*    */   
/*    */   private Node docElem;
/*    */   
/*    */   private DTD dtd;
/*    */   
/*    */   private String baseURI;
/*    */   
/*    */   private Option<String> encoding;
/*    */   
/*    */   private Option<Object> standAlone;
/*    */   
/*    */   private Option<String> version;
/*    */   
/*    */   public Seq<Node> children() {
/* 31 */     return this.children;
/*    */   }
/*    */   
/*    */   public void children_$eq(Seq<Node> x$1) {
/* 31 */     this.children = x$1;
/*    */   }
/*    */   
/*    */   public Node docElem() {
/* 34 */     return this.docElem;
/*    */   }
/*    */   
/*    */   public void docElem_$eq(Node x$1) {
/* 34 */     this.docElem = x$1;
/*    */   }
/*    */   
/*    */   public DTD dtd() {
/* 37 */     return this.dtd;
/*    */   }
/*    */   
/*    */   public void dtd_$eq(DTD x$1) {
/* 37 */     this.dtd = x$1;
/*    */   }
/*    */   
/*    */   public Seq<NotationDecl> notations() {
/* 44 */     return dtd().notations();
/*    */   }
/*    */   
/*    */   public Seq<EntityDecl> unparsedEntities() {
/* 50 */     return dtd().unparsedEntities();
/*    */   }
/*    */   
/*    */   public String baseURI() {
/* 53 */     return this.baseURI;
/*    */   }
/*    */   
/*    */   public void baseURI_$eq(String x$1) {
/* 53 */     this.baseURI = x$1;
/*    */   }
/*    */   
/*    */   public Option<String> encoding() {
/* 58 */     return this.encoding;
/*    */   }
/*    */   
/*    */   public void encoding_$eq(Option<String> x$1) {
/* 58 */     this.encoding = x$1;
/*    */   }
/*    */   
/*    */   public Option<Object> standAlone() {
/* 66 */     return this.standAlone;
/*    */   }
/*    */   
/*    */   public void standAlone_$eq(Option<Object> x$1) {
/* 66 */     this.standAlone = x$1;
/*    */   }
/*    */   
/*    */   public Option<String> version() {
/* 73 */     return this.version;
/*    */   }
/*    */   
/*    */   public void version_$eq(Option<String> x$1) {
/* 73 */     this.version = x$1;
/*    */   }
/*    */   
/*    */   private boolean allDeclarationsProcessed = false;
/*    */   
/*    */   public boolean allDeclarationsProcessed() {
/* 81 */     return this.allDeclarationsProcessed;
/*    */   }
/*    */   
/*    */   public void allDeclarationsProcessed_$eq(boolean x$1) {
/* 81 */     this.allDeclarationsProcessed = x$1;
/*    */   }
/*    */   
/*    */   public Seq<Node> theSeq() {
/* 85 */     return (Seq<Node>)docElem();
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/*    */     boolean bool;
/* 87 */     if (other instanceof Document) {
/* 87 */       bool = true;
/*    */     } else {
/* 89 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Document.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */