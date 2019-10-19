package backend.response;

import java.util.List;

import backend.model.Materi;

import java.util.ArrayList;

/**
 * Author : supi.core@gmail.com | github.com/sup1core  
 */

public class MateriResponse {
    public String status;
    public String message;
    public ArrayList<Materi> data;

    public MateriResponse(String status, String message, List<Materi> materi) {
        this.status = status;
        this.message = message;
        this.data = new ArrayList<Materi>();

        for(Materi item:materi) {
            this.data.add(item);
        }
    }

    public MateriResponse(String status, String message, Materi materi) {
        this.status = status;
        this.message = message;

        this.data = new ArrayList<Materi>();

        if(materi != null) {
            this.data.add(materi);   
        }
    }

    public MateriResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}