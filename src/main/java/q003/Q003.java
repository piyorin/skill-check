package q003;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {

    public static void main(String[] args){
        //ファイルを読み込む
        String fileText = getFileText();

        //ピリオドとカンマを除去
        String replacedFileText = "";
        replacedFileText = fileText.replace(",", "");
        replacedFileText = replacedFileText.replace(".", "");

        //スペースでSplit
        String[] splitFileText = replacedFileText.split(" ");

        //小文字化
        for (int i = 0; i < splitFileText.length; i++) {
            splitFileText[i] = splitFileText[i].toLowerCase();
        }

        //ソート
        Arrays.sort(splitFileText);

        //iをIに直す
        for(int i = 0; i < splitFileText.length; i++){
            if (splitFileText[i].equals("i")){
                splitFileText[i] = "I";
            }
        }

        List<String> countedWordList = new ArrayList<String>();
        for (int i = 0; i < splitFileText.length; i++){
            int count = 0;

            //数えているなら不要
            if (countedWordList.contains(splitFileText[i])){
               continue;
            }
            countedWordList.add(splitFileText[i]);

            //出現回数取得
            for(int j = 0; j < splitFileText.length; j++){
                if(splitFileText[i].equals(splitFileText[j])){
                    count++;
                }
            }

            //表示
            System.out.println(String.format("%s=%d", splitFileText[i], count));
        }
    }

    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    /**
     * openDataFileメソッドを使用し、ファイルを読み込み、String型に変換
     */
    private static String getFileText(){
        String fileText = "";
        try {
            InputStream stream = openDataFile();
            Reader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (true){
                String readLine = bufferedReader.readLine();
                if (null == readLine){
                    break;
                }

                if(fileText.equals("") == false){
                    fileText += ' ';
                }
                fileText += readLine;
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println("ファイルが読み込めませんでした。 詳細:" + e.getMessage());
        }

        return fileText;
    }
}
// 完成までの時間: 01時間 34分