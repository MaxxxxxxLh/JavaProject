module com.isepA1.javaProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.security.crypto;
    requires spring.web;
    requires jakarta.persistence;
    requires spring.beans;
    requires spring.data.jpa;
    requires jakarta.validation;
    requires org.hibernate.orm.core;

    opens com.isepA1.javaProject to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.config to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.controller to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.mapper to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.model.dto to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.model.enums to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.model.postgres to spring.core, spring.beans, org.hibernate.orm.core;
    opens com.isepA1.javaProject.repository to javafx.fxml, spring.core, spring.beans;
    opens com.isepA1.javaProject.service to javafx.fxml, spring.core, spring.beans;

    exports com.isepA1.javaProject;
    exports com.isepA1.javaProject.config;
    exports com.isepA1.javaProject.controller;
    exports com.isepA1.javaProject.mapper;
    exports com.isepA1.javaProject.model.dto;
    exports com.isepA1.javaProject.model.enums;
    exports com.isepA1.javaProject.model.postgres;
    exports com.isepA1.javaProject.repository;
    exports com.isepA1.javaProject.service;
}
