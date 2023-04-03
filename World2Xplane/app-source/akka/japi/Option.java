/*     */ package akka.japi;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tMb!B\001\003\003C9!AB(qi&|gN\003\002\004\t\005!!.\0319j\025\005)\021\001B1lW\006\034\001!\006\002\t-M\031\001!C\t\021\005)yQ\"A\006\013\0051i\021\001\0027b]\036T\021AD\001\005U\0064\030-\003\002\021\027\t1qJ\0316fGR\0042A\003\n\025\023\t\0312B\001\005Ji\026\024\030M\0317f!\t)b\003\004\001\005\013]\001!\031\001\r\003\003\005\013\"!G\020\021\005iiR\"A\016\013\003q\tQa]2bY\006L!AH\016\003\0179{G\017[5oOB\021!\004I\005\003Cm\0211!\0218z\021\025\031\003\001\"\001%\003\031a\024N\\5u}Q\tQ\005E\002'\001Qi\021A\001\005\006Q\0011\t!K\001\004O\026$X#\001\013\t\013-\002a\021\001\027\002\017%\034X)\0349usV\tQ\006\005\002\033]%\021qf\007\002\b\005>|G.Z1o\021\025\t\004\001\"\001-\003%I7\017R3gS:,G\rC\0034\001\031\005A'A\004bgN\033\027\r\\1\026\003U\0022A\007\034\025\023\t\t1\004C\0039\001\021\005\021(\001\005ji\026\024\030\r^8s)\005Q\004cA\036?)5\tAH\003\002>\033\005!Q\017^5m\023\tyDH\001\005Ji\026\024\030\r^8sS\r\001\021I\034\004\007\005\016CI)a>\003\t9{g.\032\004\006\003\tA\t\001R\n\003\007\026\003\"A\007$\n\005\035[\"AB!osJ+g\rC\003$\007\022\005\021\nF\001K!\t13\tC\003M\007\022\005Q*\001\003t_6,WC\001(R)\ty%\013E\002'\001A\003\"!F)\005\013]Y%\031\001\r\t\013M[\005\031\001)\002\003YDQ!V\"\005\002Y\013AA\\8oKV\021qKW\013\0021B\031a\005A-\021\005UQF!B\fU\005\004A\002\"\002/D\t\003i\026AB8qi&|g.\006\002_CR\021qL\031\t\004M\001\001\007CA\013b\t\02592L1\001\031\021\025\0316\f1\001a\021\025!7\t\"\001f\003=1'o\\7TG\006d\027m\0249uS>tWC\0014j)\t97\016E\002'\001!\004\"!F5\005\013)\034'\031\001\r\003\003QCQ\001\\2A\0025\f1b]2bY\006|\005\017^5p]B\031!D\0165\007\t=\034%\t\035\002\005'>lW-\006\002riN!aN];y!\r1\003a\035\t\003+Q$Qa\0068C\002a\001\"A\007<\n\005]\\\"a\002)s_\022,8\r\036\t\0035eL!A_\016\003\031M+'/[1mSj\f'\r\\3\t\021Ms'Q3A\005\002q,\022a\035\005\t}:\024\t\022)A\005g\006\021a\017\t\005\007G9$\t!!\001\025\t\005\r\021q\001\t\005\003\013q7/D\001D\021\025\031v\0201\001t\021\025Ac\016\"\001}\021\025Yc\016\"\001-\021\031\031d\016\"\001\002\020U\021\021\021\003\t\0055\005M1/\003\002p7!I\021q\0038\002\002\023\005\021\021D\001\005G>\004\0300\006\003\002\034\005\005B\003BA\017\003G\001R!!\002o\003?\0012!FA\021\t\0319\022Q\003b\0011!I1+!\006\021\002\003\007\021q\004\005\n\003Oq\027\023!C\001\003S\tabY8qs\022\"WMZ1vYR$\023'\006\003\002,\005\005SCAA\027U\r\031\030qF\026\003\003c\001B!a\r\002>5\021\021Q\007\006\005\003o\tI$A\005v]\016DWmY6fI*\031\0211H\016\002\025\005tgn\034;bi&|g.\003\003\002@\005U\"!E;oG\",7m[3e-\006\024\030.\0318dK\0221q#!\nC\002aA\021\"!\022o\003\003%\t%a\022\002\033A\024x\016Z;diB\023XMZ5y+\t\tI\005E\002\013\003\027J1!!\024\f\005\031\031FO]5oO\"I\021\021\0138\002\002\023\005\0211K\001\raJ|G-^2u\003JLG/_\013\003\003+\0022AGA,\023\r\tIf\007\002\004\023:$\b\"CA/]\006\005I\021AA0\0039\001(o\0343vGR,E.Z7f]R$2aHA1\021)\t\031'a\027\002\002\003\007\021QK\001\004q\022\n\004\"CA4]\006\005I\021IA5\003=\001(o\0343vGRLE/\032:bi>\024XCAA6!\025\ti'a\035 \033\t\tyGC\002\002rm\t!bY8mY\026\034G/[8o\023\ry\024q\016\005\n\003or\027\021!C\001\003s\n\001bY1o\013F,\030\r\034\013\004[\005m\004\"CA2\003k\n\t\0211\001 \021%\tyH\\A\001\n\003\n\t)\001\005iCND7i\0343f)\t\t)\006C\005\002\006:\f\t\021\"\021\002\b\006AAo\\*ue&tw\r\006\002\002J!I\0211\0228\002\002\023\005\023QR\001\007KF,\030\r\\:\025\0075\ny\tC\005\002d\005%\025\021!a\001?\035I\0211S\"\002\002#\005\021QS\001\005'>lW\r\005\003\002\006\005]e\001C8D\003\003E\t!!'\024\t\005]U\t\037\005\bG\005]E\021AAO)\t\t)\n\003\006\002\006\006]\025\021!C#\003\017C!\"a)\002\030\006\005I\021QAS\003\025\t\007\017\0357z+\021\t9+!,\025\t\005%\026q\026\t\006\003\013q\0271\026\t\004+\0055FAB\f\002\"\n\007\001\004C\004T\003C\003\r!a+\t\025\005M\026qSA\001\n\003\013),A\004v]\006\004\b\017\\=\026\t\005]\026Q\030\013\005\003s\013y\f\005\003\033m\005m\006cA\013\002>\0221q#!-C\002aA!\"!1\0022\006\005\t\031AAb\003\rAH\005\r\t\006\003\013q\0271\030\005\013\003\017\f9*!A\005\n\005%\027a\003:fC\022\024Vm]8mm\026$\022!C\004\b\003\033\034\005\022RAh\003\021quN\\3\021\007\005\025\021\tC\004\002T\016#\031!!6\002!)\fg/\031\032TG\006d\027m\0249uS>tW\003BAl\003;$B!!7\002`B!!DNAn!\r)\022Q\034\003\007/\005E'\031\001\r\t\021\005\005\030\021\033a\001\003G\f\021a\034\t\005M\001\tY\016C\004\002h\016#\031!!;\002!M\034\027\r\\13\025\0064\030m\0249uS>tW\003BAv\003c$B!!<\002tB!a\005AAx!\r)\022\021\037\003\007/\005\025(\031\001\r\t\021\005\005\030Q\035a\001\003k\004BA\007\034\002pN)\021)!?vqB\031a\005A\r\t\r\r\nE\021AA)\t\ty\r\003\004)\003\022\005!\021A\013\0023!)1&\021C\001Y!11'\021C\001\005\017)\"A!\003\017\t\t-!Q\003\b\005\005\033\021\031\"\004\002\003\020)\031!\021\003\004\002\rq\022xn\034;?\023\005a\022bAAg7!I\021QI!\002\002\023\005\023q\t\005\n\003#\n\025\021!C\001\003'B\021\"!\030B\003\003%\tA!\b\025\007}\021y\002\003\006\002d\tm\021\021!a\001\003+B\021\"a\032B\003\003%\t%!\033\t\023\005]\024)!A\005\002\t\025BcA\027\003(!I\0211\rB\022\003\003\005\ra\b\005\n\003\n\025\021!C!\003\003C\021\"!\"B\003\003%\t%a\"\t\023\005\035\027)!A\005\n\005%wA\002B\031\005!\005!*\001\004PaRLwN\034")
/*     */ public abstract class Option<A> implements Iterable<A> {
/*     */   public static <A> Option<A> scala2JavaOption(scala.Option<A> paramOption) {
/*     */     return Option$.MODULE$.scala2JavaOption(paramOption);
/*     */   }
/*     */   
/*     */   public static <A> scala.Option<A> java2ScalaOption(Option<A> paramOption) {
/*     */     return Option$.MODULE$.java2ScalaOption(paramOption);
/*     */   }
/*     */   
/*     */   public static <T> Option<T> fromScalaOption(scala.Option<T> paramOption) {
/*     */     return Option$.MODULE$.fromScalaOption(paramOption);
/*     */   }
/*     */   
/*     */   public static <A> Option<A> option(A paramA) {
/*     */     return Option$.MODULE$.option(paramA);
/*     */   }
/*     */   
/*     */   public static <A> Option<A> none() {
/*     */     return Option$.MODULE$.none();
/*     */   }
/*     */   
/*     */   public static <A> Option<A> some(A paramA) {
/*     */     return Option$.MODULE$.some(paramA);
/*     */   }
/*     */   
/*     */   public abstract A get();
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */   
/*     */   public boolean isDefined() {
/* 134 */     return !isEmpty();
/*     */   }
/*     */   
/*     */   public abstract scala.Option<A> asScala();
/*     */   
/*     */   public Iterator<A> iterator() {
/* 136 */     return isEmpty() ? Collections.<A>emptyList().iterator() : Collections.<A>singletonList(get()).iterator();
/*     */   }
/*     */   
/*     */   public static class Some<A> extends Option<A> implements Product, Serializable {
/*     */     private final A v;
/*     */     
/*     */     public A v() {
/* 168 */       return this.v;
/*     */     }
/*     */     
/*     */     public <A> Some<A> copy(Object v) {
/* 168 */       return new Some((A)v);
/*     */     }
/*     */     
/*     */     public <A> A copy$default$1() {
/* 168 */       return v();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 168 */       return "Some";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 168 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 168 */       int i = x$1;
/* 168 */       switch (i) {
/*     */         default:
/* 168 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 168 */       return v();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 168 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 168 */       return x$1 instanceof Some;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 168 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 168 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/* 168 */       if (this != x$1) {
/*     */         boolean bool;
/* 168 */         Object object = x$1;
/* 168 */         if (object instanceof Some) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/* 168 */         if (bool) {
/* 168 */           Some some = (Some)x$1;
/* 168 */           if (BoxesRunTime.equals(v(), some.v()));
/*     */         } 
/* 168 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Some(Object v) {
/* 168 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public A get() {
/* 169 */       return v();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 170 */       return false;
/*     */     }
/*     */     
/*     */     public scala.Some<A> asScala() {
/* 171 */       return new scala.Some(v());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Some$ implements Serializable {
/*     */     public static final Some$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Some";
/*     */     }
/*     */     
/*     */     public <A> Option.Some<A> apply(Object v) {
/*     */       return new Option.Some<A>((A)v);
/*     */     }
/*     */     
/*     */     public <A> scala.Option<A> unapply(Option.Some x$0) {
/*     */       return (x$0 == null) ? (scala.Option<A>)scala.None$.MODULE$ : (scala.Option<A>)new scala.Some(x$0.v());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Some$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class None$ extends Option<scala.runtime.Nothing$> implements Product, Serializable {
/*     */     public static final None$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 177 */       return "None";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 177 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 177 */       int i = x$1;
/* 177 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 177 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 177 */       return x$1 instanceof None$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 177 */       return 2433880;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 177 */       return "None";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 177 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public None$() {
/* 177 */       MODULE$ = this;
/* 177 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public scala.runtime.Nothing$ get() {
/* 178 */       throw new NoSuchElementException("None.get");
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 179 */       return true;
/*     */     }
/*     */     
/*     */     public scala.None$ asScala() {
/* 180 */       return scala.None$.MODULE$;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Option.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */