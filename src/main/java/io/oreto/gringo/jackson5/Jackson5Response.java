package io.oreto.gringo.jackson5;

import io.oreto.jackson.Fields;


public class Jackson5Response {
    Object body;
    private Fields fields = Fields.Include("");
    private String name = "";
    private boolean pretty;

    public Jackson5Response() {
        fields = Fields.Include(null);
    }

    public Jackson5Response(Object body) {
        this.body = body;
    }

    public Jackson5Response fields(Fields fields) {
        this.fields = fields;
        return this;
    }
    public Jackson5Response name(String name) {
        this.name = name;
        return this;
    }
    public Jackson5Response name(Class<?> aClass) {
        return name(aClass.getName());
    }
    public Jackson5Response pretty(boolean pretty) {
        this.pretty = pretty;
        return this;
    }

    public Object getBody() {
        return body;
    }
    public Fields getFields() {
        return fields;
    }
    public String getName() {
        return name;
    }
    public boolean isPretty() {
        return pretty;
    }
}
