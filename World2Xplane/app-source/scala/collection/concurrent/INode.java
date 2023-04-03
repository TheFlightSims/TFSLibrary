/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]g!B\001\003\005\021A!!B%O_\022,'BA\002\005\003)\031wN\\2veJ,g\016\036\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\fWcA\005\0219M\021\001A\003\t\005\0271q1$D\001\003\023\ti!AA\005J\035>$WMQ1tKB\021q\002\005\007\001\t\025\t\002A1\001\024\005\005Y5\001A\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\tyA\004B\003\036\001\t\0071CA\001W\021!y\002A!A!\002\023\001\023A\0012o!\021Y\021ED\016\n\005\t\022!\001C'bS:tu\016Z3\t\021\021\002!\021!Q\001\n\025\n\021a\032\t\003\027\031J!a\n\002\003\007\035+g\016C\003*\001\021\005!&\001\004=S:LGO\020\013\004W1j\003\003B\006\001\035mAQa\b\025A\002\001BQ\001\n\025A\002\025BQ!\013\001\005\002=\"\"a\013\031\t\013\021r\003\031A\023\t\013I\002A\021A\032\002\013]\023\026\nV#\025\005Q:\004CA\0136\023\t1dA\001\003V]&$\b\"\002\0352\001\004\001\023\001\0028wC2DQA\017\001\005\002m\n1aQ!T)\rat(\021\t\003+uJ!A\020\004\003\017\t{w\016\\3b]\")\001)\017a\001A\005\031q\016\0343\t\013\tK\004\031\001\021\002\0039DQ\001\022\001\005\002\025\013\001bZ2bgJ+\027\r\032\013\003A\031CQaR\"A\002!\013!a\031;\021\t-IebG\005\003\025\n\021q\001\026:jK6\013\007\017C\003M\001\021\005Q*A\005H\007\006\033vLU#B\tR\021\001E\024\005\006\017.\003\r\001\023\005\006!\002!I!U\001\016\017\016\0135kX\"p[BdW\r^3\025\007\001\022F\013C\003T\037\002\007\001%A\001n\021\0259u\n1\001IQ\tye\013\005\002X56\t\001L\003\002Z\r\005Q\021M\0348pi\006$\030n\0348\n\005mC&a\002;bS2\024Xm\031\005\006;\002!\tAX\001\005\017\016\0135\013\006\003=?\002\f\007\"\002!]\001\004\001\003\"\002\"]\001\004\001\003\"B$]\001\004A\005\"B2\001\t\023!\027!B3rk\006dG\003\002\037fO&DQA\0322A\0029\t!a[\031\t\013!\024\007\031\001\b\002\005-\024\004\"B$c\001\004A\005\"B6\001\t\023a\027!B5o_\022,GCA\026n\021\025q'\0161\001!\003\t\031g\016C\003q\001\021\005\021/A\005d_BLHk\\$f]R\0311F\035;\t\013M|\007\031A\023\002\t9<WM\034\005\006\017>\004\r\001\023\005\006m\002!\ta^\001\013e\026\034w,\0338tKJ$H\003\004\037yur\f\031!a\002\002\f\005=\001\"B=v\001\004q\021!A6\t\013m,\b\031A\016\002\003YDQ!`;A\002y\f!\001[2\021\005Uy\030bAA\001\r\t\031\021J\034;\t\r\005\025Q\0171\001\003\raWM\036\005\007\003\023)\b\031A\026\002\rA\f'/\0328u\021\031\ti!\036a\001K\005A1\017^1si\036,g\016C\003Hk\002\007\001\n\013\002v-\"9\021Q\003\001\005\002\005]\021\001\004:fG~Kgn]3si&4GCEA\r\003?\t\t#a\t\002&\005=\022\021GA\032\003k\001B!FA\0167%\031\021Q\004\004\003\r=\003H/[8o\021\031I\0301\003a\001\035!110a\005A\002mAa!`A\n\001\004q\b\002CA\024\003'\001\r!!\013\002\t\r|g\016\032\t\004+\005-\022bAA\027\r\t1\021I\\=SK\032Dq!!\002\002\024\001\007a\020C\004\002\n\005M\001\031A\026\t\017\0055\0211\003a\001K!1q)a\005A\002!C3!a\005W\021\035\tY\004\001C\001\003{\t!B]3d?2|wn[;q)9\tI#a\020\002B\005\r\023QIA$\003\023Ba!_A\035\001\004q\001BB?\002:\001\007a\020C\004\002\006\005e\002\031\001@\t\017\005%\021\021\ba\001W!9\021QBA\035\001\004)\003BB$\002:\001\007\001\nK\002\002:YCq!a\024\001\t\003\t\t&\001\006sK\016|&/Z7pm\026$\002#!\007\002T\005U\023qKA-\0037\ni&a\030\t\re\fi\0051\001\017\021\031Y\030Q\na\0017!1Q0!\024A\002yDq!!\002\002N\001\007a\020C\004\002\n\0055\003\031A\026\t\017\0055\021Q\na\001K!1q)!\024A\002!Cq!a\031\001\t\023\t)'A\003dY\026\fg\016F\0045\003O\nY'!\034\t\017\005%\024\021\ra\001W\005\021a\016\032\005\007\017\006\005\004\031\001%\t\017\005\025\021\021\ra\001}\"9\021\021\017\001\005\002\005M\024aC5t\035VdG.\0238pI\026$2\001PA;\021\0319\025q\016a\001\021\"9\021\021\020\001\005\002\005m\024AC2bG\",GmU5{KR\031a0! \t\r\035\0139\b1\001I\021\035\t\t\t\001C\001\003\007\013aa\035;sS:<G\003BAC\003'\003B!a\"\002\016:\031Q#!#\n\007\005-e!\001\004Qe\026$WMZ\005\005\003\037\013\tJ\001\004TiJLgn\032\006\004\003\0273\001bBA\003\003\002\rA`\004\t\003/\023\001\022\001\002\002\032\006)\021JT8eKB\0311\"a'\007\017\005\021\001\022\001\002\002\036N!\0211TA\025\021\035I\0231\024C\001\003C#\"!!'\t\025\005\025\0261\024b\001\n\003\t9+A\006L\013f{\006KU#T\013:#VCAAU!\021\tY+!.\016\005\0055&\002BAX\003c\013A\001\\1oO*\021\0211W\001\005U\0064\030-\003\003\0028\0065&AB(cU\026\034G\017C\005\002<\006m\005\025!\003\002*\006a1*R-`!J+5+\022(UA!Q\021qXAN\005\004%\t!a*\002\025-+\025lX!C'\026sE\013C\005\002D\006m\005\025!\003\002*\006Y1*R-`\003\n\033VI\024+!\021!\t9-a'\005\002\005%\027a\0038foJ{w\016\036(pI\026,b!a3\002R\006UWCAAg!\031Y\001!a4\002TB\031q\"!5\005\rE\t)M1\001\024!\ry\021Q\033\003\007;\005\025'\031A\n")
/*     */ public final class INode<K, V> extends INodeBase<K, V> {
/*     */   public INode(MainNode<K, V> bn, Gen g) {
/*  21 */     super(g);
/*  24 */     WRITE(bn);
/*     */   }
/*     */   
/*     */   public INode(Gen g) {
/*  26 */     this((MainNode<K, V>)null, g);
/*     */   }
/*     */   
/*     */   public void WRITE(MainNode nval) {
/*  28 */     INodeBase.updater.set(this, nval);
/*     */   }
/*     */   
/*     */   public boolean CAS(MainNode old, MainNode n) {
/*  30 */     return INodeBase.updater.compareAndSet(this, old, n);
/*     */   }
/*     */   
/*     */   public MainNode<K, V> gcasRead(TrieMap<K, V> ct) {
/*  32 */     return GCAS_READ(ct);
/*     */   }
/*     */   
/*     */   public MainNode<K, V> GCAS_READ(TrieMap<K, V> ct) {
/*  35 */     MainNode<K, V> m = this.mainnode;
/*  36 */     MainNode<K, V> prevval = m.prev;
/*  37 */     return (prevval == null) ? m : 
/*  38 */       GCAS_Complete(m, ct);
/*     */   }
/*     */   
/*     */   private MainNode<K, V> GCAS_Complete(MainNode<K, V> m, TrieMap ct) {
/*     */     while (true) {
/*  41 */       if (m == null) {
/*     */       
/*     */       } else {
/*  43 */         MainNode<K, V> mainNode1, prev = m.prev;
/*  44 */         INode ctr = ct.readRoot(true);
/*  46 */         if (prev == null) {
/*  48 */           mainNode1 = m;
/*  49 */         } else if (prev instanceof FailedNode) {
/*  49 */           FailedNode failedNode = (FailedNode)prev;
/*  50 */           if (CAS(m, failedNode.prev)) {
/*  50 */             mainNode1 = failedNode.prev;
/*     */           } else {
/*  51 */             m = this.mainnode;
/*     */             continue;
/*     */           } 
/*     */         } else {
/*  52 */           if (prev != null) {
/*  61 */             if (ctr.gen == this.gen && ct.nonReadOnly()) {
/*  63 */               if (m.CAS_PREV(prev, null))
/*  63 */                 mainNode1 = m; 
/*     */               continue;
/*     */             } 
/*  67 */             m.CAS_PREV(prev, new FailedNode<K, V>(prev));
/*  68 */             m = this.mainnode;
/*     */             continue;
/*     */           } 
/*     */           throw new MatchError(prev);
/*     */         } 
/*     */       } 
/*     */       return mainNode1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean GCAS(MainNode<K, V> old, MainNode<K, V> n, TrieMap<K, V> ct) {
/*  74 */     n.WRITE_PREV(old);
/*  76 */     GCAS_Complete(n, ct);
/*  77 */     return CAS(old, n) ? ((n.prev == null)) : false;
/*     */   }
/*     */   
/*     */   private boolean equal(Object k1, Object k2, TrieMap ct) {
/*  81 */     return ct.equality().equiv(k1, k2);
/*     */   }
/*     */   
/*     */   private INode<K, V> inode(MainNode cn) {
/*  84 */     INode<K, V> nin = new INode(this.gen);
/*  85 */     nin.WRITE(cn);
/*  86 */     return nin;
/*     */   }
/*     */   
/*     */   public INode<K, V> copyToGen(Gen ngen, TrieMap<K, V> ct) {
/*  90 */     INode<K, V> nin = new INode(ngen);
/*  91 */     MainNode<K, V> main = GCAS_READ(ct);
/*  92 */     nin.WRITE(main);
/*  93 */     return nin;
/*     */   }
/*     */   
/*     */   public boolean rec_insert(Object k, Object v, int hc, int lev, INode<K, V> parent, Gen startgen, TrieMap<K, V> ct) {
/*     */     MainNode<K, V> m;
/*     */     while (true) {
/*     */       boolean bool;
/* 101 */       m = GCAS_READ(ct);
/* 103 */       if (m instanceof CNode) {
/* 103 */         CNode<K, V> cNode = (CNode)m;
/* 105 */         int idx = hc >>> lev & 0x1F;
/* 106 */         int flag = 1 << idx;
/* 107 */         int bmp = cNode.bitmap();
/* 108 */         int mask = flag - 1;
/* 109 */         int pos = Integer.bitCount(bmp & mask);
/* 110 */         if ((bmp & flag) != 0) {
/* 112 */           BasicNode basicNode = cNode.array()[pos];
/* 113 */           if (basicNode instanceof INode) {
/* 113 */             INode iNode = (INode)basicNode;
/* 114 */             if (startgen == iNode.gen) {
/* 114 */               parent = this;
/* 114 */               lev += 5;
/* 114 */               hc = hc;
/* 114 */               v = v;
/* 114 */               k = k;
/* 114 */               this = iNode;
/*     */               continue;
/*     */             } 
/* 116 */             if (!GCAS(cNode, cNode.renewed(startgen, ct), ct))
/*     */               bool = false; 
/*     */             continue;
/*     */           } 
/* 119 */           if (basicNode instanceof SNode) {
/* 119 */             SNode<K, V> sNode = (SNode)basicNode;
/* 122 */             CNode<K, V> rn = (cNode.gen() == this.gen) ? cNode : cNode.renewed(this.gen, ct);
/* 123 */             CNode<K, V> nn = rn.updatedAt(pos, inode(CNode$.MODULE$.dual(sNode, sNode.hc(), new SNode<K, V>((K)k, (V)v, hc), hc, lev + 5, this.gen)), this.gen);
/* 124 */             bool = (sNode.hc() == hc && equal((K)sNode.k(), (K)k, ct)) ? GCAS(cNode, cNode.updatedAt(pos, new SNode<Object, Object>(k, v, hc), this.gen), ct) : GCAS(cNode, nn, ct);
/*     */           } else {
/*     */             throw new MatchError(basicNode);
/*     */           } 
/*     */         } else {
/* 128 */           CNode<K, V> rn = (cNode.gen() == this.gen) ? cNode : cNode.renewed(this.gen, ct);
/* 129 */           CNode<K, V> ncnode = rn.insertedAt(pos, flag, new SNode<Object, Object>(k, v, hc), this.gen);
/* 130 */           boolean bool1 = GCAS(cNode, ncnode, ct);
/*     */         } 
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */     } 
/* 132 */     if (m instanceof TNode) {
/* 133 */       clean(parent, ct, lev - 5);
/* 134 */       boolean bool = false;
/*     */     } else {
/* 135 */       if (m instanceof LNode) {
/* 135 */         LNode<Object, Object> lNode1 = (LNode)m;
/* 136 */         LNode<Object, Object> nn = lNode1.inserted(k, v);
/* 137 */         return GCAS((MainNode)lNode1, (MainNode)nn, ct);
/*     */       } 
/*     */       throw new MatchError(m);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_24;
/*     */   }
/*     */   
/*     */   public Option<V> rec_insertif(Object k, Object v, int hc, Object cond, int lev, INode parent, Gen startgen, TrieMap ct) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload #8
/*     */     //   3: invokevirtual GCAS_READ : (Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   6: astore #48
/*     */     //   8: aload #48
/*     */     //   10: instanceof scala/collection/concurrent/CNode
/*     */     //   13: ifeq -> 1199
/*     */     //   16: aload #48
/*     */     //   18: checkcast scala/collection/concurrent/CNode
/*     */     //   21: astore #29
/*     */     //   23: iload_3
/*     */     //   24: iload #5
/*     */     //   26: iushr
/*     */     //   27: bipush #31
/*     */     //   29: iand
/*     */     //   30: istore #9
/*     */     //   32: iconst_1
/*     */     //   33: iload #9
/*     */     //   35: ishl
/*     */     //   36: istore #28
/*     */     //   38: aload #29
/*     */     //   40: invokevirtual bitmap : ()I
/*     */     //   43: istore #11
/*     */     //   45: iload #28
/*     */     //   47: iconst_1
/*     */     //   48: isub
/*     */     //   49: istore #10
/*     */     //   51: iload #11
/*     */     //   53: iload #10
/*     */     //   55: iand
/*     */     //   56: invokestatic bitCount : (I)I
/*     */     //   59: istore #27
/*     */     //   61: iload #11
/*     */     //   63: iload #28
/*     */     //   65: iand
/*     */     //   66: iconst_0
/*     */     //   67: if_icmpeq -> 916
/*     */     //   70: aload #29
/*     */     //   72: invokevirtual array : ()[Lscala/collection/concurrent/BasicNode;
/*     */     //   75: iload #27
/*     */     //   77: aaload
/*     */     //   78: astore #23
/*     */     //   80: aload #23
/*     */     //   82: instanceof scala/collection/concurrent/INode
/*     */     //   85: ifeq -> 156
/*     */     //   88: aload #23
/*     */     //   90: checkcast scala/collection/concurrent/INode
/*     */     //   93: astore #12
/*     */     //   95: aload #7
/*     */     //   97: aload #12
/*     */     //   99: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   102: if_acmpne -> 130
/*     */     //   105: aload #12
/*     */     //   107: aload_1
/*     */     //   108: aload_2
/*     */     //   109: iload_3
/*     */     //   110: aload #4
/*     */     //   112: iload #5
/*     */     //   114: iconst_5
/*     */     //   115: iadd
/*     */     //   116: aload_0
/*     */     //   117: astore #6
/*     */     //   119: istore #5
/*     */     //   121: astore #4
/*     */     //   123: istore_3
/*     */     //   124: astore_2
/*     */     //   125: astore_1
/*     */     //   126: astore_0
/*     */     //   127: goto -> 0
/*     */     //   130: aload_0
/*     */     //   131: aload #29
/*     */     //   133: aload #29
/*     */     //   135: aload #7
/*     */     //   137: aload #8
/*     */     //   139: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   142: aload #8
/*     */     //   144: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   147: ifne -> 0
/*     */     //   150: aconst_null
/*     */     //   151: astore #22
/*     */     //   153: goto -> 901
/*     */     //   156: aload #23
/*     */     //   158: instanceof scala/collection/concurrent/SNode
/*     */     //   161: ifeq -> 906
/*     */     //   164: aload #23
/*     */     //   166: checkcast scala/collection/concurrent/SNode
/*     */     //   169: astore #20
/*     */     //   171: aload #4
/*     */     //   173: ifnonnull -> 355
/*     */     //   176: aload #20
/*     */     //   178: invokevirtual hc : ()I
/*     */     //   181: iload_3
/*     */     //   182: if_icmpne -> 251
/*     */     //   185: aload_0
/*     */     //   186: aload #20
/*     */     //   188: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   191: aload_1
/*     */     //   192: aload #8
/*     */     //   194: invokespecial equal : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   197: ifeq -> 251
/*     */     //   200: aload_0
/*     */     //   201: aload #29
/*     */     //   203: aload #29
/*     */     //   205: iload #27
/*     */     //   207: new scala/collection/concurrent/SNode
/*     */     //   210: dup
/*     */     //   211: aload_1
/*     */     //   212: aload_2
/*     */     //   213: iload_3
/*     */     //   214: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   217: aload_0
/*     */     //   218: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   221: invokevirtual updatedAt : (ILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   224: aload #8
/*     */     //   226: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   229: ifeq -> 247
/*     */     //   232: new scala/Some
/*     */     //   235: dup
/*     */     //   236: aload #20
/*     */     //   238: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   241: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   244: goto -> 350
/*     */     //   247: aconst_null
/*     */     //   248: goto -> 350
/*     */     //   251: aload #29
/*     */     //   253: invokevirtual gen : ()Lscala/collection/concurrent/Gen;
/*     */     //   256: aload_0
/*     */     //   257: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   260: if_acmpne -> 268
/*     */     //   263: aload #29
/*     */     //   265: goto -> 279
/*     */     //   268: aload #29
/*     */     //   270: aload_0
/*     */     //   271: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   274: aload #8
/*     */     //   276: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   279: astore #13
/*     */     //   281: aload #13
/*     */     //   283: iload #27
/*     */     //   285: aload_0
/*     */     //   286: getstatic scala/collection/concurrent/CNode$.MODULE$ : Lscala/collection/concurrent/CNode$;
/*     */     //   289: aload #20
/*     */     //   291: aload #20
/*     */     //   293: invokevirtual hc : ()I
/*     */     //   296: new scala/collection/concurrent/SNode
/*     */     //   299: dup
/*     */     //   300: aload_1
/*     */     //   301: aload_2
/*     */     //   302: iload_3
/*     */     //   303: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   306: iload_3
/*     */     //   307: iload #5
/*     */     //   309: iconst_5
/*     */     //   310: iadd
/*     */     //   311: aload_0
/*     */     //   312: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   315: invokevirtual dual : (Lscala/collection/concurrent/SNode;ILscala/collection/concurrent/SNode;IILscala/collection/concurrent/Gen;)Lscala/collection/concurrent/MainNode;
/*     */     //   318: invokespecial inode : (Lscala/collection/concurrent/MainNode;)Lscala/collection/concurrent/INode;
/*     */     //   321: aload_0
/*     */     //   322: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   325: invokevirtual updatedAt : (ILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   328: astore #14
/*     */     //   330: aload_0
/*     */     //   331: aload #29
/*     */     //   333: aload #14
/*     */     //   335: aload #8
/*     */     //   337: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   340: ifeq -> 349
/*     */     //   343: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   346: goto -> 350
/*     */     //   349: aconst_null
/*     */     //   350: astore #21
/*     */     //   352: goto -> 897
/*     */     //   355: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   358: invokevirtual KEY_ABSENT : ()Ljava/lang/Object;
/*     */     //   361: dup
/*     */     //   362: astore #15
/*     */     //   364: aload #4
/*     */     //   366: if_acmpne -> 373
/*     */     //   369: iconst_1
/*     */     //   370: goto -> 431
/*     */     //   373: aload #15
/*     */     //   375: ifnonnull -> 382
/*     */     //   378: iconst_0
/*     */     //   379: goto -> 431
/*     */     //   382: aload #15
/*     */     //   384: instanceof java/lang/Number
/*     */     //   387: ifeq -> 403
/*     */     //   390: aload #15
/*     */     //   392: checkcast java/lang/Number
/*     */     //   395: aload #4
/*     */     //   397: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   400: goto -> 431
/*     */     //   403: aload #15
/*     */     //   405: instanceof java/lang/Character
/*     */     //   408: ifeq -> 424
/*     */     //   411: aload #15
/*     */     //   413: checkcast java/lang/Character
/*     */     //   416: aload #4
/*     */     //   418: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   421: goto -> 431
/*     */     //   424: aload #15
/*     */     //   426: aload #4
/*     */     //   428: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   431: ifeq -> 577
/*     */     //   434: aload #20
/*     */     //   436: invokevirtual hc : ()I
/*     */     //   439: iload_3
/*     */     //   440: if_icmpne -> 473
/*     */     //   443: aload_0
/*     */     //   444: aload #20
/*     */     //   446: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   449: aload_1
/*     */     //   450: aload #8
/*     */     //   452: invokespecial equal : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   455: ifeq -> 473
/*     */     //   458: new scala/Some
/*     */     //   461: dup
/*     */     //   462: aload #20
/*     */     //   464: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   467: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   470: goto -> 572
/*     */     //   473: aload #29
/*     */     //   475: invokevirtual gen : ()Lscala/collection/concurrent/Gen;
/*     */     //   478: aload_0
/*     */     //   479: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   482: if_acmpne -> 490
/*     */     //   485: aload #29
/*     */     //   487: goto -> 501
/*     */     //   490: aload #29
/*     */     //   492: aload_0
/*     */     //   493: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   496: aload #8
/*     */     //   498: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   501: astore #16
/*     */     //   503: aload #16
/*     */     //   505: iload #27
/*     */     //   507: aload_0
/*     */     //   508: getstatic scala/collection/concurrent/CNode$.MODULE$ : Lscala/collection/concurrent/CNode$;
/*     */     //   511: aload #20
/*     */     //   513: aload #20
/*     */     //   515: invokevirtual hc : ()I
/*     */     //   518: new scala/collection/concurrent/SNode
/*     */     //   521: dup
/*     */     //   522: aload_1
/*     */     //   523: aload_2
/*     */     //   524: iload_3
/*     */     //   525: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   528: iload_3
/*     */     //   529: iload #5
/*     */     //   531: iconst_5
/*     */     //   532: iadd
/*     */     //   533: aload_0
/*     */     //   534: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   537: invokevirtual dual : (Lscala/collection/concurrent/SNode;ILscala/collection/concurrent/SNode;IILscala/collection/concurrent/Gen;)Lscala/collection/concurrent/MainNode;
/*     */     //   540: invokespecial inode : (Lscala/collection/concurrent/MainNode;)Lscala/collection/concurrent/INode;
/*     */     //   543: aload_0
/*     */     //   544: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   547: invokevirtual updatedAt : (ILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   550: astore #17
/*     */     //   552: aload_0
/*     */     //   553: aload #29
/*     */     //   555: aload #17
/*     */     //   557: aload #8
/*     */     //   559: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   562: ifeq -> 571
/*     */     //   565: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   568: goto -> 572
/*     */     //   571: aconst_null
/*     */     //   572: astore #21
/*     */     //   574: goto -> 897
/*     */     //   577: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   580: invokevirtual KEY_PRESENT : ()Ljava/lang/Object;
/*     */     //   583: dup
/*     */     //   584: astore #18
/*     */     //   586: aload #4
/*     */     //   588: if_acmpne -> 595
/*     */     //   591: iconst_1
/*     */     //   592: goto -> 653
/*     */     //   595: aload #18
/*     */     //   597: ifnonnull -> 604
/*     */     //   600: iconst_0
/*     */     //   601: goto -> 653
/*     */     //   604: aload #18
/*     */     //   606: instanceof java/lang/Number
/*     */     //   609: ifeq -> 625
/*     */     //   612: aload #18
/*     */     //   614: checkcast java/lang/Number
/*     */     //   617: aload #4
/*     */     //   619: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   622: goto -> 653
/*     */     //   625: aload #18
/*     */     //   627: instanceof java/lang/Character
/*     */     //   630: ifeq -> 646
/*     */     //   633: aload #18
/*     */     //   635: checkcast java/lang/Character
/*     */     //   638: aload #4
/*     */     //   640: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   643: goto -> 653
/*     */     //   646: aload #18
/*     */     //   648: aload #4
/*     */     //   650: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   653: ifeq -> 739
/*     */     //   656: aload #20
/*     */     //   658: invokevirtual hc : ()I
/*     */     //   661: iload_3
/*     */     //   662: if_icmpne -> 731
/*     */     //   665: aload_0
/*     */     //   666: aload #20
/*     */     //   668: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   671: aload_1
/*     */     //   672: aload #8
/*     */     //   674: invokespecial equal : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   677: ifeq -> 731
/*     */     //   680: aload_0
/*     */     //   681: aload #29
/*     */     //   683: aload #29
/*     */     //   685: iload #27
/*     */     //   687: new scala/collection/concurrent/SNode
/*     */     //   690: dup
/*     */     //   691: aload_1
/*     */     //   692: aload_2
/*     */     //   693: iload_3
/*     */     //   694: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   697: aload_0
/*     */     //   698: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   701: invokevirtual updatedAt : (ILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   704: aload #8
/*     */     //   706: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   709: ifeq -> 727
/*     */     //   712: new scala/Some
/*     */     //   715: dup
/*     */     //   716: aload #20
/*     */     //   718: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   721: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   724: goto -> 734
/*     */     //   727: aconst_null
/*     */     //   728: goto -> 734
/*     */     //   731: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   734: astore #21
/*     */     //   736: goto -> 897
/*     */     //   739: aload #20
/*     */     //   741: invokevirtual hc : ()I
/*     */     //   744: iload_3
/*     */     //   745: if_icmpne -> 892
/*     */     //   748: aload_0
/*     */     //   749: aload #20
/*     */     //   751: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   754: aload_1
/*     */     //   755: aload #8
/*     */     //   757: invokespecial equal : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   760: ifeq -> 892
/*     */     //   763: aload #20
/*     */     //   765: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   768: dup
/*     */     //   769: astore #19
/*     */     //   771: aload #4
/*     */     //   773: if_acmpne -> 780
/*     */     //   776: iconst_1
/*     */     //   777: goto -> 838
/*     */     //   780: aload #19
/*     */     //   782: ifnonnull -> 789
/*     */     //   785: iconst_0
/*     */     //   786: goto -> 838
/*     */     //   789: aload #19
/*     */     //   791: instanceof java/lang/Number
/*     */     //   794: ifeq -> 810
/*     */     //   797: aload #19
/*     */     //   799: checkcast java/lang/Number
/*     */     //   802: aload #4
/*     */     //   804: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   807: goto -> 838
/*     */     //   810: aload #19
/*     */     //   812: instanceof java/lang/Character
/*     */     //   815: ifeq -> 831
/*     */     //   818: aload #19
/*     */     //   820: checkcast java/lang/Character
/*     */     //   823: aload #4
/*     */     //   825: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   828: goto -> 838
/*     */     //   831: aload #19
/*     */     //   833: aload #4
/*     */     //   835: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   838: ifeq -> 892
/*     */     //   841: aload_0
/*     */     //   842: aload #29
/*     */     //   844: aload #29
/*     */     //   846: iload #27
/*     */     //   848: new scala/collection/concurrent/SNode
/*     */     //   851: dup
/*     */     //   852: aload_1
/*     */     //   853: aload_2
/*     */     //   854: iload_3
/*     */     //   855: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   858: aload_0
/*     */     //   859: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   862: invokevirtual updatedAt : (ILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   865: aload #8
/*     */     //   867: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   870: ifeq -> 888
/*     */     //   873: new scala/Some
/*     */     //   876: dup
/*     */     //   877: aload #20
/*     */     //   879: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   882: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   885: goto -> 895
/*     */     //   888: aconst_null
/*     */     //   889: goto -> 895
/*     */     //   892: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   895: astore #21
/*     */     //   897: aload #21
/*     */     //   899: astore #22
/*     */     //   901: aload #22
/*     */     //   903: goto -> 1194
/*     */     //   906: new scala/MatchError
/*     */     //   909: dup
/*     */     //   910: aload #23
/*     */     //   912: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   915: athrow
/*     */     //   916: aload #4
/*     */     //   918: ifnonnull -> 927
/*     */     //   921: iconst_1
/*     */     //   922: istore #25
/*     */     //   924: goto -> 1015
/*     */     //   927: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   930: invokevirtual KEY_ABSENT : ()Ljava/lang/Object;
/*     */     //   933: dup
/*     */     //   934: astore #24
/*     */     //   936: aload #4
/*     */     //   938: if_acmpne -> 945
/*     */     //   941: iconst_1
/*     */     //   942: goto -> 1003
/*     */     //   945: aload #24
/*     */     //   947: ifnonnull -> 954
/*     */     //   950: iconst_0
/*     */     //   951: goto -> 1003
/*     */     //   954: aload #24
/*     */     //   956: instanceof java/lang/Number
/*     */     //   959: ifeq -> 975
/*     */     //   962: aload #24
/*     */     //   964: checkcast java/lang/Number
/*     */     //   967: aload #4
/*     */     //   969: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   972: goto -> 1003
/*     */     //   975: aload #24
/*     */     //   977: instanceof java/lang/Character
/*     */     //   980: ifeq -> 996
/*     */     //   983: aload #24
/*     */     //   985: checkcast java/lang/Character
/*     */     //   988: aload #4
/*     */     //   990: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   993: goto -> 1003
/*     */     //   996: aload #24
/*     */     //   998: aload #4
/*     */     //   1000: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1003: ifeq -> 1012
/*     */     //   1006: iconst_1
/*     */     //   1007: istore #25
/*     */     //   1009: goto -> 1015
/*     */     //   1012: iconst_0
/*     */     //   1013: istore #25
/*     */     //   1015: iload #25
/*     */     //   1017: ifeq -> 1100
/*     */     //   1020: aload #29
/*     */     //   1022: invokevirtual gen : ()Lscala/collection/concurrent/Gen;
/*     */     //   1025: aload_0
/*     */     //   1026: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   1029: if_acmpne -> 1037
/*     */     //   1032: aload #29
/*     */     //   1034: goto -> 1048
/*     */     //   1037: aload #29
/*     */     //   1039: aload_0
/*     */     //   1040: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   1043: aload #8
/*     */     //   1045: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   1048: astore #26
/*     */     //   1050: aload #26
/*     */     //   1052: iload #27
/*     */     //   1054: iload #28
/*     */     //   1056: new scala/collection/concurrent/SNode
/*     */     //   1059: dup
/*     */     //   1060: aload_1
/*     */     //   1061: aload_2
/*     */     //   1062: iload_3
/*     */     //   1063: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   1066: aload_0
/*     */     //   1067: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   1070: invokevirtual insertedAt : (IILscala/collection/concurrent/BasicNode;Lscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   1073: astore #30
/*     */     //   1075: aload_0
/*     */     //   1076: aload #29
/*     */     //   1078: aload #30
/*     */     //   1080: aload #8
/*     */     //   1082: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   1085: ifeq -> 1094
/*     */     //   1088: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1091: goto -> 1095
/*     */     //   1094: aconst_null
/*     */     //   1095: astore #32
/*     */     //   1097: goto -> 1192
/*     */     //   1100: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   1103: invokevirtual KEY_PRESENT : ()Ljava/lang/Object;
/*     */     //   1106: dup
/*     */     //   1107: astore #31
/*     */     //   1109: aload #4
/*     */     //   1111: if_acmpne -> 1118
/*     */     //   1114: iconst_1
/*     */     //   1115: goto -> 1176
/*     */     //   1118: aload #31
/*     */     //   1120: ifnonnull -> 1127
/*     */     //   1123: iconst_0
/*     */     //   1124: goto -> 1176
/*     */     //   1127: aload #31
/*     */     //   1129: instanceof java/lang/Number
/*     */     //   1132: ifeq -> 1148
/*     */     //   1135: aload #31
/*     */     //   1137: checkcast java/lang/Number
/*     */     //   1140: aload #4
/*     */     //   1142: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   1145: goto -> 1176
/*     */     //   1148: aload #31
/*     */     //   1150: instanceof java/lang/Character
/*     */     //   1153: ifeq -> 1169
/*     */     //   1156: aload #31
/*     */     //   1158: checkcast java/lang/Character
/*     */     //   1161: aload #4
/*     */     //   1163: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   1166: goto -> 1176
/*     */     //   1169: aload #31
/*     */     //   1171: aload #4
/*     */     //   1173: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1176: ifeq -> 1187
/*     */     //   1179: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1182: astore #32
/*     */     //   1184: goto -> 1192
/*     */     //   1187: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1190: astore #32
/*     */     //   1192: aload #32
/*     */     //   1194: astore #47
/*     */     //   1196: goto -> 1751
/*     */     //   1199: aload #48
/*     */     //   1201: instanceof scala/collection/concurrent/TNode
/*     */     //   1204: ifeq -> 1225
/*     */     //   1207: aload_0
/*     */     //   1208: aload #6
/*     */     //   1210: aload #8
/*     */     //   1212: iload #5
/*     */     //   1214: iconst_5
/*     */     //   1215: isub
/*     */     //   1216: invokespecial clean : (Lscala/collection/concurrent/INode;Lscala/collection/concurrent/TrieMap;I)V
/*     */     //   1219: aconst_null
/*     */     //   1220: astore #47
/*     */     //   1222: goto -> 1751
/*     */     //   1225: aload #48
/*     */     //   1227: instanceof scala/collection/concurrent/LNode
/*     */     //   1230: ifeq -> 1754
/*     */     //   1233: aload #48
/*     */     //   1235: checkcast scala/collection/concurrent/LNode
/*     */     //   1238: astore #44
/*     */     //   1240: aload #4
/*     */     //   1242: ifnonnull -> 1277
/*     */     //   1245: aload #44
/*     */     //   1247: aload_1
/*     */     //   1248: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   1251: astore #33
/*     */     //   1253: aload_0
/*     */     //   1254: aload_1
/*     */     //   1255: aload_2
/*     */     //   1256: aload #8
/*     */     //   1258: aload #44
/*     */     //   1260: invokespecial insertln$1 : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;Lscala/collection/concurrent/LNode;)Z
/*     */     //   1263: ifeq -> 1271
/*     */     //   1266: aload #33
/*     */     //   1268: goto -> 1272
/*     */     //   1271: aconst_null
/*     */     //   1272: astore #46
/*     */     //   1274: goto -> 1747
/*     */     //   1277: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   1280: invokevirtual KEY_ABSENT : ()Ljava/lang/Object;
/*     */     //   1283: dup
/*     */     //   1284: astore #34
/*     */     //   1286: aload #4
/*     */     //   1288: if_acmpne -> 1295
/*     */     //   1291: iconst_1
/*     */     //   1292: goto -> 1353
/*     */     //   1295: aload #34
/*     */     //   1297: ifnonnull -> 1304
/*     */     //   1300: iconst_0
/*     */     //   1301: goto -> 1353
/*     */     //   1304: aload #34
/*     */     //   1306: instanceof java/lang/Number
/*     */     //   1309: ifeq -> 1325
/*     */     //   1312: aload #34
/*     */     //   1314: checkcast java/lang/Number
/*     */     //   1317: aload #4
/*     */     //   1319: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   1322: goto -> 1353
/*     */     //   1325: aload #34
/*     */     //   1327: instanceof java/lang/Character
/*     */     //   1330: ifeq -> 1346
/*     */     //   1333: aload #34
/*     */     //   1335: checkcast java/lang/Character
/*     */     //   1338: aload #4
/*     */     //   1340: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   1343: goto -> 1353
/*     */     //   1346: aload #34
/*     */     //   1348: aload #4
/*     */     //   1350: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1353: ifeq -> 1424
/*     */     //   1356: aload #44
/*     */     //   1358: aload_1
/*     */     //   1359: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   1362: astore #35
/*     */     //   1364: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1367: dup
/*     */     //   1368: ifnonnull -> 1380
/*     */     //   1371: pop
/*     */     //   1372: aload #35
/*     */     //   1374: ifnull -> 1388
/*     */     //   1377: goto -> 1413
/*     */     //   1380: aload #35
/*     */     //   1382: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1385: ifeq -> 1413
/*     */     //   1388: aload_0
/*     */     //   1389: aload_1
/*     */     //   1390: aload_2
/*     */     //   1391: aload #8
/*     */     //   1393: aload #44
/*     */     //   1395: invokespecial insertln$1 : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;Lscala/collection/concurrent/LNode;)Z
/*     */     //   1398: ifeq -> 1407
/*     */     //   1401: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1404: goto -> 1408
/*     */     //   1407: aconst_null
/*     */     //   1408: astore #36
/*     */     //   1410: goto -> 1417
/*     */     //   1413: aload #35
/*     */     //   1415: astore #36
/*     */     //   1417: aload #36
/*     */     //   1419: astore #46
/*     */     //   1421: goto -> 1747
/*     */     //   1424: getstatic scala/collection/concurrent/INode$.MODULE$ : Lscala/collection/concurrent/INode$;
/*     */     //   1427: invokevirtual KEY_PRESENT : ()Ljava/lang/Object;
/*     */     //   1430: dup
/*     */     //   1431: astore #37
/*     */     //   1433: aload #4
/*     */     //   1435: if_acmpne -> 1442
/*     */     //   1438: iconst_1
/*     */     //   1439: goto -> 1500
/*     */     //   1442: aload #37
/*     */     //   1444: ifnonnull -> 1451
/*     */     //   1447: iconst_0
/*     */     //   1448: goto -> 1500
/*     */     //   1451: aload #37
/*     */     //   1453: instanceof java/lang/Number
/*     */     //   1456: ifeq -> 1472
/*     */     //   1459: aload #37
/*     */     //   1461: checkcast java/lang/Number
/*     */     //   1464: aload #4
/*     */     //   1466: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   1469: goto -> 1500
/*     */     //   1472: aload #37
/*     */     //   1474: instanceof java/lang/Character
/*     */     //   1477: ifeq -> 1493
/*     */     //   1480: aload #37
/*     */     //   1482: checkcast java/lang/Character
/*     */     //   1485: aload #4
/*     */     //   1487: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   1490: goto -> 1500
/*     */     //   1493: aload #37
/*     */     //   1495: aload #4
/*     */     //   1497: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1500: ifeq -> 1606
/*     */     //   1503: aload #44
/*     */     //   1505: aload_1
/*     */     //   1506: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   1509: astore #40
/*     */     //   1511: aload #40
/*     */     //   1513: instanceof scala/Some
/*     */     //   1516: ifeq -> 1560
/*     */     //   1519: aload #40
/*     */     //   1521: checkcast scala/Some
/*     */     //   1524: astore #38
/*     */     //   1526: aload_0
/*     */     //   1527: aload_1
/*     */     //   1528: aload_2
/*     */     //   1529: aload #8
/*     */     //   1531: aload #44
/*     */     //   1533: invokespecial insertln$1 : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;Lscala/collection/concurrent/LNode;)Z
/*     */     //   1536: ifeq -> 1554
/*     */     //   1539: new scala/Some
/*     */     //   1542: dup
/*     */     //   1543: aload #38
/*     */     //   1545: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   1548: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   1551: goto -> 1555
/*     */     //   1554: aconst_null
/*     */     //   1555: astore #39
/*     */     //   1557: goto -> 1589
/*     */     //   1560: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1563: dup
/*     */     //   1564: ifnonnull -> 1576
/*     */     //   1567: pop
/*     */     //   1568: aload #40
/*     */     //   1570: ifnull -> 1584
/*     */     //   1573: goto -> 1596
/*     */     //   1576: aload #40
/*     */     //   1578: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1581: ifeq -> 1596
/*     */     //   1584: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1587: astore #39
/*     */     //   1589: aload #39
/*     */     //   1591: astore #46
/*     */     //   1593: goto -> 1747
/*     */     //   1596: new scala/MatchError
/*     */     //   1599: dup
/*     */     //   1600: aload #40
/*     */     //   1602: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   1605: athrow
/*     */     //   1606: aload #44
/*     */     //   1608: aload_1
/*     */     //   1609: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   1612: astore #41
/*     */     //   1614: aload #41
/*     */     //   1616: instanceof scala/Some
/*     */     //   1619: ifeq -> 1738
/*     */     //   1622: aload #41
/*     */     //   1624: checkcast scala/Some
/*     */     //   1627: astore #42
/*     */     //   1629: aload #42
/*     */     //   1631: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   1634: dup
/*     */     //   1635: astore #43
/*     */     //   1637: aload #4
/*     */     //   1639: if_acmpne -> 1646
/*     */     //   1642: iconst_1
/*     */     //   1643: goto -> 1704
/*     */     //   1646: aload #43
/*     */     //   1648: ifnonnull -> 1655
/*     */     //   1651: iconst_0
/*     */     //   1652: goto -> 1704
/*     */     //   1655: aload #43
/*     */     //   1657: instanceof java/lang/Number
/*     */     //   1660: ifeq -> 1676
/*     */     //   1663: aload #43
/*     */     //   1665: checkcast java/lang/Number
/*     */     //   1668: aload #4
/*     */     //   1670: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   1673: goto -> 1704
/*     */     //   1676: aload #43
/*     */     //   1678: instanceof java/lang/Character
/*     */     //   1681: ifeq -> 1697
/*     */     //   1684: aload #43
/*     */     //   1686: checkcast java/lang/Character
/*     */     //   1689: aload #4
/*     */     //   1691: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   1694: goto -> 1704
/*     */     //   1697: aload #43
/*     */     //   1699: aload #4
/*     */     //   1701: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1704: ifeq -> 1738
/*     */     //   1707: aload_0
/*     */     //   1708: aload_1
/*     */     //   1709: aload_2
/*     */     //   1710: aload #8
/*     */     //   1712: aload #44
/*     */     //   1714: invokespecial insertln$1 : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;Lscala/collection/concurrent/LNode;)Z
/*     */     //   1717: ifeq -> 1732
/*     */     //   1720: new scala/Some
/*     */     //   1723: dup
/*     */     //   1724: aload #4
/*     */     //   1726: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   1729: goto -> 1733
/*     */     //   1732: aconst_null
/*     */     //   1733: astore #45
/*     */     //   1735: goto -> 1743
/*     */     //   1738: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   1741: astore #45
/*     */     //   1743: aload #45
/*     */     //   1745: astore #46
/*     */     //   1747: aload #46
/*     */     //   1749: astore #47
/*     */     //   1751: aload #47
/*     */     //   1753: areturn
/*     */     //   1754: new scala/MatchError
/*     */     //   1757: dup
/*     */     //   1758: aload #48
/*     */     //   1760: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   1763: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #147	-> 0
/*     */     //   #150	-> 8
/*     */     //   #149	-> 8
/*     */     //   #151	-> 23
/*     */     //   #152	-> 32
/*     */     //   #153	-> 38
/*     */     //   #154	-> 45
/*     */     //   #155	-> 51
/*     */     //   #156	-> 61
/*     */     //   #158	-> 70
/*     */     //   #159	-> 80
/*     */     //   #160	-> 95
/*     */     //   #162	-> 130
/*     */     //   #160	-> 151
/*     */     //   #165	-> 156
/*     */     //   #166	-> 171
/*     */     //   #167	-> 176
/*     */     //   #168	-> 200
/*     */     //   #170	-> 251
/*     */     //   #171	-> 281
/*     */     //   #172	-> 330
/*     */     //   #167	-> 350
/*     */     //   #175	-> 355
/*     */     //   #176	-> 434
/*     */     //   #178	-> 473
/*     */     //   #179	-> 503
/*     */     //   #180	-> 552
/*     */     //   #176	-> 572
/*     */     //   #183	-> 577
/*     */     //   #184	-> 656
/*     */     //   #185	-> 680
/*     */     //   #186	-> 731
/*     */     //   #184	-> 734
/*     */     //   #188	-> 739
/*     */     //   #189	-> 841
/*     */     //   #190	-> 892
/*     */     //   #188	-> 895
/*     */     //   #165	-> 897
/*     */     //   #158	-> 901
/*     */     //   #194	-> 916
/*     */     //   #193	-> 916
/*     */     //   #195	-> 1020
/*     */     //   #196	-> 1050
/*     */     //   #197	-> 1075
/*     */     //   #194	-> 1095
/*     */     //   #198	-> 1100
/*     */     //   #199	-> 1187
/*     */     //   #193	-> 1192
/*     */     //   #150	-> 1194
/*     */     //   #201	-> 1199
/*     */     //   #202	-> 1207
/*     */     //   #201	-> 1220
/*     */     //   #204	-> 1225
/*     */     //   #210	-> 1240
/*     */     //   #209	-> 1240
/*     */     //   #211	-> 1245
/*     */     //   #212	-> 1253
/*     */     //   #210	-> 1272
/*     */     //   #213	-> 1277
/*     */     //   #214	-> 1356
/*     */     //   #215	-> 1364
/*     */     //   #216	-> 1413
/*     */     //   #214	-> 1417
/*     */     //   #218	-> 1424
/*     */     //   #219	-> 1503
/*     */     //   #220	-> 1511
/*     */     //   #219	-> 1543
/*     */     //   #220	-> 1545
/*     */     //   #221	-> 1560
/*     */     //   #219	-> 1589
/*     */     //   #224	-> 1606
/*     */     //   #225	-> 1614
/*     */     //   #224	-> 1629
/*     */     //   #225	-> 1631
/*     */     //   #226	-> 1738
/*     */     //   #224	-> 1743
/*     */     //   #209	-> 1747
/*     */     //   #204	-> 1749
/*     */     //   #149	-> 1751
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	1764	0	this	Lscala/collection/concurrent/INode;
/*     */     //   0	1764	1	k	Ljava/lang/Object;
/*     */     //   0	1764	2	v	Ljava/lang/Object;
/*     */     //   0	1764	3	hc	I
/*     */     //   0	1764	4	cond	Ljava/lang/Object;
/*     */     //   0	1764	5	lev	I
/*     */     //   0	1764	6	parent	Lscala/collection/concurrent/INode;
/*     */     //   0	1764	7	startgen	Lscala/collection/concurrent/Gen;
/*     */     //   0	1764	8	ct	Lscala/collection/concurrent/TrieMap;
/*     */     //   8	1745	48	m	Lscala/collection/concurrent/MainNode;
/*     */     //   32	1732	9	idx	I
/*     */     //   38	1726	28	flag	I
/*     */     //   45	1719	11	bmp	I
/*     */     //   51	1713	10	mask	I
/*     */     //   61	1703	27	pos	I
/*     */     //   281	1483	13	rn	Lscala/collection/concurrent/CNode;
/*     */     //   330	1434	14	nn	Lscala/collection/concurrent/CNode;
/*     */     //   503	1261	16	rn	Lscala/collection/concurrent/CNode;
/*     */     //   552	1212	17	nn	Lscala/collection/concurrent/CNode;
/*     */     //   1050	714	26	rn	Lscala/collection/concurrent/CNode;
/*     */     //   1075	689	30	ncnode	Lscala/collection/concurrent/CNode;
/*     */     //   1253	511	33	optv	Lscala/Option;
/*     */   }
/*     */   
/*     */   private final boolean insertln$1(Object k$1, Object v$1, TrieMap<K, V> ct$1, LNode<Object, Object> x4$1) {
/* 206 */     LNode<Object, Object> nn = x4$1.inserted(k$1, v$1);
/* 207 */     return GCAS((MainNode)x4$1, (MainNode)nn, ct$1);
/*     */   }
/*     */   
/*     */   public Object rec_lookup(Object k, int hc, int lev, INode parent, Gen startgen, TrieMap ct) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload #6
/*     */     //   3: invokevirtual GCAS_READ : (Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   6: astore #22
/*     */     //   8: aload #22
/*     */     //   10: instanceof scala/collection/concurrent/CNode
/*     */     //   13: ifeq -> 239
/*     */     //   16: aload #22
/*     */     //   18: checkcast scala/collection/concurrent/CNode
/*     */     //   21: astore #12
/*     */     //   23: iload_2
/*     */     //   24: iload_3
/*     */     //   25: iushr
/*     */     //   26: bipush #31
/*     */     //   28: iand
/*     */     //   29: istore #7
/*     */     //   31: iconst_1
/*     */     //   32: iload #7
/*     */     //   34: ishl
/*     */     //   35: istore #9
/*     */     //   37: aload #12
/*     */     //   39: invokevirtual bitmap : ()I
/*     */     //   42: istore #8
/*     */     //   44: iload #8
/*     */     //   46: iload #9
/*     */     //   48: iand
/*     */     //   49: iconst_0
/*     */     //   50: if_icmpne -> 57
/*     */     //   53: aconst_null
/*     */     //   54: goto -> 224
/*     */     //   57: iload #8
/*     */     //   59: iconst_m1
/*     */     //   60: if_icmpne -> 68
/*     */     //   63: iload #7
/*     */     //   65: goto -> 78
/*     */     //   68: iload #8
/*     */     //   70: iload #9
/*     */     //   72: iconst_1
/*     */     //   73: isub
/*     */     //   74: iand
/*     */     //   75: invokestatic bitCount : (I)I
/*     */     //   78: istore #10
/*     */     //   80: aload #12
/*     */     //   82: invokevirtual array : ()[Lscala/collection/concurrent/BasicNode;
/*     */     //   85: iload #10
/*     */     //   87: aaload
/*     */     //   88: astore #16
/*     */     //   90: aload #16
/*     */     //   92: instanceof scala/collection/concurrent/INode
/*     */     //   95: ifeq -> 164
/*     */     //   98: aload #16
/*     */     //   100: checkcast scala/collection/concurrent/INode
/*     */     //   103: astore #11
/*     */     //   105: aload #6
/*     */     //   107: invokevirtual isReadOnly : ()Z
/*     */     //   110: ifne -> 123
/*     */     //   113: aload #5
/*     */     //   115: aload #11
/*     */     //   117: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   120: if_acmpne -> 140
/*     */     //   123: aload #11
/*     */     //   125: aload_1
/*     */     //   126: iload_2
/*     */     //   127: iload_3
/*     */     //   128: iconst_5
/*     */     //   129: iadd
/*     */     //   130: aload_0
/*     */     //   131: astore #4
/*     */     //   133: istore_3
/*     */     //   134: istore_2
/*     */     //   135: astore_1
/*     */     //   136: astore_0
/*     */     //   137: goto -> 0
/*     */     //   140: aload_0
/*     */     //   141: aload #12
/*     */     //   143: aload #12
/*     */     //   145: aload #5
/*     */     //   147: aload #6
/*     */     //   149: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   152: aload #6
/*     */     //   154: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   157: ifne -> 0
/*     */     //   160: getstatic scala/collection/concurrent/INodeBase.RESTART : Ljava/lang/Object;
/*     */     //   163: areturn
/*     */     //   164: aload #16
/*     */     //   166: instanceof scala/collection/concurrent/SNode
/*     */     //   169: ifeq -> 229
/*     */     //   172: aload #16
/*     */     //   174: checkcast scala/collection/concurrent/SNode
/*     */     //   177: astore #14
/*     */     //   179: aload #14
/*     */     //   181: invokevirtual hc : ()I
/*     */     //   184: iload_2
/*     */     //   185: if_icmpne -> 219
/*     */     //   188: aload #14
/*     */     //   190: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   193: astore #13
/*     */     //   195: aload #6
/*     */     //   197: invokevirtual equality : ()Lscala/math/Equiv;
/*     */     //   200: aload #13
/*     */     //   202: aload_1
/*     */     //   203: invokeinterface equiv : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   208: ifeq -> 219
/*     */     //   211: aload #14
/*     */     //   213: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   216: goto -> 220
/*     */     //   219: aconst_null
/*     */     //   220: astore #15
/*     */     //   222: aload #15
/*     */     //   224: astore #21
/*     */     //   226: goto -> 329
/*     */     //   229: new scala/MatchError
/*     */     //   232: dup
/*     */     //   233: aload #16
/*     */     //   235: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   238: athrow
/*     */     //   239: aload #22
/*     */     //   241: instanceof scala/collection/concurrent/TNode
/*     */     //   244: ifeq -> 272
/*     */     //   247: aload #22
/*     */     //   249: checkcast scala/collection/concurrent/TNode
/*     */     //   252: astore #17
/*     */     //   254: aload_0
/*     */     //   255: aload #17
/*     */     //   257: aload_1
/*     */     //   258: iload_2
/*     */     //   259: iload_3
/*     */     //   260: aload #4
/*     */     //   262: aload #6
/*     */     //   264: invokespecial cleanReadOnly$1 : (Lscala/collection/concurrent/TNode;Ljava/lang/Object;IILscala/collection/concurrent/INode;Lscala/collection/concurrent/TrieMap;)Ljava/lang/Object;
/*     */     //   267: astore #21
/*     */     //   269: goto -> 329
/*     */     //   272: aload #22
/*     */     //   274: instanceof scala/collection/concurrent/LNode
/*     */     //   277: ifeq -> 332
/*     */     //   280: aload #22
/*     */     //   282: checkcast scala/collection/concurrent/LNode
/*     */     //   285: astore #18
/*     */     //   287: aload #18
/*     */     //   289: invokevirtual listmap : ()Lscala/collection/immutable/ListMap;
/*     */     //   292: aload_1
/*     */     //   293: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   296: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   299: invokevirtual conforms : ()Lscala/Predef$$less$colon$less;
/*     */     //   302: astore #19
/*     */     //   304: dup
/*     */     //   305: astore #20
/*     */     //   307: invokevirtual isEmpty : ()Z
/*     */     //   310: ifeq -> 322
/*     */     //   313: aload #19
/*     */     //   315: aconst_null
/*     */     //   316: invokevirtual apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   319: goto -> 327
/*     */     //   322: aload #20
/*     */     //   324: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   327: astore #21
/*     */     //   329: aload #21
/*     */     //   331: areturn
/*     */     //   332: new scala/MatchError
/*     */     //   335: dup
/*     */     //   336: aload #22
/*     */     //   338: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   341: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #237	-> 0
/*     */     //   #240	-> 8
/*     */     //   #239	-> 8
/*     */     //   #241	-> 23
/*     */     //   #242	-> 31
/*     */     //   #243	-> 37
/*     */     //   #244	-> 44
/*     */     //   #246	-> 57
/*     */     //   #247	-> 80
/*     */     //   #249	-> 90
/*     */     //   #248	-> 90
/*     */     //   #250	-> 105
/*     */     //   #252	-> 140
/*     */     //   #253	-> 160
/*     */     //   #255	-> 164
/*     */     //   #256	-> 179
/*     */     //   #248	-> 222
/*     */     //   #240	-> 224
/*     */     //   #248	-> 229
/*     */     //   #260	-> 239
/*     */     //   #268	-> 254
/*     */     //   #260	-> 267
/*     */     //   #269	-> 272
/*     */     //   #270	-> 287
/*     */     //   #239	-> 329
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	342	0	this	Lscala/collection/concurrent/INode;
/*     */     //   0	342	1	k	Ljava/lang/Object;
/*     */     //   0	342	2	hc	I
/*     */     //   0	342	3	lev	I
/*     */     //   0	342	4	parent	Lscala/collection/concurrent/INode;
/*     */     //   0	342	5	startgen	Lscala/collection/concurrent/Gen;
/*     */     //   0	342	6	ct	Lscala/collection/concurrent/TrieMap;
/*     */     //   8	323	22	m	Lscala/collection/concurrent/MainNode;
/*     */     //   31	311	7	idx	I
/*     */     //   37	305	9	flag	I
/*     */     //   44	298	8	bmp	I
/*     */     //   80	144	10	pos	I
/*     */     //   90	134	16	sub	Lscala/collection/concurrent/BasicNode;
/*     */   }
/*     */   
/*     */   private final Object cleanReadOnly$1(TNode tn, Object k$2, int hc$1, int lev$1, INode<K, V> parent$1, TrieMap<K, V> ct$2) {
/* 262 */     clean(parent$1, ct$2, lev$1 - 5);
/* 265 */     if (tn.hc() == hc$1) {
/*     */       Object object;
/* 265 */       if (((object = tn.k()) == k$2) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, k$2) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, k$2) : object.equals(k$2)))));
/*     */     } 
/* 265 */     return ct$2.nonReadOnly() ? INodeBase.RESTART : null;
/*     */   }
/*     */   
/*     */   public Option<V> rec_remove(Object k, Object v, int hc, int lev, INode parent, Gen startgen, TrieMap ct) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload #7
/*     */     //   3: invokevirtual GCAS_READ : (Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   6: astore #30
/*     */     //   8: aload #30
/*     */     //   10: instanceof scala/collection/concurrent/CNode
/*     */     //   13: ifeq -> 436
/*     */     //   16: aload #30
/*     */     //   18: checkcast scala/collection/concurrent/CNode
/*     */     //   21: astore #14
/*     */     //   23: iload_3
/*     */     //   24: iload #4
/*     */     //   26: iushr
/*     */     //   27: bipush #31
/*     */     //   29: iand
/*     */     //   30: istore #8
/*     */     //   32: aload #14
/*     */     //   34: invokevirtual bitmap : ()I
/*     */     //   37: istore #9
/*     */     //   39: iconst_1
/*     */     //   40: iload #8
/*     */     //   42: ishl
/*     */     //   43: istore #13
/*     */     //   45: iload #9
/*     */     //   47: iload #13
/*     */     //   49: iand
/*     */     //   50: iconst_0
/*     */     //   51: if_icmpne -> 60
/*     */     //   54: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   57: goto -> 421
/*     */     //   60: iload #9
/*     */     //   62: iload #13
/*     */     //   64: iconst_1
/*     */     //   65: isub
/*     */     //   66: iand
/*     */     //   67: invokestatic bitCount : (I)I
/*     */     //   70: istore #12
/*     */     //   72: aload #14
/*     */     //   74: invokevirtual array : ()[Lscala/collection/concurrent/BasicNode;
/*     */     //   77: iload #12
/*     */     //   79: aaload
/*     */     //   80: astore #20
/*     */     //   82: aload #20
/*     */     //   84: instanceof scala/collection/concurrent/INode
/*     */     //   87: ifeq -> 171
/*     */     //   90: aload #20
/*     */     //   92: checkcast scala/collection/concurrent/INode
/*     */     //   95: astore #10
/*     */     //   97: aload #6
/*     */     //   99: aload #10
/*     */     //   101: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   104: if_acmpne -> 127
/*     */     //   107: aload #10
/*     */     //   109: aload_1
/*     */     //   110: aload_2
/*     */     //   111: iload_3
/*     */     //   112: iload #4
/*     */     //   114: iconst_5
/*     */     //   115: iadd
/*     */     //   116: aload_0
/*     */     //   117: aload #6
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual rec_remove : (Ljava/lang/Object;Ljava/lang/Object;IILscala/collection/concurrent/INode;Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/Option;
/*     */     //   124: goto -> 166
/*     */     //   127: aload_0
/*     */     //   128: aload #14
/*     */     //   130: aload #14
/*     */     //   132: aload #6
/*     */     //   134: aload #7
/*     */     //   136: invokevirtual renewed : (Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/CNode;
/*     */     //   139: aload #7
/*     */     //   141: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   144: ifeq -> 165
/*     */     //   147: aload_0
/*     */     //   148: aload_1
/*     */     //   149: aload_2
/*     */     //   150: iload_3
/*     */     //   151: iload #4
/*     */     //   153: aload #5
/*     */     //   155: aload #6
/*     */     //   157: aload #7
/*     */     //   159: invokevirtual rec_remove : (Ljava/lang/Object;Ljava/lang/Object;IILscala/collection/concurrent/INode;Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)Lscala/Option;
/*     */     //   162: goto -> 166
/*     */     //   165: aconst_null
/*     */     //   166: astore #19
/*     */     //   168: goto -> 345
/*     */     //   171: aload #20
/*     */     //   173: instanceof scala/collection/concurrent/SNode
/*     */     //   176: ifeq -> 426
/*     */     //   179: aload #20
/*     */     //   181: checkcast scala/collection/concurrent/SNode
/*     */     //   184: astore #16
/*     */     //   186: aload #16
/*     */     //   188: invokevirtual hc : ()I
/*     */     //   191: iload_3
/*     */     //   192: if_icmpne -> 340
/*     */     //   195: aload_0
/*     */     //   196: aload #16
/*     */     //   198: invokevirtual k : ()Ljava/lang/Object;
/*     */     //   201: aload_1
/*     */     //   202: aload #7
/*     */     //   204: invokespecial equal : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   207: ifeq -> 340
/*     */     //   210: aload_2
/*     */     //   211: ifnull -> 288
/*     */     //   214: aload #16
/*     */     //   216: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   219: dup
/*     */     //   220: astore #11
/*     */     //   222: aload_2
/*     */     //   223: if_acmpne -> 230
/*     */     //   226: iconst_1
/*     */     //   227: goto -> 285
/*     */     //   230: aload #11
/*     */     //   232: ifnonnull -> 239
/*     */     //   235: iconst_0
/*     */     //   236: goto -> 285
/*     */     //   239: aload #11
/*     */     //   241: instanceof java/lang/Number
/*     */     //   244: ifeq -> 259
/*     */     //   247: aload #11
/*     */     //   249: checkcast java/lang/Number
/*     */     //   252: aload_2
/*     */     //   253: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   256: goto -> 285
/*     */     //   259: aload #11
/*     */     //   261: instanceof java/lang/Character
/*     */     //   264: ifeq -> 279
/*     */     //   267: aload #11
/*     */     //   269: checkcast java/lang/Character
/*     */     //   272: aload_2
/*     */     //   273: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   276: goto -> 285
/*     */     //   279: aload #11
/*     */     //   281: aload_2
/*     */     //   282: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   285: ifeq -> 340
/*     */     //   288: aload #14
/*     */     //   290: iload #12
/*     */     //   292: iload #13
/*     */     //   294: aload_0
/*     */     //   295: getfield gen : Lscala/collection/concurrent/Gen;
/*     */     //   298: invokevirtual removedAt : (IILscala/collection/concurrent/Gen;)Lscala/collection/concurrent/CNode;
/*     */     //   301: iload #4
/*     */     //   303: invokevirtual toContracted : (I)Lscala/collection/concurrent/MainNode;
/*     */     //   306: astore #15
/*     */     //   308: aload_0
/*     */     //   309: aload #14
/*     */     //   311: aload #15
/*     */     //   313: aload #7
/*     */     //   315: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   318: ifeq -> 336
/*     */     //   321: new scala/Some
/*     */     //   324: dup
/*     */     //   325: aload #16
/*     */     //   327: invokevirtual v : ()Ljava/lang/Object;
/*     */     //   330: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   333: goto -> 343
/*     */     //   336: aconst_null
/*     */     //   337: goto -> 343
/*     */     //   340: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   343: astore #19
/*     */     //   345: aload #19
/*     */     //   347: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   350: astore #17
/*     */     //   352: dup
/*     */     //   353: ifnonnull -> 365
/*     */     //   356: pop
/*     */     //   357: aload #17
/*     */     //   359: ifnull -> 378
/*     */     //   362: goto -> 373
/*     */     //   365: aload #17
/*     */     //   367: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   370: ifne -> 378
/*     */     //   373: aload #19
/*     */     //   375: ifnonnull -> 383
/*     */     //   378: aload #19
/*     */     //   380: goto -> 421
/*     */     //   383: aload #5
/*     */     //   385: ifnull -> 419
/*     */     //   388: aload_0
/*     */     //   389: aload #7
/*     */     //   391: invokevirtual GCAS_READ : (Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   394: astore #18
/*     */     //   396: aload #18
/*     */     //   398: instanceof scala/collection/concurrent/TNode
/*     */     //   401: ifeq -> 419
/*     */     //   404: aload_0
/*     */     //   405: aload #18
/*     */     //   407: iload_3
/*     */     //   408: iload #4
/*     */     //   410: aload #5
/*     */     //   412: aload #6
/*     */     //   414: aload #7
/*     */     //   416: invokespecial cleanParent$1 : (Ljava/lang/Object;IILscala/collection/concurrent/INode;Lscala/collection/concurrent/Gen;Lscala/collection/concurrent/TrieMap;)V
/*     */     //   419: aload #19
/*     */     //   421: astore #29
/*     */     //   423: goto -> 661
/*     */     //   426: new scala/MatchError
/*     */     //   429: dup
/*     */     //   430: aload #20
/*     */     //   432: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   435: athrow
/*     */     //   436: aload #30
/*     */     //   438: instanceof scala/collection/concurrent/TNode
/*     */     //   441: ifeq -> 462
/*     */     //   444: aload_0
/*     */     //   445: aload #5
/*     */     //   447: aload #7
/*     */     //   449: iload #4
/*     */     //   451: iconst_5
/*     */     //   452: isub
/*     */     //   453: invokespecial clean : (Lscala/collection/concurrent/INode;Lscala/collection/concurrent/TrieMap;I)V
/*     */     //   456: aconst_null
/*     */     //   457: astore #29
/*     */     //   459: goto -> 661
/*     */     //   462: aload #30
/*     */     //   464: instanceof scala/collection/concurrent/LNode
/*     */     //   467: ifeq -> 664
/*     */     //   470: aload #30
/*     */     //   472: checkcast scala/collection/concurrent/LNode
/*     */     //   475: astore #25
/*     */     //   477: aload_2
/*     */     //   478: ifnonnull -> 521
/*     */     //   481: aload #25
/*     */     //   483: aload_1
/*     */     //   484: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   487: astore #22
/*     */     //   489: aload #25
/*     */     //   491: aload_1
/*     */     //   492: aload #7
/*     */     //   494: invokevirtual removed : (Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   497: astore #21
/*     */     //   499: aload_0
/*     */     //   500: aload #25
/*     */     //   502: aload #21
/*     */     //   504: aload #7
/*     */     //   506: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   509: ifeq -> 517
/*     */     //   512: aload #22
/*     */     //   514: goto -> 659
/*     */     //   517: aconst_null
/*     */     //   518: goto -> 659
/*     */     //   521: aload #25
/*     */     //   523: aload_1
/*     */     //   524: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   527: astore #23
/*     */     //   529: aload #23
/*     */     //   531: instanceof scala/Some
/*     */     //   534: ifeq -> 652
/*     */     //   537: aload #23
/*     */     //   539: checkcast scala/Some
/*     */     //   542: astore #27
/*     */     //   544: aload #27
/*     */     //   546: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   549: dup
/*     */     //   550: astore #24
/*     */     //   552: aload_2
/*     */     //   553: if_acmpne -> 560
/*     */     //   556: iconst_1
/*     */     //   557: goto -> 615
/*     */     //   560: aload #24
/*     */     //   562: ifnonnull -> 569
/*     */     //   565: iconst_0
/*     */     //   566: goto -> 615
/*     */     //   569: aload #24
/*     */     //   571: instanceof java/lang/Number
/*     */     //   574: ifeq -> 589
/*     */     //   577: aload #24
/*     */     //   579: checkcast java/lang/Number
/*     */     //   582: aload_2
/*     */     //   583: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   586: goto -> 615
/*     */     //   589: aload #24
/*     */     //   591: instanceof java/lang/Character
/*     */     //   594: ifeq -> 609
/*     */     //   597: aload #24
/*     */     //   599: checkcast java/lang/Character
/*     */     //   602: aload_2
/*     */     //   603: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   606: goto -> 615
/*     */     //   609: aload #24
/*     */     //   611: aload_2
/*     */     //   612: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   615: ifeq -> 652
/*     */     //   618: aload #25
/*     */     //   620: aload_1
/*     */     //   621: aload #7
/*     */     //   623: invokevirtual removed : (Ljava/lang/Object;Lscala/collection/concurrent/TrieMap;)Lscala/collection/concurrent/MainNode;
/*     */     //   626: astore #26
/*     */     //   628: aload_0
/*     */     //   629: aload #25
/*     */     //   631: aload #26
/*     */     //   633: aload #7
/*     */     //   635: invokevirtual GCAS : (Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/MainNode;Lscala/collection/concurrent/TrieMap;)Z
/*     */     //   638: ifeq -> 646
/*     */     //   641: aload #27
/*     */     //   643: goto -> 647
/*     */     //   646: aconst_null
/*     */     //   647: astore #28
/*     */     //   649: goto -> 657
/*     */     //   652: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   655: astore #28
/*     */     //   657: aload #28
/*     */     //   659: astore #29
/*     */     //   661: aload #29
/*     */     //   663: areturn
/*     */     //   664: new scala/MatchError
/*     */     //   667: dup
/*     */     //   668: aload #30
/*     */     //   670: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   673: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #280	-> 0
/*     */     //   #283	-> 8
/*     */     //   #282	-> 8
/*     */     //   #284	-> 23
/*     */     //   #285	-> 32
/*     */     //   #286	-> 39
/*     */     //   #287	-> 45
/*     */     //   #289	-> 60
/*     */     //   #290	-> 72
/*     */     //   #292	-> 82
/*     */     //   #291	-> 82
/*     */     //   #293	-> 97
/*     */     //   #295	-> 127
/*     */     //   #293	-> 166
/*     */     //   #298	-> 171
/*     */     //   #299	-> 186
/*     */     //   #300	-> 288
/*     */     //   #301	-> 308
/*     */     //   #302	-> 340
/*     */     //   #299	-> 343
/*     */     //   #305	-> 345
/*     */     //   #291	-> 345
/*     */     //   #329	-> 383
/*     */     //   #330	-> 388
/*     */     //   #331	-> 396
/*     */     //   #332	-> 404
/*     */     //   #335	-> 419
/*     */     //   #283	-> 421
/*     */     //   #291	-> 426
/*     */     //   #338	-> 436
/*     */     //   #339	-> 444
/*     */     //   #338	-> 457
/*     */     //   #341	-> 462
/*     */     //   #342	-> 477
/*     */     //   #343	-> 481
/*     */     //   #344	-> 489
/*     */     //   #345	-> 499
/*     */     //   #346	-> 521
/*     */     //   #347	-> 529
/*     */     //   #346	-> 544
/*     */     //   #347	-> 546
/*     */     //   #348	-> 618
/*     */     //   #349	-> 628
/*     */     //   #347	-> 647
/*     */     //   #350	-> 652
/*     */     //   #346	-> 657
/*     */     //   #342	-> 659
/*     */     //   #282	-> 661
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	674	0	this	Lscala/collection/concurrent/INode;
/*     */     //   0	674	1	k	Ljava/lang/Object;
/*     */     //   0	674	2	v	Ljava/lang/Object;
/*     */     //   0	674	3	hc	I
/*     */     //   0	674	4	lev	I
/*     */     //   0	674	5	parent	Lscala/collection/concurrent/INode;
/*     */     //   0	674	6	startgen	Lscala/collection/concurrent/Gen;
/*     */     //   0	674	7	ct	Lscala/collection/concurrent/TrieMap;
/*     */     //   8	655	30	m	Lscala/collection/concurrent/MainNode;
/*     */     //   32	642	8	idx	I
/*     */     //   39	635	9	bmp	I
/*     */     //   45	629	13	flag	I
/*     */     //   72	602	12	pos	I
/*     */     //   82	592	20	sub	Lscala/collection/concurrent/BasicNode;
/*     */     //   308	366	15	ncn	Lscala/collection/concurrent/MainNode;
/*     */     //   396	278	18	n	Lscala/collection/concurrent/MainNode;
/*     */     //   489	185	22	optv	Lscala/Option;
/*     */     //   499	175	21	nn	Lscala/collection/concurrent/MainNode;
/*     */     //   628	46	26	nn	Lscala/collection/concurrent/MainNode;
/*     */   }
/*     */   
/*     */   private final void cleanParent$1(Object nonlive, int hc$2, int lev$2, INode parent$2, Gen startgen$1, TrieMap ct$3) {
/*     */     while (true) {
/* 308 */       MainNode pm = parent$2.GCAS_READ(ct$3);
/* 309 */       if (pm instanceof CNode) {
/* 309 */         CNode cNode = (CNode)pm;
/* 311 */         int idx = hc$2 >>> lev$2 - 5 & 0x1F;
/* 312 */         int bmp = cNode.bitmap();
/* 313 */         int flag = 1 << idx;
/* 314 */         if ((bmp & flag) != 0) {
/* 316 */           int pos = Integer.bitCount(bmp & flag - 1);
/* 317 */           BasicNode sub = cNode.array()[pos];
/* 318 */           if (sub == this) {
/* 319 */             if (nonlive instanceof TNode) {
/* 319 */               TNode tNode = (TNode)nonlive;
/* 320 */               MainNode ncn = cNode.updatedAt(pos, tNode.copyUntombed(), this.gen).toContracted(lev$2 - 5);
/* 321 */               if (!parent$2.GCAS(cNode, ncn, ct$3)) {
/* 322 */                 if ((ct$3.readRoot(ct$3.readRoot$default$1())).gen == null) {
/* 322 */                   if (startgen$1 != null)
/*     */                     break; 
/*     */                   continue;
/*     */                 } 
/* 322 */                 if (!(ct$3.readRoot(ct$3.readRoot$default$1())).gen.equals(startgen$1))
/*     */                   break; 
/*     */                 continue;
/*     */               } 
/*     */               break;
/*     */             } 
/*     */             throw new MatchError(nonlive);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void clean(INode nd, TrieMap ct, int lev) {
/* 356 */     MainNode m = nd.GCAS_READ(ct);
/* 357 */     if (m instanceof CNode) {
/* 357 */       CNode cNode = (CNode)m;
/* 357 */       nd.GCAS(cNode, cNode.toCompressed(ct, lev, this.gen), ct);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isNullInode(TrieMap<K, V> ct) {
/* 363 */     return (GCAS_READ(ct) == null);
/*     */   }
/*     */   
/*     */   public int cachedSize(TrieMap<K, V> ct) {
/* 366 */     MainNode<K, V> m = GCAS_READ(ct);
/* 367 */     return m.cachedSize(ct);
/*     */   }
/*     */   
/*     */   public String string(int lev) {
/*     */     String str;
/* 371 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 371 */     (new Object[2])[0] = (new StringOps("  ")).$times(lev);
/* 371 */     MainNode<K, V> mainNode = this.mainnode;
/* 372 */     if (mainNode == null) {
/* 372 */       str = "<null>";
/* 373 */     } else if (mainNode instanceof TNode) {
/* 373 */       TNode tNode = (TNode)mainNode;
/* 373 */       Predef$ predef$ = Predef$.MODULE$;
/* 373 */       str = (new StringOps("TNode(%s, %s, %d, !)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { tNode.k(), tNode.v(), BoxesRunTime.boxToInteger(tNode.hc()) }));
/* 374 */     } else if (mainNode instanceof CNode) {
/* 374 */       CNode cNode = (CNode)mainNode;
/* 374 */       str = cNode.string(lev);
/* 375 */     } else if (mainNode instanceof LNode) {
/* 375 */       LNode lNode = (LNode)mainNode;
/* 375 */       str = lNode.string(lev);
/*     */     } else {
/* 376 */       Predef$ predef$ = Predef$.MODULE$;
/* 376 */       str = (new StringOps("<elem: %s>")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { mainNode }));
/*     */     } 
/*     */     return (new StringOps("%sINode -> %s")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { null, str }));
/*     */   }
/*     */   
/*     */   public static <K, V> INode<K, V> newRootNode() {
/*     */     return INode$.MODULE$.newRootNode();
/*     */   }
/*     */   
/*     */   public static Object KEY_ABSENT() {
/*     */     return INode$.MODULE$.KEY_ABSENT();
/*     */   }
/*     */   
/*     */   public static Object KEY_PRESENT() {
/*     */     return INode$.MODULE$.KEY_PRESENT();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\INode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */