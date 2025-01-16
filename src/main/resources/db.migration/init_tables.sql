create table Employe (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(25) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL
);

create table Projet (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    date_limite DATE NOT NULL
);

create table Tache (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    priorite VARCHAR,
    date_limite DATE NOT NULL,
    categorie VARCHAR(50),
    commentaires TEXT,
    etat VARCHAR,
    projet_id INT NOT NULL,
    FOREIGN KEY (projet_id) REFERENCES Projet(id) ON DELETE CASCADE
);

create table Employe_Projet (
    employe_id INT,
    projet_id INT,
    PRIMARY KEY (employe_id, projet_id),
    FOREIGN KEY (employe_id) REFERENCES Employe(id) ON DELETE CASCADE,
    FOREIGN KEY (projet_id) REFERENCES Projet(id) ON DELETE CASCADE
);
