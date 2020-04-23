package q005;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {

    public static void main(String[] args){
        //WorkData取得
        List<WorkData> workDataList = getWorkDataList();

        //各形式に変換
        Map<String, Integer> positionMap = getPositionWorkTime(workDataList);
        Map<String, Integer> pCodeMap = getP_CodeWorkTime(workDataList);
        Map<String, Integer> numberMap = getNumberWorkTime(workDataList);

        //表示
        System.out.println("☆★☆★☆役職毎の作業時間★☆★☆★");
        showMap(positionMap);
        System.out.println("☆★☆★☆Pコード毎の作業時間★☆★☆★");
        showMap(pCodeMap);
        System.out.println("☆★☆★☆社員番号毎の作業時間★☆★☆★");
        showMap(numberMap);
    }

    //データファイルを読み込み、WorkDataのListを生成
    public static List<WorkData> getWorkDataList(){
        List<WorkData> workDataList = new ArrayList<WorkData>();
        try {
            InputStream stream = Q005.class.getResourceAsStream("data.txt");
            Reader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            //最初の一行目は破棄
            bufferedReader.readLine();

            //一行ごとに処理
            while (true){
                String readLine = bufferedReader.readLine();
                if (null == readLine){
                    break;
                }

                String[] splitData = readLine.split(",");
                workDataList.add(new WorkData(splitData[0], splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4])));
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println("ファイルが読み込めませんでした。 詳細:" + e.getMessage());
        }

        return workDataList;
    }

    //各部署ごとの合計作業時間を取得
    private static Map<String, Integer> getDepartmentWorkTime(List<WorkData> list){
        Map<String, Integer> workDataMap = new HashMap<String, Integer>();
        for (WorkData data : list) {
            //同じ部署は二度見ない
            if(workDataMap.containsKey(data.getDepartment())){
                continue;
            }

            int workTimeSum = 0;
            for (WorkData workData : list) {
                //違う部署の人なら確認不要
                if (data.getDepartment() != workData.getDepartment()){
                    continue;
                }

                workTimeSum = workData.getWorkTime();
            }

            workDataMap.put(data.getDepartment(), workTimeSum);
        }

        return workDataMap;
    }

    //各役職ごとの合計作業時間を取得
    private static Map<String, Integer> getPositionWorkTime(List<WorkData> list){
        Map<String, Integer> workDataMap = new HashMap<String, Integer>();
        for (WorkData data : list) {
            //同じ役職は二度見ない
            if(workDataMap.containsKey(data.getPosition())){
                continue;
            }

            int workTimeSum = 0;
            for (WorkData workData : list) {
                //違う役職の人なら確認不要
                if (data.getPosition() != workData.getPosition()){
                    continue;
                }

                workTimeSum = workData.getWorkTime();
            }

            workDataMap.put(data.getPosition(), workTimeSum);
        }

        return workDataMap;
    }

    //Pコードごとの合計作業時間を取得
    private static Map<String, Integer> getP_CodeWorkTime(List<WorkData> list){
        Map<String, Integer> workDataMap = new HashMap<String, Integer>();
        for (WorkData data : list) {
            //同じPコードは二度見ない
            if(workDataMap.containsKey(data.getP_Code())){
                continue;
            }

            int workTimeSum = 0;
            for (WorkData workData : list) {
                //違うPコードの人なら確認不要
                if (data.getP_Code() != workData.getP_Code()){
                    continue;
                }

                workTimeSum = workData.getWorkTime();
            }

            workDataMap.put(data.getP_Code(), workTimeSum);
        }

        return workDataMap;
    }

    //各社員番号ごとの合計作業時間を取得
    private static Map<String, Integer> getNumberWorkTime(List<WorkData> list){
        Map<String, Integer> workDataMap = new HashMap<String, Integer>();
        for (WorkData data : list) {
            //同じ社員番号は二度見ない
            if(workDataMap.containsKey(data.getNumber())){
                continue;
            }

            int workTimeSum = 0;
            for (WorkData workData : list) {
                //違う社員番号の人なら確認不要
                if (data.getNumber() != workData.getNumber()){
                    continue;
                }

                workTimeSum = workData.getWorkTime();
            }

            workDataMap.put(data.getNumber(), workTimeSum);
        }

        return workDataMap;
    }

    //基準ごとの作業時間がまとめられているMapをConsoleに出力
    private static void showMap(Map<String, Integer> workMap){
        for(String key: workMap.keySet()){
            //作業時間を**時間**分形式に変換
            int hour   = workMap.get(key) / 60;
            int minute = workMap.get(key) % 60;

            //桁ぞろえ
            String hourString = hour < 10 ? "00" + String.valueOf(hour) : (hour < 100 ? "0" + String.valueOf(hour) : String.valueOf(hour));
            String minuteString = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);

            String workTimeString = String.format("%s時間%s分", hourString , minuteString);

            System.out.println(String.format("%s: %s", key, workTimeString));
        }
    }
}
// 完成までの時間: 00時間 49分