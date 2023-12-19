INSERT INTO createur (id_createur, passwordd, login)
VALUES
    ('createur1', 'motdepasse1', 'login1'),
    ('createur2', 'motdepasse2', 'login2');
    
INSERT INTO film (id_film, titre, duree, langue, sous_titre, realisateur, acteurs_principaux, age_min, date_debut, date_fin, id_createur)
VALUES
    ('film1', 'Film 1', 120.50, 'Français', true, 'Réalisateur 1', 'Acteur 1, Acteur 2', 12, '2023-11-01', '2023-11-05', 'createur1'),
    ('film2', 'Film 2', 105.25, 'Anglais', false, 'Réalisateur 2', 'Acteur 3, Acteur 4', 16, '2023-11-02', '2023-11-06', 'createur2');

INSERT INTO cinema (id_cinema, ville, adresse)
VALUES
    ('cinema1', 'Paris', 'Adresse 1, Paris'),
    ('cinema2', 'Paris', 'Adresse 2, Paris');

INSERT INTO creneau_seance (id_seance, jour, heure_debut, heure_fin, salle, id_cinema)
VALUES
    ('seance1', 'Lundi', '14:00:00', '16:00:00', '26', 'cinema1'),
    ('seance2', 'Mardi', '16:30:00', '18:30:00', '12', 'cinema2');

INSERT INTO passer (id_film, id_seance)
VALUES
    ('film1', 'seance1'),
    ('film2', 'seance2');

INSERT INTO diffuser (id_film, id_cinema)
VALUES
    ('film1', 'cinema1'),
    ('film2', 'cinema2');

