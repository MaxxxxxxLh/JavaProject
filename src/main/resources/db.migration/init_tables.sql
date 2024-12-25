create table Employe (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(25) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

create table Projet (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    dateLimite DATE
);

create table Tache (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    priorite VARCHAR,
    dateLimite DATE,
    categorie VARCHAR(50),
    commentaires TEXT,
    etat VARCHAR,
    projetId INT,
    FOREIGN KEY (projetId) REFERENCES Projet(id) ON DELETE CASCADE
);

create table Employe_Projet (
    employeId INT,
    projetId INT,
    PRIMARY KEY (employeId, projetId),
    FOREIGN KEY (employeId) REFERENCES Employe(id) ON DELETE CASCADE,
    FOREIGN KEY (projetId) REFERENCES Projet(id) ON DELETE CASCADE
);
