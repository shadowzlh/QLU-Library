package com.shadowzlh.lib.domain.Area;

public class ChildArea {
    private int id;

    private String name;

    private String enname;

    private int parentId;

    private int levels;

    private int isValid;

    private String comment;

    private int sort;

    private int type;

    private String color;

    private String TotalCount;

    private int UnavailableSpace;

    private int heat_open;

    private int webhidden;

    public ChildArea(){

    }
    public ChildArea(int id){
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setEnname(String enname){
        this.enname = enname;
    }
    public String getEnname(){
        return this.enname;
    }
    public void setParentId(int parentId){
        this.parentId = parentId;
    }
    public int getParentId(){
        return this.parentId;
    }
    public void setLevels(int levels){
        this.levels = levels;
    }
    public int getLevels(){
        return this.levels;
    }
    public void setIsValid(int isValid){
        this.isValid = isValid;
    }
    public int getIsValid(){
        return this.isValid;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }
    public void setSort(int sort){
        this.sort = sort;
    }
    public int getSort(){
        return this.sort;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setTotalCount(String TotalCount){
        this.TotalCount = TotalCount;
    }
    public String getTotalCount(){
        return this.TotalCount;
    }
    public void setUnavailableSpace(int UnavailableSpace){
        this.UnavailableSpace = UnavailableSpace;
    }
    public int getUnavailableSpace(){
        return this.UnavailableSpace;
    }
    public void setHeat_open(int heat_open){
        this.heat_open = heat_open;
    }
    public int getHeat_open(){
        return this.heat_open;
    }
    public void setWebhidden(int webhidden){
        this.webhidden = webhidden;
    }
    public int getWebhidden(){
        return this.webhidden;
    }
}

