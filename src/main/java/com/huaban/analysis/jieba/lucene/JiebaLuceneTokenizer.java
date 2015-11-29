package com.huaban.analysis.jieba.lucene;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;

/**
 * author chen
 */
public class JiebaLuceneTokenizer extends Tokenizer {

    public Token next(final Token reusableToken) throws IOException {
    return null;
    }
    protected final boolean isTokenChar(char c) {
        return Character.isLetterOrDigit(c) || c == '@' || c == '#' || c == '+' || c == '&' || c == ';' || c == '\'' || c == '＆' || c =='＠' || c== '\u2019' || c == '\u2018' || c== '\u00b4' || c == '\u0060';
    }
}
