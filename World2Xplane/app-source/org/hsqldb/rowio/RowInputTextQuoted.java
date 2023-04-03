package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.error.Error;

public class RowInputTextQuoted extends RowInputText {
  private static final int NORMAL_FIELD = 0;
  
  private static final int NEED_END_QUOTE = 1;
  
  private static final int FOUND_QUOTE = 2;
  
  private char[] qtext;
  
  public RowInputTextQuoted(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    super(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  public void setSource(String paramString, long paramLong, int paramInt) {
    super.setSource(paramString, paramLong, paramInt);
    this.qtext = paramString.toCharArray();
  }
  
  protected String getField(String paramString, int paramInt, boolean paramBoolean) throws IOException {
    String str = null;
    if (this.next >= this.qtext.length || this.qtext[this.next] != '"')
      return super.getField(paramString, paramInt, paramBoolean); 
    try {
      this.field++;
      StringBuffer stringBuffer = new StringBuffer();
      boolean bool = false;
      byte b = 0;
      int i = -1;
      if (!paramBoolean)
        i = this.text.indexOf(paramString, this.next); 
      while (this.next < this.qtext.length) {
        switch (b) {
          default:
            if (this.next == i) {
              this.next += paramInt;
              bool = true;
              break;
            } 
            if (this.qtext[this.next] == '"') {
              b = 1;
              break;
            } 
            stringBuffer.append(this.qtext[this.next]);
            break;
          case true:
            if (this.qtext[this.next] == '"') {
              b = 2;
              break;
            } 
            stringBuffer.append(this.qtext[this.next]);
            break;
          case true:
            if (this.qtext[this.next] == '"') {
              stringBuffer.append(this.qtext[this.next]);
              b = 1;
              break;
            } 
            this.next += paramInt - 1;
            b = 0;
            if (!paramBoolean) {
              this.next++;
              bool = true;
            } 
            break;
        } 
        if (bool)
          break; 
        this.next++;
      } 
      str = stringBuffer.toString();
    } catch (Exception exception) {
      Object[] arrayOfObject = { new Integer(this.field), exception.toString() };
      throw new IOException(Error.getMessage(41, 0, arrayOfObject));
    } 
    return str;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowInputTextQuoted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */