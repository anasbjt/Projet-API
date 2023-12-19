document.addEventListener("DOMContentLoaded", function () {
    // Fonction pour ajouter un film
    function ajouterFilm(titre, duree, langue, realisateur, acteurs, age, ville, adresse, dateDebut, affiche, description) {
        // Créer un nouvel élément d'article
        var nouvelArticle = document.createElement("article");
        nouvelArticle.className = "film";

        // Créer les éléments internes de l'article (image, titre, description)
        nouvelArticle.innerHTML = `
            <img src="${affiche}" alt="${titre}">
            <h2>${titre}</h2>
            <p>${description}</p>
        `;

        // Ajouter l'article à la section des films
        var sectionFilms = document.getElementById("films");
        sectionFilms.appendChild(nouvelArticle);
    }

    // Fonction pour gérer la soumission du formulaire
    function soumettreFormulaire(event) {
        event.preventDefault(); // Empêcher le formulaire de se soumettre normalement

        // Récupérer les valeurs du formulaire
        var titre = document.getElementById("titre").value;
        var duree = document.getElementById("duree").value;
        var langue = document.getElementById("langue").value;
        var realisateur = document.getElementById("realisateur").value;
        var acteurs = document.getElementById("acteurs").value;
        var age = document.getElementById("age").value;
        var ville = document.getElementById("ville").value;
        var adresse = document.getElementById("adresse").value;
        var dateDebut = document.getElementById("dateDebut").value;

        // Récupérer le chemin de l'affiche (image)
        var afficheInput = document.getElementById("affiche");
        var affiche = afficheInput.files[0]; // Le premier fichier sélectionné
        var afficheSrc = affiche ? URL.createObjectURL(affiche) : 'placeholder.jpg';

        // Ajouter le film à la première page
        ajouterFilm(titre, duree, langue, realisateur, acteurs, age, ville, adresse, dateDebut, afficheSrc, "Description du film ajouté.");

        // Réinitialiser le formulaire
        document.getElementById("filmForm").reset();
    }

    // Ajouter un gestionnaire d'événement pour la soumission du formulaire
    var formulaire = document.getElementById("filmForm");
    formulaire.addEventListener("submit", soumettreFormulaire);
});
