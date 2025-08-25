package com.avsofthealthcare.dto;

import com.avsofthealthcare.entity.*;

import java.util.List;

public class FullMedicineInfo {
    private String name;
    private double price;
    private boolean isDiscontinued;
    private String manufacturerName;
    private String type;
    private String packSizeLabel;
    private String habitForming;

    private String chemicalClass;
    private String therapeuticClass;
    private String actionClass;

    private List<String> sideEffects;
    private List<String> uses;
    private List<String> substitutes;
    private List<String> compositions;


    // --- Getters and Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isDiscontinued() { return isDiscontinued; }
    public void setDiscontinued(boolean discontinued) { isDiscontinued = discontinued; }

    public String getManufacturerName() { return manufacturerName; }
    public void setManufacturerName(String manufacturerName) { this.manufacturerName = manufacturerName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPackSizeLabel() { return packSizeLabel; }
    public void setPackSizeLabel(String packSizeLabel) { this.packSizeLabel = packSizeLabel; }

    public String getHabitForming() { return habitForming; }
    public void setHabitForming(String habitForming) { this.habitForming = habitForming; }

    public String getChemicalClass() { return chemicalClass; }
    public void setChemicalClass(String chemicalClass) { this.chemicalClass = chemicalClass; }

    public String getTherapeuticClass() { return therapeuticClass; }
    public void setTherapeuticClass(String therapeuticClass) { this.therapeuticClass = therapeuticClass; }

    public String getActionClass() { return actionClass; }
    public void setActionClass(String actionClass) { this.actionClass = actionClass; }

    public List<String> getSideEffects() { return sideEffects; }
    public void setSideEffects(List<String> sideEffects) { this.sideEffects = sideEffects; }

    public List<String> getUses() { return uses; }
    public void setUses(List<String> uses) { this.uses = uses; }

    public List<String> getSubstitutes() { return substitutes; }
    public void setSubstitutes(List<String> substitutes) { this.substitutes = substitutes; }

    public List<String> getCompositions() { return compositions; }
    public void setCompositions(List<String> compositions) { this.compositions = compositions; }
}
