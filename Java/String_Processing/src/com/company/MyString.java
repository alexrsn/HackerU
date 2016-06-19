package com.company;

/**
 * Created on 29/03/2016.
 */
class MyString {
    private char[] str;

    public MyString(char[] str) {
        if (str == null)
            throw new NullPointerException();
        this.str = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            this.str[i] = str[i];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof MyString) {
            MyString otherString = (MyString) obj;
            if (this.str == null)
                return otherString.str == null;
            if (otherString.str == null)
                return false;
            if (this.str.length != otherString.str.length)
                return false;
            for (int i = 0; i < str.length; i++) {
                if (this.str[i] != otherString.str[i])
                    return false;
            }
            return true;
        }
        return false;
    }

    public char charAt(int index) {
        if (index < 0 || index >= str.length)
            throw new IndexOutOfBoundsException();
        return str[index];
    }

    public MyString concat(MyString otherString) {
        if (otherString.str.length == 0)
            return this;
        char[] temp = new char[this.str.length + otherString.str.length];
        for (int i = 0; i < this.str.length; i++) {
            temp[i] = this.str[i];
        }
        for (int i = this.str.length; i < (this.str.length + otherString.str.length); i++) {
            temp[i] = otherString.str[i - this.str.length];
        }
        return new MyString(temp);
    }

    public boolean contains(MyString otherString) {
        return indexOf(otherString) != -1;
    }

    public boolean endsWith(MyString otherString) {
        if (otherString.str.length > this.str.length)
            return false;
        for (int i = 0; i < otherString.str.length; i++) {
            if (otherString.str[otherString.str.length - i - 1] != this.str[str.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(MyString otherString) {
        if (otherString != null || this.str != null || otherString.str != null) {
            if (otherString.str.length == 0)
                return 0;
            final char first = otherString.str[0];//if variable not change better do it final(more efficient)
            final int upTo = str.length - otherString.str.length + 1;
            for (int i = 0; i < upTo; i++) {
                if (first == this.str[i]) {
                    boolean flag = true;
                    for (int j = 1; j < otherString.str.length; j++) {
                        if (this.str[i + j] != otherString.str[j]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        return i;
                }
            }
        }
        return -1;
    }

    public MyString[] split(MyString otherString) {
        int counter = 0;
        MyString tempStr = new MyString(str);
        if (otherString.str.length == 0) {
            MyString[] temp = new MyString[this.str.length];
            for (int i = 0; i < this.str.length; i++) {
                temp[i] = tempStr.substring(i, i);
            }
            return temp;
        }
        while (tempStr.indexOf(otherString) != -1) {
            counter++;
            tempStr = tempStr.substring(tempStr.indexOf(otherString) + otherString.str.length, tempStr.str.length - 1);
        }
        MyString[] temp = new MyString[counter + 1];
        tempStr = new MyString(str);
        for (int i = 0; i < counter; i++) {
            temp[i] = tempStr.substring(0, tempStr.indexOf(otherString) - 1);
            tempStr = tempStr.substring(tempStr.indexOf(otherString) + otherString.str.length, tempStr.str.length - 1);
        }
        if (!tempStr.str.equals(otherString))
            temp[counter] = tempStr;
        return temp;
    }

    public MyString[] split(char delimiter){
        int[] delimitersPosition = new int[this.str.length];
        int delimiterCounter = 0;
        for (int i = 0; i < str.length; i++) {
            if(str[i] == delimiter)
                delimitersPosition[delimiterCounter++] = i;
        }
        MyString[] result = new MyString[delimiterCounter+1];
        if(delimiterCounter == 0){
            result[0] = this;
            return result;
        }
        char[] firstWord = new char[delimitersPosition[0]];
        for (int i = 0; i < delimitersPosition[0]; i++) {
            firstWord[i] = str[i];
        }
        result[0] = new MyString(firstWord);
        int position = delimitersPosition[0] + 1;
        for (int i = 1; i < delimiterCounter; i++) {
            char[] word = new char[delimitersPosition[i]-delimitersPosition[i-1]-1];
            for (int j = 0; j < word.length; j++) {
                word[j] = str[position++];
            }
            position++;
            result[i] = new MyString(word);
        }
        if(delimiterCounter>0) {
            char[] lastWord = new char[str.length-position];
            for (int i = 0; i < lastWord.length; i++) {
                lastWord[i] = str[position++];
            }
            result[delimiterCounter] = new MyString(lastWord);
        }
        return result;
    }

    public MyString substring(int begin, int end) {
        if (begin < 0 || end >= str.length)
            throw new IndexOutOfBoundsException();
        char[] temp = new char[end - begin + 1];
        for (int i = 0; i < end - begin + 1; i++) {
            temp[i] = this.str[i + begin];
        }
        return new MyString(temp);
    }

    public MyString toUpper() {

        char[] temp = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            if (str[i] > 'a' && str[i] < 'z')
                temp[i] = (char) (((int) this.str[i]) - 32);
            else
                temp[i] = this.str[i];
        }
        return new MyString(temp);
    }

    @Override
    public String toString() {
        String tempStr = "";
        for (int i = 0; i < str.length; i++) {
            tempStr += str[i];
        }
        return tempStr;
    }
}