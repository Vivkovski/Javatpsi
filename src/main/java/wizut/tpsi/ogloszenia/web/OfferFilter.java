/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.web;

import wizut.tpsi.ogloszenia.jpa.FuelType;

/**
 *
 * @author vivkovski
 */
public class OfferFilter {

    private Integer manufacturerId;

    private Integer modelId;

    private Integer yearFrom;

    private Integer yearTo;

    private Integer fuelTypeId;

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    public Integer getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Integer fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

}
