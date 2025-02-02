module eif.viko.lt.predictionappclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens eif.viko.lt.predictionappclient to javafx.fxml;
    exports eif.viko.lt.predictionappclient;
}