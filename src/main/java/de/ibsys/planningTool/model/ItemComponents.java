package de.ibsys.planningTool.model;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Che on 16.08.2016.
 */
public class ItemComponents {


    private String id;
    private String name;
    private Map<String, Integer> components;

    public ItemComponents() {}

    public ItemComponents(String id, String name, Map<String, Integer> components) {
        this.id = id;
        this.name = name;
        this.components = Collections.unmodifiableMap(components);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<String, Integer> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(!(obj instanceof ItemComponents)) {
            return false;
        }

        ItemComponents itemComponents = (ItemComponents) obj;
        return Objects.equals(id, itemComponents.getId());
    }

    @Override
    public String toString() {
        return "Item Components{" +
                "id=" + id +
                ", name=" + name +
                ", components=" + components +
                '}';
    }
}
