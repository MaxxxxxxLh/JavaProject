package com.isepA1.javaProject.controller;

import com.isepA1.javaProject.model.postgres.Employe;
import com.isepA1.javaProject.model.postgres.Tache;
import com.isepA1.javaProject.service.EmailService;
import com.isepA1.javaProject.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class TacheNotificationSchedulerController {

    @Autowired
    private TacheService taskService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendTaskReminders() {
        Date tomorrow = Date.from((LocalDate.now().plusDays(1)).atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Tache> tasksDueTomorrow = taskService.findTacheByDateLimite(tomorrow);

        for (Tache tache : tasksDueTomorrow) {
            for (Employe employe : tache.getProjet().getMembres()) {
                String subject = "Rappel: Tache à finir demain";
                String body = String.format("Cher %s,\n\nLa tache '%s' est à finir demain.\n\nCordialement,\nLMAN App Team",
                        employe.getNom(), tache.getNom());
                emailService.sendEmail(employe.getEmail(), subject, body);
            }
        }
    }
}
