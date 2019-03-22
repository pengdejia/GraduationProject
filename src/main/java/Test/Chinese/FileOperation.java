package Test.Chinese;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * user:lenovo
 * email:2901949379@qq.com
 * date:2019/3/1
 * project name:NLP
 * package name:Test.Chinese
 **/
public class FileOperation {
    private ArrayList<String>filenames = new ArrayList<String>();
    public ArrayList<String> getFilenames() {
        return filenames;
    }
    public  void getFile(String path){
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for(int i=0;i<array.length;i++)
        {
            if(array[i].isFile())//如果是文件
            {
                String filename =  path + "\\" + array[i].getName();
                //System.out.println(filename);
                filenames.add(filename);
                // 输出当前文件的完整路径
                // System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                // System.out.println(array[i].getPath());
            }
            else if(array[i].isDirectory())//如果是文件夹
            {
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath());
            }
        }
    }
    public static void main(String[] args) {
        //ArrayList<String>arrayList = FileOperation.readFileByLines("F:/data/newbook.txt");
        FileOperation fileOperation = new FileOperation();
        String path = "F:/data/Textbooks";
        fileOperation.getFile(path);
        ArrayList<String> filenames = fileOperation.getFilenames();
        System.out.println(filenames.size());
        for(int i = 0;i < filenames.size();++i){
            System.out.println(filenames.get(i));
            FileOperation.readFileByLines(filenames.get(i));
        }
    }
    public static void writeFileByLines(ArrayList<String>list,String filename){
        try{
            File file = new File(filename);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("错误的行数");
            for(int i = 0;i < list.size();++i){
                ps.println(list.get(i));
            }
        }catch (FileNotFoundException e){
            //写入文件错误
            System.out.println("写入文件错误");
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        ArrayList<String>mylist =  new ArrayList<String>();
        ArrayList<String>list = new ArrayList<String>();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            //记录无效的句子
            int CountOfSentence = 0;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println(tempString);
                if(filterout(tempString)) {
                    mylist.add(tempString);
                }
                else{
                    CountOfSentence++;
                    list.add(tempString);
                    //System.out.println(tempString);
                }
            }
            writeFileByLines(list,"F:/data/temp/1.txt");
            System.out.println("已过滤无效的句子数量");
            System.out.println(CountOfSentence);
            reader.close();
            System.out.println("有效的句子数量");
            System.out.println(mylist.size());
            return mylist;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e1) {
                }
            }
        }
        return mylist;
    }

    /**
     * 对输入文本进行初步过滤，减少误差
     * @param temp
     * @return
     */
    public static boolean filterout(String temp){
        if(temp != null && temp.length() > 3){
            return true;
        }
        else {
            return false;
        }
    }
}
