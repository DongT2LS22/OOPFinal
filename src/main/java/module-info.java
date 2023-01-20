module hust.vietnamesehistory.controller.vietnamesehistory {
    requires javafx.controls;
    requires javafx.fxml;


    opens hust.vietnamesehistory.controller.vietnamesehistory to javafx.fxml;
    exports hust.vietnamesehistory.controller.vietnamesehistory;
}