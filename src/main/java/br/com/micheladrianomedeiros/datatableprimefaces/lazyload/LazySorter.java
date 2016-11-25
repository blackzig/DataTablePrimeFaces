/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.datatableprimefaces.lazyload;

import br.com.micheladrianomedeiros.datatableprimefaces.model.Aluno;
import org.primefaces.model.SortOrder;
import java.util.Comparator;

/**
 *
 * @author michel
 */
public class LazySorter implements Comparator<Aluno> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Aluno aluno1, Aluno aluno2) {
        String value1 = "";
        String value2 = "";
        if (this.sortField.equals("nome")) {
            value1 = aluno1.getNome();
            value2 = aluno2.getNome();
        }

        if (this.sortField.equals("curso")) {
            value1 = aluno1.getCurso();
            value2 = aluno2.getCurso();
        }

        int value = ((Comparable) value1).compareTo(value2);
        return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
    }

}
