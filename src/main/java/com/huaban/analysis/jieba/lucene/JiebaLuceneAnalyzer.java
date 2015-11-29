package com.huaban.analysis.jieba.lucene;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

/**
 * author chen
 */
public class JiebaLuceneAnalyzer extends Analyzer {
    private final JiebaSegmenter segmenter = new JiebaSegmenter();

    @Override
    @Nullable
    public TokenStream tokenStream(String fieldName, Reader reader) {
        try {
            final String str = new BufferedReader(reader).readLine();
            final List<SegToken> segTokens = segmenter.process(str, JiebaSegmenter.SegMode.SEARCH);
            final Iterator<SegToken> tokenIterator = segTokens.iterator();
            return new TokenStream() {
                @Override
                @Nullable
                public Token next(Token reusableToken) throws IOException {
                    if (tokenIterator.hasNext()) {
                        final SegToken segToken = tokenIterator.next();
                        return reusableToken.reinit(segToken.word, segToken.startOffset, segToken.endOffset);
                    }
                    return null;
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
