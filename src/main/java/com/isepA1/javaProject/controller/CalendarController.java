package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Tache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        Map<Integer, List<Tache>> tachesMap = getCalendarTachesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);
                        List<Tache> taches = tachesMap.get(currentDate);
                        if(taches != null){
                            createCalendarTaches(taches, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarTaches(List<Tache> taches, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox tachesBox = new VBox();
        for (int k = 0; k < taches.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                tachesBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    System.out.println(taches);
                });
                break;
            }
            Text text = new Text(taches.get(k).getDateLimite().toString());
            tachesBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                System.out.println(text.getText());
            });
        }
        tachesBox.setTranslateY((rectangleHeight / 2) * 0.20);
        tachesBox.setMaxWidth(rectangleWidth * 0.8);
        tachesBox.setMaxHeight(rectangleHeight * 0.65);
        tachesBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(tachesBox);
    }

    private Map<Integer, List<Tache>> createCalendarMap(List<Tache> taches) {
        Map<Integer, List<Tache>> tachesMap = new HashMap<>();

        for (Tache tache: taches) {
            int tacheDate = tache.getDateLimite().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
            if(!tachesMap.containsKey(tacheDate)){
                tachesMap.put(tacheDate, List.of(tache));
            } else {
                List<Tache> OldListByDate = tachesMap.get(tacheDate);

                List<Tache> newList = new ArrayList<>(OldListByDate);
                newList.add(tache);
                tachesMap.put(tacheDate, newList);
            }
        }
        return  tachesMap;
    }

    private Map<Integer, List<Tache>> getCalendarTachesMonth(ZonedDateTime dateFocus) {
        List<Tache> taches = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27)+1, 16,0,0,0,dateFocus.getZone());
            taches.add(new Tache());
        }

        return createCalendarMap(taches);
    }
}