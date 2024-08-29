package javaswingdev.form;

public class StagiaireBean {
    private int IdStg;

    public int getIdStg() {
        return IdStg;
    }

    public void setIdStg(int IdStg) {
        this.IdStg = IdStg;
    }
    private String NomStg;
    private String PrenomStg;
    private String Filiere;
    private String Promotion;

    public String getPromotion() {
        return Promotion;
    }

    public void setPromotion(String Promotion) {
        this.Promotion = Promotion;
    }

    public String getFiliere() {
        return Filiere;
    }

    public void setFiliere(String Filiere) {
        this.Filiere = Filiere;
    }


    public String getNomStg() {
        return NomStg;
    }

    public void setNomStg(String NomStg) {
        this.NomStg = NomStg;
    }

    public String getPrenomStg() {
        return PrenomStg;
    }

    public void setPrenomStg(String PrenomStg) {
        this.PrenomStg = PrenomStg;
    }
}