/*     */ package scala.xml.persistent;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.Writer;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.FileChannel;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.io.BufferedSource;
/*     */ import scala.io.Codec$;
/*     */ import scala.io.Source;
/*     */ import scala.io.Source$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.logging.Logged;
/*     */ import scala.xml.Elem;
/*     */ import scala.xml.MetaData;
/*     */ import scala.xml.NamespaceBinding;
/*     */ import scala.xml.Node;
/*     */ import scala.xml.NodeBuffer;
/*     */ import scala.xml.Null$;
/*     */ import scala.xml.XML$;
/*     */ import scala.xml.parsing.ConstructingParser$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001M4Q!\001\002\002\002%\021\021cQ1dQ\026$g)\0337f'R|'/Y4f\025\t\031A!\001\006qKJ\034\030n\035;f]RT!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\0312\001\001\006\023!\tY\001#D\001\r\025\tia\"\001\003mC:<'\"A\b\002\t)\fg/Y\005\003#1\021a\001\0265sK\006$\007CA\n\031\033\005!\"BA\013\027\003\035awnZ4j]\036T!a\006\004\002\tU$\030\016\\\005\0033Q\021a\001T8hO\026$\007\002C\016\001\005\013\007I\021\002\017\002\013\031LG.Z\031\026\003u\001\"AH\021\016\003}Q!\001\t\b\002\005%|\027B\001\022 \005\0211\025\016\\3\t\021\021\002!\021!Q\001\nu\taAZ5mKF\002\003\"\002\024\001\t\0039\023A\002\037j]&$h\b\006\002)UA\021\021\006A\007\002\005!)1$\na\001;!9A\006\001b\001\n\023a\022!\0024jY\026\024\004B\002\030\001A\003%Q$\001\004gS2,'\007\t\005\ba\001\001\r\021\"\003\035\003\035!\b.\032$jY\026DqA\r\001A\002\023%1'A\006uQ\0264\025\016\\3`I\025\fHC\001\0339!\t)d'D\001\007\023\t9dA\001\003V]&$\bbB\0352\003\003\005\r!H\001\004q\022\n\004BB\036\001A\003&Q$\001\005uQ\0264\025\016\\3!\021\025i\004\001\"\003?\003\031\031x/\033;dQR\tA\007C\004A\001\001\007I\021C!\002\013\021L'\017^=\026\003\t\003\"!N\"\n\005\0213!a\002\"p_2,\027M\034\005\b\r\002\001\r\021\"\005H\003%!\027N\035;z?\022*\027\017\006\0025\021\"9\021(RA\001\002\004\021\005B\002&\001A\003&!)\001\004eSJ$\030\020\t\005\b\031\002\021\r\021\"\005N\003!Ig\016^3sm\006dW#\001(\021\005Uz\025B\001)\007\005\rIe\016\036\005\007%\002\001\013\021\002(\002\023%tG/\032:wC2\004\003\"\002+\001\t#)\026\001D5oSRL\027\r\034(pI\026\034X#\001,\021\007]SF,D\001Y\025\tIf!\001\006d_2dWm\031;j_:L!a\027-\003\021%#XM]1u_J\004\"!\0300\016\003\021I!a\030\003\003\t9{G-\032\005\006C\0021\t!V\001\006]>$Wm\035\005\006G\0021\t\001Z\001\tIAdWo\035\023fcR\021A'\032\005\006M\n\004\r\001X\001\002K\")\001\016\001D\001S\006IA%\\5okN$S-\035\013\003i)DQAZ4A\002qCQ\001\034\001\005\nU\013A\001\\8bI\")a\016\001C\005}\005!1/\031<f\021\025\001\b\001\"\021?\003\r\021XO\034\005\006e\002!\tAP\001\006M2,8\017\033")
/*     */ public abstract class CachedFileStorage extends Thread implements Logged {
/*     */   private final File file1;
/*     */   
/*     */   private final File file2;
/*     */   
/*     */   private File theFile;
/*     */   
/*     */   private boolean dirty;
/*     */   
/*     */   private final int interval;
/*     */   
/*     */   public void log(String msg) {
/*  28 */     Logged.class.log(this, msg);
/*     */   }
/*     */   
/*     */   private File file1() {
/*  28 */     return this.file1;
/*     */   }
/*     */   
/*     */   public CachedFileStorage(File file1) {
/*  28 */     Logged.class.$init$(this);
/*  30 */     this.file2 = new File(file1.getParent(), (new StringBuilder()).append(file1.getName()).append("$").toString());
/*  35 */     this.theFile = null;
/*  40 */     this.dirty = false;
/*  43 */     this.interval = 1000;
/*     */   }
/*     */   
/*     */   private File file2() {
/*     */     return this.file2;
/*     */   }
/*     */   
/*     */   private File theFile() {
/*     */     return this.theFile;
/*     */   }
/*     */   
/*     */   private void theFile_$eq(File x$1) {
/*     */     this.theFile = x$1;
/*     */   }
/*     */   
/*     */   private void switch() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_0
/*     */     //   2: invokespecial theFile : ()Ljava/io/File;
/*     */     //   5: aload_0
/*     */     //   6: invokespecial file1 : ()Ljava/io/File;
/*     */     //   9: astore_1
/*     */     //   10: dup
/*     */     //   11: ifnonnull -> 22
/*     */     //   14: pop
/*     */     //   15: aload_1
/*     */     //   16: ifnull -> 29
/*     */     //   19: goto -> 36
/*     */     //   22: aload_1
/*     */     //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   26: ifeq -> 36
/*     */     //   29: aload_0
/*     */     //   30: invokespecial file2 : ()Ljava/io/File;
/*     */     //   33: goto -> 40
/*     */     //   36: aload_0
/*     */     //   37: invokespecial file1 : ()Ljava/io/File;
/*     */     //   40: invokespecial theFile_$eq : (Ljava/io/File;)V
/*     */     //   43: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #37	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	44	0	this	Lscala/xml/persistent/CachedFileStorage;
/*     */   }
/*     */   
/*     */   public boolean dirty() {
/*     */     return this.dirty;
/*     */   }
/*     */   
/*     */   public void dirty_$eq(boolean x$1) {
/*     */     this.dirty = x$1;
/*     */   }
/*     */   
/*     */   public int interval() {
/*  43 */     return this.interval;
/*     */   }
/*     */   
/*     */   public Iterator<Node> initialNodes() {
/*     */     Iterator<Node> iterator;
/*  48 */     Tuple2.mcZZ.sp sp = new Tuple2.mcZZ.sp(file1().exists(), file2().exists());
/*  48 */     if (sp != null && false == sp
/*  49 */       ._1$mcZ$sp() && false == sp._2$mcZ$sp()) {
/*  50 */       theFile_$eq(file1());
/*  51 */       iterator = Iterator$.MODULE$.empty();
/*     */     } else {
/*     */       if (sp != null)
/*  52 */         if (true == sp._1$mcZ$sp() && true == sp._2$mcZ$sp() && file1().lastModified() < file2().lastModified()) {
/*  53 */           theFile_$eq(file2());
/*  54 */           return load();
/*     */         }  
/*     */       if (sp != null)
/*  55 */         if (true == sp._1$mcZ$sp()) {
/*  56 */           theFile_$eq(file1());
/*  57 */           return load();
/*     */         }  
/*  59 */       theFile_$eq(file2());
/*  60 */       iterator = load();
/*     */     } 
/*     */     return iterator;
/*     */   }
/*     */   
/*     */   private Iterator<Node> load() {
/*  76 */     log((new StringBuilder()).append("[load]\nloading ").append(theFile()).toString());
/*  77 */     BufferedSource src = Source$.MODULE$.fromFile(theFile(), Codec$.MODULE$.fallbackSystemCodec());
/*  78 */     log((new StringBuilder()).append("parsing ").append(theFile()).toString());
/*  79 */     Node res = ConstructingParser$.MODULE$.fromSource((Source)src, false).document().docElem().apply(0);
/*  80 */     switch();
/*  81 */     log("[load done]");
/*  82 */     return res.child().iterator();
/*     */   }
/*     */   
/*     */   private void save() {
/*  86 */     if (dirty()) {
/*  87 */       log((new StringBuilder()).append("[save]\ndeleting ").append(theFile()).toString());
/*  88 */       theFile().delete();
/*  89 */       log((new StringBuilder()).append("creating new ").append(theFile()).toString());
/*  90 */       theFile().createNewFile();
/*  91 */       FileOutputStream fos = new FileOutputStream(theFile());
/*  92 */       FileChannel c = fos.getChannel();
/*  95 */       NodeBuffer $buf = new NodeBuffer();
/*  95 */       $buf.$amp$plus(nodes().toList());
/*  95 */       Elem storageNode = new Elem(null, "nodes", (MetaData)Null$.MODULE$, (NamespaceBinding)Predef$.MODULE$.$scope(), false, (Seq)$buf);
/*  96 */       Writer w = Channels.newWriter(c, "utf-8");
/*  97 */       XML$.MODULE$.write(w, (Node)storageNode, "utf-8", true, null, XML$.MODULE$.write$default$6());
/*  99 */       log((new StringBuilder()).append("writing to ").append(theFile()).toString());
/* 101 */       w.close();
/* 102 */       c.close();
/* 103 */       fos.close();
/* 104 */       dirty_$eq(false);
/* 105 */       switch();
/* 106 */       log("[save done]");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void run() {
/* 112 */     log((new StringBuilder()).append("[run]\nstarting storage thread, checking every ").append(BoxesRunTime.boxToInteger(interval())).append(" ms").toString());
/*     */     while (true) {
/* 113 */       Thread.sleep(interval());
/* 115 */       save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flush() {
/* 122 */     dirty_$eq(true);
/* 123 */     save();
/*     */   }
/*     */   
/*     */   public abstract Iterator<Node> nodes();
/*     */   
/*     */   public abstract void $plus$eq(Node paramNode);
/*     */   
/*     */   public abstract void $minus$eq(Node paramNode);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\persistent\CachedFileStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */