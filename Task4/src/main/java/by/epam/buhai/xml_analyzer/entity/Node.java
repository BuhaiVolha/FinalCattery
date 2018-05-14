package by.epam.buhai.xml_analyzer.entity;

public final class Node {
    private NodeTypes type;
    private String name;
    private static Node node;

    private Node() {
    }


    public static Node getNode() {
        if (node == null) {
            node = new Node();
        }
        return node;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.getDescription());
        sb.append("=").append(name);
        return sb.toString();
    }
}
