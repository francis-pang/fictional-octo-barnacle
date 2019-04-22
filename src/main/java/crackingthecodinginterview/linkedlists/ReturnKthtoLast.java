package crackingthecodinginterview.linkedlists;

import java.util.Iterator;
import java.util.List;

public class ReturnKthtoLast {
    public Integer getKToLastElement (List<Integer> list, int k) {
        int listSize = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            listSize++;
        }
        iterator = list.iterator();
        for (int i = 0; i < (listSize - k - 1); i++) {
            iterator.next();
        }

        return iterator.next();
    }
}
