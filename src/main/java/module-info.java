module hust.vietnamesehistory.controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires jsoup;
    requires org.json;


    opens hust.vietnamesehistory.controller to javafx.fxml;
    exports hust.vietnamesehistory.controller;
    exports hust.vietnamesehistory.controller.people;
    exports hust.vietnamesehistory.controller.peroid;
    opens hust.vietnamesehistory.controller.people to javafx.fxml;
    opens hust.vietnamesehistory.crawler.model to javafx.base;
    exports hust.vietnamesehistory.crawler.model to com.fasterxml.jackson.databind;
}