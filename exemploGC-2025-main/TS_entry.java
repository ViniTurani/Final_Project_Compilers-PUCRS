
public class TS_entry {
    private String id;
    private int tipo;
    private int nElem;
    private int tipoBase;
    private ClasseID classe;

    public TS_entry(String umId, int umTipo, int ne, int umTBase, ClasseID umaClasse) {
        id = umId;
        tipo = umTipo;
        nElem = ne;
        tipoBase = umTBase;
        classe = umaClasse;
    }

    public TS_entry(String umId, int umTipo, int ne, int umTBase) {
        id = umId;
        tipo = umTipo;
        nElem = ne;
        tipoBase = umTBase;
        classe = ClasseID.VarGlobal;
    }

    public TS_entry(String umId, int umTipo, ClasseID umaClasse) {
        this(umId, umTipo, -1, -1, umaClasse);
    }

    public TS_entry(String umId, int umTipo) {
        this(umId, umTipo, -1, -1);
    }

    public String getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public int getNumElem() {
        return nElem;
    }

    public int getTipoBase() {
        return tipoBase;
    }

    public ClasseID getClasse() {
        return classe;
    }

    public String toString() {
        String aux = (nElem != -1) ? "\t array(" + nElem + "): " + tipoBase : "";
        return "Id: " + id + "\t tipo: " + tipo + aux;
    }

}