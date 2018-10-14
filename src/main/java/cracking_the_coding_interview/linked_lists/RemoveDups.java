package cracking_the_coding_interview.linked_lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RemoveDups {
	public List removeDuplicate(List linkedList) {
		Map occurrenceMap = new HashMap();
		if (linkedList.size() < 2) {
			return linkedList;
		}
		for (Object element : linkedList) {

		}
		Iterator iterator = linkedList.iterator();
		int iteratorIndex = 0;
		do {
			Object element = iterator.next();
			if (occurrenceMap.containsKey(element)) {
				iterator.remove();
			}
			else {
				occurrenceMap.put(element, true);
			}
		} while (iterator.hasNext());
		return linkedList;
	}

	public List removeDuplicateNoBuffer(List linkedList) {
		if (linkedList.size() < 2) {
			return linkedList;
		}
		Iterator iterator = linkedList.iterator();
		int iteratorIndex = 0;
		do {
			Object element = iterator.next();
			Iterator duplicateSearchIterator = linkedList.iterator();
			boolean removeElement = false;
			for (int i = 0; i < iteratorIndex; i++) {
				if (duplicateSearchIterator.next().equals(element)) {
					removeElement = true;
					iterator.remove();
					break;
				}
			}
			if (!removeElement) {
				iteratorIndex++;
			}
		} while (iterator.hasNext());
		return linkedList;
	}
}
