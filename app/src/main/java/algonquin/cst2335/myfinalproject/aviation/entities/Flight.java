package algonquin.cst2335.myfinalproject.aviation.entities;

import java.util.List;

import algonquin.cst2335.myfinalproject.aviation.DTO.DataDTO;
import algonquin.cst2335.myfinalproject.aviation.DTO.PaginationDTO;

public class Flight {

    private PaginationDTO pagination;
    private List<DataDTO> data;

    public PaginationDTO getPagination() {
        return pagination;
    }
    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }
    public List<DataDTO> getData() {
        return data;
    }
}