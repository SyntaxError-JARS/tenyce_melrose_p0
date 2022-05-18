module com.example.p0retokerapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.reavature.retoker.p0retokerapi to javafx.fxml;

}