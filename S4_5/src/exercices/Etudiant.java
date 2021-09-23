package exercices;

/**
 * Représente un étudiant.
 * Simple structure de donnée contenant les informations d'identification d'un étudiant.
 *
 * Un étudiant est un objet immutable
 */
public class Etudiant {

    /**
     * L'email de l'étudiant
     */
    private String email;
    /**
     * le matricule de l'étudiant
     */
    private String idNumber;
    /**
     * le nom de l'étudiant , est parfois null
     */
    private String lastName;
    /**
     * Le prénom de l'étudiant, est parfois null
     */
    private String firstName;

    /**
     * Crée un nouvel étudiant avec un email et un matricule mais sans nom ni prénom
     *
     * @param email l'email de l'étudiant
     * @param matricule le matricule de l'étudiant
     */
    public Etudiant(String email, String idNumber) {
        this.email = email;
        this.idNumber = idNumber;
    }

    /**
     * Crée un nouvel étudiant sur base d'une chaine de caractère dont les différents champs sont séparés par des ";"
     * L'ordre des champs est : matricule;nom;prenom;email
     *
     * @param toSplit la chaine de caractères incluant les différents champs.
     */
    public Etudiant(String toSplit) {
        String[] mots = toSplit.split(";");
        this.email = mots[3];
        this.idNumber = mots[0];
        this.lastName = mots[1];
        this.firstName = mots[2];
    }

    /**
     *
     * @return l'email de l'étudiant
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return le matricule de l'étudiant
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     *
     * @return une chaine de caractère reprenant les différentes informations d'identification de l'étudiant
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return lastName + " " + firstName + " [" + idNumber + "] - " + email;
    }

    /**
     *
     * @return le nom de l'étudiant (ou null si inconnu )
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return le prénom de l'étudiant (ou null si inconnu )
     */
    public String getFirstName() {
        return firstName;
    }
}
