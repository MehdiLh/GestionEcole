package javaswingdev.form;

public class CompanyBean {
    private int IdCmp;
    private String NomCmp;
    private String AdresseCmp;
    private String Domaine;
    private String StatutJrd;
    private String NombreS;

    public int getIdCmp() {
        return IdCmp;
    }

    public void setIdCmp(int IdCmp) {
        this.IdCmp = IdCmp;
    }

    public String getNomCmp() {
        return NomCmp;
    }

    public void setNomCmp(String NomCmp) {
        this.NomCmp = NomCmp;
    }

    public String getAdresseCmp() {
        return AdresseCmp;
    }

    public void setAdresseCmp(String AdresseCmp) {
        this.AdresseCmp = AdresseCmp;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String Domaine) {
        this.Domaine = Domaine;
    }

    public String getStatutJrd() {
        return StatutJrd;
    }

    public void setStatutJrd(String StatutJrd) {
        this.StatutJrd = StatutJrd;
    }

    public String getNombreS() {
        return NombreS;
    }

    public void setNombreS(String NombreS) {
        this.NombreS = NombreS;
    }
    
    // Responsable de l'administration
    private String NomResponsable;
    private String FonctionResponsable;

    public String getNomResponsable() {
        return NomResponsable;
    }

    public void setNomResponsable(String NomResponsable) {
        this.NomResponsable = NomResponsable;
    }

    public String getFonctionResponsable() {
        return FonctionResponsable;
    }

    public void setFonctionResponsable(String FonctionResponsable) {
        this.FonctionResponsable = FonctionResponsable;
    }
    
}