package Modele;
/** Classe qui énumére les objectifs possibles dans le jeu..
 */
public enum Objectif {

    // Enumérations des objectifs (version Harry-Potter)
    JOURNAL("Journal de Tom Jedusor"),
    CROCHET("Crochet du Basillic"),
    MEDAILLON("Le médaillon de Serpentard"),
    DIADEME("Le diadème perdu de Serdaigle"),
    BAGUE("La bague d’Elvis Gaunt"),
    NAGINI("Nagini"),
    PIERRE("Pierre de ressurection"),
    CAPE("Cape d'invisibilité"),
    BAGUETE("Baguete de surrot"),
    CHOIXPEAU("Choixpeau magique"),
    COUPE("Coupe de feu"),
    EPEE("Epee de Gryffondor"),
    BALAI("Eclair de feu"),
    VIVE("Vive D\'or"),
    PIERRE_PHILO("Pierre philosophale"),
    HEDWIGE("Hedwige"),
    OEUF("Oeuf dorre"),
    CARTE("Carte du maraudeur"),
    SABLIER("Le Sablier du Professeur Horace Slughorn"),
    DAME("Grosse Dame"),
    DELIMNATEUR("Deluminateur"),
    VOITURE("Voiture volante"),
    RAPELTOUT("Le rapeltout"),
    LETTRE("Lettre de poudlard");

    // Déclaration des variables...
    private String description;

    /** Constructeur de l'Objectif
     * @param description > l'objectif.
     */
    private Objectif(String description) {
        this.description = description;
    }
    
    /** Méthode qui affiche l'objectif.
     * @return : l'objectif.
     */
    public String toString(){
        return this.description;
    }
}
