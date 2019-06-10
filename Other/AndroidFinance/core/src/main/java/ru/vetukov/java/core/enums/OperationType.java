package ru.vetukov.java.core.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum OperationType {

    INCOME(1), OUTCOME(2), TRANSFER(3), CONVERT(4); // нумерация id - как в таблице

    private static Map<Integer, OperationType> map = new HashMap<Integer, OperationType>();

    static {
        for (OperationType oper : OperationType.values()) {
            map.put(oper.getId(), oper);
        }
    }

    private Integer id;

    private OperationType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static OperationType getType(int id) {
        return map.get(id);
    }


}
/*public class OperationType {

    private String name;
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationType that = (OperationType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
*/