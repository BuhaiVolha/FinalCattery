package by.epam.buhai.xml_parser.entity;

public final class Node {
    private NodeTypes type;
    private String content;
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


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.getDescription());
        sb.append("=").append(content);
        return sb.toString();
    }
}
