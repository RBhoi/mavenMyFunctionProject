package com.example;

import java.util.List;

public class FunctionWrapper {
    
    private List<Object> paradigmresults;
    private List<Object> brresults;
    private List<Object> mdmresults;
    private List<Object> dccsresults;

    public FunctionWrapper(List<Object> mdmresults) {
        this.mdmresults = mdmresults;
    }
    public FunctionWrapper(List<Object> paradigmresults,
            List<Object> brresults,
            List<Object> mdmresults,
            List<Object> dccsresults) {
        this.paradigmresults = paradigmresults;
        this.brresults = brresults;
        this.mdmresults = mdmresults;
        this.dccsresults = dccsresults;
    }

    public List<Object> getParadigmresults() {
        return paradigmresults;
    }
    public List<Object> getBrresults() {
        return brresults;
    }
    public List<Object> getMdmresults() {
        return mdmresults;
    }
    public List<Object> getDccsresults() {
        return dccsresults;
    }
    public void setParadigmresults(List<Object> paradigmresults) {
        this.paradigmresults = paradigmresults;
    }

    public void setBrresults(List<Object> brresults) {
        this.brresults = brresults;
    }

    public void setMdmresults(List<Object> mdmresults) {
        this.mdmresults = mdmresults;
    }

    public void setDccsresults(List<Object> dccsresults) {
        this.dccsresults = dccsresults;
    }
}
