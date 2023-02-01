package hust.vietnamesehistory.model;

import java.util.List;

public class Period extends Model {
    private List<King> kings;

    public Period(String href, String name, List<King> kings) {
        super(href, name);
        this.kings = kings;
    }

    public List<King> getKings() {
        return kings;
    }

    public  void setKings(List<King> kings) {this.kings = kings; }


}
