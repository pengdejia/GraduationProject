package Test.Chinese;

/**
 * user:lenovo
 * email:2901949379@qq.com
 * date:2019/3/1
 * project name:NLP
 * package name:Test.Chinese
 **/
public class NumAttribute {
    public NumAttribute(String name){
        NameOfAttribute = name;
    }
    public String getNameOfAttribute() {
        return NameOfAttribute;
    }

    public String getNameofEntity() {
        return NameofEntity;
    }

    public String getValue() {
        return Value;
    }

    public String getMeasurement() {
        return Measurement;
    }

    public void setNameOfAttribute(String nameOfAttribute) {
        NameOfAttribute = nameOfAttribute;
    }

    public void setNameofEntity(String nameofEntity) {
        NameofEntity = nameofEntity;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setMeasurement(String measurement) {
        Measurement = measurement;
    }
    public String toString(){
        String result = NameOfAttribute + "," + NameofEntity + "," + Value + "," + Measurement;
        return result;
    }
    /**
     *分别定义
     * NameofAttribute表示属性的名称、
     * NameOfEntity表示修饰实体的名称、
     *Value表示该数量型属性的值、
     * Measure表示该属性的单位。
     */
    private String NameOfAttribute = "";
    private String NameofEntity = "";
    private String Value = "";
    private String Measurement = "";

}
