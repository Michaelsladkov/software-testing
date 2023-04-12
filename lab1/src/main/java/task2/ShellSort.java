package task2;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ShellSort <T extends Comparable<T>> {
    @Getter
    private List<T[]> sortingLog;

    public void sort(T[] toSort) {
        if (toSort == null)
            return;
        if (toSort.length < 2)
            return;
        sortingLog = new ArrayList<>();
        int gap = toSort.length / 2;
        while (gap > 0) {
            for (int i = gap; i < toSort.length; ++i) {
                T tmp = toSort[i];
                int j;
                for (j = i; j >= gap && toSort[j - gap].compareTo(tmp) > 0; j -= gap) {
                    toSort[j] = toSort[j - gap];
                }
                toSort[j] = tmp;
                sortingLog.add(toSort.clone());
            }
            gap = gap / 2;
        }
    }
}
