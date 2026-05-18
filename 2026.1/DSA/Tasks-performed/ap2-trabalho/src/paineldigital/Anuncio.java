package paineldigital;

public class Anuncio {
    private static int contadorId = 0;

    private int id;
    private String Empresa;
    private String descricao;

    public Anuncio(String empresa, String descricao) {
        this.id = contadorId++;
        this.Empresa = empresa;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String nomeEmpresa) {
        this.Empresa = nomeEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Anuncio.contadorId = contadorId;
    }
}
