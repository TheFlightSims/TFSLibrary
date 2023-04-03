/*    */ package scala.xml.include.sax;
/*    */ 
/*    */ import org.xml.sax.EntityResolver;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.SAXParseException;
/*    */ import org.xml.sax.XMLReader;
/*    */ import org.xml.sax.helpers.XMLReaderFactory;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.NonLocalReturnControl;
/*    */ 
/*    */ public final class Main$ {
/*    */   public static final Main$ MODULE$;
/*    */   
/*    */   private final String namespacePrefixes;
/*    */   
/*    */   private final String scala$xml$include$sax$Main$$lexicalHandler;
/*    */   
/*    */   private Main$() {
/* 18 */     MODULE$ = this;
/* 19 */     this.namespacePrefixes = "http://xml.org/sax/features/namespace-prefixes";
/* 20 */     this.scala$xml$include$sax$Main$$lexicalHandler = "http://xml.org/sax/properties/lexical-handler";
/*    */   }
/*    */   
/*    */   private String namespacePrefixes() {
/*    */     return this.namespacePrefixes;
/*    */   }
/*    */   
/*    */   public String scala$xml$include$sax$Main$$lexicalHandler() {
/* 20 */     return this.scala$xml$include$sax$Main$$lexicalHandler;
/*    */   }
/*    */   
/*    */   public void main(String[] args) {
/*    */     NonLocalReturnControl nonLocalReturnControl;
/* 30 */     Object object = new Object();
/*    */     try {
/*    */       NonLocalReturnControl.mcV.sp sp;
/* 35 */       Main$$anonfun$1 main$$anonfun$1 = new Main$$anonfun$1();
/* 35 */       (new Class[1])[0] = SAXException.class;
/* 35 */       Main$$anonfun$2 main$$anonfun$2 = 
/* 36 */         new Main$$anonfun$2(object);
/*    */       Option option = scala.util.control.Exception$.MODULE$.catching((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Class[1])).opt((Function0)main$$anonfun$1);
/*    */       if (option.isEmpty()) {
/*    */         Main$$anonfun$2$$anonfun$apply$2 main$$anonfun$2$$anonfun$apply$2 = new Main$$anonfun$2$$anonfun$apply$2(main$$anonfun$2);
/*    */         Main$ main$ = this;
/*    */         (new Class[1])[0] = SAXException.class;
/*    */         Option option1 = scala.util.control.Exception$.MODULE$.catching((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Class[1])).opt((Function0)main$$anonfun$2$$anonfun$apply$2);
/*    */         if (option1.isEmpty()) {
/*    */           Main$ main$1 = this;
/*    */           System.err.println("Could not find an XML parser");
/*    */           sp = new NonLocalReturnControl.mcV.sp(object, BoxedUnit.UNIT);
/*    */         } else {
/*    */         
/*    */         } 
/*    */       } else {
/*    */       
/*    */       } 
/*    */       if (sp.key() != object)
/*    */         throw sp; 
/*    */     } catch (NonLocalReturnControl nonLocalReturnControl1) {
/*    */       if ((nonLocalReturnControl = null).key() != object)
/*    */         throw nonLocalReturnControl; 
/*    */     } 
/*    */     nonLocalReturnControl.value$mcV$sp();
/*    */   }
/*    */   
/*    */   public final Option scala$xml$include$sax$Main$$saxe$1(Function0 body) {
/*    */     (new Class[1])[0] = SAXException.class;
/*    */     return scala.util.control.Exception$.MODULE$.catching((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Class[1])).opt(body);
/*    */   }
/*    */   
/*    */   public final void scala$xml$include$sax$Main$$fail$1(String msg) {
/*    */     System.err.println(msg);
/*    */   }
/*    */   
/*    */   public static class Main$$anonfun$1 extends AbstractFunction0<XMLReader> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final XMLReader apply() {
/*    */       return XMLReaderFactory.createXMLReader();
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
/*    */         return XMLReaderFactory.createXMLReader(scala.xml.package$.MODULE$.XercesClassName());
/*    */       }
/*    */       
/*    */       public Main$$anonfun$2$$anonfun$apply$2(Main$$anonfun$2 $outer) {}
/*    */     }
/*    */     
/*    */     public class Main$$anonfun$2$$anonfun$apply$3 extends AbstractFunction0<scala.runtime.Nothing$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final scala.runtime.Nothing$ apply() {
/* 37 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1("Could not find an XML parser");
/* 37 */         throw new NonLocalReturnControl.mcV.sp(this.$outer.nonLocalReturnKey1$1, BoxedUnit.UNIT);
/*    */       }
/*    */       
/*    */       public Main$$anonfun$2$$anonfun$apply$3(Main$$anonfun$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Main$$anonfun$2$$anonfun$apply$2 extends AbstractFunction0<XMLReader> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final XMLReader apply() {
/*    */       return XMLReaderFactory.createXMLReader(scala.xml.package$.MODULE$.XercesClassName());
/*    */     }
/*    */     
/*    */     public Main$$anonfun$2$$anonfun$apply$2(Main$$anonfun$2 $outer) {}
/*    */   }
/*    */   
/*    */   private final boolean dashR$1(String[] args$1) {
/*    */     // Byte code:
/*    */     //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*    */     //   3: aload_1
/*    */     //   4: checkcast [Ljava/lang/Object;
/*    */     //   7: invokevirtual refArrayOps : ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*    */     //   10: invokeinterface size : ()I
/*    */     //   15: iconst_2
/*    */     //   16: if_icmplt -> 47
/*    */     //   19: aload_1
/*    */     //   20: iconst_0
/*    */     //   21: aaload
/*    */     //   22: dup
/*    */     //   23: ifnonnull -> 35
/*    */     //   26: pop
/*    */     //   27: ldc '-r'
/*    */     //   29: ifnull -> 43
/*    */     //   32: goto -> 47
/*    */     //   35: ldc '-r'
/*    */     //   37: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   40: ifeq -> 47
/*    */     //   43: iconst_1
/*    */     //   44: goto -> 48
/*    */     //   47: iconst_0
/*    */     //   48: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #48	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	49	0	this	Lscala/xml/include/sax/Main$;
/*    */     //   0	49	1	args$1	[Ljava/lang/String;
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
/* 65 */         option.isEmpty() ? scala.None$.MODULE$ : new Some(BoxedUnit.UNIT);
/* 67 */         (new Class[1])[0] = SAXException.class;
/* 67 */         scala.util.control.Exception$.MODULE$.ignoring((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Class[1])).apply((Function0)new Main$$anonfun$main$1$$anonfun$apply$1(this, includer, s));
/* 71 */         includer.parse(arg);
/*    */       } catch (SAXParseException sAXParseException) {
/* 75 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1(sAXParseException.toString());
/* 76 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 76 */         Main$.MODULE$.scala$xml$include$sax$Main$$fail$1((new StringOps("Problem in %s at line %d")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { sAXParseException.getSystemId(), BoxesRunTime.boxToInteger(sAXParseException.getLineNumber()) })));
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\include\sax\Main$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */