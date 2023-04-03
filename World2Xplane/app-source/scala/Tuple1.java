/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]c\001B\001\003\001\026\021a\001V;qY\026\f$\"A\002\002\013M\034\027\r\\1\004\001U\021a\001E\n\006\001\035Y\021\006\f\t\003\021%i\021AA\005\003\025\t\021a!\0218z%\0264\007c\001\005\r\035%\021QB\001\002\t!J|G-^2ucA\021q\002\005\007\001\t%\t\002\001)A\001\n\013\007!C\001\002UcE\0211C\006\t\003\021QI!!\006\002\003\0179{G\017[5oOB\021\001bF\005\0031\t\0211!\0218zQ\025\001\"$H\021&!\tA1$\003\002\035\005\tY1\017]3dS\006d\027N_3ec\021!cd\b\021\017\005!y\022B\001\021\003\003\rIe\016^\031\005I\t\032CE\004\002\tG%\021AEA\001\005\031>tw-\r\003%M\035BcB\001\005(\023\tA#!\001\004E_V\024G.\032\t\003\021)J!a\013\002\003\017A\023x\016Z;diB\021\001\"L\005\003]\t\021AbU3sS\006d\027N_1cY\026D\001\002\r\001\003\026\004%\t!M\001\003?F*\022A\004\005\tg\001\021\t\022)A\005\035\005\031q,\r\021\t\013U\002A\021\001\034\002\rqJg.\033;?)\t9\004\bE\002\t\0019AQ\001\r\033A\0029AQA\017\001\005Bm\n\001\002^8TiJLgn\032\013\002yA\021QHQ\007\002})\021q\bQ\001\005Y\006twMC\001B\003\021Q\027M^1\n\005\rs$AB*ue&tw\rC\004F\001\005\005I\021\001$\002\t\r|\007/_\013\003\017*#\"\001S(\021\007!\001\021\n\005\002\020\025\022I\021\003\022Q\001\002\003\025\rA\005\025\006\025jaUJT\031\005Iyy\002%\r\003%E\r\"\023\007\002\023'O!Bq\001\r#\021\002\003\007\021\nC\004R\001E\005I\021\001*\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\0211KX\013\002)*\022a\"V\026\002-B\021q\013X\007\0021*\021\021LW\001\nk:\034\007.Z2lK\022T!a\027\002\002\025\005tgn\034;bi&|g.\003\002^1\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\005\023E\001\006\025!A\001\006\004\021\002&\0020\033A\006\024\027\007\002\023\037?\001\nD\001\n\022$IE\"AEJ\024)\021\035!\007!!A\005B\025\fQ\002\035:pIV\034G\017\025:fM&DX#\001\037\t\017\035\004\021\021!C!Q\006y\001O]8ek\016$\030\n^3sCR|'/F\001j!\rQWNF\007\002W*\021ANA\001\013G>dG.Z2uS>t\027B\0018l\005!IE/\032:bi>\024\bb\0029\001\003\003%\t!]\001\tG\006tW)];bYR\021!/\036\t\003\021ML!\001\036\002\003\017\t{w\016\\3b]\"9ao\\A\001\002\0041\022a\001=%c!9\001\020AA\001\n\003J\030\001\0035bg\"\034u\016Z3\025\003i\004\"\001C>\n\005q\024!aA%oi\"9a\020AA\001\n\003z\030AB3rk\006d7\017F\002s\003\003AqA^?\002\002\003\007acB\005\002\006\t\t\t\021#\001\002\b\0051A+\0369mKF\0022\001CA\005\r!\t!!!A\t\002\005-1\003BA\005\0171Bq!NA\005\t\003\ty\001\006\002\002\b!A!(!\003\002\002\023\0253\b\003\006\002\026\005%\021\021!CA\003/\tQ!\0319qYf,B!!\007\002 Q!\0211DA\025!\021A\001!!\b\021\007=\ty\002\002\006\022\003'\001\013\021!AC\002IA\023\"a\b\033\003G\t)#a\n2\t\021rr\004I\031\005I\t\032C%\r\003%M\035B\003b\002\031\002\024\001\007\021Q\004\005\013\003[\tI!!A\005\002\006=\022aB;oCB\004H._\013\005\003c\tY\004\006\003\0024\005\025\003#\002\005\0026\005e\022bAA\034\005\t1q\n\035;j_:\0042aDA\036\t)\t\0221\006Q\001\002\003\025\rA\005\025\n\003wQ\022qHA!\003\007\nD\001\n\020 AE\"AEI\022%c\021!ce\n\025\t\025\005\035\0231FA\001\002\004\tI%A\002yIA\002B\001\003\001\002:!Q\021QJA\005\003\003%I!a\024\002\027I,\027\r\032*fg>dg/\032\013\003\003#\0022!PA*\023\r\t)F\020\002\007\037\nTWm\031;")
/*     */ public class Tuple1<T1> implements Product1<T1>, Product, Serializable {
/*     */   public final T1 _1;
/*     */   
/*     */   public int productArity() {
/*  18 */     return Product1$class.productArity(this);
/*     */   }
/*     */   
/*     */   public Object productElement(int n) throws IndexOutOfBoundsException {
/*  18 */     return Product1$class.productElement(this, n);
/*     */   }
/*     */   
/*     */   public T1 _1() {
/*  18 */     return this._1;
/*     */   }
/*     */   
/*     */   public <T1> Tuple1<T1> copy(Object _1) {
/*  18 */     return new Tuple1((T1)_1);
/*     */   }
/*     */   
/*     */   public <T1> T1 copy$default$1() {
/*  18 */     return _1();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  18 */     return "Tuple1";
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  18 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  18 */     return x$1 instanceof Tuple1;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  18 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*  18 */     if (this != x$1) {
/*     */       boolean bool;
/*  18 */       if (x$1 instanceof Tuple1) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Tuple1<Object> tuple1 = (Tuple1)x$1;
/*     */         T1 t12 = (T1)tuple1._1();
/*     */         T1 t11;
/*     */         if (((((t11 = _1()) == t12) ? true : ((t11 == null) ? false : ((t11 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)t11, t12) : ((t11 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)t11, t12) : t11.equals(t12))))) && tuple1.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double _1$mcD$sp() {
/*     */     return BoxesRunTime.unboxToDouble(_1());
/*     */   }
/*     */   
/*     */   public int _1$mcI$sp() {
/*     */     return BoxesRunTime.unboxToInt(_1());
/*     */   }
/*     */   
/*     */   public long _1$mcJ$sp() {
/*     */     return BoxesRunTime.unboxToLong(_1());
/*     */   }
/*     */   
/*     */   public Tuple1<Object> copy$mDc$sp(double _1) {
/*     */     return new Tuple1$mcD$sp(_1);
/*     */   }
/*     */   
/*     */   public Tuple1<Object> copy$mIc$sp(int _1) {
/*     */     return new Tuple1$mcI$sp(_1);
/*     */   }
/*     */   
/*     */   public Tuple1<Object> copy$mJc$sp(long _1) {
/*     */     return new Tuple1$mcJ$sp(_1);
/*     */   }
/*     */   
/*     */   public <T1> double copy$default$1$mcD$sp() {
/*     */     return BoxesRunTime.unboxToDouble(copy$default$1());
/*     */   }
/*     */   
/*     */   public <T1> int copy$default$1$mcI$sp() {
/*     */     return BoxesRunTime.unboxToInt(copy$default$1());
/*     */   }
/*     */   
/*     */   public <T1> long copy$default$1$mcJ$sp() {
/*     */     return BoxesRunTime.unboxToLong(copy$default$1());
/*     */   }
/*     */   
/*     */   public boolean specInstance$() {
/*     */     return false;
/*     */   }
/*     */   
/*     */   public Tuple1(Object _1) {
/*     */     Product$class.$init$(this);
/*     */     Product1$class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return (new StringBuilder()).append("(").append(_1()).append(")").toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */