package Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * user:lenovo
 * email:2901949379@qq.com
 * date:2019/2/26
 * project name:NLP
 * package name:Test
 **/
public class NLPTest {
    public static void main(String[] args) {
        String text = "马飚向伦古转达了习近平主席的诚挚祝贺和良好祝愿。马飚表示，中国和赞比亚建交50多年来，双方始终真诚友好、平等相待，友好合作结出累累硕果，给两国人民带来了实实在在的利益。中方高度重视中赞关系发展，愿以落实两国元首共识和中非合作论坛约翰内斯堡峰会成果为契机，推动中赞关系再上新台阶。";
        Annotation document = new Annotation(text);
        StanfordCoreNLP corenlp = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
        corenlp.annotate(document);

        corenlp.prettyPrint(document, System.out);
    }
}
