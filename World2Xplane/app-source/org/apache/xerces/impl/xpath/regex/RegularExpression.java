package org.apache.xerces.impl.xpath.regex;

import java.io.Serializable;
import java.text.CharacterIterator;

public class RegularExpression implements Serializable {
  private static final long serialVersionUID = 6242499334195006401L;
  
  static final boolean DEBUG = false;
  
  String regex;
  
  int options;
  
  int nofparen;
  
  Token tokentree;
  
  boolean hasBackReferences = false;
  
  transient int minlength;
  
  transient Op operations = null;
  
  transient int numberOfClosures;
  
  transient Context context = null;
  
  transient RangeToken firstChar = null;
  
  transient String fixedString = null;
  
  transient int fixedStringOptions;
  
  transient BMPattern fixedStringTable = null;
  
  transient boolean fixedStringOnly = false;
  
  static final int IGNORE_CASE = 2;
  
  static final int SINGLE_LINE = 4;
  
  static final int MULTIPLE_LINES = 8;
  
  static final int EXTENDED_COMMENT = 16;
  
  static final int USE_UNICODE_CATEGORY = 32;
  
  static final int UNICODE_WORD_BOUNDARY = 64;
  
  static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
  
  static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
  
  static final int XMLSCHEMA_MODE = 512;
  
  static final int SPECIAL_COMMA = 1024;
  
  private static final int WT_IGNORE = 0;
  
  private static final int WT_LETTER = 1;
  
  private static final int WT_OTHER = 2;
  
  static final int LINE_FEED = 10;
  
  static final int CARRIAGE_RETURN = 13;
  
  static final int LINE_SEPARATOR = 8232;
  
  static final int PARAGRAPH_SEPARATOR = 8233;
  
  private synchronized void compile(Token paramToken) {
    if (this.operations != null)
      return; 
    this.numberOfClosures = 0;
    this.operations = compile(paramToken, null, false);
  }
  
  private Op compile(Token paramToken, Op paramOp, boolean paramBoolean) {
    Op.StringOp stringOp;
    Op.CharOp charOp;
    Op.UnionOp unionOp;
    byte b;
    Token token;
    int i;
    int j;
    Token.ConditionToken conditionToken;
    int k;
    Op op1;
    Op op2;
    Op op3;
    switch (paramToken.type) {
      case 11:
        null = Op.createDot();
        null.next = paramOp;
        return null;
      case 0:
        null = Op.createChar(paramToken.getChar());
        null.next = paramOp;
        return null;
      case 8:
        null = Op.createAnchor(paramToken.getChar());
        null.next = paramOp;
        return null;
      case 4:
      case 5:
        null = Op.createRange(paramToken);
        null.next = paramOp;
        return null;
      case 1:
        null = paramOp;
        if (!paramBoolean) {
          for (int m = paramToken.size() - 1; m >= 0; m--)
            null = compile(paramToken.getChild(m), null, false); 
        } else {
          for (byte b1 = 0; b1 < paramToken.size(); b1++)
            null = compile(paramToken.getChild(b1), null, true); 
        } 
        return null;
      case 2:
        unionOp = Op.createUnion(paramToken.size());
        for (b = 0; b < paramToken.size(); b++)
          unionOp.addElement(compile(paramToken.getChild(b), paramOp, paramBoolean)); 
        return unionOp;
      case 3:
      case 9:
        token = paramToken.getChild(0);
        i = paramToken.getMin();
        j = paramToken.getMax();
        if (i >= 0 && i == j) {
          null = paramOp;
          for (byte b1 = 0; b1 < i; b1++)
            null = compile(token, null, paramBoolean); 
        } else {
          if (i > 0 && j > 0)
            j -= i; 
          if (j > 0) {
            null = paramOp;
            for (byte b1 = 0; b1 < j; b1++) {
              Op.ChildOp childOp = Op.createQuestion((paramToken.type == 9));
              childOp.next = paramOp;
              childOp.setChild(compile(token, null, paramBoolean));
              null = childOp;
            } 
          } else {
            Op.ChildOp childOp;
            if (paramToken.type == 9) {
              childOp = Op.createNonGreedyClosure();
            } else if (token.getMinLength() == 0) {
              childOp = Op.createClosure(this.numberOfClosures++);
            } else {
              childOp = Op.createClosure(-1);
            } 
            childOp.next = paramOp;
            childOp.setChild(compile(token, childOp, paramBoolean));
            null = childOp;
          } 
          if (i > 0)
            for (byte b1 = 0; b1 < i; b1++)
              null = compile(token, null, paramBoolean);  
        } 
        return null;
      case 7:
        return paramOp;
      case 10:
        stringOp = Op.createString(paramToken.getString());
        stringOp.next = paramOp;
        return stringOp;
      case 12:
        charOp = Op.createBackReference(paramToken.getReferenceNumber());
        charOp.next = paramOp;
        return charOp;
      case 6:
        if (paramToken.getParenNumber() == 0) {
          Op op = compile(paramToken.getChild(0), paramOp, paramBoolean);
        } else if (paramBoolean) {
          paramOp = Op.createCapture(paramToken.getParenNumber(), paramOp);
          paramOp = compile(paramToken.getChild(0), paramOp, paramBoolean);
          charOp = Op.createCapture(-paramToken.getParenNumber(), paramOp);
        } else {
          paramOp = Op.createCapture(-paramToken.getParenNumber(), paramOp);
          paramOp = compile(paramToken.getChild(0), paramOp, paramBoolean);
          charOp = Op.createCapture(paramToken.getParenNumber(), paramOp);
        } 
        return charOp;
      case 20:
        return Op.createLook(20, paramOp, compile(paramToken.getChild(0), null, false));
      case 21:
        return Op.createLook(21, paramOp, compile(paramToken.getChild(0), null, false));
      case 22:
        return Op.createLook(22, paramOp, compile(paramToken.getChild(0), null, true));
      case 23:
        return Op.createLook(23, paramOp, compile(paramToken.getChild(0), null, true));
      case 24:
        return Op.createIndependent(paramOp, compile(paramToken.getChild(0), null, paramBoolean));
      case 25:
        return Op.createModifier(paramOp, compile(paramToken.getChild(0), null, paramBoolean), ((Token.ModifierToken)paramToken).getOptions(), ((Token.ModifierToken)paramToken).getOptionsMask());
      case 26:
        conditionToken = (Token.ConditionToken)paramToken;
        k = conditionToken.refNumber;
        op1 = (conditionToken.condition == null) ? null : compile(conditionToken.condition, null, paramBoolean);
        op2 = compile(conditionToken.yes, paramOp, paramBoolean);
        op3 = (conditionToken.no == null) ? null : compile(conditionToken.no, paramOp, paramBoolean);
        return Op.createCondition(paramOp, k, op1, op2, op3);
    } 
    throw new RuntimeException("Unknown token type: " + paramToken.type);
  }
  
  public boolean matches(char[] paramArrayOfchar) {
    return matches(paramArrayOfchar, 0, paramArrayOfchar.length, (Match)null);
  }
  
  public boolean matches(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    return matches(paramArrayOfchar, paramInt1, paramInt2, (Match)null);
  }
  
  public boolean matches(char[] paramArrayOfchar, Match paramMatch) {
    return matches(paramArrayOfchar, 0, paramArrayOfchar.length, paramMatch);
  }
  
  public boolean matches(char[] paramArrayOfchar, int paramInt1, int paramInt2, Match paramMatch) {
    int j;
    synchronized (this) {
      if (this.operations == null)
        prepare(); 
      if (this.context == null)
        this.context = new Context(); 
    } 
    Context context = null;
    synchronized (this.context) {
      context = this.context.inuse ? new Context() : this.context;
      context.reset(paramArrayOfchar, paramInt1, paramInt2, this.numberOfClosures);
    } 
    if (paramMatch != null) {
      paramMatch.setNumberOfGroups(this.nofparen);
      paramMatch.setSource(paramArrayOfchar);
    } else if (this.hasBackReferences) {
      paramMatch = new Match();
      paramMatch.setNumberOfGroups(this.nofparen);
    } 
    context.match = paramMatch;
    if (isSet(this.options, 512)) {
      int m = matchCharArray(context, this.operations, context.start, 1, this.options);
      if (m == context.limit) {
        if (context.match != null) {
          context.match.setBeginning(0, context.start);
          context.match.setEnd(0, m);
        } 
        context.inuse = false;
        return true;
      } 
      return false;
    } 
    if (this.fixedStringOnly) {
      int m = this.fixedStringTable.matches(paramArrayOfchar, context.start, context.limit);
      if (m >= 0) {
        if (context.match != null) {
          context.match.setBeginning(0, m);
          context.match.setEnd(0, m + this.fixedString.length());
        } 
        context.inuse = false;
        return true;
      } 
      context.inuse = false;
      return false;
    } 
    if (this.fixedString != null) {
      int m = this.fixedStringTable.matches(paramArrayOfchar, context.start, context.limit);
      if (m < 0) {
        context.inuse = false;
        return false;
      } 
    } 
    int i = context.limit - this.minlength;
    int k = -1;
    if (this.operations != null && this.operations.type == 7 && (this.operations.getChild()).type == 0) {
      if (isSet(this.options, 4)) {
        j = context.start;
        k = matchCharArray(context, this.operations, context.start, 1, this.options);
      } else {
        boolean bool = true;
        for (j = context.start; j <= i; j++) {
          char c = paramArrayOfchar[j];
          if (isEOLChar(c)) {
            bool = true;
          } else {
            if (bool && 0 <= (k = matchCharArray(context, this.operations, j, 1, this.options)))
              break; 
            bool = false;
          } 
        } 
      } 
    } else if (this.firstChar != null) {
      RangeToken rangeToken = this.firstChar;
      if (isSet(this.options, 2)) {
        rangeToken = this.firstChar.getCaseInsensitiveToken();
        for (j = context.start; j <= i; j++) {
          int m = paramArrayOfchar[j];
          if (REUtil.isHighSurrogate(m) && j + 1 < context.limit) {
            m = REUtil.composeFromSurrogates(m, paramArrayOfchar[j + 1]);
            if (!rangeToken.match(m))
              continue; 
          } else if (!rangeToken.match(m)) {
            char c = Character.toUpperCase((char)m);
            if (!rangeToken.match(c) && !rangeToken.match(Character.toLowerCase(c)))
              continue; 
          } 
          if (0 <= (k = matchCharArray(context, this.operations, j, 1, this.options)))
            break; 
          continue;
        } 
      } else {
        for (j = context.start; j <= i; j++) {
          int m = paramArrayOfchar[j];
          if (REUtil.isHighSurrogate(m) && j + 1 < context.limit)
            m = REUtil.composeFromSurrogates(m, paramArrayOfchar[j + 1]); 
          if (rangeToken.match(m) && 0 <= (k = matchCharArray(context, this.operations, j, 1, this.options)))
            break; 
        } 
      } 
    } else {
      for (j = context.start; j <= i && 0 > (k = matchCharArray(context, this.operations, j, 1, this.options)); j++);
    } 
    if (k >= 0) {
      if (context.match != null) {
        context.match.setBeginning(0, j);
        context.match.setEnd(0, k);
      } 
      context.inuse = false;
      return true;
    } 
    context.inuse = false;
    return false;
  }
  
  private int matchCharArray(Context paramContext, Op paramOp, int paramInt1, int paramInt2, int paramInt3) {
    char[] arrayOfChar = paramContext.charTarget;
    while (true) {
      boolean bool;
      int j;
      String str;
      int i;
      int k;
      int m;
      Op.ConditionOp conditionOp;
      int n;
      if (paramOp == null)
        return (isSet(paramInt3, 512) && paramInt1 != paramContext.limit) ? -1 : paramInt1; 
      if (paramInt1 > paramContext.limit || paramInt1 < paramContext.start)
        return -1; 
      switch (paramOp.type) {
        case 1:
          if (isSet(paramInt3, 2)) {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || !matchIgnoreCase(i1, arrayOfChar[paramInt1]))
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || !matchIgnoreCase(i1, arrayOfChar[i2]))
                return -1; 
              paramInt1 = i2;
            } 
          } else {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || i1 != arrayOfChar[paramInt1])
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || i1 != arrayOfChar[i2])
                return -1; 
              paramInt1 = i2;
            } 
          } 
          paramOp = paramOp.next;
          continue;
        case 0:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            char c = arrayOfChar[paramInt1];
            if (isSet(paramInt3, 4)) {
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                paramInt1++; 
            } else {
              int i1;
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                i1 = REUtil.composeFromSurrogates(c, arrayOfChar[++paramInt1]); 
              if (isEOLChar(i1))
                return -1; 
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            char c = arrayOfChar[i1];
            if (isSet(paramInt3, 4)) {
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i1--; 
            } else {
              int i2;
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i2 = REUtil.composeFromSurrogates(arrayOfChar[--i1], c); 
              if (!isEOLChar(i2))
                return -1; 
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 3:
        case 4:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            int i1 = arrayOfChar[paramInt1];
            if (REUtil.isHighSurrogate(i1) && paramInt1 + 1 < paramContext.limit)
              i1 = REUtil.composeFromSurrogates(i1, arrayOfChar[++paramInt1]); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i1)) {
                if (i1 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i1)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i1)) {
              return -1;
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            int i2 = arrayOfChar[i1];
            if (REUtil.isLowSurrogate(i2) && i1 - 1 >= 0)
              i2 = REUtil.composeFromSurrogates(arrayOfChar[--i1], i2); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i2)) {
                if (i2 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i2)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i2)) {
              return -1;
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 5:
          bool = false;
          switch (paramOp.getData()) {
            case 94:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(arrayOfChar[paramInt1 - 1])))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 64:
              if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(arrayOfChar[paramInt1 - 1])))
                return -1; 
              break;
            case 36:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.limit && (paramInt1 >= paramContext.limit || !isEOLChar(arrayOfChar[paramInt1])))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(arrayOfChar[paramInt1])) && (paramInt1 + 2 != paramContext.limit || arrayOfChar[paramInt1] != '\r' || arrayOfChar[paramInt1 + 1] != '\n'))
                return -1; 
              break;
            case 65:
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 90:
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(arrayOfChar[paramInt1])) && (paramInt1 + 2 != paramContext.limit || arrayOfChar[paramInt1] != '\r' || arrayOfChar[paramInt1 + 1] != '\n'))
                return -1; 
              break;
            case 122:
              if (paramInt1 != paramContext.limit)
                return -1; 
              break;
            case 98:
              if (paramContext.length == 0)
                return -1; 
              j = getWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == 0)
                return -1; 
              k = getPreviousWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == k)
                return -1; 
              break;
            case 66:
              if (paramContext.length == 0) {
                bool = true;
              } else {
                j = getWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3);
                bool = (j == 0 || j == getPreviousWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3)) ? true : false;
              } 
              if (!bool)
                return -1; 
              break;
            case 60:
              if (paramContext.length == 0 || paramInt1 == paramContext.limit)
                return -1; 
              if (getWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1 || getPreviousWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2)
                return -1; 
              break;
            case 62:
              if (paramContext.length == 0 || paramInt1 == paramContext.start)
                return -1; 
              if (getWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2 || getPreviousWordType(arrayOfChar, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1)
                return -1; 
              break;
          } 
          paramOp = paramOp.next;
          continue;
        case 16:
          j = paramOp.getData();
          if (j <= 0 || j >= this.nofparen)
            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
          if (paramContext.match.getBeginning(j) < 0 || paramContext.match.getEnd(j) < 0)
            return -1; 
          k = paramContext.match.getBeginning(j);
          m = paramContext.match.getEnd(j) - k;
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(arrayOfChar, paramInt1, paramContext.limit, k, m))
                return -1; 
              paramInt1 += m;
            } else {
              if (!regionMatches(arrayOfChar, paramInt1 - m, paramContext.limit, k, m))
                return -1; 
              paramInt1 -= m;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(arrayOfChar, paramInt1, paramContext.limit, k, m))
              return -1; 
            paramInt1 += m;
          } else {
            if (!regionMatchesIgnoreCase(arrayOfChar, paramInt1 - m, paramContext.limit, k, m))
              return -1; 
            paramInt1 -= m;
          } 
          paramOp = paramOp.next;
          continue;
        case 6:
          str = paramOp.getString();
          k = str.length();
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(arrayOfChar, paramInt1, paramContext.limit, str, k))
                return -1; 
              paramInt1 += k;
            } else {
              if (!regionMatches(arrayOfChar, paramInt1 - k, paramContext.limit, str, k))
                return -1; 
              paramInt1 -= k;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(arrayOfChar, paramInt1, paramContext.limit, str, k))
              return -1; 
            paramInt1 += k;
          } else {
            if (!regionMatchesIgnoreCase(arrayOfChar, paramInt1 - k, paramContext.limit, str, k))
              return -1; 
            paramInt1 -= k;
          } 
          paramOp = paramOp.next;
          continue;
        case 7:
          i = paramOp.getData();
          if (i >= 0) {
            k = paramContext.offsets[i];
            if (k < 0 || k != paramInt1) {
              paramContext.offsets[i] = paramInt1;
            } else {
              paramContext.offsets[i] = -1;
              paramOp = paramOp.next;
              continue;
            } 
          } 
          k = matchCharArray(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            paramContext.offsets[i] = -1; 
          if (k >= 0)
            return k; 
          paramOp = paramOp.next;
          continue;
        case 9:
          i = matchCharArray(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.next;
          continue;
        case 8:
        case 10:
          i = matchCharArray(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.getChild();
          continue;
        case 11:
          for (i = 0; i < paramOp.size(); i++) {
            k = matchCharArray(paramContext, paramOp.elementAt(i), paramInt1, paramInt2, paramInt3);
            if (k >= 0)
              return k; 
          } 
          return -1;
        case 15:
          k = paramOp.getData();
          if (paramContext.match != null && k > 0) {
            m = paramContext.match.getBeginning(k);
            paramContext.match.setBeginning(k, paramInt1);
            int i1 = matchCharArray(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i1 < 0)
              paramContext.match.setBeginning(k, m); 
            return i1;
          } 
          if (paramContext.match != null && k < 0) {
            m = -k;
            int i1 = paramContext.match.getEnd(m);
            paramContext.match.setEnd(m, paramInt1);
            int i2 = matchCharArray(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i2 < 0)
              paramContext.match.setEnd(m, i1); 
            return i2;
          } 
          paramOp = paramOp.next;
          continue;
        case 20:
          if (0 > matchCharArray(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 21:
          if (0 <= matchCharArray(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 22:
          if (0 > matchCharArray(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 23:
          if (0 <= matchCharArray(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 24:
          m = matchCharArray(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (m < 0)
            return m; 
          paramInt1 = m;
          paramOp = paramOp.next;
          continue;
        case 25:
          m = paramInt3;
          m |= paramOp.getData();
          m &= paramOp.getData2() ^ 0xFFFFFFFF;
          n = matchCharArray(paramContext, paramOp.getChild(), paramInt1, paramInt2, m);
          if (n < 0)
            return n; 
          paramInt1 = n;
          paramOp = paramOp.next;
          continue;
        case 26:
          conditionOp = (Op.ConditionOp)paramOp;
          n = 0;
          if (conditionOp.refNumber > 0) {
            if (conditionOp.refNumber >= this.nofparen)
              throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber); 
            n = (paramContext.match.getBeginning(conditionOp.refNumber) >= 0 && paramContext.match.getEnd(conditionOp.refNumber) >= 0) ? 1 : 0;
          } else {
            n = (0 <= matchCharArray(paramContext, conditionOp.condition, paramInt1, paramInt2, paramInt3)) ? 1 : 0;
          } 
          if (n != 0) {
            paramOp = conditionOp.yes;
            continue;
          } 
          if (conditionOp.no != null) {
            paramOp = conditionOp.no;
            continue;
          } 
          paramOp = conditionOp.next;
          continue;
      } 
      throw new RuntimeException("Unknown operation type: " + paramOp.type);
    } 
  }
  
  private static final int getPreviousWordType(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i;
    for (i = getWordType(paramArrayOfchar, paramInt1, paramInt2, --paramInt3, paramInt4); i == 0; i = getWordType(paramArrayOfchar, paramInt1, paramInt2, --paramInt3, paramInt4));
    return i;
  }
  
  private static final int getWordType(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt3 < paramInt1 || paramInt3 >= paramInt2) ? 2 : getWordType0(paramArrayOfchar[paramInt3], paramInt4);
  }
  
  private static final boolean regionMatches(char[] paramArrayOfchar, int paramInt1, int paramInt2, String paramString, int paramInt3) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt3)
      return false; 
    byte b = 0;
    while (paramInt3-- > 0) {
      if (paramArrayOfchar[paramInt1++] != paramString.charAt(b++))
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatches(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt4)
      return false; 
    int i = paramInt3;
    while (paramInt4-- > 0) {
      if (paramArrayOfchar[paramInt1++] != paramArrayOfchar[i++])
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatchesIgnoreCase(char[] paramArrayOfchar, int paramInt1, int paramInt2, String paramString, int paramInt3) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt3)
      return false; 
    byte b = 0;
    while (paramInt3-- > 0) {
      char c1 = paramArrayOfchar[paramInt1++];
      char c2 = paramString.charAt(b++);
      if (c1 == c2)
        continue; 
      char c3 = Character.toUpperCase(c1);
      char c4 = Character.toUpperCase(c2);
      if (c3 != c4 && Character.toLowerCase(c3) != Character.toLowerCase(c4))
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatchesIgnoreCase(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt4)
      return false; 
    int i = paramInt3;
    while (paramInt4-- > 0) {
      char c1 = paramArrayOfchar[paramInt1++];
      char c2 = paramArrayOfchar[i++];
      if (c1 == c2)
        continue; 
      char c3 = Character.toUpperCase(c1);
      char c4 = Character.toUpperCase(c2);
      if (c3 != c4 && Character.toLowerCase(c3) != Character.toLowerCase(c4))
        return false; 
    } 
    return true;
  }
  
  public boolean matches(String paramString) {
    return matches(paramString, 0, paramString.length(), (Match)null);
  }
  
  public boolean matches(String paramString, int paramInt1, int paramInt2) {
    return matches(paramString, paramInt1, paramInt2, (Match)null);
  }
  
  public boolean matches(String paramString, Match paramMatch) {
    return matches(paramString, 0, paramString.length(), paramMatch);
  }
  
  public boolean matches(String paramString, int paramInt1, int paramInt2, Match paramMatch) {
    int j;
    synchronized (this) {
      if (this.operations == null)
        prepare(); 
      if (this.context == null)
        this.context = new Context(); 
    } 
    Context context = null;
    synchronized (this.context) {
      context = this.context.inuse ? new Context() : this.context;
      context.reset(paramString, paramInt1, paramInt2, this.numberOfClosures);
    } 
    if (paramMatch != null) {
      paramMatch.setNumberOfGroups(this.nofparen);
      paramMatch.setSource(paramString);
    } else if (this.hasBackReferences) {
      paramMatch = new Match();
      paramMatch.setNumberOfGroups(this.nofparen);
    } 
    context.match = paramMatch;
    if (isSet(this.options, 512)) {
      int m = matchString(context, this.operations, context.start, 1, this.options);
      if (m == context.limit) {
        if (context.match != null) {
          context.match.setBeginning(0, context.start);
          context.match.setEnd(0, m);
        } 
        context.inuse = false;
        return true;
      } 
      return false;
    } 
    if (this.fixedStringOnly) {
      int m = this.fixedStringTable.matches(paramString, context.start, context.limit);
      if (m >= 0) {
        if (context.match != null) {
          context.match.setBeginning(0, m);
          context.match.setEnd(0, m + this.fixedString.length());
        } 
        context.inuse = false;
        return true;
      } 
      context.inuse = false;
      return false;
    } 
    if (this.fixedString != null) {
      int m = this.fixedStringTable.matches(paramString, context.start, context.limit);
      if (m < 0) {
        context.inuse = false;
        return false;
      } 
    } 
    int i = context.limit - this.minlength;
    int k = -1;
    if (this.operations != null && this.operations.type == 7 && (this.operations.getChild()).type == 0) {
      if (isSet(this.options, 4)) {
        j = context.start;
        k = matchString(context, this.operations, context.start, 1, this.options);
      } else {
        boolean bool = true;
        for (j = context.start; j <= i; j++) {
          char c = paramString.charAt(j);
          if (isEOLChar(c)) {
            bool = true;
          } else {
            if (bool && 0 <= (k = matchString(context, this.operations, j, 1, this.options)))
              break; 
            bool = false;
          } 
        } 
      } 
    } else if (this.firstChar != null) {
      RangeToken rangeToken = this.firstChar;
      if (isSet(this.options, 2)) {
        rangeToken = this.firstChar.getCaseInsensitiveToken();
        for (j = context.start; j <= i; j++) {
          int m = paramString.charAt(j);
          if (REUtil.isHighSurrogate(m) && j + 1 < context.limit) {
            m = REUtil.composeFromSurrogates(m, paramString.charAt(j + 1));
            if (!rangeToken.match(m))
              continue; 
          } else if (!rangeToken.match(m)) {
            char c = Character.toUpperCase((char)m);
            if (!rangeToken.match(c) && !rangeToken.match(Character.toLowerCase(c)))
              continue; 
          } 
          if (0 <= (k = matchString(context, this.operations, j, 1, this.options)))
            break; 
          continue;
        } 
      } else {
        for (j = context.start; j <= i; j++) {
          int m = paramString.charAt(j);
          if (REUtil.isHighSurrogate(m) && j + 1 < context.limit)
            m = REUtil.composeFromSurrogates(m, paramString.charAt(j + 1)); 
          if (rangeToken.match(m) && 0 <= (k = matchString(context, this.operations, j, 1, this.options)))
            break; 
        } 
      } 
    } else {
      for (j = context.start; j <= i && 0 > (k = matchString(context, this.operations, j, 1, this.options)); j++);
    } 
    if (k >= 0) {
      if (context.match != null) {
        context.match.setBeginning(0, j);
        context.match.setEnd(0, k);
      } 
      context.inuse = false;
      return true;
    } 
    context.inuse = false;
    return false;
  }
  
  private int matchString(Context paramContext, Op paramOp, int paramInt1, int paramInt2, int paramInt3) {
    String str = paramContext.strTarget;
    while (true) {
      boolean bool;
      int j;
      String str1;
      int i;
      int k;
      int m;
      Op.ConditionOp conditionOp;
      int n;
      if (paramOp == null)
        return (isSet(paramInt3, 512) && paramInt1 != paramContext.limit) ? -1 : paramInt1; 
      if (paramInt1 > paramContext.limit || paramInt1 < paramContext.start)
        return -1; 
      switch (paramOp.type) {
        case 1:
          if (isSet(paramInt3, 2)) {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || !matchIgnoreCase(i1, str.charAt(paramInt1)))
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || !matchIgnoreCase(i1, str.charAt(i2)))
                return -1; 
              paramInt1 = i2;
            } 
          } else {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || i1 != str.charAt(paramInt1))
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || i1 != str.charAt(i2))
                return -1; 
              paramInt1 = i2;
            } 
          } 
          paramOp = paramOp.next;
          continue;
        case 0:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            char c = str.charAt(paramInt1);
            if (isSet(paramInt3, 4)) {
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                paramInt1++; 
            } else {
              int i1;
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                i1 = REUtil.composeFromSurrogates(c, str.charAt(++paramInt1)); 
              if (isEOLChar(i1))
                return -1; 
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            char c = str.charAt(i1);
            if (isSet(paramInt3, 4)) {
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i1--; 
            } else {
              int i2;
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i2 = REUtil.composeFromSurrogates(str.charAt(--i1), c); 
              if (!isEOLChar(i2))
                return -1; 
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 3:
        case 4:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            int i1 = str.charAt(paramInt1);
            if (REUtil.isHighSurrogate(i1) && paramInt1 + 1 < paramContext.limit)
              i1 = REUtil.composeFromSurrogates(i1, str.charAt(++paramInt1)); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i1)) {
                if (i1 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i1)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i1)) {
              return -1;
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            int i2 = str.charAt(i1);
            if (REUtil.isLowSurrogate(i2) && i1 - 1 >= 0)
              i2 = REUtil.composeFromSurrogates(str.charAt(--i1), i2); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i2)) {
                if (i2 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i2)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i2)) {
              return -1;
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 5:
          bool = false;
          switch (paramOp.getData()) {
            case 94:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(str.charAt(paramInt1 - 1))))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 64:
              if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(str.charAt(paramInt1 - 1))))
                return -1; 
              break;
            case 36:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.limit && (paramInt1 >= paramContext.limit || !isEOLChar(str.charAt(paramInt1))))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(str.charAt(paramInt1))) && (paramInt1 + 2 != paramContext.limit || str.charAt(paramInt1) != '\r' || str.charAt(paramInt1 + 1) != '\n'))
                return -1; 
              break;
            case 65:
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 90:
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(str.charAt(paramInt1))) && (paramInt1 + 2 != paramContext.limit || str.charAt(paramInt1) != '\r' || str.charAt(paramInt1 + 1) != '\n'))
                return -1; 
              break;
            case 122:
              if (paramInt1 != paramContext.limit)
                return -1; 
              break;
            case 98:
              if (paramContext.length == 0)
                return -1; 
              j = getWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == 0)
                return -1; 
              k = getPreviousWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == k)
                return -1; 
              break;
            case 66:
              if (paramContext.length == 0) {
                bool = true;
              } else {
                j = getWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3);
                bool = (j == 0 || j == getPreviousWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3)) ? true : false;
              } 
              if (!bool)
                return -1; 
              break;
            case 60:
              if (paramContext.length == 0 || paramInt1 == paramContext.limit)
                return -1; 
              if (getWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1 || getPreviousWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2)
                return -1; 
              break;
            case 62:
              if (paramContext.length == 0 || paramInt1 == paramContext.start)
                return -1; 
              if (getWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2 || getPreviousWordType(str, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1)
                return -1; 
              break;
          } 
          paramOp = paramOp.next;
          continue;
        case 16:
          j = paramOp.getData();
          if (j <= 0 || j >= this.nofparen)
            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
          if (paramContext.match.getBeginning(j) < 0 || paramContext.match.getEnd(j) < 0)
            return -1; 
          k = paramContext.match.getBeginning(j);
          m = paramContext.match.getEnd(j) - k;
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(str, paramInt1, paramContext.limit, k, m))
                return -1; 
              paramInt1 += m;
            } else {
              if (!regionMatches(str, paramInt1 - m, paramContext.limit, k, m))
                return -1; 
              paramInt1 -= m;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(str, paramInt1, paramContext.limit, k, m))
              return -1; 
            paramInt1 += m;
          } else {
            if (!regionMatchesIgnoreCase(str, paramInt1 - m, paramContext.limit, k, m))
              return -1; 
            paramInt1 -= m;
          } 
          paramOp = paramOp.next;
          continue;
        case 6:
          str1 = paramOp.getString();
          k = str1.length();
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(str, paramInt1, paramContext.limit, str1, k))
                return -1; 
              paramInt1 += k;
            } else {
              if (!regionMatches(str, paramInt1 - k, paramContext.limit, str1, k))
                return -1; 
              paramInt1 -= k;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(str, paramInt1, paramContext.limit, str1, k))
              return -1; 
            paramInt1 += k;
          } else {
            if (!regionMatchesIgnoreCase(str, paramInt1 - k, paramContext.limit, str1, k))
              return -1; 
            paramInt1 -= k;
          } 
          paramOp = paramOp.next;
          continue;
        case 7:
          i = paramOp.getData();
          if (i >= 0) {
            k = paramContext.offsets[i];
            if (k < 0 || k != paramInt1) {
              paramContext.offsets[i] = paramInt1;
            } else {
              paramContext.offsets[i] = -1;
              paramOp = paramOp.next;
              continue;
            } 
          } 
          k = matchString(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            paramContext.offsets[i] = -1; 
          if (k >= 0)
            return k; 
          paramOp = paramOp.next;
          continue;
        case 9:
          i = matchString(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.next;
          continue;
        case 8:
        case 10:
          i = matchString(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.getChild();
          continue;
        case 11:
          for (i = 0; i < paramOp.size(); i++) {
            k = matchString(paramContext, paramOp.elementAt(i), paramInt1, paramInt2, paramInt3);
            if (k >= 0)
              return k; 
          } 
          return -1;
        case 15:
          k = paramOp.getData();
          if (paramContext.match != null && k > 0) {
            m = paramContext.match.getBeginning(k);
            paramContext.match.setBeginning(k, paramInt1);
            int i1 = matchString(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i1 < 0)
              paramContext.match.setBeginning(k, m); 
            return i1;
          } 
          if (paramContext.match != null && k < 0) {
            m = -k;
            int i1 = paramContext.match.getEnd(m);
            paramContext.match.setEnd(m, paramInt1);
            int i2 = matchString(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i2 < 0)
              paramContext.match.setEnd(m, i1); 
            return i2;
          } 
          paramOp = paramOp.next;
          continue;
        case 20:
          if (0 > matchString(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 21:
          if (0 <= matchString(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 22:
          if (0 > matchString(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 23:
          if (0 <= matchString(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 24:
          m = matchString(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (m < 0)
            return m; 
          paramInt1 = m;
          paramOp = paramOp.next;
          continue;
        case 25:
          m = paramInt3;
          m |= paramOp.getData();
          m &= paramOp.getData2() ^ 0xFFFFFFFF;
          n = matchString(paramContext, paramOp.getChild(), paramInt1, paramInt2, m);
          if (n < 0)
            return n; 
          paramInt1 = n;
          paramOp = paramOp.next;
          continue;
        case 26:
          conditionOp = (Op.ConditionOp)paramOp;
          n = 0;
          if (conditionOp.refNumber > 0) {
            if (conditionOp.refNumber >= this.nofparen)
              throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber); 
            n = (paramContext.match.getBeginning(conditionOp.refNumber) >= 0 && paramContext.match.getEnd(conditionOp.refNumber) >= 0) ? 1 : 0;
          } else {
            n = (0 <= matchString(paramContext, conditionOp.condition, paramInt1, paramInt2, paramInt3)) ? 1 : 0;
          } 
          if (n != 0) {
            paramOp = conditionOp.yes;
            continue;
          } 
          if (conditionOp.no != null) {
            paramOp = conditionOp.no;
            continue;
          } 
          paramOp = conditionOp.next;
          continue;
      } 
      throw new RuntimeException("Unknown operation type: " + paramOp.type);
    } 
  }
  
  private static final int getPreviousWordType(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i;
    for (i = getWordType(paramString, paramInt1, paramInt2, --paramInt3, paramInt4); i == 0; i = getWordType(paramString, paramInt1, paramInt2, --paramInt3, paramInt4));
    return i;
  }
  
  private static final int getWordType(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt3 < paramInt1 || paramInt3 >= paramInt2) ? 2 : getWordType0(paramString.charAt(paramInt3), paramInt4);
  }
  
  private static final boolean regionMatches(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) {
    return (paramInt2 - paramInt1 < paramInt3) ? false : paramString1.regionMatches(paramInt1, paramString2, 0, paramInt3);
  }
  
  private static final boolean regionMatches(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt2 - paramInt1 < paramInt4) ? false : paramString.regionMatches(paramInt1, paramString, paramInt3, paramInt4);
  }
  
  private static final boolean regionMatchesIgnoreCase(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) {
    return paramString1.regionMatches(true, paramInt1, paramString2, 0, paramInt3);
  }
  
  private static final boolean regionMatchesIgnoreCase(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt2 - paramInt1 < paramInt4) ? false : paramString.regionMatches(true, paramInt1, paramString, paramInt3, paramInt4);
  }
  
  public boolean matches(CharacterIterator paramCharacterIterator) {
    return matches(paramCharacterIterator, (Match)null);
  }
  
  public boolean matches(CharacterIterator paramCharacterIterator, Match paramMatch) {
    int m;
    int i = paramCharacterIterator.getBeginIndex();
    int j = paramCharacterIterator.getEndIndex();
    synchronized (this) {
      if (this.operations == null)
        prepare(); 
      if (this.context == null)
        this.context = new Context(); 
    } 
    Context context = null;
    synchronized (this.context) {
      context = this.context.inuse ? new Context() : this.context;
      context.reset(paramCharacterIterator, i, j, this.numberOfClosures);
    } 
    if (paramMatch != null) {
      paramMatch.setNumberOfGroups(this.nofparen);
      paramMatch.setSource(paramCharacterIterator);
    } else if (this.hasBackReferences) {
      paramMatch = new Match();
      paramMatch.setNumberOfGroups(this.nofparen);
    } 
    context.match = paramMatch;
    if (isSet(this.options, 512)) {
      int i1 = matchCharacterIterator(context, this.operations, context.start, 1, this.options);
      if (i1 == context.limit) {
        if (context.match != null) {
          context.match.setBeginning(0, context.start);
          context.match.setEnd(0, i1);
        } 
        context.inuse = false;
        return true;
      } 
      return false;
    } 
    if (this.fixedStringOnly) {
      int i1 = this.fixedStringTable.matches(paramCharacterIterator, context.start, context.limit);
      if (i1 >= 0) {
        if (context.match != null) {
          context.match.setBeginning(0, i1);
          context.match.setEnd(0, i1 + this.fixedString.length());
        } 
        context.inuse = false;
        return true;
      } 
      context.inuse = false;
      return false;
    } 
    if (this.fixedString != null) {
      int i1 = this.fixedStringTable.matches(paramCharacterIterator, context.start, context.limit);
      if (i1 < 0) {
        context.inuse = false;
        return false;
      } 
    } 
    int k = context.limit - this.minlength;
    int n = -1;
    if (this.operations != null && this.operations.type == 7 && (this.operations.getChild()).type == 0) {
      if (isSet(this.options, 4)) {
        m = context.start;
        n = matchCharacterIterator(context, this.operations, context.start, 1, this.options);
      } else {
        boolean bool = true;
        for (m = context.start; m <= k; m++) {
          char c = paramCharacterIterator.setIndex(m);
          if (isEOLChar(c)) {
            bool = true;
          } else {
            if (bool && 0 <= (n = matchCharacterIterator(context, this.operations, m, 1, this.options)))
              break; 
            bool = false;
          } 
        } 
      } 
    } else if (this.firstChar != null) {
      RangeToken rangeToken = this.firstChar;
      if (isSet(this.options, 2)) {
        rangeToken = this.firstChar.getCaseInsensitiveToken();
        for (m = context.start; m <= k; m++) {
          int i1 = paramCharacterIterator.setIndex(m);
          if (REUtil.isHighSurrogate(i1) && m + 1 < context.limit) {
            i1 = REUtil.composeFromSurrogates(i1, paramCharacterIterator.setIndex(m + 1));
            if (!rangeToken.match(i1))
              continue; 
          } else if (!rangeToken.match(i1)) {
            char c = Character.toUpperCase((char)i1);
            if (!rangeToken.match(c) && !rangeToken.match(Character.toLowerCase(c)))
              continue; 
          } 
          if (0 <= (n = matchCharacterIterator(context, this.operations, m, 1, this.options)))
            break; 
          continue;
        } 
      } else {
        for (m = context.start; m <= k; m++) {
          int i1 = paramCharacterIterator.setIndex(m);
          if (REUtil.isHighSurrogate(i1) && m + 1 < context.limit)
            i1 = REUtil.composeFromSurrogates(i1, paramCharacterIterator.setIndex(m + 1)); 
          if (rangeToken.match(i1) && 0 <= (n = matchCharacterIterator(context, this.operations, m, 1, this.options)))
            break; 
        } 
      } 
    } else {
      for (m = context.start; m <= k && 0 > (n = matchCharacterIterator(context, this.operations, m, 1, this.options)); m++);
    } 
    if (n >= 0) {
      if (context.match != null) {
        context.match.setBeginning(0, m);
        context.match.setEnd(0, n);
      } 
      context.inuse = false;
      return true;
    } 
    context.inuse = false;
    return false;
  }
  
  private int matchCharacterIterator(Context paramContext, Op paramOp, int paramInt1, int paramInt2, int paramInt3) {
    CharacterIterator characterIterator = paramContext.ciTarget;
    while (true) {
      boolean bool;
      int j;
      String str;
      int i;
      int k;
      int m;
      Op.ConditionOp conditionOp;
      int n;
      if (paramOp == null)
        return (isSet(paramInt3, 512) && paramInt1 != paramContext.limit) ? -1 : paramInt1; 
      if (paramInt1 > paramContext.limit || paramInt1 < paramContext.start)
        return -1; 
      switch (paramOp.type) {
        case 1:
          if (isSet(paramInt3, 2)) {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || !matchIgnoreCase(i1, characterIterator.setIndex(paramInt1)))
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || !matchIgnoreCase(i1, characterIterator.setIndex(i2)))
                return -1; 
              paramInt1 = i2;
            } 
          } else {
            int i1 = paramOp.getData();
            if (paramInt2 > 0) {
              if (paramInt1 >= paramContext.limit || i1 != characterIterator.setIndex(paramInt1))
                return -1; 
              paramInt1++;
            } else {
              int i2 = paramInt1 - 1;
              if (i2 >= paramContext.limit || i2 < 0 || i1 != characterIterator.setIndex(i2))
                return -1; 
              paramInt1 = i2;
            } 
          } 
          paramOp = paramOp.next;
          continue;
        case 0:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            char c = characterIterator.setIndex(paramInt1);
            if (isSet(paramInt3, 4)) {
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                paramInt1++; 
            } else {
              int i1;
              if (REUtil.isHighSurrogate(c) && paramInt1 + 1 < paramContext.limit)
                i1 = REUtil.composeFromSurrogates(c, characterIterator.setIndex(++paramInt1)); 
              if (isEOLChar(i1))
                return -1; 
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            char c = characterIterator.setIndex(i1);
            if (isSet(paramInt3, 4)) {
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i1--; 
            } else {
              int i2;
              if (REUtil.isLowSurrogate(c) && i1 - 1 >= 0)
                i2 = REUtil.composeFromSurrogates(characterIterator.setIndex(--i1), c); 
              if (!isEOLChar(i2))
                return -1; 
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 3:
        case 4:
          if (paramInt2 > 0) {
            if (paramInt1 >= paramContext.limit)
              return -1; 
            int i1 = characterIterator.setIndex(paramInt1);
            if (REUtil.isHighSurrogate(i1) && paramInt1 + 1 < paramContext.limit)
              i1 = REUtil.composeFromSurrogates(i1, characterIterator.setIndex(++paramInt1)); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i1)) {
                if (i1 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i1)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i1)) {
              return -1;
            } 
            paramInt1++;
          } else {
            int i1 = paramInt1 - 1;
            if (i1 >= paramContext.limit || i1 < 0)
              return -1; 
            int i2 = characterIterator.setIndex(i1);
            if (REUtil.isLowSurrogate(i2) && i1 - 1 >= 0)
              i2 = REUtil.composeFromSurrogates(characterIterator.setIndex(--i1), i2); 
            RangeToken rangeToken = paramOp.getToken();
            if (isSet(paramInt3, 2)) {
              rangeToken = rangeToken.getCaseInsensitiveToken();
              if (!rangeToken.match(i2)) {
                if (i2 >= 65536)
                  return -1; 
                char c;
                if (!rangeToken.match(c = Character.toUpperCase((char)i2)) && !rangeToken.match(Character.toLowerCase(c)))
                  return -1; 
              } 
            } else if (!rangeToken.match(i2)) {
              return -1;
            } 
            paramInt1 = i1;
          } 
          paramOp = paramOp.next;
          continue;
        case 5:
          bool = false;
          switch (paramOp.getData()) {
            case 94:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(characterIterator.setIndex(paramInt1 - 1))))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 64:
              if (paramInt1 != paramContext.start && (paramInt1 <= paramContext.start || !isEOLChar(characterIterator.setIndex(paramInt1 - 1))))
                return -1; 
              break;
            case 36:
              if (isSet(paramInt3, 8)) {
                if (paramInt1 != paramContext.limit && (paramInt1 >= paramContext.limit || !isEOLChar(characterIterator.setIndex(paramInt1))))
                  return -1; 
                break;
              } 
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(characterIterator.setIndex(paramInt1))) && (paramInt1 + 2 != paramContext.limit || characterIterator.setIndex(paramInt1) != '\r' || characterIterator.setIndex(paramInt1 + 1) != '\n'))
                return -1; 
              break;
            case 65:
              if (paramInt1 != paramContext.start)
                return -1; 
              break;
            case 90:
              if (paramInt1 != paramContext.limit && (paramInt1 + 1 != paramContext.limit || !isEOLChar(characterIterator.setIndex(paramInt1))) && (paramInt1 + 2 != paramContext.limit || characterIterator.setIndex(paramInt1) != '\r' || characterIterator.setIndex(paramInt1 + 1) != '\n'))
                return -1; 
              break;
            case 122:
              if (paramInt1 != paramContext.limit)
                return -1; 
              break;
            case 98:
              if (paramContext.length == 0)
                return -1; 
              j = getWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == 0)
                return -1; 
              k = getPreviousWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3);
              if (j == k)
                return -1; 
              break;
            case 66:
              if (paramContext.length == 0) {
                bool = true;
              } else {
                j = getWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3);
                bool = (j == 0 || j == getPreviousWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3)) ? true : false;
              } 
              if (!bool)
                return -1; 
              break;
            case 60:
              if (paramContext.length == 0 || paramInt1 == paramContext.limit)
                return -1; 
              if (getWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1 || getPreviousWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2)
                return -1; 
              break;
            case 62:
              if (paramContext.length == 0 || paramInt1 == paramContext.start)
                return -1; 
              if (getWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 2 || getPreviousWordType(characterIterator, paramContext.start, paramContext.limit, paramInt1, paramInt3) != 1)
                return -1; 
              break;
          } 
          paramOp = paramOp.next;
          continue;
        case 16:
          j = paramOp.getData();
          if (j <= 0 || j >= this.nofparen)
            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + j); 
          if (paramContext.match.getBeginning(j) < 0 || paramContext.match.getEnd(j) < 0)
            return -1; 
          k = paramContext.match.getBeginning(j);
          m = paramContext.match.getEnd(j) - k;
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(characterIterator, paramInt1, paramContext.limit, k, m))
                return -1; 
              paramInt1 += m;
            } else {
              if (!regionMatches(characterIterator, paramInt1 - m, paramContext.limit, k, m))
                return -1; 
              paramInt1 -= m;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(characterIterator, paramInt1, paramContext.limit, k, m))
              return -1; 
            paramInt1 += m;
          } else {
            if (!regionMatchesIgnoreCase(characterIterator, paramInt1 - m, paramContext.limit, k, m))
              return -1; 
            paramInt1 -= m;
          } 
          paramOp = paramOp.next;
          continue;
        case 6:
          str = paramOp.getString();
          k = str.length();
          if (!isSet(paramInt3, 2)) {
            if (paramInt2 > 0) {
              if (!regionMatches(characterIterator, paramInt1, paramContext.limit, str, k))
                return -1; 
              paramInt1 += k;
            } else {
              if (!regionMatches(characterIterator, paramInt1 - k, paramContext.limit, str, k))
                return -1; 
              paramInt1 -= k;
            } 
          } else if (paramInt2 > 0) {
            if (!regionMatchesIgnoreCase(characterIterator, paramInt1, paramContext.limit, str, k))
              return -1; 
            paramInt1 += k;
          } else {
            if (!regionMatchesIgnoreCase(characterIterator, paramInt1 - k, paramContext.limit, str, k))
              return -1; 
            paramInt1 -= k;
          } 
          paramOp = paramOp.next;
          continue;
        case 7:
          i = paramOp.getData();
          if (i >= 0) {
            k = paramContext.offsets[i];
            if (k < 0 || k != paramInt1) {
              paramContext.offsets[i] = paramInt1;
            } else {
              paramContext.offsets[i] = -1;
              paramOp = paramOp.next;
              continue;
            } 
          } 
          k = matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            paramContext.offsets[i] = -1; 
          if (k >= 0)
            return k; 
          paramOp = paramOp.next;
          continue;
        case 9:
          i = matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.next;
          continue;
        case 8:
        case 10:
          i = matchCharacterIterator(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
          if (i >= 0)
            return i; 
          paramOp = paramOp.getChild();
          continue;
        case 11:
          for (i = 0; i < paramOp.size(); i++) {
            k = matchCharacterIterator(paramContext, paramOp.elementAt(i), paramInt1, paramInt2, paramInt3);
            if (k >= 0)
              return k; 
          } 
          return -1;
        case 15:
          k = paramOp.getData();
          if (paramContext.match != null && k > 0) {
            m = paramContext.match.getBeginning(k);
            paramContext.match.setBeginning(k, paramInt1);
            int i1 = matchCharacterIterator(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i1 < 0)
              paramContext.match.setBeginning(k, m); 
            return i1;
          } 
          if (paramContext.match != null && k < 0) {
            m = -k;
            int i1 = paramContext.match.getEnd(m);
            paramContext.match.setEnd(m, paramInt1);
            int i2 = matchCharacterIterator(paramContext, paramOp.next, paramInt1, paramInt2, paramInt3);
            if (i2 < 0)
              paramContext.match.setEnd(m, i1); 
            return i2;
          } 
          paramOp = paramOp.next;
          continue;
        case 20:
          if (0 > matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 21:
          if (0 <= matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, 1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 22:
          if (0 > matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 23:
          if (0 <= matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, -1, paramInt3))
            return -1; 
          paramOp = paramOp.next;
          continue;
        case 24:
          m = matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, paramInt2, paramInt3);
          if (m < 0)
            return m; 
          paramInt1 = m;
          paramOp = paramOp.next;
          continue;
        case 25:
          m = paramInt3;
          m |= paramOp.getData();
          m &= paramOp.getData2() ^ 0xFFFFFFFF;
          n = matchCharacterIterator(paramContext, paramOp.getChild(), paramInt1, paramInt2, m);
          if (n < 0)
            return n; 
          paramInt1 = n;
          paramOp = paramOp.next;
          continue;
        case 26:
          conditionOp = (Op.ConditionOp)paramOp;
          n = 0;
          if (conditionOp.refNumber > 0) {
            if (conditionOp.refNumber >= this.nofparen)
              throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber); 
            n = (paramContext.match.getBeginning(conditionOp.refNumber) >= 0 && paramContext.match.getEnd(conditionOp.refNumber) >= 0) ? 1 : 0;
          } else {
            n = (0 <= matchCharacterIterator(paramContext, conditionOp.condition, paramInt1, paramInt2, paramInt3)) ? 1 : 0;
          } 
          if (n != 0) {
            paramOp = conditionOp.yes;
            continue;
          } 
          if (conditionOp.no != null) {
            paramOp = conditionOp.no;
            continue;
          } 
          paramOp = conditionOp.next;
          continue;
      } 
      throw new RuntimeException("Unknown operation type: " + paramOp.type);
    } 
  }
  
  private static final int getPreviousWordType(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i;
    for (i = getWordType(paramCharacterIterator, paramInt1, paramInt2, --paramInt3, paramInt4); i == 0; i = getWordType(paramCharacterIterator, paramInt1, paramInt2, --paramInt3, paramInt4));
    return i;
  }
  
  private static final int getWordType(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return (paramInt3 < paramInt1 || paramInt3 >= paramInt2) ? 2 : getWordType0(paramCharacterIterator.setIndex(paramInt3), paramInt4);
  }
  
  private static final boolean regionMatches(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, String paramString, int paramInt3) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt3)
      return false; 
    byte b = 0;
    while (paramInt3-- > 0) {
      if (paramCharacterIterator.setIndex(paramInt1++) != paramString.charAt(b++))
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatches(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt4)
      return false; 
    int i = paramInt3;
    while (paramInt4-- > 0) {
      if (paramCharacterIterator.setIndex(paramInt1++) != paramCharacterIterator.setIndex(i++))
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatchesIgnoreCase(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, String paramString, int paramInt3) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt3)
      return false; 
    byte b = 0;
    while (paramInt3-- > 0) {
      char c1 = paramCharacterIterator.setIndex(paramInt1++);
      char c2 = paramString.charAt(b++);
      if (c1 == c2)
        continue; 
      char c3 = Character.toUpperCase(c1);
      char c4 = Character.toUpperCase(c2);
      if (c3 != c4 && Character.toLowerCase(c3) != Character.toLowerCase(c4))
        return false; 
    } 
    return true;
  }
  
  private static final boolean regionMatchesIgnoreCase(CharacterIterator paramCharacterIterator, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (paramInt1 < 0)
      return false; 
    if (paramInt2 - paramInt1 < paramInt4)
      return false; 
    int i = paramInt3;
    while (paramInt4-- > 0) {
      char c1 = paramCharacterIterator.setIndex(paramInt1++);
      char c2 = paramCharacterIterator.setIndex(i++);
      if (c1 == c2)
        continue; 
      char c3 = Character.toUpperCase(c1);
      char c4 = Character.toUpperCase(c2);
      if (c3 != c4 && Character.toLowerCase(c3) != Character.toLowerCase(c4))
        return false; 
    } 
    return true;
  }
  
  void prepare() {
    compile(this.tokentree);
    this.minlength = this.tokentree.getMinLength();
    this.firstChar = null;
    if (!isSet(this.options, 128) && !isSet(this.options, 512)) {
      RangeToken rangeToken = Token.createRange();
      int i = this.tokentree.analyzeFirstCharacter(rangeToken, this.options);
      if (i == 1) {
        rangeToken.compactRanges();
        this.firstChar = rangeToken;
      } 
    } 
    if (this.operations != null && (this.operations.type == 6 || this.operations.type == 1) && this.operations.next == null) {
      this.fixedStringOnly = true;
      if (this.operations.type == 6) {
        this.fixedString = this.operations.getString();
      } else if (this.operations.getData() >= 65536) {
        this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
      } else {
        char[] arrayOfChar = new char[1];
        arrayOfChar[0] = (char)this.operations.getData();
        this.fixedString = new String(arrayOfChar);
      } 
      this.fixedStringOptions = this.options;
      this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
    } else if (!isSet(this.options, 256) && !isSet(this.options, 512)) {
      Token.FixedStringContainer fixedStringContainer = new Token.FixedStringContainer();
      this.tokentree.findFixedString(fixedStringContainer, this.options);
      this.fixedString = (fixedStringContainer.token == null) ? null : fixedStringContainer.token.getString();
      this.fixedStringOptions = fixedStringContainer.options;
      if (this.fixedString != null && this.fixedString.length() < 2)
        this.fixedString = null; 
      if (this.fixedString != null)
        this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2)); 
    } 
  }
  
  private static final boolean isSet(int paramInt1, int paramInt2) {
    return ((paramInt1 & paramInt2) == paramInt2);
  }
  
  public RegularExpression(String paramString) throws ParseException {
    setPattern(paramString, (String)null);
  }
  
  public RegularExpression(String paramString1, String paramString2) throws ParseException {
    setPattern(paramString1, paramString2);
  }
  
  RegularExpression(String paramString, Token paramToken, int paramInt1, boolean paramBoolean, int paramInt2) {
    this.regex = paramString;
    this.tokentree = paramToken;
    this.nofparen = paramInt1;
    this.options = paramInt2;
    this.hasBackReferences = paramBoolean;
  }
  
  public void setPattern(String paramString) throws ParseException {
    setPattern(paramString, this.options);
  }
  
  private void setPattern(String paramString, int paramInt) throws ParseException {
    this.regex = paramString;
    this.options = paramInt;
    RegexParser regexParser = isSet(this.options, 512) ? new ParserForXMLSchema() : new RegexParser();
    this.tokentree = regexParser.parse(this.regex, this.options);
    this.nofparen = regexParser.parennumber;
    this.hasBackReferences = regexParser.hasBackReferences;
    this.operations = null;
    this.context = null;
  }
  
  public void setPattern(String paramString1, String paramString2) throws ParseException {
    setPattern(paramString1, REUtil.parseOptions(paramString2));
  }
  
  public String getPattern() {
    return this.regex;
  }
  
  public String toString() {
    return this.tokentree.toString(this.options);
  }
  
  public String getOptions() {
    return REUtil.createOptionString(this.options);
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (!(paramObject instanceof RegularExpression))
      return false; 
    RegularExpression regularExpression = (RegularExpression)paramObject;
    return (this.regex.equals(regularExpression.regex) && this.options == regularExpression.options);
  }
  
  boolean equals(String paramString, int paramInt) {
    return (this.regex.equals(paramString) && this.options == paramInt);
  }
  
  public int hashCode() {
    return (this.regex + "/" + getOptions()).hashCode();
  }
  
  public int getNumberOfGroups() {
    return this.nofparen;
  }
  
  private static final int getWordType0(char paramChar, int paramInt) {
    if (!isSet(paramInt, 64))
      return isSet(paramInt, 32) ? (Token.getRange("IsWord", true).match(paramChar) ? 1 : 2) : (isWordChar(paramChar) ? 1 : 2); 
    switch (Character.getType(paramChar)) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 8:
      case 9:
      case 10:
      case 11:
        return 1;
      case 6:
      case 7:
      case 16:
        return 0;
      case 15:
        switch (paramChar) {
          case '\t':
          case '\n':
          case '\013':
          case '\f':
          case '\r':
            return 2;
        } 
        return 0;
    } 
    return 2;
  }
  
  private static final boolean isEOLChar(int paramInt) {
    return (paramInt == 10 || paramInt == 13 || paramInt == 8232 || paramInt == 8233);
  }
  
  private static final boolean isWordChar(int paramInt) {
    return (paramInt == 95) ? true : ((paramInt < 48) ? false : ((paramInt > 122) ? false : ((paramInt <= 57) ? true : ((paramInt < 65) ? false : ((paramInt <= 90) ? true : (!(paramInt < 97)))))));
  }
  
  private static final boolean matchIgnoreCase(int paramInt1, int paramInt2) {
    if (paramInt1 == paramInt2)
      return true; 
    if (paramInt1 > 65535 || paramInt2 > 65535)
      return false; 
    char c1 = Character.toUpperCase((char)paramInt1);
    char c2 = Character.toUpperCase((char)paramInt2);
    return (c1 == c2) ? true : ((Character.toLowerCase(c1) == Character.toLowerCase(c2)));
  }
  
  static final class Context {
    CharacterIterator ciTarget;
    
    String strTarget;
    
    char[] charTarget;
    
    int start;
    
    int limit;
    
    int length;
    
    Match match;
    
    boolean inuse = false;
    
    int[] offsets;
    
    private void resetCommon(int param1Int) {
      this.length = this.limit - this.start;
      this.inuse = true;
      this.match = null;
      if (this.offsets == null || this.offsets.length != param1Int)
        this.offsets = new int[param1Int]; 
      for (byte b = 0; b < param1Int; b++)
        this.offsets[b] = -1; 
    }
    
    void reset(CharacterIterator param1CharacterIterator, int param1Int1, int param1Int2, int param1Int3) {
      this.ciTarget = param1CharacterIterator;
      this.start = param1Int1;
      this.limit = param1Int2;
      resetCommon(param1Int3);
    }
    
    void reset(String param1String, int param1Int1, int param1Int2, int param1Int3) {
      this.strTarget = param1String;
      this.start = param1Int1;
      this.limit = param1Int2;
      resetCommon(param1Int3);
    }
    
    void reset(char[] param1ArrayOfchar, int param1Int1, int param1Int2, int param1Int3) {
      this.charTarget = param1ArrayOfchar;
      this.start = param1Int1;
      this.limit = param1Int2;
      resetCommon(param1Int3);
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xpath\regex\RegularExpression.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */