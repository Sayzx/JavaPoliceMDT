package com.sayzx.panel.locker;

public class CriminalRecord {
    private final int id;
    private String details;
    private final int userId;

    /**
     * Constructeur de la classe CriminalRecord.
     * @param id L'identifiant unique de l'infraction.
     * @param userId L'identifiant de l'utilisateur associé à cette infraction.
     */
    public CriminalRecord(int id, int userId) {
        this.id = id;
        this.userId = userId;
        this.details = "";
    }

    /**
     * Ajouter une nouvelle infraction.
     * @param newDetails Les détails de la nouvelle infraction.
     */
    public void addRecord(String newDetails) {
        if (!this.details.contains(newDetails)) {
            this.details += (this.details.isEmpty() ? "" : "\n") + newDetails;
        } else {
            System.out.println("Duplicate record not added.");
        }
    }


    /**
     * Obtenir les détails des infractions dans un format propre.
     * @return Les détails des infractions.
     */
    public String toString() {
        return String.format(
                """

                        ----------CRIMINAL RECORD ID: %d------------
                        UserID:   %d
                        Details: %s
                        -------------------------------------------""",
                id, userId, details);
    }
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }
}
