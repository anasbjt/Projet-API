package com.projetrest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/films")
public class FilmResource {

    @GET
    @Path("/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilm(@PathParam("filmId") int filmId) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        Film film = null;

        try {
            // Exécuter une requête SQL pour récupérer les détails du film par son ID
            String query = "SELECT * FROM films WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Créer un objet Film à partir des données de la base de données
                film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setTitre(resultSet.getString("titre"));
                film.setDuree(resultSet.getDouble("duree"));
                // Ajoutez d'autres propriétés du film ici
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération du film").build();
        }

        if (film != null) {
            return Response.ok(film).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();
        List<Film> films = new ArrayList<>();

        try {
            // Exécuter une requête SQL pour récupérer tous les films
            String query = "SELECT * FROM films";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Créer des objets Film à partir des données de la base de données
                Film film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setTitre(resultSet.getString("titre"));
                film.setDuree(resultSet.getDouble("duree"));
                // Ajoutez d'autres propriétés du film ici
                films.add(film);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la récupération des films").build();
        }

        return Response.ok(films).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFilm(Film film) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            // Exécuter une requête SQL pour insérer un nouveau film dans la base de données
            String query = "INSERT INTO films (titre, duree) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, film.getTitre());
            statement.setDouble(2, film.getDuree());
            // Ajoutez d'autres propriétés du film à insérer
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.status(Response.Status.CREATED).entity("Film créé avec succès").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Échec de la création du film").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création du film").build();
        }
    }

    @PUT
    @Path("/{filmId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("filmId") int filmId, Film film) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            // Exécuter une requête SQL pour mettre à jour les détails du film par son ID
            String query = "UPDATE films SET titre = ?, duree = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, film.getTitre());
            statement.setDouble(2, film.getDuree());
            statement.setInt(3, filmId);
            // Ajoutez d'autres propriétés du film à mettre à jour
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Film mis à jour avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour du film").build();
        }
    }

    @DELETE
    @Path("/{filmId}")
    public Response deleteFilm(@PathParam("filmId") int filmId) {
        // Connexion à la base de données
        Connection conn = DatabaseUtil.getConnection();

        try {
            // Exécuter une requête SQL pour supprimer le film par son ID
            String query = "DELETE FROM films WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, filmId);
            int rows = statement.executeUpdate();
            statement.close();
            conn.close();

            if (rows > 0) {
                return Response.ok("Film supprimé avec succès").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Film introuvable").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du film").build();
        }
    }
}
