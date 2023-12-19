CREATE TABLE createur(
   id_createur VARCHAR(50) PRIMARY KEY NOT NULL,
   passwordd VARCHAR(50),
   login VARCHAR(50)
);

CREATE TABLE film(
   id_film VARCHAR(50) PRIMARY KEY NOT NULL,
   titre VARCHAR(50),
   duree DECIMAL(15,2),
   langue VARCHAR(50),
   sous_titre BOOLEAN,
   realisateur VARCHAR(50),
   acteurs_principaux VARCHAR(50),
   age_min INT,
   date_debut DATE,
   date_fin DATE,
   id_createur VARCHAR(50) NOT NULL,
   FOREIGN KEY(id_createur) REFERENCES createur(id_createur)
);

CREATE TABLE cinema(
   id_cinema VARCHAR(50) PRIMARY KEY NOT NULL,
   ville VARCHAR(50),
   adresse VARCHAR(50)
);

CREATE TABLE creneau_seance(
   id_seance VARCHAR(50) PRIMARY KEY NOT NULL,
   jour VARCHAR(50),
   heure_debut TIME,
   heure_fin TIME,
   salle INT,
   id_cinema VARCHAR(50) NOT NULL,
   FOREIGN KEY(id_cinema) REFERENCES cinema(id_cinema)
);

CREATE TABLE passer(
   id_film VARCHAR(50),
   id_seance VARCHAR(50),
   PRIMARY KEY(id_film, id_seance),
   FOREIGN KEY(id_film) REFERENCES film(id_film),
   FOREIGN KEY(id_seance) REFERENCES creneau_seance(id_seance)
);

CREATE TABLE diffuser(
   id_film VARCHAR(50),
   id_cinema VARCHAR(50),
   PRIMARY KEY(id_film, id_cinema),
   FOREIGN KEY(id_film) REFERENCES film(id_film),
   FOREIGN KEY(id_cinema) REFERENCES cinema(id_cinema)
);

