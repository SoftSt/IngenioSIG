/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.newvi.sic.modelo;

import ec.com.newvi.sic.dto.FichaCatastralDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Andrés
 */
public class ModeloPredioLazy extends LazyDataModel<FichaCatastralDto> {

    private final List<FichaCatastralDto> listaFichaCatastralDtoFuente;

    public ModeloPredioLazy(List<FichaCatastralDto> listaFichaCatastralDtoFuente) {
        this.listaFichaCatastralDtoFuente = listaFichaCatastralDtoFuente;
    }

    @Override
    public FichaCatastralDto getRowData(String rowKey) {
        for (FichaCatastralDto fichaCatastralDto : listaFichaCatastralDtoFuente) {
            if (fichaCatastralDto.getPredio().getNomCodigocatastral().equals(rowKey)) {
                return fichaCatastralDto;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(FichaCatastralDto fichaCatastralDto) {
        return fichaCatastralDto.getPredio().getNomCodigocatastral();
    }

    @Override
    public List<FichaCatastralDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<FichaCatastralDto> data = new ArrayList<>();

        //filter
        for (FichaCatastralDto car : listaFichaCatastralDtoFuente) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(car);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

}
