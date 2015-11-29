package com.huaban.analysis.jieba;

import com.huaban.analysis.jieba.lucene.JiebaLuceneAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.junit.Test;

import java.io.StringReader;

/**
 * author chen
 */
public class TestJiebaLuceneAnalyzer {

    @Test
    public void test() throws Exception {
        final String trickyString = "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作";
        final JiebaLuceneAnalyzer analyzer = new JiebaLuceneAnalyzer();
        final Analyzer chineseAnalyzer = new CJKAnalyzer();
        print(analyzer, trickyString);
        System.out.println();
        print(chineseAnalyzer, trickyString);
    }

    private void print(final Analyzer chineseAnalyzer, final String trickyString) throws Exception {
        final TokenStream ts = chineseAnalyzer.tokenStream("", new StringReader(trickyString));

        final Token reusableToken = new Token();
        Token tok = ts.next(reusableToken);
        while (tok != null) {
            System.out.println(tok.term());
            tok = ts.next(reusableToken);
        }
    }
}
