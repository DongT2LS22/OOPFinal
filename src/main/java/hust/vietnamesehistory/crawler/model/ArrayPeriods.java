package hust.vietnamesehistory.crawler.model;

import java.util.List;

public class ArrayPeriods {
    List<Period> periods;

    public ArrayPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
