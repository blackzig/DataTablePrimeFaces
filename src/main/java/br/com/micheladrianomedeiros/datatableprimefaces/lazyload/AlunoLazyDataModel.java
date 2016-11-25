/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.datatableprimefaces.lazyload;

import br.com.micheladrianomedeiros.datatableprimefaces.model.Aluno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author michel
 */
public class AlunoLazyDataModel extends LazyDataModel<Aluno> {

    private List<Aluno> datasource;

    public AlunoLazyDataModel(List<Aluno> datasource) {
        this.datasource = datasource;
    }

    public List<Aluno> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {

        List<Aluno> data = new ArrayList<>();

        for (Aluno aluno : datasource) {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    String filterProperty = it.next();
                    Object filterValue = filters.get(filterProperty);

                    String fieldValue = "";
                    if (filterProperty.equals("nome")) {
                        fieldValue = aluno.getNome();
                    }

                    if (filterProperty.equals("curso")) {
                        fieldValue = aluno.getCurso();
                    }

                    if (fieldValue == null || fieldValue.startsWith(fieldValue.toString())) {
                        match = true;
                    } else {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(aluno);
            }
        }

        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        int dataSize = data.size();
        this.setRowCount(dataSize);

        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (Exception e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }

    }

}
