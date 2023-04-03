/*    */ package scala.util;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Eg\001C\001\003!\003\r\t\001\002\004\003\037A\023x\016]3si&,7\017\026:bSRT!a\001\003\002\tU$\030\016\034\006\002\013\005)1oY1mCN\021\001a\002\t\003\021%i\021\001B\005\003\025\021\021a!\0218z%\0264\007\"\002\007\001\t\003q\021A\002\023j]&$He\001\001\025\003=\001\"\001\003\t\n\005E!!\001B+oSRDQa\005\001\007\022Q\tA\002\035:pa\016\013G/Z4pef,\022!\006\t\003-eq!\001C\f\n\005a!\021A\002)sK\022,g-\003\002\0337\t11\013\036:j]\036T!\001\007\003\t\013u\001a\021\003\020\002\035AL7m\033&be\n\0137/\0323P]V\tq\004\r\002!KA\031a#I\022\n\005\tZ\"!B\"mCN\034\bC\001\023&\031\001!\021B\n\017\002\002\003\005)\021A\024\003\007}##'\005\002)WA\021\001\"K\005\003U\021\021qAT8uQ&tw\r\005\002\tY%\021Q\006\002\002\004\003:L\bbB\030\001\005\004%\t\002M\001\raJ|\007OR5mK:\fW.Z\013\002cA\021!gN\007\002g)\021A'N\001\005Y\006twMC\0017\003\021Q\027M^1\n\005i\031\004BB\035\001A\003%\021'A\007qe>\004h)\0337f]\006lW\r\t\005\tw\001A)\031!C\ty\005Q1oY1mCB\023x\016]:\026\003u\002\"A\020!\016\003}R!aA\033\n\005\005{$A\003)s_B,'\017^5fg\"A1\t\001E\001B\003&Q(A\006tG\006d\027\r\025:paN\004\003\"B#\001\t\0231\025AD9vS\026$H.\037#jgB|7/\032\013\004\037\035c\005B\002%E\t\003\007\021*\001\004bGRLwN\034\t\004\021){\021BA&\005\005!a$-\0378b[\026t\004BB'E\t\003\007\021*\001\005eSN\004xn]1m\021\025y\005\001\"\001Q\003%\001(o\0349JgN+G\017\006\002R)B\021\001BU\005\003'\022\021qAQ8pY\026\fg\016C\003V\035\002\007Q#\001\003oC6,\007\"B,\001\t\003A\026a\0039s_BL5oU3u)>$2!U-[\021\025)f\0131\001\026\021\025Yf\0131\001\026\003\0251\030\r\\;f\021\025i\006\001\"\001_\003)\001(o\0349Pe\026c7/\032\013\004c}\003\007\"B+]\001\004)\002\"B1]\001\004)\022aA1mi\")1\r\001C\001I\006Y\001O]8q\037J,U\016\035;z)\t\tT\rC\003VE\002\007Q\003C\003h\001\021\005\001.\001\006qe>\004xJ\035(vY2$\"!M5\t\013U3\007\031A\013\t\013-\004A\021\0017\002\025A\024x\016](s\035>tW\r\006\002naB\031\001B\\\031\n\005=$!AB(qi&|g\016C\003VU\002\007Q\003C\003s\001\021\0051/A\006qe>\004xJ\035$bYN,GCA)u\021\025)\026\0171\001\026\021\0251\b\001\"\001x\003\035\031X\r\036)s_B$2!\r=z\021\025)V\0171\001\026\021\025YV\0171\001\026\021\025Y\b\001\"\001}\003%\031G.Z1s!J|\007\017\006\0022{\")QK\037a\001+!1q\020\001C\001\003\003\t\021\"\0328w\037J,En]3\025\013E\n\031!!\002\t\013Us\b\031A\013\t\013\005t\b\031A\013\t\017\005%\001\001\"\001\002\f\005IQM\034<Pe:{g.\032\013\004[\0065\001BB+\002\b\001\007Q\003C\004\002\022\001!\t!a\005\002\037M\034\027\r\\1Qe>\004xJ]#mg\026$R!FA\013\003/Aa!VA\b\001\004)\002BB1\002\020\001\007Q\003C\004\002\034\001!\t!!\b\002!M\034\027\r\\1Qe>\004xJ]#naRLHcA\013\002 !1Q+!\007A\002UAq!a\t\001\t\003\t)#A\btG\006d\027\r\025:pa>\023hj\0348f)\021\t9#!\013\021\007!qW\003\003\004V\003C\001\r!\006\005\n\003[\001!\031!C\001\003_\taB]3mK\006\034XMV3sg&|g.\006\002\002(!A\0211\007\001!\002\023\t9#A\bsK2,\027m]3WKJ\034\030n\0348!\021%\t9\004\001b\001\n\003\ty#\001\neKZ,Gn\0349nK:$h+\032:tS>t\007\002CA\036\001\001\006I!a\n\002'\021,g/\0327pa6,g\016\036,feNLwN\034\021\t\r\005}\002\001\"\001\025\003M1XM]:j_:tU/\0342feN#(/\0338h\021!\t\031\005\001b\001\n\003\001\024!\004<feNLwN\\*ue&tw\rC\004\002H\001\001\013\021B\031\002\035Y,'o]5p]N#(/\0338hA!A\0211\n\001C\002\023\005A#A\bd_BL(/[4iiN#(/\0338h\021\035\ty\005\001Q\001\nU\t\001cY8qsJLw\r\033;TiJLgn\032\021\t\r\005M\003\001\"\001\025\0039\031x.\036:dK\026s7m\0343j]\036Da!a\026\001\t\003!\022\001D:pkJ\034WMU3bI\026\024\bBBA.\001\021\005\001'\001\bf]\016|G-\0338h'R\024\030N\\4\t\r\005}\003\001\"\0011\0035a\027N\\3TKB\f'/\031;pe\"1\0211\r\001\005\002A\nQB[1wC\016c\027m]:QCRD\007BBA4\001\021\005\001'\001\005kCZ\f\007j\\7f\021\031\tY\007\001C\001a\005Q!.\031<b-\026tGm\034:\t\r\005=\004\001\"\0011\003-Q\027M^1WKJ\034\030n\0348\t\r\005M\004\001\"\0011\003)Q\027M^1W[&sgm\034\005\007\003o\002A\021\001\031\002\025)\fg/\031,n\035\006lW\r\003\004\002|\001!\t\001M\001\rU\0064\030MV7WK:$wN\035\005\007\003\002A\021\001\031\002\033)\fg/\031,n-\026\0248/[8o\021\031\t\031\t\001C\005a\005y!.\031<b'B,7MV3sg&|g\016\003\004\002\b\002!\t\001M\001\007_Nt\025-\\3\t\r\005-\005\001\"\0011\003%\0318-\0317b\021>lW\r\003\004\002\020\002!\t\001M\001\007i6\004H)\033:\t\r\005M\005\001\"\0011\003\035)8/\032:ESJDa!a&\001\t\003\001\024\001C;tKJDu.\\3\t\r\005m\005\001\"\0011\003!)8/\032:OC6,\007bBAP\001\021\005\021\021U\001\006SN<\026N\\\013\002#\"9\021Q\025\001\005\002\005\005\026!B5t\033\006\034\007BBAU\001\021\005\001'A\004kI.Du.\\3\t\r\0055\006\001\"\001\025\003)1XM]:j_:l5o\032\005\007\003c\003A\021\001\031\002\021M\034\027\r\\1D[\022Da!!.\001\t\003\001\024!C:dC2\f7mQ7e\021\035\tI\f\001C\001\003w\013Q\"[:KCZ\f\027\t\036'fCN$HcA)\002>\"9\021qXA\\\001\004)\022a\002<feNLwN\034\005\b\003\007\004A\021AAc\003\021i\027-\0338\025\007=\t9\r\003\005\002J\006\005\007\031AAf\003\021\t'oZ:\021\t!\ti-F\005\004\003\037$!!B!se\006L\b")
/*    */ public interface PropertiesTrait {
/*    */   void scala$util$PropertiesTrait$_setter_$propFilename_$eq(String paramString);
/*    */   
/*    */   void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(Option paramOption);
/*    */   
/*    */   void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(Option paramOption);
/*    */   
/*    */   void scala$util$PropertiesTrait$_setter_$versionString_$eq(String paramString);
/*    */   
/*    */   void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(String paramString);
/*    */   
/*    */   String propCategory();
/*    */   
/*    */   Class<?> pickJarBasedOn();
/*    */   
/*    */   String propFilename();
/*    */   
/*    */   Properties scalaProps();
/*    */   
/*    */   boolean propIsSet(String paramString);
/*    */   
/*    */   boolean propIsSetTo(String paramString1, String paramString2);
/*    */   
/*    */   String propOrElse(String paramString1, String paramString2);
/*    */   
/*    */   String propOrEmpty(String paramString);
/*    */   
/*    */   String propOrNull(String paramString);
/*    */   
/*    */   Option<String> propOrNone(String paramString);
/*    */   
/*    */   boolean propOrFalse(String paramString);
/*    */   
/*    */   String setProp(String paramString1, String paramString2);
/*    */   
/*    */   String clearProp(String paramString);
/*    */   
/*    */   String envOrElse(String paramString1, String paramString2);
/*    */   
/*    */   Option<String> envOrNone(String paramString);
/*    */   
/*    */   String scalaPropOrElse(String paramString1, String paramString2);
/*    */   
/*    */   String scalaPropOrEmpty(String paramString);
/*    */   
/*    */   Option<String> scalaPropOrNone(String paramString);
/*    */   
/*    */   Option<String> releaseVersion();
/*    */   
/*    */   Option<String> developmentVersion();
/*    */   
/*    */   String versionNumberString();
/*    */   
/*    */   String versionString();
/*    */   
/*    */   String copyrightString();
/*    */   
/*    */   String sourceEncoding();
/*    */   
/*    */   String sourceReader();
/*    */   
/*    */   String encodingString();
/*    */   
/*    */   String lineSeparator();
/*    */   
/*    */   String javaClassPath();
/*    */   
/*    */   String javaHome();
/*    */   
/*    */   String javaVendor();
/*    */   
/*    */   String javaVersion();
/*    */   
/*    */   String javaVmInfo();
/*    */   
/*    */   String javaVmName();
/*    */   
/*    */   String javaVmVendor();
/*    */   
/*    */   String javaVmVersion();
/*    */   
/*    */   String osName();
/*    */   
/*    */   String scalaHome();
/*    */   
/*    */   String tmpDir();
/*    */   
/*    */   String userDir();
/*    */   
/*    */   String userHome();
/*    */   
/*    */   String userName();
/*    */   
/*    */   boolean isWin();
/*    */   
/*    */   boolean isMac();
/*    */   
/*    */   String jdkHome();
/*    */   
/*    */   String versionMsg();
/*    */   
/*    */   String scalaCmd();
/*    */   
/*    */   String scalacCmd();
/*    */   
/*    */   boolean isJavaAtLeast(String paramString);
/*    */   
/*    */   void main(String[] paramArrayOfString);
/*    */   
/*    */   public class PropertiesTrait$$anonfun$scalaProps$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Properties props$1;
/*    */     
/*    */     public final InputStream stream$1;
/*    */     
/*    */     public final void apply() {
/* 37 */       this.props$1.load(this.stream$1);
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 37 */       this.props$1.load(this.stream$1);
/*    */     }
/*    */     
/*    */     public PropertiesTrait$$anonfun$scalaProps$1(PropertiesTrait $outer, Properties props$1, InputStream stream$1) {}
/*    */   }
/*    */   
/*    */   public class PropertiesTrait$$anonfun$scalaProps$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final InputStream stream$1;
/*    */     
/*    */     public final void apply() {
/* 37 */       this.stream$1.close();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 37 */       this.stream$1.close();
/*    */     }
/*    */     
/*    */     public PropertiesTrait$$anonfun$scalaProps$2(PropertiesTrait $outer, InputStream stream$1) {}
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public $anonfun$1(PropertiesTrait $outer) {}
/*    */     
/*    */     public final boolean apply(String v) {
/* 78 */       return !v.endsWith("-SNAPSHOT");
/*    */     }
/*    */   }
/*    */   
/*    */   public class $anonfun$2 extends AbstractFunction1<String, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public $anonfun$2(PropertiesTrait $outer) {}
/*    */     
/*    */     public final String apply(String v) {
/* 79 */       return v;
/*    */     }
/*    */   }
/*    */   
/*    */   public class $anonfun$3 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public $anonfun$3(PropertiesTrait $outer) {}
/*    */     
/*    */     public final boolean apply(String v) {
/* 92 */       return v.endsWith("-SNAPSHOT");
/*    */     }
/*    */   }
/*    */   
/*    */   public class $anonfun$4 extends AbstractFunction1<String, Option<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public $anonfun$4(PropertiesTrait $outer) {}
/*    */     
/*    */     public final Option<String> apply(String v) {
/*    */       Option<String> option;
/* 93 */       return (option = this.$outer.scalaPropOrNone("version.number")).isEmpty() ? (Option<String>)None$.MODULE$ : (Option<String>)new Some(option.get());
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\PropertiesTrait.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */