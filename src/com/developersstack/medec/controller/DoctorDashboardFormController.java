package com.developersstack.medec.controller;

import com.developersstack.medec.db.Database;
import com.developersstack.medec.dto.DoctorDto;
import com.developersstack.medec.utill.Cookie;
import com.sun.org.apache.bcel.internal.generic.DADD;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class DoctorDashboardFormController {
    public AnchorPane DoctorDashboardContext;
    public Label lbldate;
    public Label lbltime;

    public void initialize() throws IOException {
        //checkUser();
        initializeData();
    }

    private void initializeData() throws IOException {
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String today = simpleDateFormat.format(date);
//        lbldate.setText(today);
        lbldate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                e ->{
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
                    String time = LocalTime.now().format(dtf);
                    lbltime.setText(time);
                }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        //----Initialize Doctor details too (tobe implemented)
        checkDoctor();
    }

    private void checkDoctor() throws IOException {
        Optional<DoctorDto> selectedDoctor =
                Database.doctorTable.stream()
                        .filter(e -> e.getEmail().equals("dinuth@gmail.com"))
                        .findFirst();
        if(!selectedDoctor.isPresent()){
            // open a new window
            setUI("DoctorRegistrationController");
        }

    }
        public void checkUser() throws IOException {
        if(null == Cookie.selectedUser){
            setUI("DoctorDashboard");

        }
    }
    private void setUI(String pathName) throws IOException {
        Stage stage = (Stage)DoctorDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+pathName+".fxml"))));
        stage.centerOnScreen();
    }
}
