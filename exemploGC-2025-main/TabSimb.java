import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TabSimb {
  // cada nível de escopo: uma lista de TS_entry
  private Stack<List<TS_entry>> escopos;

  public TabSimb() {
    escopos = new Stack<>();
    escopos.push(new ArrayList<>()); // escopo global
  }

  /** entra num novo escopo (e.g. ao começar a parse de uma função ou bloco) */
  public void enterScope() {
    escopos.push(new ArrayList<>());
  }

  /** sai do escopo atual */
  public void exitScope() {
    if (escopos.size() > 1) {
      escopos.pop();
    } else {
      System.err.println("Warning: tentou sair do escopo global!");
    }
  }

  /** insere no escopo atual */
  public void insert(TS_entry nodo) {
    escopos.peek().add(nodo);
  }

  /** pesquisa do escopo interno para o externo */
  public TS_entry pesquisa(String umId) {
    for (int i = escopos.size() - 1; i >= 0; i--) {
      for (TS_entry nodo : escopos.get(i)) {
        if (nodo.getId().equals(umId)) {
          return nodo;
        }
      }
    }
    return null;
  }

  /** gera dados globais: apenas o escopo 0 (global) */
  public void geraGlobais() {
    System.out.println("\n# área de dados globais");
    for (TS_entry nodo : escopos.get(0)) {
      // só funções causariam duplo label — pule elas
      if (nodo.getClasse() == ClasseID.Funcao)
        continue;

      // para variáveis (simples ou arrays), gera .zero
      System.out.printf("_%s:\t.zero %d\n",
          nodo.getId(),
          nodo.getNumElem() != -1
              ? nodo.getNumElem() * 4
              : 4);
    }
  }

  /** depuração: lista todos os escopos e suas entradas */
  public void listar() {
    System.out.println("\n# Tabela de Símbolos (por escopo):");
    for (int nivel = 0; nivel < escopos.size(); nivel++) {
      System.out.println("## Escopo “" + nivel + "”:");
      for (TS_entry nodo : escopos.get(nivel)) {
        System.out.println("   " + nodo);
      }
    }
  }
}
