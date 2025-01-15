module com.isepA1.javaProject {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.security.crypto;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires jakarta.validation;
    requires spring.context.support;
    requires jakarta.mail;


    opens com.isepA1.javaProject to javafx.fxml, spring.core, spring.beans, org.hibernate.orm.core;
    opens com.isepA1.javaProject.controller to javafx.fxml, spring.core;
    opens com.isepA1.javaProject.service to spring.core;
    opens com.isepA1.javaProject.model.postgres to spring.core, spring.beans, org.hibernate.orm.core;

    exports com.isepA1.javaProject.controller to spring.beans;
    exports com.isepA1.javaProject.config to spring.beans;
    exports com.isepA1.javaProject.service to spring.beans;
    exports com.isepA1.javaProject;
    exports com.isepA1.javaProject.api to spring.beans;
    opens com.isepA1.javaProject.api to javafx.fxml, spring.core;
}
