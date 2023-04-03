package akka.dispatch;

import akka.actor.ActorRef;
import akka.dispatch.sysmsg.SystemMessage;

public abstract class DefaultSystemMessageQueue$class {
  public static void $init$(Mailbox $this) {}
  
  public static final void systemEnqueue(Mailbox $this, ActorRef receiver, SystemMessage message) {
    // Byte code:
    //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
    //   3: aload_2
    //   4: invokeinterface unlinked : ()Z
    //   9: invokevirtual assert : (Z)V
    //   12: aload_0
    //   13: invokevirtual systemQueueGet : ()Lakka/dispatch/sysmsg/SystemMessage;
    //   16: astore #4
    //   18: aload #4
    //   20: getstatic akka/dispatch/sysmsg/NoMessage$.MODULE$ : Lakka/dispatch/sysmsg/NoMessage$;
    //   23: astore #5
    //   25: dup
    //   26: ifnonnull -> 38
    //   29: pop
    //   30: aload #5
    //   32: ifnull -> 46
    //   35: goto -> 83
    //   38: aload #5
    //   40: invokevirtual equals : (Ljava/lang/Object;)Z
    //   43: ifeq -> 83
    //   46: aload_0
    //   47: invokevirtual actor : ()Lakka/actor/ActorCell;
    //   50: ifnull -> 77
    //   53: aload_0
    //   54: invokevirtual actor : ()Lakka/actor/ActorCell;
    //   57: invokevirtual dispatcher : ()Lakka/dispatch/MessageDispatcher;
    //   60: invokevirtual mailboxes : ()Lakka/dispatch/Mailboxes;
    //   63: invokevirtual deadLetterMailbox : ()Lakka/dispatch/Mailbox;
    //   66: aload_1
    //   67: aload_2
    //   68: invokevirtual systemEnqueue : (Lakka/actor/ActorRef;Lakka/dispatch/sysmsg/SystemMessage;)V
    //   71: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
    //   74: goto -> 108
    //   77: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
    //   80: goto -> 108
    //   83: aload_0
    //   84: aload #4
    //   86: aload_2
    //   87: astore #6
    //   89: getstatic akka/dispatch/sysmsg/LatestFirstSystemMessageList$.MODULE$ : Lakka/dispatch/sysmsg/LatestFirstSystemMessageList$;
    //   92: aload #4
    //   94: aload #6
    //   96: invokevirtual $colon$colon$extension : (Lakka/dispatch/sysmsg/SystemMessage;Lakka/dispatch/sysmsg/SystemMessage;)Lakka/dispatch/sysmsg/SystemMessage;
    //   99: invokevirtual systemQueuePut : (Lakka/dispatch/sysmsg/SystemMessage;Lakka/dispatch/sysmsg/SystemMessage;)Z
    //   102: ifeq -> 110
    //   105: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
    //   108: pop
    //   109: return
    //   110: aload_2
    //   111: invokeinterface unlink : ()V
    //   116: aload_0
    //   117: aload_1
    //   118: aload_2
    //   119: astore_2
    //   120: astore_1
    //   121: astore_0
    //   122: goto -> 0
    // Line number table:
    //   Java source line number -> byte code offset
    //   #394	-> 0
    //   #396	-> 12
    //   #397	-> 18
    //   #398	-> 46
    //   #400	-> 83
    //   #397	-> 108
    //   #401	-> 110
    //   #402	-> 116
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	125	0	$this	Lakka/dispatch/Mailbox;
    //   0	125	1	receiver	Lakka/actor/ActorRef;
    //   0	125	2	message	Lakka/dispatch/sysmsg/SystemMessage;
    //   18	91	4	currentList	Lakka/dispatch/sysmsg/SystemMessage;
  }
  
  public static final SystemMessage systemDrain(Mailbox $this, SystemMessage newContents) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual systemQueueGet : ()Lakka/dispatch/sysmsg/SystemMessage;
    //   4: astore_3
    //   5: aload_3
    //   6: getstatic akka/dispatch/sysmsg/NoMessage$.MODULE$ : Lakka/dispatch/sysmsg/NoMessage$;
    //   9: astore #4
    //   11: dup
    //   12: ifnonnull -> 24
    //   15: pop
    //   16: aload #4
    //   18: ifnull -> 32
    //   21: goto -> 38
    //   24: aload #4
    //   26: invokevirtual equals : (Ljava/lang/Object;)Z
    //   29: ifeq -> 38
    //   32: aconst_null
    //   33: pop
    //   34: aconst_null
    //   35: goto -> 54
    //   38: aload_0
    //   39: aload_3
    //   40: aload_1
    //   41: invokevirtual systemQueuePut : (Lakka/dispatch/sysmsg/SystemMessage;Lakka/dispatch/sysmsg/SystemMessage;)Z
    //   44: ifeq -> 55
    //   47: getstatic akka/dispatch/sysmsg/LatestFirstSystemMessageList$.MODULE$ : Lakka/dispatch/sysmsg/LatestFirstSystemMessageList$;
    //   50: aload_3
    //   51: invokevirtual reverse$extension : (Lakka/dispatch/sysmsg/SystemMessage;)Lakka/dispatch/sysmsg/SystemMessage;
    //   54: areturn
    //   55: aload_0
    //   56: aload_1
    //   57: astore_1
    //   58: astore_0
    //   59: goto -> 0
    // Line number table:
    //   Java source line number -> byte code offset
    //   #409	-> 0
    //   #410	-> 5
    //   #411	-> 38
    //   #408	-> 54
    //   #412	-> 55
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	62	0	$this	Lakka/dispatch/Mailbox;
    //   0	62	1	newContents	Lakka/dispatch/sysmsg/SystemMessage;
    //   5	57	3	currentList	Lakka/dispatch/sysmsg/SystemMessage;
  }
  
  public static boolean hasSystemMessages(Mailbox $this) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual systemQueueGet : ()Lakka/dispatch/sysmsg/SystemMessage;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull -> 14
    //   9: iconst_1
    //   10: istore_2
    //   11: goto -> 45
    //   14: getstatic akka/dispatch/sysmsg/NoMessage$.MODULE$ : Lakka/dispatch/sysmsg/NoMessage$;
    //   17: aload_1
    //   18: astore_3
    //   19: dup
    //   20: ifnonnull -> 31
    //   23: pop
    //   24: aload_3
    //   25: ifnull -> 38
    //   28: goto -> 43
    //   31: aload_3
    //   32: invokevirtual equals : (Ljava/lang/Object;)Z
    //   35: ifeq -> 43
    //   38: iconst_1
    //   39: istore_2
    //   40: goto -> 45
    //   43: iconst_0
    //   44: istore_2
    //   45: iload_2
    //   46: ifeq -> 55
    //   49: iconst_0
    //   50: istore #4
    //   52: goto -> 58
    //   55: iconst_1
    //   56: istore #4
    //   58: iload #4
    //   60: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #415	-> 0
    //   #416	-> 5
    //   #417	-> 55
    //   #415	-> 58
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	61	0	$this	Lakka/dispatch/Mailbox;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DefaultSystemMessageQueue$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */