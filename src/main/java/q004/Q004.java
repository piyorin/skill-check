package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args){
        ListManager manager = new ListManager();

        //範囲を決定
        int head = 0;
        int tail = manager.size() - 1;

        //クイックソート
        sort(manager, head, tail);

        manager.checkResult();
    }

    /**
     * headとtailで指定された範囲内をクイックソート
     * @param head 範囲開始
     * @param tail 範囲終了
     */
    public static void sort(ListManager manager,int head,int tail) {
        if (head == tail){
            return;
        }

        int partitionIndex = partition(manager, head, tail);
        sort(manager, head, partitionIndex - 1);
        sort(manager, partitionIndex, tail);
    }

    /**
     * headとtailで指定された範囲内をクイックソート(軸はhead)
     * @param head 範囲開始
     * @param tail 範囲終了
     * @return 分割点のうち、大きいと判定された部分の最初の配列番号
     */
    private static int partition(ListManager manager, int head, int tail){
        int checkHead = head;
        int checkTail = tail;

        while(true){
            // 先頭のデータよりも大きい数字を先頭から検索し、その数字の配列番号を取得
            while(checkHead <= tail && -1 == manager.compare(checkHead, head)){
                checkHead++;
            }

            // 先頭のデータよりも小さい数字を末尾から検索し、その数字の配列番号を取得
            while(checkTail >= head && 1 == manager.compare(checkTail, head)){
                checkTail--;
            }

            // 検索が交差したら終了
            if(checkHead > checkTail){
                break;
            }

            manager.exchange(checkHead, checkTail);
            checkHead++;
            checkTail--;
        }

        return checkHead;
    }
}
// 完成までの時間: 01時間 01分