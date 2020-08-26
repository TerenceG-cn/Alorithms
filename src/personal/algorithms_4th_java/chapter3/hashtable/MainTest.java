package personal.algorithms_4th_java.chapter3.hashtable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static List<String> readTxtFile(String filePath) {
        List<String> result = new ArrayList<>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //�ж��ļ��Ƿ����
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//���ǵ������ʽ
                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result.add(lineTxt);
                }
                read.close();
                return result;
            } else {
                System.out.println("�Ҳ���ָ�����ļ�");
                return result;
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        //"C:\Users\10352\IdeaProjects\Alorithms\src\\personal\\algorithms_4th_java\\chapter3\\hashtable\\hash-attack.txt" �ļ�·��
        List<String> hashAtkStr = readTxtFile("src\\personal\\algorithms_4th_java\\chapter3\\hashtable\\hash-attack.txt");
        for (String str :
                hashAtkStr) {
            if (st.get(str) == null)
                st.put(str, 1);
            else
                st.put(str, st.get(str) + 1);
        }
        Iterable<String> keys=st.keys();
        for (String str:
             keys) {
            System.out.println(str);
        }
        //����ǵ�������
    }
}
