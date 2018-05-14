package by.epam.buhai.xml_analyzer.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.io.Serializable;

public final class Node implements Serializable {  // singleton?
    private NodeTypes type;
    private String name;

    public Node(NodeTypes type, String name) {
        this.type = type;
        this.name = name;
    }

    public Node() {
    }


    public NodeTypes getType() {
        return type;
    }


    public void setType(NodeTypes type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(getType(), node.getType())
                .append(getName(), node.getName())
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(getName())
                .toHashCode();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.getDescription());
        sb.append("=").append(name);
        return sb.toString();
    }
}
