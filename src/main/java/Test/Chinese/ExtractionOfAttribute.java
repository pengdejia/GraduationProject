package Test.Chinese;

/**
 * user:lenovo
 * email:2901949379@qq.com
 * date:2019/3/18
 * project name:NLP
 * package name:Test.Chinese
 **/
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class ExtractionOfAttribute {
    //记录时间
    long startTime;
    long endTime;
    //选出的量词表
    String[]measurementArray = {"米","纳米","微米","毫米","厘米","分米","千米","公里","英里","光年",
            "摄氏度","℃","K","开尔文","k","°",
            "平方千米","平方米","公顷","立方米","m3","m2",
            "毫克","克","千克","吨","公斤","斤","kg","g",
            "秒","分钟","时",
            "美元","人民币","元",
    };
    //根据动词提取
    String[]verbArray = {"增加","上升","减少","下降","升高","降至","升至","低","高","增强"};

    //提取出的属性
    ArrayList<String>attributes = new ArrayList<String>();
    ArrayList<String>AttributesOfSentence = new ArrayList<String>();
    public static void main(String[] args) {
        ExtractionOfAttribute example = new ExtractionOfAttribute();
        try {
            example.runChineseAnnotators();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void runChineseAnnotators()throws FileNotFoundException {

        //导入模块
        startTime=System.currentTimeMillis();
        StanfordCoreNLP coreNLP = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
        endTime =System.currentTimeMillis();
        System.out.println("导入模块共计 "+(endTime-startTime)/1000+"s");
        /**
         * 三种输入方式文本、单文本、文件夹
         */
        //文本输入
        String Testtext =
                "它厚薄不一，大陆部分比较厚，大洋部分比较薄，平均厚度为17千米。" +
                        "地幔介于莫霍界面和古登堡界面之间，厚度为2800多千米。" +
                        "地核以古登堡界面与地幔分界，厚度为3400多千米。"
                +"1光年即光在一年中传播的距离，约为94605亿千米。"
                +"太阳直射点回归运动的周期为365日5时48分46秒，叫做回归年。"
                +"全球海平面将上升18～59厘米。"
                +"这个陆地面积仅为26平方千米的弹丸之国";
        Annotation annotation = new Annotation(Testtext);
        coreNLP.annotate(annotation);
        showDependencyTree(annotation);
        //测试单个文本文件

        /*String file = "F:/data/input/T1.txt";
        Annotation annotation = new Annotation(IOUtils.slurpFileNoExceptions(file));
        coreNLP.annotate(annotation);

        try {
            TestofDependencyTree(annotation);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        */

        /*FileOperation fileOperation = new FileOperation();
        String path = "F:/data/input/clinga";
        fileOperation.getFile(path);
        ArrayList<String> filenames = fileOperation.getFilenames();
        System.out.println(filenames.size());
        int startnumoftext = 0;
        int endnumoftext = filenames.size();
        for(int i = startnumoftext;i < endnumoftext;++i){
            System.out.println("当前处理的" + i + "个文件");
            String file = filenames.get(i);
            Annotation annotation = new Annotation(IOUtils.slurpFileNoExceptions(file));
            endTime = System.currentTimeMillis();
            System.out.println("进入corenlp处理" + (endTime-startTime)/1000 +"s");
            try {
                coreNLP.annotate(annotation);
            }catch (Exception e){
                System.out.println("无法解析当前文本");
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println("开始分析" + (endTime-startTime)/1000 +"s");
            TestofDependencyTree(annotation);
            //parseOutput(annotation);
        }*/

        /*System.out.println("从" + (endnumoftext - startnumoftext) + "个文本中获取了" + attributes.size() + "个属性");

        for(int i = 0;i < attributes.size();++i){
            System.out.println(attributes.get(i));
            System.out.println(AttributesOfSentence.get(i));
        }
        for(int i = 0;i < attributes.size();++i){
            System.out.println(attributes.get(i));
        }*/
    }
    public void showDependencyTree(Annotation annotation){
        List<CoreMap>sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        System.out.println("开始进入每句的处理阶段");
        String words = "";
        for(CoreMap sentence:sentences){
            words = "";
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // 获取句子的token（可以是作为分词后的词语）
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                //System.out.println(word);
                //词性标注
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                System.out.println(word + "\t" + pos);
                words += word;
                // 命名实体识别
                //String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                //System.out.println(word + "\t" + pos + "\t" + ne);
            }
            System.out.println(words);
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            System.out.println(dependencies.toString(SemanticGraph.OutputFormat.LIST));
             /*
            System.out.println("句法树");
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println(tree.toString());
            */
            /*System.out.println("指代关系");
            Map<Integer, CorefChain> corefChains = annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
            if (corefChains == null) {
                return;
            }
            for (Map.Entry<Integer, CorefChain> entry : corefChains.entrySet()) {
                System.out.println("Chain " + entry.getKey() + " ");
                for (CorefChain.CorefMention m : entry.getValue().getMentionsInTextualOrder()) {
                    // We need to subtract one since the indices count from 1 but the Lists start from 0
                    List<CoreLabel> tokens = sentences.get(m.sentNum - 1).get(CoreAnnotations.TokensAnnotation.class);
                    // We subtract two for end: one for 0-based indexing, and one because we want last token of mention not one following.
                    System.out.println("  " + m + ", i.e., 0-based character offsets [" + tokens.get(m.startIndex - 1).beginPosition() +
                            ", " + tokens.get(m.endIndex - 2).endPosition() + ")");
                }

            }*/
        }
    }
    public void TestofDependencyTree(Annotation annotation)throws FileNotFoundException{
        List<CoreMap>sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        System.out.println("开始进入每句的处理阶段");
        String words = "";
        String filename = "F:/data/output/T1.txt";
        File file = new File(filename);
        PrintStream ps = new PrintStream(new FileOutputStream(file,true));
        for(CoreMap sentence:sentences){
            words = "";
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // 获取句子的token（可以是作为分词后的词语）
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                //词性标注
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                words += word;
            }
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            String[] dArray = dependencies.toString(SemanticGraph.OutputFormat.LIST).split("\n");
            String measurement = "";
            int countofMeasure = 0;
            boolean flag = false;
            for(int i = 0;i < dArray.length;++i){
                if(dArray[i].contains("mark:clf")){
                    flag = true;
                    measurement = dArray[i].split(" ")[1].split("-")[0];
                    for(int j = 0;j < measurementArray.length;++j){
                        if(measurement.contains(measurementArray[j])){
                            countofMeasure++;
                            break;
                        }
                    }
                }
            }
            if(countofMeasure > 0){
                ps.println(words);
            }
            else {
                if(flag == false){
                }
                else{
                    System.out.println(words);
                }
            }
        }

    }
    /**
     * 此部分为匹配主谓结构的属性例如气温上升：主要是基于对每个句子生成的依存树判断其中谓语动词为变化性动词
     */
    public void parseOutput(Annotation annotation){
        List<CoreMap>sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        ArrayList<String>posArray = new ArrayList<String>();
        System.out.println("开始进入每句的处理阶段");
        int mycountofSentence = 0;
        for(CoreMap sentence:sentences) {
            posArray.clear();
            mycountofSentence++;
            if(mycountofSentence%100 == 0){
                System.out.println("当前已经处理了" + mycountofSentence + "个句子");
            }
            String words = "";
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                posArray.add(word + ","+pos);
                words += word;
            }
            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
            String[] dArray = dependencies.toString(SemanticGraph.OutputFormat.LIST).split("\n");
            boolean flag = false;
            for (int i = 0;i < dArray.length;++i){
                flag = false;
                if(dArray[i].contains("nsubj")){
                    for(int j = 0;j < verbArray.length;++j){
                        if(dArray[i].split(",")[0].contains(verbArray[j])){
                            String text = dArray[i] ;
                            String s = text.split(",")[1];
                            s = s.split("-")[1];
                            s = s.substring(0,s.length() - 1);
                            int count  = Integer.parseInt(s) - 1;
                            if(posArray.get(count).contains("NN")){
                                flag = true;
                                System.out.println(dArray[i] + posArray.get(count));
                                break;
                            }
                        }
                    }
                }
                if(flag == true){
                    System.out.println("原句是");
                    System.out.println(words);
                    System.out.println("包含目标语句的结构是");
                    System.out.println(dArray[i]);
                    //System.out.println("完整的结构是");
                    //System.out.println(dependencies.toString(SemanticGraph.OutputFormat.LIST));

                    String attribute = dArray[i].split(",")[1].split("-")[0];
                    attribute = attribute.substring(1,attribute.length());
                    attributes.add(attribute);
                    AttributesOfSentence.add(words);
                    System.out.println("该属性是" + attribute);
                }
            }
        }
    }
}
