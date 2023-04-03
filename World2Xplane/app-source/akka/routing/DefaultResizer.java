/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefWithCell;
/*     */ import akka.actor.Cell;
/*     */ import com.typesafe.config.Config;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005t!B\001\003\021\003;\021A\004#fM\006,H\016\036*fg&TXM\035\006\003\007\021\tqA]8vi&twMC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001EA\027\tqA)\0324bk2$(+Z:ju\026\0248\003B\005\r%U\001\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021a!\0218z%\0264\007CA\007\024\023\t!bBA\004Qe>$Wo\031;\021\00551\022BA\f\017\0051\031VM]5bY&T\030M\0317f\021\025I\022\002\"\001\033\003\031a\024N\\5u}Q\tq\001C\003\035\023\021\005Q$A\003baBd\027\020F\002\037\003{\003\"\001C\020\007\t)\021\001\tI\n\006?1\t##\006\t\003\021\tJ!a\t\002\003\017I+7/\033>fe\"AQe\bBK\002\023\005a%\001\006m_^,'OQ8v]\022,\022a\n\t\003\033!J!!\013\b\003\007%sG\017\003\005,?\tE\t\025!\003(\003-awn^3s\005>,h\016\032\021\t\0215z\"Q3A\005\002\031\n!\"\0369qKJ\024u.\0368e\021!ysD!E!\002\0239\023aC;qa\026\024(i\\;oI\002B\001\"M\020\003\026\004%\tAJ\001\022aJ,7o];sKRC'/Z:i_2$\007\002C\032 \005#\005\013\021B\024\002%A\024Xm]:ve\026$\006N]3tQ>dG\r\t\005\tk}\021)\032!C\001m\005Q!/Y7qkB\024\026\r^3\026\003]\002\"!\004\035\n\005er!A\002#pk\ndW\r\003\005<?\tE\t\025!\0038\003-\021\030-\0349vaJ\013G/\032\021\t\021uz\"Q3A\005\002Y\n\001CY1dW>4g\r\0265sKNDw\016\0343\t\021}z\"\021#Q\001\n]\n\021CY1dW>4g\r\0265sKNDw\016\0343!\021!\tuD!f\001\n\0031\024a\0032bG.|gM\032*bi\026D\001bQ\020\003\022\003\006IaN\001\rE\006\0347n\0344g%\006$X\r\t\005\t\013~\021)\032!C\001M\005\tR.Z:tC\036,7\017U3s%\026\034\030N_3\t\021\035{\"\021#Q\001\n\035\n!#\\3tg\006<Wm\035)feJ+7/\033>fA!)\021d\bC\001\023RAaDS&M\033:{\005\013C\004&\021B\005\t\031A\024\t\0175B\005\023!a\001O!9\021\007\023I\001\002\0049\003bB\033I!\003\005\ra\016\005\b{!\003\n\0211\0018\021\035\t\005\n%AA\002]Bq!\022%\021\002\003\007q\005C\003\032?\021\005!\013F\002\037'VCQ\001V)A\002\035\nQ\001\\8xKJDQAV)A\002\035\nQ!\0369qKJDQ\001W\020\005\002e\013q\"[:US6,gi\034:SKNL'0\032\013\0035v\003\"!D.\n\005qs!a\002\"p_2,\027M\034\005\006=^\003\raX\001\017[\026\0348/Y4f\007>,h\016^3s!\ti\001-\003\002b\035\t!Aj\0348h\021\025\031w\004\"\021e\003\031\021Xm]5{KR\021q%\032\005\006M\n\004\raZ\001\017GV\024(/\0328u%>,H/Z3t!\rAWn\\\007\002S*\021!n[\001\nS6lW\017^1cY\026T!\001\034\b\002\025\r|G\016\\3di&|g.\003\002oS\nQ\021J\0343fq\026$7+Z9\021\005!\001\030BA9\003\005\031\021v.\036;fK\")1o\bC\001i\006A1-\0319bG&$\030\020\006\002(k\")aO\035a\001O\0069!o\\;uK\026\034\b\"\002= \t\003I\030\001\0039sKN\034XO]3\025\005\035R\b\"\002<x\001\0049\007\"\002? \t\003i\030A\0024jYR,'\017F\002(}~DQ\001_>A\002\035BQa]>A\002\035Bq!a\001 \t\003\t)!\001\004sC6\004X\017\035\013\006O\005\035\021\021\002\005\007q\006\005\001\031A\024\t\rM\f\t\0011\001(\021\035\tia\bC\001\003\037\tqAY1dW>4g\rF\003(\003#\t\031\002\003\004y\003\027\001\ra\n\005\007g\006-\001\031A\024\t\023\005]q$!A\005\002\005e\021\001B2paf$rBHA\016\003;\ty\"!\t\002$\005\025\022q\005\005\tK\005U\001\023!a\001O!AQ&!\006\021\002\003\007q\005\003\0052\003+\001\n\0211\001(\021!)\024Q\003I\001\002\0049\004\002C\037\002\026A\005\t\031A\034\t\021\005\013)\002%AA\002]B\001\"RA\013!\003\005\ra\n\005\n\003Wy\022\023!C\001\003[\tabY8qs\022\"WMZ1vYR$\023'\006\002\0020)\032q%!\r,\005\005M\002\003BA\033\003i!!a\016\013\t\005e\0221H\001\nk:\034\007.Z2lK\022T1!!\020\017\003)\tgN\\8uCRLwN\\\005\005\003\003\n9DA\tv]\016DWmY6fIZ\013'/[1oG\026D\021\"!\022 #\003%\t!!\f\002\035\r|\007/\037\023eK\032\fW\017\034;%e!I\021\021J\020\022\002\023\005\021QF\001\017G>\004\030\020\n3fM\006,H\016\036\0234\021%\tieHI\001\n\003\ty%\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\005E#fA\034\0022!I\021QK\020\022\002\023\005\021qJ\001\017G>\004\030\020\n3fM\006,H\016\036\0236\021%\tIfHI\001\n\003\ty%\001\bd_BLH\005Z3gCVdG\017\n\034\t\023\005us$%A\005\002\0055\022AD2paf$C-\0324bk2$He\016\005\n\003Cz\022\021!C!\003G\nQ\002\035:pIV\034G\017\025:fM&DXCAA3!\021\t9'!\035\016\005\005%$\002BA6\003[\nA\001\\1oO*\021\021qN\001\005U\0064\030-\003\003\002t\005%$AB*ue&tw\r\003\005\002x}\t\t\021\"\001'\0031\001(o\0343vGR\f%/\033;z\021%\tYhHA\001\n\003\ti(\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005}\024Q\021\t\004\033\005\005\025bAAB\035\t\031\021I\\=\t\023\005\035\025\021PA\001\002\0049\023a\001=%c!I\0211R\020\002\002\023\005\023QR\001\020aJ|G-^2u\023R,'/\031;peV\021\021q\022\t\007\003#\013\031*a \016\003-L1!!&l\005!IE/\032:bi>\024\b\"CAM?\005\005I\021AAN\003!\031\027M\\#rk\006dGc\001.\002\036\"Q\021qQAL\003\003\005\r!a \t\023\005\005v$!A\005B\005\r\026\001\0035bg\"\034u\016Z3\025\003\035B\021\"a* \003\003%\t%!+\002\021Q|7\013\036:j]\036$\"!!\032\t\023\0055v$!A\005B\005=\026AB3rk\006d7\017F\002[\003cC!\"a\"\002,\006\005\t\031AA@Q\025y\022QWA^!\ri\021qW\005\004\003ss!\001E*fe&\fGNV3sg&|g.V%E=\005\t\001bBA`7\001\007\021\021Y\001\016e\026\034\030N_3s\007>tg-[4\021\t\005\r\027\021[\007\003\003\013TA!a2\002J\00611m\0348gS\036TA!a3\002N\006AA/\0379fg\0064WM\003\002\002P\006\0311m\\7\n\t\005M\027Q\031\002\007\007>tg-[4\t\017\005]\027\002\"\001\002Z\006QaM]8n\007>tg-[4\025\t\005m\027\021\035\t\005\033\005ug$C\002\002`:\021aa\0249uS>t\007\002CA`\003+\004\r!!1\t\021qI\021\021!CA\003K$rBHAt\003S\fY/!<\002p\006E\0301\037\005\tK\005\r\b\023!a\001O!AQ&a9\021\002\003\007q\005\003\0052\003G\004\n\0211\001(\021!)\0241\035I\001\002\0049\004\002C\037\002dB\005\t\031A\034\t\021\005\013\031\017%AA\002]B\001\"RAr!\003\005\ra\n\005\n\003oL\021\021!CA\003s\fq!\0368baBd\027\020\006\003\002|\n\r\001#B\007\002^\006u\bCC\007\002\000\036:seN\0348O%\031!\021\001\b\003\rQ+\b\017\\38\021%\021)!!>\002\002\003\007a$A\002yIAB\021B!\003\n#\003%\t!!\f\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0232\021%\021i!CI\001\n\003\ti#A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$HE\r\005\n\005#I\021\023!C\001\003[\t1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\032\004\"\003B\013\023E\005I\021AA(\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%i!I!\021D\005\022\002\023\005\021qJ\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\033\t\023\tu\021\"%A\005\002\005=\023a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$c\007C\005\003\"%\t\n\021\"\001\002.\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uI]B\021B!\n\n#\003%\t!!\f\002\037\005\004\b\017\\=%I\0264\027-\0367uIEB\021B!\013\n#\003%\t!!\f\002\037\005\004\b\017\\=%I\0264\027-\0367uIIB\021B!\f\n#\003%\t!!\f\002\037\005\004\b\017\\=%I\0264\027-\0367uIMB\021B!\r\n#\003%\t!a\024\002\037\005\004\b\017\\=%I\0264\027-\0367uIQB\021B!\016\n#\003%\t!a\024\002\037\005\004\b\017\\=%I\0264\027-\0367uIUB\021B!\017\n#\003%\t!a\024\002\037\005\004\b\017\\=%I\0264\027-\0367uIYB\021B!\020\n#\003%\t!!\f\002\037\005\004\b\017\\=%I\0264\027-\0367uI]B\021\"!\031\n\003\003%\t%a\031\t\021\005]\024\"!A\005\002\031B\021\"a\037\n\003\003%\tA!\022\025\t\005}$q\t\005\n\003\017\023\031%!AA\002\035B\021\"a#\n\003\003%\t%!$\t\023\005e\025\"!A\005\002\t5Cc\001.\003P!Q\021q\021B&\003\003\005\r!a \t\023\005\005\026\"!A\005B\005\r\006\"CAT\023\005\005I\021IAU\021%\0219&CA\001\n\023\021I&A\006sK\006$'+Z:pYZ,GC\001B.!\021\t9G!\030\n\t\t}\023\021\016\002\007\037\nTWm\031;")
/*     */ public class DefaultResizer implements Resizer, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int lowerBound;
/*     */   
/*     */   private final int upperBound;
/*     */   
/*     */   private final int pressureThreshold;
/*     */   
/*     */   private final double rampupRate;
/*     */   
/*     */   private final double backoffThreshold;
/*     */   
/*     */   private final double backoffRate;
/*     */   
/*     */   private final int messagesPerResize;
/*     */   
/*     */   public DefaultResizer copy(int lowerBound, int upperBound, int pressureThreshold, double rampupRate, double backoffThreshold, double backoffRate, int messagesPerResize) {
/*  85 */     return new DefaultResizer(
/*     */         
/*  89 */         lowerBound, 
/*     */         
/*  94 */         upperBound, 
/*     */         
/* 107 */         pressureThreshold, 
/*     */         
/* 113 */         rampupRate, 
/*     */         
/* 123 */         backoffThreshold, 
/*     */         
/* 130 */         backoffRate, 
/*     */         
/* 135 */         messagesPerResize);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "DefaultResizer";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 7;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 6:
/*     */       
/*     */       case 5:
/*     */       
/*     */       case 4:
/*     */       
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return BoxesRunTime.boxToInteger(lowerBound());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof DefaultResizer;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, lowerBound());
/*     */     i = Statics.mix(i, upperBound());
/*     */     i = Statics.mix(i, pressureThreshold());
/*     */     i = Statics.mix(i, Statics.doubleHash(rampupRate()));
/*     */     i = Statics.mix(i, Statics.doubleHash(backoffThreshold()));
/*     */     i = Statics.mix(i, Statics.doubleHash(backoffRate()));
/*     */     i = Statics.mix(i, messagesPerResize());
/*     */     return Statics.finalizeHash(i, 7);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     if (this != x$1) {
/*     */       boolean bool;
/*     */       Object object = x$1;
/*     */       if (object instanceof DefaultResizer) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         DefaultResizer defaultResizer = (DefaultResizer)x$1;
/*     */         if ((lowerBound() == defaultResizer.lowerBound() && upperBound() == defaultResizer.upperBound() && pressureThreshold() == defaultResizer.pressureThreshold() && rampupRate() == defaultResizer.rampupRate() && backoffThreshold() == defaultResizer.backoffThreshold() && backoffRate() == defaultResizer.backoffRate() && messagesPerResize() == defaultResizer.messagesPerResize() && defaultResizer.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lowerBound() {
/*     */     return this.lowerBound;
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*     */     return lowerBound();
/*     */   }
/*     */   
/*     */   public DefaultResizer(int lowerBound, int upperBound, int pressureThreshold, double rampupRate, double backoffThreshold, double backoffRate, int messagesPerResize) {
/*     */     Product.class.$init$(this);
/* 142 */     if (lowerBound < 0)
/* 142 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("lowerBound must be >= 0, was: [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(lowerBound) }))); 
/* 143 */     if (upperBound < 0)
/* 143 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("upperBound must be >= 0, was: [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(upperBound) }))); 
/* 144 */     if (upperBound < lowerBound)
/* 144 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("upperBound must be >= lowerBound, was: [%s] < [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(upperBound), BoxesRunTime.boxToInteger(lowerBound) }))); 
/* 145 */     if (rampupRate < 0.0D)
/* 145 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("rampupRate must be >= 0.0, was [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToDouble(rampupRate) }))); 
/* 146 */     if (backoffThreshold > 1.0D)
/* 146 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("backoffThreshold must be <= 1.0, was [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToDouble(backoffThreshold) }))); 
/* 147 */     if (backoffRate < 0.0D)
/* 147 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("backoffRate must be >= 0.0, was [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToDouble(backoffRate) }))); 
/* 148 */     if (messagesPerResize <= 0)
/* 148 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("messagesPerResize must be > 0, was [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(messagesPerResize) }))); 
/*     */   }
/*     */   
/*     */   public int upperBound() {
/*     */     return this.upperBound;
/*     */   }
/*     */   
/*     */   public int copy$default$2() {
/*     */     return upperBound();
/*     */   }
/*     */   
/*     */   public int pressureThreshold() {
/*     */     return this.pressureThreshold;
/*     */   }
/*     */   
/*     */   public int copy$default$3() {
/*     */     return pressureThreshold();
/*     */   }
/*     */   
/*     */   public double rampupRate() {
/*     */     return this.rampupRate;
/*     */   }
/*     */   
/*     */   public double copy$default$4() {
/*     */     return rampupRate();
/*     */   }
/*     */   
/*     */   public double backoffThreshold() {
/*     */     return this.backoffThreshold;
/*     */   }
/*     */   
/*     */   public double copy$default$5() {
/*     */     return backoffThreshold();
/*     */   }
/*     */   
/*     */   public double backoffRate() {
/*     */     return this.backoffRate;
/*     */   }
/*     */   
/*     */   public double copy$default$6() {
/*     */     return backoffRate();
/*     */   }
/*     */   
/*     */   public int messagesPerResize() {
/*     */     return this.messagesPerResize;
/*     */   }
/*     */   
/*     */   public int copy$default$7() {
/*     */     return messagesPerResize();
/*     */   }
/*     */   
/*     */   public DefaultResizer(int lower, int upper) {
/*     */     this(lower, upper, DefaultResizer$.MODULE$.$lessinit$greater$default$3(), DefaultResizer$.MODULE$.$lessinit$greater$default$4(), DefaultResizer$.MODULE$.$lessinit$greater$default$5(), DefaultResizer$.MODULE$.$lessinit$greater$default$6(), DefaultResizer$.MODULE$.$lessinit$greater$default$7());
/*     */   }
/*     */   
/*     */   public boolean isTimeForResize(long messageCounter) {
/* 150 */     return (messageCounter % messagesPerResize() == 0L);
/*     */   }
/*     */   
/*     */   public int resize(IndexedSeq<Routee> currentRoutees) {
/* 153 */     return capacity(currentRoutees);
/*     */   }
/*     */   
/*     */   public int capacity(IndexedSeq<Routee> routees) {
/* 163 */     int currentSize = routees.size();
/* 164 */     int press = pressure(routees);
/* 165 */     int delta = filter(press, currentSize);
/* 166 */     int proposed = currentSize + delta;
/* 168 */     return (proposed < lowerBound()) ? (delta + lowerBound() - proposed) : (
/* 169 */       (proposed > upperBound()) ? (delta - proposed - upperBound()) : 
/* 170 */       delta);
/*     */   }
/*     */   
/*     */   public int pressure(IndexedSeq routees) {
/* 191 */     return routees.count((Function1)new DefaultResizer$$anonfun$pressure$1(this));
/*     */   }
/*     */   
/*     */   public class DefaultResizer$$anonfun$pressure$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Routee x0$1) {
/* 191 */       Routee routee = x0$1;
/* 192 */       if (routee instanceof ActorRefRoutee) {
/* 192 */         ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/* 192 */         ActorRef a = actorRefRoutee.ref();
/* 192 */         if (a instanceof ActorRefWithCell) {
/*     */           boolean bool;
/* 192 */           ActorRefWithCell actorRefWithCell = (ActorRefWithCell)a;
/* 193 */           Cell cell = actorRefWithCell.underlying();
/* 194 */           if (cell instanceof ActorCell) {
/* 194 */             ActorCell actorCell = (ActorCell)cell;
/* 195 */             int i = this.$outer.pressureThreshold();
/* 195 */             switch (i) {
/*     */               default:
/* 197 */                 if (i < 1)
/* 197 */                   if (actorCell.mailbox().isScheduled() && actorCell.currentMessage() != null); 
/* 198 */                 if (actorCell.mailbox().numberOfMessages() >= i);
/*     */               case 1:
/*     */                 if (actorCell.mailbox().isScheduled() && actorCell.mailbox().hasMessages());
/*     */                 break;
/*     */             } 
/*     */             bool = false;
/*     */           } else {
/* 201 */             int i = this.$outer.pressureThreshold();
/* 201 */             switch (i) {
/*     */               default:
/* 203 */                 if (i < 1);
/* 204 */                 if (cell.numberOfMessages() >= i);
/*     */               case 1:
/*     */                 break;
/*     */             } 
/*     */             bool = cell.hasMessages();
/*     */           } 
/*     */           return bool;
/*     */         } 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public DefaultResizer$$anonfun$pressure$1(DefaultResizer $outer) {}
/*     */   }
/*     */   
/*     */   public int filter(int pressure, int capacity) {
/* 220 */     return rampup(pressure, capacity) + backoff(pressure, capacity);
/*     */   }
/*     */   
/*     */   public int rampup(int pressure, int capacity) {
/* 230 */     return (pressure < capacity) ? 0 : (int)package$.MODULE$.ceil(rampupRate() * capacity);
/*     */   }
/*     */   
/*     */   public int backoff(int pressure, int capacity) {
/* 240 */     return (backoffThreshold() > 0.0D && backoffRate() > 0.0D && capacity > 0 && pressure / capacity < backoffThreshold()) ? 
/* 241 */       (int)package$.MODULE$.floor(-1.0D * backoffRate() * capacity) : 
/* 242 */       0;
/*     */   }
/*     */   
/*     */   public static int apply$default$7() {
/*     */     return DefaultResizer$.MODULE$.apply$default$7();
/*     */   }
/*     */   
/*     */   public static double apply$default$6() {
/*     */     return DefaultResizer$.MODULE$.apply$default$6();
/*     */   }
/*     */   
/*     */   public static double apply$default$5() {
/*     */     return DefaultResizer$.MODULE$.apply$default$5();
/*     */   }
/*     */   
/*     */   public static double apply$default$4() {
/*     */     return DefaultResizer$.MODULE$.apply$default$4();
/*     */   }
/*     */   
/*     */   public static int apply$default$3() {
/*     */     return DefaultResizer$.MODULE$.apply$default$3();
/*     */   }
/*     */   
/*     */   public static int apply$default$2() {
/*     */     return DefaultResizer$.MODULE$.apply$default$2();
/*     */   }
/*     */   
/*     */   public static int apply$default$1() {
/*     */     return DefaultResizer$.MODULE$.apply$default$1();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$7() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$7();
/*     */   }
/*     */   
/*     */   public static double $lessinit$greater$default$6() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$6();
/*     */   }
/*     */   
/*     */   public static double $lessinit$greater$default$5() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$5();
/*     */   }
/*     */   
/*     */   public static double $lessinit$greater$default$4() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$4();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$3() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$3();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$2() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$2();
/*     */   }
/*     */   
/*     */   public static int $lessinit$greater$default$1() {
/*     */     return DefaultResizer$.MODULE$.$lessinit$greater$default$1();
/*     */   }
/*     */   
/*     */   public static Option<DefaultResizer> fromConfig(Config paramConfig) {
/*     */     return DefaultResizer$.MODULE$.fromConfig(paramConfig);
/*     */   }
/*     */   
/*     */   public static DefaultResizer apply(Config paramConfig) {
/*     */     return DefaultResizer$.MODULE$.apply(paramConfig);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\DefaultResizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */