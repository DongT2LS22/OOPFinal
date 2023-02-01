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
    exports hust.vietnamesehistory.controller.place;
    exports hust.vietnamesehistory.controller.festival;
    opens hust.vietnamesehistory.controller.place to javafx.fxml;
    opens hust.vietnamesehistory.controller.people to javafx.fxml;
    opens hust.vietnamesehistory.model to javafx.base;
    opens hust.vietnamesehistory.controller.festival to javafx.fxml;
    opens hust.vietnamesehistory.controller.event to javafx.fxml;
    exports hust.vietnamesehistory.model to com.fasterxml.jackson.databind;
}