/*    */ package scala.xml.include.sax;
/*    */ 
/*    */ import org.xml.sax.EntityResolver;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.SAXParseException;
/*    */ import org.xml.sax.XMLReader;
/*    */ import org.xml.sax.helpers.XMLReaderFactory;
/*    */ import scala.Function0;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.NonLocalReturnControl;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.control.Exception$;
/*    */ import scala.xml.package$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t;Q!\001\002\t\002-\tA!T1j]*\0211\001B\001\004g\006D(BA\003\007\003\035Ign\0317vI\026T!a\002\005\002\007alGNC\001\n\003\025\0318-\0317b\007\001\001\"\001D\007\016\003\t1QA\004\002\t\002=\021A!T1j]N\021Q\002\005\t\003#Ii\021\001C\005\003'!\021a!\0218z%\0264\007\"B\013\016\t\0031\022A\002\037j]&$h\bF\001\f\021\035ARB1A\005\ne\t\021C\\1nKN\004\030mY3Qe\0264\027\016_3t+\005Q\002CA\016!\033\005a\"BA\017\037\003\021a\027M\\4\013\003}\tAA[1wC&\021\021\005\b\002\007'R\024\030N\\4\t\r\rj\001\025!\003\033\003Iq\027-\\3ta\006\034W\r\025:fM&DXm\035\021\t\017\025j!\031!C\0053\005qA.\032=jG\006d\007*\0318eY\026\024\bBB\024\016A\003%!$A\bmKbL7-\0317IC:$G.\032:!\021\025IS\002\"\001+\003\021i\027-\0338\025\005-r\003CA\t-\023\ti\003B\001\003V]&$\b\"B\030)\001\004\001\024\001B1sON\0042!E\0314\023\t\021\004BA\003BeJ\f\027\020\005\0025o9\021\021#N\005\003m!\ta\001\025:fI\0264\027BA\0219\025\t1\004\002\013\003\016uuz\004CA\t<\023\ta\004B\001\006eKB\024XmY1uK\022\f\023AP\001-\007>$W\rI3yC6\004H.\032\021xS2d\007EY3![>4X\r\032\021u_\002\"wnY;nK:$\030\r^5p]:\n\023\001Q\001\007e9\n\004G\f\031)\t\001QTh\020")
/*    */ public final class Main {
/*    */   public static void main(String[] paramArrayOfString) {
/*    */     Main$.MODULE$.main(paramArrayOfString);
/*    */   }
/*    */   
/*    */   public static class Main$$anonfun$1 extends AbstractFunction0<XMLReader> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final XMLReader apply() {
/* 35 */       return XMLReaderFactory.createXMLReader();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Main$$anonfun$2 extends AbstractFunction0<XMLReader> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Object nonLocalReturnKey1$1;
/*    */     
/*    */     public final XMLReader apply() {
/* 36 */       Main$$anonfun$2$$anonfun$apply$3 main$$anonfun$2$$anonfun$apply$3 = 
/* 37 */         new Main$$anonfun$2$$anonfun$apply$3(this);
/*    */       Option option;
/*    */       if ((option = Main$.MODULE$.scala$xml$include$sax$Main$$saxe$1((Function0)new Main$$anonfun$2$$anonfun$apply$2(this))).isEmpty())
/*    */         throw main$$anonfun$2$$anonfun$apply$3.apply(); 
/*    */       return (XMLReader)option.get();
/*    */     }
/*    */     
/*    */     public Main$$anonfun$2(Object nonLocalReturnKey1$1) {}
/*    */     
/*    */     public class Main$$anonfun$2$$anonfun$apply$2 extends AbstractFunction0<XMLReader> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final XMLReader apply() {
/*    */         return XMLReaderFactory.createXMLReader(package$.MODULE$.XercesClassName());
/*    */       }
/*    */       
/*    */       public Main$$anonfun$2$$anonfun$apply$2(Main$$anonfun$2 $outer) {}
/*    */     }
/*    */     
/*    */     public class Main$$anonfun$2$$anonfun$apply$3 extends AbstractFunction0<Nothing$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Nothing$ apply() {
/* 37 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1("Could not find an XML parser");
/* 37 */         throw new NonLocalReturnControl.mcV.sp(this.$outer.nonLocalReturnKey1$1, BoxedUnit.UNIT);
/*    */       }
/*    */       
/*    */       public Main$$anonfun$2$$anonfun$apply$3(Main$$anonfun$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Main$$anonfun$3 extends AbstractFunction0<EntityResolver> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String[] args$1;
/*    */     
/*    */     private final XMLReader parser$1;
/*    */     
/*    */     public Main$$anonfun$3(String[] args$1, XMLReader parser$1) {}
/*    */     
/*    */     public final EntityResolver apply() {
/* 53 */       EntityResolver r = (EntityResolver)Class.forName(this.args$1[1]).newInstance();
/* 54 */       this.parser$1.setEntityResolver(r);
/* 55 */       return r;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Main$$anonfun$main$1 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final XMLReader parser$1;
/*    */     
/*    */     private final Option resolver$1;
/*    */     
/*    */     public Main$$anonfun$main$1(XMLReader parser$1, Option resolver$1) {}
/*    */     
/*    */     public final void apply(String arg) {
/*    */       try {
/* 60 */         XIncludeFilter includer = new XIncludeFilter();
/* 61 */         includer.setParent(this.parser$1);
/* 62 */         XIncluder s = new XIncluder(System.out, "UTF-8");
/* 63 */         includer.setContentHandler(s);
/* 65 */         Option option = this.resolver$1;
/* 65 */         Object object = option.get();
/* 65 */         EntityResolver entityResolver = (EntityResolver)object;
/* 65 */         includer.setEntityResolver(entityResolver);
/* 65 */         option.isEmpty() ? None$.MODULE$ : new Some(BoxedUnit.UNIT);
/* 67 */         (new Class[1])[0] = SAXException.class;
/* 67 */         Exception$.MODULE$.ignoring((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Class[1])).apply((Function0)new Main$$anonfun$main$1$$anonfun$apply$1(this, includer, s));
/* 71 */         includer.parse(arg);
/*    */       } catch (SAXParseException sAXParseException) {
/* 75 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1(sAXParseException.toString());
/* 76 */         Predef$ predef$ = Predef$.MODULE$;
/* 76 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1((new StringOps("Problem in %s at line %d")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { sAXParseException.getSystemId(), BoxesRunTime.boxToInteger(sAXParseException.getLineNumber()) })));
/*    */       } catch (SAXException sAXException) {
/* 78 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1(sAXException.toString());
/*    */       } 
/*    */     }
/*    */     
/*    */     public class Main$$anonfun$main$1$$anonfun$apply$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final XIncludeFilter includer$1;
/*    */       
/*    */       private final XIncluder s$1;
/*    */       
/*    */       public final void apply() {
/*    */         apply$mcV$sp();
/*    */       }
/*    */       
/*    */       public Main$$anonfun$main$1$$anonfun$apply$1(Main$$anonfun$main$1 $outer, XIncludeFilter includer$1, XIncluder s$1) {}
/*    */       
/*    */       public void apply$mcV$sp() {
/*    */         this.includer$1.setProperty(Main$.MODULE$.scala$xml$include$sax$Main$$lexicalHandler(), this.s$1);
/*    */         this.s$1.setFilter(this.includer$1);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */